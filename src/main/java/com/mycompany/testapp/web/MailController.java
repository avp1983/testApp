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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author www
 */
@Named(value = "mailController")
@RequestScoped
public class MailController {

    private String header;
    private String body;
    @EJB
    private StatusesFacadeLocal statusManager;

    @EJB
    private MailsFacadeLocal mailsManager;

    /**
     * Creates a new instance of MailController
     */
    public MailController() {
    }

    public String save() {
        Statuses status = statusManager.find(1);
        Mails mail = new Mails();
        mail.setHeader(header);
        mail.setBody("BODY");
        mail.setStatusId(status);
        mailsManager.create(mail);

        return "/index.xhtml";
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