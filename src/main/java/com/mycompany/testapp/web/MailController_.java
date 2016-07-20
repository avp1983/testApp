/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;

import com.mycompany.testapp.ejb.MailsFacadeLocal;
import com.mycompany.testapp.ejb.StatusesFacadeLocal;
import com.mycompany.testapp.entities.Mails;
import com.mycompany.testapp.entities.Statuses;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

/**
 *
 * @author www
 */

@Model
public class MailController_ implements Serializable {
    private String header;
    private String body;
    @EJB
    private MailsFacadeLocal mailsManager;
    
    @EJB
    private StatusesFacadeLocal statusManager;
    
    /**
     * Creates a new instance of MailController
     */
    public MailController_() {
    }
    
    public String save(){
        Mails mail = new Mails();
        Statuses status = statusManager.find(1);
        
        
        
        status.setStatusId(1);
        mail.setHeader(header);
        mail.setBody("BODY");
        mail.setStatusId(status);
        mailsManager.create(mail);
        return  "login";
    }
    

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    
    
    
}
