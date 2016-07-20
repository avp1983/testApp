/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.ejb;

import com.mycompany.testapp.entities.Mails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author www
 */
@Local
public interface MailsFacadeLocal {

    void create(Mails mails);

    void edit(Mails mails);

    void remove(Mails mails);

    Mails find(Object id);

    List<Mails> findAll();

    List<Mails> findRange(int[] range);

    int count();
    
}
