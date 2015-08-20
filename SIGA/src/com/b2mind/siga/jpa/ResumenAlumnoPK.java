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
public class ResumenAlumnoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO")
    private long idPeriodoAcademico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSONA")
    private long idPersona;

    public ResumenAlumnoPK() {
    }

    public ResumenAlumnoPK(long idPeriodoAcademico, long idPersona) {
        this.idPeriodoAcademico = idPeriodoAcademico;
        this.idPersona = idPersona;
    }

    public long getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(long idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPeriodoAcademico;
        hash += (int) idPersona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResumenAlumnoPK)) {
            return false;
        }
        ResumenAlumnoPK other = (ResumenAlumnoPK) object;
        if (this.idPeriodoAcademico != other.idPeriodoAcademico) {
            return false;
        }
        if (this.idPersona != other.idPersona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.ResumenAlumnoPK[ idPeriodoAcademico=" + idPeriodoAcademico + ", idPersona=" + idPersona + " ]";
    }
    
}
