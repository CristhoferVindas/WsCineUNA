/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

/**
 *
 * @author Cristhofer
 */
public class AlimentoxfacturaDto {
    
    private Long id;
    private Integer cantidad;
    private Integer precio;
    private boolean modificado;
    
    private Factura IDFactura;
    private Alimento IDAlimento;
    
    public Long idFactura;
    public Long idAlimento;

    public AlimentoxfacturaDto() {
        this.modificado = false;
    }

    public AlimentoxfacturaDto(Alimentoxfactura alimentoxfactura) {
        this();
        this.id = alimentoxfactura.getAxfId();
        this.cantidad = alimentoxfactura.getAxfCantidad();
        this.precio = alimentoxfactura.getAxfPrecio();
        this.IDFactura = alimentoxfactura.getAxfIdfac();
        this.IDAlimento = alimentoxfactura.getAxfIdali();
        this.idAlimento = Long.valueOf(0);
        this.idFactura = Long.valueOf(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public Factura getIDFactura() {
        return IDFactura;
    }

    public void setIDFactura(Factura idFactura) {
        this.IDFactura = idFactura;
    }

    public Alimento getIDAlimento() {
        return IDAlimento;
    }

    public void setIDAlimento(Alimento idAlimento) {
        this.IDAlimento = idAlimento;
    }
    
   public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Long getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(Long idAlimento) {
        this.idAlimento = idAlimento;
    }
    
    @Override
    public String toString() {
        return "AlimentoxfacturaDto{" + "id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }

}
