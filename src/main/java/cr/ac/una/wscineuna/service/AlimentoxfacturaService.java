/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;


import cr.ac.una.wscineuna.model.Alimento;
import cr.ac.una.wscineuna.model.Alimentoxfactura;
import cr.ac.una.wscineuna.model.AlimentoxfacturaDto;
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
public class AlimentoxfacturaService {
    
    private static final Logger LOG = Logger.getLogger(AlimentoxfacturaService.class.getName());

    
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta getAlimentoxfactura(Long id) {
        try {
            Query qryAlimentoxfactura = em.createNamedQuery("Alimentoxfactura.findByAxfId", Alimentoxfactura.class);
            qryAlimentoxfactura.setParameter("axfId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Alimentoxfactura", new AlimentoxfacturaDto((Alimentoxfactura) qryAlimentoxfactura.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un alimentoxfactura con el código ingresado.", "getAlimentoxfactura NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el alimentoxfactura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el alimentoxfactura.", "getAlimentoxfactura NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el alimentoxfactura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el alimentoxfactura.", "getAlimentoxfactura " + ex.getMessage());
        }
    }
        
        
        public Respuesta guardarAlimentoxfactura(AlimentoxfacturaDto alimentoxfacturaDto) {
        try {
            Alimento alimento = em.find(Alimento.class, alimentoxfacturaDto.getIdAlimento());
            if (alimento == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el alimento a modificar.", "Encontrar Alimento NoResultException");
            }
            Alimentoxfactura alimentoxfactura;
            if (alimentoxfacturaDto.getId()!= null && alimentoxfacturaDto.getId()> 0) {
                alimentoxfactura = em.find(Alimentoxfactura.class, alimentoxfacturaDto.getId());
                if (alimentoxfactura == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el alimentoxfactura a modificar.", "guardarAlimentoxfactura NoResultException");
                }
                alimentoxfactura.actualizarAlimentoxfactura(alimentoxfacturaDto);
                alimentoxfactura = em.merge(alimentoxfactura);
            } else {
                alimentoxfactura = new Alimentoxfactura(alimentoxfacturaDto);
                alimentoxfactura.setAxfIdali(alimento);
                em.persist(alimentoxfactura);  
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Alimentoxfactura", new AlimentoxfacturaDto(alimentoxfactura));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el alimentoxfactura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el alimentoxfactura.", "guardarAlimentoxfactura " + ex.getMessage());
        }
    }
        
        public Respuesta eliminarAlimentoxfactura(Long id) {
        try {
            Alimentoxfactura alimentoxfactura;
            if (id != null && id > 0) {
                alimentoxfactura = em.find(Alimentoxfactura.class, id);
                if (alimentoxfactura == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el alimentoxfactura a eliminar.", "eliminarAlimentoxfactura NoResultException");
                }
                em.remove(alimentoxfactura);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el alimentoxfactura a eliminar.", "eliminarAlimentoxfactura NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el alimentoxfactura porque tiene relaciones con otros registros.", "eliminarAlimentoxfactura " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el alimentoxfactura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el alimentoxfactura.", "eliminarAlimentoxfactura " + ex.getMessage());
        }
    }
    
}
