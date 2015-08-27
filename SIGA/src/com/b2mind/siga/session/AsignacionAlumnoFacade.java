/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.Asignacion;
import com.b2mind.siga.jpa.AsignacionAlumno;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class AsignacionAlumnoFacade extends AbstractFacade<AsignacionAlumno> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignacionAlumnoFacade() {
        super(AsignacionAlumno.class);
    }
    
	public List<Asignacion> obtenerAsignacionesAlumnoSubPeriodo(long idPersona,
			long idSubPeriodo) {
		return (List<Asignacion>)em.createQuery("select a from Asignacion a , AsignacionAlumno aa " +
				"where aa.alumno.idPersona = :idPersona " +
				"and aa.asignacion.idAsignacion = a.idAsignacion " +
				"and aa.periodoAcademico.idPeriodoAcademico = :idSubPeriodo")
        		.setParameter("idPersona", idPersona)
				.setParameter("idSubPeriodo",idSubPeriodo)
        		.getResultList();
	}
    
}
