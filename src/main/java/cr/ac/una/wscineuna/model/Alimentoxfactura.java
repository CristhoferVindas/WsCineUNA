/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cristhofer
 */
@Entity
@Table(name = "CINEUNA_ALIMENTOXFACTURA", schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "Alimentoxfactura.findAll", query = "SELECT a FROM Alimentoxfactura a"),
    @NamedQuery(name = "Alimentoxfactura.findByAxfId", query = "SELECT a FROM Alimentoxfactura a WHERE a.axfId = :axfId"),
    @NamedQuery(name = "Alimentoxfactura.findByAxfCantidad", query = "SELECT a FROM Alimentoxfactura a WHERE a.axfCantidad = :axfCantidad"),
    @NamedQuery(name = "Alimentoxfactura.findByAxfPrecio", query = "SELECT a FROM Alimentoxfactura a WHERE a.axfPrecio = :axfPrecio")})
public class Alimentoxfactura implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CINEUNA_ALIMENTOXFACTURA_AXF_ID_GENERATOR", sequenceName = "CINEUNA.CINEUNA_ALIMENTOXFACTURA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CINEUNA_ALIMENTOXFACTURA_AXF_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "AXF_ID")
    private Long axfId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AXF_CANTIDAD")
    private Integer axfCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AXF_PRECIO")
    private Integer axfPrecio;
    @JoinColumn(name = "AXF_IDALI", referencedColumnName = "ALI_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    private Alimento axfIdali;
    @JoinColumn(name = "AXF_IDFAC", referencedColumnName = "FAC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    private Factura axfIdfac;

    public Alimentoxfactura() {
    }

    public Alimentoxfactura(Long axfId) {
        this.axfId = axfId;
    }

    public Alimentoxfactura(Long axfId, Integer axfCantidad, Integer axfPrecio) {
        this.axfId = axfId;
        this.axfCantidad = axfCantidad;
        this.axfPrecio = axfPrecio;
    }

     public Alimentoxfactura(AlimentoxfacturaDto alimentoxfacturaDto) {
        this.axfId = alimentoxfacturaDto.getId();
        actualizarAlimentoxfactura(alimentoxfacturaDto);

    }

    public void actualizarAlimentoxfactura(AlimentoxfacturaDto alimentoxfacturaDto) {

        this.axfCantidad = alimentoxfacturaDto.getCantidad();
        this.axfPrecio = alimentoxfacturaDto.getPrecio();
        this.axfIdfac = alimentoxfacturaDto.getIDFactura();
        this.axfIdali = alimentoxfacturaDto.getIDAlimento();

    }
    public Long getAxfId() {
        return axfId;
    }

    public void setAxfId(Long axfId) {
        this.axfId = axfId;
    }

    public Integer getAxfCantidad() {
        return axfCantidad;
    }

    public void setAxfCantidad(Integer axfCantidad) {
        this.axfCantidad = axfCantidad;
    }

    public Integer getAxfPrecio() {
        return axfPrecio;
    }

    public void setAxfPrecio(Integer axfPrecio) {
        this.axfPrecio = axfPrecio;
    }

    public Alimento getAxfIdali() {
        return axfIdali;
    }

    public void setAxfIdali(Alimento axfIdali) {
        this.axfIdali = axfIdali;
    }

    public Factura getAxfIdfac() {
        return axfIdfac;
    }

    public void setAxfIdfac(Factura axfIdfac) {
        this.axfIdfac = axfIdfac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (axfId != null ? axfId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alimentoxfactura)) {
            return false;
        }
        Alimentoxfactura other = (Alimentoxfactura) object;
        if ((this.axfId == null && other.axfId != null) || (this.axfId != null && !this.axfId.equals(other.axfId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.Alimentoxfactura[ axfId=" + axfId + " ]";
    }
    
}
