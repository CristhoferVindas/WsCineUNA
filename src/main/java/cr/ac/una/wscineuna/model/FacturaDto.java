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
public class FacturaDto {
    private Long id;
    private LocalDate fecha;
    private String hora;
    private Integer precioTotal;
    private boolean modificado;
    @JsonbTransient
    private Usuario idUsuario;
    public Long IDUsuario;
    
    List<AsientoxtandaDto> asientosxTandas;
    List<AsientoxtandaDto> asientosxTandasEliminadas;
    
    List<AlimentoxfacturaDto> alimentoxFactura;
    List<AlimentoxfacturaDto> alimentoxFacturaEliminadas;

    public FacturaDto() {
        this.modificado = false;
        asientosxTandas = new ArrayList<>();
        asientosxTandasEliminadas = new ArrayList<>();
        alimentoxFactura = new ArrayList<>();
        alimentoxFacturaEliminadas = new ArrayList<>();
        
        
    }

    public FacturaDto(Factura factura) {
        this();
        this.id = factura.getFacId();
        this.fecha = factura.getFacFecha();
        this.hora = factura.getFacHora();
        this.precioTotal = factura.getFacPreciototal();
        this.idUsuario = factura.getFacIdusu();
        this.IDUsuario = Long.valueOf(0);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(Long IDUsuario) {
        this.IDUsuario = IDUsuario;
    }
    
    public List<AsientoxtandaDto> getAsientosxTandas() {
        return asientosxTandas;
    }

    public void setAsientosxTandas(List<AsientoxtandaDto> asientosxTandas) {
        this.asientosxTandas = asientosxTandas;
    }

    public List<AsientoxtandaDto> getAsientosxTandasEliminadas() {
        return asientosxTandasEliminadas;
    }

    public void setAsientosxTandasEliminadas(List<AsientoxtandaDto> asientosxTandasEliminadas) {
        this.asientosxTandasEliminadas = asientosxTandasEliminadas;
    }

    public List<AlimentoxfacturaDto> getAlimentoxFactura() {
        return alimentoxFactura;
    }

    public void setAlimentoxFactura(List<AlimentoxfacturaDto> alimentoxFactura) {
        this.alimentoxFactura = alimentoxFactura;
    }

    public List<AlimentoxfacturaDto> getAlimentoxFacturaEliminadas() {
        return alimentoxFacturaEliminadas;
    }

    public void setAlimentoxFacturaEliminadas(List<AlimentoxfacturaDto> alimentoxFacturaEliminadas) {
        this.alimentoxFacturaEliminadas = alimentoxFacturaEliminadas;
    } 
}
