package com.jelena.nenad.tim16.controller;

import com.jelena.nenad.tim16.domain.UserRoles;
import com.jelena.nenad.tim16.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") //react url
@RestController
@RequestMapping("/api/rolesData")
public class UserRolesController {

    @Autowired
    private UserRolesService userService; //interfejs

    /*
     * url: /api/rolesData GET
	 */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserRoles>> getRoles() {
        Collection<UserRoles> users = userService.findAll();
        return new ResponseEntity<Collection<UserRoles>>(users, HttpStatus.OK); //200 OK
    }

    /*
     * url: /api/rolesData/1 GET
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoles> getRoles(@PathVariable("id") Long id) {
        Optional<UserRoles> user = userService.findOne(id);

        if (!user.isPresent()) {
            return new ResponseEntity<UserRoles>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserRoles>(user.get(), HttpStatus.OK);
    }

    /*
     * url: /api/rolesData/1 GET
     */
    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoles> getUserRole(@PathVariable("userId") Long userid) {
        UserRoles user = userService.getUserRole(userid);

        if (user == null) {
            return new ResponseEntity<UserRoles>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserRoles>(user, HttpStatus.OK);
    }

    /*
     * url: /api/rolesData POST
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoles> createRoles(@RequestBody UserRoles user) throws Exception {
        UserRoles savedUser = userService.create(user);
        return new ResponseEntity<UserRoles>(savedUser, HttpStatus.CREATED);
    }

    /*
     * url: /api/rolesData/1 PUT
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoles> updateRoles(@RequestBody UserRoles user, @PathVariable Long id)
            throws Exception {
        Optional<UserRoles> userForUpdate = userService.findOne(id);
        userForUpdate.get().copyValues(user);

        UserRoles updatedUser = userService.update(userForUpdate.get());

        if (updatedUser == null) {
            return new ResponseEntity<UserRoles>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UserRoles>(updatedUser, HttpStatus.OK);
    }

    /*
     * url: /api/rolesData/1 DELETE
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserRoles> deleteRoles(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<UserRoles>(HttpStatus.NO_CONTENT);
    }
}
