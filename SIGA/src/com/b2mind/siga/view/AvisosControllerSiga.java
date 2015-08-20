package com.b2mind.siga.view;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.b2mind.siga.jpa.Aviso;

@ManagedBean(name = "avisoControllerSiga")
@SessionScoped
public class AvisosControllerSiga {

	
	
	@EJB
    private com.b2mind.siga.bo.AvisoBO  ejbAvisoBO ;
    
	@ManagedProperty(value="#{loginController}")
	LoginController loginController ;

    
	
    public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	private List<Aviso> avisosAlumno ;
    
    public List<Aviso> obtenerAvisosAlumno(){
    	return avisosAlumno = ejbAvisoBO.buscarAvisosAlumno(loginController.getResumenAlumno().getIdSeccion().getIdSeccion()
    			, loginController.getResumenAlumno().getPeriodoAcademico().getIdPeriodoAcademico() ) ;
    
    	
    }

	public List<Aviso> getAvisosAlumno() {
		return avisosAlumno;
	}

	public void setAvisosAlumno(List<Aviso> avisosAlumno) {
		this.avisosAlumno = avisosAlumno;
	}
    
    
}
