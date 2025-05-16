/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

/**
 *
 * @author Cristhofer
 */
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String pApellido;
    private String sApellido;       
    private String usuario;
    private String contrasenna;
    private String correo;
    private String idioma;
    private String tipo;
    private String estado;
    private String restablecido;
    private String token;
    private boolean modificado;

    public UsuarioDto() {
        this.modificado = false;
    }
    public UsuarioDto(Usuario usuario) {
        this();
        this.id = usuario.getUsuId();
        this.nombre = usuario.getUsuNombre();
        this.pApellido = usuario.getUsuPapellido();
        this.sApellido = usuario.getUsuSapellido();
        this.usuario = usuario.getUsuUsuario();
        this.contrasenna = usuario.getUsuContrasenna();
        this.correo = usuario.getUsuCorreo();
        this.idioma = usuario.getUsuIdioma();
        this.tipo = usuario.getUsuTipo();
        this.estado = usuario.getUsuEstado();
        this.restablecido = usuario.getUsuRestablecido();  
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getpApellido() {
        return pApellido;
    }

    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRestablecido() {
        return restablecido;
    }

    public void setRestablecido(String restablecido) {
        this.restablecido = restablecido;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" + "id=" + id + ", nombre=" + nombre + ", pApellido=" + pApellido + ", idioma=" + idioma + ", tipo=" + tipo + ", estado=" + estado + ", restablecido=" + restablecido + ", modificado=" + modificado + '}';
    }

}
