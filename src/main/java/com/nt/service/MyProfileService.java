package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.MyProfile;
import com.nt.repository.MyProfileRepository;

@Service
public class MyProfileService {

    @Autowired
    private MyProfileRepository myProfileRepository;

    public MyProfile saveProfile(MyProfile profile) {
        Optional<MyProfile> existing = myProfileRepository.findByUserId(profile.getUserId());
        if (existing.isPresent()) {
            MyProfile existingProfile = existing.get();
            existingProfile.setName(profile.getName());
            existingProfile.setAddress(profile.getAddress());
            existingProfile.setMobileno(profile.getMobileno());
            existingProfile.setGender(profile.getGender());
            existingProfile.setPincode(profile.getPincode());
            return myProfileRepository.save(existingProfile);
        }
        return myProfileRepository.save(profile);
    }

    public Optional<MyProfile> getProfileByUserId(Long userId) {
        return myProfileRepository.findByUserId(userId);
    }

    public Optional<MyProfile> getProfileById(Long id) {
        return myProfileRepository.findById(id);
    }

    public MyProfile updateProfile(Long id, MyProfile profileDetails) {
        MyProfile profile = myProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        
        profile.setName(profileDetails.getName());
        profile.setAddress(profileDetails.getAddress());
        profile.setMobileno(profileDetails.getMobileno());
        profile.setGender(profileDetails.getGender());
        profile.setPincode(profileDetails.getPincode());
        
        return myProfileRepository.save(profile);
    }
}
