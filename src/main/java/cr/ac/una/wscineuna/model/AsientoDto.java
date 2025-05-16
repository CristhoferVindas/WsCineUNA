/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristhofer
 */
public class AsientoDto {
    
    private Long id;
    private String fila;
    private Integer numero;
    private boolean modificado;
    
    private Long IDsala; 
    private Sala idSala;
    
    List<Asientoxtanda> asientosxTandas;
    List<Asientoxtanda> asientosxTandasEliminadas;

    public AsientoDto() {
        this.modificado = false;
        asientosxTandas = new ArrayList<>();
        asientosxTandasEliminadas = new ArrayList<>();
    }
     public AsientoDto(Asiento asiento) {
         this();
         this.id = asiento.getAsiId();
         this.fila = asiento.getAsiFila();
         this.numero = asiento.getAsiNumero();
         this.idSala = asiento.getAsiIdsal();
         this.IDsala = Long.valueOf(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    @Override
    public String toString() {
        return "AsientoDto{" + "id=" + id + ", fila=" + fila + ", numero=" + numero + ", idSala=" + idSala + '}';
    }

    public Long getIDsala() {
        return IDsala;
    }

    public void setIDsala(Long IDsala) {
        this.IDsala = IDsala;
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
}
