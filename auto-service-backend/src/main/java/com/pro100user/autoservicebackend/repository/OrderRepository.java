package com.pro100user.autoservicebackend.repository;

import com.pro100user.autoservicebackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
