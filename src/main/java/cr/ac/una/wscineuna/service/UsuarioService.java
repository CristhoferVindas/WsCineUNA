/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.Usuario;
import cr.ac.una.wscineuna.model.UsuarioDto;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cristhofer
 */
@Stateless
@LocalBean
public class UsuarioService {
    private static final Logger LOG = Logger.getLogger(UsuarioService.class.getName());

    
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta validarUsuario(String usuario, String clave) {
        try {
            Query qryActividad = em.createNamedQuery("Usuario.findByUsuNombreYContrasena", Usuario.class);
            qryActividad.setParameter("usuUsuario", usuario);
            qryActividad.setParameter("usuContrasenna", clave);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuarioDto((Usuario) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }
    
    public Respuesta getUsuario(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("Usuario.findByUsuId", Usuario.class);
            qryUsuario.setParameter("usuId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuarioDto((Usuario) qryUsuario.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con el código ingresado.", "getUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario " + ex.getMessage());
        }
    }
        
        public Respuesta getUsuarioPorCorreo(String correo) {
        try {
            Query qryUsuario = em.createNamedQuery("Usuario.findByUsuCorreo", Usuario.class);
            qryUsuario.setParameter("usuCorreo", correo);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuarioDto((Usuario) qryUsuario.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con el código ingresado.", "getUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta activacionUsuario(Long id) {
        try {
            Usuario usuario;

            usuario = em.find(Usuario.class, id);
            if (usuario == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "guardarUsuario NoResultException");
            }
            usuario.setUsuEstado("A");
            usuario = em.merge(usuario);

            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuarioDto(usuario));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "guardarUsuario " + ex.getMessage());
        }
    }
        public Respuesta guardarUsuario(UsuarioDto usuarioDto) {
        try {
            Usuario usuario;
            if (usuarioDto.getId()!= null && usuarioDto.getId()> 0) {
                usuario = em.find(Usuario.class, usuarioDto.getId());
                if (usuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "guardarUsuario NoResultException");
                }
                usuario.actualizarUsuario(usuarioDto);
                usuario = em.merge(usuario);
            } else {
                usuario = new Usuario(usuarioDto);
                em.persist(usuario);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new UsuarioDto(usuario));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "guardarUsuario " + ex.getMessage());
        }
    }
        
        public Respuesta eliminarUsuario(Long id) {
        try {
            Usuario usuario;
            if (id != null && id > 0) {
                usuario = em.find(Usuario.class, id);
                if (usuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a eliminar.", "eliminarUsuario NoResultException");
                }
                em.remove(usuario);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarUsuario NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el usuario porque tiene relaciones con otros registros.", "eliminarUsuario " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarUsuario " + ex.getMessage());
        }
    }
    
}
