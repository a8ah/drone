package com.demo.drone.data.cargo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.demo.drone.data.cargo.model.OrderState;
import com.demo.drone.data.common.domain.FieldConstrain;
import com.demo.drone.data.common.model.annotation.GeneratedCode;
import com.demo.drone.data.common.repository.SCHEMAS;
import com.demo.drone.data.management.entity.Drone;

import jakarta.persistence.*;

@Entity
@Table( schema = SCHEMAS.cargo
    )
public class Order implements Serializable {

    @Id
    @Column(length = FieldConstrain.UUID)
    protected String uuid;

    @GeneratedCode(length = 7)
    @Column(nullable = false, unique = true)
    protected String code;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<OrderMedication> medications;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Drone drone;

    @Enumerated(EnumType.STRING)
    protected OrderState state = OrderState.PENDING;

    @Column(nullable = false)
    private Double weigth = 0.0;

    protected LocalDateTime lastUpdate;

    protected LocalDateTime createdAt;

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

    public Order() {
        this.uuid = UUID.randomUUID().toString();
        this.lastUpdate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<OrderMedication> getMedications() {
        return medications;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void setMedications(Set<OrderMedication> medications) {
        this.medications = medications;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

}