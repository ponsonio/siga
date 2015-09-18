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
@Table(name = "LIBRETA_NOTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LibretaNotas.findAll", query = "SELECT l FROM LibretaNotas l"),
    @NamedQuery(name = "LibretaNotas.findByIdLibretaNotas", query = "SELECT l FROM LibretaNotas l WHERE l.idLibretaNotas = :idLibretaNotas"),
    @NamedQuery(name = "LibretaNotas.findByIdPeriodoAcademicoSeccion", query = "SELECT l FROM LibretaNotas l WHERE l.idPeriodoAcademicoSeccion = :idPeriodoAcademicoSeccion")})
public class LibretaNotas implements Serializable {
    @JoinColumn(name = "ID_PERIODO_ACADEMICO_SECCION", referencedColumnName = "ID_PERIODO_ACADEMICO_SECCION")
    @ManyToOne(optional = false)
    private PeriodoAcademicoSeccion idPeriodoAcademicoSeccion;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LIBRETA_NOTAS")
    private Long idLibretaNotas;
    

    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLibretaNotas")
    private Collection<LibretaNotasSubperiodo> libretaNotasSubperiodoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLibretaNotas")
    private Collection<LibretaNotasFinalesCurso> libretaNotasFinalesCursoCollection;
    @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION")
    @ManyToOne(optional = false)
    private Seccion idSeccion;
    @JoinColumn(name = "ID_PERIODO_ACADEMICO", referencedColumnName = "ID_PERIODO_ACADEMICO")
    @ManyToOne(optional = false)
    private PeriodoAcademico idPeriodoAcademico;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Alumno idAlumno;

    public LibretaNotas() {
    }

    public LibretaNotas(Long idLibretaNotas) {
        this.idLibretaNotas = idLibretaNotas;
    }

    public LibretaNotas(Long idLibretaNotas, long idPeriodoAcademicoSeccion) {
        this.idLibretaNotas = idLibretaNotas;
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public Long getIdLibretaNotas() {
        return idLibretaNotas;
    }

    public void setIdLibretaNotas(Long idLibretaNotas) {
        this.idLibretaNotas = idLibretaNotas;
    }



    @XmlTransient
    public Collection<LibretaNotasSubperiodo> getLibretaNotasSubperiodoCollection() {
        return libretaNotasSubperiodoCollection;
    }

    public void setLibretaNotasSubperiodoCollection(Collection<LibretaNotasSubperiodo> libretaNotasSubperiodoCollection) {
        this.libretaNotasSubperiodoCollection = libretaNotasSubperiodoCollection;
    }

    @XmlTransient
    public Collection<LibretaNotasFinalesCurso> getLibretaNotasFinalesCursoCollection() {
        return libretaNotasFinalesCursoCollection;
    }

    public void setLibretaNotasFinalesCursoCollection(Collection<LibretaNotasFinalesCurso> libretaNotasFinalesCursoCollection) {
        this.libretaNotasFinalesCursoCollection = libretaNotasFinalesCursoCollection;
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

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibretaNotas != null ? idLibretaNotas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LibretaNotas)) {
            return false;
        }
        LibretaNotas other = (LibretaNotas) object;
        if ((this.idLibretaNotas == null && other.idLibretaNotas != null) || (this.idLibretaNotas != null && !this.idLibretaNotas.equals(other.idLibretaNotas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.LibretaNotas[ idLibretaNotas=" + idLibretaNotas + " ]";
    }

    public PeriodoAcademicoSeccion getIdPeriodoAcademicoSeccion() {
        return idPeriodoAcademicoSeccion;
    }

    public void setIdPeriodoAcademicoSeccion(PeriodoAcademicoSeccion idPeriodoAcademicoSeccion) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }
    
}
