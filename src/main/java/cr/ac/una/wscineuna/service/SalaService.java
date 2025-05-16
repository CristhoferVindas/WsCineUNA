/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.Asiento;
import cr.ac.una.wscineuna.model.AsientoDto;
import cr.ac.una.wscineuna.model.Sala;
import cr.ac.una.wscineuna.model.SalaDto;
import cr.ac.una.wscineuna.model.Tanda;
import cr.ac.una.wscineuna.model.TandaDto;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import java.io.File;
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
public class SalaService {
    
    private static final Logger LOG = Logger.getLogger(SalaService.class.getName());
    
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta getSala(Long id) {
        try {
            Query qrySala = em.createNamedQuery("Sala.findBySalId", Sala.class);
            qrySala.setParameter("salId", id);

            Sala sala = (Sala) qrySala.getSingleResult();
            SalaDto salaDto = new SalaDto(sala);
            for (Tanda tan : sala.getTandaList()) {
                salaDto.getTandas().add(new TandaDto(tan));
            }
            for (Asiento asi : sala.getAsientoList()) {
                salaDto.getAsientos().add(new AsientoDto(asi));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sala", salaDto);
            
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un sala con el código ingresado.", "getSala NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el sala.", "getSala NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el sala.", "getSala " + ex.getMessage());
        }
    }
    
    public Respuesta getSalas(String nombre, String detalle) {
        try {
            Query qrySala = em.createNamedQuery("Sala.findBySalNombreDetalle", Sala.class);
            qrySala.setParameter("nombre", nombre);
            qrySala.setParameter("detalle", detalle);
            List<Sala> salas = qrySala.getResultList();
            List<SalaDto> salasDto = new ArrayList<>();
            for (Sala sala : salas) {
                salasDto.add(new SalaDto(sala));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Salas", salasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen salas con los criterios ingresados.", "getSalas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la sala.", "getSala " + ex.getMessage());
        }
        
    }
    
    public Respuesta guardarSala(SalaDto salaDto) {
        try {
            Sala sala;
            if (salaDto.getId() != null && salaDto.getId() > 0) {
                sala = em.find(Sala.class, salaDto.getId());
                if (sala == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el sala a modificar.", "guardarSala NoResultException");
                }
                sala.actualizarSala(salaDto);
                for (TandaDto emp : salaDto.getTandasEliminadas()) {
                    sala.getTandaList().remove(new Tanda(emp.getId()));
                }
                if (!salaDto.getTandas().isEmpty()) {
                    for (TandaDto tan : salaDto.getTandas()) {
                        Tanda tanda = em.find(Tanda.class, tan.getId());
                        tanda.setTanIdsal(sala);
                        sala.getTandaList().add(tanda);
                    }
                }
                for (AsientoDto asi : salaDto.getAsientosEliminadas()) {
                    sala.getAsientoList().remove(new Asiento(asi.getId()));
                    Asiento asiento = em.find(Asiento.class, asi.getId());
                    em.remove(asiento);
                }
                if (!salaDto.getAsientos().isEmpty()) {
                    for (AsientoDto asi : salaDto.getAsientos()) {
                        if (asi.isModificado()) {
                            Asiento asiento = em.find(Asiento.class, asi.getId());
                            asiento.setAsiIdsal(sala);
                            sala.getAsientoList().add(asiento);
                        }
                    }
                }
                sala = em.merge(sala);
            } else {
                sala = new Sala(salaDto);
                em.persist(sala);
            }
            em.flush();

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sala", new SalaDto(sala));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el sala.", "guardarSala " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarSala(Long id) {
        try {
            Sala sala;
            
            if (id != null && id > 0) {
                sala = em.find(Sala.class, id);
                if (sala == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el sala a eliminar.", "eliminarSala NoResultException");
                }
                
                em.remove(sala);
                File directory = new File("..\\Asientos\\" + sala.getSalNombre() + ".jpg");
                directory.delete();
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el sala a eliminar.", "eliminarSala NoResultException");
            }
            em.flush();
            String RutaEliminar = sala.getSalImagenasiento();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", RutaEliminar);
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el sala porque tiene relaciones con otros registros.", "eliminarSala " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el sala.", "eliminarSala " + ex.getMessage());
        }
    }
    
}
