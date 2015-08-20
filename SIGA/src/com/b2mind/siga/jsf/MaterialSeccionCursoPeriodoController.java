package com.b2mind.siga.jsf;

import com.b2mind.siga.jpa.MaterialSeccionCursoPeriodo;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.JsfUtil.PersistAction;
import com.b2mind.siga.session.MaterialSeccionCursoPeriodoFacade;

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

@ManagedBean(name = "materialSeccionCursoPeriodoController")
@SessionScoped
public class MaterialSeccionCursoPeriodoController implements Serializable {

    @EJB
    private com.b2mind.siga.session.MaterialSeccionCursoPeriodoFacade ejbFacade;
    private List<MaterialSeccionCursoPeriodo> items = null;
    private MaterialSeccionCursoPeriodo selected;

    public MaterialSeccionCursoPeriodoController() {
    }

    public MaterialSeccionCursoPeriodo getSelected() {
        return selected;
    }

    public void setSelected(MaterialSeccionCursoPeriodo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getMaterialSeccionCursoPeriodoPK().setIdMaterial(selected.getMateriales().getIdMaterial());
    }

    protected void initializeEmbeddableKey() {
        selected.setMaterialSeccionCursoPeriodoPK(new com.b2mind.siga.jpa.MaterialSeccionCursoPeriodoPK());
    }

    private MaterialSeccionCursoPeriodoFacade getFacade() {
        return ejbFacade;
    }

    public MaterialSeccionCursoPeriodo prepareCreate() {
        selected = new MaterialSeccionCursoPeriodo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MaterialSeccionCursoPeriodoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MaterialSeccionCursoPeriodoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MaterialSeccionCursoPeriodoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MaterialSeccionCursoPeriodo> getItems() {
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

    public List<MaterialSeccionCursoPeriodo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MaterialSeccionCursoPeriodo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MaterialSeccionCursoPeriodo.class)
    public static class MaterialSeccionCursoPeriodoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MaterialSeccionCursoPeriodoController controller = (MaterialSeccionCursoPeriodoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "materialSeccionCursoPeriodoController");
            return controller.getFacade().find(getKey(value));
        }

        com.b2mind.siga.jpa.MaterialSeccionCursoPeriodoPK getKey(String value) {
            com.b2mind.siga.jpa.MaterialSeccionCursoPeriodoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.b2mind.siga.jpa.MaterialSeccionCursoPeriodoPK();
            key.setIdPeriodoAcademicoSeccion(Long.parseLong(values[0]));
            key.setIdPeriodoAcademicoSeccionCurso(Long.parseLong(values[1]));
            key.setIdMaterial(Long.parseLong(values[2]));
            return key;
        }

        String getStringKey(com.b2mind.siga.jpa.MaterialSeccionCursoPeriodoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPeriodoAcademicoSeccion());
            sb.append(SEPARATOR);
            sb.append(value.getIdPeriodoAcademicoSeccionCurso());
            sb.append(SEPARATOR);
            sb.append(value.getIdMaterial());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MaterialSeccionCursoPeriodo) {
                MaterialSeccionCursoPeriodo o = (MaterialSeccionCursoPeriodo) object;
                return getStringKey(o.getMaterialSeccionCursoPeriodoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MaterialSeccionCursoPeriodo.class.getName()});
                return null;
            }
        }

    }

}
