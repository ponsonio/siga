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
@Table(name = "CURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByIdCurso", query = "SELECT c FROM Curso c WHERE c.idCurso = :idCurso"),
    @NamedQuery(name = "Curso.findByNombre", query = "SELECT c FROM Curso c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Curso.findByVigente", query = "SELECT c FROM Curso c WHERE c.vigente = :vigente")})
public class Curso implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<PeriodoAcademicoSeccionCurso> periodoAcademicoSeccionCursoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CURSO")
    private Integer idCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENTE")
    private Character vigente;
    @JoinTable(name = "CURSO_GRADO", joinColumns = {
        @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_GRADO", referencedColumnName = "ID_GRADO")})
    @ManyToMany
    private Collection<Grado> gradoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<LibretaNotasSubperiodo> libretaNotasSubperiodoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<LibretaNotasFinalesCurso> libretaNotasFinalesCursoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<SubPeriodoAcademicoSeccionCurso> subPeriodoAcademicoSeccionCursoCollection;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio idColegio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<Asistencia> asistenciaCollection;

    public Curso() {
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Curso(Integer idCurso, String nombre, Character vigente) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.vigente = vigente;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getVigente() {
        return vigente;
    }

    public void setVigente(Character vigente) {
        this.vigente = vigente;
    }

    @XmlTransient
    public Collection<Grado> getGradoCollection() {
        return gradoCollection;
    }

    public void setGradoCollection(Collection<Grado> gradoCollection) {
        this.gradoCollection = gradoCollection;
    }

    @XmlTransient
    public Collection<LibretaNotasSubperiodo> getLibretaNotasSubperiodoCollection() {
        return libretaNotasSubperiodoCollection;
    }

    public void setLibretaNotasSubperiodoCollection(Collection<LibretaNotasSubperiodo> libretaNotasSubperiodoCollection) {
        this.libretaNotasSubperiodoCollection = libretaNotasSubperiodoCollection;
    }

    @XmlTransient
    public Collection<LibretaNotasFinalesCurso> getLibretaNotasFinalesCursoCollection() {
        return libretaNotasFinalesCursoCollection;
    }

    public void setLibretaNotasFinalesCursoCollection(Collection<LibretaNotasFinalesCurso> libretaNotasFinalesCursoCollection) {
        this.libretaNotasFinalesCursoCollection = libretaNotasFinalesCursoCollection;
    }

    @XmlTransient
    public Collection<SubPeriodoAcademicoSeccionCurso> getSubPeriodoAcademicoSeccionCursoCollection() {
        return subPeriodoAcademicoSeccionCursoCollection;
    }

    public void setSubPeriodoAcademicoSeccionCursoCollection(Collection<SubPeriodoAcademicoSeccionCurso> subPeriodoAcademicoSeccionCursoCollection) {
        this.subPeriodoAcademicoSeccionCursoCollection = subPeriodoAcademicoSeccionCursoCollection;
    }

    public Colegio getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Colegio idColegio) {
        this.idColegio = idColegio;
    }

    @XmlTransient
    public Collection<Asistencia> getAsistenciaCollection() {
        return asistenciaCollection;
    }

    public void setAsistenciaCollection(Collection<Asistencia> asistenciaCollection) {
        this.asistenciaCollection = asistenciaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Curso[ idCurso=" + idCurso + " ]";
    }

    @XmlTransient
    public Collection<PeriodoAcademicoSeccionCurso> getPeriodoAcademicoSeccionCursoCollection() {
        return periodoAcademicoSeccionCursoCollection;
    }

    public void setPeriodoAcademicoSeccionCursoCollection(Collection<PeriodoAcademicoSeccionCurso> periodoAcademicoSeccionCursoCollection) {
        this.periodoAcademicoSeccionCursoCollection = periodoAcademicoSeccionCursoCollection;
    }
    
}
