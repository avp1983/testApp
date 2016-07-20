/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.ejb;

import com.mycompany.testapp.entities.Documents;
import com.mycompany.testapp.entities.StatusHystory;
import com.mycompany.testapp.entities.Statuses;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author www
 */
@Local
public interface StatusHystoryFacadeLocal {

    void create(StatusHystory statusHystory);

    void edit(StatusHystory statusHystory);

    void remove(StatusHystory statusHystory);

    StatusHystory find(Object id);

    List<StatusHystory> findAll();

    List<StatusHystory> findRange(int[] range);

    List<StatusHystory> getHystory(int documentId);

    void removeByDocId(int documentId);

    public void createHystory(Documents d, Statuses status);

    int count();

}
