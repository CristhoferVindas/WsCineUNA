/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.SalaDto;
import cr.ac.una.wscineuna.service.SalaService;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
@Path("/SalaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SalaController {

    @EJB
    SalaService salaService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response
                .ok("sala")
                .build();
    }

    @GET
    @Path("/salas/{id}")
    public Response getSala(@PathParam("id") Long id) {
        try {
            Respuesta res = salaService.getSala(id);
            //String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            SalaDto salaDto = (SalaDto) res.getResultado("Sala");
            File asientoFile = new File("..\\Asientos\\" + salaDto.getImagenAsiento());
            FileInputStream fileFileInputStream = new FileInputStream(asientoFile);
            byte[] asientoByte = fileFileInputStream.readAllBytes();
            String asientob64 = Base64.getEncoder().encodeToString(asientoByte);
            salaDto.setImagenAsiento(asientob64);
            fileFileInputStream.close();
            
            File fondoFile = new File("..\\Asientos\\" + salaDto.getImagenFondo());
            FileInputStream fileFileInputStream1 = new FileInputStream(fondoFile);
            byte[] fondoByte = fileFileInputStream1.readAllBytes();
            String fondoB64 = Base64.getEncoder().encodeToString(fondoByte);
            salaDto.setImagenFondo(fondoB64);
            fileFileInputStream1.close();
            return Response.ok(salaDto).build();
        } catch (Exception ex) {
            Logger.getLogger(SalaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la sala").build();

        }
    }

    @GET
    @Path("/salas/{nombre}/{detalle}")
    public Response getSalas(@PathParam("nombre") String nombre, @PathParam("detalle") String detalle) {
        try {
            Respuesta res = salaService.getSalas(nombre, detalle);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();

            }
            return Response.ok(new GenericEntity<List<SalaDto>>((List<SalaDto>) res.getResultado("Salas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(SalaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo lista de salas").build();
        }
    }

    @POST
    public Response guardarSala(SalaDto sala) {
        try {

            byte[] resultImagen = Base64.getDecoder().decode(new String(sala.getImagenAsiento()));
            byte[] resultFondo = Base64.getDecoder().decode(new String(sala.getImagenFondo()));
            File directory = new File("..\\Asientos"); //"C:\\Users\\Cristhofer\\Desktop\\UNA\\CineUNA(Cliente-Servidor)\\WsCineUNA\\Imagenes\\+"
            if (!directory.exists()) {
                if (directory.mkdir()) {
                    System.out.println("Creado");

                } else {
                    System.out.println("Noooooooo");
                }
            }
            File imagenAsiento = new File(directory.getPath() + "\\" + sala.getNombre() + "asi.jpg");
            imagenAsiento.setWritable(true);
            FileOutputStream fileWriter1 = new FileOutputStream(imagenAsiento);
            fileWriter1.write(resultImagen);
            fileWriter1.close();
             File imagenFondo = new File(directory.getPath() + "\\" + sala.getNombre() + "fon.jpg");
            imagenFondo.setWritable(true);
            FileOutputStream fileWriter = new FileOutputStream(imagenFondo);
            fileWriter.write(resultFondo);
            fileWriter.close();
            
            sala.setImagenAsiento(sala.getNombre() + "asi.jpg");
            sala.setImagenFondo(sala.getNombre() + "fon.jpg");
            
            Respuesta res = salaService.guardarSala(sala);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            SalaDto salaDto = (SalaDto) res.getResultado("Sala");
            File retImagenAsiento = new File("..\\Asientos\\" + salaDto.getImagenAsiento());
            FileInputStream fileFileInputStream = new FileInputStream(retImagenAsiento);
            byte[] asientoByte = fileFileInputStream.readAllBytes();
            String asientoB64 = Base64.getEncoder().encodeToString(asientoByte);
            salaDto.setImagenAsiento(asientoB64);
            fileFileInputStream.close();
            
            File retImagenFondo = new File("..\\Asientos\\" + salaDto.getImagenFondo());
            FileInputStream fileFileInputStream1 = new FileInputStream(retImagenFondo);
            byte[] fondoByte = fileFileInputStream1.readAllBytes();
            String fondoB64 = Base64.getEncoder().encodeToString(fondoByte);
            salaDto.setImagenAsiento(fondoB64);
            fileFileInputStream1.close();

            return Response.ok((SalaDto) res.getResultado("Sala")).build();
        } catch (Exception ex) {
            Logger.getLogger(SalaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la sala").build();
        }
    }

    @DELETE
    @Path("/salas/{id}")
    public Response eliminarSala(@PathParam("id") Long id) {
        try {
            Respuesta res = salaService.eliminarSala(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(SalaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando la sala").build();
        }
    }

}
