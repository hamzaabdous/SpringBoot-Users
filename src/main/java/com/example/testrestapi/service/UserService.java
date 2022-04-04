package com.example.testrestapi.service;

import com.example.testrestapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User userSave (User user );
    public List<User> findAll();
    public String  DeleteId(int id);
    public Optional<User> UserCherche (int id);
    public long countUsers();
    public Object UpdateUser(User user,int id);

}
