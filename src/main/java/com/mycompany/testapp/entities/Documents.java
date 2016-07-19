/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author www
 */
@Entity
@Table(name = "documents")
@NamedQueries({
    @NamedQuery(name = "Documents.findAll", query = "SELECT d FROM Documents d"),
    @NamedQuery(name = "Documents.findByDocumentId", query = "SELECT d FROM Documents d WHERE d.documentId = :documentId"),
    @NamedQuery(name = "Documents.findByDataCreate", query = "SELECT d FROM Documents d WHERE d.dataCreate = :dataCreate"),
    @NamedQuery(name = "Documents.findByDocType", query = "SELECT d FROM Documents d WHERE d.docType = :docType")})
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="doc_type",discriminatorType = DiscriminatorType.STRING)
abstract class Documents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "document_id")
    private Integer documentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCreate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "doc_type")
    private String docType;
    
    /*@OneToOne(cascade = CascadeType.ALL, mappedBy = "documents")
    private Mails mails;*/
    
    
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private Statuses statusId;
    
    @OneToMany(mappedBy = "documentId")
    private Set<StatusHystory> statusHystorySet;

    public Documents() {
    }

    public Documents(Integer documentId) {
        this.documentId = documentId;
    }

    public Documents(Integer documentId, Date dataCreate, String docType) {
        this.documentId = documentId;
        this.dataCreate = dataCreate;
        this.docType = docType;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Date getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(Date dataCreate) {
        this.dataCreate = dataCreate;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    /*public Mails getMails() {
        return mails;
    }

    public void setMails(Mails mails) {
        this.mails = mails;
    }*/

    public Statuses getStatusId() {
        return statusId;
    }

    public void setStatusId(Statuses statusId) {
        this.statusId = statusId;
    }

    @XmlTransient
    public Set<StatusHystory> getStatusHystorySet() {
        return statusHystorySet;
    }

    public void setStatusHystorySet(Set<StatusHystory> statusHystorySet) {
        this.statusHystorySet = statusHystorySet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentId != null ? documentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documents)) {
            return false;
        }
        Documents other = (Documents) object;
        if ((this.documentId == null && other.documentId != null) || (this.documentId != null && !this.documentId.equals(other.documentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.testapp.entities.Documents[ documentId=" + documentId + " ]";
    }
    
}
