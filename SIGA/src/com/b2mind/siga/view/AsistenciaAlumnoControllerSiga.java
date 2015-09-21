package com.b2mind.siga.view;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.b2mind.siga.jpa.Asignacion;
import com.b2mind.siga.jpa.Asistencia;

@ManagedBean(name = "asistenciaAlumnoControllerSiga")
@SessionScoped
public class AsistenciaAlumnoControllerSiga {
	
	@EJB
    private com.b2mind.siga.bo.AsistenciaBO  ejbAsistenciaBO ;
    
	@ManagedProperty(value="#{loginController}")
	LoginController loginController ;
	
	
	private  List<Asistencia>  asistenciaLista ;

	
	public List<Asistencia> obtenerAsignacionesAlumno(){
		asistenciaLista = ejbAsistenciaBO.obtenerAsistenciaAlumnoPeriodo(loginController.getResumenAlumno().getAlumno().getIdPersona()
				, loginController.getResumenAlumno().getPeriodoAcademico().getIdPeriodoAcademico()) ;
		return asistenciaLista;
	}

	public List<Asistencia> getAsistenciaLista() {
		return asistenciaLista;
	}


	public void setAsistenciaLista(List<Asistencia> asistenciaLista) {
		this.asistenciaLista = asistenciaLista;
	}
	
	
	

}
