package com.b2mind.siga.view;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.b2mind.siga.jpa.PersonaMedioContacto;

@ManagedBean(name = "personaMedioContactoControllerSiga")
@SessionScoped
public class PersonaMedioContactoControllerSiga {
	
	@EJB
    private com.b2mind.siga.bo.MedioContactoPersonaBO  ejbMedioContactoPersonaBO ;
    
	@ManagedProperty(value="#{loginController}")
	LoginController loginController ;

    
	List<PersonaMedioContacto> listPersonaMedioContacto ;
	
	
    public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
	
	public List<PersonaMedioContacto> obtenerPersonaMedioContactoPersona() {
		 listPersonaMedioContacto = ejbMedioContactoPersonaBO.obtenerPersonaMedioContactoPersona(loginController.getPersona().getIdPersona());		
		 System.out.println("listPersonaMedioContacto.size:"+listPersonaMedioContacto.size());
		 return listPersonaMedioContacto;
	}

	public List<PersonaMedioContacto> getListPersonaMedioContacto() {
		return listPersonaMedioContacto;
	}

	public void setListPersonaMedioContacto(List<PersonaMedioContacto> listPersonaMedioContacto) {
		this.listPersonaMedioContacto = listPersonaMedioContacto;
	}
	
	

}
