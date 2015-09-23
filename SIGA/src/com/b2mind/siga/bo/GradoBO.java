package com.b2mind.siga.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.jpa.Grado;

@Stateless
public class GradoBO {
	
	
	   @EJB
	    private com.b2mind.siga.session.GradoFacade ejbGrado;
	
	    public List<Grado> obtenerGradosColegio(long idColegio) throws  BaseDatosException{
	    	return ejbGrado.obtenerGradosColegio(idColegio);
	    }
	
	   public Grado crearGrado(Grado g) throws  BaseDatosException{
		   return ejbGrado.crearGrado(g);
	   } 
	   
	   public void  modificarGrado(Grado g) throws  BaseDatosException{
		    ejbGrado.edit(g);
	   } 
    
	   public void eliminarGrado(Grado g) throws  BaseDatosException{
		  ejbGrado.remove(g);
	   } 

}
