package com.b2mind.siga.jsf;

import com.b2mind.siga.jpa.CuentaBanco;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.JsfUtil.PersistAction;
import com.b2mind.siga.session.CuentaBancoFacade;

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

@ManagedBean(name = "cuentaBancoController")
@SessionScoped
public class CuentaBancoController implements Serializable {

    @EJB
    private com.b2mind.siga.session.CuentaBancoFacade ejbFacade;
    private List<CuentaBanco> items = null;
    private CuentaBanco selected;

    public CuentaBancoController() {
    }

    public CuentaBanco getSelected() {
        return selected;
    }

    public void setSelected(CuentaBanco selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getCuentaBancoPK().setIdBanco(selected.getBanco().getIdBanco());
        selected.getCuentaBancoPK().setIdColegio(selected.getColegio().getIdColegio());
    }

    protected void initializeEmbeddableKey() {
        selected.setCuentaBancoPK(new com.b2mind.siga.jpa.CuentaBancoPK());
    }

    private CuentaBancoFacade getFacade() {
        return ejbFacade;
    }

    public CuentaBanco prepareCreate() {
        selected = new CuentaBanco();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CuentaBancoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CuentaBancoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CuentaBancoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CuentaBanco> getItems() {
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

    public List<CuentaBanco> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CuentaBanco> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CuentaBanco.class)
    public static class CuentaBancoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CuentaBancoController controller = (CuentaBancoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cuentaBancoController");
            return controller.getFacade().find(getKey(value));
        }

        com.b2mind.siga.jpa.CuentaBancoPK getKey(String value) {
            com.b2mind.siga.jpa.CuentaBancoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.b2mind.siga.jpa.CuentaBancoPK();
            key.setIdBanco(Short.parseShort(values[0]));
            key.setIdColegio(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(com.b2mind.siga.jpa.CuentaBancoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdBanco());
            sb.append(SEPARATOR);
            sb.append(value.getIdColegio());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CuentaBanco) {
                CuentaBanco o = (CuentaBanco) object;
                return getStringKey(o.getCuentaBancoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CuentaBanco.class.getName()});
                return null;
            }
        }

    }

}
