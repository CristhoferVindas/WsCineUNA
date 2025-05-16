/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

/**
 *
 * @author Cristhofer
 */
public class AlimentoDto {
    
    private Long id;
    private String nombre;
    private Integer precio;
    private String tipo;
    private boolean modificado;
    
    
    public AlimentoDto(){
        this.modificado = false;
    }

    public AlimentoDto(Alimento alimento) {
        this();
        this.id = alimento.getAliId();
        this.nombre = alimento.getAliNombre();
        this.precio = alimento.getAliPrecio();
        this.tipo = alimento.getAliTipo();
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }
}
