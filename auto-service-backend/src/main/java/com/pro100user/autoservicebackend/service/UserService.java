package com.pro100user.autoservicebackend.service;

import com.pro100user.autoservicebackend.dto.UserCreateDTO;
import com.pro100user.autoservicebackend.dto.UserDTO;
import com.pro100user.autoservicebackend.dto.UserUpdateDTO;
import com.pro100user.autoservicebackend.entity.User;

public interface UserService {

    boolean create(UserCreateDTO dto);
    UserDTO getById(Long userId);
    UserDTO update(UserUpdateDTO dto);
    boolean delete(Long userId);

    User findByEmail(String email);
    User findByPhone(String phone);
}
