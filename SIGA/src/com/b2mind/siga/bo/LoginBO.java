package com.b2mind.siga.bo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.exception.InconsistenciaDatosException;
import com.b2mind.siga.exception.LoginException;
import com.b2mind.siga.jpa.Usuario;

@Stateless
public class LoginBO {

	
    @EJB
    private com.b2mind.siga.session.UsuarioFacade ejbFacade;
    
	public Usuario login(String password , String usuario, long idColegio	) 
				throws LoginException, InconsistenciaDatosException, BaseDatosException{

			Usuario u =  ejbFacade.obtenerUsuario(usuario, idColegio) ;
			
			if (u == null) throw new LoginException("No se encontro el usuario");
			if (u.getPassword().equals(password)) return u ;
			else {
				throw new LoginException("Contraseña incorecta");
			}
		
	} 

}
