/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;

import com.mycompany.testapp.ejb.MailsFacadeLocal;
import com.mycompany.testapp.entities.Mails;
import com.mycompany.testapp.utils.DocStatus;
import com.mycompany.testapp.utils.DocumentsOnChangeEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.faces.component.html.HtmlDataTable;
import javax.inject.Inject;

/**
 *
 * @author www
 */
@Named(value = "operMailsScroller")
@SessionScoped
public class OperMailsScroller implements Serializable {

    /**
     * Creates a new instance of OperMailsScroller
     */
    public OperMailsScroller() {
    }
    private List<Mails> mails;
    @Inject
    private MailsFacadeLocal mailsManager;
    
  
    
   
   
   
    
    public void refresh(){
         mails = mailsManager.findByStatus(DocStatus.PROCESSING.getValue());
         
    }
  
    
     public void onDataCahnge(@Observes DocumentsOnChangeEvent event) {
          mails = mailsManager.findByStatus(DocStatus.PROCESSING.getValue());
         
          
    }

    @PostConstruct
    public void init() {
        mails = mailsManager.findByStatus(DocStatus.PROCESSING.getValue());
    }

    public List<Mails> getMails() {
        return mails;
    }

   
    
    
    
    
 
    
}
