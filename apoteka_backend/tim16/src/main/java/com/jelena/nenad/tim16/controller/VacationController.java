package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.PaidLeave;
import com.jelena.nenad.tim16.domain.Vacation;
import com.jelena.nenad.tim16.service.PaidLeaveService;
import com.jelena.nenad.tim16.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") //react url
@RestController
@RequestMapping("/api/vacationData")
public class VacationController {
    @Autowired
    private VacationService service; //interfejs

    /*
     * url: /api/vacationData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Vacation>> getVacations() {
        Collection<Vacation> obj = service.findAll();
        return new ResponseEntity<Collection<Vacation>>(obj, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/paidLeaveData/pharmacy/id GET
     */
    @GetMapping(value = "pharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Vacation>> getVacations(@PathVariable("id") Long id) {
        Collection<Vacation> obj = service.findAll(id);
        return new ResponseEntity<Collection<Vacation>>(obj, HttpStatus.OK); //200 OK
    }


    /*
     * url: /api/vacationData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vacation> getVacation(@PathVariable("id") Long id) {
        Optional<Vacation> obj = service.findOne(id);

        if (!obj.isPresent()) {
            return new ResponseEntity<Vacation>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Vacation>(obj.get(), HttpStatus.OK);
    }

    /*
     * url: /api/vacationData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vacation> createVacation(@RequestBody Vacation obj) throws Exception {
        Vacation saveObj = service.create(obj);
        return new ResponseEntity<Vacation>(saveObj, HttpStatus.CREATED);
    }

    /*
     * url: /api/vacationData/1 PUT
     */
    @PutMapping(value = "/{id}/{approved}/{message}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vacation> updateVacation(@RequestBody Vacation obj, @PathVariable Long id,
                                                     @PathVariable Boolean approved, @PathVariable String message)
            throws Exception {
        Optional<Vacation> objsForUpdate = service.findOne(id);
        objsForUpdate.get().copyValues(obj);

        Vacation updatedObj = service.updateStatus(id,message,approved);

        if (updatedObj == null) {
            return new ResponseEntity<Vacation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Vacation>(updatedObj, HttpStatus.OK);
    }
}
