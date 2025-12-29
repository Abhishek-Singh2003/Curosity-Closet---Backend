package com.nt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "my_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private String mobileno;

    @Column
    private String gender;

    @Column
    private String pincode;
}
