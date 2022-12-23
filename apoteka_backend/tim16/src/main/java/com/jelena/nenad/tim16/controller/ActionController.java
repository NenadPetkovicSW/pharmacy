package com.jelena.nenad.tim16.controller;


import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.Promotion;
import com.jelena.nenad.tim16.domain.User;
import com.jelena.nenad.tim16.service.ActionService;
import com.jelena.nenad.tim16.service.PromotionService;
import com.jelena.nenad.tim16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/actionsData")
public class ActionController {
    @Autowired
    private ActionService actionService; //interfejs

    /*
     * url: /api/actionsData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Action>> getActions() {
        Collection<Action> p = actionService.findAll();
        return new ResponseEntity<Collection<Action>>(p, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/actionsData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Action> getAction(@PathVariable("id") Long id) {
        Optional<Action> p = actionService.findOne(id); //find all in certain pharmacy
        //trazimo ih preko medicationID-a
        if (!p.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(p.get(), HttpStatus.OK);
    }

    // url: /api/actionsData/pharmacy/1 GET
    @GetMapping(value = "/pharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Action>> getAllActionPharmacy(@PathVariable("id") Long id) {
        Collection<Action> p = actionService.findAll(id);

        return new ResponseEntity<Collection<Action>>(p, HttpStatus.OK);
    }

    /*
     * url: /api/actionsData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Action> createAction(@RequestBody Action p) throws Exception {
        Action savedAction = actionService.create(p);
        return new ResponseEntity<>(savedAction, HttpStatus.CREATED);
    }

    //ovo ce se koristiti samo za update na false
    /*
     * url: /api/actionsData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Action> updatePromotion(@RequestBody Action p, @PathVariable Long id)
            throws Exception {
        Optional<Action> actionToUpdate = actionService.findOne(id);
        actionToUpdate.get().copyValues(p);

        Action updatedAction = actionService.update(actionToUpdate.get());

        if (updatedAction == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    /*
     * url: /api/actionsData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Action> deleteAction(@PathVariable("id") Long id) {
        actionService.delete(id);
        return new ResponseEntity<Action>(HttpStatus.NO_CONTENT);
    }
}
