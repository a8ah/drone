package com.demo.drone.tracking.controller;

import java.util.Map;

import com.demo.drone.cargo.process.OrderProcess;
import com.demo.drone.data.common.controller.AbstractController;
import com.demo.drone.data.common.exception.DroneBaseException;
import com.demo.drone.data.common.model.CustomMessages;
import com.demo.drone.data.common.model.DroneConstraintKey;
import com.demo.drone.data.common.tools.SuccessResponse;
import com.demo.drone.data.tracking.model.OrderHistory;
import com.demo.drone.tracking.process.OrderHistoryProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author alfredo
 */
@RestController
@RequestMapping("tacking/order_history")
public class OrderController extends AbstractController {

    @Autowired
    OrderHistoryProcess orderHistoryProcess;

    @GetMapping("{uuid}")
    public SuccessResponse getOrderHistory(@PathVariable String uuid) throws Exception {
        try {
            return new SuccessResponse(Boolean.TRUE, null, orderHistoryProcess.getOrderHistory(uuid));
        } catch (Exception ex) {
            return SuccessResponse.fail(ex);
        }
    }

    @GetMapping("order/{uuid}")
    public SuccessResponse findByOrderUuid(@PathVariable String uuid) throws Exception {
        try {
            return new SuccessResponse(Boolean.TRUE, null, orderHistoryProcess.findByOrderUuid(uuid));
        } catch (Exception ex) {
            return SuccessResponse.fail(ex);
        }
    }

    @GetMapping("drone/{uuid}")
    public SuccessResponse findByDroneUuid(@PathVariable String uuid) throws Exception {
        try {
            return new SuccessResponse(Boolean.TRUE, null, orderHistoryProcess.findByDroneUuid(uuid));
        } catch (Exception ex) {
            return SuccessResponse.fail(ex);
        }
    }

    @GetMapping("order_drone/{orderUuid}/{droneUuid}")
    public SuccessResponse findByDroneUuidAndOrderUuid(@PathVariable String orderUuid,@PathVariable String droneUuid) throws Exception {
        try {
            return new SuccessResponse(Boolean.TRUE, null, orderHistoryProcess.findByDroneUuidAndOrderUuid(droneUuid,orderUuid));
        } catch (Exception ex) {
            return SuccessResponse.fail(ex);
        }
    }



}