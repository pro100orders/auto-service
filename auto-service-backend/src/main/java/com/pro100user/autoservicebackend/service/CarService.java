package com.pro100user.autoservicebackend.service;

import com.pro100user.autoservicebackend.dto.*;
import com.pro100user.autoservicebackend.entity.User;

import java.util.List;

public interface CarService {

    CarDTO create(CarCreateDTO dto);
    CarDTO getById(Long carId);
    CarDTO update(CarUpdateDTO dto);
    boolean delete(Long carId);
    List<CarDTO> getAll();
    List<CarListDTO> getAllCarListDTO();
}
