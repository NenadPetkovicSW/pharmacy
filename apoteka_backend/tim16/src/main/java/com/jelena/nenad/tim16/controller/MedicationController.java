package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.service.MedicationService;
import com.jelena.nenad.tim16.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/medicationsData")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;



    /*
     * url: /api/medicationsData GET
	 */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Medication>> getMedications() {
        Collection<Medication> medications = medicationService.findAll();
        return new ResponseEntity<Collection<Medication>>(medications, HttpStatus.OK);
    }


    /*
     * url: /api/medicationsData/pharmacy/1 GET
     */
    @GetMapping(value = "/pharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Medication>> getMedicationInPharmacy(@PathVariable("id") Long id) {
        Collection<Medication> medications = medicationService.findAll(id);
        return new ResponseEntity<Collection<Medication>>(medications, HttpStatus.OK);
    }

    /*
     * url: /api/medicationsData/score/1 GET
     */
    @GetMapping(value = "/score/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Medication>> getDermatologistsToScore(@PathVariable("userId") Long userId) {
        Collection<Medication> dermatologists = medicationService.findMedicationToScore(userId);
        return new ResponseEntity<Collection<Medication>>(dermatologists, HttpStatus.OK);
    }


    /*
     * url: /api/medicationsData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medication> getMedication(@PathVariable("id") Long id) {
        Optional<Medication> medication = medicationService.findOne(id);

        if (!medication.isPresent()) {
            return new ResponseEntity<Medication>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Medication>(medication.get(), HttpStatus.OK);
    }

    /*
     * url: /api/medicationsData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) throws Exception {
        Medication savedMedication = medicationService.create(medication);
        return new ResponseEntity<Medication>(savedMedication, HttpStatus.CREATED);
    }

    /*
     * url: /api/medicationsData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medication> updateMedication(@RequestBody Medication medication, @PathVariable Long id)
            throws Exception {
        Optional<Medication> medicationForUpdate = medicationService.findOne(id);
        medicationForUpdate.get().copyValues(medication);

        Medication updatedMedication = medicationService.update(medicationForUpdate.get());

        if (updatedMedication == null) {
            return new ResponseEntity<Medication>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Medication>(updatedMedication, HttpStatus.OK);
    }

    /*
     * url: /api/medicationsData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Medication> deleteMedication(@PathVariable("id") Long id) {
        medicationService.delete(id);
        return new ResponseEntity<Medication>(HttpStatus.NO_CONTENT);
    }
}
