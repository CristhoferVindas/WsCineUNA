/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.TandaDto;
import cr.ac.una.wscineuna.service.TandaService;
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
@Path("/TandaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class TandaController {

    @EJB
    TandaService tandaService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response
                .ok("tanda")
                .build();
    }

    @GET
    @Path("/tandas/{id}")
    public Response getTanda(@PathParam("id") Long id) {
        try {
            Respuesta res = tandaService.getTanda(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            TandaDto tandaDto = (TandaDto) res.getResultado("Tanda");
            tandaDto.setIDPelicula(tandaDto.getIdPelicula().getPelId());
            tandaDto.setIDSala(tandaDto.getIdSala().getSalId());

            return Response.ok(tandaDto).build();
        } catch (Exception ex) {
            Logger.getLogger(TandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la tanda").build();

        }
    }

    @GET
    @Path("/tandasP/{pelicula}")
    public Response getTandasxpelicula(@PathParam("pelicula") Long pelicula) {
        try {
            Respuesta res = tandaService.getTandas(pelicula);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            return Response.ok(new GenericEntity<List<TandaDto>>((List<TandaDto>) res.getResultado("Tandas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo lista de tandas").build();
        }
    }

    @POST
    public Response guardarTanda(TandaDto tanda) {
        try {
            Respuesta res = tandaService.guardarTanda(tanda);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((TandaDto) res.getResultado("Tanda")).build();
        } catch (Exception ex) {
            Logger.getLogger(TandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la tanda").build();
        }
    }

    @DELETE
    @Path("/tandas/{id}")
    public Response eliminarTanda(@PathParam("id") Long id) {
        try {
            Respuesta res = tandaService.eliminarTanda(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(TandaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando la tanda").build();
        }
    }

}
