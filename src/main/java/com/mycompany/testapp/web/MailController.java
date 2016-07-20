/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;

import com.mycompany.testapp.ejb.MailsFacadeLocal;
import com.mycompany.testapp.ejb.StatusesFacadeLocal;
import com.mycompany.testapp.entities.Mails;
import com.mycompany.testapp.entities.StatusHystory;
import com.mycompany.testapp.entities.Statuses;
import com.mycompany.testapp.utils.DocumentsOnChangeEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author www
 */
@Named(value = "mailController")
@ConversationScoped
public class MailController implements Serializable {

    private String header;
    private String body;
    private Set<StatusHystory> statusesHistory;

    private Mails mail = new Mails();

    enum Action {
        VIEW, ADD, EDIT, DELETE, SEND
    }
    private Action action;

    @EJB
    private StatusesFacadeLocal statusManager;

    @EJB
    private MailsFacadeLocal mailsManager;

    @Inject
    private Conversation conversation;

    @Inject
    private Event<DocumentsOnChangeEvent> itemNotifier;

    /**
     * Creates a new instance of MailController
     */
    public MailController() {

    }

    public void delete(Mails item) {
        conversation.begin();
        mail = item;
        action = Action.DELETE;
        save();

    }

    public String view(Mails item) {
        //conversation.begin();
        mail = item;
        action = Action.VIEW;
        statusesHistory = mail.getStatusHystorySet();
        return "addMail";
    }

    public String edit(Mails item) {
        conversation.begin();
        mail = item;
        action = Action.EDIT;
        return "addMail";
    }

    public String add() {
        conversation.begin();
        mail = new Mails();
        action = Action.ADD;
        return "addMail";
    }

    public String save() {


        /*Mails mail = new Mails();
        mail.setHeader(header);
        mail.setBody("BODY");*/
        switch (action) {
            case ADD:
                //Statuses status = statusManager.find(1); 
                Statuses status = new Statuses();
                status.setStatusId(1);
                mail.setDataCreate(new Date());
                mail.setStatusId(status);
                mailsManager.create(mail);
                break;
            case EDIT:
                mailsManager.edit(mail);
                break;
            /*case VIEW:
                statusesHistory = mail.getStatusHystorySet();
                break;*/

            case DELETE:
                mailsManager.remove(mail);
                break;
            case SEND:
                break;

        }
        conversation.end();
        itemNotifier.fire(new DocumentsOnChangeEvent() {
        });
        return "userScroller";
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

    public Mails getMail() {
        return mail;
    }

    public void setMail(Mails mail) {
        this.mail = mail;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Set<StatusHystory> getStatusesHistory() {
        return statusesHistory;
    }

  
    

}
