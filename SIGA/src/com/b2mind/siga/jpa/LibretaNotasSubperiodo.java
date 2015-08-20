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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "LIBRETA_NOTAS_SUBPERIODO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LibretaNotasSubperiodo.findAll", query = "SELECT l FROM LibretaNotasSubperiodo l"),
    @NamedQuery(name = "LibretaNotasSubperiodo.findByIdLibretaNotasSubperiodo", query = "SELECT l FROM LibretaNotasSubperiodo l WHERE l.idLibretaNotasSubperiodo = :idLibretaNotasSubperiodo"),
    @NamedQuery(name = "LibretaNotasSubperiodo.findByNota", query = "SELECT l FROM LibretaNotasSubperiodo l WHERE l.nota = :nota")})
public class LibretaNotasSubperiodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LIBRETA_NOTAS_SUBPERIODO")
    private Long idLibretaNotasSubperiodo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTA")
    private BigDecimal nota;
    @JoinColumn(name = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO", referencedColumnName = "ID_SUB_PERIODO_ACADEMICO_SECCION_CURSO")
    @ManyToOne(optional = false)
    private SubPeriodoAcademicoSeccionCurso idSubPeriodoAcademicoSeccionCurso;
    @JoinColumn(name = "ID_SUB_PERIODO", referencedColumnName = "ID_PERIODO_ACADEMICO")
    @ManyToOne(optional = false)
    private PeriodoAcademico idSubPeriodo;
    @JoinColumn(name = "ID_LIBRETA_NOTAS", referencedColumnName = "ID_LIBRETA_NOTAS")
    @ManyToOne(optional = false)
    private LibretaNotas idLibretaNotas;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false)
    private Curso idCurso;

    public LibretaNotasSubperiodo() {
    }

    public LibretaNotasSubperiodo(Long idLibretaNotasSubperiodo) {
        this.idLibretaNotasSubperiodo = idLibretaNotasSubperiodo;
    }

    public LibretaNotasSubperiodo(Long idLibretaNotasSubperiodo, BigDecimal nota) {
        this.idLibretaNotasSubperiodo = idLibretaNotasSubperiodo;
        this.nota = nota;
    }

    public Long getIdLibretaNotasSubperiodo() {
        return idLibretaNotasSubperiodo;
    }

    public void setIdLibretaNotasSubperiodo(Long idLibretaNotasSubperiodo) {
        this.idLibretaNotasSubperiodo = idLibretaNotasSubperiodo;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public SubPeriodoAcademicoSeccionCurso getIdSubPeriodoAcademicoSeccionCurso() {
        return idSubPeriodoAcademicoSeccionCurso;
    }

    public void setIdSubPeriodoAcademicoSeccionCurso(SubPeriodoAcademicoSeccionCurso idSubPeriodoAcademicoSeccionCurso) {
        this.idSubPeriodoAcademicoSeccionCurso = idSubPeriodoAcademicoSeccionCurso;
    }

    public PeriodoAcademico getIdSubPeriodo() {
        return idSubPeriodo;
    }

    public void setIdSubPeriodo(PeriodoAcademico idSubPeriodo) {
        this.idSubPeriodo = idSubPeriodo;
    }

    public LibretaNotas getIdLibretaNotas() {
        return idLibretaNotas;
    }

    public void setIdLibretaNotas(LibretaNotas idLibretaNotas) {
        this.idLibretaNotas = idLibretaNotas;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibretaNotasSubperiodo != null ? idLibretaNotasSubperiodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LibretaNotasSubperiodo)) {
            return false;
        }
        LibretaNotasSubperiodo other = (LibretaNotasSubperiodo) object;
        if ((this.idLibretaNotasSubperiodo == null && other.idLibretaNotasSubperiodo != null) || (this.idLibretaNotasSubperiodo != null && !this.idLibretaNotasSubperiodo.equals(other.idLibretaNotasSubperiodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.LibretaNotasSubperiodo[ idLibretaNotasSubperiodo=" + idLibretaNotasSubperiodo + " ]";
    }
    
}
