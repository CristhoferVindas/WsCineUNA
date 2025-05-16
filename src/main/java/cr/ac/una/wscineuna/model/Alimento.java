/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cristhofer
 */
@Entity
@Table(name = "CINEUNA_ALIMENTO", schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "Alimento.findAll", query = "SELECT a FROM Alimento a"),
    @NamedQuery(name = "Alimento.findByAliId", query = "SELECT a FROM Alimento a WHERE a.aliId = :aliId"),
    @NamedQuery(name = "Alimento.findByAliNombre", query = "SELECT a FROM Alimento a WHERE a.aliNombre = :aliNombre"),
    @NamedQuery(name = "Alimento.findByAliPrecio", query = "SELECT a FROM Alimento a WHERE a.aliPrecio = :aliPrecio"),
    @NamedQuery(name = "Alimento.findByAliTipo", query = "SELECT a FROM Alimento a WHERE a.aliTipo = :aliTipo")})
public class Alimento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CINEUNA_ALIMENTO_ALI_ID_GENERATOR", sequenceName = "CINEUNA.CINEUNA_ALIMENTO_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CINEUNA_ALIMENTO_ALI_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ALI_ID")
    private Long aliId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ALI_NOMBRE")
    private String aliNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALI_PRECIO")
    private Integer aliPrecio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ALI_TIPO")
    private String aliTipo;
    @OneToMany(mappedBy = "axfIdali", fetch = FetchType.LAZY)
    private List<Alimentoxfactura> alimentoxfacturaList;

    public Alimento() {
    }

    public Alimento(Long aliId) {
        this.aliId = aliId;
    }

    public Alimento(Long aliId, String aliNombre, Integer aliPrecio, String aliTipo) {
        this.aliId = aliId;
        this.aliNombre = aliNombre;
        this.aliPrecio = aliPrecio;
        this.aliTipo = aliTipo;
    }

    public Alimento(AlimentoDto alimentoDto){
            this.aliId = alimentoDto.getId();
        actualizarAlimento(alimentoDto);
        
    } 
    public void actualizarAlimento(AlimentoDto alimentoDto){
        
        this.aliNombre = alimentoDto.getNombre();
        this.aliPrecio = alimentoDto.getPrecio();
        this.aliTipo = alimentoDto.getTipo();        
        
    }
            
    public Long getAliId() {
        return aliId;
    }

    public void setAliId(Long aliId) {
        this.aliId = aliId;
    }

    public String getAliNombre() {
        return aliNombre;
    }

    public void setAliNombre(String aliNombre) {
        this.aliNombre = aliNombre;
    }

    public Integer getAliPrecio() {
        return aliPrecio;
    }

    public void setAliPrecio(Integer aliPrecio) {
        this.aliPrecio = aliPrecio;
    }

    public String getAliTipo() {
        return aliTipo;
    }

    public void setAliTipo(String aliTipo) {
        this.aliTipo = aliTipo;
    }

    public List<Alimentoxfactura> getAlimentoxfacturaList() {
        return alimentoxfacturaList;
    }

    public void setAlimentoxfacturaList(List<Alimentoxfactura> alimentoxfacturaList) {
        this.alimentoxfacturaList = alimentoxfacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aliId != null ? aliId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alimento)) {
            return false;
        }
        Alimento other = (Alimento) object;
        if ((this.aliId == null && other.aliId != null) || (this.aliId != null && !this.aliId.equals(other.aliId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.Alimento[ aliId=" + aliId + " ]";
    }
    
}
