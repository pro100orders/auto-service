package com.pro100user.autoservicebackend.mapper;

import com.pro100user.autoservicebackend.dto.CarCreateDTO;
import com.pro100user.autoservicebackend.dto.CarDTO;
import com.pro100user.autoservicebackend.dto.CarListDTO;
import com.pro100user.autoservicebackend.dto.CarUpdateDTO;
import com.pro100user.autoservicebackend.entity.Car;
import com.pro100user.autoservicebackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {UserMapper.class})
public interface CarMapper {

    @Mapping(source = "userId", target = "user.id")
    Car toEntity(CarCreateDTO dto);

    @Mapping(source = "userId", target = "user.id")
    Car toEntity(CarUpdateDTO dto);

    Car toEntity(CarDTO dto);

    CarDTO toCarDTO(Car car);

    List<CarDTO> toCarDTO(List<Car> cars);

    default String getFullName(User user) {
        return user.getSurname() + " " + user.getName();
    }

    default List<CarListDTO> toCarListDTO(List<Car> cars) {
        if (cars == null) {
            return null;
        }

        List<CarListDTO> list = new ArrayList<CarListDTO>(cars.size());
        for (Car car : cars) {
            list.add(carToCarListDTO(car));
        }

        return list;
    }

    default CarListDTO carToCarListDTO(Car car) {
        if (car == null) {
            return null;
        }

        CarListDTO.CarListDTOBuilder carListDTO = CarListDTO.builder();

        carListDTO.id(car.getId());
        carListDTO.userId(car.getUser().getId());
        carListDTO.fullName(getFullName(car.getUser()));
        carListDTO.brand(car.getBrand());
        carListDTO.model(car.getModel());
        carListDTO.year(car.getYear());
        carListDTO.number(car.getNumber());
        carListDTO.vinCode(car.getVinCode());
        carListDTO.fuel(car.getFuel());
        carListDTO.transmission(car.getTransmission());
        carListDTO.driveType(car.getDriveType());

        return carListDTO.build();
    }
}
