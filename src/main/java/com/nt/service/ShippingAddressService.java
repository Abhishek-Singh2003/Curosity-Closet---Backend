package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.ShippingAddress;
import com.nt.repository.ShippingAddressRepository;

@Service
public class ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    public ShippingAddress saveShippingAddress(ShippingAddress address) {
        return shippingAddressRepository.save(address);
    }
}
