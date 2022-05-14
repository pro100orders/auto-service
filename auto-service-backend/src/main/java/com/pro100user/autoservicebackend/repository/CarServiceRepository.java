package com.pro100user.autoservicebackend.repository;

import com.pro100user.autoservicebackend.entity.CarService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarServiceRepository extends JpaRepository<CarService, Long> {
}