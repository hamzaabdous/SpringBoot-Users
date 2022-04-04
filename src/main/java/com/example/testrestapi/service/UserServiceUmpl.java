package com.example.testrestapi.service;

import com.example.testrestapi.model.User;
import com.example.testrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceUmpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User userSave(User user) {
        return userRepository.save(user);
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    public String DeleteId(int id){
        userRepository.deleteById(id);
        return "delete done";

    }
    @Override
    public Optional<User> UserCherche(int id){


        return userRepository.findById(id);

    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }



    @Override
    public Object UpdateUser(User user,int id) {
        return userRepository.save(user);
    }

}
