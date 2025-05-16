/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CINEUNA_ASIENTO", schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "Asiento.findAll", query = "SELECT a FROM Asiento a"),
    @NamedQuery(name = "Asiento.findByAsiId", query = "SELECT a FROM Asiento a WHERE a.asiId = :asiId"),
    @NamedQuery(name = "Asiento.findByAsiFila", query = "SELECT a FROM Asiento a WHERE a.asiFila = :asiFila"),
    @NamedQuery(name = "Asiento.findByAsiNumero", query = "SELECT a FROM Asiento a WHERE a.asiNumero = :asiNumero")})
public class Asiento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CINEUNA_ASIENTO_ASI_ID_GENERATOR", sequenceName = "CINEUNA.CINEUNA_ASIENTO_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CINEUNA_ASIENTO_ASI_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ASI_ID")
    private Long asiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ASI_FILA")
    private String asiFila;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASI_NUMERO")
    private Integer asiNumero;
    @JoinColumn(name = "ASI_IDSAL", referencedColumnName = "SAL_ID")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonbTransient
    private Sala asiIdsal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "axtIdasi", fetch = FetchType.LAZY)
    private List<Asientoxtanda> asientoxtandaList;

    public Asiento() {
    }

    public Asiento(Long asiId) {
        this.asiId = asiId;
    }

    public Asiento(Long asiId, String asiFila, Integer asiNumero) {
        this.asiId = asiId;
        this.asiFila = asiFila;
        this.asiNumero = asiNumero;
    }

    public Asiento(AsientoDto asientoDto) {
        this.asiId = asientoDto.getId();
        actualizarAsiento(asientoDto);

    }

    public void actualizarAsiento(AsientoDto asientoDto) {

        this.asiFila = asientoDto.getFila();
        this.asiNumero = asientoDto.getNumero();
        this.asiIdsal = asientoDto.getIdSala();

    }
    
    public Long getAsiId() {
        return asiId;
    }

    public void setAsiId(Long asiId) {
        this.asiId = asiId;
    }

    public String getAsiFila() {
        return asiFila;
    }

    public void setAsiFila(String asiFila) {
        this.asiFila = asiFila;
    }

    public Integer getAsiNumero() {
        return asiNumero;
    }

    public void setAsiNumero(Integer asiNumero) {
        this.asiNumero = asiNumero;
    }

    public Sala getAsiIdsal() {
        return asiIdsal;
    }

    public void setAsiIdsal(Sala asiIdsal) {
        this.asiIdsal = asiIdsal;
    }

    public List<Asientoxtanda> getAsientoxtandaList() {
        return asientoxtandaList;
    }

    public void setAsientoxtandaList(List<Asientoxtanda> asientoxtandaList) {
        this.asientoxtandaList = asientoxtandaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asiId != null ? asiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asiento)) {
            return false;
        }
        Asiento other = (Asiento) object;
        if ((this.asiId == null && other.asiId != null) || (this.asiId != null && !this.asiId.equals(other.asiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.Asiento[ asiId=" + asiId + " ]";
    }
    
}
