package com.demo.drone.data.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

import com.demo.drone.data.common.repository.CustomRepositoryImpl;
import com.demo.drone.data.management.entity.Medication;
import com.demo.drone.data.management.projection.MedicationProjection;

@Repository
public class DroneRepositoryImpl extends CustomRepositoryImpl implements DroneRepositoryCustom {

        public DroneRepositoryImpl(EntityManager entityManager) {
                super(entityManager);
        }


}