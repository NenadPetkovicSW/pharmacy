package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.ScorePharmacy;
import com.jelena.nenad.tim16.service.ScorePharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pharmacyScoreData")
public class ScorePhrmacyController {

    @Autowired
    private ScorePharmacyService dermatologistService;

    /*
     * url: /api/dermatologistsScoreData GET
	 */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ScorePharmacy>> getScores() {
        Collection<ScorePharmacy> dermatologists = dermatologistService.findAll();
        return new ResponseEntity<Collection<ScorePharmacy>>(dermatologists, HttpStatus.OK);
    }


    /*
     * url: /api/dermatologistsScoreData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScorePharmacy> getDermatologist(@PathVariable("id") Long id) {
        Optional<ScorePharmacy> dermatologist = dermatologistService.findOne(id);

        if (!dermatologist.isPresent()) {
            return new ResponseEntity<ScorePharmacy>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ScorePharmacy>(dermatologist.get(), HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsScoreData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScorePharmacy> createDermatologist(@RequestBody ScorePharmacy dermatologist) throws Exception {
        ScorePharmacy savedDermatologist = dermatologistService.create(dermatologist);
        return new ResponseEntity<ScorePharmacy>(savedDermatologist, HttpStatus.CREATED);
    }

    /*
     * url: /api/dermatologistsScoreData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScorePharmacy> updateDermatologist(@RequestBody ScorePharmacy dermatologist, @PathVariable Long id)
            throws Exception {
        Optional<ScorePharmacy> dermatologistForUpdate = dermatologistService.findOne(id);
        dermatologistForUpdate.get().copyValues(dermatologist);

        ScorePharmacy updatedDermatologist = dermatologistService.update(dermatologistForUpdate.get());

        if (updatedDermatologist == null) {
            return new ResponseEntity<ScorePharmacy>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ScorePharmacy>(updatedDermatologist, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsScoreData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ScorePharmacy> deletePharmacy(@PathVariable("id") Long id) {
        dermatologistService.delete(id);
        return new ResponseEntity<ScorePharmacy>(HttpStatus.NO_CONTENT);
    }

}
