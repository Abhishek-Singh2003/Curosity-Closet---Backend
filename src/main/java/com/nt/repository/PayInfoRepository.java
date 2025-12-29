package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.PayInfo;

@Repository
public interface PayInfoRepository extends JpaRepository<PayInfo, Long> {
}
