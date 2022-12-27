package com.demo.drone.data.cargo.business;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.lang.reflect.Field;
import java.util.List;

import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.cargo.entity.OrderMedication;
import com.demo.drone.data.cargo.execption.OrderException;
import com.demo.drone.data.cargo.request.MedicationOrderRequest;
import com.demo.drone.data.cargo.service.OrderMedicationService;
import com.demo.drone.data.cargo.service.OrderService;
import com.demo.drone.data.common.business.AbstractBusiness;
import com.demo.drone.data.common.model.annotation.GeneratedCode;
import com.demo.drone.data.management.business.MedicationBusiness;
import com.demo.drone.data.management.entity.Medication;
import com.demo.drone.data.management.execption.MedicationException;
import com.demo.drone.data.tracking.model.OrderHistory;
import com.demo.drone.data.tracking.service.OrderHistoryService;


@Service
public class OrderBusiness extends AbstractBusiness {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MedicationBusiness medicationBusiness;

    @Autowired
    private OrderMedicationService orderMedicationService;

    @Autowired
    private OrderHistoryService orderHistoryService;

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

        // register OrderHistory
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setState(order.getState());
        orderHistory.setOrder(order);

        this.orderHistoryService.saveAndFlush(orderHistory);

        return order.getUuid();
    }

    public Order getOrder(String uuid) throws MedicationException, OrderException{
        Order order = this.orderService.findByUuid(uuid);

        if(null == order)
            throw OrderException.orderNoExist(uuid);
        return order;
    }

    @Transactional
    public void save(Order order) {

        this.orderService.saveAndFlush(order);

    }


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
