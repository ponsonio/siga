/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "PERIODO_ACADEMICO_SECCION_CURSO_NOTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoAcademicoSeccionCursoNota.findAll", query = "SELECT p FROM PeriodoAcademicoSeccionCursoNota p"),
    @NamedQuery(name = "PeriodoAcademicoSeccionCursoNota.findByIdPersona", query = "SELECT p FROM PeriodoAcademicoSeccionCursoNota p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "PeriodoAcademicoSeccionCursoNota.findByIdPeriodoAcademicoSeccionCurso", query = "SELECT p FROM PeriodoAcademicoSeccionCursoNota p WHERE p.idPeriodoAcademicoSeccionCurso = :idPeriodoAcademicoSeccionCurso"),
    @NamedQuery(name = "PeriodoAcademicoSeccionCursoNota.findByNotaFinalCurso", query = "SELECT p FROM PeriodoAcademicoSeccionCursoNota p WHERE p.notaFinalCurso = :notaFinalCurso"),
    @NamedQuery(name = "PeriodoAcademicoSeccionCursoNota.findByIdPeriodoAcademicoSeccion", query = "SELECT p FROM PeriodoAcademicoSeccionCursoNota p WHERE p.idPeriodoAcademicoSeccion = :idPeriodoAcademicoSeccion")})
public class PeriodoAcademicoSeccionCursoNota implements Serializable {
    @JoinColumns( {
	//@PrimaryKeyJoinColumn({        
		@JoinColumn(insertable=false, updatable=false ,name = "ID_PERIODO_ACADEMICO_SECCION", referencedColumnName = "ID_PERIODO_ACADEMICO_SECCION"),
        @JoinColumn(insertable=false, updatable=false ,name = "ID_PERIODO_ACADEMICO_SECCION_CURSO", referencedColumnName = "ID_PERIODO_ACADEMICO_SECCION_CURSO")})
    @ManyToOne(optional = false)
    private PeriodoAcademicoSeccionCurso periodoAcademicoSeccionCurso;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA")
    private Long idPersona;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION_CURSO")
    private long idPeriodoAcademicoSeccionCurso;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTA_FINAL_CURSO")
    private BigDecimal notaFinalCurso;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION")
    private long idPeriodoAcademicoSeccion;
    
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Alumno alumno;

    public PeriodoAcademicoSeccionCursoNota() {
    }

    public PeriodoAcademicoSeccionCursoNota(Long idPersona) {
        this.idPersona = idPersona;
    }

    public PeriodoAcademicoSeccionCursoNota(Long idPersona, long idPeriodoAcademicoSeccionCurso, BigDecimal notaFinalCurso, long idPeriodoAcademicoSeccion) {
        this.idPersona = idPersona;
        this.idPeriodoAcademicoSeccionCurso = idPeriodoAcademicoSeccionCurso;
        this.notaFinalCurso = notaFinalCurso;
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public long getIdPeriodoAcademicoSeccionCurso() {
        return idPeriodoAcademicoSeccionCurso;
    }

    public void setIdPeriodoAcademicoSeccionCurso(long idPeriodoAcademicoSeccionCurso) {
        this.idPeriodoAcademicoSeccionCurso = idPeriodoAcademicoSeccionCurso;
    }

    public BigDecimal getNotaFinalCurso() {
        return notaFinalCurso;
    }

    public void setNotaFinalCurso(BigDecimal notaFinalCurso) {
        this.notaFinalCurso = notaFinalCurso;
    }

    public long getIdPeriodoAcademicoSeccion() {
        return idPeriodoAcademicoSeccion;
    }

    public void setIdPeriodoAcademicoSeccion(long idPeriodoAcademicoSeccion) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
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
        if (!(object instanceof PeriodoAcademicoSeccionCursoNota)) {
            return false;
        }
        PeriodoAcademicoSeccionCursoNota other = (PeriodoAcademicoSeccionCursoNota) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.PeriodoAcademicoSeccionCursoNota[ idPersona=" + idPersona + " ]";
    }

    public PeriodoAcademicoSeccionCurso getPeriodoAcademicoSeccionCurso() {
        return periodoAcademicoSeccionCurso;
    }

    public void setPeriodoAcademicoSeccionCurso(PeriodoAcademicoSeccionCurso periodoAcademicoSeccionCurso) {
        this.periodoAcademicoSeccionCurso = periodoAcademicoSeccionCurso;
    }
    
}
