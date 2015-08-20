/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "AVISO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aviso.findAll", query = "SELECT a FROM Aviso a"),
    @NamedQuery(name = "Aviso.findByIdAviso", query = "SELECT a FROM Aviso a WHERE a.idAviso = :idAviso"),
    @NamedQuery(name = "Aviso.findByNombre", query = "SELECT a FROM Aviso a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Aviso.findByDescripcion", query = "SELECT a FROM Aviso a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Aviso.findByLugar", query = "SELECT a FROM Aviso a WHERE a.lugar = :lugar"),
    @NamedQuery(name = "Aviso.findByFechaFinPublicacion", query = "SELECT a FROM Aviso a WHERE a.fechaFinPublicacion = :fechaFinPublicacion"),
    @NamedQuery(name = "Aviso.findByFechaInicioPublicacion", query = "SELECT a FROM Aviso a WHERE a.fechaInicioPublicacion = :fechaInicioPublicacion"),
    @NamedQuery(name = "Aviso.findByGeneral", query = "SELECT a FROM Aviso a WHERE a.general = :general")})
public class Aviso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_AVISO")
    private Long idAviso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LUGAR")
    private String lugar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN_PUBLICACION")
    @Temporal(TemporalType.DATE)
    private Date fechaFinPublicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO_PUBLICACION")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioPublicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GENERAL")
    private Character general;
    @JoinTable(name = "AVISO_SECCION", joinColumns = {
        @JoinColumn(name = "ID_AVISO", referencedColumnName = "ID_AVISO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION")})
    @ManyToMany
    private Collection<Seccion> seccionCollection;
    @JoinColumn(name = "ID_SUB_PERIODO_ACADEMICO", referencedColumnName = "ID_PERIODO_ACADEMICO")
    @ManyToOne(optional = false)
    private PeriodoAcademico idSubPeriodoAcademico;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio idColegio;

    public Aviso() {
    }

    public Aviso(Long idAviso) {
        this.idAviso = idAviso;
    }

    public Aviso(Long idAviso, String nombre, String descripcion, String lugar, Date fechaFinPublicacion, Date fechaInicioPublicacion, Character general) {
        this.idAviso = idAviso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fechaFinPublicacion = fechaFinPublicacion;
        this.fechaInicioPublicacion = fechaInicioPublicacion;
        this.general = general;
    }

    public Long getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(Long idAviso) {
        this.idAviso = idAviso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFechaFinPublicacion() {
        return fechaFinPublicacion;
    }

    public void setFechaFinPublicacion(Date fechaFinPublicacion) {
        this.fechaFinPublicacion = fechaFinPublicacion;
    }

    public Date getFechaInicioPublicacion() {
        return fechaInicioPublicacion;
    }

    public void setFechaInicioPublicacion(Date fechaInicioPublicacion) {
        this.fechaInicioPublicacion = fechaInicioPublicacion;
    }

    public Character getGeneral() {
        return general;
    }

    public void setGeneral(Character general) {
        this.general = general;
    }

    @XmlTransient
    public Collection<Seccion> getSeccionCollection() {
        return seccionCollection;
    }

    public void setSeccionCollection(Collection<Seccion> seccionCollection) {
        this.seccionCollection = seccionCollection;
    }

    public PeriodoAcademico getIdSubPeriodoAcademico() {
        return idSubPeriodoAcademico;
    }

    public void setIdSubPeriodoAcademico(PeriodoAcademico idSubPeriodoAcademico) {
        this.idSubPeriodoAcademico = idSubPeriodoAcademico;
    }

    public Colegio getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Colegio idColegio) {
        this.idColegio = idColegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAviso != null ? idAviso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aviso)) {
            return false;
        }
        Aviso other = (Aviso) object;
        if ((this.idAviso == null && other.idAviso != null) || (this.idAviso != null && !this.idAviso.equals(other.idAviso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Aviso[ idAviso=" + idAviso + " ]";
    }
    
}
