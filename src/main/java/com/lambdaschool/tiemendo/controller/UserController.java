package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.User;
import com.lambdaschool.tiemendo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping()
public class UserController
{

    @Autowired
    UserService userService;

    @GetMapping(value = "/users/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }


    @GetMapping(value = "/users/user/{userId}", produces = {"application/json"})
    public ResponseEntity<?> getUserById(@PathVariable Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    
    @GetMapping(value = "/user/usertype", produces = {"application/json"})
    public ResponseEntity<?> getUserType(Authentication authentication)
    {
        String username = authentication.getName();
        return new ResponseEntity<>(userService.findUserByName(username).getAuthority(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/username/{username}", produces = {"application/json"})
    public ResponseEntity<?> getUserByNameIgnoreCase(@PathVariable String username)
    {
        String upperCase = username.toUpperCase();
        List<User> u = userService.findUsersByUsernameIsLike(upperCase);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PostMapping(value = "/users/newuser", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> createUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser = userService.save(newuser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/userid")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(newuser, HttpStatus.CREATED);
    }


    @PutMapping(value = "/users/update-user/{userId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateUserById(@RequestBody User user, @PathVariable Long userId)
    {
        userService.update(user, userId);
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }


    @DeleteMapping("/users/user/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId)
    {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


