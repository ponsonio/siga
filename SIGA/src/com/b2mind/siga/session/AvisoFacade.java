/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.Aviso;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class AvisoFacade extends AbstractFacade<Aviso> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

	static String general = "1";
	
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvisoFacade() {
        super(Aviso.class);
    }
    
    
	
	public List<Aviso> getAvisosAlumno(long idSeccion , long idSubPeriodoAcademico ) {
		
		/**
		return (List<Aviso>)em.createQuery(
				" select aviso from Aviso aviso	where aviso.id in " +
				"(select a.id from Aviso a where  a.general = :general and a.periodoAcademico.idPeriodoAcademico = :idPeriodoAcademico ) " +
				" or aviso.id in " +
				"(select av.id from AvisoSeccion avisoSeccion , Aviso av where avisoSeccion.seccion.idSeccion = :idSeccion and av.idAviso = avisoSeccion.aviso.idAviso)"
				)
        		.setParameter("idSeccion", idSeccion)
        		.setParameter("idPeriodoAcademico", idSubPeriodoAcademico)
        		.setParameter("general", AvisoFacade.general)        		
        		.getResultList();
			**/
		System.out.println("idSeccion:"+idSeccion);
		System.out.println("idSubPeriodoAcademico:"+idSubPeriodoAcademico);
		
		return (List<Aviso>)em.createQuery(
				" select aviso from Aviso aviso ").getResultList();
	}
    
}
