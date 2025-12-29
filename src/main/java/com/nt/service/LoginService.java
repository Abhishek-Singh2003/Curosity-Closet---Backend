package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Login;
import com.nt.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Login registerUser(Login login) {
        if (loginRepository.existsByEmail(login.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        return loginRepository.save(login);
    }

    public Optional<Login> loginUser(String email, String password) {
        return loginRepository.findByEmailAndPassword(email, password);
    }

    public Login resetPassword(String email, String newPassword) {
        Login login = loginRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
        login.setPassword(newPassword);
        return loginRepository.save(login);
    }

    public Boolean emailExists(String email) {
        return loginRepository.existsByEmail(email);
    }
}
