/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;

import com.mycompany.testapp.ejb.MailsFacadeLocal;
import com.mycompany.testapp.entities.Mails;
import com.mycompany.testapp.utils.DocumentsOnChangeEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author www
 */
@Named(value = "userMailsScroller")
@SessionScoped
public class UserMailsScroller implements Serializable {

    private List<Mails> mails;
    @Inject
    private MailsFacadeLocal mailsManager;
    
   
    
   
    
    /**
     * Creates a new instance of UserMailScroller
     */
    public UserMailsScroller() {
    }
    
    public void refresh(){
         mails = mailsManager.findAll();
         refreshView();
    }
     public static void refreshView() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse(); //Optional
    }
    
    
     public void onDataCahnge(@Observes DocumentsOnChangeEvent event) {
          mails = mailsManager.findAll();
          refreshView();
          
    }

    @PostConstruct
    public void init() {
        mails = mailsManager.findAll();
    }

    public List<Mails> getMails() {
        return mails;
    }
    
    
 

}
