/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2mind.siga.session;

import com.b2mind.siga.jpa.SubPeriodoAcademicoSeccionCurso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author koki
 */
@Stateless
public class SubPeriodoAcademicoSeccionCursoFacade extends AbstractFacade<SubPeriodoAcademicoSeccionCurso> {
    @PersistenceContext(unitName = "CrudSIGAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubPeriodoAcademicoSeccionCursoFacade() {
        super(SubPeriodoAcademicoSeccionCurso.class);
    }
    
}
