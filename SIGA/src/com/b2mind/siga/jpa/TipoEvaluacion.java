/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "TIPO_EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEvaluacion.findAll", query = "SELECT t FROM TipoEvaluacion t"),
    @NamedQuery(name = "TipoEvaluacion.findByIdTipoEvaluacion", query = "SELECT t FROM TipoEvaluacion t WHERE t.idTipoEvaluacion = :idTipoEvaluacion"),
    @NamedQuery(name = "TipoEvaluacion.findByNombre", query = "SELECT t FROM TipoEvaluacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoEvaluacion.findByDescripcion", query = "SELECT t FROM TipoEvaluacion t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoEvaluacion.findByCodigo", query = "SELECT t FROM TipoEvaluacion t WHERE t.codigo = :codigo")})
public class TipoEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_EVALUACION")
    private Integer idTipoEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32600)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32600)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODIGO")
    private String codigo;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio idColegio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEvaluacion")
    private Collection<Evaluacion> evaluacionCollection;

    public TipoEvaluacion() {
    }

    public TipoEvaluacion(Integer idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public TipoEvaluacion(Integer idTipoEvaluacion, String nombre, String descripcion, String codigo) {
        this.idTipoEvaluacion = idTipoEvaluacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    public Integer getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    public void setIdTipoEvaluacion(Integer idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Colegio getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Colegio idColegio) {
        this.idColegio = idColegio;
    }

    @XmlTransient
    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEvaluacion != null ? idTipoEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEvaluacion)) {
            return false;
        }
        TipoEvaluacion other = (TipoEvaluacion) object;
        if ((this.idTipoEvaluacion == null && other.idTipoEvaluacion != null) || (this.idTipoEvaluacion != null && !this.idTipoEvaluacion.equals(other.idTipoEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.TipoEvaluacion[ idTipoEvaluacion=" + idTipoEvaluacion + " ]";
    }
    
}
