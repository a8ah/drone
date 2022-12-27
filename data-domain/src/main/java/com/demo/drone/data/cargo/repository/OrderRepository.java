package com.demo.drone.data.cargo.repository;

import org.springframework.stereotype.Repository;

import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.common.repository.EntityRepository;

@Repository
public interface OrderRepository extends EntityRepository<Order, String>, OrderRepositoryCustom {

    // Drone findByUuid(String uuid);

    // Drone findByUuidAndEnabled(String uuid, Boolean enabled);

    // Boolean existsBySerial(String serial); 
}