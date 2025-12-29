package com.nt.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.MyProfile;
import com.nt.service.MyProfileService;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class MyProfileController {

    @Autowired
    private MyProfileService myProfileService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProfile(@RequestBody MyProfile profile) {
        try {
            MyProfile savedProfile = myProfileService.saveProfile(profile);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Profile saved successfully");
            response.put("profile", savedProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        Optional<MyProfile> profile = myProfileService.getProfileById(id);
        
        Map<String, Object> response = new HashMap<>();
        if (profile.isPresent()) {
            response.put("success", true);
            response.put("profile", profile.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Profile not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable Long userId) {
        Optional<MyProfile> profile = myProfileService.getProfileByUserId(userId);
        
        Map<String, Object> response = new HashMap<>();
        if (profile.isPresent()) {
            response.put("success", true);
            response.put("profile", profile.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Profile not found");
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody MyProfile profileDetails) {
        try {
            MyProfile updatedProfile = myProfileService.updateProfile(id, profileDetails);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Profile updated successfully");
            response.put("profile", updatedProfile);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
