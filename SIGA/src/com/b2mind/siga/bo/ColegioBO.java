package com.b2mind.siga.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.jpa.Colegio;

@Stateless
public class ColegioBO {

	   @EJB
	    private com.b2mind.siga.session.ColegioFacade ejbColegio;
	   
	   public List<Colegio> obtenerListaColegios(){
		   return ejbColegio.findAll();
	   }
	   
	   
	   
}
