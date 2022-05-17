package com.pro100user.autoservicebackend.entity;

import com.pro100user.autoservicebackend.entity.enums.DriveType;
import com.pro100user.autoservicebackend.entity.enums.Fuel;
import com.pro100user.autoservicebackend.entity.enums.Transmission;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "cars")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "vin_code", nullable = false)
    private String vinCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel", nullable = false)
    private Fuel fuel;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission", nullable = false)
    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "drive_type", nullable = false)
    private DriveType driveType;



    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, targetEntity = Order.class)
    private Set<Order> orders = new HashSet<>();
}
