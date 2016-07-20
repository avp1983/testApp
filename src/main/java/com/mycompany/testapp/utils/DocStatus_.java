/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author www
 */
@Named(value="docStatus")
@ApplicationScoped
public class DocStatus_ {
    private  final int  Processing=2;
    private  final int  Accepted=3;
    private  final int  Denied=4;
    
    public  int getProcessing() {
        return Processing;
    }

    public int getAccepted() {
        return Accepted;
    }

    public int getDenied() {
        return Denied;
    }

    
    
}
