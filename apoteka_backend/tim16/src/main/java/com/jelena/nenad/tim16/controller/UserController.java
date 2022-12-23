package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.User;
import com.jelena.nenad.tim16.service.SendEmailService;
import com.jelena.nenad.tim16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") //react url
@RestController
@RequestMapping("/api/usersData")
public class UserController {

    @Autowired
    private UserService userService; //interfejs
    @Autowired
    private SendEmailService sendEmailService;
    /*
     * url: /api/usersData GET
	 */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> getUsers() {
        Collection<User> users = userService.findAll();
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/usersData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.findOne(id);

        if (!user.isPresent()) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    /*
     * url: /api/usersData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        User savedUser = userService.create(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    /*
     * url: /api/usersData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id)
            throws Exception {
        Optional<User> userForUpdate = userService.findOne(id);
        userForUpdate.get().copyValuesNoEmail(user);

        User updatedUser = userService.update(userForUpdate.get());

        if (updatedUser == null) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    /*
     * url: /api/usersData/promotions/1/1 PUT
     */
    @PutMapping (value = "/promotions/{userId}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addPromotion(@PathVariable("userId") Long userId,@PathVariable("id") Long id)
            throws Exception {
        Optional<User> userForUpdate = userService.findOne(userId);
        userForUpdate.get().addPromotions(id.toString());
        sendEmailService.sendEmail(userForUpdate.get().getEmail(),"Uspesno ste se prijavili na promociju", "Promocija iz apoteke");
        if (userForUpdate.get() == null) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<User>(userForUpdate.get(),HttpStatus.OK);
    }

    /*
     * url: /api/usersData/actions/1/1 PUT
     */
    @PutMapping (value = "/actions/{userId}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addAction(@PathVariable("userId") Long userId,@PathVariable("id") Long id)
            throws Exception {
        Optional<User> userForUpdate = userService.findOne(userId);
        userForUpdate.get().addActions(id.toString());
        sendEmailService.sendEmail(userForUpdate.get().getEmail(),"Uspesno ste se prijavili na akciju", "Akcija iz apoteke");
        if (userForUpdate.get() == null) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<User>(userForUpdate.get(),HttpStatus.OK);
    }

    /*
     * url: /api/usersData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
