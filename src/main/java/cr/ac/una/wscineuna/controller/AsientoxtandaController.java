/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.AsientoxtandaDto;
import cr.ac.una.wscineuna.service.AsientoxtandaService;
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
@Path("/AsientoxtandaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AsientoxtandaController {
    
    @EJB
    AsientoxtandaService asientoxtandaService;
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response
                .ok("asientoxtanda")
                .build();
    }
    
    
    @GET
    @Path("/asientoxtandas/{id}")
    public Response getAsientoxtanda(@PathParam("id") Long id) {
        try {
            Respuesta res = asientoxtandaService.getAsientoxtanda(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            AsientoxtandaDto asientoxtandaDto = (AsientoxtandaDto) res.getResultado("Asientoxtanda");
            return Response.ok(asientoxtandaDto).build();
        } catch (Exception ex) {
            Logger.getLogger(AsientoxtandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el asientoxtanda").build();

        }
    }
    @GET
    @Path("/asientosxtandas/{idTanda}")
    public Response getAsientoxtandas(@PathParam("idTanda") Long idTanda) {
        try {
            Respuesta res = asientoxtandaService.getAsientosxtandas(idTanda);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            return Response.ok(new GenericEntity<List<AsientoxtandaDto>>((List<AsientoxtandaDto>) res.getResultado("Asientoxtandas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(AsientoxtandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo lista de asientoxtandas").build();
        }
    }
    
    @GET
    @Path("/asientosxtandas")
    public Response getAsientsoxtandas() {
        try {
            Respuesta res = asientoxtandaService.getAsientosxtandas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            return Response.ok(new GenericEntity<List<AsientoxtandaDto>>((List<AsientoxtandaDto>) res.getResultado("Asientoxtandas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(AsientoxtandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo lista de asientoxtandas").build();
        }
    }
    @POST
    public Response guardarAsientoxtanda(AsientoxtandaDto asientoxtanda) {
        try {
            Respuesta res = asientoxtandaService.guardarAsientoxtanda(asientoxtanda);
            if (!res.getEstado()) {
                 return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((AsientoxtandaDto) res.getResultado("Asientoxtanda")).build();
        } catch (Exception ex) {
            Logger.getLogger(AsientoxtandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el asientoxtanda").build();
        }
    }
    
    @DELETE
    @Path("/asientoxtandas/{id}")
    public Response eliminarAsientoxtanda(@PathParam("id")Long id) {
        try {
            Respuesta res = asientoxtandaService.eliminarAsientoxtanda(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(AsientoxtandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el asientoxtanda").build();
        }
    }
    
}
