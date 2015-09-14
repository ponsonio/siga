package com.b2mind.siga.jsf;

import com.b2mind.siga.jpa.PersonaMedioContacto;
import com.b2mind.siga.jsf.util.JsfUtil;
import com.b2mind.siga.jsf.util.PaginationHelper;
import com.b2mind.siga.session.PersonaMedioContactoFacade;

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

@ManagedBean(name = "personaMedioContactoController")
@SessionScoped
public class PersonaMedioContactoController implements Serializable {

    private PersonaMedioContacto current;
    private DataModel items = null;
    @EJB
    private com.b2mind.siga.session.PersonaMedioContactoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PersonaMedioContactoController() {
    }

    public PersonaMedioContacto getSelected() {
        if (current == null) {
            current = new PersonaMedioContacto();
            current.setPersonaMedioContactoPK(new com.b2mind.siga.jpa.PersonaMedioContactoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private PersonaMedioContactoFacade getFacade() {
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
        current = (PersonaMedioContacto) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new PersonaMedioContacto();
        current.setPersonaMedioContactoPK(new com.b2mind.siga.jpa.PersonaMedioContactoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getPersonaMedioContactoPK().setIdPersona(current.getPersona().getIdPersona());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PersonaMedioContactoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (PersonaMedioContacto) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getPersonaMedioContactoPK().setIdPersona(current.getPersona().getIdPersona());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PersonaMedioContactoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (PersonaMedioContacto) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PersonaMedioContactoDeleted"));
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
            return controller.ejbFacade.find(getKey(value));
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
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PersonaMedioContacto.class.getName());
            }
        }

    }

}
