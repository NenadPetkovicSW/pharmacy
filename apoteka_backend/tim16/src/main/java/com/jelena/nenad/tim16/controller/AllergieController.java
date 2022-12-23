package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.Allergie;
import com.jelena.nenad.tim16.service.AllergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") //react url
@RestController
@RequestMapping("/api/allergiesData")
public class AllergieController {

    @Autowired
    private AllergieService allergieService; //interfejs

    /*
     * url: /api/allergiesData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Allergie>> getAllergies() {
        Collection<Allergie> allergies = allergieService.findAll();
        return new ResponseEntity<Collection<Allergie>>(allergies, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/allergiesData GET
     */
    @GetMapping(value = "/medication/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Allergie>> getAllergiesByMedicationId(@PathVariable("id") Long id) {
        Collection<Allergie> allergies = allergieService.getAllergiesByMedicationId(id);
        return new ResponseEntity<Collection<Allergie>>(allergies, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/allergiesData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Allergie> getAllergie(@PathVariable("id") Long id) {
        Optional<Allergie> allergie = allergieService.findOne(id);

        if (!allergie.isPresent()) {
            return new ResponseEntity<Allergie>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Allergie>(allergie.get(), HttpStatus.OK);
    }

    /*
     * url: /api/allergiesData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Allergie> createAllergie(@RequestBody Allergie allergie) throws Exception {
        Allergie savedallergie = allergieService.create(allergie);
        return new ResponseEntity<Allergie>(savedallergie, HttpStatus.CREATED);
    }

    /*
     * url: /api/allergiesData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Allergie> updateAllergie(@RequestBody Allergie allergie, @PathVariable Long id)
            throws Exception {
        Optional<Allergie> allergieForUpdate = allergieService.findOne(id);
        allergieForUpdate.get().copyValues(allergie);

        Allergie updatedallergie = allergieService.update(allergieForUpdate.get());

        if (updatedallergie == null) {
            return new ResponseEntity<Allergie>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Allergie>(updatedallergie, HttpStatus.OK);
    }

    /*
     * url: /api/allergiesData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Allergie> deleteAllergie(@PathVariable("id") Long id) {
        allergieService.delete(id);
        return new ResponseEntity<Allergie>(HttpStatus.NO_CONTENT);
    }
}
