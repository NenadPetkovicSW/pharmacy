package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.DermatologistWorkTime;
import com.jelena.nenad.tim16.service.DermatologistWorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/dermatologistsWorkTimeData")
public class DermatologistWorkTimeController {

    @Autowired
    private DermatologistWorkTimeService dermatologistService;

    /*
     * url: /api/dermatologistsWorkTimeData GET
	 */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<DermatologistWorkTime>> getDermatologists() {
        Collection<DermatologistWorkTime> dermatologists = dermatologistService.findAll();
        return new ResponseEntity<Collection<DermatologistWorkTime>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsWorkTimeData/all/1 GET
     */
    @GetMapping(value = "all/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<DermatologistWorkTime>> getDermatologistsAll(@PathVariable("id") Long id) {
        Collection<DermatologistWorkTime> dermatologists = dermatologistService.findAll(id);
        return new ResponseEntity<Collection<DermatologistWorkTime>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsWorkTimeData/pharmacyAll/1 GET
     */
    @GetMapping(value = "pharmacyAll/{id}/{pharmacy}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<DermatologistWorkTime>> getDermatologistsAllPharmacy(@PathVariable("id") Long id,
                                                                                          @PathVariable("pharmacy") Long pharmacy) {
        Collection<DermatologistWorkTime> dermatologists = dermatologistService.findAllPharmacy(id,pharmacy);
        return new ResponseEntity<Collection<DermatologistWorkTime>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsWorkTimeData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DermatologistWorkTime> getDermatologist(@PathVariable("id") Long id) {
        Optional<DermatologistWorkTime> dermatologist = dermatologistService.findOne(id);

        if (!dermatologist.isPresent()) {
            return new ResponseEntity<DermatologistWorkTime>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<DermatologistWorkTime>(dermatologist.get(), HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsWorkTimeData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DermatologistWorkTime> createDermatologist(@RequestBody DermatologistWorkTime dermatologist) throws Exception {
        DermatologistWorkTime savedDermatologist = dermatologistService.create(dermatologist);
        return new ResponseEntity<DermatologistWorkTime>(savedDermatologist, HttpStatus.CREATED);
    }

    /*
     * url: /api/dermatologistsWorkTimeData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DermatologistWorkTime> updateDermatologist(@RequestBody DermatologistWorkTime dermatologist, @PathVariable Long id)
            throws Exception {
        Optional<DermatologistWorkTime> dermatologistForUpdate = dermatologistService.findOne(id);
        dermatologistForUpdate.get().copyValues(dermatologist);

        DermatologistWorkTime updatedDermatologist = dermatologistService.update(dermatologistForUpdate.get());

        if (updatedDermatologist == null) {
            return new ResponseEntity<DermatologistWorkTime>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DermatologistWorkTime>(updatedDermatologist, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsWorkTimeData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DermatologistWorkTime> deletePharmacy(@PathVariable("id") Long id) {
        dermatologistService.delete(id);
        return new ResponseEntity<DermatologistWorkTime>(HttpStatus.NO_CONTENT);
    }
}
