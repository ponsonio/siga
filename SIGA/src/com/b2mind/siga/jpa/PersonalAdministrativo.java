/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "PERSONAL_ADMINISTRATIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalAdministrativo.findAll", query = "SELECT p FROM PersonalAdministrativo p"),
    @NamedQuery(name = "PersonalAdministrativo.findByIdPersona", query = "SELECT p FROM PersonalAdministrativo p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "PersonalAdministrativo.findByIdColegio", query = "SELECT p FROM PersonalAdministrativo p WHERE p.idColegio = :idColegio")})
public class PersonalAdministrativo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA")
    private Long idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COLEGIO")
    private long idColegio;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;

    public PersonalAdministrativo() {
    }

    public PersonalAdministrativo(Long idPersona) {
        this.idPersona = idPersona;
    }

    public PersonalAdministrativo(Long idPersona, long idColegio) {
        this.idPersona = idPersona;
        this.idColegio = idColegio;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public long getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(long idColegio) {
        this.idColegio = idColegio;
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
        if (!(object instanceof PersonalAdministrativo)) {
            return false;
        }
        PersonalAdministrativo other = (PersonalAdministrativo) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.PersonalAdministrativo[ idPersona=" + idPersona + " ]";
    }
    
}
