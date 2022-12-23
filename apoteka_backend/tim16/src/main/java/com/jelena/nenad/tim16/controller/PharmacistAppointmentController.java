package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.*;
import com.jelena.nenad.tim16.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pharmacistsAppointmentData")
public class PharmacistAppointmentController {

    @Autowired
    private PharmacistAppointmentService pharmacistService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private UserService userService;

    @Autowired
    private PharmacistService pharService;

    @Autowired
    private PharmacyService pharmacyService;

    /*
     * url: /api/pharmacistsAppointmentData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PharmacistAppointment>> getPharmacists() {
        Collection<PharmacistAppointment> pharmacistAppointment = pharmacistService.findAll();
        return new ResponseEntity<Collection<PharmacistAppointment>>(pharmacistAppointment, HttpStatus.OK);
    }
    /*
     * url: /api/pharmacistsAppointmentData/byUser/1 GET
     */
    @GetMapping(value = "/byUser/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PharmacistAppointment>> getPharAppointmentByUser(@PathVariable("id") Long id) {
        Collection<PharmacistAppointment> dermatologists = pharmacistService.getAppointmentByUser(id);
        return new ResponseEntity<Collection<PharmacistAppointment>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsAppointmentData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PharmacistAppointment> getPharmacist(@PathVariable("id") Long id) {
        Optional<PharmacistAppointment> pharmacistAppointment = pharmacistService.findOne(id);

        if (!pharmacistAppointment.isPresent()) {
            return new ResponseEntity<PharmacistAppointment>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PharmacistAppointment>(pharmacistAppointment.get(), HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsAppointmentData/free/1 GET
     */
    @GetMapping(value = "/free/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PharmacistAppointment>> getFreePharmacist(@PathVariable("id") Long id) {
        Collection<PharmacistAppointment> pharmacistAppointment = pharmacistService.getPAppointmentByPharmacist(id);

        return new ResponseEntity<Collection<PharmacistAppointment>>(pharmacistAppointment, HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsAppointmentData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PharmacistAppointment> createDermatologist(@RequestBody PharmacistAppointment pharmacistAppointment) throws Exception {
        PharmacistAppointment savedPharmacistAppointment = pharmacistService.create(pharmacistAppointment);
        return new ResponseEntity<PharmacistAppointment>(savedPharmacistAppointment, HttpStatus.CREATED);
    }

    /*
     * url: /api/pharmacistsAppointmentData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PharmacistAppointment> updateDermatologist(@RequestBody PharmacistAppointment pharmacistAppointment, @PathVariable Long id)
            throws Exception {
        Optional<PharmacistAppointment> pharmacistForUpdate = pharmacistService.findOne(id);
        pharmacistForUpdate.get().copyValues(pharmacistAppointment);

        PharmacistAppointment updatedPharmacist = pharmacistService.update(pharmacistForUpdate.get());

        if (updatedPharmacist == null) {
            return new ResponseEntity<PharmacistAppointment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PharmacistAppointment>(updatedPharmacist, HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsAppointmentData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PharmacistAppointment> deletePharmacistAppointment(@PathVariable("id") Long id) {
        pharmacistService.delete(id);
        return new ResponseEntity<PharmacistAppointment>(HttpStatus.NO_CONTENT);
    }

    /*
     * url: /api/pharmacistsAppointmentData/1/1 PUT
     */

    @PutMapping(value = "/{idAp}/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> makeAppointment(@PathVariable("idAp") long idAppointment, @PathVariable("idUser") long idUser) throws Exception
    {
        int pharmacistForUpdate = pharmacistService.makeAppointment(idAppointment,idUser);

        if (pharmacistForUpdate != 1) {
            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<PharmacistAppointment> appointment = pharmacistService.findOne(idAppointment);

        Optional<Pharmacist> derma = pharService.findOne(appointment.get().getPharmacistId());

        Optional<User> user = userService.findOne(idUser);

        Optional<Pharmacy> phar = pharmacyService.findOne(appointment.get().getPharmacyId());


        String eText = "Pregled:\nFarmaceut: " + derma.get().getFirstName()+ " " + derma.get().getLastName() + "\nDatum pregleda: " + appointment.get().getDate().toString() +
                "\nCena: " + appointment.get().getPrice() + "\nVreme:" + appointment.get().getAppointmentStart() + " - " + appointment.get().getAppointmentEnd() + "\nApoteka: " + phar.get().getName();

        sendEmailService.sendEmail(user.get().getEmail(), eText, "Potvrda o pregledu kod farmaceuta");
        return new ResponseEntity<Integer>(pharmacistForUpdate, HttpStatus.OK);
    }

    /*
     * url: /api/pharmacistsAppointmentData/1/1 PUT
     */
    @PutMapping(value = "/cancel/{idAp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> cancelAppointment(@PathVariable("idAp") long idAppointment) throws Exception
    {
        int pharmacistForUpdate = pharmacistService.cancelAppointment(idAppointment);
        return new ResponseEntity<Integer>(pharmacistForUpdate, HttpStatus.OK);
    }
}
