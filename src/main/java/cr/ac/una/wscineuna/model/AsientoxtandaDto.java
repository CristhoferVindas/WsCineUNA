/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

/**
 *
 * @author Cristhofer
 */
public class AsientoxtandaDto {
    
    private Long id;
    private boolean modificado;
    private Long IDTanda;
    private Long  IDAsiento;
     private Long  IDFactura;
    
    private Tanda idTanda;
    private Asiento idAsiento;
    private Factura idFactura;

    public AsientoxtandaDto() {
        this.modificado = false;
    }
    
    public AsientoxtandaDto(Asientoxtanda asientoxtanda) {
        this();
        this.id = asientoxtanda.getAxtId();
        this.idTanda = asientoxtanda.getAxtIdtan();
        this.idAsiento = asientoxtanda.getAxtIdasi();
        this.idFactura = asientoxtanda.getFacId();
        this.IDTanda = Long.valueOf(0);
        this.IDAsiento = Long.valueOf(0);
        this.IDFactura = Long.valueOf(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public Tanda getIdTanda() {
        return idTanda;
    }

    public void setIdTanda(Tanda idTanda) {
        this.idTanda = idTanda;
    }

    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Long getIDTanda() {
        return IDTanda;
    }

    public void setIDTanda(Long IDTanda) {
        this.IDTanda = IDTanda;
    }

    public Long getIDAsiento() {
        return IDAsiento;
    }

    public void setIDAsiento(Long IDAsiento) {
        this.IDAsiento = IDAsiento;
    }

    public Long getIDFactura() {
        return IDFactura;
    }

    public void setIDFactura(Long IDFactura) {
        this.IDFactura = IDFactura;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }
    
    @Override
    public String toString() {
        return "AsientoxtandaDto{" + "id=" + id + ", modificado=" + modificado + ", idTanda=" + idTanda + ", idAsiento=" + idAsiento + '}';
    }

}
