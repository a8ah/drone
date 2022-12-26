package com.demo.drone.data.common.entity;

import jakarta.persistence.Column;

public abstract class AbstractDroneModelEntity extends AbstractModelEntity{

    @Column(nullable = false)
    protected Boolean enabled = true;

    public AbstractDroneModelEntity() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }   
}