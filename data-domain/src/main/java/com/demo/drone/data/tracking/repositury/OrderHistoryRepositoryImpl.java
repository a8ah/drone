package com.demo.drone.data.tracking.repositury;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import com.demo.drone.data.common.repository.CustomRepositoryImpl;

@Repository
public class OrderHistoryRepositoryImpl extends CustomRepositoryImpl implements OrderHistoryRepositoryCustom {

        public OrderHistoryRepositoryImpl(EntityManager entityManager) {
                super(entityManager);
        }


}