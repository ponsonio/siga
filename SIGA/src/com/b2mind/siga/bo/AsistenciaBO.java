package com.b2mind.siga.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.jpa.Asistencia;

@Stateless
public class AsistenciaBO {
	
	   @EJB
	    private com.b2mind.siga.session.AsistenciaFacade ejbAsistencia;
	   
	   /**
	    * Obtiene las asistencias para un subperiodo
	    * @param idAlumno
	    * @param idSubPeriodoAcademico
	    * @return
	    */
	   public List<Asistencia> obtenerAsistenciaAlumnoSubPeriodo(long idAlumno, long idSubPeriodoAcademico) {
		   return ejbAsistencia.obtenerAsistenciaAlumnoSubPeriodo(idAlumno, idSubPeriodoAcademico);
	   }
	   
	   /**
	    * Obtiene las asistencias para un Periodo
	    * @param idAlumno
	    * @param idPeriodoAcademico
	    * @return
	    */
		public List<Asistencia> obtenerAsistenciaAlumnoPeriodo(long idAlumno, long idPeriodoAcademico) {
			return ejbAsistencia.obtenerAsistenciaAlumnoPeriodo(idAlumno, idPeriodoAcademico);
		}

			

}
