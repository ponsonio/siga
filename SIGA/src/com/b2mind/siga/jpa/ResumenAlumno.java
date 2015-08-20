/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "RESUMEN_ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResumenAlumno.findAll", query = "SELECT r FROM ResumenAlumno r"),
    @NamedQuery(name = "ResumenAlumno.findByIdPeriodoAcademico", query = "SELECT r FROM ResumenAlumno r WHERE r.resumenAlumnoPK.idPeriodoAcademico = :idPeriodoAcademico"),
    @NamedQuery(name = "ResumenAlumno.findByIdPersona", query = "SELECT r FROM ResumenAlumno r WHERE r.resumenAlumnoPK.idPersona = :idPersona")})
public class ResumenAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResumenAlumnoPK resumenAlumnoPK;
    @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION")
    @ManyToOne(optional = false)
    private Seccion idSeccion;
    @JoinColumn(name = "ID_PERIODO_ACADEMICO", referencedColumnName = "ID_PERIODO_ACADEMICO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PeriodoAcademico periodoAcademico;
    @JoinColumn(name = "ID_GRADO", referencedColumnName = "ID_GRADO")
    @ManyToOne(optional = false)
    private Grado idGrado;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio idColegio;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;

    public ResumenAlumno() {
    }

    public ResumenAlumno(ResumenAlumnoPK resumenAlumnoPK) {
        this.resumenAlumnoPK = resumenAlumnoPK;
    }

    public ResumenAlumno(long idPeriodoAcademico, long idPersona) {
        this.resumenAlumnoPK = new ResumenAlumnoPK(idPeriodoAcademico, idPersona);
    }

    public ResumenAlumnoPK getResumenAlumnoPK() {
        return resumenAlumnoPK;
    }

    public void setResumenAlumnoPK(ResumenAlumnoPK resumenAlumnoPK) {
        this.resumenAlumnoPK = resumenAlumnoPK;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public PeriodoAcademico getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
    }

    public Colegio getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Colegio idColegio) {
        this.idColegio = idColegio;
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
        hash += (resumenAlumnoPK != null ? resumenAlumnoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResumenAlumno)) {
            return false;
        }
        ResumenAlumno other = (ResumenAlumno) object;
        if ((this.resumenAlumnoPK == null && other.resumenAlumnoPK != null) || (this.resumenAlumnoPK != null && !this.resumenAlumnoPK.equals(other.resumenAlumnoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.ResumenAlumno[ resumenAlumnoPK=" + resumenAlumnoPK + " ]";
    }
    
}
