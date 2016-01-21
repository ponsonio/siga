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
import com.b2mind.siga.jpa.PeriodoAcademico;
import com.b2mind.siga.jpa.Persona;
import com.b2mind.siga.jpa.PersonalAdministrativo;
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
    
    @EJB
    private com.b2mind.siga.bo.PeriodoAcademicoBO   ejbPeriodoAcademico;
    
    
	private Usuario usuario;
	
	private Colegio colegio;

	private Persona persona;

	private Alumno alumno;
	
	private PersonalAdministrativo personalAdministrativo ;

	private ResumenAlumno resumenAlumno;

	private PeriodoAcademico periodoAcademico;

	private PeriodoAcademico subPeriodoAcademico;

	private Collection<Rol> collectionRoles;

	private String username;

	private String password;

	private long idColegio;
	
	private  boolean  admin = false ;
 
    
	public LoginController() {
    }


	public String getUsername() {
		return username;
	}

	/**
	 * Realiza el email
	 * @param event
	 */
    public String login() {
        //RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        
	        try{
	        	usuario = ejbLogin.login(this.password, this.username , this.idColegio) ;
	        	loggedIn = true;
	            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", username);
	            cargarDatosPrincipales() ;
	            ejbLog.insertarLogINFO(this.getClass().getName(), 
	            		"Inicio de sesiòn", "["+this.password+ "][" +this.username + "][" + this.idColegio+ "]" 
	            		, null, this.username, "Login" ) ;
	            return "principal.xthml";
	            
	        }catch(LoginException e){ 
	            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique el usuario, colegio y/o contraseña " ,"Error de inicio de sesiòn");
	            ejbLog.insertarLogINFO(this.getClass().getName(), 
	            		"Error de inicio de sesiòn", "Verifique el usuario, colegio y/o contraseña "
	            		, null, this.username, "Login" ) ;
	            FacesContext.getCurrentInstance().addMessage(null, message);
	            return "login.xthml";
	        }catch (InconsistenciaDatosException e) {
	            ejbLog.insertarLogERROR(this.getClass().getName(), 
	            		"Error de inicio de sesiòn", e.getMessage()
	            		, null, this.username, "Login" ) ;
	            e.printStackTrace();
	            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error inesperado, nuestro equipo a sido notificado ", "Error de inicio de sesiòn");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	            return "login.xthml";
	        }catch (BaseDatosException  e) {
	            ejbLog.insertarLogERROR(this.getClass().getName(), 
	            		"Error de inicio de sesiòn", e.getMessage()
	            		, null, this.username, "Login" ) ;
	            e.printStackTrace();
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Ocurrio un error inesperado, nuestro equipo a sido notificado ","Error de inicio de sesiòn");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	            return "login.xthml";
			}catch (Exception  e) {
	            ejbLog.insertarLogERROR(this.getClass().getName(), 
	            		"Error de inicio de sesiòn", e.getMessage()
	            		, null, this.username, "Login" ) ;
	            e.printStackTrace();
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, " Ocurrio un error inesperado, nuestro equipo a sido notificado ","Error de inicio de sesiòn");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	            return "login.xthml";
			}

    }  
    
    
    
    /**
     * Carga los datos Principales
     * @throws InconsistenciaDatosException
     * @throws BaseDatosException
     */
    void cargarDatosPrincipales() throws InconsistenciaDatosException , BaseDatosException{
    	try{
       		persona = usuario.getIdPersona() ;
       		colegio = usuario.getIdColegio();
            //persona.getPersonaMedioContactoCollection();
            cargarRoles();
            cargarPeriodosAcademicos(colegio.getIdColegio());
            
            ejbLog.insertarLogINFO(this.getClass().getName(), 
            		"cargarDatosPrincipales", persona.toString() + colegio.toString() 
            		, null, this.username, "Cargar Datos Principales" ) ;
            
            if (this.hasRole(Rol.alumno)) {
	            alumno = persona.getAlumno();
	            cargarResumenAlumno();
	            ejbLog.insertarLogINFO(this.getClass().getName(), 
	            		"cargarDatosPrincipales", alumno.toString() + this.resumenAlumno.toString() 
	            		, null, this.username, "Cargar Datos Principales - Alumno" ) ;

            } else if (this.hasRole(Rol.personalAdministrativo)){
            	personalAdministrativo = persona.getPersonalAdministrativo();
                ejbLog.insertarLogINFO(this.getClass().getName(), 
	            		"cargarDatosPrincipales", personalAdministrativo.toString()  
	            		, null, this.username, "Cargar Datos Principales - PERSONAL ADMINISTRATIVO COLEGIO" ) ;
            }else if (this.hasRole(Rol.administradorSistema)){
            	this.admin = true;
                ejbLog.insertarLogINFO(this.getClass().getName(), 
	            		"cargarDatosPrincipales", null  
	            		, null, this.username, "Cargar Datos Principales - ADMINISTRADOR DE SISTEMA" ) ;
            }

    	}catch(InconsistenciaDatosException ex){
    		throw ex;
    	}catch(Exception e){
            ejbLog.insertarLogERROR(this.getClass().getName(), 
            		"Error cargando datos principales", e.getMessage()
            		, null, this.username, "cargarDatosPrincipales" ) ;
    		throw new InconsistenciaDatosException("Error Cargando Datos Principales : " + e.getMessage(), e);
    	}
    }
    
    public boolean hasRole(String nombreRol){
    	Iterator<Rol> it = collectionRoles.iterator() ; 
    	while (it.hasNext()){
    		Rol r = it.next();
    		if (r.getNombre().equals(nombreRol)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Carga los Roles
     * @throws InconsistenciaDatosException
     */
    public void cargarRoles() throws InconsistenciaDatosException{
    	try{
			if (usuario.getRolCollection().size() == 0) throw new InconsistenciaDatosException("Error Cargando Roles, usuario sin roles "); ;
			collectionRoles = usuario.getRolCollection() ;
			
            ejbLog.insertarLogINFO(this.getClass().getName(), 
            		"Cargando Roles", collectionRoles.toString()
            		, null, this.username, "Cargar Roles" ) ;
            
    	}catch(Exception e){
    		throw new InconsistenciaDatosException("Error Cargando Roles : " + e.getMessage(), e);
    	}
    }
    

    
    /**
     * Public carga el periodo y subPerido Académico
     * @throws InconsistenciaDatosException
     */
    public void cargarPeriodosAcademicos(long idColegio) throws InconsistenciaDatosException {
    	try{
    		periodoAcademico  =  ejbPeriodoAcademico.obtenerPeriodoAcademicoEnCursoCalendario(idColegio);
    		subPeriodoAcademico =  ejbPeriodoAcademico.obtenerSubPeriodoAcademicoEnCursoCalendario(idColegio);
            ejbLog.insertarLogINFO(this.getClass().getName(), 
            		"Cargando Peridos Academicos", "Periodo:" +periodoAcademico.toString() + "Sub Periodo:" +subPeriodoAcademico.toString()
            		, null, this.username, "Cargar Periodo Academico" ) ;
    	}catch (Exception e) {    		
    		throw new InconsistenciaDatosException("Error Cargando Periodos Académicos : " + e.getMessage(), e);
		}  	
    }
    
    /**
     * Carga el resumen del alumno
     * @throws InconsistenciaDatosException
     */
    public void cargarResumenAlumno() throws InconsistenciaDatosException{
    	try{
    		if (alumno.getResumenAlumnoCollection() == null){
    			throw new InconsistenciaDatosException("Error Cargando Resumen , sin resumenes ");
    		}
	        Iterator<ResumenAlumno> it =  alumno.getResumenAlumnoCollection().iterator();
	        while (it.hasNext()){
	        	resumenAlumno = it.next();
	        }
            ejbLog.insertarLogINFO(this.getClass().getName(), 
            		"Cargando Resumen", resumenAlumno.toString()
            		, null, this.username, "Cargar Resumen" ) ;
            
    	}catch (Exception e) {
    		throw new InconsistenciaDatosException("Error Cargando Resumen : " + e.getMessage(), e);
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


	public PeriodoAcademico getPeriodoAcademico() {
		return periodoAcademico;
	}


	public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
		this.periodoAcademico = periodoAcademico;
	}


	public PeriodoAcademico getSubPeriodoAcademico() {
		return subPeriodoAcademico;
	}


	public void setSubPeriodoAcademico(PeriodoAcademico subPeriodoAcademico) {
		this.subPeriodoAcademico = subPeriodoAcademico;
	}


	public PersonalAdministrativo getPersonalAdministrativo() {
		return personalAdministrativo;
	}


	public void setPersonalAdministrativo(PersonalAdministrativo personalAdministrativo) {
		this.personalAdministrativo = personalAdministrativo;
	}


	public Colegio getColegio() {
		return colegio;
	}


	public void setColegio(Colegio colegio) {
		this.colegio = colegio;
	}


	public boolean isAdmin() {
		return admin;
	}



	
	
	
}
