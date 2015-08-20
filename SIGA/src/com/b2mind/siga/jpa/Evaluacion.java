/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e"),
    @NamedQuery(name = "Evaluacion.findByIdEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.idEvaluacion = :idEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByFechaProgramada", query = "SELECT e FROM Evaluacion e WHERE e.fechaProgramada = :fechaProgramada"),
    @NamedQuery(name = "Evaluacion.findByEtiqueta", query = "SELECT e FROM Evaluacion e WHERE e.etiqueta = :etiqueta"),
    @NamedQuery(name = "Evaluacion.findByFechaEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.fechaEvaluacion = :fechaEvaluacion")})
public class Evaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EVALUACION")
    private Long idEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PROGRAMADA")
    @Temporal(TemporalType.DATE)
    private Date fechaProgramada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ETIQUETA")
    private String etiqueta;
    @Column(name = "FECHA_EVALUACION")
    @Temporal(TemporalType.DATE)
    private Date fechaEvaluacion;
    @JoinTable(name = "MATERIAL_EVALUACION", joinColumns = {
        @JoinColumn(name = "ID_EVALUACION", referencedColumnName = "ID_EVALUACION")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_MATERIAL", referencedColumnName = "ID_MATERIAL")})
    @ManyToMany
    private Collection<Materiales> materialesCollection;
    @JoinColumn(name = "ID_TIPO_EVALUACION", referencedColumnName = "ID_TIPO_EVALUACION")
    @ManyToOne(optional = false)
    private TipoEvaluacion idTipoEvaluacion;
    @JoinColumn(name = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO", referencedColumnName = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO")
    @ManyToOne(optional = false)
    private SubPeriodoAcademicoSeccionCurso idSubPeriodoAcademicoSeccionCurso;

    public Evaluacion() {
    }

    public Evaluacion(Long idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Evaluacion(Long idEvaluacion, Date fechaProgramada, String etiqueta) {
        this.idEvaluacion = idEvaluacion;
        this.fechaProgramada = fechaProgramada;
        this.etiqueta = etiqueta;
    }

    public Long getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Long idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    @XmlTransient
    public Collection<Materiales> getMaterialesCollection() {
        return materialesCollection;
    }

    public void setMaterialesCollection(Collection<Materiales> materialesCollection) {
        this.materialesCollection = materialesCollection;
    }

    public TipoEvaluacion getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    public void setIdTipoEvaluacion(TipoEvaluacion idTipoEvaluacion) {
        this.idTipoEvaluacion = idTipoEvaluacion;
    }

    public SubPeriodoAcademicoSeccionCurso getIdSubPeriodoAcademicoSeccionCurso() {
        return idSubPeriodoAcademicoSeccionCurso;
    }

    public void setIdSubPeriodoAcademicoSeccionCurso(SubPeriodoAcademicoSeccionCurso idSubPeriodoAcademicoSeccionCurso) {
        this.idSubPeriodoAcademicoSeccionCurso = idSubPeriodoAcademicoSeccionCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Evaluacion[ idEvaluacion=" + idEvaluacion + " ]";
    }
    
}
