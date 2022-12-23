package com.jelena.nenad.tim16.controller;


import com.jelena.nenad.tim16.domain.DermatologistWorkTime;
import com.jelena.nenad.tim16.domain.LoginObject;
import com.jelena.nenad.tim16.domain.User;
import com.jelena.nenad.tim16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000") //react url
@RestController
@RequestMapping("/api/login")
public class LoginController {


    @Autowired
    private UserService serviceUser; //interfejs


    /*
     * url: /api/login GET
     */
    @GetMapping(value = "/{user}/{pass}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> LoginUser(@PathVariable("user") String username, @PathVariable("pass") String password) {
        User loginUser = serviceUser.validateUser(username, password);

        if (loginUser == null) {
            return new ResponseEntity<User>(loginUser, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(loginUser, HttpStatus.OK);
    }


}
