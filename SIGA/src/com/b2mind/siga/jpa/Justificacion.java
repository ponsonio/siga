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
@Table(name = "JUSTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Justificacion.findAll", query = "SELECT j FROM Justificacion j"),
    @NamedQuery(name = "Justificacion.findByIdJustificacion", query = "SELECT j FROM Justificacion j WHERE j.idJustificacion = :idJustificacion"),
    @NamedQuery(name = "Justificacion.findByTipoJustificacion", query = "SELECT j FROM Justificacion j WHERE j.tipoJustificacion = :tipoJustificacion")})
public class Justificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_JUSTIFICACION")
    private Short idJustificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TIPO_JUSTIFICACION")
    private String tipoJustificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJustificacion")
    private Collection<Asistencia> asistenciaCollection;

    public Justificacion() {
    }

    public Justificacion(Short idJustificacion) {
        this.idJustificacion = idJustificacion;
    }

    public Justificacion(Short idJustificacion, String tipoJustificacion) {
        this.idJustificacion = idJustificacion;
        this.tipoJustificacion = tipoJustificacion;
    }

    public Short getIdJustificacion() {
        return idJustificacion;
    }

    public void setIdJustificacion(Short idJustificacion) {
        this.idJustificacion = idJustificacion;
    }

    public String getTipoJustificacion() {
        return tipoJustificacion;
    }

    public void setTipoJustificacion(String tipoJustificacion) {
        this.tipoJustificacion = tipoJustificacion;
    }

    @XmlTransient
    public Collection<Asistencia> getAsistenciaCollection() {
        return asistenciaCollection;
    }

    public void setAsistenciaCollection(Collection<Asistencia> asistenciaCollection) {
        this.asistenciaCollection = asistenciaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJustificacion != null ? idJustificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Justificacion)) {
            return false;
        }
        Justificacion other = (Justificacion) object;
        if ((this.idJustificacion == null && other.idJustificacion != null) || (this.idJustificacion != null && !this.idJustificacion.equals(other.idJustificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Justificacion[ idJustificacion=" + idJustificacion + " ]";
    }
    
}
