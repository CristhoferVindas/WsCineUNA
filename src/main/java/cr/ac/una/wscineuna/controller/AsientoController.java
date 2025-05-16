/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.AsientoDto;
import cr.ac.una.wscineuna.model.PeliculaDto;
import cr.ac.una.wscineuna.service.AsientoService;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import java.util.List;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Cristhofer
 */
@Path("/AsientoController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AsientoController {
    @EJB
    AsientoService asientoService;
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response
                .ok("asiento")
                .build();
    }
    
    
    @GET
    @Path("/asientos/{id}")
    public Response getAsiento(@PathParam("id") Long id) {
        try {
            Respuesta res = asientoService.getAsiento(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            AsientoDto asientoDto = (AsientoDto) res.getResultado("Asiento");
            return Response.ok(asientoDto).build();
        } catch (Exception ex) {
            Logger.getLogger(AsientoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el asiento").build();

        }
    }
    
    @GET
    @Path("/asientos")
    public Response getAsientos() {
        try {
            Respuesta res = asientoService.getAsientos();
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            return Response.ok(new GenericEntity<List<AsientoDto>>((List<AsientoDto>) res.getResultado("Asientos")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(AsientoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el asiento").build();

        }
    }
    
    @POST
    public Response guardarAsiento(AsientoDto asiento) {
        try {
            Respuesta res = asientoService.guardarAsiento(asiento);
            if (!res.getEstado()) {
                 return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((AsientoDto) res.getResultado("Asiento")).build();
        } catch (Exception ex) {
            Logger.getLogger(AsientoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el asiento").build();
        }
    }
    
    @DELETE
    @Path("/asientos/{id}")
    public Response eliminarAsiento(@PathParam("id")Long id) {
        try {
            Respuesta res = asientoService.eliminarAsiento(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(AsientoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el asiento").build();
        }
    }
}
