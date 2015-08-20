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
public class RolUsuarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROL")
    private long idRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private long idUsuario;

    public RolUsuarioPK() {
    }

    public RolUsuarioPK(long idRol, long idUsuario) {
        this.idRol = idRol;
        this.idUsuario = idUsuario;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRol;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuarioPK)) {
            return false;
        }
        RolUsuarioPK other = (RolUsuarioPK) object;
        if (this.idRol != other.idRol) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.RolUsuarioPK[ idRol=" + idRol + ", idUsuario=" + idUsuario + " ]";
    }
    
}
