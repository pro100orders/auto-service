package com.pro100user.autoservicebackend.controller;

import com.pro100user.autoservicebackend.annotation.CurrentUser;
import com.pro100user.autoservicebackend.dto.CarCreateDTO;
import com.pro100user.autoservicebackend.dto.CarDTO;
import com.pro100user.autoservicebackend.dto.CarUpdateDTO;
import com.pro100user.autoservicebackend.entity.enums.DriveType;
import com.pro100user.autoservicebackend.entity.enums.Fuel;
import com.pro100user.autoservicebackend.entity.enums.Transmission;
import com.pro100user.autoservicebackend.security.UserSecurity;
import com.pro100user.autoservicebackend.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("cars")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {

    public final CarService carService;

    @GetMapping
    public List<CarDTO> services() {
        return carService.getAll();
    }

    @GetMapping("{id}")
    public CarDTO details(
            @PathVariable("id") Long id
    ) {
        return carService.getById(id);
    }

    @PostMapping
    public CarDTO create(
            @RequestBody CarCreateDTO dto,
            @CurrentUser UserSecurity userSecurity
    ) {
        dto.setUserId(userSecurity.getId());
        return carService.create(dto);
    }

    @PutMapping
    public CarDTO update(
            @RequestBody CarUpdateDTO dto,
            @CurrentUser UserSecurity userSecurity
    ) {
        dto.setUserId(userSecurity.getId());
        return carService.update(dto);
    }

    @DeleteMapping("{id}")
    public boolean delete(
            @PathVariable("id") Long id
    ) {
        return carService.delete(id);
    }

    @GetMapping("fuels")
    public List<String> fuels() {
        return Arrays.stream(Fuel.values()).map(Enum::name).toList();
    }

    @GetMapping("transmissions")
    public List<String> transmissions() {
        return Arrays.stream(Transmission.values()).map(Enum::name).toList();
    }

    @GetMapping("drive-types")
    public List<String> driveTypes() {
        return Arrays.stream(DriveType.values()).map(Enum::name).toList();
    }
}
