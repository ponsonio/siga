/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author koki
 */
@Embeddable
public class PersonaMedioContactoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA")
    private long idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MEDIO_CONTACTO")
    private int idMedioContacto;

    public PersonaMedioContactoPK() {
    }

    public PersonaMedioContactoPK(long idPersona, int idMedioContacto) {
        this.idPersona = idPersona;
        this.idMedioContacto = idMedioContacto;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdMedioContacto() {
        return idMedioContacto;
    }

    public void setIdMedioContacto(int idMedioContacto) {
        this.idMedioContacto = idMedioContacto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) idMedioContacto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaMedioContactoPK)) {
            return false;
        }
        PersonaMedioContactoPK other = (PersonaMedioContactoPK) object;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.idMedioContacto != other.idMedioContacto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.PersonaMedioContactoPK[ idPersona=" + idPersona + ", idMedioContacto=" + idMedioContacto + " ]";
    }
    
}
