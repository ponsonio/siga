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
public class MaterialSeccionCursoPeriodoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION")
    private long idPeriodoAcademicoSeccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION_CURSO")
    private long idPeriodoAcademicoSeccionCurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MATERIAL")
    private long idMaterial;

    public MaterialSeccionCursoPeriodoPK() {
    }

    public MaterialSeccionCursoPeriodoPK(long idPeriodoAcademicoSeccion, long idPeriodoAcademicoSeccionCurso, long idMaterial) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
        this.idPeriodoAcademicoSeccionCurso = idPeriodoAcademicoSeccionCurso;
        this.idMaterial = idMaterial;
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

    public long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(long idMaterial) {
        this.idMaterial = idMaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPeriodoAcademicoSeccion;
        hash += (int) idPeriodoAcademicoSeccionCurso;
        hash += (int) idMaterial;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialSeccionCursoPeriodoPK)) {
            return false;
        }
        MaterialSeccionCursoPeriodoPK other = (MaterialSeccionCursoPeriodoPK) object;
        if (this.idPeriodoAcademicoSeccion != other.idPeriodoAcademicoSeccion) {
            return false;
        }
        if (this.idPeriodoAcademicoSeccionCurso != other.idPeriodoAcademicoSeccionCurso) {
            return false;
        }
        if (this.idMaterial != other.idMaterial) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.MaterialSeccionCursoPeriodoPK[ idPeriodoAcademicoSeccion=" + idPeriodoAcademicoSeccion + ", idPeriodoAcademicoSeccionCurso=" + idPeriodoAcademicoSeccionCurso + ", idMaterial=" + idMaterial + " ]";
    }
    
}
