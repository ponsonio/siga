/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.Alumno;
import com.b2mind.siga.jpa.ResumenAlumno;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class AlumnoFacade extends AbstractFacade<Alumno> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnoFacade() {
        super(Alumno.class);
    }
    

	
	/**
	 * Obtiene el resumen de un alumno para de un periodo
	 * @param idPersona
	 * @param idPeriodo
	 * @return
	 */
	public ResumenAlumno obtenerResumenPeriodo(long idPersona, long idPeriodo){
		return (ResumenAlumno)em.createQuery("select r from ResumenAlumno r where r.idPeriodoAcademico = :idPeriodo and r.alumno.idPersona = :idPersona ")
        		.setParameter("idpersona", idPersona).getSingleResult();
		
	}
    
}
