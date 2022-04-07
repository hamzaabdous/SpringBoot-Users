package com.example.testrestapi.controller;


import com.example.testrestapi.model.User;
import com.example.testrestapi.service.UserService;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectCodec mapper;

    @PostMapping("/add")
    public Optional<User> add(@RequestBody User user){

        Optional<User> userCreated=userService.userSave(user);

            return userService.userSave(user);

    }

    @GetMapping("/")
    public List<User> findAll(){
        List<User> users= userService.findAll();
        System.err.println(users);
        return users;
    }
    @PostMapping("/delete/{id}")
    public void DeleteId(@PathVariable("id") int id){
            userService.DeleteId(id);
    }
    @GetMapping("/{id}")
    public Optional<Object> UserCherche(@PathVariable("id") int id){
        ObjectNode objectNode = (ObjectNode) mapper.createObjectNode();
        objectNode.put("status", "404-user");
        objectNode.put("description", "user not found");

        Optional<User> user= userService.UserCherche(id);
        if(user.isEmpty()){
            return Optional.of(objectNode);
        }
        else  return Optional.of(user);

        // System.err.println(user);
    }
    @GetMapping("/count")
    public ObjectNode countUsers(){
        ObjectNode objectNode = (ObjectNode) mapper.createObjectNode();
        objectNode.put("count", userService.countUsers());

        return objectNode ;
    }
    @PutMapping("/update")
    public User UpdateUser(@RequestBody User user){
        return userService.UpdateUser(user, user.getId());
    }
}
