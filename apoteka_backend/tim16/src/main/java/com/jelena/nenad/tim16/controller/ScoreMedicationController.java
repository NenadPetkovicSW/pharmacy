package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.ScoreMedication;
import com.jelena.nenad.tim16.service.ScoreMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/medicationScoreData")
public class ScoreMedicationController {

    @Autowired
    private ScoreMedicationService dermatologistService;

    /*
     * url: /api/dermatologistsScoreData GET
	 */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ScoreMedication>> getScores() {
        Collection<ScoreMedication> dermatologists = dermatologistService.findAll();
        return new ResponseEntity<Collection<ScoreMedication>>(dermatologists, HttpStatus.OK);
    }


    /*
     * url: /api/dermatologistsScoreData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreMedication> getDermatologist(@PathVariable("id") Long id) {
        Optional<ScoreMedication> dermatologist = dermatologistService.findOne(id);

        if (!dermatologist.isPresent()) {
            return new ResponseEntity<ScoreMedication>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ScoreMedication>(dermatologist.get(), HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsScoreData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreMedication> createDermatologist(@RequestBody ScoreMedication dermatologist) throws Exception {
        ScoreMedication savedDermatologist = dermatologistService.create(dermatologist);
        return new ResponseEntity<ScoreMedication>(savedDermatologist, HttpStatus.CREATED);
    }

    /*
     * url: /api/dermatologistsScoreData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreMedication> updateDermatologist(@RequestBody ScoreMedication dermatologist, @PathVariable Long id)
            throws Exception {
        Optional<ScoreMedication> dermatologistForUpdate = dermatologistService.findOne(id);
        dermatologistForUpdate.get().copyValues(dermatologist);

        ScoreMedication updatedDermatologist = dermatologistService.update(dermatologistForUpdate.get());

        if (updatedDermatologist == null) {
            return new ResponseEntity<ScoreMedication>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ScoreMedication>(updatedDermatologist, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsScoreData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ScoreMedication> deletePharmacy(@PathVariable("id") Long id) {
        dermatologistService.delete(id);
        return new ResponseEntity<ScoreMedication>(HttpStatus.NO_CONTENT);
    }

}
