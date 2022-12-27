package com.demo.drone.data.cargo.repository;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import com.demo.drone.data.common.repository.CustomRepositoryImpl;

@Repository
public class OrderMedicationRepositoryImpl extends CustomRepositoryImpl implements OrderMedicationRepositoryCustom {

        public OrderMedicationRepositoryImpl(EntityManager entityManager) {
                super(entityManager);
        }

}