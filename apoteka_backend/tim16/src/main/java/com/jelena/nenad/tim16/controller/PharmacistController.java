package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.domain.Pharmacist;
import com.jelena.nenad.tim16.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pharmacistsData")
public class PharmacistController {

    @Autowired
    private PharmacistService pharmacistService;

    /*
     * url: /api/pharmacistsData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Pharmacist>> getPharmacists() {
        Collection<Pharmacist> pharmacists = pharmacistService.findAll();
        return new ResponseEntity<Collection<Pharmacist>>(pharmacists, HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsData GET
     */
    @GetMapping(value = "/score/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Pharmacist>> findPharmacistToScore(@PathVariable("idUser") Long idUser) {
        Collection<Pharmacist> pharmacists = pharmacistService.findPharmacistToScore(idUser);
        return new ResponseEntity<Collection<Pharmacist>>(pharmacists, HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsData/search/name/last_name/pharmacy_id GET
     */
    @GetMapping(value = "/search/{name}/{lastname}/{pharmacyid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Pharmacist>> getDermatologistsByQuery(@PathVariable("name") String name,
                                                                              @PathVariable("lastname") String lastName,
                                                                              @PathVariable("pharmacyid") Long pharmacyId) {
        Collection<Pharmacist> pharmacists = pharmacistService.findAllByName(name,lastName,pharmacyId);
        return new ResponseEntity<Collection<Pharmacist>>(pharmacists, HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsData/free/1 GET
     */
    @GetMapping(value = "/free/{pharmacyId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Pharmacist>> getFreePharmacists(@PathVariable("pharmacyId") Long pharmacyId) {
        Collection<Pharmacist> pharmacists = pharmacistService.getFreePharmacist(pharmacyId);
        return new ResponseEntity<Collection<Pharmacist>>(pharmacists, HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pharmacist> getPharmacist(@PathVariable("id") Long id) {
        Optional<Pharmacist> pharmacist = pharmacistService.findOne(id);

        if (!pharmacist.isPresent()) {
            return new ResponseEntity<Pharmacist>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pharmacist>(pharmacist.get(), HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pharmacist> createPharmacist(@RequestBody Pharmacist pharmacist) throws Exception {
        Pharmacist savedPharmacist = pharmacistService.create(pharmacist);
        return new ResponseEntity<Pharmacist>(savedPharmacist, HttpStatus.CREATED);
    }

    /*
     * url: /api/pharmacistsData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pharmacist> updatePharmacist(@RequestBody Pharmacist pharmacist, @PathVariable Long id)
            throws Exception {
        Optional<Pharmacist> pharmacistForUpdate = pharmacistService.findOne(id);
        pharmacistForUpdate.get().copyValues(pharmacist);

        Pharmacist updatedPharmacist = pharmacistService.update(pharmacistForUpdate.get());

        if (updatedPharmacist == null) {
            return new ResponseEntity<Pharmacist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Pharmacist>(updatedPharmacist, HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Pharmacist> deletePharmacy(@PathVariable("id") Long id) {
        pharmacistService.delete(id);
        return new ResponseEntity<Pharmacist>(HttpStatus.NO_CONTENT);
    }
}
