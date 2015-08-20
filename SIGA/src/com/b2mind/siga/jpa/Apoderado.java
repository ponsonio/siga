/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "APODERADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apoderado.findAll", query = "SELECT a FROM Apoderado a"),
    @NamedQuery(name = "Apoderado.findByIdPersona", query = "SELECT a FROM Apoderado a WHERE a.idPersona = :idPersona"),
    @NamedQuery(name = "Apoderado.findByIdTipoApoderado", query = "SELECT a FROM Apoderado a WHERE a.idTipoApoderado = :idTipoApoderado")})
public class Apoderado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA")
    private Long idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_APODERADO")
    private short idTipoApoderado;
    @ManyToMany(mappedBy = "apoderadoCollection")
    private Collection<Alumno> alumnoCollection;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;

    public Apoderado() {
    }

    public Apoderado(Long idPersona) {
        this.idPersona = idPersona;
    }

    public Apoderado(Long idPersona, short idTipoApoderado) {
        this.idPersona = idPersona;
        this.idTipoApoderado = idTipoApoderado;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public short getIdTipoApoderado() {
        return idTipoApoderado;
    }

    public void setIdTipoApoderado(short idTipoApoderado) {
        this.idTipoApoderado = idTipoApoderado;
    }

    @XmlTransient
    public Collection<Alumno> getAlumnoCollection() {
        return alumnoCollection;
    }

    public void setAlumnoCollection(Collection<Alumno> alumnoCollection) {
        this.alumnoCollection = alumnoCollection;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apoderado)) {
            return false;
        }
        Apoderado other = (Apoderado) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Apoderado[ idPersona=" + idPersona + " ]";
    }
    
}
