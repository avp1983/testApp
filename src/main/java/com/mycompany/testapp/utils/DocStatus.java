/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.utils;



/**
 *
 * @author www
 */

public enum DocStatus {
    CREATED(1), PROCESSING(2), ACCEPTED(3), DENIED(4);

    private final int value;

    private DocStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
