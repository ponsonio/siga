package com.b2mind.siga.view;


import com.b2mind.siga.bo.GradoBO;
import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.jpa.Grado;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.PaginationHelper;
import com.b2mind.siga.session.GradoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "gradoControllerSiga")
@SessionScoped
public class GradoControllerSiga implements Serializable {

    private Grado current;
    private DataModel items = null;
    @EJB
    //private com.b2mind.siga.session.GradoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    FacesMessage message = null;
    
    @EJB
    private com.b2mind.siga.bo.LoggerBO   ejbLog;
    
        
    @EJB
    private com.b2mind.siga.bo.GradoBO  ejbGradoBO ;
    
	
	@ManagedProperty(value="#{loginController}")
	LoginController loginController ;
	
	
	public List<Grado>  listarGradosUsuario(){
		   
		try{
			
			return ejbGradoBO.obtenerGradosColegio(loginController.getResumenAlumno().getPeriodoAcademico().getIdPeriodoAcademico());
		}catch (BaseDatosException e){
            ejbLog.insertarLogERROR(this.getClass().getName(), 
            		"Error listado de grados", e.getMessage()
            		, null, this.username, "Login" ) ;
            e.printStackTrace();
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Ocurrio un error inesperado, nuestro equipo a sido notificado ","Error de inicio de sesiòn");
            FacesContext.getCurrentInstance().addMessage(null, message);
			
		}
	} 
	
	
}
