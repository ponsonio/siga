/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.exception.InconsistenciaDatosException;
import com.b2mind.siga.jpa.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
	
	static char bloqueado = '1' ;
	
	
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario obtenerUsuario(String usuario, Long idColegio)  throws InconsistenciaDatosException , BaseDatosException {
    	try{
    		/**
    		return (Usuario)em.createQuery("select u from Usuario u where u.bloqueado = :bloqueado "
    				+ " and u.usuario = :usuario and  u.idColegio.idColegio = :idColegio ")
            		.setParameter("usuario", usuario)
            		.setParameter("bloqueado", UsuarioFacade.bloqueado)
            		.setParameter("idColegio", idColegio).
            		getSingleResult();
    		**/
    		return this.find( new Long(1) );
    	}catch(NoResultException nre) {
    		return null;
    	}catch (NonUniqueResultException e) {
			throw new InconsistenciaDatosException("Múltiples usuarios : " + e.getMessage(), e);
		}catch (Exception e) {
			throw new BaseDatosException("Error de Base de Datos :"+e.getMessage(),e);
		}
    }
    
}
