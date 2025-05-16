/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.PeliculaDto;
import cr.ac.una.wscineuna.service.PeliculaService;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import cr.ac.una.wscineuna.util.TableViewFacturas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Base64;
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
@Path("/PeliculaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeliculaController {

    @EJB
    PeliculaService peliculaService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response
                .ok("pelicula")
                .build();
    }

    @GET
    @Path("/peliculas/{id}")
    public Response getPelicula(@PathParam("id") Long id) {
        try {
            Respuesta res = peliculaService.getPelicula(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            
            PeliculaDto peliculaDto = (PeliculaDto) res.getResultado("Pelicula");
            
            File portadaInglesFile = new File("..\\Portadas\\" + peliculaDto.getPortadaIngles());
            FileInputStream portadaInglesInputStream = new FileInputStream(portadaInglesFile);
            byte[] portadaInglesByte = portadaInglesInputStream.readAllBytes();
            String portadaInglesEncode = Base64.getEncoder().encodeToString(portadaInglesByte);
            peliculaDto.setPortadaIngles(portadaInglesEncode);
            portadaInglesInputStream.close();
            
            File portadaEspanolFile = new File("..\\Portadas\\" + peliculaDto.getPortadaEspannol());
            FileInputStream portadaEspanolInputStream = new FileInputStream(portadaEspanolFile);
            byte[] portadaEspanolByte = portadaEspanolInputStream.readAllBytes();
            String portadaEspanolEncoded = Base64.getEncoder().encodeToString(portadaEspanolByte);
            peliculaDto.setPortadaEspannol(portadaEspanolEncoded);
            portadaEspanolInputStream.close();

            return Response.ok(peliculaDto).build();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la pelicula").build();

        }
    }

    
    
    @GET
    @Path("/peliculas/{nombreEspannol}/{nombreIngles}") ///{fechaEstreno}
    public Response getPeliculas(@PathParam("nombreEspannol") String nombreEspannol, @PathParam("nombreIngles") String nombreIngles) { //, @PathParam("fechaEstreno")String fechaEstreno
        try {
            Respuesta res = peliculaService.getPeliculas(nombreEspannol, nombreIngles);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            return Response.ok(new GenericEntity<List<PeliculaDto>>((List<PeliculaDto>) res.getResultado("Peliculas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo lista de peliculas").build();
        }
    }
    
    @GET
    @Path("/peliculas") ///{fechaEstreno}
    public Response getAllPeliculas() { //, @PathParam("fechaEstreno")String fechaEstreno
        try {
            Respuesta res = peliculaService.getAllPeliculas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            return Response.ok(new GenericEntity<List<PeliculaDto>>((List<PeliculaDto>) res.getResultado("Peliculas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo lista de peliculas").build();
        }
    }
    
    @GET
    @Path("/rpeliculas/{fechainicio}/{fechafin}/{estado}")
    public Response getReporteBtwFecha(@PathParam("fechainicio") String fechainicio, @PathParam("fechafin") String fechafin,@PathParam("estado") String estado) {
        try {
            Respuesta res = peliculaService.ReporteCantPelBtwnFecha(fechainicio, fechafin, estado);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
           
            String PdfString = res.getResultado("Peliculas").toString();
           
            return Response.ok(PdfString).build();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la pelicula").build();

        }
    }
    
        @GET
    @Path("/rpeliculas/{peliculaEspanol}/{peliculaIngles}")
    public Response getReporteByPelicula(@PathParam("peliculaEspanol") String peliculaEspanol, @PathParam("peliculaIngles") String peliculaIngles) {
        try {
            Respuesta res = peliculaService.ReporteByPelicula(peliculaEspanol, peliculaIngles);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
           
            String PdfString = res.getResultado("Peliculas").toString();
           
            return Response.ok(PdfString).build();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la pelicula").build();

        }
    }
    
    @GET
    @Path("/peliculasE/{estado}")
    public Response getPeliculasPorEstrenarse(@PathParam("estado") String estado) { 
        try {
            Respuesta res = peliculaService.getPeliculasPorEstrenarse(estado);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            
            return Response.ok(new GenericEntity<List<PeliculaDto>>((List<PeliculaDto>) res.getResultado("Peliculas")) {}).build();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo lista de peliculas").build();
        }
    }

    @POST
    public Response guardarPelicula(PeliculaDto pelicula) {
        try {
            
            File portadasDir = new File("..\\Portadas"); //"C:\\Users\\Cristhofer\\Desktop\\UNA\\CineUNA(Cliente-Servidor)\\WsCineUNA\\Imagenes\\+"
            if (!portadasDir.exists()){
                portadasDir.mkdir();
                portadasDir.setWritable(true);
            }

            byte[] portadaEspanolDecode = Base64.getDecoder().decode(new String(pelicula.getPortadaEspannol()));
            File portadaEspanolFile = new File(portadasDir.getPath() + "\\" + pelicula.getNombreEspannol()+ "_Espanol.jpg");
            FileOutputStream portadaEspanolOutputStream = new FileOutputStream(portadaEspanolFile);
            portadaEspanolOutputStream.write(portadaEspanolDecode);
            pelicula.setPortadaEspannol(pelicula.getNombreEspannol()+ "_Espanol.jpg");
            portadaEspanolOutputStream.close();

            byte[] portadaInglesDecode = Base64.getDecoder().decode(new String(pelicula.getPortadaIngles()));
            File portadaInglesFile = new File(portadasDir.getPath() + "\\" + pelicula.getNombreIngles()+ "_Ingles.jpg");
            FileOutputStream portadaInglesOutputStream = new FileOutputStream(portadaInglesFile);
            portadaInglesOutputStream.write(portadaInglesDecode);
            pelicula.setPortadaIngles(pelicula.getNombreIngles()+ "_Ingles.jpg");
            portadaInglesOutputStream.close();
            
            Respuesta res = peliculaService.guardarPelicula(pelicula);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((PeliculaDto) res.getResultado("Pelicula")).build();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la pelicula").build();
        }
    }

    @DELETE
    @Path("/peliculas/{id}")
    public Response eliminarPelicula(@PathParam("id") Long id) {
        try {
            Respuesta res = peliculaService.eliminarPelicula(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando la pelicula").build();
        }
    }

}
