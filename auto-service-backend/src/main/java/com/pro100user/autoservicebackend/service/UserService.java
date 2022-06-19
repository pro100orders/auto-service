package com.pro100user.autoservicebackend.service;

import com.pro100user.autoservicebackend.dto.*;
import com.pro100user.autoservicebackend.entity.User;

import java.util.List;

public interface UserService {

    UserDTO create(UserCreateDTO dto);
    UserDTO getById(Long userId);
    UserDTO update(UserUpdateDTO dto);
    boolean delete(Long userId);
    List<UserDTO> getAll();

    User findByEmail(String email);
    User findByPhone(String phone);

    List<CarDTO> getCars(Long userId);

    List<OrderDTO> orders(Long userId);
    OrderDTO toOrder(OrderCreateDTO dto);
}
