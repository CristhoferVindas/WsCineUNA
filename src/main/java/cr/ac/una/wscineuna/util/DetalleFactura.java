/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.util;

/**
 *
 * @author Cristhofer
 */
public class DetalleFactura {

    private String nombrePeliculaEspanol;
    private String nombrePeliculaIngles;
    private String nombreSala;
    private String fechaFactura;
    private String horaFactura;
    private String precioTotal;
    private String fila;
    private String numero;
    private String fechaTanda;
    private String horaTanda;
    private String costoEntrada;
    

    public DetalleFactura() {
    }

    public DetalleFactura(String nombreSala, String fechaFactura, String horaFactura, String precioTotal, String fila, String numero, String fechaTanda, String horaTanda, String costoEntrada, String nombreAlimento, String precioAlimento, String cantidadAlimento) {
        this.nombreSala = nombreSala;
        this.fechaFactura = fechaFactura;
        this.horaFactura = horaFactura;
        this.precioTotal = precioTotal;
        this.fila = fila;
        this.numero = numero;
        this.fechaTanda = fechaTanda;
        this.horaTanda = horaTanda;
        this.costoEntrada = costoEntrada;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getHoraFactura() {
        return horaFactura;
    }

    public void setHoraFactura(String horaFactura) {
        this.horaFactura = horaFactura;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaTanda() {
        return fechaTanda;
    }

    public void setFechaTanda(String fechaTanda) {
        this.fechaTanda = fechaTanda;
    }

    public String getHoraTanda() {
        return horaTanda;
    }

    public void setHoraTanda(String horaTanda) {
        this.horaTanda = horaTanda;
    }

    public String getCostoEntrada() {
        return costoEntrada;
    }

    public void setCostoEntrada(String costoEntrada) {
        this.costoEntrada = costoEntrada;
    }

    public String getNombrePeliculaEspanol() {
        return nombrePeliculaEspanol;
    }

    public void setNombrePeliculaEspanol(String nombrePeliculaEspanol) {
        this.nombrePeliculaEspanol = nombrePeliculaEspanol;
    }

    public String getNombrePeliculaIngles() {
        return nombrePeliculaIngles;
    }

    public void setNombrePeliculaIngles(String nombrePeliculaIngles) {
        this.nombrePeliculaIngles = nombrePeliculaIngles;
    }

    
}
