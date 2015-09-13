package com.b2mind.siga.bo;

import java.util.List;

import javax.ejb.EJB;

import com.b2mind.siga.jpa.Alumno;
import com.b2mind.siga.jpa.Colegio;

public class PersonaBO {

	   @EJB
	    private com.b2mind.siga.session.PersonaFacade ejbPersona;
	   
	   
	   /**
	    * Obtiene los dependientes de una persona
	    * @param idpersona
	    * @return
	    */
		public List<Alumno> obtenerDependientesPersona(long idpersona){
			return ejbPersona.obtenerDependientesPersona(idpersona);	
		}
}
