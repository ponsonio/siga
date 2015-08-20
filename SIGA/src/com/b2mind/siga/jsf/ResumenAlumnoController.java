package com.b2mind.siga.jsf;

import com.b2mind.siga.jpa.ResumenAlumno;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.JsfUtil.PersistAction;
import com.b2mind.siga.session.ResumenAlumnoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "resumenAlumnoController")
@SessionScoped
public class ResumenAlumnoController implements Serializable {

    @EJB
    private com.b2mind.siga.session.ResumenAlumnoFacade ejbFacade;
    private List<ResumenAlumno> items = null;
    private ResumenAlumno selected;

    public ResumenAlumnoController() {
    }

    public ResumenAlumno getSelected() {
        return selected;
    }

    public void setSelected(ResumenAlumno selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getResumenAlumnoPK().setIdPeriodoAcademico(selected.getPeriodoAcademico().getIdPeriodoAcademico());
        selected.getResumenAlumnoPK().setIdPersona(selected.getAlumno().getIdPersona());
    }

    protected void initializeEmbeddableKey() {
        selected.setResumenAlumnoPK(new com.b2mind.siga.jpa.ResumenAlumnoPK());
    }

    private ResumenAlumnoFacade getFacade() {
        return ejbFacade;
    }

    public ResumenAlumno prepareCreate() {
        selected = new ResumenAlumno();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ResumenAlumnoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ResumenAlumnoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ResumenAlumnoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ResumenAlumno> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<ResumenAlumno> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ResumenAlumno> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ResumenAlumno.class)
    public static class ResumenAlumnoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ResumenAlumnoController controller = (ResumenAlumnoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "resumenAlumnoController");
            return controller.getFacade().find(getKey(value));
        }

        com.b2mind.siga.jpa.ResumenAlumnoPK getKey(String value) {
            com.b2mind.siga.jpa.ResumenAlumnoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.b2mind.siga.jpa.ResumenAlumnoPK();
            key.setIdPeriodoAcademico(Long.parseLong(values[0]));
            key.setIdPersona(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(com.b2mind.siga.jpa.ResumenAlumnoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPeriodoAcademico());
            sb.append(SEPARATOR);
            sb.append(value.getIdPersona());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ResumenAlumno) {
                ResumenAlumno o = (ResumenAlumno) object;
                return getStringKey(o.getResumenAlumnoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ResumenAlumno.class.getName()});
                return null;
            }
        }

    }

}
