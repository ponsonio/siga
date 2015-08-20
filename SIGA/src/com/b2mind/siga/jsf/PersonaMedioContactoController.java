package com.b2mind.siga.jsf;

import com.b2mind.siga.jpa.PersonaMedioContacto;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.JsfUtil.PersistAction;
import com.b2mind.siga.session.PersonaMedioContactoFacade;

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

@ManagedBean(name = "personaMedioContactoController")
@SessionScoped
public class PersonaMedioContactoController implements Serializable {

    @EJB
    private com.b2mind.siga.session.PersonaMedioContactoFacade ejbFacade;
    private List<PersonaMedioContacto> items = null;
    private PersonaMedioContacto selected;

    public PersonaMedioContactoController() {
    }

    public PersonaMedioContacto getSelected() {
        return selected;
    }

    public void setSelected(PersonaMedioContacto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getPersonaMedioContactoPK().setIdPersona(selected.getPersona().getIdPersona());
    }

    protected void initializeEmbeddableKey() {
        selected.setPersonaMedioContactoPK(new com.b2mind.siga.jpa.PersonaMedioContactoPK());
    }

    private PersonaMedioContactoFacade getFacade() {
        return ejbFacade;
    }

    public PersonaMedioContacto prepareCreate() {
        selected = new PersonaMedioContacto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonaMedioContactoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonaMedioContactoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonaMedioContactoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PersonaMedioContacto> getItems() {
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

    public List<PersonaMedioContacto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PersonaMedioContacto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PersonaMedioContacto.class)
    public static class PersonaMedioContactoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaMedioContactoController controller = (PersonaMedioContactoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaMedioContactoController");
            return controller.getFacade().find(getKey(value));
        }

        com.b2mind.siga.jpa.PersonaMedioContactoPK getKey(String value) {
            com.b2mind.siga.jpa.PersonaMedioContactoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.b2mind.siga.jpa.PersonaMedioContactoPK();
            key.setIdPersona(Long.parseLong(values[0]));
            key.setIdMedioContacto(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.b2mind.siga.jpa.PersonaMedioContactoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPersona());
            sb.append(SEPARATOR);
            sb.append(value.getIdMedioContacto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PersonaMedioContacto) {
                PersonaMedioContacto o = (PersonaMedioContacto) object;
                return getStringKey(o.getPersonaMedioContactoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonaMedioContacto.class.getName()});
                return null;
            }
        }

    }

}
