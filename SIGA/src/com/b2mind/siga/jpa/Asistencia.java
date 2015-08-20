/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "ASISTENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByIdAsistencia", query = "SELECT a FROM Asistencia a WHERE a.idAsistencia = :idAsistencia"),
    @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Asistencia.findByAsistio", query = "SELECT a FROM Asistencia a WHERE a.asistio = :asistio"),
    @NamedQuery(name = "Asistencia.findByJustificada", query = "SELECT a FROM Asistencia a WHERE a.justificada = :justificada")})
public class Asistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ASISTENCIA")
    private Long idAsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASISTIO")
    private Character asistio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "JUSTIFICADA")
    private Character justificada;
    @JoinColumn(name = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO", referencedColumnName = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO")
    @ManyToOne(optional = false)
    private SubPeriodoAcademicoSeccionCurso idSubPeriodoAcademicoSeccionCurso;
    @JoinColumn(name = "ID_SUB_PERIODO_ACADEMICO", referencedColumnName = "ID_PERIODO_ACADEMICO")
    @ManyToOne(optional = false)
    private PeriodoAcademico idSubPeriodoAcademico;
    @JoinColumn(name = "ID_JUSTIFICACION", referencedColumnName = "ID_JUSTIFICACION")
    @ManyToOne(optional = false)
    private Justificacion idJustificacion;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false)
    private Curso idCurso;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Alumno idAlumno;

    public Asistencia() {
    }

    public Asistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Asistencia(Long idAsistencia, Date fecha, Character asistio, Character justificada) {
        this.idAsistencia = idAsistencia;
        this.fecha = fecha;
        this.asistio = asistio;
        this.justificada = justificada;
    }

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Character getAsistio() {
        return asistio;
    }

    public void setAsistio(Character asistio) {
        this.asistio = asistio;
    }

    public Character getJustificada() {
        return justificada;
    }

    public void setJustificada(Character justificada) {
        this.justificada = justificada;
    }

    public SubPeriodoAcademicoSeccionCurso getIdSubPeriodoAcademicoSeccionCurso() {
        return idSubPeriodoAcademicoSeccionCurso;
    }

    public void setIdSubPeriodoAcademicoSeccionCurso(SubPeriodoAcademicoSeccionCurso idSubPeriodoAcademicoSeccionCurso) {
        this.idSubPeriodoAcademicoSeccionCurso = idSubPeriodoAcademicoSeccionCurso;
    }

    public PeriodoAcademico getIdSubPeriodoAcademico() {
        return idSubPeriodoAcademico;
    }

    public void setIdSubPeriodoAcademico(PeriodoAcademico idSubPeriodoAcademico) {
        this.idSubPeriodoAcademico = idSubPeriodoAcademico;
    }

    public Justificacion getIdJustificacion() {
        return idJustificacion;
    }

    public void setIdJustificacion(Justificacion idJustificacion) {
        this.idJustificacion = idJustificacion;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Asistencia[ idAsistencia=" + idAsistencia + " ]";
    }
    
}
