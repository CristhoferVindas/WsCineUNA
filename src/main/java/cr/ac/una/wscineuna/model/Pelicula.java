/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "CINEUNA_PELICULA", schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "Pelicula.findAll", query = "SELECT p FROM Pelicula p"),
    @NamedQuery(name = "Pelicula.findByPelId", query = "SELECT p FROM Pelicula p WHERE p.pelId = :pelId"),
    @NamedQuery(name = "Pelicula.findByPelNombreespannol", query = "SELECT p FROM Pelicula p WHERE p.pelNombreespannol = :pelNombreespannol"),
    @NamedQuery(name = "Pelicula.findByPelNombreingles", query = "SELECT p FROM Pelicula p WHERE p.pelNombreingles = :pelNombreingles"),
    @NamedQuery(name = "Pelicula.findByPelIdiomasdisponibles", query = "SELECT p FROM Pelicula p WHERE p.pelIdiomasdisponibles = :pelIdiomasdisponibles"),
    @NamedQuery(name = "Pelicula.findByPelTrailerespanol", query = "SELECT p FROM Pelicula p WHERE p.pelTrailerespanol = :pelTrailerespanol"),
    @NamedQuery(name = "Pelicula.findByPelTraileringles", query = "SELECT p FROM Pelicula p WHERE p.pelTraileringles = :pelTraileringles"),
    @NamedQuery(name = "Pelicula.findByPelPortadaespannol", query = "SELECT p FROM Pelicula p WHERE p.pelPortadaespannol = :pelPortadaespannol"),
    @NamedQuery(name = "Pelicula.findByPelPortadaingles", query = "SELECT p FROM Pelicula p WHERE p.pelPortadaingles = :pelPortadaingles"),
    @NamedQuery(name = "Pelicula.findByPelFechaestreno", query = "SELECT p FROM Pelicula p WHERE p.pelFechaestreno = :pelFechaestreno"),
    @NamedQuery(name = "Pelicula.findByPelFechafin", query = "SELECT p FROM Pelicula p WHERE p.pelFechafin = :pelFechafin"),
    @NamedQuery(name = "Pelicula.ReporteCantPelByBtwnFecha", query = "SELECT p.pelNombreespannol,p.pelNombreingles,f.facFecha,count(p.pelId) as Cantidad FROM Factura f JOIN f.asientoxtandaList axt JOIN axt.axtIdtan t JOIN t.tanIdpel p WHERE F.facFecha BETWEEN :facFechaInicio AND :facFechaFin AND p.pelEstado = :pelEstado GROUP BY p.pelNombreespannol, p.pelNombreingles, f.facFecha ORDER BY CANTIDAD DESC" ,hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "Pelicula.ReporteCantPerByPelicula", query = "SELECT p.pelNombreespannol,p.pelNombreingles,t.tanHorainicio,t.tanFecha,count(p.pelId) as Cantidad FROM Factura f JOIN f.asientoxtandaList axt JOIN axt.axtIdtan t JOIN t.tanIdpel p WHERE p.pelNombreespannol = :peliculaEspanol or p.pelNombreingles =:peliculaIngles GROUP BY p.pelNombreespannol,p.pelNombreingles,t.tanHorainicio,t.tanFecha",hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "Pelicula.findByPelNombreespannolNombreingles", query = "SELECT p FROM Pelicula p WHERE UPPER(p.pelNombreespannol) like :nombreEspannol and UPPER(p.pelNombreingles) like :nombreIngles ", hints = @QueryHint(name = "eclipselink.refresh", value = "true")), //and p.pelFechaestreno like :fechaEstreno
    @NamedQuery(name = "Pelicula.findByPelEstadoPorEstrenarse", query = "SELECT p FROM Pelicula p WHERE p.pelEstado = :estado order by p.pelFechaestreno", hints = @QueryHint(name = "eclipselink.refresh", value = "true"))})
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CINEUNA_PELICULA_PEL_ID_GENERATOR", sequenceName = "CINEUNA.CINEUNA_PELICULA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CINEUNA_PELICULA_PEL_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "PEL_ID")
    private Long pelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PEL_NOMBREESPANNOL")
    private String pelNombreespannol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PEL_NOMBREINGLES")
    private String pelNombreingles;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PEL_IDIOMASDISPONIBLES")
    private String pelIdiomasdisponibles;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PEL_TRAILERESPANOL")
    private String pelTrailerespanol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PEL_TRAILERINGLES")
    private String pelTraileringles;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PEL_PORTADAESPANNOL")
    private String pelPortadaespannol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PEL_PORTADAINGLES")
    private String pelPortadaingles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEL_FECHAESTRENO")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate pelFechaestreno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEL_FECHAFIN")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate pelFechafin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PEL_ESTADO")
    private String pelEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PEL_RESENNAESPANNOL")
    private String pelResennaespannol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PEL_RESENNAINGLES")
    private String pelResennaingles;
    @OneToMany(mappedBy = "tanIdpel", fetch = FetchType.LAZY)
    private List<Tanda> tandaList;

    public Pelicula() {
    }

    public Pelicula(Long pelId) {
        this.pelId = pelId;
    }

    public Pelicula(Long pelId, String pelNombreespannol, String pelNombreingles, String pelIdiomasdisponibles, String pelTrailerespanol, String pelTraileringles, String pelPortadaespannol, String pelPortadaingles, LocalDate pelFechaestreno, LocalDate pelFechafin, String pelEstado, String pelResennaespannol, String pelResennaingles) {
        this.pelId = pelId;
        this.pelNombreespannol = pelNombreespannol;
        this.pelNombreingles = pelNombreingles;
        this.pelIdiomasdisponibles = pelIdiomasdisponibles;
        this.pelTrailerespanol = pelTrailerespanol;
        this.pelTraileringles = pelTraileringles;
        this.pelPortadaespannol = pelPortadaespannol;
        this.pelPortadaingles = pelPortadaingles;
        this.pelFechaestreno = pelFechaestreno;
        this.pelFechafin = pelFechafin;
        this.pelEstado = pelEstado;
        this.pelResennaespannol = pelResennaespannol;
        this.pelResennaingles = pelResennaingles;
    }
    
     public Pelicula(PeliculaDto peliculaDto) {
        this.pelId = peliculaDto.getId();
        actualizarPelicula(peliculaDto);
    }

    public void actualizarPelicula(PeliculaDto peliculaDto) {
        this.pelNombreespannol = peliculaDto.getNombreEspannol();
        this.pelNombreingles = peliculaDto.getNombreIngles();
        this.pelIdiomasdisponibles = peliculaDto.getIdiomasDisponibles();
        this.pelTrailerespanol = peliculaDto.getTrailerEspannol();
        this.pelTraileringles = peliculaDto.getTrailerIngles();
        this.pelPortadaespannol = peliculaDto.getPortadaEspannol();
        this.pelPortadaingles = peliculaDto.getPortadaIngles();
        this.pelFechaestreno = peliculaDto.getFechaEstreno();
        this.pelFechafin = peliculaDto.getFechaFin();
        this.pelEstado = peliculaDto.getEstado();
        this.pelResennaespannol = peliculaDto.getResennaEspannol();
        this.pelResennaingles = peliculaDto.getResennaIngles();
    }


    public Long getPelId() {
        return pelId;
    }

    public void setPelId(Long pelId) {
        this.pelId = pelId;
    }

    public String getPelNombreespannol() {
        return pelNombreespannol;
    }

    public void setPelNombreespannol(String pelNombreespannol) {
        this.pelNombreespannol = pelNombreespannol;
    }

    public String getPelNombreingles() {
        return pelNombreingles;
    }

    public void setPelNombreingles(String pelNombreingles) {
        this.pelNombreingles = pelNombreingles;
    }

    public String getPelIdiomasdisponibles() {
        return pelIdiomasdisponibles;
    }

    public void setPelIdiomasdisponibles(String pelIdiomasdisponibles) {
        this.pelIdiomasdisponibles = pelIdiomasdisponibles;
    }

    public String getPelTrailerespanol() {
        return pelTrailerespanol;
    }

    public void setPelTrailerespanol(String pelTrailerespanol) {
        this.pelTrailerespanol = pelTrailerespanol;
    }

    public String getPelTraileringles() {
        return pelTraileringles;
    }

    public void setPelTraileringles(String pelTraileringles) {
        this.pelTraileringles = pelTraileringles;
    }

    public String getPelPortadaespannol() {
        return pelPortadaespannol;
    }

    public void setPelPortadaespannol(String pelPortadaespannol) {
        this.pelPortadaespannol = pelPortadaespannol;
    }

    public String getPelPortadaingles() {
        return pelPortadaingles;
    }

    public void setPelPortadaingles(String pelPortadaingles) {
        this.pelPortadaingles = pelPortadaingles;
    }

    public LocalDate getPelFechaestreno() {
        return pelFechaestreno;
    }

    public void setPelFechaestreno(LocalDate pelFechaestreno) {
        this.pelFechaestreno = pelFechaestreno;
    }

    public LocalDate getPelFechafin() {
        return pelFechafin;
    }

    public void setPelFechafin(LocalDate pelFechafin) {
        this.pelFechafin = pelFechafin;
    }

    public String getPelEstado() {
        return pelEstado;
    }

    public void setPelEstado(String pelEstado) {
        this.pelEstado = pelEstado;
    }

    public String getPelResennaespannol() {
        return pelResennaespannol;
    }

    public void setPelResennaespannol(String pelResennaespannol) {
        this.pelResennaespannol = pelResennaespannol;
    }

    public String getPelResennaingles() {
        return pelResennaingles;
    }

    public void setPelResennaingles(String pelResennaingles) {
        this.pelResennaingles = pelResennaingles;
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
        hash += (pelId != null ? pelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelicula)) {
            return false;
        }
        Pelicula other = (Pelicula) object;
        if ((this.pelId == null && other.pelId != null) || (this.pelId != null && !this.pelId.equals(other.pelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.Pelicula[ pelId=" + pelId + " ]";
    }
    
}
