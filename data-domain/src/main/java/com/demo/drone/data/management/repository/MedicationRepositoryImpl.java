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
public class MedicationRepositoryImpl extends CustomRepositoryImpl implements MedicationRepositoryCustom {

        public MedicationRepositoryImpl(EntityManager entityManager) {
                super(entityManager);
        }

        @Override
        public Page<MedicationProjection> getAllMedicationByEnabled(Pageable pageable, Boolean enabled) {
                // // -------- Data Query ---
                // CriteriaQuery<MedicationProjection> dataQuery = criteriaBuilder
                //                 .createQuery(MedicationProjection.class);

                // // From
                // Root<Medication> root = dataQuery.from(Medication.class);
        
                // // Where : Specification - Filter

                // dataQuery.where(criteriaBuilder.equal(root.get(Medication_.enabled), enabled));

                // // Select
                // dataQuery.multiselect(
                //                 root.get(Medication_.uuid).alias("uuid"),
                //                 root.get(Medication_.name).alias("name"),
                //                 root.get(Medication_.code).alias("code"),
                //                 root.get(Medication_.weigth).alias("weigth")
                // );

                // // Order
                // dataQuery.orderBy(
                //                 new OrderImpl(root.get(Medication_.createdAt), false));

                // // Run DataQuery
                // Query dataQueryRun = this.entityManager.createQuery(dataQuery);
                // dataQueryRun.setFirstResult(Math.toIntExact(pageable.getOffset()));
                // dataQueryRun.setMaxResults(this.getPageMaxResult(pageable));

                // List<MedicationProjection> result = dataQueryRun.getResultList();

                // // ------ Count Query ---
                // CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);

                // // From
                // Root<Medication> countRoot = countQuery.from(Medication.class);

                // // Where: Specification - Filter
                // countQuery.where(criteriaBuilder.equal(root.get(Medication_.enabled), enabled));

                // // Count
                // countQuery.select(criteriaBuilder.count(countRoot.get(Medication_.uuid)));

                // Query countQueryRun = this.entityManager.createQuery(countQuery);
                // Long count = (Long) countQueryRun.getSingleResult();

                // return new PageImpl<>(result, pageable, count);

                return null;
        }

}