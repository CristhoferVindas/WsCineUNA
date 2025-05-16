/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristhofer
 */
@Entity
@Table(name = "CINEUNA_FACTURA", schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByFacId", query = "SELECT f FROM Factura f WHERE f.facId = :facId"),
    @NamedQuery(name = "Factura.findByFacFecha", query = "SELECT f FROM Factura f WHERE f.facFecha = :facFecha"),
    @NamedQuery(name = "Factura.ComprobanteAlimento", query = "SELECT f.facFecha,f.facHora,a.aliPrecio,a.aliNombre,axf.axfCantidad FROM Factura f JOIN f.alimentoxfacturaList axf JOIN axf.axfIdali a WHERE f.facId =:facId"),
    @NamedQuery(name = "Factura.FechaYHora", query = "SELECT CURRENT_TIMESTAMP FROM Factura f"),
    @NamedQuery(name = "Factura.DetalleFactura", query = "SELECT asi.asiFila,asi.asiNumero,s.salNombre, t.tanFecha,t.tanHorainicio,p.pelNombreespannol,p.pelNombreingles,t.tanCostoentrada FROM Asientoxtanda a JOIN a.axtIdtan t JOIN t.tanIdsal s JOIN a.axtIdasi asi JOIN t.tanIdpel p WHERE t.tanId = :tanId"),
    @NamedQuery(name = "Factura.Comprobante", query = "SELECT s.salNombre,f.facFecha,f.facHora,f.facPreciototal,a.asiFila,a.asiNumero,t.tanFecha,t.tanHorainicio,t.tanCostoentrada,p.pelNombreespannol,p.pelNombreingles FROM Factura f JOIN f.asientoxtandaList axt JOIN axt.axtIdtan t JOIN t.tanIdsal s JOIN axt.axtIdasi a JOIN t.tanIdpel p WHERE f.facId =:facId")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CINEUNA_FACTURA_FAC_ID_GENERATOR", sequenceName = "CINEUNA.CINEUNA_FACTURA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CINEUNA_FACTURA_FAC_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "FAC_ID")
    private Long facId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_FECHA")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate facFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "FAC_HORA")
    private String facHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_PRECIOTOTAL")
    private Integer facPreciototal;
    @OneToMany(mappedBy = "facId", fetch = FetchType.LAZY)
    private List<Asientoxtanda> asientoxtandaList;
    @JoinColumn(name = "FAC_IDUSU", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonbTransient
    private Usuario facIdusu;
    @OneToMany(mappedBy = "axfIdfac", fetch = FetchType.LAZY)
    private List<Alimentoxfactura> alimentoxfacturaList;

    public Factura() {
    }

    public Factura(Long facId) {
        this.facId = facId;
    }

    public Factura(Long facId, LocalDate facFecha, String facHora, Integer facPreciototal) {
        this.facId = facId;
        this.facFecha = facFecha;
        this.facHora = facHora;
        this.facPreciototal = facPreciototal;
    }
    

    public Factura(FacturaDto facturaDto) {
        this.facId = facturaDto.getId();
        actualizarFactura(facturaDto);
    }

    public void actualizarFactura(FacturaDto facturaDto) {
        this.facFecha = facturaDto.getFecha();
        this.facHora = facturaDto.getHora();
        this.facPreciototal = facturaDto.getPrecioTotal();
        this.facIdusu = facturaDto.getIdUsuario();
    }
    
    public Long getFacId() {
        return facId;
    }

    public void setFacId(Long facId) {
        this.facId = facId;
    }

    public LocalDate getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(LocalDate facFecha) {
        this.facFecha = facFecha;
    }

    public String getFacHora() {
        return facHora;
    }

    public void setFacHora(String facHora) {
        this.facHora = facHora;
    }

    public Integer getFacPreciototal() {
        return facPreciototal;
    }

    public void setFacPreciototal(Integer facPreciototal) {
        this.facPreciototal = facPreciototal;
    }

    public List<Asientoxtanda> getAsientoxtandaList() {
        return asientoxtandaList;
    }

    public void setAsientoxtandaList(List<Asientoxtanda> asientoxtandaList) {
        this.asientoxtandaList = asientoxtandaList;
    }

    public Usuario getFacIdusu() {
        return facIdusu;
    }

    public void setFacIdusu(Usuario facIdusu) {
        this.facIdusu = facIdusu;
    }

    public List<Alimentoxfactura> getAlimentoxfacturaList() {
        return alimentoxfacturaList;
    }

    public void setAlimentoxfacturaList(List<Alimentoxfactura> alimentoxfacturaList) {
        this.alimentoxfacturaList = alimentoxfacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facId != null ? facId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.facId == null && other.facId != null) || (this.facId != null && !this.facId.equals(other.facId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.Factura[ facId=" + facId + " ]";
    }
    
}
