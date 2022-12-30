# Dron API
===============================================================================================
Buind
===============================================================================================

Inside project folder run:
- mvn install
===============================================================================================
Deploy
===============================================================================================

- Move inside Deploy folder "cd Deploy"
- execute "docker build -t postgres_drone ."
- exec "docker-compose up"
- exex "cat musala_drone.sql | docker exec -i drone_alfredo_ochoa psql -U postgres -d musala_drone"

- java -jar api/drone/target/drone-1.0.0.jar

===============================================================================================
Endpoints
===============================================================================================
# management/drone
@GetMapping("enabled")
Return all enabled Drone.

@GetMapping("{uuid}")
Return a drone by gived UUID.

@PostMapping
Register a Drone
{
    "serial": "0001",
    "model": "LIGHTWEIGHT",
    "weigth": "20.3"
}

@PutMapping("{id}")
Modify done's data by gived UUID.
{
    "serial": "0001",
    "model": "LIGHTWEIGHT",
    "weigth": "20.3",
    "batery": 100
}

@DeleteMapping("{id}")
Soft delete  a drone by gived UUID.

@PostMapping("{uuid}/update_state")
Update drone's STATE.
management/drone/6f34fa19-a289-4d1a-9834-c94ec71606a9/update_state?state=LOADING

@GetMapping("{uuid}/battery")
Return drone Battery Level by gived UUID.

@GetMapping("availables")
Return LOADING process available drons.

# management/medication

@GetMapping("{uuid}")
Return a medication by gived UUID.

@PostMapping
Register a Medication
{
    "name": "Medication_2",
    "code": "MED_2",
    "weigth": "22.3",
    "image": "aaaaa"
}

@PutMapping("{id}")
Modify medication data by gived UUID.
{
    "name": "Medication_2",
    "code": "MED_1",
    "weigth": "22.3",
    "image": "aaaaa"
}

@DeleteMapping("{id}")
Soft delete  a medication by gived UUID.

# cargo/order
@PostMapping
Register a new Order.
{
    "medicationOrder":[
                        {
                           "item" :  {
                                        "medication": "MED_1",
                                        "quantity": 1
                                    }
                            
                        },
                        {
                            "item":{
                                        "medication": "MED_2",
                                        "quantity": 5
                                    }
                        }
                    ]
}

@GetMapping("{uuid}")
Return an Order by gived UUID.

# cargo/load
@PostMapping("{uuid}")
Start LOADING a drone with an order's list.
The uuid un URL is the drone's id.
{
   "orders": [
                {
                    "uuid": "4f688964-6300-4395-bd09-3b65dfb3baae"
                },
                {
                    "uuid": "ec130d10-1d16-438a-b5a4-332e1f7db6a1"
                }
       
            ]
}

# tacking/deliver
@GetMapping("drone/{uuid}")
Return Done medication bag.

# tacking/order_history
@GetMapping("{uuid}")
Delails of an specific  "order_history".

@GetMapping("order/{uuid}")
Return a list of "order_history" asociate to an order.

@GetMapping("drone/{uuid}")
Return a list of "order_history" asociate to a drone.

@GetMapping("order_drone/{orderUuid}/{droneUuid}")
Return a list of "order_history" asociate to an order and a drone.
===============================================================================================

===============================================================================================