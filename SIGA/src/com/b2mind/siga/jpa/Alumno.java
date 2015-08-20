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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByIdPersona", query = "SELECT a FROM Alumno a WHERE a.idPersona = :idPersona")})
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA")
    private Long idPersona;
    @JoinTable(name = "ALUMNO_APODERADO", joinColumns = {
        @JoinColumn(name = "ID_PERSONA_ALUMNO", referencedColumnName = "ID_PERSONA")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PERSONA_APODERADO", referencedColumnName = "ID_PERSONA")})
    @ManyToMany
    private Collection<Apoderado> apoderadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumno")
    private Collection<MatriculaAlumno> matriculaAlumnoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumno")
    private Collection<LibretaNotas> libretaNotasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumno")
    private Collection<ReciboPension> reciboPensionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private Collection<ResumenAlumno> resumenAlumnoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumno")
    private Collection<AsignacionAlumno> asignacionAlumnoCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "alumno")
    private PeriodoAcademicoSeccionCursoNota periodoAcademicoSeccionCursoNota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumno")
    private Collection<Asistencia> asistenciaCollection;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio idColegio;

    public Alumno() {
    }

    public Alumno(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public Collection<Apoderado> getApoderadoCollection() {
        return apoderadoCollection;
    }

    public void setApoderadoCollection(Collection<Apoderado> apoderadoCollection) {
        this.apoderadoCollection = apoderadoCollection;
    }

    @XmlTransient
    public Collection<MatriculaAlumno> getMatriculaAlumnoCollection() {
        return matriculaAlumnoCollection;
    }

    public void setMatriculaAlumnoCollection(Collection<MatriculaAlumno> matriculaAlumnoCollection) {
        this.matriculaAlumnoCollection = matriculaAlumnoCollection;
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

    public PeriodoAcademicoSeccionCursoNota getPeriodoAcademicoSeccionCursoNota() {
        return periodoAcademicoSeccionCursoNota;
    }

    public void setPeriodoAcademicoSeccionCursoNota(PeriodoAcademicoSeccionCursoNota periodoAcademicoSeccionCursoNota) {
        this.periodoAcademicoSeccionCursoNota = periodoAcademicoSeccionCursoNota;
    }

    @XmlTransient
    public Collection<Asistencia> getAsistenciaCollection() {
        return asistenciaCollection;
    }

    public void setAsistenciaCollection(Collection<Asistencia> asistenciaCollection) {
        this.asistenciaCollection = asistenciaCollection;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Colegio getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Colegio idColegio) {
        this.idColegio = idColegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
	public String toString() {
		return "Alumno [idPersona=" + idPersona + ", apoderadoCollection=" + apoderadoCollection
				+ ", matriculaAlumnoCollection=" + matriculaAlumnoCollection + ", libretaNotasCollection="
				+ libretaNotasCollection + ", reciboPensionCollection=" + reciboPensionCollection
				+ ", resumenAlumnoCollection=" + resumenAlumnoCollection + ", asignacionAlumnoCollection="
				+ asignacionAlumnoCollection + ", periodoAcademicoSeccionCursoNota=" + periodoAcademicoSeccionCursoNota
				+ ", asistenciaCollection=" + asistenciaCollection + ", persona=" + persona + ", idColegio=" + idColegio
				+ "]";
	}
    
    
    
}
