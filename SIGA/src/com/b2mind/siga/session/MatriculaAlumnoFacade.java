/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.MatriculaAlumno;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class MatriculaAlumnoFacade extends AbstractFacade<MatriculaAlumno> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatriculaAlumnoFacade() {
        super(MatriculaAlumno.class);
    }
    
    
	@SuppressWarnings("unchecked")
	public List<PeriodoAcademicoSeccionCurso> obtenerMatriculaAlumno(long idAlumno,
			long idPeriodoAcademico) {
		//log.info("obteniendo matricula para idAlumno:"
			//+idAlumno+" idPeriodoAcademico:"+idPeriodoAcademico);
		
		return (List<PeriodoAcademicoSeccionCurso> )em.createQuery(
				"select ma.periodoAcademicoSeccion.periodoAcademicoSeccionCursos " +
				"from MatriculaAlumno ma " +
				"where ma.alumno.idPersona = :idAlumno " +
				"and ma.periodoAcademicoSeccion.periodoAcademico.idPeriodoAcademico = :idPeriodoAcademico ")
        		.setParameter("idAlumno", idAlumno)
				.setParameter("idPeriodoAcademico", idPeriodoAcademico)
				.getResultList();
	}

    
}
