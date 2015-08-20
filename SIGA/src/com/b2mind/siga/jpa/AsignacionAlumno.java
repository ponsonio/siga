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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "ASIGNACION_ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignacionAlumno.findAll", query = "SELECT a FROM AsignacionAlumno a"),
    @NamedQuery(name = "AsignacionAlumno.findByIdAsignacionAlumno", query = "SELECT a FROM AsignacionAlumno a WHERE a.idAsignacionAlumno = :idAsignacionAlumno"),
    @NamedQuery(name = "AsignacionAlumno.findByNota", query = "SELECT a FROM AsignacionAlumno a WHERE a.nota = :nota")})
public class AsignacionAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ASIGNACION_ALUMNO")
    private Long idAsignacionAlumno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NOTA")
    private BigDecimal nota;
    @JoinColumn(name = "ID_SUB_PERIODO", referencedColumnName = "ID_PERIODO_ACADEMICO")
    @ManyToOne(optional = false)
    private PeriodoAcademico idSubPeriodo;
    @JoinColumn(name = "ID_ASIGNACION", referencedColumnName = "ID_ASIGNACION")
    @ManyToOne(optional = false)
    private Asignacion idAsignacion;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Alumno idAlumno;

    public AsignacionAlumno() {
    }

    public AsignacionAlumno(Long idAsignacionAlumno) {
        this.idAsignacionAlumno = idAsignacionAlumno;
    }

    public Long getIdAsignacionAlumno() {
        return idAsignacionAlumno;
    }

    public void setIdAsignacionAlumno(Long idAsignacionAlumno) {
        this.idAsignacionAlumno = idAsignacionAlumno;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public PeriodoAcademico getIdSubPeriodo() {
        return idSubPeriodo;
    }

    public void setIdSubPeriodo(PeriodoAcademico idSubPeriodo) {
        this.idSubPeriodo = idSubPeriodo;
    }

    public Asignacion getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Asignacion idAsignacion) {
        this.idAsignacion = idAsignacion;
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
        hash += (idAsignacionAlumno != null ? idAsignacionAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionAlumno)) {
            return false;
        }
        AsignacionAlumno other = (AsignacionAlumno) object;
        if ((this.idAsignacionAlumno == null && other.idAsignacionAlumno != null) || (this.idAsignacionAlumno != null && !this.idAsignacionAlumno.equals(other.idAsignacionAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.AsignacionAlumno[ idAsignacionAlumno=" + idAsignacionAlumno + " ]";
    }
    
}
