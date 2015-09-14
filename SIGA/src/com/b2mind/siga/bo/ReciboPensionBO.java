package com.b2mind.siga.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.jpa.ReciboPension;

@Stateless
public class ReciboPensionBO {

	   @EJB
	    private com.b2mind.siga.session.ReciboPensionFacade ejbReciboPension;
	
	public List<ReciboPension> buscarRecibosPendientesDependientes(long idPersona) {
		return ejbReciboPension.buscarRecibosDependientes(idPersona);
	}
	

		
	/**
	 * Busca los recibos de un alumno	
	 * @param idAlumno
	 * @return
	 */
	public List<ReciboPension> buscarRecibosAlumno(	long idAlumno) {
		return ejbReciboPension.buscarRecibosAlumno(idAlumno);
	}
	
	/**
	 * Busca los recibos pendientes de un alumno
	 * @param idAlumno
	 * @return
	 */
	public List<ReciboPension> buscarRecibosPendientesAlumno(	long idAlumno) {
		return ejbReciboPension.buscarRecibosPendientesAlumno(idAlumno);
	}
	
}
