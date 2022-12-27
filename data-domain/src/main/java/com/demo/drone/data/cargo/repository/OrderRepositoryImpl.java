package com.demo.drone.data.cargo.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import com.demo.drone.data.common.repository.CustomRepositoryImpl;

@Repository
public class OrderRepositoryImpl extends CustomRepositoryImpl implements OrderRepositoryCustom {

        public OrderRepositoryImpl(EntityManager entityManager) {
                super(entityManager);
        }


}