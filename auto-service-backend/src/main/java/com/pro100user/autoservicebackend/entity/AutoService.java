package com.pro100user.autoservicebackend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "services")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AutoService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "description", nullable = false)
    private String description;


    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, targetEntity = Order.class)
    private List<Order> orders = new ArrayList<>();
}
