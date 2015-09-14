package com.b2mind.siga.jsf;

import com.b2mind.siga.jpa.ResumenAlumno;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.PaginationHelper;
import com.b2mind.siga.session.ResumenAlumnoFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "resumenAlumnoController")
@SessionScoped
public class ResumenAlumnoController implements Serializable {

    private ResumenAlumno current;
    private DataModel items = null;
    @EJB
    private com.b2mind.siga.session.ResumenAlumnoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ResumenAlumnoController() {
    }

    public ResumenAlumno getSelected() {
        if (current == null) {
            current = new ResumenAlumno();
            current.setResumenAlumnoPK(new com.b2mind.siga.jpa.ResumenAlumnoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ResumenAlumnoFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (ResumenAlumno) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ResumenAlumno();
        current.setResumenAlumnoPK(new com.b2mind.siga.jpa.ResumenAlumnoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getResumenAlumnoPK().setIdPeriodoAcademico(current.getPeriodoAcademico().getIdPeriodoAcademico());
            current.getResumenAlumnoPK().setIdPersona(current.getAlumno().getIdPersona());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ResumenAlumnoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ResumenAlumno) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getResumenAlumnoPK().setIdPeriodoAcademico(current.getPeriodoAcademico().getIdPeriodoAcademico());
            current.getResumenAlumnoPK().setIdPersona(current.getAlumno().getIdPersona());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ResumenAlumnoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ResumenAlumno) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ResumenAlumnoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
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
            return controller.ejbFacade.find(getKey(value));
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
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ResumenAlumno.class.getName());
            }
        }

    }

}
