/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "PERIODO_ACADEMICO_SECCION_CURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoAcademicoSeccionCurso.findAll", query = "SELECT p FROM PeriodoAcademicoSeccionCurso p"),
    @NamedQuery(name = "PeriodoAcademicoSeccionCurso.findByIdPeriodoAcademicoSeccion", query = "SELECT p FROM PeriodoAcademicoSeccionCurso p WHERE p.periodoAcademicoSeccionCursoPK.idPeriodoAcademicoSeccion = :idPeriodoAcademicoSeccion"),
    @NamedQuery(name = "PeriodoAcademicoSeccionCurso.findByIdPeriodoAcademicoSeccionCurso", query = "SELECT p FROM PeriodoAcademicoSeccionCurso p WHERE p.periodoAcademicoSeccionCursoPK.idPeriodoAcademicoSeccionCurso = :idPeriodoAcademicoSeccionCurso"),
    @NamedQuery(name = "PeriodoAcademicoSeccionCurso.findByFormulaNotaFinal", query = "SELECT p FROM PeriodoAcademicoSeccionCurso p WHERE p.formulaNotaFinal = :formulaNotaFinal")})
public class PeriodoAcademicoSeccionCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeriodoAcademicoSeccionCursoPK periodoAcademicoSeccionCursoPK;
    @Size(max = 50)
    @Column(name = "FORMULA_NOTA_FINAL")
    private String formulaNotaFinal;
    @ManyToMany(mappedBy = "periodoAcademicoSeccionCursoCollection")
    private Collection<Materiales> materialesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoAcademicoSeccionCurso")
    private Collection<LibretaNotasFinalesCurso> libretaNotasFinalesCursoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoAcademicoSeccionCurso")
    private Collection<SubPeriodoAcademicoSeccionCurso> subPeriodoAcademicoSeccionCursoCollection;
    @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION")
    @ManyToOne(optional = false)
    private Seccion idSeccion;
    @JoinColumn(name = "ID_PERIODO_ACADEMICO_SECCION", referencedColumnName = "ID_PERIODO_ACADEMICO_SECCION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PeriodoAcademicoSeccion periodoAcademicoSeccion;
    @JoinColumn(name = "ID_DOCENTE", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Docente idDocente;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false)
    private Curso idCurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoAcademicoSeccionCurso")
    private Collection<PeriodoAcademicoSeccionCursoNota> periodoAcademicoSeccionCursoNotaCollection;

    public PeriodoAcademicoSeccionCurso() {
    }

    public PeriodoAcademicoSeccionCurso(PeriodoAcademicoSeccionCursoPK periodoAcademicoSeccionCursoPK) {
        this.periodoAcademicoSeccionCursoPK = periodoAcademicoSeccionCursoPK;
    }

    public PeriodoAcademicoSeccionCurso(long idPeriodoAcademicoSeccion, long idPeriodoAcademicoSeccionCurso) {
        this.periodoAcademicoSeccionCursoPK = new PeriodoAcademicoSeccionCursoPK(idPeriodoAcademicoSeccion, idPeriodoAcademicoSeccionCurso);
    }

    public PeriodoAcademicoSeccionCursoPK getPeriodoAcademicoSeccionCursoPK() {
        return periodoAcademicoSeccionCursoPK;
    }

    public void setPeriodoAcademicoSeccionCursoPK(PeriodoAcademicoSeccionCursoPK periodoAcademicoSeccionCursoPK) {
        this.periodoAcademicoSeccionCursoPK = periodoAcademicoSeccionCursoPK;
    }

    public String getFormulaNotaFinal() {
        return formulaNotaFinal;
    }

    public void setFormulaNotaFinal(String formulaNotaFinal) {
        this.formulaNotaFinal = formulaNotaFinal;
    }

    @XmlTransient
    public Collection<Materiales> getMaterialesCollection() {
        return materialesCollection;
    }

    public void setMaterialesCollection(Collection<Materiales> materialesCollection) {
        this.materialesCollection = materialesCollection;
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

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public PeriodoAcademicoSeccion getPeriodoAcademicoSeccion() {
        return periodoAcademicoSeccion;
    }

    public void setPeriodoAcademicoSeccion(PeriodoAcademicoSeccion periodoAcademicoSeccion) {
        this.periodoAcademicoSeccion = periodoAcademicoSeccion;
    }

    public Docente getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    @XmlTransient
    public Collection<PeriodoAcademicoSeccionCursoNota> getPeriodoAcademicoSeccionCursoNotaCollection() {
        return periodoAcademicoSeccionCursoNotaCollection;
    }

    public void setPeriodoAcademicoSeccionCursoNotaCollection(Collection<PeriodoAcademicoSeccionCursoNota> periodoAcademicoSeccionCursoNotaCollection) {
        this.periodoAcademicoSeccionCursoNotaCollection = periodoAcademicoSeccionCursoNotaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodoAcademicoSeccionCursoPK != null ? periodoAcademicoSeccionCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoAcademicoSeccionCurso)) {
            return false;
        }
        PeriodoAcademicoSeccionCurso other = (PeriodoAcademicoSeccionCurso) object;
        if ((this.periodoAcademicoSeccionCursoPK == null && other.periodoAcademicoSeccionCursoPK != null) || (this.periodoAcademicoSeccionCursoPK != null && !this.periodoAcademicoSeccionCursoPK.equals(other.periodoAcademicoSeccionCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.PeriodoAcademicoSeccionCurso[ periodoAcademicoSeccionCursoPK=" + periodoAcademicoSeccionCursoPK + " ]";
    }
    
}
