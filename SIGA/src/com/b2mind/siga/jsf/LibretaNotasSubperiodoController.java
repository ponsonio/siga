package com.b2mind.siga.jsf;

import com.b2mind.siga.jpa.LibretaNotasSubperiodo;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.JsfUtil.PersistAction;
import com.b2mind.siga.session.LibretaNotasSubperiodoFacade;

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

@ManagedBean(name = "libretaNotasSubperiodoController")
@SessionScoped
public class LibretaNotasSubperiodoController implements Serializable {

    @EJB
    private com.b2mind.siga.session.LibretaNotasSubperiodoFacade ejbFacade;
    private List<LibretaNotasSubperiodo> items = null;
    private LibretaNotasSubperiodo selected;

    public LibretaNotasSubperiodoController() {
    }

    public LibretaNotasSubperiodo getSelected() {
        return selected;
    }

    public void setSelected(LibretaNotasSubperiodo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LibretaNotasSubperiodoFacade getFacade() {
        return ejbFacade;
    }

    public LibretaNotasSubperiodo prepareCreate() {
        selected = new LibretaNotasSubperiodo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LibretaNotasSubperiodoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LibretaNotasSubperiodoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LibretaNotasSubperiodoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LibretaNotasSubperiodo> getItems() {
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

    public List<LibretaNotasSubperiodo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LibretaNotasSubperiodo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = LibretaNotasSubperiodo.class)
    public static class LibretaNotasSubperiodoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LibretaNotasSubperiodoController controller = (LibretaNotasSubperiodoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "libretaNotasSubperiodoController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof LibretaNotasSubperiodo) {
                LibretaNotasSubperiodo o = (LibretaNotasSubperiodo) object;
                return getStringKey(o.getIdLibretaNotasSubperiodo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LibretaNotasSubperiodo.class.getName()});
                return null;
            }
        }

    }

}
