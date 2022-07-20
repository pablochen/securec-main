package com.securec.main.controller;

import com.securec.main.domain.User;
import com.securec.main.service.UserCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/restful/api")
public class UserController {

    @Autowired
    private UserCrudService userCrudService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        boolean flag = userCrudService.createUser(user);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> userList(@PathVariable String userId) {
        Optional<User> user = userCrudService.findUserByUserId(userId);
        if(user.isPresent()){
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<User> patchUser(@PathVariable String userId, @RequestBody User user) {
        User updateUser = userCrudService.patchUser(userId, user);
        if(updateUser != null){
            return new ResponseEntity<User>(updateUser, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

}