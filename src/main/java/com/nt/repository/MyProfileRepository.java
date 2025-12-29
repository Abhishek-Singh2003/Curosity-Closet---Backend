package com.nt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.MyProfile;

@Repository
public interface MyProfileRepository extends JpaRepository<MyProfile, Long> {
    Optional<MyProfile> findByUserId(Long userId);
}
