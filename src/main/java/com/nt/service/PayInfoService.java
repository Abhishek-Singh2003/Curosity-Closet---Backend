package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.PayInfo;
import com.nt.repository.PayInfoRepository;

@Service
public class PayInfoService {

    @Autowired
    private PayInfoRepository payInfoRepository;

    public PayInfo savePayInfo(PayInfo payInfo) {
        return payInfoRepository.save(payInfo);
    }
}
