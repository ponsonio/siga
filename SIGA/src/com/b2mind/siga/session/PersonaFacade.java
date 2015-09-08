/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.Alumno;
import com.b2mind.siga.jpa.Persona;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    
    /**
     * Obtiene los dependientes de un apoderado
     * @param idpersona
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Alumno> obtenerDependientesPersona(long idpersona){
		return (List<Alumno>)em.createQuery("select ap.alumnos from Apoderado ap where ap.idPersona = :idpersona")
        		.setParameter("idpersona", idpersona).getResultList();
	}
    
}
