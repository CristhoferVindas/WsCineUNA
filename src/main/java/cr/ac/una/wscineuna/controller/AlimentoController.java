/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.AlimentoDto;
import cr.ac.una.wscineuna.service.AlimentoService;
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
@Path("/AlimentoController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlimentoController {
    
    @EJB
    AlimentoService alimentoService;
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response
                .ok("alimento")
                .build();
    }
    
    
    @GET
    @Path("/alimentos/{id}")
    public Response getAlimento(@PathParam("id") Long id) {
        try {
            Respuesta res = alimentoService.getAlimento(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            AlimentoDto alimentoDto = (AlimentoDto) res.getResultado("Alimento");
            return Response.ok(alimentoDto).build();
        } catch (Exception ex) {
            Logger.getLogger(AlimentoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el alimento").build();

        }
    }
    @GET
    @Path("/alimentos")
    public Response getAlimentos() {
        try {
            Respuesta res = alimentoService.getAlimentos();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            return Response.ok(new GenericEntity<List<AlimentoDto>>((List<AlimentoDto>) res.getResultado("Alimentos")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(AlimentoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo lista de Alimentos").build();
        }
    }
    
    @POST
    public Response guardarAlimento(AlimentoDto alimento) {
        try {
            Respuesta res = alimentoService.guardarAlimento(alimento);
            if (!res.getEstado()) {
                 return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((AlimentoDto) res.getResultado("Alimento")).build();
        } catch (Exception ex) {
            Logger.getLogger(AlimentoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el alimento").build();
        }
    }
    
    @DELETE
    @Path("/alimentos/{id}")
    public Response eliminarAlimento(@PathParam("id")Long id) {
        try {
            Respuesta res = alimentoService.eliminarAlimento(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(AlimentoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el alimento").build();
        }
    }
    
}
