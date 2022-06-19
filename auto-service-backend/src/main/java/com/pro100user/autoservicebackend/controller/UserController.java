package com.pro100user.autoservicebackend.controller;

import com.pro100user.autoservicebackend.annotation.CurrentUser;
import com.pro100user.autoservicebackend.dto.*;
import com.pro100user.autoservicebackend.entity.Car;
import com.pro100user.autoservicebackend.security.UserSecurity;
import com.pro100user.autoservicebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("profile")
    public UserDTO profile(
            @CurrentUser UserSecurity userSecurity
    ) {
        return userService.getById(userSecurity.getId());
    }

    @PutMapping("profile")
    public UserDTO updateProfile(
            @Valid @RequestBody UserUpdateDTO dto,
            @CurrentUser UserSecurity userSecurity
    ) {
        dto.setId(userSecurity.getId());
        return userService.update(dto);
    }

    @DeleteMapping("profile")
    public boolean deleteProfile(
            @CurrentUser UserSecurity userSecurity
    ) {
        return userService.delete(userSecurity.getId());
    }

    @GetMapping("cars")
    public List<CarDTO> cars(
            @CurrentUser UserSecurity userSecurity
    ) {
        return userService.getCars(userSecurity.getId());
    }

    @GetMapping("orders")
    public List<OrderDTO> orders(
            @CurrentUser UserSecurity userSecurity
    ) {
        return userService.orders(userSecurity.getId());
    }

    @PostMapping("orders")
    public OrderDTO toOrder(
            @RequestBody OrderCreateDTO dto
    ) {
        return userService.toOrder(dto);
    }
}
