package com.demo.drone.data.tracking.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.cargo.model.OrderState;
import com.demo.drone.data.common.domain.FieldConstrain;
import com.demo.drone.data.common.repository.SCHEMAS;
import com.demo.drone.data.management.entity.Drone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table( schema = SCHEMAS.traking )
public class OrderHistory implements Serializable {

    @Id
    @Column(length = FieldConstrain.UUID)
    protected String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Drone drone;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Order order;

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

    public OrderHistory() {
        this.uuid = UUID.randomUUID().toString();
        this.lastUpdate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}