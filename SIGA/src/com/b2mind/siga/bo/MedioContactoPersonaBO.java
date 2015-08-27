package com.b2mind.siga.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.jpa.PersonaMedioContacto;

@Stateless
public class MedioContactoPersonaBO {
	
	
	
    @EJB
    private com.b2mind.siga.session.PersonaMedioContactoFacade ejbPersonaMedioContacto;
    
	public List<PersonaMedioContacto> obtenerPersonaMedioContactoPersona(long idPersona) {
		return ejbPersonaMedioContacto.obtenerMedioContactoPersona(idPersona);		
	}

}
