package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.ScorePharmacist;
import com.jelena.nenad.tim16.service.ScorePharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pharmacistScoreData")
public class ScorePhrmacistController {

    @Autowired
    private ScorePharmacistService dermatologistService;

    /*
     * url: /api/dermatologistsScoreData GET
	 */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ScorePharmacist>> getScores() {
        Collection<ScorePharmacist> dermatologists = dermatologistService.findAll();
        return new ResponseEntity<Collection<ScorePharmacist>>(dermatologists, HttpStatus.OK);
    }


    /*
     * url: /api/dermatologistsScoreData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScorePharmacist> getDermatologist(@PathVariable("id") Long id) {
        Optional<ScorePharmacist> dermatologist = dermatologistService.findOne(id);

        if (!dermatologist.isPresent()) {
            return new ResponseEntity<ScorePharmacist>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ScorePharmacist>(dermatologist.get(), HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsScoreData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScorePharmacist> createDermatologist(@RequestBody ScorePharmacist dermatologist) throws Exception {
        ScorePharmacist savedDermatologist = dermatologistService.create(dermatologist);
        return new ResponseEntity<ScorePharmacist>(savedDermatologist, HttpStatus.CREATED);
    }

    /*
     * url: /api/dermatologistsScoreData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScorePharmacist> updateDermatologist(@RequestBody ScorePharmacist dermatologist, @PathVariable Long id)
            throws Exception {
        Optional<ScorePharmacist> dermatologistForUpdate = dermatologistService.findOne(id);
        dermatologistForUpdate.get().copyValues(dermatologist);

        ScorePharmacist updatedDermatologist = dermatologistService.update(dermatologistForUpdate.get());

        if (updatedDermatologist == null) {
            return new ResponseEntity<ScorePharmacist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ScorePharmacist>(updatedDermatologist, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsScoreData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ScorePharmacist> deletePharmacy(@PathVariable("id") Long id) {
        dermatologistService.delete(id);
        return new ResponseEntity<ScorePharmacist>(HttpStatus.NO_CONTENT);
    }

}
