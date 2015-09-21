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
import javax.persistence.JoinColumns;
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
@Table(name = "SUB_PERIODO_ACADEMICO_SECCION_CURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubPeriodoAcademicoSeccionCurso.findAll", query = "SELECT s FROM SubPeriodoAcademicoSeccionCurso s"),
    @NamedQuery(name = "SubPeriodoAcademicoSeccionCurso.findByIdSubPeriodoAcademicoSeccionCurso", query = "SELECT s FROM SubPeriodoAcademicoSeccionCurso s WHERE s.idSubPeriodoAcademicoSeccionCurso = :idSubPeriodoAcademicoSeccionCurso"),
    @NamedQuery(name = "SubPeriodoAcademicoSeccionCurso.findByFormulaNotaSubPeriodo", query = "SELECT s FROM SubPeriodoAcademicoSeccionCurso s WHERE s.formulaNotaSubPeriodo = :formulaNotaSubPeriodo"),
    @NamedQuery(name = "SubPeriodoAcademicoSeccionCurso.findByIdPeriodoAcademicoSeccionCurso", query = "SELECT s FROM SubPeriodoAcademicoSeccionCurso s WHERE s.idPeriodoAcademicoSeccionCurso = :idPeriodoAcademicoSeccionCurso"),
    @NamedQuery(name = "SubPeriodoAcademicoSeccionCurso.findByIdPeriodoAcademicoSeccion", query = "SELECT s FROM SubPeriodoAcademicoSeccionCurso s WHERE s.idPeriodoAcademicoSeccion = :idPeriodoAcademicoSeccion")})
public class SubPeriodoAcademicoSeccionCurso implements Serializable {
    @JoinColumns({
        @JoinColumn(insertable=false, updatable=false ,name = "ID_PERIODO_ACADEMICO_SECCION", referencedColumnName = "ID_PERIODO_ACADEMICO_SECCION"),
        @JoinColumn(insertable=false, updatable=false ,name = "ID_PERIODO_ACADEMICO_SECCION_CURSO", referencedColumnName = "ID_PERIODO_ACADEMICO_SECCION_CURSO")})
    @ManyToOne(optional = false)
    private PeriodoAcademicoSeccionCurso periodoAcademicoSeccionCurso;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO")
    private Long idSubPeriodoAcademicoSeccionCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FORMULA_NOTA_SUB_PERIODO")
    private String formulaNotaSubPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION_CURSO")
    private long idPeriodoAcademicoSeccionCurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION")
    private long idPeriodoAcademicoSeccion;
    @ManyToMany(mappedBy = "subPeriodoAcademicoSeccionCursoCollection")
    private Collection<Materiales> materialesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubPeriodoAcademicoSeccionCurso")
    private Collection<Asignacion> asignacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubPeriodoAcademicoSeccionCurso")
    private Collection<LibretaNotasSubperiodo> libretaNotasSubperiodoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubPeriodoAcademicoSeccionCurso")
    private Collection<Evaluacion> evaluacionCollection;
    @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION")
    @ManyToOne(optional = false)
    private Seccion idSeccion;
    @JoinColumn(name = "ID_SUB_PERIODO_ACADEMICO", referencedColumnName = "ID_PERIODO_ACADEMICO")
    @ManyToOne(optional = false)
    private PeriodoAcademico idSubPeriodoAcademico;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false)
    private Curso idCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubPeriodoAcademicoSeccionCurso")
    private Collection<Asistencia> asistenciaCollection;

    public SubPeriodoAcademicoSeccionCurso() {
    }

    public SubPeriodoAcademicoSeccionCurso(Long idSubPeriodoAcademicoSeccionCurso) {
        this.idSubPeriodoAcademicoSeccionCurso = idSubPeriodoAcademicoSeccionCurso;
    }

    public SubPeriodoAcademicoSeccionCurso(Long idSubPeriodoAcademicoSeccionCurso, String formulaNotaSubPeriodo, long idPeriodoAcademicoSeccionCurso, long idPeriodoAcademicoSeccion) {
        this.idSubPeriodoAcademicoSeccionCurso = idSubPeriodoAcademicoSeccionCurso;
        this.formulaNotaSubPeriodo = formulaNotaSubPeriodo;
        this.idPeriodoAcademicoSeccionCurso = idPeriodoAcademicoSeccionCurso;
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public Long getIdSubPeriodoAcademicoSeccionCurso() {
        return idSubPeriodoAcademicoSeccionCurso;
    }

    public void setIdSubPeriodoAcademicoSeccionCurso(Long idSubPeriodoAcademicoSeccionCurso) {
        this.idSubPeriodoAcademicoSeccionCurso = idSubPeriodoAcademicoSeccionCurso;
    }

    public String getFormulaNotaSubPeriodo() {
        return formulaNotaSubPeriodo;
    }

    public void setFormulaNotaSubPeriodo(String formulaNotaSubPeriodo) {
        this.formulaNotaSubPeriodo = formulaNotaSubPeriodo;
    }

    public long getIdPeriodoAcademicoSeccionCurso() {
        return idPeriodoAcademicoSeccionCurso;
    }

    public void setIdPeriodoAcademicoSeccionCurso(long idPeriodoAcademicoSeccionCurso) {
        this.idPeriodoAcademicoSeccionCurso = idPeriodoAcademicoSeccionCurso;
    }

    public long getIdPeriodoAcademicoSeccion() {
        return idPeriodoAcademicoSeccion;
    }

    public void setIdPeriodoAcademicoSeccion(long idPeriodoAcademicoSeccion) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    @XmlTransient
    public Collection<Materiales> getMaterialesCollection() {
        return materialesCollection;
    }

    public void setMaterialesCollection(Collection<Materiales> materialesCollection) {
        this.materialesCollection = materialesCollection;
    }

    @XmlTransient
    public Collection<Asignacion> getAsignacionCollection() {
        return asignacionCollection;
    }

    public void setAsignacionCollection(Collection<Asignacion> asignacionCollection) {
        this.asignacionCollection = asignacionCollection;
    }

    @XmlTransient
    public Collection<LibretaNotasSubperiodo> getLibretaNotasSubperiodoCollection() {
        return libretaNotasSubperiodoCollection;
    }

    public void setLibretaNotasSubperiodoCollection(Collection<LibretaNotasSubperiodo> libretaNotasSubperiodoCollection) {
        this.libretaNotasSubperiodoCollection = libretaNotasSubperiodoCollection;
    }

    @XmlTransient
    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public PeriodoAcademico getIdSubPeriodoAcademico() {
        return idSubPeriodoAcademico;
    }

    public void setIdSubPeriodoAcademico(PeriodoAcademico idSubPeriodoAcademico) {
        this.idSubPeriodoAcademico = idSubPeriodoAcademico;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
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
        hash += (idSubPeriodoAcademicoSeccionCurso != null ? idSubPeriodoAcademicoSeccionCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubPeriodoAcademicoSeccionCurso)) {
            return false;
        }
        SubPeriodoAcademicoSeccionCurso other = (SubPeriodoAcademicoSeccionCurso) object;
        if ((this.idSubPeriodoAcademicoSeccionCurso == null && other.idSubPeriodoAcademicoSeccionCurso != null) || (this.idSubPeriodoAcademicoSeccionCurso != null && !this.idSubPeriodoAcademicoSeccionCurso.equals(other.idSubPeriodoAcademicoSeccionCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.SubPeriodoAcademicoSeccionCurso[ idSubPeriodoAcademicoSeccionCurso=" + idSubPeriodoAcademicoSeccionCurso + " ]";
    }

    public PeriodoAcademicoSeccionCurso getPeriodoAcademicoSeccionCurso() {
        return periodoAcademicoSeccionCurso;
    }

    public void setPeriodoAcademicoSeccionCurso(PeriodoAcademicoSeccionCurso periodoAcademicoSeccionCurso) {
        this.periodoAcademicoSeccionCurso = periodoAcademicoSeccionCurso;
    }
    
}
