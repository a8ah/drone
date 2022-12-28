package com.demo.drone.tracking.controller;

import java.util.List;
import java.util.Map;


import com.demo.drone.data.common.controller.AbstractController;
import com.demo.drone.data.common.tools.SuccessResponse;
import com.demo.drone.data.management.projection.OrderMedicationCheckProjection;
import com.demo.drone.management.process.DroneProcess;
import com.demo.drone.tracking.process.OrderHistoryProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author alfredo
 */
@RestController
@RequestMapping("tacking/deliver")
public class DeliverController extends AbstractController {

    @Autowired
    DroneProcess droneProcess;

    @GetMapping("drone/{uuid}")
    public SuccessResponse checkingLoadedMedication(@PathVariable String uuid) throws Exception {
        try {
            List<OrderMedicationCheckProjection> orders = droneProcess.checkingLoadedMedication(uuid);
            return new SuccessResponse(Boolean.TRUE, null, orders);
        } catch (Exception ex) {
            return SuccessResponse.fail(ex);
        }
    }

    


}