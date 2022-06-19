package com.pro100user.autoservicebackend.service.impl;

import com.pro100user.autoservicebackend.dto.CarCreateDTO;
import com.pro100user.autoservicebackend.dto.CarDTO;
import com.pro100user.autoservicebackend.dto.CarListDTO;
import com.pro100user.autoservicebackend.dto.CarUpdateDTO;
import com.pro100user.autoservicebackend.entity.Car;
import com.pro100user.autoservicebackend.mapper.CarMapper;
import com.pro100user.autoservicebackend.repository.CarRepository;
import com.pro100user.autoservicebackend.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarDTO create(CarCreateDTO dto) {
        Car entity = carMapper.toEntity(dto);
        return carMapper.toCarDTO(
                carRepository.save(entity)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CarDTO getById(Long carId) {
        return carMapper.toCarDTO(
                carRepository.findById(carId).orElseThrow()
        );
    }

    @Override
    public CarDTO update(CarUpdateDTO dto) {
        Car entity = carMapper.toEntity(dto);
        return carMapper.toCarDTO(
                carRepository.save(entity)
        );
    }

    @Override
    public boolean delete(Long carId) {
        carRepository.deleteById(carId);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarDTO> getAll() {
        return carMapper.toCarDTO(
                carRepository.findAll()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarListDTO> getAllCarListDTO() {
        return carMapper.toCarListDTO(
                carRepository.findAll()
        );
    }
}
