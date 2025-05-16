/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.util;

import java.time.LocalDate;

/**
 *
 * @author Cristhofer
 */
public class Reportes {
    
    
    private String horainicio;
    private String peliculanombreEspañol;
    private String peliculanombreIngles;
    private String fecha; 
    private Long cantidad;

    public Reportes() {
    }

    
    
    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getPeliculanombreEspañol() {
        return peliculanombreEspañol;
    }

    public void setPeliculanombreEspañol(String peliculanombreEspañol) {
        this.peliculanombreEspañol = peliculanombreEspañol;
    }

    public String getPeliculanombreIngles() {
        return peliculanombreIngles;
    }

    public void setPeliculanombreIngles(String peliculanombreIngles) {
        this.peliculanombreIngles = peliculanombreIngles;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
   
}
