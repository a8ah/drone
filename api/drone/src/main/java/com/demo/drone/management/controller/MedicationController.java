package com.demo.drone.management.controller;

import com.demo.drone.data.common.controller.AbstractController;
import com.demo.drone.data.common.exception.DroneBaseException;
import com.demo.drone.data.common.model.CustomMessages;
import com.demo.drone.data.common.model.DroneConstraintKey;
import com.demo.drone.data.common.tools.SuccessResponse;
import com.demo.drone.data.management.projection.MedicationProjection;
import com.demo.drone.management.dto.MedicationCreateDto;
import com.demo.drone.management.process.MedicationProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * @author alfredo
 */
@RestController
@RequestMapping("management/medication")
public class MedicationController extends AbstractController {

    @Autowired
    MedicationProcess medicationProcess;

    @GetMapping("{uuid}")
    public SuccessResponse details(@PathVariable String uuid) throws Exception {
        try {
            return new SuccessResponse(Boolean.TRUE, null, medicationProcess.getMedication(uuid));
        } catch (Exception ex) {
            return SuccessResponse.fail(ex);
        }
    }

    @PostMapping("search_enabled")
    public SuccessResponse search(@RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        try {
            Page<MedicationProjection> medicatios = medicationProcess.getAllEnabledMedication(page, size);
            return new SuccessResponse(Boolean.TRUE, null, medicatios);
        } catch (Exception ex) {
            return SuccessResponse.fail(ex);
        }
    }

    @PostMapping
    public SuccessResponse create(@RequestBody @Valid MedicationCreateDto entity) throws Exception {
        try {
            return new SuccessResponse(Boolean.TRUE, null, medicationProcess.registerMedication(entity));
        } catch (Exception ex) {
            DroneConstraintKey key = DroneBaseException.contraintKey(ex);
            return SuccessResponse.fail(CustomMessages.message(key.toString()));
        }
    }

    @PutMapping("{id}")
    public SuccessResponse edit(@PathVariable String id, @RequestBody @Valid MedicationCreateDto entity)
            throws DroneBaseException {
        try {
            return new SuccessResponse(Boolean.TRUE, null, medicationProcess.editMedication(id, entity));
        } catch (Exception ex) {
            DroneConstraintKey key = DroneBaseException.contraintKey(ex);
            return SuccessResponse.fail(CustomMessages.message(key.toString()));
        }

    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable String id) throws Exception {
        try {
            medicationProcess.deleteMedication(id);
            return new SuccessResponse(Boolean.TRUE, CustomMessages.message("MSMG001"), id);
        } catch (Exception ex) {
            DroneConstraintKey key = DroneBaseException.contraintKey(ex);
            return SuccessResponse.fail(CustomMessages.message(key.toString()));
        }
    }

}