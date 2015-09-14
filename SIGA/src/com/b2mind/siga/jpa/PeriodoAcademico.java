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
@Table(name = "PERIODO_ACADEMICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoAcademico.findAll", query = "SELECT p FROM PeriodoAcademico p"),
    @NamedQuery(name = "PeriodoAcademico.findByIdPeriodoAcademico", query = "SELECT p FROM PeriodoAcademico p WHERE p.idPeriodoAcademico = :idPeriodoAcademico"),
    @NamedQuery(name = "PeriodoAcademico.findByEnCurso", query = "SELECT p FROM PeriodoAcademico p WHERE p.enCurso = :enCurso"),
    @NamedQuery(name = "PeriodoAcademico.findByEtiqueta", query = "SELECT p FROM PeriodoAcademico p WHERE p.etiqueta = :etiqueta"),
    @NamedQuery(name = "PeriodoAcademico.findByFechaInicio", query = "SELECT p FROM PeriodoAcademico p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "PeriodoAcademico.findByFechaFin", query = "SELECT p FROM PeriodoAcademico p WHERE p.fechaFin = :fechaFin")})
public class PeriodoAcademico implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodoAcademico")
    private Collection<PeriodoAcademicoSeccion> periodoAcademicoSeccionCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERIODO_ACADEMICO")
    private Long idPeriodoAcademico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EN_CURSO")
    private Character enCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ETIQUETA")
    private String etiqueta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idParent")
    private Collection<PeriodoAcademico> periodoAcademicoCollection;
    @JoinColumn(name = "ID_PARENT", referencedColumnName = "ID_PERIODO_ACADEMICO")
    @ManyToOne(optional = false)
    private PeriodoAcademico idParent;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio idColegio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubPeriodo")
    private Collection<LibretaNotasSubperiodo> libretaNotasSubperiodoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodoAcademico")
    private Collection<LibretaNotas> libretaNotasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodoAcademico")
    private Collection<ReciboPension> reciboPensionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoAcademico")
    private Collection<ResumenAlumno> resumenAlumnoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubPeriodo")
    private Collection<AsignacionAlumno> asignacionAlumnoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubPeriodoAcademico")
    private Collection<SubPeriodoAcademicoSeccionCurso> subPeriodoAcademicoSeccionCursoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubPeriodoAcademico")
    private Collection<Asistencia> asistenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubPeriodoAcademico")
    private Collection<Aviso> avisoCollection;

    public PeriodoAcademico() {
    }

    public PeriodoAcademico(Long idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public PeriodoAcademico(Long idPeriodoAcademico, Character enCurso, String etiqueta, Date fechaInicio, Date fechaFin) {
        this.idPeriodoAcademico = idPeriodoAcademico;
        this.enCurso = enCurso;
        this.etiqueta = etiqueta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(Long idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public Character getEnCurso() {
        return enCurso;
    }

    public void setEnCurso(Character enCurso) {
        this.enCurso = enCurso;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public Collection<PeriodoAcademico> getPeriodoAcademicoCollection() {
        return periodoAcademicoCollection;
    }

    public void setPeriodoAcademicoCollection(Collection<PeriodoAcademico> periodoAcademicoCollection) {
        this.periodoAcademicoCollection = periodoAcademicoCollection;
    }

    public PeriodoAcademico getIdParent() {
        return idParent;
    }

    public void setIdParent(PeriodoAcademico idParent) {
        this.idParent = idParent;
    }

    public Colegio getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Colegio idColegio) {
        this.idColegio = idColegio;
    }

    @XmlTransient
    public Collection<LibretaNotasSubperiodo> getLibretaNotasSubperiodoCollection() {
        return libretaNotasSubperiodoCollection;
    }

    public void setLibretaNotasSubperiodoCollection(Collection<LibretaNotasSubperiodo> libretaNotasSubperiodoCollection) {
        this.libretaNotasSubperiodoCollection = libretaNotasSubperiodoCollection;
    }

    @XmlTransient
    public Collection<LibretaNotas> getLibretaNotasCollection() {
        return libretaNotasCollection;
    }

    public void setLibretaNotasCollection(Collection<LibretaNotas> libretaNotasCollection) {
        this.libretaNotasCollection = libretaNotasCollection;
    }

    @XmlTransient
    public Collection<ReciboPension> getReciboPensionCollection() {
        return reciboPensionCollection;
    }

    public void setReciboPensionCollection(Collection<ReciboPension> reciboPensionCollection) {
        this.reciboPensionCollection = reciboPensionCollection;
    }

    @XmlTransient
    public Collection<ResumenAlumno> getResumenAlumnoCollection() {
        return resumenAlumnoCollection;
    }

    public void setResumenAlumnoCollection(Collection<ResumenAlumno> resumenAlumnoCollection) {
        this.resumenAlumnoCollection = resumenAlumnoCollection;
    }

    @XmlTransient
    public Collection<AsignacionAlumno> getAsignacionAlumnoCollection() {
        return asignacionAlumnoCollection;
    }

    public void setAsignacionAlumnoCollection(Collection<AsignacionAlumno> asignacionAlumnoCollection) {
        this.asignacionAlumnoCollection = asignacionAlumnoCollection;
    }

    @XmlTransient
    public Collection<SubPeriodoAcademicoSeccionCurso> getSubPeriodoAcademicoSeccionCursoCollection() {
        return subPeriodoAcademicoSeccionCursoCollection;
    }

    public void setSubPeriodoAcademicoSeccionCursoCollection(Collection<SubPeriodoAcademicoSeccionCurso> subPeriodoAcademicoSeccionCursoCollection) {
        this.subPeriodoAcademicoSeccionCursoCollection = subPeriodoAcademicoSeccionCursoCollection;
    }

    @XmlTransient
    public Collection<Asistencia> getAsistenciaCollection() {
        return asistenciaCollection;
    }

    public void setAsistenciaCollection(Collection<Asistencia> asistenciaCollection) {
        this.asistenciaCollection = asistenciaCollection;
    }

    @XmlTransient
    public Collection<Aviso> getAvisoCollection() {
        return avisoCollection;
    }

    public void setAvisoCollection(Collection<Aviso> avisoCollection) {
        this.avisoCollection = avisoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodoAcademico != null ? idPeriodoAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoAcademico)) {
            return false;
        }
        PeriodoAcademico other = (PeriodoAcademico) object;
        if ((this.idPeriodoAcademico == null && other.idPeriodoAcademico != null) || (this.idPeriodoAcademico != null && !this.idPeriodoAcademico.equals(other.idPeriodoAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.PeriodoAcademico[ idPeriodoAcademico=" + idPeriodoAcademico + " ]";
    }

    @XmlTransient
    public Collection<PeriodoAcademicoSeccion> getPeriodoAcademicoSeccionCollection() {
        return periodoAcademicoSeccionCollection;
    }

    public void setPeriodoAcademicoSeccionCollection(Collection<PeriodoAcademicoSeccion> periodoAcademicoSeccionCollection) {
        this.periodoAcademicoSeccionCollection = periodoAcademicoSeccionCollection;
    }
    
}
