/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "ROL_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolUsuario.findAll", query = "SELECT r FROM RolUsuario r"),
    @NamedQuery(name = "RolUsuario.findByIdRol", query = "SELECT r FROM RolUsuario r WHERE r.rolUsuarioPK.idRol = :idRol"),
    @NamedQuery(name = "RolUsuario.findByIdUsuario", query = "SELECT r FROM RolUsuario r WHERE r.rolUsuarioPK.idUsuario = :idUsuario")})
public class RolUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolUsuarioPK rolUsuarioPK;

    public RolUsuario() {
    }

    public RolUsuario(RolUsuarioPK rolUsuarioPK) {
        this.rolUsuarioPK = rolUsuarioPK;
    }

    public RolUsuario(long idRol, long idUsuario) {
        this.rolUsuarioPK = new RolUsuarioPK(idRol, idUsuario);
    }

    public RolUsuarioPK getRolUsuarioPK() {
        return rolUsuarioPK;
    }

    public void setRolUsuarioPK(RolUsuarioPK rolUsuarioPK) {
        this.rolUsuarioPK = rolUsuarioPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolUsuarioPK != null ? rolUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuario)) {
            return false;
        }
        RolUsuario other = (RolUsuario) object;
        if ((this.rolUsuarioPK == null && other.rolUsuarioPK != null) || (this.rolUsuarioPK != null && !this.rolUsuarioPK.equals(other.rolUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.RolUsuario[ rolUsuarioPK=" + rolUsuarioPK + " ]";
    }
    
}
