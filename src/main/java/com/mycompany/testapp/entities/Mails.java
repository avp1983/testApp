/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author www
 */
@Entity
@Table(name = "mails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mails.findAll", query = "SELECT m FROM Mails m"),   
    @NamedQuery(name = "Mails.findByHeader", query = "SELECT m FROM Mails m WHERE m.header = :header")})
@DiscriminatorValue(value = "Mails")
public class Mails extends Documents  implements Serializable {

    private static final long serialVersionUID = 1L;
 
    @Size(max = 256)
    @Column(name = "header")
    private String header;
    @Lob
    @Size(max = 65535)
    @Column(name = "body")
    private String body;
    @Lob
    @Column(name = "attachment")
    private byte[] attachment;
    
    
    /*@JoinColumn(name = "mail_id", referencedColumnName = "document_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Documents documents;*/

    public Mails() {
    }

  

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    /*public Documents getDocuments() {
        return documents;
    }

    public void setDocuments(Documents documents) {
        this.documents = documents;
    }*/

   

    
    
}
