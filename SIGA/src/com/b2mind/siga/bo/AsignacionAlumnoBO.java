package com.b2mind.siga.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.jpa.Asignacion;

@Stateless
public class AsignacionAlumnoBO {
	
    @EJB
    private com.b2mind.siga.session.AsignacionAlumnoFacade ejbAsignacionAlumnoFacade;
	
	public List<Asignacion>  obtenerAsignacionesAlumnoSubPeriodo(long idPersona,
			long idSubPeriodo) {
		
		return ejbAsignacionAlumnoFacade.obtenerAsignacionesAlumnoSubPeriodo(idPersona, idSubPeriodo) ;
		
	}

}
