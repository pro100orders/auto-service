package com.pro100user.autoservicebackend.service;

import com.pro100user.autoservicebackend.entity.User;

public interface UserService {

    User findByEmail(String email);
}
