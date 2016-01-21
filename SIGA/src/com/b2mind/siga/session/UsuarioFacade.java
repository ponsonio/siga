/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.exception.InconsistenciaDatosException;
import com.b2mind.siga.jpa.Usuario;

import javax.ejb.EJB;
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
	
    @EJB
    private com.b2mind.siga.bo.LoggerBO   ejbLog;
	
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    /**
     * Busca un usuario , retorna null de no encontrarlo
     * @param usuario
     * @param idColegio
     * @return
     * @throws InconsistenciaDatosException
     * @throws BaseDatosException
     */
    public Usuario obtenerUsuario(String usuario, Long idColegio)  throws InconsistenciaDatosException , BaseDatosException {
    	try{
            System.out.println("1"+usuario+idColegio);
    		return (Usuario)em.createQuery("select u from Usuario u where u.bloqueado != :bloqueado "
    				+ " and u.usuario = :usuario and  u.idColegio.idColegio = :idColegio ")
            		.setParameter("usuario", usuario)
            		.setParameter("bloqueado", UsuarioFacade.bloqueado)
            		.setParameter("idColegio", idColegio).
            		getSingleResult();


    		//return this.find( new Long(1) );
    	}catch(NoResultException nre) {
            ejbLog.insertarLogERROR(this.getClass().getName(), 
            		"Error buscando usuario", nre.getMessage()
            		, null, usuario, "obtenerUsuario" ) ;

    		return null;
    	}catch (NonUniqueResultException e) {
            ejbLog.insertarLogERROR(this.getClass().getName(), 
            		"Error buscando usuario", e.getMessage()
            		, null, usuario, "obtenerUsuario" ) ;
			throw new InconsistenciaDatosException("Múltiples usuarios : " + e.getMessage(), e);
		}catch (Exception e) {
            ejbLog.insertarLogERROR(this.getClass().getName(), 
            		"Error buscando usuario", e.getMessage()
            		, null, usuario, "obtenerUsuario" ) ;
			throw new BaseDatosException("Error de Base de Datos :"+e.getMessage(),e);
		}
    }
    
}
