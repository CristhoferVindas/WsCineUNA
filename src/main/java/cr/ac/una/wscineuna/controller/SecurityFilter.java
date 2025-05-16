package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.util.JwTokenHelper;
import cr.ac.una.wscineuna.util.Secure;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import java.security.Principal;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;

@Provider
@Secure
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_SERVICE_PATH = "getUsuario";
    private static final String RENEWAL_SERVICE_PATH = "renovarToken";    
    private static final String AUTHENTICATION_SCHEME = "Bearer ";
    private final JwTokenHelper jwTokenHelper = JwTokenHelper.getInstance();

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        Method method = resourceInfo.getResourceMethod();

        // Permitir acceso sin token al método de autenticación
        if (method.getName().equals(AUTHORIZATION_SERVICE_PATH)) {
            return;
        }

        // Validar header Authorization
        String authorizationHeader = request.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            abortWithUnauthorized(request, "Falta el header Authorization.");
            return;
        }

        if (!authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase())) {
            abortWithUnauthorized(request, "Autenticación inválida.");
            return;
        }

        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            Claims claims = jwTokenHelper.claimKey(token);

            // Si es para renovación, verificar el claim especial "rnw"
            if (method.getName().equals(RENEWAL_SERVICE_PATH)) {
                if (!(boolean) claims.getOrDefault("rnw", false)) {
                    abortWithUnauthorized(request, "Token no autorizado para renovación.");
                    return;
                }
            }

            final SecurityContext currentSecurityContext = request.getSecurityContext();
            request.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return () -> claims.getSubject();
                }

                @Override
                public boolean isUserInRole(String role) {
                    return true;
                }

                @Override
                public boolean isSecure() {
                    return currentSecurityContext.isSecure();
                }

                @Override
                public String getAuthenticationScheme() {
                    return AUTHENTICATION_SCHEME;
                }
            });

        } catch (ExpiredJwtException e) {
            abortWithUnauthorized(request, "Token expirado.");
        } catch (MalformedJwtException e) {
            abortWithUnauthorized(request, "Token mal formado.");
        } catch (Exception e) {
            abortWithUnauthorized(request, "Token inválido.");
        }
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext, String message) {
        requestContext.abortWith(
            Response.status(Response.Status.UNAUTHORIZED)
                    .header(HttpHeaders.WWW_AUTHENTICATE, "Bearer")
                    .entity(message)
                    .build()
        );
    }
}
