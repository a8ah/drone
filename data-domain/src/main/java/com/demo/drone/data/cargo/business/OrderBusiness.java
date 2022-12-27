package com.demo.drone.data.cargo.business;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.cargo.entity.OrderMedication;
import com.demo.drone.data.cargo.request.MedicationOrderRequest;
import com.demo.drone.data.cargo.service.OrderMedicationService;
import com.demo.drone.data.cargo.service.OrderService;
import com.demo.drone.data.common.business.AbstractBusiness;
import com.demo.drone.data.common.model.annotation.GeneratedCode;
import com.demo.drone.data.management.business.MedicationBusiness;
import com.demo.drone.data.management.entity.Medication;
import com.demo.drone.data.management.execption.MedicationException;


@Service
public class OrderBusiness extends AbstractBusiness {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MedicationBusiness medicationBusiness;

    @Autowired
    private OrderMedicationService orderMedicationService;

    // public Page<MedicationProjection> getAllMedicationByEnabled(PageRequest page, Boolean enabled ){
        
    //     return this.medicationService.getAllMedicationByEnabled(page, enabled);
    // }

    @Transactional
    public String register(List<MedicationOrderRequest> medicationOrderRequests ) throws MedicationException, NoSuchFieldException, SecurityException {

        Order order = new Order();
        Double totalWeigth = 0.0;
        Field codeField = order.getClass().getDeclaredField("code");
        final var generatedCode = codeField.getAnnotation(GeneratedCode.class);
        order.setCode(this.orderService.generateCode(order, generatedCode.length()).toString());

        this.orderService.saveAndFlush(order);

        for (MedicationOrderRequest medicationOrderRequest : medicationOrderRequests) {

            Medication medication =medicationBusiness.findByCode(medicationOrderRequest.getMedication());
            Double weigth = medication.getWeigth() * medicationOrderRequest.getQuantity();

            totalWeigth += weigth;

            OrderMedication orderMedication = new OrderMedication();
            orderMedication.setMedication(medication);
            orderMedication.setQuantity(medicationOrderRequest.getQuantity());
            orderMedication.setWeigth(weigth);
            orderMedication.setOrder(order);

            orderMedicationService.saveAndFlush(orderMedication);
        }

        order.setWeigth(totalWeigth);

        this.orderService.saveAndFlush(order);

        return order.getUuid();
    }

    // public Medication findByUuid(String uuid) throws MedicationException{
    //     Medication medication = this.medicationService.findByUuid(uuid);

    //     if(null == medication)
    //         throw MedicationException.medicationNoExist(uuid);
    //     return medication;
    // }

    // public Medication getMedication(String uuid) throws MedicationException{
    //     Medication medication = this.medicationService.getMedication(uuid, Boolean.TRUE);

    //     if(null == medication)
    //         throw MedicationException.medicationNoExist(uuid);
    //     return medication;
    // }

    // public String editMedication(String uuid, Medication medicationInput) throws MedicationException{
        
    //     Medication medication = this.findByUuid(uuid);

    //     medication.setCode(medicationInput.getCode());
    //     medication.setName(medicationInput.getName());
    //     medication.setWeigth(medicationInput.getWeigth());
    //     medication.setImage(medicationInput.getImage());

    //     this.medicationService.saveAndFlush(medication);

    //     return medication.getUuid();
    // }

    // public void deleteMedication(String uuid) throws MedicationException{
        
    //     Medication medication = this.findByUuid(uuid);

    //     medication.setEnabled(Boolean.FALSE);

    //     this.medicationService.saveAndFlush(medication);
    // }
}
