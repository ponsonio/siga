package com.b2mind.siga.bo;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.jpa.LogEvento;

@Stateless
public class LoggerBO {
	
    @EJB
    private com.b2mind.siga.session.LogEventoFacade ejblog;
    
    boolean escribirconsola = true;
    boolean escribirbd = false;
    
    static String WARNING = "WARNING"; 
    
    static String ERROR = "ERROR";
    
    static String FATAL = "FATALITY";
    
    static String DEBUG = "DEBUG";
    
    static String INFO = "INFO";
    
    static String EVENTO = "EVENTO";
    
    

    public void insertarLogEVENTO(String nombreClase, String mensaje , String mensajeDetallado,
    		String referencia, String usuario, String operacion){
		    	insertarLog(LoggerBO.EVENTO ,  nombreClase, mensaje ,  mensajeDetallado, 
		    			referencia,usuario,  operacion);
    }
    
    public void insertarLogINFO(String nombreClase, String mensaje , String mensajeDetallado,
    		String referencia, String usuario, String operacion){
		    	insertarLog(LoggerBO.INFO ,  nombreClase, mensaje ,  mensajeDetallado, 
		    			referencia,usuario,  operacion);
    }
    
    public void insertarLogDEBUG(String nombreClase, String mensaje , String mensajeDetallado,
    		String referencia, String usuario, String operacion){
		    	insertarLog(LoggerBO.DEBUG ,  nombreClase, mensaje ,  mensajeDetallado, 
		    			referencia,usuario,  operacion);
    }
    
    public void insertarLogFATAL(String nombreClase, String mensaje , String mensajeDetallado,
    		String referencia, String usuario, String operacion){
		    	insertarLog(LoggerBO.FATAL ,  nombreClase, mensaje ,  mensajeDetallado, 
		    			referencia,usuario,  operacion);
    }
    
    public void insertarLogWARNING(String nombreClase, String mensaje , String mensajeDetallado,
    		String referencia, String usuario, String operacion){
		    	insertarLog(LoggerBO.WARNING ,  nombreClase, mensaje ,  mensajeDetallado, 
		    			referencia,usuario,  operacion);
    }
    
    public void insertarLogERROR(String nombreClase, String mensaje , String mensajeDetallado,
    		String referencia, String usuario, String operacion){
		    	insertarLog(LoggerBO.ERROR ,  nombreClase, mensaje ,  mensajeDetallado, 
		    			referencia,usuario,  operacion);
    }
    
    private void escribirConsola(String nombreClase, String mensaje , String mensajeDetallado,
    		String referencia, String severidad , String usuario, String operacion){

    	System.out.println("********* Log SIGA ***************");
    	System.out.println("severidad:"+severidad);
    	System.out.println("usuario:"+usuario);
    	System.out.println("nombreClase:"+nombreClase);
    	System.out.println("mensaje:"+mensaje);
    	System.out.println("mensajeDetallado:"+mensajeDetallado);
    	System.out.println("referencia:"+referencia);
    	System.out.println("operacion:"+operacion);
    	System.out.println("********* Log SIGA ***************");

    }
    
    
    public void insertarLog(String severidad , String nombreClase, String mensaje , String mensajeDetallado,
    		String referencia, String usuario, String operacion){
    	try{
    		if(escribirconsola)  escribirConsola(nombreClase, mensaje , mensajeDetallado, 
    				referencia,  severidad , usuario , operacion);
    		
    		 if (escribirbd){
        		LogEvento e = new LogEvento();
        		e.setFecha(new Date());
        		e.setClase(nombreClase);
        		e.setMensaje(mensaje);
        		e.setMensajeDetallado(mensajeDetallado);
        		e.setSeveridad(severidad);
        		e.setUsuario(usuario);
        		e.setReferencia(referencia);
        		ejblog.create(e);
    		}
    		
    	}catch(Exception e){
    		System.out.println("Ocurrio un error escribiendo el log " + e.getMessage());
    		e.printStackTrace();
    		escribirConsola(nombreClase, mensaje , mensajeDetallado, 
    				referencia,  severidad , usuario, operacion);
    	}
    	
    }
    
    
    
    
    

}
