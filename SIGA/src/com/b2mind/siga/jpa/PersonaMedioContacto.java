/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "PERSONA_MEDIO_CONTACTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaMedioContacto.findAll", query = "SELECT p FROM PersonaMedioContacto p"),
    @NamedQuery(name = "PersonaMedioContacto.findByIdPersona", query = "SELECT p FROM PersonaMedioContacto p WHERE p.personaMedioContactoPK.idPersona = :idPersona"),
    @NamedQuery(name = "PersonaMedioContacto.findByIdMedioContacto", query = "SELECT p FROM PersonaMedioContacto p WHERE p.personaMedioContactoPK.idMedioContacto = :idMedioContacto"),
    @NamedQuery(name = "PersonaMedioContacto.findByReferencia", query = "SELECT p FROM PersonaMedioContacto p WHERE p.referencia = :referencia")})
public class PersonaMedioContacto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaMedioContactoPK personaMedioContactoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32600)
    @Column(name = "REFERENCIA")
    private String referencia;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;

    public PersonaMedioContacto() {
    }

    public PersonaMedioContacto(PersonaMedioContactoPK personaMedioContactoPK) {
        this.personaMedioContactoPK = personaMedioContactoPK;
    }

    public PersonaMedioContacto(PersonaMedioContactoPK personaMedioContactoPK, String referencia) {
        this.personaMedioContactoPK = personaMedioContactoPK;
        this.referencia = referencia;
    }

    public PersonaMedioContacto(long idPersona, int idMedioContacto) {
        this.personaMedioContactoPK = new PersonaMedioContactoPK(idPersona, idMedioContacto);
    }

    public PersonaMedioContactoPK getPersonaMedioContactoPK() {
        return personaMedioContactoPK;
    }

    public void setPersonaMedioContactoPK(PersonaMedioContactoPK personaMedioContactoPK) {
        this.personaMedioContactoPK = personaMedioContactoPK;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
        hash += (personaMedioContactoPK != null ? personaMedioContactoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaMedioContacto)) {
            return false;
        }
        PersonaMedioContacto other = (PersonaMedioContacto) object;
        if ((this.personaMedioContactoPK == null && other.personaMedioContactoPK != null) || (this.personaMedioContactoPK != null && !this.personaMedioContactoPK.equals(other.personaMedioContactoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.PersonaMedioContacto[ personaMedioContactoPK=" + personaMedioContactoPK + " ]";
    }
    
}
