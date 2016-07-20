/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testapp.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Size;


/**
 *
 * @author www
 */
@Entity
@Table(name = "statuses")
@NamedQueries({
    @NamedQuery(name = "Statuses.findAll", query = "SELECT s FROM Statuses s"),
    @NamedQuery(name = "Statuses.findByStatusId", query = "SELECT s FROM Statuses s WHERE s.statusId = :statusId"),
    @NamedQuery(name = "Statuses.findByName", query = "SELECT s FROM Statuses s WHERE s.name = :name")})
public class Statuses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_id")
    private Integer statusId;
    
    @Basic(optional = false)    
    @Size(min = 1, max = 256)
    @Column(name = "Name")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<Documents> documentsSet;
    
    @OneToMany(mappedBy = "statusId")
    private Set<StatusHystory> statusHystorySet;

    public Statuses() {
    }

    public Statuses(Integer statusId) {
        this.statusId = statusId;
    }

    public Statuses(Integer statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public Set<Documents> getDocumentsSet() {
        return documentsSet;
    }

    public void setDocumentsSet(Set<Documents> documentsSet) {
        this.documentsSet = documentsSet;
    }

    
    public Set<StatusHystory> getStatusHystorySet() {
        return statusHystorySet;
    }

    public void setStatusHystorySet(Set<StatusHystory> statusHystorySet) {
        this.statusHystorySet = statusHystorySet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statuses)) {
            return false;
        }
        Statuses other = (Statuses) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.testapp.entities.Statuses[ statusId=" + statusId + " ]";
    }
    
}
