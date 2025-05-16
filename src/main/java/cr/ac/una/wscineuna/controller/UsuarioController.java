/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.UsuarioDto;
import cr.ac.una.wscineuna.service.UsuarioService;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.JwTokenHelper;
import cr.ac.una.wscineuna.util.Respuesta;
import cr.ac.una.wscineuna.util.Secure;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Cristhofer
 */
@Path("/UsuarioController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UsuarioController {
    
    @EJB
    UsuarioService usuarioService;
    
    @Context
    SecurityContext securityContext;
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response
                .ok("usuario")
                .build();
    }
    @GET
    @Path("/renovar")
    @Secure
    public Response renovarToken() {
        try {
            if (securityContext.getUserPrincipal() == null) {
                return Response.status(CodigoRespuesta.ERROR_PERMISOS.getValue()).entity("Usuario no autenticado").build();
            }
            String usuarioRequest = securityContext.getUserPrincipal().getName();
            return Response.ok(JwTokenHelper.getInstance().generatePrivateKey(usuarioRequest)).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error renovando el token").build();
        }
    }

    @GET
    @Path("/usuarios/{usuario}/{contrasena}")
    public Response getUsuario(@PathParam("usuario")String usuario,@PathParam("contrasena") String contrasena) {
        try {
            Respuesta res = usuarioService.validarUsuario(usuario, contrasena);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            UsuarioDto usuarioDto = (UsuarioDto) res.getResultado("Usuario");
            usuarioDto.setToken(JwTokenHelper.getInstance().generatePrivateKey(usuario));
            return Response.ok(usuarioDto).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }
    
    @GET
    @Path("/usuarios/{id}")
    public Response getUsuario(@PathParam("id") Long id) {
        try {
            Respuesta res = usuarioService.getUsuario(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            UsuarioDto usuarioDto = (UsuarioDto) res.getResultado("Usuario");
            return Response.ok(usuarioDto).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la usuario").build();

        }
    }
@GET
    @Path("/usuarioActivo/{id}")
    public Response activacionUsuario(@PathParam("id")Long id) {
        try {
            Respuesta res = usuarioService.activacionUsuario(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok("Activación de usuario exitosa").build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la usuario").build();
        }
    }
    
    @POST
    public Response guardarUsuario(UsuarioDto usuario) {
        try {
            Respuesta res = usuarioService.guardarUsuario(usuario);
            if (!res.getEstado()) {
                 return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((UsuarioDto) res.getResultado("Usuario")).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la usuario").build();
        }
    }
    
    @DELETE
    @Path("/usuarios/{id}")
    public Response eliminarUsuario(@PathParam("id")Long id) {
        try {
            Respuesta res = usuarioService.eliminarUsuario(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando la usuario").build();
        }
    }
  @GET
    @Path("/usuarioCorreo/{correo}")
    public Response getUsuarioPorCorreo(@PathParam("correo") String correo) {
        try {
            Respuesta res = usuarioService.getUsuarioPorCorreo(correo);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            UsuarioDto usuarioDto = (UsuarioDto) res.getResultado("Usuario");
            return Response.ok(usuarioDto).build();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la usuario con correo").build();

        }
    }
    
}
