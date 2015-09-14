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
public class PeriodoAcademicoSeccionCursoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION")
    private long idPeriodoAcademicoSeccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION_CURSO")
    private long idPeriodoAcademicoSeccionCurso;

    public PeriodoAcademicoSeccionCursoPK() {
    }

    public PeriodoAcademicoSeccionCursoPK(long idPeriodoAcademicoSeccion, long idPeriodoAcademicoSeccionCurso) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
        this.idPeriodoAcademicoSeccionCurso = idPeriodoAcademicoSeccionCurso;
    }

    public long getIdPeriodoAcademicoSeccion() {
        return idPeriodoAcademicoSeccion;
    }

    public void setIdPeriodoAcademicoSeccion(long idPeriodoAcademicoSeccion) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public long getIdPeriodoAcademicoSeccionCurso() {
        return idPeriodoAcademicoSeccionCurso;
    }

    public void setIdPeriodoAcademicoSeccionCurso(long idPeriodoAcademicoSeccionCurso) {
        this.idPeriodoAcademicoSeccionCurso = idPeriodoAcademicoSeccionCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPeriodoAcademicoSeccion;
        hash += (int) idPeriodoAcademicoSeccionCurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoAcademicoSeccionCursoPK)) {
            return false;
        }
        PeriodoAcademicoSeccionCursoPK other = (PeriodoAcademicoSeccionCursoPK) object;
        if (this.idPeriodoAcademicoSeccion != other.idPeriodoAcademicoSeccion) {
            return false;
        }
        if (this.idPeriodoAcademicoSeccionCurso != other.idPeriodoAcademicoSeccionCurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.PeriodoAcademicoSeccionCursoPK[ idPeriodoAcademicoSeccion=" + idPeriodoAcademicoSeccion + ", idPeriodoAcademicoSeccionCurso=" + idPeriodoAcademicoSeccionCurso + " ]";
    }
    
}
