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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "MATERIALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materiales.findAll", query = "SELECT m FROM Materiales m"),
    @NamedQuery(name = "Materiales.findByIdMaterial", query = "SELECT m FROM Materiales m WHERE m.idMaterial = :idMaterial"),
    @NamedQuery(name = "Materiales.findByNombreArchivo", query = "SELECT m FROM Materiales m WHERE m.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "Materiales.findByDescripcion", query = "SELECT m FROM Materiales m WHERE m.descripcion = :descripcion")})
public class Materiales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MATERIAL")
    private Long idMaterial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @ManyToMany(mappedBy = "materialesCollection")
    private Collection<Asignacion> asignacionCollection;
    @ManyToMany(mappedBy = "materialesCollection")
    private Collection<Evaluacion> evaluacionCollection;
    @JoinTable(name = "MATERIAL_SECCION_CURSO_SUB_PERIODO", joinColumns = {
        @JoinColumn(name = "ID_MATERIAL", referencedColumnName = "ID_MATERIAL")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO", referencedColumnName = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO")})
    @ManyToMany
    private Collection<SubPeriodoAcademicoSeccionCurso> subPeriodoAcademicoSeccionCursoCollection;
    @JoinColumn(name = "ID_REPOSITORIO", referencedColumnName = "ID_REPOSITORIO")
    @ManyToOne(optional = false)
    private RepositorioArchivos idRepositorio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materiales")
    private Collection<MaterialSeccionCursoPeriodo> materialSeccionCursoPeriodoCollection;

    public Materiales() {
    }

    public Materiales(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Materiales(Long idMaterial, String nombreArchivo, String descripcion) {
        this.idMaterial = idMaterial;
        this.nombreArchivo = nombreArchivo;
        this.descripcion = descripcion;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Asignacion> getAsignacionCollection() {
        return asignacionCollection;
    }

    public void setAsignacionCollection(Collection<Asignacion> asignacionCollection) {
        this.asignacionCollection = asignacionCollection;
    }

    @XmlTransient
    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }

    @XmlTransient
    public Collection<SubPeriodoAcademicoSeccionCurso> getSubPeriodoAcademicoSeccionCursoCollection() {
        return subPeriodoAcademicoSeccionCursoCollection;
    }

    public void setSubPeriodoAcademicoSeccionCursoCollection(Collection<SubPeriodoAcademicoSeccionCurso> subPeriodoAcademicoSeccionCursoCollection) {
        this.subPeriodoAcademicoSeccionCursoCollection = subPeriodoAcademicoSeccionCursoCollection;
    }

    public RepositorioArchivos getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(RepositorioArchivos idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    @XmlTransient
    public Collection<MaterialSeccionCursoPeriodo> getMaterialSeccionCursoPeriodoCollection() {
        return materialSeccionCursoPeriodoCollection;
    }

    public void setMaterialSeccionCursoPeriodoCollection(Collection<MaterialSeccionCursoPeriodo> materialSeccionCursoPeriodoCollection) {
        this.materialSeccionCursoPeriodoCollection = materialSeccionCursoPeriodoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materiales)) {
            return false;
        }
        Materiales other = (Materiales) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Materiales[ idMaterial=" + idMaterial + " ]";
    }
    
}
