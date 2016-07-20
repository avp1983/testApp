/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author www
 */
@Entity
@Table(name = "status_hystory")
@NamedQueries({
    @NamedQuery(name = "StatusHystory.findAll", query = "SELECT s FROM StatusHystory s"),
    @NamedQuery(name = "StatusHystory.findById", query = "SELECT s FROM StatusHystory s WHERE s.id = :id"),
    @NamedQuery(name = "StatusHystory.findByDocument", query = "SELECT s FROM StatusHystory s WHERE s.documentId = :documentId"),
    @NamedQuery(name = "StatusHystory.removeByDocId", query = "DELETE FROM StatusHystory s WHERE s.documentId = :documentId"),
    @NamedQuery(name = "StatusHystory.findByDateChange", query = "SELECT s FROM StatusHystory s WHERE s.dateChange = :dateChange")})
public class StatusHystory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "date_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChange;
    
    @JoinColumn(name = "document_id", referencedColumnName = "document_id")
    @ManyToOne
    private Documents documentId;
    
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private Statuses statusId;

    public StatusHystory() {
    }

    public StatusHystory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }

    public Documents getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Documents documentId) {
        this.documentId = documentId;
    }

    public Statuses getStatusId() {
        return statusId;
    }

    public void setStatusId(Statuses statusId) {
        this.statusId = statusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusHystory)) {
            return false;
        }
        StatusHystory other = (StatusHystory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.testapp.entities.StatusHystory[ id=" + id + " ]";
    }
    
}
