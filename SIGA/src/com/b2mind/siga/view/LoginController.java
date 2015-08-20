package com.b2mind.siga.view;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
import org.primefaces.context.RequestContext;

import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.exception.InconsistenciaDatosException;
import com.b2mind.siga.exception.LoginException;
import com.b2mind.siga.jpa.Alumno;
import com.b2mind.siga.jpa.Colegio;
import com.b2mind.siga.jpa.Persona;
import com.b2mind.siga.jpa.ResumenAlumno;
import com.b2mind.siga.jpa.Rol;
import com.b2mind.siga.jpa.Usuario;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {
	
	
	
    @EJB
    private com.b2mind.siga.bo.LoginBO   ejbLogin;
    
    @EJB
    private com.b2mind.siga.bo.LoggerBO   ejbLog;
    
    @EJB
    private com.b2mind.siga.bo.ColegioBO   ejbColegio;
    

    
    private Usuario usuario ;
    
   private Persona persona ;
   
   private Alumno alumno ;
   
   private ResumenAlumno resumenAlumno;
   
   private Collection<Rol> collectionRoles ;
   
	private String username;
    
    private String password;
    
    private long idColegio ;
 
    
	public LoginController() {
    }


	public String getUsername() {
		return username;
	}


    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        
	        try{
	        	
	        	ejbLog.insertarLogDEBUG(this.getClass().getName(),
	        			"verificando usuario", "["+this.password+ "][" +this.username + "][" + this.idColegio+ "]" 
	        					, null, null, "login"); 
	        	
	        	usuario = ejbLogin.login(this.password, this.username , this.idColegio) ;
	        	loggedIn = true;
	            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", username);

	            context.addCallbackParam("loggedIn", loggedIn);
	            //collectionRoles = usuario.getRolCollection() ;
	            
	           // System.out.println("roles :" + collectionRoles.toString());
	            
	            cargarDatosPrincipales() ;
	            System.out.println("LOGUEADO!!");
	            
	            
	            System.out.println("LOGUEADO!!");
	            
	        }catch(LoginException e){ 
	            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique el usuario, colegio y/o contraseña " ,"Error de inicio de sesiòn");
	            ejbLog.insertarLogINFO(this.getClass().getName(), 
	            		"Error de inicio de sesiòn", "Verifique el usuario, colegio y/o contraseña "
	            		, null, this.username, "Login" ) ;
	            FacesContext.getCurrentInstance().addMessage(null, message);	            
	        }catch (InconsistenciaDatosException e) {
	            ejbLog.insertarLogERROR(this.getClass().getName(), 
	            		"Error de inicio de sesiòn", e.getMessage()
	            		, null, this.username, "Login" ) ;
	            e.printStackTrace();
	            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error inesperado, nuestro equipo a sido notificado ", "Error de inicio de sesiòn");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	        }catch (BaseDatosException  e) {
	            ejbLog.insertarLogERROR(this.getClass().getName(), 
	            		"Error de inicio de sesiòn", e.getMessage()
	            		, null, this.username, "Login" ) ;
	            e.printStackTrace();
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Ocurrio un error inesperado, nuestro equipo a sido notificado ","Error de inicio de sesiòn");
	            FacesContext.getCurrentInstance().addMessage(null, message);
			}

    }   
    
    void cargarDatosPrincipales(){
    	try{
    		usuario.getRolCollection().size();
    		collectionRoles = usuario.getRolCollection() ;
    		System.out.println("collectionRoles:"+collectionRoles);		
            persona = usuario.getIdPersona() ;
            //System.out.println("persona:"+persona.toString());
            alumno = persona.getAlumno();
            
            //System.out.println("alumno:"+alumno.toString());
            
            //System.out.println("resumenes : "+alumno.getResumenAlumnoCollection().size());
            
            Iterator<ResumenAlumno> it =  alumno.getResumenAlumnoCollection().iterator();
            while (it.hasNext()){
            	resumenAlumno = it.next();
            	System.out.println("resumenAlumno:"+resumenAlumno.toString());
            }

    	}catch(Exception e){
            ejbLog.insertarLogERROR(this.getClass().getName(), 
            		"Error cargando datos", e.getMessage()
            		, null, this.username, "Login-carga datos" ) ;
            e.printStackTrace();
    	}

        
    }
    
	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public long getIdColegio() {
		return idColegio;
	}


	public void setIdColegio(long idColegio) {
		this.idColegio = idColegio;
	}


	public List<Colegio> listaColegios() {
		return ejbColegio.obtenerListaColegios();
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public Alumno getAlumno() {
		return alumno;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public ResumenAlumno getResumenAlumno() {
		return resumenAlumno;
	}


	public void setResumenAlumno(ResumenAlumno resumenAlumno) {
		this.resumenAlumno = resumenAlumno;
	}
	
	
	
}
