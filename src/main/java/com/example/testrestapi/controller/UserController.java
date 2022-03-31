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
    public ObjectNode add(@RequestBody User user){
        ObjectNode objectNodeBad = (ObjectNode) mapper.createObjectNode();
        objectNodeBad.put("status", "404-user");
        objectNodeBad.put("description", "user not add");
        ObjectNode objectNodeoK = (ObjectNode) mapper.createObjectNode();
        objectNodeoK.put("status", "DONE");
        objectNodeoK.put("description", "user add Add");
        if (user.getName()==null){
            return objectNodeBad;
        }
        else {
            userService.userSave(user);
            return objectNodeoK;
        }
    }

    @GetMapping("/")
    public List<User> findAll(){
        List<User> users= userService.findAll();
        System.err.println(users);
        return users;
    }
    @PostMapping("/delete/{id}")
    public ObjectNode DeleteId(@PathVariable("id") int id){
        ObjectNode objectNodeBad = (ObjectNode) mapper.createObjectNode();
        objectNodeBad.put("status", "404-user");
        objectNodeBad.put("description", "user not found");
        ObjectNode objectNodeOk = (ObjectNode) mapper.createObjectNode();
        objectNodeOk.put("status", "DONE");
        objectNodeOk.put("description", "user is found");

        Optional<Object> userIsFound =UserCherche(id);
        if(userIsFound.isEmpty()){
            return objectNodeBad;
        }
        else {
            userService.DeleteId(id);
            return objectNodeOk;
        }
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
}
