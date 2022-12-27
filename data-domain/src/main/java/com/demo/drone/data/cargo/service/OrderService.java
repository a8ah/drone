package com.demo.drone.data.cargo.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.Query;

import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.cargo.repository.OrderRepository;
import com.demo.drone.data.common.service.AbstractEntityService;
import com.demo.drone.data.management.entity.Drone;
import com.demo.drone.data.management.repository.DroneRepository;

@Service
public class OrderService extends AbstractEntityService<Order, String, OrderRepository> {


    public Object generateCode(Order entity, int lengthCode) {

        try {

            String sql = "SELECT SUBSTRING(max(code), 3) " +
                    " FROM Order or " +
                    " WHERE SUBSTRING(code, 1, 2) = (:year)";

            Query query = this.em.createQuery(sql);

            String year = String.valueOf(entity.getCreatedAt().getYear());
            year = year.substring(2,4);
            query.setParameter("year", year);

            Object result = null;
            Object resultLastCode = null;

            try{

                resultLastCode = query.getSingleResult();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            Integer partialLastCode = null;
            if(null == resultLastCode){

                partialLastCode = 0;
            }
            else{

                partialLastCode = Integer.parseInt((String)resultLastCode);
            }


            String partialCode = this.generateStringCode(partialLastCode, lengthCode - 2);

            return year   + partialCode;
        }
        catch (Exception e){

            e.printStackTrace();
            return null;
        }
    }

    public Order findByUuid(String uuid){
        return this.repository.findByUuid(uuid);
    }

    // public Drone getDrone(String uuid, Boolean enabled){
    //     return this.repository.findByUuidAndEnabled(uuid, enabled);
    // }

    // public Boolean existByUuid(String uuid){
    //     return this.repository.existsById(uuid);
    // }

    // public Boolean existsBySerial(String serial){
    //     return this.repository.existsBySerial(serial);
    // }
}