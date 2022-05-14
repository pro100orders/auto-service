package com.pro100user.autoservicebackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "services")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "description", nullable = false)
    private String description;



    @OneToMany(mappedBy = "carService", fetch = FetchType.LAZY, targetEntity = Order.class)
    private Set<Order> orders = new HashSet<>();
}
