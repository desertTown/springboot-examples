package com.evan.pojo;


import com.evan.config.Auditable;
import com.evan.config.AuditableListener;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER")
@ToString
@EntityListeners(AuditableListener.class)
public class User implements Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATED_BY_DOMAIN_ID")
    private String createdByDomainId;

    @Column(name = "CREATED_BY_GSID")
    private String createdByGsid;

    @Column(name = "UPDATED_BY_DOMAIN_ID")
    private String updatedByDomainId;

    @Column(name = "UPDATED_BY_GSID")
    private String updatedByGsid;

    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedByDomainId() {
        return createdByDomainId;
    }

    public void setCreatedByDomainId(String createdByDomainId) {
        this.createdByDomainId = createdByDomainId;
    }

    @Override
    public String getCreatedByGsid() {
        return createdByGsid;
    }

    @Override
    public void setCreatedByGsid(String createdByGsid) {
        this.createdByGsid = createdByGsid;
    }

    public String getUpdatedByDomainId() {
        return updatedByDomainId;
    }

    public void setUpdatedByDomainId(String updatedByDomainId) {
        this.updatedByDomainId = updatedByDomainId;
    }

    public String getUpdatedByGsid() {
        return updatedByGsid;
    }

    public void setUpdatedByGsid(String updatedByGsid) {
        this.updatedByGsid = updatedByGsid;
    }

    @Override
    public Date getCreatedTime() {
        return createdTime;
    }

    @Override
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Override
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
