package com.demo.drone.data.tracking.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.demo.drone.data.cargo.model.OrderState;
import com.demo.drone.data.common.domain.FieldConstrain;
import com.demo.drone.data.common.repository.SCHEMAS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table( schema = SCHEMAS.traking )
public class DroneBatteryHistory implements Serializable {

    @Id
    @Column(length = FieldConstrain.UUID)
    protected String uuid;

    protected String droneId;

    protected String droneSerial;

    protected Integer battery;

    @Enumerated(EnumType.STRING)
    protected OrderState state;

    protected LocalDateTime lastUpdate;

    protected LocalDateTime createdAt;

    @Column(nullable = false)
    protected Boolean enabled = true;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }  

    public DroneBatteryHistory() {
        this.uuid = UUID.randomUUID().toString();
        this.lastUpdate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
    }

    public String getDroneSerial() {
        return droneSerial;
    }

    public void setDroneSerial(String droneSerial) {
        this.droneSerial = droneSerial;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }
}