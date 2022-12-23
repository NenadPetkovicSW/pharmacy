package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.DermatologistWorkTime;
import com.jelena.nenad.tim16.domain.Pharmacy;
import com.jelena.nenad.tim16.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/dermatologistsData")
public class DermatologistController {

    @Autowired
    private DermatologistService dermatologistService;

    /*
     * url: /api/dermatologistsData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Dermatologist>> getDermatologists() {
        Collection<Dermatologist> dermatologists = dermatologistService.findAll();
        return new ResponseEntity<Collection<Dermatologist>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsData GET
     */
    @GetMapping(value = "/score/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Dermatologist>> getDermatologistsToScore(@PathVariable("userId") Long userId) {
        Collection<Dermatologist> dermatologists = dermatologistService.findDermatologistToScore(userId);
        return new ResponseEntity<Collection<Dermatologist>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsData/search/name/last_name/pharmacy_id GET
     */
    @GetMapping(value = "/search/{name}/{lastname}/{pharmacyid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Dermatologist>> getDermatologistsByQuery(@PathVariable("name") String name,
                                                                              @PathVariable("lastname") String lastName,
                                                                              @PathVariable("pharmacyid") Long pharmacyId) {
        Collection<Dermatologist> dermatologists = dermatologistService.findAllByName(name,lastName,pharmacyId);
        return new ResponseEntity<Collection<Dermatologist>>(dermatologists, HttpStatus.OK);
    }


    /*
     * url: /api/dermatologistsData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dermatologist> getDermatologist(@PathVariable("id") Long id) {
        Optional<Dermatologist> dermatologist = dermatologistService.findOne(id);

        if (!dermatologist.isPresent()) {
            return new ResponseEntity<Dermatologist>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Dermatologist>(dermatologist.get(), HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dermatologist> createDermatologist(@RequestBody Dermatologist dermatologist) throws Exception {
        Dermatologist savedDermatologist = dermatologistService.create(dermatologist);
        return new ResponseEntity<Dermatologist>(savedDermatologist, HttpStatus.CREATED);
    }

    /*
     * url: /api/dermatologistsData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dermatologist> updateDermatologist(@RequestBody Dermatologist dermatologist, @PathVariable Long id)
            throws Exception {
        Optional<Dermatologist> dermatologistForUpdate = dermatologistService.findOne(id);
        dermatologistForUpdate.get().copyValues(dermatologist);

        Dermatologist updatedDermatologist = dermatologistService.update(dermatologistForUpdate.get());

        if (updatedDermatologist == null) {
            return new ResponseEntity<Dermatologist>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Dermatologist>(updatedDermatologist, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Dermatologist> deletePharmacy(@PathVariable("id") Long id) {
        dermatologistService.delete(id);
        return new ResponseEntity<Dermatologist>(HttpStatus.NO_CONTENT);
    }

    /*
     * url: /api/dermatologistsData/1/1 GET
     */
    @GetMapping(value = "/{dId}/{pId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DermatologistWorkTime> getDermatologistWorkTime(@PathVariable("dId") long dId, @PathVariable("pId") long pId) {
        DermatologistWorkTime dermatologistWorkTime = dermatologistService.getWorkTime(dId, pId);

        if (dermatologistWorkTime == null) {
            return new ResponseEntity<DermatologistWorkTime>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<DermatologistWorkTime>(dermatologistWorkTime, HttpStatus.OK);
    }
}
