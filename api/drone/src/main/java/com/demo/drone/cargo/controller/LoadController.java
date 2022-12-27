package com.demo.drone.cargo.controller;

import java.util.List;
import java.util.Map;

import com.demo.drone.cargo.process.LoadProcess;
import com.demo.drone.cargo.process.OrderProcess;
import com.demo.drone.data.common.controller.AbstractController;
import com.demo.drone.data.common.exception.DroneBaseException;
import com.demo.drone.data.common.model.CustomMessages;
import com.demo.drone.data.common.model.DroneConstraintKey;
import com.demo.drone.data.common.tools.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author alfredo
 */
@RestController
@RequestMapping("cargo/load")
public class LoadController extends AbstractController {

    @Autowired
    LoadProcess loadProcess;

    @PostMapping("{uuid}")
    public SuccessResponse loadCargoToDrone(@PathVariable String uuid, @RequestBody  Map<String,Object> entity) throws Exception {
        try {
            loadProcess.load(uuid,entity);
            return new SuccessResponse(Boolean.TRUE, CustomMessages.message("MSCA002"),null);
        } catch (Exception ex) {
            DroneConstraintKey key = DroneBaseException.contraintKey(ex);
            if(key != DroneConstraintKey.UK00000)
                return SuccessResponse.fail(CustomMessages.message(key.toString()));
            return SuccessResponse.fail(ex);
        }
    }

    // @PutMapping("{id}")
    // public SuccessResponse edit(@PathVariable String id, @RequestBody @Valid MedicationCreateDto entity)
    //         throws DroneBaseException {
    //     try {
    //         return new SuccessResponse(Boolean.TRUE, null, medicationProcess.editMedication(id, entity));
    //     } catch (Exception ex) {
    //         DroneConstraintKey key = DroneBaseException.contraintKey(ex);
    //         return SuccessResponse.fail(CustomMessages.message(key.toString()));
    //     }

    // }

    // @DeleteMapping("{id}")
    // public SuccessResponse delete(@PathVariable String id) throws Exception {
    //     try {
    //         medicationProcess.deleteMedication(id);
    //         return new SuccessResponse(Boolean.TRUE, CustomMessages.message("MSMG001"), id);
    //     } catch (Exception ex) {
    //         DroneConstraintKey key = DroneBaseException.contraintKey(ex);
    //         return SuccessResponse.fail(CustomMessages.message(key.toString()));
    //     }
    // }

}