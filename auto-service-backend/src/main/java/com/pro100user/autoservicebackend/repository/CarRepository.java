package com.pro100user.autoservicebackend.repository;

import com.pro100user.autoservicebackend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
