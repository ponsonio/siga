package com.b2mind.siga.jsf;

import com.b2mind.siga.jpa.PeriodoAcademicoSeccionCurso;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.PaginationHelper;
import com.b2mind.siga.session.PeriodoAcademicoSeccionCursoFacade;

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

@ManagedBean(name = "periodoAcademicoSeccionCursoController")
@SessionScoped
public class PeriodoAcademicoSeccionCursoController implements Serializable {

    private PeriodoAcademicoSeccionCurso current;
    private DataModel items = null;
    @EJB
    private com.b2mind.siga.session.PeriodoAcademicoSeccionCursoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PeriodoAcademicoSeccionCursoController() {
    }

    public PeriodoAcademicoSeccionCurso getSelected() {
        if (current == null) {
            current = new PeriodoAcademicoSeccionCurso();
            current.setPeriodoAcademicoSeccionCursoPK(new com.b2mind.siga.jpa.PeriodoAcademicoSeccionCursoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private PeriodoAcademicoSeccionCursoFacade getFacade() {
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
        current = (PeriodoAcademicoSeccionCurso) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new PeriodoAcademicoSeccionCurso();
        current.setPeriodoAcademicoSeccionCursoPK(new com.b2mind.siga.jpa.PeriodoAcademicoSeccionCursoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getPeriodoAcademicoSeccionCursoPK().setIdPeriodoAcademicoSeccion(current.getPeriodoAcademicoSeccion().getIdPeriodoAcademicoSeccion());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeriodoAcademicoSeccionCursoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (PeriodoAcademicoSeccionCurso) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getPeriodoAcademicoSeccionCursoPK().setIdPeriodoAcademicoSeccion(current.getPeriodoAcademicoSeccion().getIdPeriodoAcademicoSeccion());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeriodoAcademicoSeccionCursoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PeriodoAcademicoSeccionCurso) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeriodoAcademicoSeccionCursoDeleted"));
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

    @FacesConverter(forClass = PeriodoAcademicoSeccionCurso.class)
    public static class PeriodoAcademicoSeccionCursoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeriodoAcademicoSeccionCursoController controller = (PeriodoAcademicoSeccionCursoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "periodoAcademicoSeccionCursoController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.b2mind.siga.jpa.PeriodoAcademicoSeccionCursoPK getKey(String value) {
            com.b2mind.siga.jpa.PeriodoAcademicoSeccionCursoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.b2mind.siga.jpa.PeriodoAcademicoSeccionCursoPK();
            key.setIdPeriodoAcademicoSeccion(Long.parseLong(values[0]));
            key.setIdPeriodoAcademicoSeccionCurso(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(com.b2mind.siga.jpa.PeriodoAcademicoSeccionCursoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPeriodoAcademicoSeccion());
            sb.append(SEPARATOR);
            sb.append(value.getIdPeriodoAcademicoSeccionCurso());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PeriodoAcademicoSeccionCurso) {
                PeriodoAcademicoSeccionCurso o = (PeriodoAcademicoSeccionCurso) object;
                return getStringKey(o.getPeriodoAcademicoSeccionCursoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PeriodoAcademicoSeccionCurso.class.getName());
            }
        }

    }

}
