package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.domain.Pharmacy;
import com.jelena.nenad.tim16.domain.Promotion;
import com.jelena.nenad.tim16.service.ActionService;
import com.jelena.nenad.tim16.service.PharmacyService;
import com.jelena.nenad.tim16.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") //react url
@RestController
@RequestMapping("/api/pharmaciesData")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService; //interfejs
    @Autowired
    private ActionService actionService;
    @Autowired
    private PromotionService promotionService;

    /*
     * url: /api/pharmaciesData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Pharmacy>> getPharmacies() {
        Collection<Pharmacy> pharmacies = pharmacyService.findAll();
        return new ResponseEntity<Collection<Pharmacy>>(pharmacies, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/medicationsData/score/1 GET
     */
    @GetMapping(value = "/score/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Pharmacy>> getDermatologistsToScore(@PathVariable("userId") Long userId) {
        Collection<Pharmacy> dermatologists = pharmacyService.findPharmacyToScore(userId);
        return new ResponseEntity<Collection<Pharmacy>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/pharmaciesData/free GET
     */
    @GetMapping(value = "/free",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Pharmacy>> getFreePharmacies() {
        Collection<Pharmacy> pharmacies = pharmacyService.getFreePharmacies();
        return new ResponseEntity<Collection<Pharmacy>>(pharmacies, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/pharmaciesData/free GET
     */
    @GetMapping(value = "/free/{date}/{from}/{to}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Pharmacy>> getFreePharmaciesByDateAndTime(@PathVariable("date") Long date,@PathVariable("from") Long from,@PathVariable("to") Long to) {

        Date dateD = new Date(date);


        Date fromD = new Date(from);

        Date toD = new Date(to);


        Collection<Pharmacy> pharmacies = pharmacyService.getFreePharmaciesByDateAndTime(dateD, new Time(fromD.getTime()), new Time(toD.getTime()));
        return new ResponseEntity<Collection<Pharmacy>>(pharmacies, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/pharmaciesData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pharmacy> getPharmacy(@PathVariable("id") Long id) {
        Optional<Pharmacy> pharmacy = pharmacyService.findOne(id);

        if (!pharmacy.isPresent()) {
            return new ResponseEntity<Pharmacy>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pharmacy>(pharmacy.get(), HttpStatus.OK);
    }
    /*
     * url: /api/pharmaciesData/actions/1 GET
     */
    @GetMapping(value = "/actions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Action>> getPharmacyActions(@PathVariable("id") Long id) {
        Collection<Action> actions  = actionService.findAll(id);
        return new ResponseEntity<Collection<Action>>(actions, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/pharmaciesData/promotions/1 GET
     */
    @GetMapping(value = "/promotions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Promotion>> getPharmacyPromotions(@PathVariable("id") Long id) {
        Collection<Promotion> promotions  = promotionService.findAll(id);
        return new ResponseEntity<Collection<Promotion>>(promotions, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/pharmaciesData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pharmacy> createPharmacy(@RequestBody Pharmacy pharmacy) throws Exception {
        Pharmacy savedPharmacy = pharmacyService.create(pharmacy);
        return new ResponseEntity<Pharmacy>(savedPharmacy, HttpStatus.CREATED);
    }

    /*
     * url: /api/pharmaciesData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pharmacy> updatePharmacy(@RequestBody Pharmacy pharmacy, @PathVariable Long id)
            throws Exception {
        Optional<Pharmacy> pharmacyForUpdate = pharmacyService.findOne(id);
        pharmacyForUpdate.get().copyValues(pharmacy);

        Pharmacy updatedPharmacy = pharmacyService.update(pharmacyForUpdate.get());

        if (updatedPharmacy == null) {
            return new ResponseEntity<Pharmacy>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Pharmacy>(updatedPharmacy, HttpStatus.OK);
    }

    /*
     * url: /api/pharmaciesData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Pharmacy> deletePharmacy(@PathVariable("id") Long id) {
        pharmacyService.delete(id);
        return new ResponseEntity<Pharmacy>(HttpStatus.NO_CONTENT);
    }
}
