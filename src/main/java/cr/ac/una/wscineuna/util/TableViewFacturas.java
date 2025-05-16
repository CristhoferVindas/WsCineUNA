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
public class TableViewFacturas {

    private String fila;
    private Integer numero;
    private String nombreSala;
    private LocalDate fecha;
    private String horainicio;
    private String peliculanombreEspañol;
    private String peliculanombreIngles;
    private Double precio;
    private boolean modificado;

    public TableViewFacturas() {
        this.modificado = false;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

}
