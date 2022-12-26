package com.demo.drone.data.management.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import com.demo.drone.data.common.domain.FieldConstrain;
import com.demo.drone.data.common.entity.AbstractDroneModelEntity;
import com.demo.drone.data.common.entity.AbstractModelEntity;
import com.demo.drone.data.common.repository.SCHEMAS;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table( schema = SCHEMAS.medication,
        uniqueConstraints = { @UniqueConstraint(name = "UKMG001", columnNames = {"code"})}
    )
public class Medication implements Serializable {

    @Id
    @Column(length = FieldConstrain.UUID)
    protected String uuid;

    protected LocalDateTime lastUpdate;

    protected LocalDateTime createdAt;

    @Column(nullable = false)
    protected Boolean enabled = true;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Double weigth;

    private String image;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getFullName(){
        return "[" + code + "] " + name;
    }

    public Double getWeigth() {
        return weigth;
    }

    public void setWeigth(Double weigth) {
        this.weigth = weigth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Medication() {
        this.uuid = UUID.randomUUID().toString();
        this.lastUpdate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}