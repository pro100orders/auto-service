package com.pro100user.autoservicebackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pro100user.autoservicebackend.entity.User;
import com.pro100user.autoservicebackend.entity.enums.DriveType;
import com.pro100user.autoservicebackend.entity.enums.Fuel;
import com.pro100user.autoservicebackend.entity.enums.Transmission;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CarUpdateDTO {

    @Min(value = 0, message = "Car id cannot be less than 0")
    private Long id;

    @JsonIgnore
    private User user;

    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @NotBlank(message = "Model cannot be empty")
    private String model;

    @Min(value = 0, message = "Year cannot be less than 0")
    private int year;

    @NotBlank(message = "Number cannot be empty")
    private String number;

    @NotBlank(message = "Vin Code cannot be empty")
    private String vinCode;

    @NotNull
    private Fuel fuel;

    @NotNull
    private Transmission transmission;

    @NotNull
    private DriveType driveType;
}
