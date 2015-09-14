/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.Materiales;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class MaterialesFacade extends AbstractFacade<Materiales> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterialesFacade() {
        super(Materiales.class);
    }
    
    
    /**
     * Retorna nulo!!
     * @param idAlumno
     * @param idPeriodoAcademico
     * @return
     */
	public List<Materiales> obtenerMaterialesAlumnoCiclo(long idAlumno,
			long idPeriodoAcademico) {
		// TODO Auto-generated method stub
		/*return (List<Materiales>)entityManager.createQuery("select libreta from LibretaNotas libreta where libreta.alumno.idPersona = :idAlumno")
        		.setParameter("idAlumno", idAlumno).getResultList();*/
		return null;
	}

    
}
