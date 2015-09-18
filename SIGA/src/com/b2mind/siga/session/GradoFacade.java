/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.jpa.Grado;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class GradoFacade extends AbstractFacade<Grado> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GradoFacade() {
        super(Grado.class);
    }
    
    /**
     * 
     * @param idColegio
     * @return
     * @throws BaseDatosException
     */
    public List<Grado> obtenerGradosColegio(long idColegio) throws  BaseDatosException{
    	try{
		return (List<Grado>)em.createQuery(
				" select g from Grado g  where g.idColegio.id = :idColegio ")
        		.setParameter("idColegio", idColegio)
        		.getResultList();
    	}catch (Exception e) {
			throw new BaseDatosException("Error de Base de Datos :"+e.getMessage(),e);
		}
    }
    
    /**
     * 
     * @param g
     * @return
     * @throws BaseDatosException
     */
    public Grado crearGrado(Grado g) throws  BaseDatosException{
    	try{
    		em.persist(g); 
		 return g;
    	}catch (Exception e) {
			throw new BaseDatosException("Error de Base de Datos :"+e.getMessage(),e);
		}
    }
    
    
    /**
     * 
     * @param g
     * @return
     * @throws BaseDatosException
     */
    public Grado modificarGrado(Grado g) throws  BaseDatosException{
    	try{
    		em.persist(g); 
		 return g;
    	}catch (Exception e) {
			throw new BaseDatosException("Error de Base de Datos :"+e.getMessage(),e);
		}
    }
    
}
