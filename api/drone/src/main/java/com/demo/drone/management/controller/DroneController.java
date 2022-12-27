package com.demo.drone.management.controller;

import com.demo.drone.data.common.controller.AbstractController;
import com.demo.drone.data.common.exception.DroneBaseException;
import com.demo.drone.data.common.model.CustomMessages;
import com.demo.drone.data.common.model.DroneConstraintKey;
import com.demo.drone.data.common.tools.SuccessResponse;
import com.demo.drone.data.management.model.State;
import com.demo.drone.management.dto.DroneCreateDto;
import com.demo.drone.management.dto.DroneEditDto;
import com.demo.drone.management.process.DroneProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * @author alfredo
 */
@RestController
@RequestMapping("management/drone")
public class DroneController extends AbstractController {

    @Autowired
    DroneProcess droneProcess;

    @GetMapping("{uuid}")
    public SuccessResponse details(@PathVariable String uuid) throws Exception {
        try {
            return new SuccessResponse(Boolean.TRUE, null, droneProcess.getDrone(uuid));
        } catch (Exception ex) {
            return SuccessResponse.fail(ex);
        }
    }

    @PostMapping
    public SuccessResponse create(@RequestBody @Valid DroneCreateDto entity) throws Exception {
        try {
            return new SuccessResponse(Boolean.TRUE, CustomMessages.message("MSMG004"), droneProcess.registerDrone(entity));
        } catch (Exception ex) {
            DroneConstraintKey key = DroneBaseException.contraintKey(ex);
            if(key != DroneConstraintKey.UK00000)
                return SuccessResponse.fail(CustomMessages.message(key.toString()));
            return SuccessResponse.fail(ex);
        }
    }

    @PutMapping("{id}")
    public SuccessResponse edit(@PathVariable String id, @RequestBody @Valid DroneEditDto entity)
            throws DroneBaseException {
        try {
            return new SuccessResponse(Boolean.TRUE, CustomMessages.message("MSMG005"), droneProcess.editDrone(id, entity));
        } catch (Exception ex) {
            DroneConstraintKey key = DroneBaseException.contraintKey(ex);
            if(key != DroneConstraintKey.UK00000)
                return SuccessResponse.fail(CustomMessages.message(key.toString()));
            return SuccessResponse.fail(ex);
        }

    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable String id) throws Exception {
        try {
            droneProcess.deleteDrone(id);
            return new SuccessResponse(Boolean.TRUE, CustomMessages.message("MSMG002"), id);
        } catch (Exception ex) {
            DroneConstraintKey key = DroneBaseException.contraintKey(ex);
            if(key != DroneConstraintKey.UK00000)
                return SuccessResponse.fail(CustomMessages.message(key.toString()));
            return SuccessResponse.fail(ex);
        }
    }

    @PostMapping("{uuid}/update_state")
    public SuccessResponse create(@RequestParam String state , @PathVariable String uuid) throws Exception {
        try {
            droneProcess.changeState(uuid,state);
            return new SuccessResponse(Boolean.TRUE, CustomMessages.message("MSMG003"), uuid);
        } catch (Exception ex) {
            DroneConstraintKey key = DroneBaseException.contraintKey(ex);
            if(key != DroneConstraintKey.UK00000)
                return SuccessResponse.fail(CustomMessages.message(key.toString()));
            return SuccessResponse.fail(ex);
        }
    }

}