/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.ejb;

import com.mycompany.testapp.entities.Statuses;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author www
 */
@Local
public interface StatusesFacadeLocal {

    void create(Statuses statuses);

    void edit(Statuses statuses);

    void remove(Statuses statuses);

    Statuses find(Object id);

    List<Statuses> findAll();

    List<Statuses> findRange(int[] range);

    int count();
    
}
