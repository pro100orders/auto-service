package com.pro100user.autoservicebackend.mapper;

import com.pro100user.autoservicebackend.dto.CarCreateDTO;
import com.pro100user.autoservicebackend.dto.CarDTO;
import com.pro100user.autoservicebackend.dto.CarUpdateDTO;
import com.pro100user.autoservicebackend.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(uses = {UserMapper.class})
public interface CarMapper {

    Car toEntity(CarCreateDTO dto);
    Car toEntity(CarUpdateDTO dto);

    CarDTO toCarDTO(Car car);
    List<CarDTO> toListCarDTO(List<Car> cars);
}
