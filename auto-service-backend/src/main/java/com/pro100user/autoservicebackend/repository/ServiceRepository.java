package com.pro100user.autoservicebackend.repository;

import com.pro100user.autoservicebackend.entity.AutoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<AutoService, Long> {
}