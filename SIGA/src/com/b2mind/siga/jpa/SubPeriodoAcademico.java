package com.b2mind.siga.jpa;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

public class SubPeriodoAcademico {
	
	long id_peridoAcademicoPadre ; 
	
	private PeriodoAcademico p ;
	
	public SubPeriodoAcademico(long idPeriodoAcademicoPadre, PeriodoAcademico subPeriodoAcademico){
        this.id_peridoAcademicoPadre = idPeriodoAcademicoPadre;
		this.p.setIdPeriodoAcademico(subPeriodoAcademico.getIdPeriodoAcademico());
        this.p.setEnCurso(subPeriodoAcademico.getEnCurso());
        this.p.setEtiqueta(subPeriodoAcademico.getEtiqueta());
        this.p.setFechaInicio(subPeriodoAcademico.getFechaInicio());
        this.p.setFechaFin(subPeriodoAcademico.getFechaFin());
	}

	public long getId_peridoAcademicoPadre() {
		return id_peridoAcademicoPadre;
	}

	public void setId_peridoAcademicoPadre(long id_peridoAcademicoPadre) {
		this.id_peridoAcademicoPadre = id_peridoAcademicoPadre;
	}
	
	
	/**
	  public Long getIdPeriodoAcademico() {
	        return idPeriodoAcademico;
	    }

	    public void setIdPeriodoAcademico(Long idPeriodoAcademico) {
	        this.idPeriodoAcademico = idPeriodoAcademico;
	    }

	    public Character getEnCurso() {
	        return enCurso;
	    }

	    public void setEnCurso(Character enCurso) {
	        this.enCurso = enCurso;
	    }

	    public String getEtiqueta() {
	        return etiqueta;
	    }

	    public void setEtiqueta(String etiqueta) {
	        this.etiqueta = etiqueta;
	    }

	    public Date getFechaInicio() {
	        return fechaInicio;
	    }

	    public void setFechaInicio(Date fechaInicio) {
	        this.fechaInicio = fechaInicio;
	    }

	    public Date getFechaFin() {
	        return fechaFin;
	    }

	    public void setFechaFin(Date fechaFin) {
	        this.fechaFin = fechaFin;
	    }

	    @XmlTransient
	    public Collection<PeriodoAcademico> getPeriodoAcademicoCollection() {
	        return periodoAcademicoCollection;
	    }

	    public void setPeriodoAcademicoCollection(Collection<PeriodoAcademico> periodoAcademicoCollection) {
	        this.periodoAcademicoCollection = periodoAcademicoCollection;
	    }

	    public PeriodoAcademico getIdParent() {
	        return idParent;
	    }

	    public void setIdParent(PeriodoAcademico idParent) {
	        this.idParent = idParent;
	    }

	    public Colegio getIdColegio() {
	        return this.p.getIdColegio();
	    }

	   **/ 
	    @XmlTransient
	    public Collection<LibretaNotasSubperiodo> getLibretaNotasSubperiodoCollection() {
	        return this.p.getLibretaNotasSubperiodoCollection();
	    }



	    @XmlTransient
	    public Collection<LibretaNotas> getLibretaNotasCollection() {
	        return this.p.getLibretaNotasCollection();
	    }



	    @XmlTransient
	    public Collection<ReciboPension> getReciboPensionCollection() {
	        return this.getReciboPensionCollection();
	    }


	    @XmlTransient
	    public Collection<ResumenAlumno> getResumenAlumnoCollection() {
	        return this.p.getResumenAlumnoCollection();
	    }



	    @XmlTransient
	    public Collection<AsignacionAlumno> getAsignacionAlumnoCollection() {
	        return  this.p.getAsignacionAlumnoCollection();
	    }



	    @XmlTransient
	    public Collection<SubPeriodoAcademicoSeccionCurso> getSubPeriodoAcademicoSeccionCursoCollection() {
	        return this.p.getSubPeriodoAcademicoSeccionCursoCollection();
	    }



	    @XmlTransient
	    public Collection<Asistencia> getAsistenciaCollection() {
	        return this.p.getAsistenciaCollection();
	    }



	    @XmlTransient
	    public Collection<Aviso> getAvisoCollection() {
	        return this.p.getAvisoCollection();
	    }




	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof PeriodoAcademico)) {
	            return false;
	        }
	        PeriodoAcademico other = (PeriodoAcademico) object;
	        if ((this.id_peridoAcademicoPadre == null && other.id_peridoAcademicoPadre != null) || (this.id_peridoAcademicoPadre != null && !this.id_peridoAcademicoPadre.equals(other.id_peridoAcademicoPadre))) {
	            return false;
	        }
	        return true;
	    }

	    
	
	
	



}
