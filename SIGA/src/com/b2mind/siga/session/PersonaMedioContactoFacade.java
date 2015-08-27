/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.Asignacion;
import com.b2mind.siga.jpa.PersonaMedioContacto;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author koki
 */
@Stateless
public class PersonaMedioContactoFacade extends AbstractFacade<PersonaMedioContacto> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaMedioContactoFacade() {
        super(PersonaMedioContacto.class);
    }
    
    public List<PersonaMedioContacto> obtenerMedioContactoPersona(long idPersona){
		return (List<PersonaMedioContacto>)em.createQuery("select pmc from PersonaMedioContacto pmc ")
				//"where pmc.personaMedioContactoPK.idPersona = :idPersona " )
        		//.setParameter("idPersona", idPersona)
        		.getResultList();
    }
    
}
