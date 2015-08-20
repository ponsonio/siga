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
public class CuentaBancoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_BANCO")
    private short idBanco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COLEGIO")
    private long idColegio;

    public CuentaBancoPK() {
    }

    public CuentaBancoPK(short idBanco, long idColegio) {
        this.idBanco = idBanco;
        this.idColegio = idColegio;
    }

    public short getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(short idBanco) {
        this.idBanco = idBanco;
    }

    public long getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(long idColegio) {
        this.idColegio = idColegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idBanco;
        hash += (int) idColegio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaBancoPK)) {
            return false;
        }
        CuentaBancoPK other = (CuentaBancoPK) object;
        if (this.idBanco != other.idBanco) {
            return false;
        }
        if (this.idColegio != other.idColegio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.CuentaBancoPK[ idBanco=" + idBanco + ", idColegio=" + idColegio + " ]";
    }
    
}
