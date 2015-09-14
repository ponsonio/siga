/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.ReciboPension;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class ReciboPensionFacade extends AbstractFacade<ReciboPension> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReciboPensionFacade() {
        super(ReciboPension.class);
    }
    
    
	
	/* *
	 * Busca los recibos de los dependientes de la persona que esten pendientes
	 * (non-Javadoc)
	 * @see org.domain.colegium.dao.IReciboPensionDAO#buscarPagosPendientesDependientes(long)
	 */
	public List<ReciboPension> buscarRecibosPendientesDependientes(long idPersona) {
		return (List<ReciboPension>)em.createQuery("	select r from ReciboPension r where r.fechaPago is null and r.alumno.idPersona	in ( select a.idPersona from  Apoderado ap inner join ap.alumnos a 	where ap.idPersona = :id ) ")
        		.setParameter("id", idPersona).getResultList();
	}

	
	/* *
	 * Busca los recibos de los dependientes de la persona 
	 * (non-Javadoc)
	 * @see org.domain.colegium.dao.IReciboPensionDAO#buscarPagosPendientesDependientes(long)
	 */


	public List<ReciboPension> buscarRecibosDependientes(long idPersona) {
		return (List<ReciboPension>)em.createQuery("	select r from ReciboPension r where r.alumno.idPersona	in ( select a.idPersona from  Apoderado ap inner join ap.alumnos a 	where ap.idPersona = :id ) ")
        		.setParameter("id", idPersona).getResultList();
	}

	
	/* *
	 * Busca los recibos de un alumno que esten pendientes
	 * (non-Javadoc)
	 * * @see org.domain.colegium.dao.IReciboPensionDAO#buscarPagosPendientesAlumno(long)
	 */

	public List<ReciboPension> buscarRecibosAlumno(	long idAlumno) {
		return (List<ReciboPension>)em.createQuery("select r from ReciboPension r where   r.alumno.idPersona = :id")
        		.setParameter("id", idAlumno).getResultList();
		}


	/* *
	 * Busca los recibos de un alumno que esten pendientes
	 * (non-Javadoc)
	 * * @see org.domain.colegium.dao.IReciboPensionDAO#buscarPagosPendientesAlumno(long)
	 */

	public List<ReciboPension> buscarRecibosPendientesAlumno(	long idAlumno) {
		return (List<ReciboPension>)em.createQuery("select r from ReciboPension r where r.fechaPago is null and r.alumno.idPersona = :id")
        		.setParameter("id", idAlumno).getResultList();
		}

    
}
