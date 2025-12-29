package com.nt.controller;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.OrderDetail;
import com.nt.entity.PayInfo;
import com.nt.entity.PaymentDetails;
import com.nt.entity.ShippingAddress;
import com.nt.service.OrderDetailService;
import com.nt.service.PayInfoService;
import com.nt.service.PaymentDetailsService;
import com.nt.service.ShippingAddressService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PayInfoService payInfoService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@RequestBody Map<String, Object> paymentData) {
        try {
            Long userId = Long.valueOf(paymentData.get("userId").toString());
            
            // Save PayInfo
            PayInfo payInfo = new PayInfo();
            payInfo.setUserId(userId);
            payInfo.setFirstName((String) paymentData.get("firstName"));
            payInfo.setLastName((String) paymentData.get("lastName"));
            payInfo.setMobileno((String) paymentData.get("phone"));
            payInfo.setEmail((String) paymentData.get("email"));
            payInfoService.savePayInfo(payInfo);

            // Save ShippingAddress
            ShippingAddress shippingAddress = new ShippingAddress();
            shippingAddress.setUserId(userId);
            shippingAddress.setAddress((String) paymentData.get("address"));
            shippingAddress.setCity((String) paymentData.get("city"));
            shippingAddress.setZipcode((String) paymentData.get("zipCode"));
            shippingAddressService.saveShippingAddress(shippingAddress);

            // Save PaymentDetails
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setUserId(userId);
            paymentDetails.setCardno((String) paymentData.get("cardNumber"));
            paymentDetails.setExpiredate((String) paymentData.get("expiryDate"));
            paymentDetails.setCvv((String) paymentData.get("cvv"));
            paymentDetails.setCardHolderName((String) paymentData.get("cardName"));
            paymentDetails.setPaymentDate(LocalDate.now());
            paymentDetailsService.savePaymentDetails(paymentDetails);

            // Save OrderDetails
            List<Map<String, Object>> cartItems = (List<Map<String, Object>>) paymentData.get("cartItems");
            String orderId = "ORD" + System.currentTimeMillis();
            
            for (Map<String, Object> item : cartItems) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setUserId(userId);
                orderDetail.setProductName((String) item.get("name"));
                orderDetail.setProductImage((String) item.get("img"));
                orderDetail.setProductPrice(Double.valueOf(item.get("price").toString()));
                orderDetail.setPurchaseDate(LocalDate.now());
                orderDetail.setPurchaseTime(LocalTime.now());
                orderDetail.setQuantity(Integer.valueOf(item.get("quantity").toString()));
                orderDetail.setOrderId(orderId);
                orderDetailService.saveOrderDetail(orderDetail);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Payment processed successfully");
            response.put("orderId", orderId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
