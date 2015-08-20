/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author koki
 */
@Entity
@Table(name = "MATRICULA_ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MatriculaAlumno.findAll", query = "SELECT m FROM MatriculaAlumno m"),
    @NamedQuery(name = "MatriculaAlumno.findByIdMatricula", query = "SELECT m FROM MatriculaAlumno m WHERE m.idMatricula = :idMatricula"),
    @NamedQuery(name = "MatriculaAlumno.findByUsuario", query = "SELECT m FROM MatriculaAlumno m WHERE m.usuario = :usuario"),
    @NamedQuery(name = "MatriculaAlumno.findByFecha", query = "SELECT m FROM MatriculaAlumno m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MatriculaAlumno.findByIdPeriodoAcademicoSeccion", query = "SELECT m FROM MatriculaAlumno m WHERE m.idPeriodoAcademicoSeccion = :idPeriodoAcademicoSeccion")})
public class MatriculaAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MATRICULA")
    private Long idMatricula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODO_ACADEMICO_SECCION")
    private long idPeriodoAcademicoSeccion;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private Alumno idAlumno;

    public MatriculaAlumno() {
    }

    public MatriculaAlumno(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public MatriculaAlumno(Long idMatricula, String usuario, Date fecha, long idPeriodoAcademicoSeccion) {
        this.idMatricula = idMatricula;
        this.usuario = usuario;
        this.fecha = fecha;
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getIdPeriodoAcademicoSeccion() {
        return idPeriodoAcademicoSeccion;
    }

    public void setIdPeriodoAcademicoSeccion(long idPeriodoAcademicoSeccion) {
        this.idPeriodoAcademicoSeccion = idPeriodoAcademicoSeccion;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatriculaAlumno)) {
            return false;
        }
        MatriculaAlumno other = (MatriculaAlumno) object;
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.b2mind.siga.jpa.MatriculaAlumno[ idMatricula=" + idMatricula + " ]";
    }
    
}
