package edu.alenkin.HomeLibUpd.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@ManagedBean
@SessionScoped
public class SearchController implements Serializable {
    private SearchType searchType;
    private Map<String, SearchType> searchList = new HashMap<>();

    public SearchController() {
        ResourceBundle bundle = ResourceBundle.getBundle("msg", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        searchList.put(bundle.getString("author"), SearchType.AUTHOR);
        searchList.put(bundle.getString("title"), SearchType.TITLE);
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public Map<String, SearchType> getSearchList() {
        return searchList;
    }
}
