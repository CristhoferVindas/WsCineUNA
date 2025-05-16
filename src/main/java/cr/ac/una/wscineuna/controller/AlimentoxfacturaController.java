/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.AlimentoxfacturaDto;
import cr.ac.una.wscineuna.service.AlimentoxfacturaService;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Cristhofer
 */
@Path("/AlimentoxfacturaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlimentoxfacturaController {
    
    @EJB
    AlimentoxfacturaService alimentoxfacturaService;
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response
                .ok("alimentoxfactura")
                .build();
    }
    
    
    @GET
    @Path("/alimentoxfacturas/{id}")
    public Response getAlimentoxfactura(@PathParam("id") Long id) {
        try {
            Respuesta res = alimentoxfacturaService.getAlimentoxfactura(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            AlimentoxfacturaDto alimentoxfacturaDto = (AlimentoxfacturaDto) res.getResultado("Alimentoxfactura");
            return Response.ok(alimentoxfacturaDto).build();
        } catch (Exception ex) {
            Logger.getLogger(AlimentoxfacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el alimentoxfactura").build();

        }
    }
    
    @POST
    public Response guardarAlimentoxfactura(AlimentoxfacturaDto alimentoxfactura) {
        try {
            Respuesta res = alimentoxfacturaService.guardarAlimentoxfactura(alimentoxfactura);
            if (!res.getEstado()) {
                 return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((AlimentoxfacturaDto) res.getResultado("Alimentoxfactura")).build();
        } catch (Exception ex) {
            Logger.getLogger(AlimentoxfacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el alimentoxfactura").build();
        }
    }
    
    @DELETE
    @Path("/alimentoxfacturas/{id}")
    public Response eliminarAlimentoxfactura(@PathParam("id")Long id) {
        try {
            Respuesta res = alimentoxfacturaService.eliminarAlimentoxfactura(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(AlimentoxfacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el alimentoxfactura").build();
        }
    }
    
}
