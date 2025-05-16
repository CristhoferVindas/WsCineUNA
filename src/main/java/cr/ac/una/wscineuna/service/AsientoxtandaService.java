/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.Asiento;
import cr.ac.una.wscineuna.model.Asientoxtanda;
import cr.ac.una.wscineuna.model.AsientoxtandaDto;
import cr.ac.una.wscineuna.model.Tanda;
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
public class AsientoxtandaService {

    private static final Logger LOG = Logger.getLogger(AsientoxtandaService.class.getName());

    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;

    public Respuesta getAsientoxtanda(Long id) {
        try {
            Query qryAsientoxtanda = em.createNamedQuery("Asientoxtanda.findByAxtId", Asientoxtanda.class);
            qryAsientoxtanda.setParameter("axtId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Asientoxtanda", new AsientoxtandaDto((Asientoxtanda) qryAsientoxtanda.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un asientoxtanda con el código ingresado.", "getAsientoxtanda NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asientoxtanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asientoxtanda.", "getAsiento NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asientoxtanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asientoxtanda.", "getAsientoxtanda " + ex.getMessage());
        }
    }

    public Respuesta getAsientosxtandas(Long idTanda) {
        try {
            Tanda tanda;
            tanda = em.find(Tanda.class, idTanda);
            if (tanda == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asientoxtanda.", "BuscarAsientoxtanda NoResultException");
            }
            Query qryAsientoxtanda = em.createNamedQuery("Asientoxtanda.findByIdTan", Asientoxtanda.class);
            qryAsientoxtanda.setParameter("axtIdtan", tanda);

            List<Asientoxtanda> asientoxtandas = qryAsientoxtanda.getResultList();
            List<AsientoxtandaDto> asientoxtandasDto = new ArrayList<>();
            for (Asientoxtanda asientoxtanda : asientoxtandas) {
                AsientoxtandaDto asientoxtandaDto = new AsientoxtandaDto(asientoxtanda);
                asientoxtandaDto.setIDAsiento(asientoxtanda.getAxtIdasi().getAsiId());
                asientoxtandaDto.setIDTanda(asientoxtanda.getAxtIdtan().getTanId());
                if(asientoxtandaDto.getIdFactura() == null){
                    asientoxtandaDto.setIDFactura(Long.valueOf(0));
                }else{
                    asientoxtandaDto.setIDFactura(asientoxtandaDto.getIdFactura().getFacId());
                }
                
                asientoxtandasDto.add(asientoxtandaDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Asientoxtandas", asientoxtandasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen asientoxtandas con los criterios ingresados.", "getAsientoxtandas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la asientoxtanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la asientoxtanda.", "getAsientoxtanda " + ex.getMessage());
        }

    }

    public Respuesta getAsientosxtandas() {
        try {
            Query qryAsientoxtanda = em.createNamedQuery("Asientoxtanda.findByIDfacturaNull", Asientoxtanda.class);

            List<Asientoxtanda> asientoxtandas = qryAsientoxtanda.getResultList();
            List<AsientoxtandaDto> asientoxtandasDto = new ArrayList<>();
            for (Asientoxtanda asientoxtanda : asientoxtandas) {
                AsientoxtandaDto asientoxtandaDto = new AsientoxtandaDto(asientoxtanda);
                asientoxtandaDto.setIDAsiento(asientoxtanda.getAxtIdasi().getAsiId());
                asientoxtandaDto.setIDTanda(asientoxtanda.getAxtIdtan().getTanId());
                if(asientoxtandaDto.getIdFactura() == null){
                    asientoxtandaDto.setIDFactura(Long.valueOf(0));
                }else{
                    asientoxtandaDto.setIDFactura(asientoxtandaDto.getIdFactura().getFacId());
                }
                asientoxtandasDto.add(asientoxtandaDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Asientoxtandas", asientoxtandasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen asientoxtandas con los criterios ingresados.", "getAsientoxtandas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la asientoxtanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la asientoxtanda.", "getAsientoxtanda " + ex.getMessage());
        }

    }

    public Respuesta guardarAsientoxtanda(AsientoxtandaDto asientoxtandaDto) {
        try {

            Tanda tanda = em.find(Tanda.class, asientoxtandaDto.getIDTanda());
            if (tanda == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asientoxtanda a modificar.", "guardarAsientoxtanda NoResultException");
            }
            Asiento asiento = em.find(Asiento.class, asientoxtandaDto.getIDAsiento());
            if (asiento == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asientoxtanda a modificar.", "guardarAsientoxtanda NoResultException");
            }
            Asientoxtanda asientoxtanda;

            if (asientoxtandaDto.getId() != null && asientoxtandaDto.getId() > 0) {
                asientoxtanda = em.find(Asientoxtanda.class, asientoxtandaDto.getId());
                if (asientoxtanda == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asientoxtanda a modificar.", "guardarAsientoxtanda NoResultException");
                }
                asientoxtanda.actualizarAsientoxtanda(asientoxtandaDto);
                asientoxtanda.setAxtIdtan(tanda);
                asientoxtanda.setAxtIdasi(asiento);
                asientoxtanda = em.merge(asientoxtanda);
            } else {

                asientoxtanda = new Asientoxtanda(asientoxtandaDto);
                asientoxtanda.setAxtIdtan(tanda);
                asientoxtanda.setAxtIdasi(asiento);
                em.persist(asientoxtanda);
            }
            em.flush();
            AsientoxtandaDto asientoxtandaDtos = new AsientoxtandaDto(asientoxtanda);
                asientoxtandaDtos.setIDAsiento(asientoxtanda.getAxtIdasi().getAsiId());
                asientoxtandaDtos.setIDTanda(asientoxtanda.getAxtIdtan().getTanId());
                if(asientoxtandaDtos.getIdFactura() == null){
                    asientoxtandaDtos.setIDFactura(Long.valueOf(0));
                }else{
                    asientoxtandaDtos.setIDFactura(asientoxtandaDto.getIdFactura().getFacId());
                }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Asientoxtanda",asientoxtandaDtos);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el asientoxtanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el asientoxtanda.", "guardarAsientoxtanda " + ex.getMessage());
        }
    }

    public Respuesta eliminarAsientoxtanda(Long id) {
        try {
            Asientoxtanda asientoxtanda;
            if (id != null && id > 0) {
                asientoxtanda = em.find(Asientoxtanda.class, id);
                if (asientoxtanda == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asientoxtanda a eliminar.", "eliminarAsientoxtanda NoResultException");
                }
                em.remove(asientoxtanda);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el asientoxtanda a eliminar.", "eliminarAsientoxtanda NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el asientoxtanda porque tiene relaciones con otros registros.", "eliminarAsientoxtanda " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el asientoxtanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el asientoxtanda.", "eliminarAsientoxtanda " + ex.getMessage());
        }
    }

}
