/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;

import com.mycompany.testapp.entities.Mails;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.html.HtmlDataTable;

/**
 *
 * @author www
 */
@Named(value = "operMailsScroller")
@SessionScoped
public class OperMailsScroller implements Serializable {

    /**
     * Creates a new instance of OperMailsScroller
     */
    public OperMailsScroller() {
    }

 
    
}
