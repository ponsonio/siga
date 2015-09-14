/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.exception.InconsistenciaDatosException;
import com.b2mind.siga.jpa.PeriodoAcademico;

import java.util.Date;

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
public class PeriodoAcademicoFacade extends AbstractFacade<PeriodoAcademico> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodoAcademicoFacade() {
        super(PeriodoAcademico.class);
    }
    
    /**
     * Obtiene el perido academico en curso segun calendario
     * @param idColegio
     * @return
     */
    public PeriodoAcademico obtenerPeriodoAcademicoVigenteCalendario(long idColegio) throws InconsistenciaDatosException , BaseDatosException{
    	try{
    		return (PeriodoAcademico)em.createQuery(				
					" SELECT p FROM PeriodoAcademico p WHERE CURRENT_DATE BETWEEN p.fechaInicio AND p.fechaFin  and p.idColegio.idColegio = :idColegio  and p.idParent IS NULL  ")
	        		.setParameter("idColegio", idColegio)
	        		.getSingleResult();
		    	}catch(NoResultException nre) {
		    		throw new InconsistenciaDatosException("No se encuentra Periodo Académico : "+ nre.getMessage() , nre);
		    	}catch (NonUniqueResultException e) {
					throw new InconsistenciaDatosException("Múltiples periodos : " + e.getMessage(), e);
				}catch (Exception e) {
					throw new BaseDatosException("Error de Base de Datos :"+e.getMessage(),e);
				}
    }
    
    /**
     * Obtiene el subperido academico en curso segun calendario
     * @param idColegio
     * @return
     */
    public PeriodoAcademico obtenerSubPeriodoAcademicoVigenteCalendario(long idColegio) throws InconsistenciaDatosException , BaseDatosException{
		try{
		    	return (PeriodoAcademico)em.createQuery(				
						" SELECT p FROM PeriodoAcademico p WHERE CURRENT_DATE BETWEEN p.fechaInicio AND p.fechaFin and p.idColegio.idColegio = :idColegio and p.idParent IS NOT NULL  ")
		        		.setParameter("idColegio", idColegio)
		        		.getSingleResult();
		    }catch(NoResultException nre) {
	    		throw new InconsistenciaDatosException("No se encuentra Sub Periodo Académico : "+ nre.getMessage() , nre);
	    	}catch (NonUniqueResultException e) {
				throw new InconsistenciaDatosException("Múltiples Sub periodos : " + e.getMessage(), e);
			}catch (Exception e) {
				throw new BaseDatosException("Error de Base de Datos :"+e.getMessage(),e);
			}
    }
}
