package com.jelena.nenad.tim16.controller;


import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.Promotion;
import com.jelena.nenad.tim16.domain.User;
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
@RequestMapping("/api/promotionsData")
public class PromotionController {
    @Autowired
    private PromotionService promotionService; //interfejs

    /*
     * url: /api/promotionsData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Promotion>> getPromotions() {
        Collection<Promotion> p = promotionService.findAll();
        return new ResponseEntity<Collection<Promotion>>(p, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/promotionsData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Promotion> getPromotion(@PathVariable("id") Long id) {
        Optional<Promotion> p = promotionService.findOne(id);

        if (!p.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(p.get(), HttpStatus.OK);
    }

    // url: /api/promotionsData/pharmacy/1 GET
    @GetMapping(value = "/pharmacy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Promotion>> getAllPromotionPharmacy(@PathVariable("id") Long id) {
        Collection<Promotion> p = promotionService.findAll(id);
        return new ResponseEntity<Collection<Promotion>>(p, HttpStatus.OK);
    }

    /*
     * url: /api/promotionsData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion p) throws Exception {
        Promotion savedPromotion = promotionService.create(p);
        return new ResponseEntity<>(savedPromotion, HttpStatus.CREATED);
    }

    //ovo ce se koristiti samo za update na false
    /*
     * url: /api/promotionsData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Promotion> updatePromotion(@RequestBody Promotion p, @PathVariable Long id)
            throws Exception {
        Optional<Promotion> promotionForUpdate = promotionService.findOne(id);
        promotionForUpdate.get().copyValues(p);

        Promotion updatePromotion = promotionService.update(promotionForUpdate.get());

        if (updatePromotion == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    /*
     * url: /api/promotionsData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Promotion> deletePromotion(@PathVariable("id") Long id) {
        promotionService.delete(id);
        return new ResponseEntity<Promotion>(HttpStatus.NO_CONTENT);
    }
}
