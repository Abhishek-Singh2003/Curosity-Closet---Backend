package com.nt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "order_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String productName;

    @Column
    private String productImage;

    @Column
    private Double productPrice;

    @Column
    private LocalDate purchaseDate;

    @Column
    private LocalTime purchaseTime;

    @Column
    private Integer quantity;

    @Column
    private String orderId;
}
