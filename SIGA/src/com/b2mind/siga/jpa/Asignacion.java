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
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
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
@Table(name = "ASIGNACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignacion.findAll", query = "SELECT a FROM Asignacion a"),
    @NamedQuery(name = "Asignacion.findByIdAsignacion", query = "SELECT a FROM Asignacion a WHERE a.idAsignacion = :idAsignacion"),
    @NamedQuery(name = "Asignacion.findByDescripcion", query = "SELECT a FROM Asignacion a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Asignacion.findByFechaEntrega", query = "SELECT a FROM Asignacion a WHERE a.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Asignacion.findByEtiqueta", query = "SELECT a FROM Asignacion a WHERE a.etiqueta = :etiqueta")})
public class Asignacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ASIGNACION")
    private Long idAsignacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32600)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ETIQUETA")
    private String etiqueta;
    @JoinTable(name = "MATERIAL_ASIGNACION", joinColumns = {
        @JoinColumn(name = "ID_ASIGNACION", referencedColumnName = "ID_ASIGNACION")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_MATERIAL", referencedColumnName = "ID_MATERIAL")})
    @ManyToMany
    private Collection<Materiales> materialesCollection;
    @JoinColumn(name = "ID_TIPO_ASIGNACION", referencedColumnName = "ID_TIPO_ASIGNACION")
    @ManyToOne(optional = false)
    private TipoAsignacion idTipoAsignacion;
    @JoinColumn(name = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO", referencedColumnName = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO")
    @ManyToOne(optional = false)
    private SubPeriodoAcademicoSeccionCurso idSubPeriodoAcademicoSeccionCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAsignacion")
    private Collection<AsignacionAlumno> asignacionAlumnoCollection;

    public Asignacion() {
    }

    public Asignacion(Long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Asignacion(Long idAsignacion, String descripcion, Date fechaEntrega, String etiqueta) {
        this.idAsignacion = idAsignacion;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.etiqueta = etiqueta;
    }

    public Long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @XmlTransient
    public Collection<Materiales> getMaterialesCollection() {
        return materialesCollection;
    }

    public void setMaterialesCollection(Collection<Materiales> materialesCollection) {
        this.materialesCollection = materialesCollection;
    }

    public TipoAsignacion getIdTipoAsignacion() {
        return idTipoAsignacion;
    }

    public void setIdTipoAsignacion(TipoAsignacion idTipoAsignacion) {
        this.idTipoAsignacion = idTipoAsignacion;
    }

    public SubPeriodoAcademicoSeccionCurso getIdSubPeriodoAcademicoSeccionCurso() {
        return idSubPeriodoAcademicoSeccionCurso;
    }

    public void setIdSubPeriodoAcademicoSeccionCurso(SubPeriodoAcademicoSeccionCurso idSubPeriodoAcademicoSeccionCurso) {
        this.idSubPeriodoAcademicoSeccionCurso = idSubPeriodoAcademicoSeccionCurso;
    }

    @XmlTransient
    public Collection<AsignacionAlumno> getAsignacionAlumnoCollection() {
        return asignacionAlumnoCollection;
    }

    public void setAsignacionAlumnoCollection(Collection<AsignacionAlumno> asignacionAlumnoCollection) {
        this.asignacionAlumnoCollection = asignacionAlumnoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacion != null ? idAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignacion)) {
            return false;
        }
        Asignacion other = (Asignacion) object;
        if ((this.idAsignacion == null && other.idAsignacion != null) || (this.idAsignacion != null && !this.idAsignacion.equals(other.idAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Asignacion[ idAsignacion=" + idAsignacion + " ]";
    }
    
}
