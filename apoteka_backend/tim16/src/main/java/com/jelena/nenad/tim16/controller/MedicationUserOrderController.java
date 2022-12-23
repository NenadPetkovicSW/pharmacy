package com.jelena.nenad.tim16.controller;


import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.domain.MedicationOrder;
import com.jelena.nenad.tim16.domain.Pharmacy;
import com.jelena.nenad.tim16.domain.User;
import com.jelena.nenad.tim16.service.*;
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
@RequestMapping("/api/medicationUserOrderData")
public class MedicationUserOrderController {

    @Autowired
    private MedicationUserOrderService medicationService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private UserService userService;

    @Autowired
    private MedicationService medicationsService;

    @Autowired
    private PharmacyService pharmacyService;

    /*
     * url: /api/medicationsData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<MedicationOrder>> getMedications() {
        Collection<MedicationOrder> medications = medicationService.findAll();
        return new ResponseEntity<Collection<MedicationOrder>>(medications, HttpStatus.OK);
    }



    /*
     * url: /api/medicationsData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicationOrder> getMedication(@PathVariable("id") Long id) {
        Optional<MedicationOrder> medication = medicationService.findOne(id);

        if (!medication.isPresent()) {
            return new ResponseEntity<MedicationOrder>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<MedicationOrder>(medication.get(), HttpStatus.OK);
    }

    /*
     * url: /api/medicationsData/user/1 GET
     */
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<MedicationOrder>> getOrderByUser(@PathVariable("id") Long id) {
        Collection<MedicationOrder> medication = medicationService.getOrderByUser(id);
        return new ResponseEntity<Collection<MedicationOrder>>(medication, HttpStatus.OK);
    }

    /*
     * url: /api/medicationsData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicationOrder> createMedication(@RequestBody MedicationOrder medication) throws Exception {
        MedicationOrder savedMedication = medicationService.create(medication);
        Optional<User> user = userService.findOne(savedMedication.getPatientId());
        Optional<Medication> med = medicationsService.findOne(savedMedication.getMedicationId());
        Optional<Pharmacy> phar = pharmacyService.findOne(savedMedication.getPharmacyId());
        String eText = "Poruceno: \n" + med.get().getName() + "\nKolicina: " + savedMedication.getAmount() + "\nDatum isporuke:"+savedMedication.getDeliveryDate().toString() +
                "\nCena: " + savedMedication.getPrice() + "\nDatum narucivanja:" + savedMedication.getReservationDate().toString() + "\nApoteka: " + phar.get().getName();
        sendEmailService.sendEmail(user.get().getEmail(), eText, "Potvrda o porudzbini");
        return new ResponseEntity<MedicationOrder>(savedMedication, HttpStatus.CREATED);
    }

    /*
     * url: /api/medicationsData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicationOrder> updateMedication(@RequestBody MedicationOrder medication, @PathVariable Long id)
            throws Exception {
        Optional<MedicationOrder> medicationForUpdate = medicationService.findOne(id);
        medicationForUpdate.get().copyValues(medication);

        MedicationOrder updatedMedication = medicationService.update(medicationForUpdate.get());

        if (updatedMedication == null) {
            return new ResponseEntity<MedicationOrder>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<MedicationOrder>(updatedMedication, HttpStatus.OK);
    }

    /*
     * url: /api/medicationsData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MedicationOrder> deleteMedication(@PathVariable("id") Long id) throws Exception {
        medicationService.delete(id);
        return new ResponseEntity<MedicationOrder>(HttpStatus.NO_CONTENT);
    }
}
