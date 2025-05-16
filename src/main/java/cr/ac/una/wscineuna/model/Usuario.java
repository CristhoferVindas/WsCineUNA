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
@Table(name = "CINEUNA_USUARIO", schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuId", query = "SELECT u FROM Usuario u WHERE u.usuId = :usuId"),
    @NamedQuery(name = "Usuario.findByUsuNombre", query = "SELECT u FROM Usuario u WHERE u.usuNombre = :usuNombre"),
    @NamedQuery(name = "Usuario.findByUsuNombreYContrasena", query = "SELECT u FROM Usuario u WHERE u.usuUsuario = :usuUsuario and u.usuContrasenna = :usuContrasenna", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "Usuario.findByUsuPapellido", query = "SELECT u FROM Usuario u WHERE u.usuPapellido = :usuPapellido"),
    @NamedQuery(name = "Usuario.findByUsuSapellido", query = "SELECT u FROM Usuario u WHERE u.usuSapellido = :usuSapellido"),
    @NamedQuery(name = "Usuario.findByUsuUsuario", query = "SELECT u FROM Usuario u WHERE u.usuUsuario = :usuUsuario"),
    @NamedQuery(name = "Usuario.findByUsuContrasenna", query = "SELECT u FROM Usuario u WHERE u.usuContrasenna = :usuContrasenna"),
    @NamedQuery(name = "Usuario.findByUsuCorreo", query = "SELECT u FROM Usuario u WHERE u.usuCorreo = :usuCorreo"),
    @NamedQuery(name = "Usuario.findByUsuIdioma", query = "SELECT u FROM Usuario u WHERE u.usuIdioma = :usuIdioma"),
    @NamedQuery(name = "Usuario.findByUsuTipo", query = "SELECT u FROM Usuario u WHERE u.usuTipo = :usuTipo"),
    @NamedQuery(name = "Usuario.findByUsuEstado", query = "SELECT u FROM Usuario u WHERE u.usuEstado = :usuEstado"),
    @NamedQuery(name = "Usuario.findByUsuRestablecido", query = "SELECT u FROM Usuario u WHERE u.usuRestablecido = :usuRestablecido")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CINEUNA_USUARIO_USU_ID_GENERATOR", sequenceName = "CINEUNA.CINEUNA_USUARIO_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CINEUNA_USUARIO_USU_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "USU_ID")
    private Long usuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_NOMBRE")
    private String usuNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_PAPELLIDO")
    private String usuPapellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_SAPELLIDO")
    private String usuSapellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_USUARIO")
    private String usuUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_CONTRASENNA")
    private String usuContrasenna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USU_CORREO")
    private String usuCorreo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USU_IDIOMA")
    private String usuIdioma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USU_TIPO")
    private String usuTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USU_ESTADO")
    private String usuEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USU_RESTABLECIDO")
    private String usuRestablecido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facIdusu", fetch = FetchType.LAZY)
    private List<Factura> facturaList;

    public Usuario() {
    }

    public Usuario(Long usuId) {
        this.usuId = usuId;
    }

    public Usuario(Long usuId, String usuNombre, String usuPapellido, String usuSapellido, String usuUsuario, String usuContrasenna, String usuCorreo, String usuIdioma, String usuTipo, String usuEstado, String usuRestablecido) {
        this.usuId = usuId;
        this.usuNombre = usuNombre;
        this.usuPapellido = usuPapellido;
        this.usuSapellido = usuSapellido;
        this.usuUsuario = usuUsuario;
        this.usuContrasenna = usuContrasenna;
        this.usuCorreo = usuCorreo;
        this.usuIdioma = usuIdioma;
        this.usuTipo = usuTipo;
        this.usuEstado = usuEstado;
        this.usuRestablecido = usuRestablecido;
    }

    public Usuario(UsuarioDto usuarioDto) {
        this.usuId = usuarioDto.getId();
        actualizarUsuario(usuarioDto);
    }

    public void actualizarUsuario(UsuarioDto usuarioDto) {
        this.usuNombre = usuarioDto.getNombre();
        this.usuPapellido = usuarioDto.getpApellido();
        this.usuSapellido = usuarioDto.getsApellido();
        this.usuUsuario = usuarioDto.getUsuario();
        this.usuContrasenna = usuarioDto.getContrasenna();
        this.usuCorreo = usuarioDto.getCorreo();
        this.usuIdioma = usuarioDto.getIdioma();
        this.usuTipo = usuarioDto.getTipo();
        this.usuEstado = usuarioDto.getEstado();
        this.usuRestablecido = usuarioDto.getRestablecido();

    }
    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuPapellido() {
        return usuPapellido;
    }

    public void setUsuPapellido(String usuPapellido) {
        this.usuPapellido = usuPapellido;
    }

    public String getUsuSapellido() {
        return usuSapellido;
    }

    public void setUsuSapellido(String usuSapellido) {
        this.usuSapellido = usuSapellido;
    }

    public String getUsuUsuario() {
        return usuUsuario;
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    public String getUsuContrasenna() {
        return usuContrasenna;
    }

    public void setUsuContrasenna(String usuContrasenna) {
        this.usuContrasenna = usuContrasenna;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuIdioma() {
        return usuIdioma;
    }

    public void setUsuIdioma(String usuIdioma) {
        this.usuIdioma = usuIdioma;
    }

    public String getUsuTipo() {
        return usuTipo;
    }

    public void setUsuTipo(String usuTipo) {
        this.usuTipo = usuTipo;
    }

    public String getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

    public String getUsuRestablecido() {
        return usuRestablecido;
    }

    public void setUsuRestablecido(String usuRestablecido) {
        this.usuRestablecido = usuRestablecido;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.Usuario[ usuId=" + usuId + " ]";
    }
    
}
