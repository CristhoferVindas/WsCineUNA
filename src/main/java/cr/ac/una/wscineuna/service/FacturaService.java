/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.Alimentoxfactura;
import cr.ac.una.wscineuna.model.AlimentoxfacturaDto;
import cr.ac.una.wscineuna.model.Asiento;
import cr.ac.una.wscineuna.model.Asientoxtanda;
import cr.ac.una.wscineuna.model.AsientoxtandaDto;
import cr.ac.una.wscineuna.model.Factura;
import cr.ac.una.wscineuna.model.FacturaDto;
import cr.ac.una.wscineuna.model.Tanda;
import cr.ac.una.wscineuna.model.Usuario;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.DetalleFactura;
import cr.ac.una.wscineuna.util.DetalleFacturaAlimento;
import cr.ac.una.wscineuna.util.Respuesta;
import cr.ac.una.wscineuna.util.TableViewFacturas;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Cristhofer
 */
@Stateless
@LocalBean
public class FacturaService {

    private static final Logger LOG = Logger.getLogger(FacturaService.class.getName());

    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;

    public Respuesta getDetalleFactura(Long id) {
        try {
            Query qryFactura = em.createNamedQuery("Factura.DetalleFactura", TableViewFacturas.class);
            qryFactura.setParameter("tanId", id);

            List<Object[]> objects = qryFactura.getResultList();
            List<TableViewFacturas> facturases = new ArrayList<>();
            for (Object[] object : objects) {
                TableViewFacturas facturas = new TableViewFacturas();
                facturas.setFila(object[0].toString());
                facturas.setNumero(Integer.valueOf(object[1].toString()));
                facturas.setNombreSala(object[2].toString());
                facturas.setFecha(LocalDate.parse(object[3].toString()));
                facturas.setHorainicio(object[4].toString());
                facturas.setPeliculanombreEspañol(object[5].toString());
                facturas.setPeliculanombreIngles(object[6].toString());
                facturas.setPrecio(Double.valueOf(object[7].toString()));
                facturases.add(facturas);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Facturas", facturases);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un factura con el código ingresado.", "getFactura NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura " + ex.getMessage());
        }
    }

    public Respuesta getComprobanteFactura(Long facId) {
        List<DetalleFactura> detalleFacturas = new ArrayList<>();
        String PdfEncode = "";
        try {
            Query qryFactura = em.createNamedQuery("Factura.Comprobante", DetalleFactura.class);
            qryFactura.setParameter("facId", facId);

            List<Object[]> objects = qryFactura.getResultList();

            for (Object[] object : objects) {
                DetalleFactura detalleFactura = new DetalleFactura();
                detalleFactura.setNombreSala(object[0].toString());
                detalleFactura.setFechaFactura(object[1].toString());
                detalleFactura.setHoraFactura(object[2].toString());
                detalleFactura.setPrecioTotal(object[3].toString());
                detalleFactura.setFila(object[4].toString());
                detalleFactura.setNumero(object[5].toString());
                detalleFactura.setFechaTanda(object[6].toString());
                detalleFactura.setHoraTanda(object[7].toString());
                detalleFactura.setCostoEntrada(object[8].toString());
                detalleFactura.setNombrePeliculaEspanol(object[9].toString());
                detalleFactura.setNombrePeliculaIngles(object[10].toString());
                detalleFacturas.add(detalleFactura);
            }
            //-----------------------------------------------------------------------------------
            try {
                String path = "C:\\Users\\vinda\\OneDrive\\Documentos\\GitHub\\WSCineUNA\\src\\main\\java\\cr\\ac\\una\\wscineuna\\util\\Factura.jasper";
                JasperReport reporte = null;
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                JRBeanCollectionDataSource arrayDataSource = new JRBeanCollectionDataSource(detalleFacturas);
                HashMap parametros = new HashMap();
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, arrayDataSource);
                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jprint);
                PdfEncode = Base64.getEncoder().encodeToString(pdfBytes);
            } catch (JRException ex) {
                Logger.getLogger(AsientoService.class.getName()).log(Level.SEVERE, null, ex);
            }
            //----------------------------------------------------------------------------------
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Facturas", PdfEncode);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un factura con el código ingresado.", "getFactura NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura " + ex.getMessage());
        }
    }

    public Respuesta getComprobanteFacturaAlimento(Long facId) {
        List<DetalleFacturaAlimento> detalleFacturas = new ArrayList<>();
        String PdfEncode = "";
        try {
            Query qryFactura = em.createNamedQuery("Factura.ComprobanteAlimento", DetalleFacturaAlimento.class);
            qryFactura.setParameter("facId", facId);

            List<Object[]> objects = qryFactura.getResultList();

            for (Object[] object : objects) {
                DetalleFacturaAlimento detalleFactura = new DetalleFacturaAlimento();
                detalleFactura.setFechaFactura(object[0].toString());
                detalleFactura.setHoraFactura(LocalTime.parse(object[1].toString()).toString());
                detalleFactura.setPrecioAlimento(object[2].toString());
                detalleFactura.setNombreAlimento(object[3].toString());
                detalleFactura.setCantidadAlimento(object[4].toString());
                detalleFacturas.add(detalleFactura);
            }
            //-----------------------------------------------------------------------------------
            try {
                String path = "C:\\Users\\vinda\\OneDrive\\Documentos\\GitHub\\WSCineUNA\\src\\main\\java\\cr\\ac\\una\\wscineuna\\util\\FacturaAlimento.jasper";
                JasperReport reporte = null;
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                JRBeanCollectionDataSource arrayDataSource = new JRBeanCollectionDataSource(detalleFacturas);
                HashMap parametros = new HashMap();
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, arrayDataSource);
                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jprint);
                PdfEncode = Base64.getEncoder().encodeToString(pdfBytes);
            } catch (JRException ex) {
                Logger.getLogger(AsientoService.class.getName()).log(Level.SEVERE, null, ex);
            }
            //----------------------------------------------------------------------------------
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Facturas", PdfEncode);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un factura con el código ingresado.", "getFactura NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura " + ex.getMessage());
        }
    }
    public Respuesta getFactura(Long id) {
        try {
            Query qryFactura = em.createNamedQuery("Factura.findByFacId", Factura.class);
            qryFactura.setParameter("facId", id);

            Factura factura = (Factura) qryFactura.getSingleResult();
            FacturaDto facturaDto = new FacturaDto(factura);
            for (Asientoxtanda axt : factura.getAsientoxtandaList()) {
                facturaDto.getAsientosxTandas().add(new AsientoxtandaDto(axt));
            }
            for (Alimentoxfactura axt : factura.getAlimentoxfacturaList()) {
                facturaDto.getAlimentoxFactura().add(new AlimentoxfacturaDto(axt));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Factura", facturaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un factura con el código ingresado.", "getFactura NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura " + ex.getMessage());
        }
    }

    public Respuesta getFechaFactura() {
        try {
            Query qryFactura = em.createNamedQuery("Factura.FechaYHora", Timestamp.class);
            List<Timestamp> timestamps = qryFactura.getResultList();
            Timestamp t;
            LocalDateTime date = null;
            for (Timestamp timestamp : timestamps) {
                t = timestamp;
                date = t.toLocalDateTime();
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Factura", date);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un factura con el código ingresado.", "getFactura NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el factura.", "getFactura " + ex.getMessage());
        }
    }

    public Respuesta guardarFactura(FacturaDto facturaDto) {
        try {
            Factura fac = null;
            Usuario usuario = em.find(Usuario.class, facturaDto.getIDUsuario());
            if (usuario == null) {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario.", "guardarusuario NoResultException");
            }
            Factura factura;
            if (facturaDto.getId() != null && facturaDto.getId() > 0) {
                factura = em.find(Factura.class, facturaDto.getId());
                if (factura == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el factura a modificar.", "guardarFactura NoResultException");
                }
                factura.actualizarFactura(facturaDto);
                for (AsientoxtandaDto axt : facturaDto.getAsientosxTandasEliminadas()) {
                    factura.getAsientoxtandaList().remove(new Asientoxtanda(axt.getId()));
                }
                if (!facturaDto.getAsientosxTandas().isEmpty()) {
                    for (AsientoxtandaDto axtDto : facturaDto.getAsientosxTandas()) {
                        Asientoxtanda axt = em.find(Asientoxtanda.class, axtDto.getId());
                        axt.setFacId(factura);
                        factura.getAsientoxtandaList().add(axt);
                    }
                }

                for (AlimentoxfacturaDto axf : facturaDto.getAlimentoxFacturaEliminadas()) {
                    factura.getAsientoxtandaList().remove(new Asientoxtanda(axf.getId()));
                }
                if (!facturaDto.getAsientosxTandas().isEmpty()) {
                    for (AlimentoxfacturaDto axfDto : facturaDto.getAlimentoxFactura()) {
                        Alimentoxfactura axt = em.find(Alimentoxfactura.class, axfDto.getId());
                        axt.setAxfIdfac(factura);
                        factura.getAlimentoxfacturaList().add(axt);
                    }
                }

                factura = em.merge(factura);
            } else {
                factura = new Factura(facturaDto);
                factura.setFacIdusu(usuario);
                em.persist(factura);
                em.flush();
                System.out.print(factura);

                fac = em.find(Factura.class, factura.getFacId());
                for (AsientoxtandaDto axt : facturaDto.getAsientosxTandasEliminadas()) {
                    fac.getAsientoxtandaList().remove(new Asientoxtanda(axt.getId()));
                }
                if (!facturaDto.getAsientosxTandas().isEmpty()) {
                    for (AsientoxtandaDto axtDto : facturaDto.getAsientosxTandas()) {
                        Asientoxtanda axt = em.find(Asientoxtanda.class, axtDto.getId());
                        Asiento asi = em.find(Asiento.class, axtDto.getIDAsiento());
                        Tanda tan = em.find(Tanda.class, axtDto.getIDTanda());

                        axt.setAxtIdtan(tan);
                        axt.setAxtIdasi(asi);
                        axt.setFacId(fac);
                    }
                }

                for (AlimentoxfacturaDto axf : facturaDto.getAlimentoxFacturaEliminadas()) {
                    fac.getAsientoxtandaList().remove(new Asientoxtanda(axf.getId()));
                }
                if (!facturaDto.getAsientosxTandas().isEmpty()) {
                    for (AlimentoxfacturaDto axfDto : facturaDto.getAlimentoxFactura()) {
                        Alimentoxfactura axt = em.find(Alimentoxfactura.class, axfDto.getIdAlimento());
                        axt.setAxfIdfac(fac);
                    }
                }
                fac = em.merge(fac);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Factura", new FacturaDto(factura));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el factura.", "guardarFactura " + ex.getMessage());
        }
    }

    public Respuesta eliminarFactura(Long id) {
        try {
            Factura factura;
            if (id != null && id > 0) {
                factura = em.find(Factura.class, id);
                if (factura == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el factura a eliminar.", "eliminarFactura NoResultException");
                }
                em.remove(factura);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el factura a eliminar.", "eliminarFactura NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el factura porque tiene relaciones con otros registros.", "eliminarFactura " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el factura.", "eliminarFactura " + ex.getMessage());
        }
    }

}
