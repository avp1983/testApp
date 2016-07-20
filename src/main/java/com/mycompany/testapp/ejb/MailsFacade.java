/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.ejb;

import com.mycompany.testapp.entities.Mails;
import com.mycompany.testapp.entities.Statuses;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author www
 */
@Stateless
public class MailsFacade extends AbstractFacade<Mails> implements MailsFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_testApp_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MailsFacade() {
        super(Mails.class);
    }

    @Override
    public List<Mails> findByStatus(Statuses status) {
       return em.createQuery("SELECT m FROM Mails m WHERE m.statusId=:status", Mails.class).setParameter("status", status).getResultList();
    }

    @Override
    public List<Mails> findByStatus(int s) {
        Statuses status = new Statuses();
        status.setStatusId(s);
       return findByStatus(status);
    }
    
    
    
    
}
