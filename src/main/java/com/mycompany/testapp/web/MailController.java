/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;

import com.mycompany.testapp.ejb.MailsFacadeLocal;
import com.mycompany.testapp.ejb.StatusHystoryFacadeLocal;
import com.mycompany.testapp.ejb.StatusesFacadeLocal;
import com.mycompany.testapp.entities.Documents;
import com.mycompany.testapp.entities.Mails;
import com.mycompany.testapp.entities.StatusHystory;
import com.mycompany.testapp.entities.Statuses;
import com.mycompany.testapp.utils.DocStatus;
import com.mycompany.testapp.utils.DocumentsOnChangeEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private List<StatusHystory> statusesHistory;

    private Mails mail = new Mails();
    private String _back;

    enum Action {
        VIEW, ADD, EDIT, DELETE, SEND
    }
    private Action action;

    @EJB
    private StatusesFacadeLocal statusManager;

    @EJB
    private MailsFacadeLocal mailsManager;

    @EJB
    private StatusHystoryFacadeLocal hystoryManager;

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

    private void setStatus(Mails item, int st) {
        Statuses status = new Statuses();
        status.setStatusId(st);
        item.setStatusId(status);
        mailsManager.edit(item);
        hystoryManager.createHystory(item, status);
        itemNotifier.fire(new DocumentsOnChangeEvent() {
        });
    }

    public String send(Mails item, String back, String forward) {
        setStatus(item, DocStatus.PROCESSING.getValue());

        return back;
    }

    public String accept(Mails item, String back, String forward) {
        setStatus(item, DocStatus.ACCEPTED.getValue());
        return back;

    }

    public String deny(Mails item, String back, String forward) {
        setStatus(item, DocStatus.DENIED.getValue());
        return back;

    }

    public String view(Mails item, String back, String forward) {

        conversation.begin();  //?????? 
        this._back = back;
        mail = item;
        action = Action.VIEW;
        statusesHistory = hystoryManager.getHystory(item.getDocumentId());
        //????
        return forward;
    }

    public String back() {

        conversation.end();
        return _back;

    }

    public String edit(Mails item, String back, String forward) {

        conversation.begin();
        this._back = back;
        mail = item;
        action = Action.EDIT;
        return forward;
    }

    public String add(String back, String forward) {

        conversation.begin();
        this._back = back;
        mail = new Mails();
        action = Action.ADD;
        return forward;
    }

    public String save() {


        /*Mails mail = new Mails();
        mail.setHeader(header);
        mail.setBody("BODY");*/
        switch (action) {
            case ADD:
                //Statuses status = statusManager.find(1); 
                Statuses status = new Statuses();
                status.setStatusId(DocStatus.CREATED.getValue());
                Date d = new Date();
                mail.setDataCreate(d);
                mail.setStatusId(status);

                mailsManager.create(mail);

                hystoryManager.createHystory(mail, status);

                break;
            case EDIT:
                mailsManager.edit(mail);
                break;
            /*case VIEW:
                statusesHistory = mail.getStatusHystorySet();
                break;*/

            case DELETE:

                hystoryManager.removeByDocId(mail.getDocumentId());
                mailsManager.remove(mail);
                break;
            case SEND:
                break;

        }
        conversation.end();
        itemNotifier.fire(new DocumentsOnChangeEvent() {
        });
        return _back;
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

    public List<StatusHystory> getStatusesHistory() {
        return statusesHistory;
    }

}
