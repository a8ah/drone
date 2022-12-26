package com.demo.drone.data.common.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.demo.drone.data.common.domain.FieldConstrain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;

public abstract class AbstractModelEntity implements java.io.Serializable {

    @Id
    @Column(length = FieldConstrain.UUID)
    protected String uuid;

    protected LocalDateTime lastUpdate;

    protected LocalDateTime createdAt;
    @Column(nullable = false)
    protected Boolean enabled = true;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }  

    public AbstractModelEntity() {
        this.uuid = UUID.randomUUID().toString();
        this.lastUpdate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}