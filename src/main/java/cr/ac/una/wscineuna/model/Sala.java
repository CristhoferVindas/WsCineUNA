/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristhofer
 */
@Entity
@Table(name = "CINEUNA_SALA", schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "Sala.findBySalId", query = "SELECT s FROM Sala s WHERE s.salId = :salId"),
    @NamedQuery(name = "Sala.findBySalNombre", query = "SELECT s FROM Sala s WHERE s.salNombre = :salNombre"),
    @NamedQuery(name = "Sala.findBySalEstado", query = "SELECT s FROM Sala s WHERE s.salEstado = :salEstado"),
    @NamedQuery(name = "Sala.findBySalImagenasiento", query = "SELECT s FROM Sala s WHERE s.salImagenasiento = :salImagenasiento"),
    @NamedQuery(name = "Sala.findBySalImagenfondo", query = "SELECT s FROM Sala s WHERE s.salImagenfondo = :salImagenfondo"),
    @NamedQuery(name = "Sala.findBySalNombreDetalle", query = "SELECT s FROM Sala s WHERE UPPER(s.salNombre) like :nombre and UPPER(s.salDetalle) like :detalle", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "Sala.findBySalDetalle", query = "SELECT s FROM Sala s WHERE s.salDetalle = :salDetalle")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CINEUNA_SALA_SAL_ID_GENERATOR", sequenceName = "CINEUNA.CINEUNA_SALA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CINEUNA_SALA_SAL_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "SAL_ID")
    private Long salId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "SAL_NOMBRE")
    private String salNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SAL_ESTADO")
    private String salEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "SAL_IMAGENASIENTO")
    private String salImagenasiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "SAL_IMAGENFONDO")
    private String salImagenfondo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "SAL_DETALLE")
    private String salDetalle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asiIdsal", fetch = FetchType.LAZY)
    private List<Asiento> asientoList;
    @OneToMany(mappedBy = "tanIdsal", fetch = FetchType.LAZY)
    private List<Tanda> tandaList;

    public Sala() {
    }

    public Sala(Long salId) {
        this.salId = salId;
    }

    public Sala(Long salId, String salNombre, String salEstado, String salImagenasiento, String salImagenfondo, String salDetalle) {
        this.salId = salId;
        this.salNombre = salNombre;
        this.salEstado = salEstado;
        this.salImagenasiento = salImagenasiento;
        this.salImagenfondo = salImagenfondo;
        this.salDetalle = salDetalle;
    }

     public Sala(SalaDto salaDto) {
            this.salId = salaDto.getId();
        actualizarSala(salaDto);
    }

    public void actualizarSala(SalaDto salaDto) {
        this.salNombre = salaDto.getNombre();
        this.salEstado = salaDto.getEstado();
        this.salImagenasiento = salaDto.getImagenAsiento();
        this.salImagenfondo = salaDto.getImagenFondo();
        this.salDetalle = salaDto.getDetalle();
        
    }
    
    public Long getSalId() {
        return salId;
    }

    public void setSalId(Long salId) {
        this.salId = salId;
    }

    public String getSalNombre() {
        return salNombre;
    }

    public void setSalNombre(String salNombre) {
        this.salNombre = salNombre;
    }

    public String getSalEstado() {
        return salEstado;
    }

    public void setSalEstado(String salEstado) {
        this.salEstado = salEstado;
    }

    public String getSalImagenasiento() {
        return salImagenasiento;
    }

    public void setSalImagenasiento(String salImagenasiento) {
        this.salImagenasiento = salImagenasiento;
    }

    public String getSalImagenfondo() {
        return salImagenfondo;
    }

    public void setSalImagenfondo(String salImagenfondo) {
        this.salImagenfondo = salImagenfondo;
    }

    public String getSalDetalle() {
        return salDetalle;
    }

    public void setSalDetalle(String salDetalle) {
        this.salDetalle = salDetalle;
    }

    public List<Asiento> getAsientoList() {
        return asientoList;
    }

    public void setAsientoList(List<Asiento> asientoList) {
        this.asientoList = asientoList;
    }

    public List<Tanda> getTandaList() {
        return tandaList;
    }

    public void setTandaList(List<Tanda> tandaList) {
        this.tandaList = tandaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salId != null ? salId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.salId == null && other.salId != null) || (this.salId != null && !this.salId.equals(other.salId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.Sala[ salId=" + salId + " ]";
    }
    
}
