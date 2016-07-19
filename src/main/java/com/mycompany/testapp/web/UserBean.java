/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;

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

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Principal principal = context.getUserPrincipal();
        name = principal.getName();
        inOperRole = context.isUserInRole("oper");
        inUserRole = context.isUserInRole("user");
    }

    public String getName() {
        return name;
    }

    

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public boolean isInOperRole() {
        return inOperRole;
    }

   

    public boolean isInUserRole() {
        return inUserRole;
    }

  

}
