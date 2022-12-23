package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.dto.MedicationOrderDTO;
import com.jelena.nenad.tim16.dto.MedicationOrderWrapperDTO;
import com.jelena.nenad.tim16.service.MedicationOrderService;
import com.jelena.nenad.tim16.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/medicationOrderData")
public class MedicationOrderController {

    @Autowired
    private MedicationOrderService medicationService;

    /*
     * url: /api/medicationOrderData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<MedicationOrderDTO>> getEntireOrder() {
        Collection<MedicationOrderDTO> orderDTOS = medicationService.findAll();
        return new ResponseEntity<Collection<MedicationOrderDTO>>(orderDTOS, HttpStatus.OK);
    }


    /*
     * url: /api/medicationOrderData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicationOrderDTO> getOneItemInOrder(@PathVariable("id") Long id) {
        Optional<MedicationOrderDTO> medication = Optional.ofNullable(medicationService.findOne(id));

        if (!medication.isPresent()) {
            return new ResponseEntity<MedicationOrderDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<MedicationOrderDTO>(medication.get(), HttpStatus.OK);
    }

    /*
     * url: /api/medicationOrderData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Medication>> createOrder(@RequestBody MedicationOrderWrapperDTO wrapper) throws Exception {

        List<Medication> orders = new ArrayList<Medication>();
        for (MedicationOrderDTO order: wrapper.getOrders()){
            orders.add(medicationService.create(order));
        }
        return new ResponseEntity<Collection<Medication>>(orders, HttpStatus.CREATED);
    }
}
