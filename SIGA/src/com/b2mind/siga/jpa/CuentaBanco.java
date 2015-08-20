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
@Table(name = "CUENTA_BANCO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaBanco.findAll", query = "SELECT c FROM CuentaBanco c"),
    @NamedQuery(name = "CuentaBanco.findByIdBanco", query = "SELECT c FROM CuentaBanco c WHERE c.cuentaBancoPK.idBanco = :idBanco"),
    @NamedQuery(name = "CuentaBanco.findByIdColegio", query = "SELECT c FROM CuentaBanco c WHERE c.cuentaBancoPK.idColegio = :idColegio"),
    @NamedQuery(name = "CuentaBanco.findByNumero", query = "SELECT c FROM CuentaBanco c WHERE c.numero = :numero")})
public class CuentaBanco implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuentaBancoPK cuentaBancoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NUMERO")
    private String numero;
    @JoinColumn(name = "ID_COLEGIO", referencedColumnName = "ID_COLEGIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Colegio colegio;
    @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_BANCO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Banco banco;

    public CuentaBanco() {
    }

    public CuentaBanco(CuentaBancoPK cuentaBancoPK) {
        this.cuentaBancoPK = cuentaBancoPK;
    }

    public CuentaBanco(CuentaBancoPK cuentaBancoPK, String numero) {
        this.cuentaBancoPK = cuentaBancoPK;
        this.numero = numero;
    }

    public CuentaBanco(short idBanco, long idColegio) {
        this.cuentaBancoPK = new CuentaBancoPK(idBanco, idColegio);
    }

    public CuentaBancoPK getCuentaBancoPK() {
        return cuentaBancoPK;
    }

    public void setCuentaBancoPK(CuentaBancoPK cuentaBancoPK) {
        this.cuentaBancoPK = cuentaBancoPK;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaBancoPK != null ? cuentaBancoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaBanco)) {
            return false;
        }
        CuentaBanco other = (CuentaBanco) object;
        if ((this.cuentaBancoPK == null && other.cuentaBancoPK != null) || (this.cuentaBancoPK != null && !this.cuentaBancoPK.equals(other.cuentaBancoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.CuentaBanco[ cuentaBancoPK=" + cuentaBancoPK + " ]";
    }
    
}
