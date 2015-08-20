/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "MATERIAL_SECCION_CURSO_PERIODO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialSeccionCursoPeriodo.findAll", query = "SELECT m FROM MaterialSeccionCursoPeriodo m"),
    @NamedQuery(name = "MaterialSeccionCursoPeriodo.findByIdPeriodoAcademicoSeccion", query = "SELECT m FROM MaterialSeccionCursoPeriodo m WHERE m.materialSeccionCursoPeriodoPK.idPeriodoAcademicoSeccion = :idPeriodoAcademicoSeccion"),
    @NamedQuery(name = "MaterialSeccionCursoPeriodo.findByIdPeriodoAcademicoSeccionCurso", query = "SELECT m FROM MaterialSeccionCursoPeriodo m WHERE m.materialSeccionCursoPeriodoPK.idPeriodoAcademicoSeccionCurso = :idPeriodoAcademicoSeccionCurso"),
    @NamedQuery(name = "MaterialSeccionCursoPeriodo.findByIdMaterial", query = "SELECT m FROM MaterialSeccionCursoPeriodo m WHERE m.materialSeccionCursoPeriodoPK.idMaterial = :idMaterial")})
public class MaterialSeccionCursoPeriodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaterialSeccionCursoPeriodoPK materialSeccionCursoPeriodoPK;
    @JoinColumn(name = "ID_MATERIAL", referencedColumnName = "ID_MATERIAL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Materiales materiales;

    public MaterialSeccionCursoPeriodo() {
    }

    public MaterialSeccionCursoPeriodo(MaterialSeccionCursoPeriodoPK materialSeccionCursoPeriodoPK) {
        this.materialSeccionCursoPeriodoPK = materialSeccionCursoPeriodoPK;
    }

    public MaterialSeccionCursoPeriodo(long idPeriodoAcademicoSeccion, long idPeriodoAcademicoSeccionCurso, long idMaterial) {
        this.materialSeccionCursoPeriodoPK = new MaterialSeccionCursoPeriodoPK(idPeriodoAcademicoSeccion, idPeriodoAcademicoSeccionCurso, idMaterial);
    }

    public MaterialSeccionCursoPeriodoPK getMaterialSeccionCursoPeriodoPK() {
        return materialSeccionCursoPeriodoPK;
    }

    public void setMaterialSeccionCursoPeriodoPK(MaterialSeccionCursoPeriodoPK materialSeccionCursoPeriodoPK) {
        this.materialSeccionCursoPeriodoPK = materialSeccionCursoPeriodoPK;
    }

    public Materiales getMateriales() {
        return materiales;
    }

    public void setMateriales(Materiales materiales) {
        this.materiales = materiales;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialSeccionCursoPeriodoPK != null ? materialSeccionCursoPeriodoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialSeccionCursoPeriodo)) {
            return false;
        }
        MaterialSeccionCursoPeriodo other = (MaterialSeccionCursoPeriodo) object;
        if ((this.materialSeccionCursoPeriodoPK == null && other.materialSeccionCursoPeriodoPK != null) || (this.materialSeccionCursoPeriodoPK != null && !this.materialSeccionCursoPeriodoPK.equals(other.materialSeccionCursoPeriodoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.MaterialSeccionCursoPeriodo[ materialSeccionCursoPeriodoPK=" + materialSeccionCursoPeriodoPK + " ]";
    }
    
}
