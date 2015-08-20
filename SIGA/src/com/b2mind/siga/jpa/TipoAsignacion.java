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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "TIPO_ASIGNACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAsignacion.findAll", query = "SELECT t FROM TipoAsignacion t"),
    @NamedQuery(name = "TipoAsignacion.findByIdTipoAsignacion", query = "SELECT t FROM TipoAsignacion t WHERE t.idTipoAsignacion = :idTipoAsignacion"),
    @NamedQuery(name = "TipoAsignacion.findByNombre", query = "SELECT t FROM TipoAsignacion t WHERE t.nombre = :nombre")})
public class TipoAsignacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_ASIGNACION")
    private Short idTipoAsignacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO")
    @ManyToOne(optional = false)
    private Colegio idColegio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoAsignacion")
    private Collection<Asignacion> asignacionCollection;

    public TipoAsignacion() {
    }

    public TipoAsignacion(Short idTipoAsignacion) {
        this.idTipoAsignacion = idTipoAsignacion;
    }

    public TipoAsignacion(Short idTipoAsignacion, String nombre) {
        this.idTipoAsignacion = idTipoAsignacion;
        this.nombre = nombre;
    }

    public Short getIdTipoAsignacion() {
        return idTipoAsignacion;
    }

    public void setIdTipoAsignacion(Short idTipoAsignacion) {
        this.idTipoAsignacion = idTipoAsignacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Colegio getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Colegio idColegio) {
        this.idColegio = idColegio;
    }

    @XmlTransient
    public Collection<Asignacion> getAsignacionCollection() {
        return asignacionCollection;
    }

    public void setAsignacionCollection(Collection<Asignacion> asignacionCollection) {
        this.asignacionCollection = asignacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoAsignacion != null ? idTipoAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAsignacion)) {
            return false;
        }
        TipoAsignacion other = (TipoAsignacion) object;
        if ((this.idTipoAsignacion == null && other.idTipoAsignacion != null) || (this.idTipoAsignacion != null && !this.idTipoAsignacion.equals(other.idTipoAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.TipoAsignacion[ idTipoAsignacion=" + idTipoAsignacion + " ]";
    }
    
}
