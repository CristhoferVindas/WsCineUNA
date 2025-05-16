/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.Asiento;
import cr.ac.una.wscineuna.model.AsientoDto;
import cr.ac.una.wscineuna.model.Sala;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
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
 * @author Arian
 */
@Stateless
@LocalBean
public class AsientoService {
    
    private static final Logger LOG = Logger.getLogger(AsientoService.class.getName());

    
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta getAsiento(Long id) {
        try {
            Query qryAsiento = em.createNamedQuery("Asiento.findByAsiId", Asiento.class);
            qryAsiento.setParameter("asiId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Asiento", new AsientoDto((Asiento) qryAsiento.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un asiento con el código ingresado.", "getAsiento NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "getAsiento NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "getAsiento " + ex.getMessage());
        }
    }
        
        
        public Respuesta guardarAsiento(AsientoDto asientoDto) {
        try {
            Asiento asiento;
            Sala sala;
            sala = em.find(Sala.class, asientoDto.getIDsala());
            if (sala == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asiento a modificar.", "guardarAsiento NoResultException");
                }
            
            if (asientoDto.getId()!= null && asientoDto.getId()> 0) {
                asiento = em.find(Asiento.class, asientoDto.getId());
                if (asiento == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asiento a modificar.", "guardarAsiento NoResultException");
                }
                asiento.setAsiIdsal(sala);
                asiento.actualizarAsiento(asientoDto);
                asiento = em.merge(asiento);
            } else {
                
                asiento = new Asiento(asientoDto);
                asiento.setAsiIdsal(sala);
                em.persist(asiento);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Asiento", new AsientoDto(asiento));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el asiento.", "guardarAsiento " + ex.getMessage());
        }
    }
        
        public Respuesta eliminarAsiento(Long id) {
        try {
            Asiento asiento;
            if (id != null && id > 0) {
                asiento = em.find(Asiento.class, id);
                if (asiento == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asiento a eliminar.", "eliminarAsiento NoResultException");
                }
                
//                Sala sala = em.find(Sala.class, asiento.getAsiIdsal().getSalId());
//                sala.getAsientoList().remove(asiento);
                em.remove(asiento);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el asiento a eliminar.", "eliminarAsiento NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el asiento porque tiene relaciones con otros registros.", "eliminarAsiento " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el asiento.", "eliminarAsiento " + ex.getMessage());
        }
    }
    public Respuesta getAsientos() {
        try {
            Query qryAsiento = em.createNamedQuery("Asiento.findAll", Asiento.class);

            List<Asiento> asientos = qryAsiento.getResultList();
            List<AsientoDto> asientoDtos = new ArrayList<>();
            for (Asiento asi : asientos) {
                AsientoDto asiento = new AsientoDto(asi);

                asientoDtos.add(asiento);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Asientos", asientoDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un asiento con el código ingresado.", "getAsiento NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "getAsiento NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "getAsiento " + ex.getMessage());
        }
    }
}
