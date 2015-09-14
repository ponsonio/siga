/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.Asistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class AsistenciaFacade extends AbstractFacade<Asistencia> {
	
	static String asistio = "0";
	
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaFacade() {
        super(Asistencia.class);
    }
    
    /**
     * Obtiene las asistencia de un alumno en un Sub Periodo
     * @param idAlumno
     * @param idSubPeriodoAcademico
     * @return
     */
    public List<Asistencia> obtenerAsistenciaAlumnoSubPeriodo(long idAlumno, long idSubPeriodoAcademico) {
		//return (List<Asistencia>)entityManager.createQuery("select a from Asistencia a")
		return (List<Asistencia>)em.createQuery("select a from Asistencia a where a.alumno.idPersona = :idalumno and a.asistio = :asistio and a.periodoAcademico.idPeriodoAcademico = :idperiodo")
        		.setParameter("idperiodo", idSubPeriodoAcademico)
				.setParameter("asistio", this.asistio)
        		.setParameter("idalumno", idAlumno)
        		.getResultList();
		
	}
	
	/**
	 * Obtiene la asistencia de alumno para un periodo academico
	 * @param idAlumno
	 * @param idPeriodoAcademico
	 * @return
	 */
	public List<Asistencia> obtenerAsistenciaAlumnoPeriodo(long idAlumno, long idPeriodoAcademico) {
		//return (List<Asistencia>)entityManager.createQuery("select a from Asistencia a")
		return (List<Asistencia>)em.createQuery("select a from Asistencia a where a.asistio = :asistio and a.alumno.idPersona = :idalumno  and a.periodoAcademicoSeccionCurso.periodoAcademicoSeccion.periodoAcademico.idPeriodoAcademico = :idperiodo")
        		.setParameter("idperiodo", idPeriodoAcademico)
				.setParameter("asistio", this.asistio)
        		.setParameter("idalumno", idAlumno)
        		.getResultList();
		
	}

    
}
