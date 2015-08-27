package com.b2mind.siga.view;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.b2mind.siga.jpa.Asignacion;

@ManagedBean(name = "asignacionAlumnoControllerSiga")
@SessionScoped
public class AsignacionAlumnoControllerSiga {
	
	@EJB
    private com.b2mind.siga.bo.AsignacionAlumnoBO  ejbAsignacionAlumnoBO ;
    
	@ManagedProperty(value="#{loginController}")
	LoginController loginController ;
	
	
	private  List<Asignacion>  asignacionLista ;


	public List<Asignacion> getAsignacionLista() {
		return asignacionLista;
	}


	public void setAsignacionLista(List<Asignacion> asignacionLista) {
		this.asignacionLista = asignacionLista;
	}
	
	public List<Asignacion> obtenerAsignacionesAlumno(){
		asignacionLista = ejbAsignacionAlumnoBO.obtenerAsignacionesAlumnoSubPeriodo(loginController.getResumenAlumno().getAlumno().getIdPersona()
				, loginController.getResumenAlumno().getPeriodoAcademico().getIdPeriodoAcademico()) ;
		return asignacionLista;
	}

}
