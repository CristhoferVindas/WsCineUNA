/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.util;

/**
 *
 * @author Cristhofer
 */
public class DetalleFacturaAlimento {
    
    
    private String fechaFactura;
    private String horaFactura;
    private String nombreAlimento;
    private String precioAlimento;
    private String cantidadAlimento;

    public DetalleFacturaAlimento() {
    }

    public String getNombreAlimento() {
        return nombreAlimento;
    }

    public void setNombreAlimento(String nombreAlimento) {
        this.nombreAlimento = nombreAlimento;
    }

    public String getPrecioAlimento() {
        return precioAlimento;
    }

    public void setPrecioAlimento(String precioAlimento) {
        this.precioAlimento = precioAlimento;
    }

    public String getCantidadAlimento() {
        return cantidadAlimento;
    }

    public void setCantidadAlimento(String cantidadAlimento) {
        this.cantidadAlimento = cantidadAlimento;
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
    
    
    
}
