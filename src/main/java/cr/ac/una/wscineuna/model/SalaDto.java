/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhofer
 */
public class SalaDto {
    
    private Long id;
    private String nombre;
    private String estado;
    private String imagenAsiento;
    private String imagenFondo;
    private String detalle;
    private boolean modificado;

    List<TandaDto> tandas;
    List<TandaDto> tandasEliminadas;
    
    List<AsientoDto> asientos;
    List<AsientoDto> asientosEliminadas;
    
    public SalaDto() {
        tandas = new ArrayList<>();
        tandasEliminadas = new ArrayList<>();
        asientos = new ArrayList<>();
        asientosEliminadas = new ArrayList<>();
    }
    public SalaDto(Sala sala) {
        this();
        this.id = sala.getSalId();
        this.nombre = sala.getSalNombre();
        this.estado = sala.getSalEstado();
        this.imagenAsiento = sala.getSalImagenasiento();
        this.imagenFondo = sala.getSalImagenfondo();
        this.detalle = sala.getSalDetalle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagenAsiento() {
        return imagenAsiento;
    }

    public void setImagenAsiento(String imagenAsiento) {
        this.imagenAsiento = imagenAsiento;
    }

    public String getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(String imagenFondo) {
        this.imagenFondo = imagenFondo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
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

    public List<AsientoDto> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<AsientoDto> asientos) {
        this.asientos = asientos;
    }

    public List<AsientoDto> getAsientosEliminadas() {
        return asientosEliminadas;
    }

    public void setAsientosEliminadas(List<AsientoDto> asientosEliminadas) {
        this.asientosEliminadas = asientosEliminadas;
    }
    

    @Override
    public String toString() {
        return "SalaDto{" + "id=" + id + ", nombre=" + nombre + ", estado=" + estado + ", modificado=" + modificado + '}';
    }

 
    
    
    
}
