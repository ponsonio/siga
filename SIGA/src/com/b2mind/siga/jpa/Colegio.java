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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "COLEGIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colegio.findAll", query = "SELECT c FROM Colegio c"),
    @NamedQuery(name = "Colegio.findByIdColegio", query = "SELECT c FROM Colegio c WHERE c.idColegio = :idColegio"),
    @NamedQuery(name = "Colegio.findByCodigo", query = "SELECT c FROM Colegio c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Colegio.findByDireccion", query = "SELECT c FROM Colegio c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Colegio.findByNombre", query = "SELECT c FROM Colegio c WHERE c.nombre = :nombre")})
public class Colegio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COLEGIO")
    private Long idColegio;
    @Size(max = 32600)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32600)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32600)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinTable(name = "MONEDA_COLEGIO", joinColumns = {
        @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID_MONEDA")})
    @ManyToMany
    private Collection<Moneda> monedaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<Concepto> conceptoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<PeriodoAcademico> periodoAcademicoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<TipoAsignacion> tipoAsignacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<TipoEvaluacion> tipoEvaluacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<Docente> docenteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<Grado> gradoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<RepositorioArchivos> repositorioArchivosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colegio")
    private Collection<CuentaBanco> cuentaBancoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<ResumenAlumno> resumenAlumnoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<Seccion> seccionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<Curso> cursoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<Aviso> avisoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColegio")
    private Collection<Alumno> alumnoCollection;

    public Colegio() {
    }

    public Colegio(Long idColegio) {
        this.idColegio = idColegio;
    }

    public Colegio(Long idColegio, String direccion, String nombre) {
        this.idColegio = idColegio;
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public Long getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Long idColegio) {
        this.idColegio = idColegio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Moneda> getMonedaCollection() {
        return monedaCollection;
    }

    public void setMonedaCollection(Collection<Moneda> monedaCollection) {
        this.monedaCollection = monedaCollection;
    }

    @XmlTransient
    public Collection<Concepto> getConceptoCollection() {
        return conceptoCollection;
    }

    public void setConceptoCollection(Collection<Concepto> conceptoCollection) {
        this.conceptoCollection = conceptoCollection;
    }

    @XmlTransient
    public Collection<PeriodoAcademico> getPeriodoAcademicoCollection() {
        return periodoAcademicoCollection;
    }

    public void setPeriodoAcademicoCollection(Collection<PeriodoAcademico> periodoAcademicoCollection) {
        this.periodoAcademicoCollection = periodoAcademicoCollection;
    }

    @XmlTransient
    public Collection<TipoAsignacion> getTipoAsignacionCollection() {
        return tipoAsignacionCollection;
    }

    public void setTipoAsignacionCollection(Collection<TipoAsignacion> tipoAsignacionCollection) {
        this.tipoAsignacionCollection = tipoAsignacionCollection;
    }

    @XmlTransient
    public Collection<TipoEvaluacion> getTipoEvaluacionCollection() {
        return tipoEvaluacionCollection;
    }

    public void setTipoEvaluacionCollection(Collection<TipoEvaluacion> tipoEvaluacionCollection) {
        this.tipoEvaluacionCollection = tipoEvaluacionCollection;
    }

    @XmlTransient
    public Collection<Docente> getDocenteCollection() {
        return docenteCollection;
    }

    public void setDocenteCollection(Collection<Docente> docenteCollection) {
        this.docenteCollection = docenteCollection;
    }

    @XmlTransient
    public Collection<Grado> getGradoCollection() {
        return gradoCollection;
    }

    public void setGradoCollection(Collection<Grado> gradoCollection) {
        this.gradoCollection = gradoCollection;
    }

    @XmlTransient
    public Collection<RepositorioArchivos> getRepositorioArchivosCollection() {
        return repositorioArchivosCollection;
    }

    public void setRepositorioArchivosCollection(Collection<RepositorioArchivos> repositorioArchivosCollection) {
        this.repositorioArchivosCollection = repositorioArchivosCollection;
    }

    @XmlTransient
    public Collection<CuentaBanco> getCuentaBancoCollection() {
        return cuentaBancoCollection;
    }

    public void setCuentaBancoCollection(Collection<CuentaBanco> cuentaBancoCollection) {
        this.cuentaBancoCollection = cuentaBancoCollection;
    }

    @XmlTransient
    public Collection<ResumenAlumno> getResumenAlumnoCollection() {
        return resumenAlumnoCollection;
    }

    public void setResumenAlumnoCollection(Collection<ResumenAlumno> resumenAlumnoCollection) {
        this.resumenAlumnoCollection = resumenAlumnoCollection;
    }

    @XmlTransient
    public Collection<Seccion> getSeccionCollection() {
        return seccionCollection;
    }

    public void setSeccionCollection(Collection<Seccion> seccionCollection) {
        this.seccionCollection = seccionCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Curso> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<Curso> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }

    @XmlTransient
    public Collection<Aviso> getAvisoCollection() {
        return avisoCollection;
    }

    public void setAvisoCollection(Collection<Aviso> avisoCollection) {
        this.avisoCollection = avisoCollection;
    }

    @XmlTransient
    public Collection<Alumno> getAlumnoCollection() {
        return alumnoCollection;
    }

    public void setAlumnoCollection(Collection<Alumno> alumnoCollection) {
        this.alumnoCollection = alumnoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColegio != null ? idColegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colegio)) {
            return false;
        }
        Colegio other = (Colegio) object;
        if ((this.idColegio == null && other.idColegio != null) || (this.idColegio != null && !this.idColegio.equals(other.idColegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Colegio[ idColegio=" + idColegio + " ]";
    }
    
}
