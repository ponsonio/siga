package com.b2mind.siga.jsf;

import com.b2mind.siga.jpa.RolUsuario;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.JsfUtil.PersistAction;
import com.b2mind.siga.session.RolUsuarioFacade;

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

@ManagedBean(name = "rolUsuarioController")
@SessionScoped
public class RolUsuarioController implements Serializable {

    @EJB
    private com.b2mind.siga.session.RolUsuarioFacade ejbFacade;
    private List<RolUsuario> items = null;
    private RolUsuario selected;

    public RolUsuarioController() {
    }

    public RolUsuario getSelected() {
        return selected;
    }

    public void setSelected(RolUsuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setRolUsuarioPK(new com.b2mind.siga.jpa.RolUsuarioPK());
    }

    private RolUsuarioFacade getFacade() {
        return ejbFacade;
    }

    public RolUsuario prepareCreate() {
        selected = new RolUsuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RolUsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RolUsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RolUsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<RolUsuario> getItems() {
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

    public List<RolUsuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<RolUsuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = RolUsuario.class)
    public static class RolUsuarioControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolUsuarioController controller = (RolUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolUsuarioController");
            return controller.getFacade().find(getKey(value));
        }

        com.b2mind.siga.jpa.RolUsuarioPK getKey(String value) {
            com.b2mind.siga.jpa.RolUsuarioPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.b2mind.siga.jpa.RolUsuarioPK();
            key.setIdRol(Long.parseLong(values[0]));
            key.setIdUsuario(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(com.b2mind.siga.jpa.RolUsuarioPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdRol());
            sb.append(SEPARATOR);
            sb.append(value.getIdUsuario());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof RolUsuario) {
                RolUsuario o = (RolUsuario) object;
                return getStringKey(o.getRolUsuarioPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RolUsuario.class.getName()});
                return null;
            }
        }

    }

}
