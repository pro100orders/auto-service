package com.pro100user.autoservicebackend.dto;

import com.pro100user.autoservicebackend.entity.enums.DriveType;
import com.pro100user.autoservicebackend.entity.enums.Fuel;
import com.pro100user.autoservicebackend.entity.enums.Transmission;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CarDTO {

    private Long id;

    private UserDTO user;

    private String brand;

    private String model;

    private int year;

    private String number;

    private String vinCode;

    private Fuel fuel;

    private Transmission transmission;

    private DriveType driveType;
}
