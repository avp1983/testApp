/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.web;

/**
 *
 * @author www
 */
public interface IUserBean {
    String getName();
    String logout();
    boolean isInOperRole();
    boolean isInUserRole();    
}
