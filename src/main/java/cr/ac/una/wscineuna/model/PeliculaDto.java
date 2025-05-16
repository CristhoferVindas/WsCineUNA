/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhofer
 */
public class PeliculaDto {
    
    private Long id;
    private String nombreEspannol;
    private String nombreIngles;
    private String idiomasDisponibles;
    private String trailerEspannol;
    private String trailerIngles;
    private String portadaEspannol;
    private String portadaIngles;
    private LocalDate fechaEstreno;
    private LocalDate fechaFin;
    private String estado;
    private String resennaEspannol;
    private String resennaIngles;
    private boolean modificado;
    
    List<TandaDto> tandas;
    List<TandaDto> tandasEliminadas;

    public PeliculaDto() {
        this.modificado = false;
        tandas = new ArrayList<>();
        tandasEliminadas = new ArrayList<>();
    }
    public PeliculaDto(Pelicula pelicula) {
        this();
        this.id = pelicula.getPelId();
        this.nombreEspannol = pelicula.getPelNombreespannol();
        this.nombreIngles = pelicula.getPelNombreingles();
        this.idiomasDisponibles = pelicula.getPelIdiomasdisponibles();
        this.trailerEspannol = pelicula.getPelTrailerespanol();
        this.trailerIngles = pelicula.getPelTraileringles();
        this.portadaEspannol = pelicula.getPelPortadaespannol();
        this.portadaIngles = pelicula.getPelPortadaingles();
        this.fechaEstreno = pelicula.getPelFechaestreno();
        this.fechaFin = pelicula.getPelFechafin();
        this.estado = pelicula.getPelEstado();
        this.resennaEspannol = pelicula.getPelResennaespannol();
        this.resennaIngles = pelicula.getPelResennaingles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEspannol() {
        return nombreEspannol;
    }

    public void setNombreEspannol(String nombreEspannol) {
        this.nombreEspannol = nombreEspannol;
    }

    public String getNombreIngles() {
        return nombreIngles;
    }

    public void setNombreIngles(String nombreIngles) {
        this.nombreIngles = nombreIngles;
    }

    public String getIdiomasDisponibles() {
        return idiomasDisponibles;
    }

    public void setIdiomasDisponibles(String idiomasDisponibles) {
        this.idiomasDisponibles = idiomasDisponibles;
    }

    public String getTrailerEspannol() {
        return trailerEspannol;
    }

    public void setTrailerEspannol(String trailerEspannol) {
        this.trailerEspannol = trailerEspannol;
    }

    public String getTrailerIngles() {
        return trailerIngles;
    }

    public void setTrailerIngles(String trailerIngles) {
        this.trailerIngles = trailerIngles;
    }

    public String getPortadaEspannol() {
        return portadaEspannol;
    }

    public void setPortadaEspannol(String portadaEspannol) {
        this.portadaEspannol = portadaEspannol;
    }

    public String getPortadaIngles() {
        return portadaIngles;
    }

    public void setPortadaIngles(String portadaIngles) {
        this.portadaIngles = portadaIngles;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResennaEspannol() {
        return resennaEspannol;
    }

    public void setResennaEspannol(String resennaEspannol) {
        this.resennaEspannol = resennaEspannol;
    }

    public String getResennaIngles() {
        return resennaIngles;
    }

    public void setResennaIngles(String resennaIngles) {
        this.resennaIngles = resennaIngles;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "PeliculaDto{" + "id=" + id + ", nombreEspannol=" + nombreEspannol + ", nombreIngles=" + nombreIngles + ", idiomasDisponibles=" + idiomasDisponibles + ", fechaEstreno=" + fechaEstreno + ", estado=" + estado + ", modificado=" + modificado + '}';
    }
  
    public List<TandaDto> getTandas() {
        return tandas;
    }

    public void setTandas(List<TandaDto> tandas) {
        this.tandas = tandas;
    }

    public List<TandaDto> getTandasEliminadas() {
        return tandasEliminadas;
    }

    public void setTandasEliminadas(List<TandaDto> tandasEliminadas) {
        this.tandasEliminadas = tandasEliminadas;
    }
    
    
    
    
    
}
