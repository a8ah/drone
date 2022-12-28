package com.demo.drone.data.management.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.common.domain.FieldConstrain;
import com.demo.drone.data.common.repository.SCHEMAS;
import com.demo.drone.data.management.model.Model;
import com.demo.drone.data.management.model.State;

import jakarta.persistence.*;

@Entity
@Table( schema = SCHEMAS.drone,
        uniqueConstraints = { @UniqueConstraint(name = "UKMG002", columnNames = {"serial"})}
    )
public class Drone implements Serializable {

    @Id
    @Column(length = FieldConstrain.UUID)
    protected String uuid;

    @Column(nullable = false)
    private String serial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Model model;

    @Enumerated(EnumType.STRING)
    protected State state = State.IDLE;

    @Column(nullable = false)
    private Double weigth;

    @Column(nullable = false)
    private Integer battery = 0 ;

    protected LocalDateTime lastUpdate;

    protected LocalDateTime createdAt;

    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @Column(nullable = false)
    protected Boolean enabled = true;

    public Double getWeigth() {
        return weigth;
    }

    public void setWeigth(Double weigth) {
        this.weigth = weigth;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }  

    public Drone() {
        this.uuid = UUID.randomUUID().toString();
        this.lastUpdate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}