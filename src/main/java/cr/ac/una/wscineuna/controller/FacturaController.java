/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.FacturaDto;
import cr.ac.una.wscineuna.service.FacturaService;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import cr.ac.una.wscineuna.util.TableViewFacturas;
import java.time.LocalDateTime;
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
@Path("/FacturaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacturaController {
    
    @EJB
    FacturaService facturaService;
    
    @GET
    @Path("/ping")
    public Response ping(){
        return Response
                .ok("factura")
                .build();
    }
    
    
    @GET
    @Path("/facturas/{id}")
    public Response getFactura(@PathParam("id") Long id) {
        try {
            Respuesta res = facturaService.getFactura(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            FacturaDto facturaDto = (FacturaDto) res.getResultado("Factura");
            return Response.ok(facturaDto).build();
        } catch (Exception ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la factura").build();

        }
    }
    @GET
    @Path("/fechafacturas")
    public Response getFechaFactura() {
        try {
            Respuesta res = facturaService.getFechaFactura();
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            LocalDateTime facturaDto = (LocalDateTime) res.getResultado("Factura");
            return Response.ok(facturaDto).build();
        } catch (Exception ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la factura").build();

        }
    }
    @GET
    @Path("/detallefacturas/{idtanda}")
    public Response getDetalleFactura(@PathParam("idtanda") Long idtanda) {
        try {
            Respuesta res = facturaService.getDetalleFactura(idtanda);//getFactura(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            return Response.ok(new GenericEntity<List<TableViewFacturas>>((List<TableViewFacturas>) res.getResultado("Facturas")) {}).build();
        } catch (Exception ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la factura").build();

        }
    }
    
    @GET
    @Path("/comprobantefactura/{idFactura}")
    public Response getComprobanteFactura(@PathParam("idFactura") Long idFactura) {
        try {
            Respuesta res = facturaService.getComprobanteFactura(idFactura);//getFactura(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            String PdfString = res.getResultado("Facturas").toString();
            return Response.ok(PdfString).build();
        } catch (Exception ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la factura").build();

        }
    }
    @GET
    @Path("/comprobantefacturaalimento/{idFactura}")
    public Response getComprobanteFacturaAlimento(@PathParam("idFactura") Long idFactura) {
        try {
            Respuesta res = facturaService.getComprobanteFacturaAlimento(idFactura);//getFactura(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            String PdfString = res.getResultado("Facturas").toString();
            return Response.ok(PdfString).build();
        } catch (Exception ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la factura").build();

        }
    }
    
    @POST
    public Response guardarFactura(FacturaDto factura) {
        try {
            Respuesta res = facturaService.guardarFactura(factura);
            if (!res.getEstado()) {
                 return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((FacturaDto) res.getResultado("Factura")).build();
        } catch (Exception ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la factura").build();
        }
    }
    
    @DELETE
    @Path("/facturas/{id}")
    public Response eliminarFactura(@PathParam("id")Long id) {
        try {
            Respuesta res = facturaService.eliminarFactura(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando la factura").build();
        }
    }
    
}
