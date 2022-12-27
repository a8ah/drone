package com.demo.drone.data.cargo.repository;

import org.springframework.stereotype.Repository;

import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.common.repository.EntityRepository;

@Repository
public interface OrderRepository extends EntityRepository<Order, String>, OrderRepositoryCustom {

    Order findByUuid(String uuid);

    Order findByUuidAndEnabled(String uuid, Boolean enabled);

    Boolean existsByCode(String code); 
}