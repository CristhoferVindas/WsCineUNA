/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.Pelicula;
import cr.ac.una.wscineuna.model.Tanda;
import cr.ac.una.wscineuna.model.TandaDto;
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
public class TandaService {

    private static final Logger LOG = Logger.getLogger(TandaService.class.getName());

    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;

    public Respuesta getTanda(Long id) {
        try {
            Query qryTanda = em.createNamedQuery("Tanda.findByTanId", Tanda.class);
            qryTanda.setParameter("tanId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Tanda", new TandaDto((Tanda) qryTanda.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tanda con el código ingresado.", "getTanda NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tanda.", "getTanda NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tanda.", "getTanda " + ex.getMessage());
        }
    }

    public Respuesta guardarTanda(TandaDto tandaDto) {
        try {
            Tanda tanda;
            if (tandaDto.getId() != null && tandaDto.getId() > 0) {
                tanda = em.find(Tanda.class, tandaDto.getId());
                if (tanda == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el tanda a modificar.", "guardarTanda NoResultException");
                }
                tanda.actualizarTanda(tandaDto);
                tanda = em.merge(tanda);
            } else {
                tanda = new Tanda(tandaDto);
                em.persist(tanda);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Tanda", new TandaDto(tanda));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el tanda.", "guardarTanda " + ex.getMessage());
        }
    }

    public Respuesta eliminarTanda(Long id) {
        try {
            Tanda tanda;
            if (id != null && id > 0) {
                tanda = em.find(Tanda.class, id);
                if (tanda == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el tanda a eliminar.", "eliminarTanda NoResultException");
                }
                em.remove(tanda);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el tanda a eliminar.", "eliminarTanda NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el tanda porque tiene relaciones con otros registros.", "eliminarTanda " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el tanda.", "eliminarTanda " + ex.getMessage());
        }
    }

    public Respuesta getTandas(Long peliculaP) {
        try {
            Pelicula pelicula;
            pelicula = em.find(Pelicula.class, peliculaP);
            Query qryTanda = em.createNamedQuery("Tanda.findByTanxpelicula", Tanda.class);
            qryTanda.setParameter("pelicula", pelicula);

            List<Tanda> tandas = qryTanda.getResultList();
            List<TandaDto> tandasDto = new ArrayList<>();
            for (Tanda tanda : tandas) {
                tandasDto.add(new TandaDto(tanda));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Tandas", tandasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen tandas con los criterios ingresados.", "getTandas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la tanda.", "getTanda " + ex.getMessage());
        }
    }
}
