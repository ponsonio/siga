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
@Table(name = "SECCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findByIdSeccion", query = "SELECT s FROM Seccion s WHERE s.idSeccion = :idSeccion"),
    @NamedQuery(name = "Seccion.findByCorrelativo", query = "SELECT s FROM Seccion s WHERE s.correlativo = :correlativo"),
    @NamedQuery(name = "Seccion.findByEtiqueta", query = "SELECT s FROM Seccion s WHERE s.etiqueta = :etiqueta")})
public class Seccion implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion")
    private Collection<PeriodoAcademicoSeccion> periodoAcademicoSeccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion")
    private Collection<PeriodoAcademicoSeccionCurso> periodoAcademicoSeccionCursoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SECCION")
    private Long idSeccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CORRELATIVO")
    private int correlativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ETIQUETA")
    private String etiqueta;
    @ManyToMany(mappedBy = "seccionCollection")
    private Collection<Aviso> avisoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion")
    private Collection<LibretaNotas> libretaNotasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion")
    private Collection<ResumenAlumno> resumenAlumnoCollection;
    @JoinColumn(name = "ID_GRADO", referencedColumnName = "ID_GRADO")
    @ManyToOne(optional = false)
    private Grado idGrado;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio idColegio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeccion")
    private Collection<SubPeriodoAcademicoSeccionCurso> subPeriodoAcademicoSeccionCursoCollection;

    public Seccion() {
    }

    public Seccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Seccion(Long idSeccion, int correlativo, String etiqueta) {
        this.idSeccion = idSeccion;
        this.correlativo = correlativo;
        this.etiqueta = etiqueta;
    }

    public Long getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @XmlTransient
    public Collection<Aviso> getAvisoCollection() {
        return avisoCollection;
    }

    public void setAvisoCollection(Collection<Aviso> avisoCollection) {
        this.avisoCollection = avisoCollection;
    }

    @XmlTransient
    public Collection<LibretaNotas> getLibretaNotasCollection() {
        return libretaNotasCollection;
    }

    public void setLibretaNotasCollection(Collection<LibretaNotas> libretaNotasCollection) {
        this.libretaNotasCollection = libretaNotasCollection;
    }

    @XmlTransient
    public Collection<ResumenAlumno> getResumenAlumnoCollection() {
        return resumenAlumnoCollection;
    }

    public void setResumenAlumnoCollection(Collection<ResumenAlumno> resumenAlumnoCollection) {
        this.resumenAlumnoCollection = resumenAlumnoCollection;
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

    @XmlTransient
    public Collection<SubPeriodoAcademicoSeccionCurso> getSubPeriodoAcademicoSeccionCursoCollection() {
        return subPeriodoAcademicoSeccionCursoCollection;
    }

    public void setSubPeriodoAcademicoSeccionCursoCollection(Collection<SubPeriodoAcademicoSeccionCurso> subPeriodoAcademicoSeccionCursoCollection) {
        this.subPeriodoAcademicoSeccionCursoCollection = subPeriodoAcademicoSeccionCursoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeccion != null ? idSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idSeccion == null && other.idSeccion != null) || (this.idSeccion != null && !this.idSeccion.equals(other.idSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Seccion[ idSeccion=" + idSeccion + " ]";
    }

    @XmlTransient
    public Collection<PeriodoAcademicoSeccion> getPeriodoAcademicoSeccionCollection() {
        return periodoAcademicoSeccionCollection;
    }

    public void setPeriodoAcademicoSeccionCollection(Collection<PeriodoAcademicoSeccion> periodoAcademicoSeccionCollection) {
        this.periodoAcademicoSeccionCollection = periodoAcademicoSeccionCollection;
    }

    @XmlTransient
    public Collection<PeriodoAcademicoSeccionCurso> getPeriodoAcademicoSeccionCursoCollection() {
        return periodoAcademicoSeccionCursoCollection;
    }

    public void setPeriodoAcademicoSeccionCursoCollection(Collection<PeriodoAcademicoSeccionCurso> periodoAcademicoSeccionCursoCollection) {
        this.periodoAcademicoSeccionCursoCollection = periodoAcademicoSeccionCursoCollection;
    }
    
}
