package com.pro100user.autoservicebackend.controller;

import com.pro100user.autoservicebackend.dto.*;
import com.pro100user.autoservicebackend.entity.enums.Status;
import com.pro100user.autoservicebackend.service.CarService;
import com.pro100user.autoservicebackend.service.OrderService;
import com.pro100user.autoservicebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final UserService userService;
    private final CarService carService;
    private final OrderService orderService;

    @GetMapping("users")
    public List<UserDTO> users() {
        return userService.getAll();
    }

    @GetMapping("users/{id}")
    public UserDTO userDetails(
            @PathVariable("id") Long id
    ) {
        return userService.getById(id);
    }

    @PostMapping("users")
    public UserDTO createUser(
            @RequestBody UserCreateDTO dto
    ) {
        return userService.create(dto);
    }

    @PutMapping("users")
    public UserDTO updateUser(
            @RequestBody UserUpdateDTO dto
    ) {
        return userService.update(dto);
    }

    @DeleteMapping("users/{id}")
    public boolean deleteUser(
            @PathVariable("id") Long id
    ) {
        return userService.delete(id);
    }

    @GetMapping("cars")
    public List<CarListDTO> cars() {
        return carService.getAllCarListDTO();
    }

    @PostMapping("cars")
    public CarDTO createCar(
            @RequestBody CarCreateDTO dto
    ) {
        return carService.create(dto);
    }

    @PutMapping("cars")
    public CarDTO updateCar(
            @RequestBody CarUpdateDTO dto
    ) {
        return carService.update(dto);
    }

    @GetMapping("orders")
    public List<OrderDTO> orders() {
        return orderService.getAll();
    }

    @PutMapping("orders")
    public OrderDTO updateOrder(
            @RequestBody OrderUpdateDTO dto
    ) {
        return orderService.update(dto);
    }

    @DeleteMapping("/orders/{id}")
    public boolean deleteOrder(
            @PathVariable("id") Long id
    ) {
        return orderService.delete(id);
    }

    @GetMapping("/order-statuses")
    public List<String> statuses() {
        return Arrays.stream(Status.values())
                .map(Enum::name)
                .toList();
    }
}
