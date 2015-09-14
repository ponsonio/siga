package com.b2mind.siga.bo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.b2mind.siga.exception.BaseDatosException;
import com.b2mind.siga.exception.InconsistenciaDatosException;
import com.b2mind.siga.jpa.PeriodoAcademico;

@Stateless
public class PeriodoAcademicoBO {
	
    @EJB
    private com.b2mind.siga.session.PeriodoAcademicoFacade ejbPeriodoAcademicoFacade;
    
    
	public PeriodoAcademico obtenerPeriodoAcademicoEnCursoCalendario(long idColegio) throws InconsistenciaDatosException, BaseDatosException{
		return ejbPeriodoAcademicoFacade.obtenerPeriodoAcademicoVigenteCalendario(idColegio);
	}
	
	
	public PeriodoAcademico obtenerSubPeriodoAcademicoEnCursoCalendario(long idColegio) throws InconsistenciaDatosException, BaseDatosException{
		return ejbPeriodoAcademicoFacade.obtenerSubPeriodoAcademicoVigenteCalendario(idColegio);
	}

}
