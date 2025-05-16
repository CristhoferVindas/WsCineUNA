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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristhofer
 */
@Entity
@Table(name = "CINEUNA_TANDA", schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "Tanda.findAll", query = "SELECT t FROM Tanda t"),
    @NamedQuery(name = "Tanda.findByTanId", query = "SELECT t FROM Tanda t WHERE t.tanId = :tanId"),
    @NamedQuery(name = "Tanda.findByTanCostoentrada", query = "SELECT t FROM Tanda t WHERE t.tanCostoentrada = :tanCostoentrada"),
    @NamedQuery(name = "Tanda.findByTanHorainicio", query = "SELECT t FROM Tanda t WHERE t.tanHorainicio = :tanHorainicio"),
    @NamedQuery(name = "Tanda.findByTanHorafin", query = "SELECT t FROM Tanda t WHERE t.tanHorafin = :tanHorafin"),
    @NamedQuery(name = "Tanda.findByTanFecha", query = "SELECT t FROM Tanda t WHERE t.tanFecha = :tanFecha"),
    @NamedQuery(name = "Tanda.findByTanIdioma", query = "SELECT t FROM Tanda t WHERE t.tanIdioma = :tanIdioma")})
public class Tanda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CINEUNA_TANDA_TAN_ID_GENERATOR", sequenceName = "CINEUNA.CINEUNA_TANDA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CINEUNA_TANDA_TAN_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "TAN_ID")
    private Long tanId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAN_COSTOENTRADA")
    private Integer tanCostoentrada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TAN_HORAINICIO")
    private String tanHorainicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TAN_HORAFIN")
    private String tanHorafin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAN_FECHA")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate tanFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TAN_IDIOMA")
    private String tanIdioma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "axtIdtan", fetch = FetchType.LAZY)
    private List<Asientoxtanda> asientoxtandaList;
    @JoinColumn(name = "TAN_IDPEL", referencedColumnName = "PEL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    private Pelicula tanIdpel;
    @JoinColumn(name = "TAN_IDSAL", referencedColumnName = "SAL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    private Sala tanIdsal;

    public Tanda() {
    }

    public Tanda(Long tanId) {
        this.tanId = tanId;
    }

    public Tanda(Long tanId, Integer tanCostoentrada, String tanHorainicio, String tanHorafin, LocalDate tanFecha, String tanIdioma) {
        this.tanId = tanId;
        this.tanCostoentrada = tanCostoentrada;
        this.tanHorainicio = tanHorainicio;
        this.tanHorafin = tanHorafin;
        this.tanFecha = tanFecha;
        this.tanIdioma = tanIdioma;
    }

      public Tanda(TandaDto tandaDto) {
            this.tanId = tandaDto.getId();
        actualizarTanda(tandaDto);
    }

    public void actualizarTanda(TandaDto tandaDto) {
        this.tanCostoentrada = tandaDto.getCostoEntrada();
        this.tanHorainicio = tandaDto.getHoraInicio();
        this.tanHorafin = tandaDto.getHoraFin();
        this.tanFecha = tandaDto.getFecha();
        this.tanIdioma = tandaDto.getIdioma();
        this.tanIdpel = tandaDto.getIdPelicula();
        this.tanIdsal = tandaDto.getIdSala();
          
    }
    
    public Long getTanId() {
        return tanId;
    }

    public void setTanId(Long tanId) {
        this.tanId = tanId;
    }

    public Integer getTanCostoentrada() {
        return tanCostoentrada;
    }

    public void setTanCostoentrada(Integer tanCostoentrada) {
        this.tanCostoentrada = tanCostoentrada;
    }

    public String getTanHorainicio() {
        return tanHorainicio;
    }

    public void setTanHorainicio(String tanHorainicio) {
        this.tanHorainicio = tanHorainicio;
    }

    public String getTanHorafin() {
        return tanHorafin;
    }

    public void setTanHorafin(String tanHorafin) {
        this.tanHorafin = tanHorafin;
    }

    public LocalDate getTanFecha() {
        return tanFecha;
    }

    public void setTanFecha(LocalDate tanFecha) {
        this.tanFecha = tanFecha;
    }

    public String getTanIdioma() {
        return tanIdioma;
    }

    public void setTanIdioma(String tanIdioma) {
        this.tanIdioma = tanIdioma;
    }

    public List<Asientoxtanda> getAsientoxtandaList() {
        return asientoxtandaList;
    }

    public void setAsientoxtandaList(List<Asientoxtanda> asientoxtandaList) {
        this.asientoxtandaList = asientoxtandaList;
    }

    public Pelicula getTanIdpel() {
        return tanIdpel;
    }

    public void setTanIdpel(Pelicula tanIdpel) {
        this.tanIdpel = tanIdpel;
    }

    public Sala getTanIdsal() {
        return tanIdsal;
    }

    public void setTanIdsal(Sala tanIdsal) {
        this.tanIdsal = tanIdsal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tanId != null ? tanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tanda)) {
            return false;
        }
        Tanda other = (Tanda) object;
        if ((this.tanId == null && other.tanId != null) || (this.tanId != null && !this.tanId.equals(other.tanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.Tanda[ tanId=" + tanId + " ]";
    }
    
}
