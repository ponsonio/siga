/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.LibretaNotas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class LibretaNotasFacade extends AbstractFacade<LibretaNotas> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibretaNotasFacade() {
        super(LibretaNotas.class);
    }
    
    /**
     * Obtiene la libreta de notas de un alumno para un periodo
     * @param idAlumno
     * @param idPeriodo
     * @return
     */
	public LibretaNotas obtenerLibretaNotasAlumnoPeriodo(long idAlumno, long idPeriodo){
		return (LibretaNotas)em.createQuery("select libreta from LibretaNotas libreta where libreta.alumno.idPersona = :idAlumno and libreta.periodoAcademico.idPeriodoAcademico = :idPeriodo")
        		.setParameter("idAlumno", idAlumno)
        		.setParameter("idPeriodo", idPeriodo).getSingleResult();		
	}
	
	/**
	 * Obtiene las libretas de notas de un alumno	
	 * @param idAlumno
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LibretaNotas> obtenerLibretaNotasAlumno(long idAlumno){
		return (List<LibretaNotas>)em.createQuery("select libreta from LibretaNotas libreta where libreta.alumno.idPersona = :idAlumno")
        		.setParameter("idAlumno", idAlumno).getResultList();	
	};
    
}
