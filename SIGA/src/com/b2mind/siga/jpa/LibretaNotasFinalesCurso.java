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
import javax.persistence.JoinColumns;
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
@Table(name = "LIBRETA_NOTAS_FINALES_CURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LibretaNotasFinalesCurso.findAll", query = "SELECT l FROM LibretaNotasFinalesCurso l"),
    @NamedQuery(name = "LibretaNotasFinalesCurso.findById", query = "SELECT l FROM LibretaNotasFinalesCurso l WHERE l.id = :id"),
    @NamedQuery(name = "LibretaNotasFinalesCurso.findByNota", query = "SELECT l FROM LibretaNotasFinalesCurso l WHERE l.nota = :nota"),
    @NamedQuery(name = "LibretaNotasFinalesCurso.findByIdPeriodoAcademicoSeccionCurso", query = "SELECT l FROM LibretaNotasFinalesCurso l WHERE l.idPeriodoAcademicoSeccionCurso = :idPeriodoAcademicoSeccionCurso"),
    @NamedQuery(name = "LibretaNotasFinalesCurso.findByIdPeriodoAcademicoSeccion", query = "SELECT l FROM LibretaNotasFinalesCurso l WHERE l.idPeriodoAcademicoSeccion = :idPeriodoAcademicoSeccion")})
public class LibretaNotasFinalesCurso implements Serializable {
    @JoinColumns({
        @JoinColumn(name = "ID_PERIODO_ACADEMICO_SECCION", referencedColumnName = "ID_PERIODO_ACADEMICO_SECCION"),
        @JoinColumn(name = "ID_PERIODO_ACADEMICO_SECCION_CURSO", referencedColumnName = "ID_PERIODO_ACADEMICO_SECCION_CURSO")})
    @ManyToOne(optional = false)
    private PeriodoAcademicoSeccionCurso periodoAcademicoSeccionCurso;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTA")
    private BigDecimal nota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION_CURSO")
    private long idPeriodoAcademicoSeccionCurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION")
    private long idPeriodoAcademicoSeccion;
    @JoinColumn(name = "ID_LIBRETA_NOTAS", referencedColumnName = "ID_LIBRETA_NOTAS")
    @ManyToOne(optional = false)
    private LibretaNotas idLibretaNotas;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false)
    private Curso idCurso;

    public LibretaNotasFinalesCurso() {
    }

    public LibretaNotasFinalesCurso(Long id) {
        this.id = id;
    }

    public LibretaNotasFinalesCurso(Long id, BigDecimal nota, long idPeriodoAcademicoSeccionCurso, long idPeriodoAcademicoSeccion) {
        this.id = id;
        this.nota = nota;
        this.idPeriodoAcademicoSeccionCurso = idPeriodoAcademicoSeccionCurso;
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LibretaNotasFinalesCurso)) {
            return false;
        }
        LibretaNotasFinalesCurso other = (LibretaNotasFinalesCurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.LibretaNotasFinalesCurso[ id=" + id + " ]";
    }

    public PeriodoAcademicoSeccionCurso getPeriodoAcademicoSeccionCurso() {
        return periodoAcademicoSeccionCurso;
    }

    public void setPeriodoAcademicoSeccionCurso(PeriodoAcademicoSeccionCurso periodoAcademicoSeccionCurso) {
        this.periodoAcademicoSeccionCurso = periodoAcademicoSeccionCurso;
    }
    
}
