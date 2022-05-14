package com.pro100user.autoservicebackend.repository;

import com.pro100user.autoservicebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
