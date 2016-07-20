/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.ejb;

import com.mycompany.testapp.entities.Statuses;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author www
 */
@Stateless
public class StatusesFacade extends AbstractFacade<Statuses> implements StatusesFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_testApp_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusesFacade() {
        super(Statuses.class);
    }
    
}
