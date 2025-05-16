/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

/**
 *
 * @author Cristhofer
 */
public class TandaDto {
    private Long id;
    private Integer costoEntrada;
    private String horaInicio;
    private String horaFin;      
    private LocalDate fecha; 
    private String idioma;      
    private boolean modificado;
    
    private Long IDPelicula;
    private Long IDSala;
   
    @JsonbTransient
    private Pelicula idPelicula;
    @JsonbTransient
    private Sala idSala;

    List<Asientoxtanda> asientosxTandas;
    List<Asientoxtanda> asientosxTandasEliminadas;

    public TandaDto() {
        
        this.modificado = false;
        asientosxTandas = new ArrayList<>();
        asientosxTandasEliminadas = new ArrayList<>();
    
    }
    
    public TandaDto(Tanda tanda) {
        this();
        this.id = tanda.getTanId();
        this.costoEntrada = tanda.getTanCostoentrada();
        this.horaInicio = tanda.getTanHorainicio();
        this.horaFin = tanda.getTanHorafin();
        this.fecha = tanda.getTanFecha();
        this.idioma = tanda.getTanIdioma();
        this.idPelicula = tanda.getTanIdpel();
        this.idSala = tanda.getTanIdsal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCostoEntrada() {
        return costoEntrada;
    }

    public void setCostoEntrada(Integer costoEntrada) {
        this.costoEntrada = costoEntrada;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fechaInicio) {
        this.fecha = fechaInicio;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public Long getIDPelicula() {
        return IDPelicula;
    }

    public void setIDPelicula(Long IDPelicula) {
        this.IDPelicula = IDPelicula;
    }

    public Long getIDSala() {
        return IDSala;
    }

    public void setIDSala(Long IDSala) {
        this.IDSala = IDSala;
    }

    public List<Asientoxtanda> getAsientosxTandas() {
        return asientosxTandas;
    }

    public void setAsientosxTandas(List<Asientoxtanda> asientosxTandas) {
        this.asientosxTandas = asientosxTandas;
    }

    public List<Asientoxtanda> getAsientosxTandasEliminadas() {
        return asientosxTandasEliminadas;
    }

    public void setAsientosxTandasEliminadas(List<Asientoxtanda> asientosxTandasEliminadas) {
        this.asientosxTandasEliminadas = asientosxTandasEliminadas;
    }

  
    
    @Override
    public String toString() {
        return "TandaDto{" + "id=" + id + ", costoEntrada=" + costoEntrada + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", fechaInicio=" + fecha + ", idioma=" + idioma + ", modificado=" + modificado + ", idPelicula=" + idPelicula + ", idSala=" + idSala + '}';
    }
    
    
            
}
