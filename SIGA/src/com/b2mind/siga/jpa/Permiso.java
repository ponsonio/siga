/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "PERMISO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p"),
    @NamedQuery(name = "Permiso.findByIdPermiso", query = "SELECT p FROM Permiso p WHERE p.idPermiso = :idPermiso"),
    @NamedQuery(name = "Permiso.findByFechaPermiso", query = "SELECT p FROM Permiso p WHERE p.fechaPermiso = :fechaPermiso"),
    @NamedQuery(name = "Permiso.findByFechaRegistro", query = "SELECT p FROM Permiso p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Permiso.findByComentarios", query = "SELECT p FROM Permiso p WHERE p.comentarios = :comentarios")})
public class Permiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERMISO")
    private Long idPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PERMISO")
    @Temporal(TemporalType.DATE)
    private Date fechaPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "COMENTARIOS")
    private String comentarios;
    @JoinColumn(name = "PERSONA_ID_REGISTRO", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Persona personaIdRegistro;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Persona idPersona;

    public Permiso() {
    }

    public Permiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Permiso(Long idPermiso, Date fechaPermiso, Date fechaRegistro, String comentarios) {
        this.idPermiso = idPermiso;
        this.fechaPermiso = fechaPermiso;
        this.fechaRegistro = fechaRegistro;
        this.comentarios = comentarios;
    }

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Date getFechaPermiso() {
        return fechaPermiso;
    }

    public void setFechaPermiso(Date fechaPermiso) {
        this.fechaPermiso = fechaPermiso;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Persona getPersonaIdRegistro() {
        return personaIdRegistro;
    }

    public void setPersonaIdRegistro(Persona personaIdRegistro) {
        this.personaIdRegistro = personaIdRegistro;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.Permiso[ idPermiso=" + idPermiso + " ]";
    }
    
}
