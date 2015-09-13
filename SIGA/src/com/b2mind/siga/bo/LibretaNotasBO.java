package com.b2mind.siga.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.jpa.LibretaNotas;

@Stateless
public class LibretaNotasBO {

	   @EJB
	    private com.b2mind.siga.session.LibretaNotasFacade ejbLibretaNotas;
	   
	/**
	 * Obtiene la libreta de notas de un alumno para Periodo
	 * @param idAlumno
	 * @param idPeriodo
	 * @return
	 */
	public LibretaNotas obtenerLibretaNotasAlumnoPeriodo(long idAlumno, long idPeriodo){
		return ejbLibretaNotas.obtenerLibretaNotasAlumnoPeriodo(idAlumno, idPeriodo) ;
	}
	
	
	/**
	 * Obtiene las libretas de notas de un alumno
	 * @param idAlumno
	 * @return
	 */
	public 	List<LibretaNotas> obtenerLibretaNotasAlumno(long idAlumno){
		return ejbLibretaNotas.obtenerLibretaNotasAlumno(idAlumno);
	}
	
}
