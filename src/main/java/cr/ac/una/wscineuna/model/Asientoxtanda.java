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
@Table(name = "CINEUNA_ASIENTOXTANDA", schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "Asientoxtanda.findAll", query = "SELECT a FROM Asientoxtanda a"),
     @NamedQuery(name = "Asientoxtanda.findByIdTan", query = "SELECT a FROM Asientoxtanda a where a.axtIdtan = :axtIdtan"),
     @NamedQuery(name = "Asientoxtanda.findByIDfacturaNull", query = "SELECT a FROM Asientoxtanda a where a.facId is null"),
    @NamedQuery(name = "Asientoxtanda.findByAxtId", query = "SELECT a FROM Asientoxtanda a WHERE a.axtId = :axtId")})
public class Asientoxtanda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CINEUNA_ASIENTOXTANDA_AXT_ID_GENERATOR", sequenceName = "CINEUNA.CINEUNA_ASIENTOXTANDA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CINEUNA_ASIENTOXTANDA_AXT_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "AXT_ID")
    private Long axtId;
    @JoinColumn(name = "AXT_IDASI", referencedColumnName = "ASI_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonbTransient
    private Asiento axtIdasi;
    @JoinColumn(name = "FAC_ID", referencedColumnName = "FAC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    private Factura facId;
    @JoinColumn(name = "AXT_IDTAN", referencedColumnName = "TAN_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonbTransient
    private Tanda axtIdtan;

    public Asientoxtanda() {
    }

    public Asientoxtanda(Long axtId) {
        this.axtId = axtId;
    }

     public Asientoxtanda(AsientoxtandaDto asientoxtandaDto) {
        this.axtId = asientoxtandaDto.getId();
        actualizarAsientoxtanda(asientoxtandaDto);
    }

    public void actualizarAsientoxtanda(AsientoxtandaDto asientoxtandaDto) {
        this.axtIdtan = asientoxtandaDto.getIdTanda();
        this.axtIdasi = asientoxtandaDto.getIdAsiento();
        this.facId = asientoxtandaDto.getIdFactura();
    }
    public Long getAxtId() {
        return axtId;
    }

    public void setAxtId(Long axtId) {
        this.axtId = axtId;
    }

    public Asiento getAxtIdasi() {
        return axtIdasi;
    }

    public void setAxtIdasi(Asiento axtIdasi) {
        this.axtIdasi = axtIdasi;
    }

    public Factura getFacId() {
        return facId;
    }

    public void setFacId(Factura facId) {
        this.facId = facId;
    }

    public Tanda getAxtIdtan() {
        return axtIdtan;
    }

    public void setAxtIdtan(Tanda axtIdtan) {
        this.axtIdtan = axtIdtan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (axtId != null ? axtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asientoxtanda)) {
            return false;
        }
        Asientoxtanda other = (Asientoxtanda) object;
        if ((this.axtId == null && other.axtId != null) || (this.axtId != null && !this.axtId.equals(other.axtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.Asientoxtanda[ axtId=" + axtId + " ]";
    }
    
}
