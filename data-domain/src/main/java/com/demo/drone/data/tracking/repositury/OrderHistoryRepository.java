package com.demo.drone.data.tracking.repositury;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.demo.drone.data.common.repository.EntityRepository;
import com.demo.drone.data.tracking.model.OrderHistory;

@Repository
public interface OrderHistoryRepository extends EntityRepository<OrderHistory, String>, OrderHistoryRepositoryCustom {

    List<OrderHistory> findByOrderUuid(String orderUuid);

    List<OrderHistory> findByDroneUuid(String droneUuid);

    List<OrderHistory> findByDroneUuidAndOrderUuid(String droneUuid, String orderUuid);

    OrderHistory findByUuid(String uuid);
}