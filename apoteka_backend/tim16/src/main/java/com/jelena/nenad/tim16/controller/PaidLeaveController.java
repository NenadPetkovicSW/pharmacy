package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.Allergie;
import com.jelena.nenad.tim16.domain.PaidLeave;
import com.jelena.nenad.tim16.service.AllergieService;
import com.jelena.nenad.tim16.service.PaidLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") //react url
@RestController
@RequestMapping("/api/paidLeaveData")
public class PaidLeaveController {

    @Autowired
    private PaidLeaveService service; //interfejs

    /*
     * url: /api/paidLeaveData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PaidLeave>> getPaidLeaves() {
        Collection<PaidLeave> obj = service.findAll();
        return new ResponseEntity<Collection<PaidLeave>>(obj, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/paidLeaveData/pharmacy/id GET
     */
    @GetMapping(value = "pharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PaidLeave>> getPaidLeaves(@PathVariable("id") Long id) {
        Collection<PaidLeave> obj = service.findAll(id);
        return new ResponseEntity<Collection<PaidLeave>>(obj, HttpStatus.OK); //200 OK
    }


    /*
     * url: /api/paidLeaveData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaidLeave> getPaidLeave(@PathVariable("id") Long id) {
        Optional<PaidLeave> obj = service.findOne(id);

        if (!obj.isPresent()) {
            return new ResponseEntity<PaidLeave>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PaidLeave>(obj.get(), HttpStatus.OK);
    }

    /*
     * url: /api/paidLeaveData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaidLeave> createPaidLeave(@RequestBody PaidLeave obj) throws Exception {
        PaidLeave saveObj = service.create(obj);
        return new ResponseEntity<PaidLeave>(saveObj, HttpStatus.CREATED);
    }

    /*
     * url: /api/paidLeaveData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaidLeave> updatePaidLeave(@RequestBody PaidLeave obj, @PathVariable Long id
                                        )
            throws Exception {
        Optional<PaidLeave> objsForUpdate = service.findOne(id);
        objsForUpdate.get().copyValues(obj);

        PaidLeave updatedObj = service.updateStatus(id,obj.getResponse(),obj.isApproved());

        if (updatedObj == null) {
            return new ResponseEntity<PaidLeave>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PaidLeave>(updatedObj, HttpStatus.OK);
    }
}
