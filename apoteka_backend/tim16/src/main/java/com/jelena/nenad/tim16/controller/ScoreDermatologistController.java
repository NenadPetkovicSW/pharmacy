package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.ScoreDermatologist;
import com.jelena.nenad.tim16.service.ScoreDermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/dermatologistsScoreData")
public class ScoreDermatologistController {

    @Autowired
    private ScoreDermatologistService dermatologistService;

    /*
     * url: /api/dermatologistsScoreData GET
	 */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ScoreDermatologist>> getScores() {
        Collection<ScoreDermatologist> dermatologists = dermatologistService.findAll();
        return new ResponseEntity<Collection<ScoreDermatologist>>(dermatologists, HttpStatus.OK);
    }


    /*
     * url: /api/dermatologistsScoreData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreDermatologist> getDermatologist(@PathVariable("id") Long id) {
        Optional<ScoreDermatologist> dermatologist = dermatologistService.findOne(id);

        if (!dermatologist.isPresent()) {
            return new ResponseEntity<ScoreDermatologist>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ScoreDermatologist>(dermatologist.get(), HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsScoreData/1/1 GET
     */
    @GetMapping(value = "/{idUser}/{idDermatologist}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ScoreDermatologist>> getByUserAndDermatologist(@PathVariable("idUser") Long idUser,@PathVariable("idDermatologist") Long idDermatologist) {
        Collection<ScoreDermatologist> dermatologist = dermatologistService.getScoresByUserAndDermatologist(idUser,idDermatologist);

        return new ResponseEntity<Collection<ScoreDermatologist>>(dermatologist, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsScoreData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreDermatologist> createDermatologist(@RequestBody ScoreDermatologist dermatologist) throws Exception {
        ScoreDermatologist savedDermatologist = dermatologistService.create(dermatologist);
        return new ResponseEntity<ScoreDermatologist>(savedDermatologist, HttpStatus.CREATED);
    }

    /*
     * url: /api/dermatologistsScoreData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreDermatologist> updateDermatologist(@RequestBody ScoreDermatologist dermatologist, @PathVariable Long id)
            throws Exception {
        Optional<ScoreDermatologist> dermatologistForUpdate = dermatologistService.findOne(id);
        dermatologistForUpdate.get().copyValues(dermatologist);

        ScoreDermatologist updatedDermatologist = dermatologistService.update(dermatologistForUpdate.get());

        if (updatedDermatologist == null) {
            return new ResponseEntity<ScoreDermatologist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ScoreDermatologist>(updatedDermatologist, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsScoreData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ScoreDermatologist> deletePharmacy(@PathVariable("id") Long id) {
        dermatologistService.delete(id);
        return new ResponseEntity<ScoreDermatologist>(HttpStatus.NO_CONTENT);
    }

}
