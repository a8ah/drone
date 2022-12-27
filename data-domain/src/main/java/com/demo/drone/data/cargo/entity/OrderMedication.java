package com.demo.drone.data.cargo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.demo.drone.data.cargo.model.OrderState;
import com.demo.drone.data.common.domain.FieldConstrain;
import com.demo.drone.data.common.repository.SCHEMAS;
import com.demo.drone.data.management.entity.Medication;

import jakarta.persistence.*;

@Entity
@Table( schema = SCHEMAS.cargo
    )
public class OrderMedication implements Serializable {

    @Id
    @Column(length = FieldConstrain.UUID)
    protected String uuid;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    protected Medication medication; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    protected Order order;

    @Column(nullable = false)
    private Double weigth;

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

    public OrderMedication() {
        this.uuid = UUID.randomUUID().toString();
        this.lastUpdate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}