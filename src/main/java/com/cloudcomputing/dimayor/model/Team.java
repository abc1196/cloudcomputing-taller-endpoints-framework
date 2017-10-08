/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudcomputing.dimayor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebastiand
 */
@Entity
@Table(name = "team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
    , @NamedQuery(name = "Team.findById", query = "SELECT t FROM Team t WHERE t.id = :id")
    , @NamedQuery(name = "Team.findByName", query = "SELECT t FROM Team t WHERE t.name = :name")
    , @NamedQuery(name = "Team.findByFundationYear", query = "SELECT t FROM Team t WHERE t.fundationYear = :fundationYear")
    , @NamedQuery(name = "Team.findByTitles", query = "SELECT t FROM Team t WHERE t.titles = :titles")
    , @NamedQuery(name = "Team.findByCreated", query = "SELECT t FROM Team t WHERE t.created = :created")
    , @NamedQuery(name = "Team.findByUpdated", query = "SELECT t FROM Team t WHERE t.updated = :updated")})
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "fundation_year")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fundationYear;
    @Basic(optional = false)
    @Column(name = "titles")
    private int titles;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Team() {
    }

    public Team(Integer id) {
        this.id = id;
    }

    public Team(Integer id, String name, Date fundationYear, int titles, Date created) {
        this.id = id;
        this.name = name;
        this.fundationYear = fundationYear;
        this.titles = titles;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFundationYear() {
        return fundationYear;
    }

    public void setFundationYear(Date fundationYear) {
        this.fundationYear = fundationYear;
    }

    public int getTitles() {
        return titles;
    }

    public void setTitles(int titles) {
        this.titles = titles;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sebastiandg7.dimayor.model.Team[ id=" + id + " ]";
    }
    
}
