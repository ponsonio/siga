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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "PERIODO_ACADEMICO_SECCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoAcademicoSeccion.findAll", query = "SELECT p FROM PeriodoAcademicoSeccion p"),
    @NamedQuery(name = "PeriodoAcademicoSeccion.findByIdPeriodoAcademicoSeccion", query = "SELECT p FROM PeriodoAcademicoSeccion p WHERE p.idPeriodoAcademicoSeccion = :idPeriodoAcademicoSeccion"),
    @NamedQuery(name = "PeriodoAcademicoSeccion.findByIdDocenteTutor", query = "SELECT p FROM PeriodoAcademicoSeccion p WHERE p.idDocenteTutor = :idDocenteTutor")})
public class PeriodoAcademicoSeccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION")
    private Long idPeriodoAcademicoSeccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DOCENTE_TUTOR")
    private long idDocenteTutor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodoAcademicoSeccion")
    private Collection<MatriculaAlumno> matriculaAlumnoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodoAcademicoSeccion")
    private Collection<LibretaNotas> libretaNotasCollection;
    @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION")
    @ManyToOne(optional = false)
    private Seccion idSeccion;
    @JoinColumn(name = "ID_PERIODO_ACADEMICO", referencedColumnName = "ID_PERIODO_ACADEMICO")
    @ManyToOne(optional = false)
    private PeriodoAcademico idPeriodoAcademico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoAcademicoSeccion")
    private Collection<PeriodoAcademicoSeccionCurso> periodoAcademicoSeccionCursoCollection;

    public PeriodoAcademicoSeccion() {
    }

    public PeriodoAcademicoSeccion(Long idPeriodoAcademicoSeccion) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public PeriodoAcademicoSeccion(Long idPeriodoAcademicoSeccion, long idDocenteTutor) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
        this.idDocenteTutor = idDocenteTutor;
    }

    public Long getIdPeriodoAcademicoSeccion() {
        return idPeriodoAcademicoSeccion;
    }

    public void setIdPeriodoAcademicoSeccion(Long idPeriodoAcademicoSeccion) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public long getIdDocenteTutor() {
        return idDocenteTutor;
    }

    public void setIdDocenteTutor(long idDocenteTutor) {
        this.idDocenteTutor = idDocenteTutor;
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

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public PeriodoAcademico getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(PeriodoAcademico idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    @XmlTransient
    public Collection<PeriodoAcademicoSeccionCurso> getPeriodoAcademicoSeccionCursoCollection() {
        return periodoAcademicoSeccionCursoCollection;
    }

    public void setPeriodoAcademicoSeccionCursoCollection(Collection<PeriodoAcademicoSeccionCurso> periodoAcademicoSeccionCursoCollection) {
        this.periodoAcademicoSeccionCursoCollection = periodoAcademicoSeccionCursoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodoAcademicoSeccion != null ? idPeriodoAcademicoSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoAcademicoSeccion)) {
            return false;
        }
        PeriodoAcademicoSeccion other = (PeriodoAcademicoSeccion) object;
        if ((this.idPeriodoAcademicoSeccion == null && other.idPeriodoAcademicoSeccion != null) || (this.idPeriodoAcademicoSeccion != null && !this.idPeriodoAcademicoSeccion.equals(other.idPeriodoAcademicoSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.PeriodoAcademicoSeccion[ idPeriodoAcademicoSeccion=" + idPeriodoAcademicoSeccion + " ]";
    }
    
}
