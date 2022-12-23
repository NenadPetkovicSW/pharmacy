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
@RequestMapping("/api/dermatologistsAppointmentData")
public class DermatologistAppointmentController {

    @Autowired
    private DermatologistAppointmentService dermatologistService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private UserService userService;

    @Autowired
    private DermatologistService dermaService;

    @Autowired
    private PharmacyService pharmacyService;
    /*
     * url: /api/dermatologistsAppointmentData GET
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<DermatologistAppointment>> getDermatologists() {
        Collection<DermatologistAppointment> dermatologists = dermatologistService.findAll();
        return new ResponseEntity<Collection<DermatologistAppointment>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsAppointmentData/byPharmacy/1 GET
     */
    @GetMapping(value = "/byPharmacy/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<DermatologistAppointment>> getDermAppointmentByPhar(@PathVariable("id") Long id) {
        Collection<DermatologistAppointment> dermatologists = dermatologistService.getDerAppointmentsByPhar(id);
        return new ResponseEntity<Collection<DermatologistAppointment>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsAppointmentData/byUser/1 GET
     */
    @GetMapping(value = "/byUser/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<DermatologistAppointment>> getDermAppointmentByUser(@PathVariable("id") Long id) {
        Collection<DermatologistAppointment> dermatologists = dermatologistService.getDerAppointmentsByUser(id);
        return new ResponseEntity<Collection<DermatologistAppointment>>(dermatologists, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsAppointmentData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DermatologistAppointment> getDermatologist(@PathVariable("id") Long id) {
        Optional<DermatologistAppointment> dermatologist = dermatologistService.findOne(id);

        if (!dermatologist.isPresent()) {
            return new ResponseEntity<DermatologistAppointment>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<DermatologistAppointment>(dermatologist.get(), HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsAppointmentData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DermatologistAppointment> createDermatologist(@RequestBody DermatologistAppointment dermatologist) throws Exception {
        DermatologistAppointment savedDermatologist = dermatologistService.create(dermatologist);
        return new ResponseEntity<DermatologistAppointment>(savedDermatologist, HttpStatus.CREATED);
    }

    /*
     * url: /api/dermatologistsAppointmentData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DermatologistAppointment> updateDermatologist(@RequestBody DermatologistAppointment dermatologist, @PathVariable Long id)
            throws Exception {
        Optional<DermatologistAppointment> dermatologistForUpdate = dermatologistService.findOne(id);
        dermatologistForUpdate.get().copyValues(dermatologist);

        DermatologistAppointment updatedDermatologist = dermatologistService.update(dermatologistForUpdate.get());

        if (updatedDermatologist == null) {
            return new ResponseEntity<DermatologistAppointment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<DermatologistAppointment>(updatedDermatologist, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsAppointmentData/1/1 PUT
     */
    @PutMapping(value = "/{idAp}/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> makeAppointment(@PathVariable("idAp") long idAppointment, @PathVariable("idUser") long idUser) throws Exception
    {
        int dermatologistForUpdate = dermatologistService.makeAppointment(idAppointment,idUser);

        if (dermatologistForUpdate != 1) {
            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<DermatologistAppointment> appointment = dermatologistService.findOne(idAppointment);

        Optional<Dermatologist> derma = dermaService.findOne(appointment.get().getDermatologistId());

        Optional<User> user = userService.findOne(idUser);

        Optional<Pharmacy> phar = pharmacyService.findOne(appointment.get().getPharmacyId());


        String eText = "Pregled:\nDermatolog: " + derma.get().getFirstName()+ " " + derma.get().getLastName() + "\nDatum pregleda: " + appointment.get().getDate().toString() +
                "\nCena: " + appointment.get().getPrice() + "\nVreme:" + appointment.get().getAppointmentStart() + " - " + appointment.get().getAppointmentEnd() + "\nApoteka: " + phar.get().getName();

        sendEmailService.sendEmail(user.get().getEmail(), eText, "Potvrda o pregledu kod dermatologa");

        return new ResponseEntity<Integer>(dermatologistForUpdate, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsAppointmentData/1/1 PUT
     */
    @PutMapping(value = "/cancel/{idAp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> cancelAppointment(@PathVariable("idAp") long idAppointment) throws Exception
    {
        int dermatologistForUpdate = dermatologistService.cancelAppointment(idAppointment);
        return new ResponseEntity<Integer>(dermatologistForUpdate, HttpStatus.OK);
    }

    /*
     * url: /api/dermatologistsAppointmentData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DermatologistAppointment> deletePharmacy(@PathVariable("id") Long id) {
        dermatologistService.delete(id);
        return new ResponseEntity<DermatologistAppointment>(HttpStatus.NO_CONTENT);
    }
}
