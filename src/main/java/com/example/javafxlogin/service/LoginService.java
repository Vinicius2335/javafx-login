package com.example.javafxlogin.service;

import com.example.javafxlogin.models.User;
import com.example.javafxlogin.repository.LoginRepository;

import java.util.List;

public class LoginService {
    public static List<User> findByUserName(String username){
       return LoginRepository.findByUserName(username);
    }

    public static void save(User user) {
        LoginRepository.save(user);
    }
}
