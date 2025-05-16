/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.Pelicula;
import cr.ac.una.wscineuna.model.PeliculaDto;
import cr.ac.una.wscineuna.model.Tanda;
import cr.ac.una.wscineuna.model.TandaDto;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Reportes;
import cr.ac.una.wscineuna.util.Respuesta;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
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
public class PeliculaService {

    private static final Logger LOG = Logger.getLogger(PeliculaService.class.getName());

    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;

    public Respuesta ReporteCantPelBtwnFecha(String facFechaInicio, String facFechaFin, String pelEstado) {

        LocalDate fInicio = LocalDate.parse(facFechaInicio);
        LocalDate fFin = LocalDate.parse(facFechaFin);
        String PdfEncode = "";
        try {
            Query qryPelicula = em.createNamedQuery("Pelicula.ReporteCantPelByBtwnFecha", Reportes.class);
            qryPelicula.setParameter("facFechaInicio", fInicio);
            qryPelicula.setParameter("facFechaFin", fFin);
            qryPelicula.setParameter("pelEstado", pelEstado);

            List<Object[]> objects = qryPelicula.getResultList();
            List<Reportes> reportes = new ArrayList<>();
            for (Object[] object : objects) {
                Reportes reporte = new Reportes();
                reporte.setPeliculanombreEspañol(object[0].toString());
                reporte.setPeliculanombreIngles(object[1].toString());
                reporte.setFecha((object[2].toString()));
                reporte.setCantidad(Long.valueOf(object[3].toString()));
                reportes.add(reporte);
            }
            //-----------------------------------------------------------------------------------

            try {
                String path = "C:\\Users\\vinda\\OneDrive\\Documentos\\GitHub\\WSCineUNA\\src\\main\\java\\cr\\ac\\una\\wscineuna\\util\\ReportePorTanda.jasper";
                JasperReport reporte = null;
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                JRBeanCollectionDataSource arrayDataSource = new JRBeanCollectionDataSource(reportes);
                HashMap parametros = new HashMap();
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, arrayDataSource);
                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jprint);
                PdfEncode = Base64.getEncoder().encodeToString(pdfBytes);

            } catch (JRException ex) {
                Logger.getLogger(AsientoService.class.getName()).log(Level.SEVERE, null, ex);
            }
            //----------------------------------------------------------------------------------
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Peliculas", PdfEncode);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un pelicula con el código ingresado.", "getPelicula NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el pelicula.", "getPelicula NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el pelicula.", "getPelicula " + ex.getMessage());
        }
    }

    public Respuesta ReporteByPelicula(String peliculaEspanol, String peliculaIngles) {
        String PdfEncode = "";
        try {
            Query qryPelicula = em.createNamedQuery("Pelicula.ReporteCantPerByPelicula", Reportes.class);
            qryPelicula.setParameter("peliculaEspanol", peliculaEspanol);
            qryPelicula.setParameter("peliculaIngles", peliculaIngles);

            List<Object[]> objects = qryPelicula.getResultList();
            List<Reportes> reportes = new ArrayList<>();
            for (Object[] object : objects) {
                Reportes reporte = new Reportes();
                reporte.setPeliculanombreEspañol(object[0].toString());
                reporte.setPeliculanombreIngles(object[1].toString());
                reporte.setHorainicio(object[2].toString());
                reporte.setFecha(object[3].toString());
                reporte.setCantidad(Long.valueOf(object[4].toString()));
                reportes.add(reporte);
            }
            //-----------------------------------------------------------------------------------

            try {
                String path = "C:\\Users\\vinda\\OneDrive\\Documentos\\GitHub\\WsCineUNA\\src\\main\\java\\cr\\ac\\una\\wscineuna\\util\\ReportePeliculas.jasper";
                JasperReport reporte = null;
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                JRBeanCollectionDataSource arrayDataSource = new JRBeanCollectionDataSource(reportes);
                HashMap parametros = new HashMap();
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, arrayDataSource);
                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jprint);
                PdfEncode = Base64.getEncoder().encodeToString(pdfBytes);
            } catch (JRException ex) {
                Logger.getLogger(AsientoService.class.getName()).log(Level.SEVERE, null, ex);
            }

            //----------------------------------------------------------------------------------
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Peliculas", PdfEncode);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un pelicula con el código ingresado.", "getPelicula NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el pelicula.", "getPelicula NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el pelicula.", "getPelicula " + ex.getMessage());
        }
    }

    public Respuesta getPelicula(Long id) {
        try {
            Query qryPelicula = em.createNamedQuery("Pelicula.findByPelId", Pelicula.class);
            qryPelicula.setParameter("pelId", id);

            Pelicula pelicula = (Pelicula) qryPelicula.getSingleResult();
            PeliculaDto peliculaDto = new PeliculaDto(pelicula);
            for (Tanda tan : pelicula.getTandaList()) {
                peliculaDto.getTandas().add(new TandaDto(tan));
            }
           
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Pelicula", peliculaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un pelicula con el código ingresado.", "getPelicula NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el pelicula.", "getPelicula NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el pelicula.", "getPelicula " + ex.getMessage());
        }
    }

    public Respuesta getPeliculas(String nombreEspannol, String nombreIngles) { //String fechaEstreno
        try {
            Query qryPelicula = em.createNamedQuery("Pelicula.findByPelNombreespannolNombreingles", Pelicula.class);
            qryPelicula.setParameter("nombreEspannol", nombreEspannol);
            qryPelicula.setParameter("nombreIngles", nombreIngles);

            List<Pelicula> peliculas = qryPelicula.getResultList();
            List<PeliculaDto> peliculasDto = new ArrayList<>();
            for (Pelicula pelicula : peliculas) {
                PeliculaDto peliculaDto = new PeliculaDto(pelicula);
                File portadaInglesFile = new File("..\\Portadas\\" + peliculaDto.getPortadaIngles());
                FileInputStream portadaInglesInputStream = new FileInputStream(portadaInglesFile);
                byte[] portadaInglesByte = portadaInglesInputStream.readAllBytes();
                String portadaInglesEncode = Base64.getEncoder().encodeToString(portadaInglesByte);
                peliculaDto.setPortadaIngles(portadaInglesEncode);
                portadaInglesInputStream.close();

                File portadaEspanolFile = new File("..\\Portadas\\" + peliculaDto.getPortadaEspannol());
                FileInputStream portadaEspanolInputStream = new FileInputStream(portadaEspanolFile);
                byte[] portadaEspanolByte = portadaEspanolInputStream.readAllBytes();
                String portadaEspanolEncoded = Base64.getEncoder().encodeToString(portadaEspanolByte);
                peliculaDto.setPortadaEspannol(portadaEspanolEncoded);
                portadaEspanolInputStream.close();
                peliculasDto.add(new PeliculaDto(pelicula));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Peliculas", peliculasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen peliculas con los criterios ingresados.", "getPeliculas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la pelicula.", "getPelicula " + ex.getMessage());
        }
    }

    public Respuesta getAllPeliculas() { //String fechaEstreno
        try {
            Query qryPelicula = em.createNamedQuery("Pelicula.findAll", Pelicula.class);

            List<Pelicula> peliculas = qryPelicula.getResultList();
            List<PeliculaDto> peliculasDto = new ArrayList<>();
            for (Pelicula pelicula : peliculas) {
                peliculasDto.add(new PeliculaDto(pelicula));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Peliculas", peliculasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen peliculas con los criterios ingresados.", "getPeliculas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la pelicula.", "getPelicula " + ex.getMessage());
        }

    }
    
    public Respuesta getPeliculasPorEstrenarse(String estado) {
        try {
            Query qryPelicula = em.createNamedQuery("Pelicula.findByPelEstadoPorEstrenarse", Pelicula.class);
            qryPelicula.setParameter("estado", estado);

            List<Pelicula> peliculas = qryPelicula.getResultList();
            List<PeliculaDto> peliculasDto = new ArrayList<>();
            for (Pelicula pelicula : peliculas) {

                PeliculaDto peliculaDto = new PeliculaDto(pelicula);
                File portadaInglesFile = new File("..\\Portadas\\" + peliculaDto.getPortadaIngles());
                FileInputStream portadaInglesInputStream = new FileInputStream(portadaInglesFile);
                byte[] portadaInglesByte = portadaInglesInputStream.readAllBytes();
                String portadaInglesEncode = Base64.getEncoder().encodeToString(portadaInglesByte);
                peliculaDto.setPortadaIngles(portadaInglesEncode);
                portadaInglesInputStream.close();

                File portadaEspanolFile = new File("..\\Portadas\\" + peliculaDto.getPortadaEspannol());
                FileInputStream portadaEspanolInputStream = new FileInputStream(portadaEspanolFile);
                byte[] portadaEspanolByte = portadaEspanolInputStream.readAllBytes();
                String portadaEspanolEncoded = Base64.getEncoder().encodeToString(portadaEspanolByte);
                peliculaDto.setPortadaEspannol(portadaEspanolEncoded);
                portadaEspanolInputStream.close();

                peliculasDto.add(peliculaDto);

            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Peliculas", peliculasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen peliculas con los criterios ingresados.", "getPeliculas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la pelicula.", "getPelicula " + ex.getMessage());
        }

    }

    public Respuesta guardarPelicula(PeliculaDto peliculaDto) {
        try {
            Pelicula pelicula;
            if (peliculaDto.getId() != null && peliculaDto.getId() > 0) {
                pelicula = em.find(Pelicula.class, peliculaDto.getId());
                if (pelicula == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el pelicula a modificar.", "guardarPelicula NoResultException");
                }
                pelicula.actualizarPelicula(peliculaDto);
                for (TandaDto emp : peliculaDto.getTandasEliminadas()) {
                    pelicula.getTandaList().remove(new Tanda(emp.getId()));
                }
                if (!peliculaDto.getTandas().isEmpty()) {
                    for (TandaDto tan : peliculaDto.getTandas()) {
                        Tanda tanda = em.find(Tanda.class, tan.getId());
                        tanda.setTanIdpel(pelicula);
                        pelicula.getTandaList().add(tanda);
                    }
                }
                pelicula = em.merge(pelicula);
            } else {
                pelicula = new Pelicula(peliculaDto);
                em.persist(pelicula);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Pelicula", new PeliculaDto(pelicula));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el pelicula.", "guardarPelicula " + ex.getMessage());
        }
    }

    public Respuesta eliminarPelicula(Long id) {
        try {
            Pelicula pelicula;
            if (id != null && id > 0) {
                pelicula = em.find(Pelicula.class, id);
                if (pelicula == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el pelicula a eliminar.", "eliminarPelicula NoResultException");
                }
                em.remove(pelicula);
                File portadaEspañol = new File("..\\Portadas\\" + pelicula.getPelPortadaespannol());
                portadaEspañol.delete();
                File portadaIngles = new File("..\\Portadas\\" + pelicula.getPelPortadaingles());
                portadaIngles.delete();
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el pelicula a eliminar.", "eliminarPelicula NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el pelicula porque tiene relaciones con otros registros.", "eliminarPelicula " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el pelicula.", "eliminarPelicula " + ex.getMessage());
        }
    }
}
