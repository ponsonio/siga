package com.b2mind.siga.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.exception.InconsistenciaDatosException;
import com.b2mind.siga.jpa.Aviso;

@Stateless
public class AvisoBO {

	
	
    @EJB
    private com.b2mind.siga.session.AvisoFacade ejbAvisoFacade;
	
	public  List<Aviso> buscarAvisosAlumno(long idSeccion, long idPeriodoAcademico) {
			return ejbAvisoFacade.getAvisosAlumno(
					idSeccion , idSeccion);
		
	}
	
}
