/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.ejb;

import com.mycompany.testapp.entities.Documents;
import com.mycompany.testapp.entities.StatusHystory;
import com.mycompany.testapp.entities.Statuses;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author www
 */
@Stateless
public class StatusHystoryFacade extends AbstractFacade<StatusHystory> implements StatusHystoryFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_testApp_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusHystoryFacade() {
        super(StatusHystory.class);
    }

    @Override
    public List<StatusHystory> getHystory(int documentId) {
        Documents d = new Documents();
        d.setDocumentId(documentId);
        return em.createNamedQuery("StatusHystory.findByDocument", StatusHystory.class).setParameter("documentId", d).getResultList();

    }

    @Override
    public void removeByDocId(int documentId) {
        Documents d = new Documents();
        d.setDocumentId(documentId);
        em.createNamedQuery("StatusHystory.removeByDocId", StatusHystory.class).setParameter("documentId", d).executeUpdate();

    }
    
     public void createHystory(Documents d, Statuses status ) {
        StatusHystory hystory = new StatusHystory();
        hystory.setDocumentId(d);
        hystory.setStatusId(status);
        hystory.setDateChange(new Date());
        create(hystory);

    }

}
