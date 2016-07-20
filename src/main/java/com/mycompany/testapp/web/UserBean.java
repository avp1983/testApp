/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author www
 */
@Named
@SessionScoped
public class UserBean implements Serializable, IUserBean {

    private String name;
    private boolean inOperRole;
    private boolean inUserRole;
    private boolean guest;

    /*private final String currentcontent;
    private static final Map<String,String> NAV_RULES = new HashMap<String,String>();
    private static final String ERRORPAGE="/WEB-INF/error.html";
    static {
        NAV_RULES.put("oper", "/WEB-INF/oper/scroller.xhtml");
        NAV_RULES.put("user", "/WEB-INF/oper/scroller.xhtml");        
    }*/
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {

        // currentcontent = navigate(name);
    }

    public void navigation() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        checkCredentials(context);
        if (isInUserRole()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/user/scroller.xhtml");
            return;
        }
        if (isInOperRole()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/oper/scroller.xhtml");
            return;
        }
        
       throw new RuntimeException("navigation error");

    }

    public String getName() {
        return name;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        guest = true;
        inOperRole = false;
        inUserRole = false;

        return "login";
    }

    public boolean isInOperRole() {
        return inOperRole;
    }

    public boolean isInUserRole() {
        return inUserRole;
    }

    /* public String getCurrentcontent() {
        return currentcontent;
    }*/
    private void checkCredentials(ExternalContext context) {
        Principal principal = context.getUserPrincipal();
        name = principal.getName();
        inOperRole = context.isUserInRole("oper");
        inUserRole = context.isUserInRole("user");
    }

    public boolean getGuest() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Principal principal = context.getUserPrincipal();
        if (principal == null) {
            return true;
        } else {
            checkCredentials(context);
        }

        return false;
    }

}
