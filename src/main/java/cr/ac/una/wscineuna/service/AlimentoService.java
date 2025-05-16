/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.Alimento;
import cr.ac.una.wscineuna.model.AlimentoDto;
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
 * @author Cristhofer
 */


@Stateless
@LocalBean
public class AlimentoService {
    private static final Logger LOG = Logger.getLogger(AlimentoService.class.getName());

    
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    
        public Respuesta getAlimento(Long id) {
        try {
            Query qryAlimento = em.createNamedQuery("Alimento.findByAliId", Alimento.class);
            qryAlimento.setParameter("aliId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Alimento", new AlimentoDto((Alimento) qryAlimento.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un alimento con el código ingresado.", "getAlimento NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el alimento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el alimento.", "getAlimento NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el alimento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el alimento.", "getAlimento " + ex.getMessage());
        }
    }
        
        
        public Respuesta guardarAlimento(AlimentoDto alimentoDto) {
        try {
            Alimento alimento;
            if (alimentoDto.getId()!= null && alimentoDto.getId()> 0) {
                alimento = em.find(Alimento.class, alimentoDto.getId());
                if (alimento == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el alimento a modificar.", "guardarAlimento NoResultException");
                }
                alimento.actualizarAlimento(alimentoDto);
                alimento = em.merge(alimento);
            } else {
                alimento = new Alimento(alimentoDto);
                em.persist(alimento);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Alimento", new AlimentoDto(alimento));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el alimento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el alimento.", "guardarAlimento " + ex.getMessage());
        }
    }
        
        public Respuesta eliminarAlimento(Long id) {
        try {
            Alimento alimento;
            if (id != null && id > 0) {
                alimento = em.find(Alimento.class, id);
                if (alimento == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el alimento a eliminar.", "eliminarAlimento NoResultException");
                }
                em.remove(alimento);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el alimento a eliminar.", "eliminarAlimento NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el alimento porque tiene relaciones con otros registros.", "eliminarAlimento " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el alimento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el alimento.", "eliminarAlimento " + ex.getMessage());
        }
    }
        
        public Respuesta getAlimentos() {
        try {
            Query qryAlimento = em.createNamedQuery("Alimento.findAll", Alimento.class);

            List<Alimento> alimentos = qryAlimento.getResultList();
            List<AlimentoDto> alimentosDto = new ArrayList<>();
            for (Alimento alimento : alimentos) {
                AlimentoDto alimentoDto = new AlimentoDto(alimento);
                alimentosDto.add(alimentoDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Alimentos", alimentosDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen alimentos con los criterios ingresados.", "getAlimentos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la alimento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la alimento.", "getAlimento " + ex.getMessage());
        }

    }
}
