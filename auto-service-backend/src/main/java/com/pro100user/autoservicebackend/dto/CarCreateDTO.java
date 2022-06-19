package com.pro100user.autoservicebackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pro100user.autoservicebackend.entity.User;
import com.pro100user.autoservicebackend.entity.enums.DriveType;
import com.pro100user.autoservicebackend.entity.enums.Fuel;
import com.pro100user.autoservicebackend.entity.enums.Transmission;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CarCreateDTO {

    private Long userId;

    @NotNull
    @Size(max = 64, message = "Brand must be up to 64 characters long")
    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @NotNull
    @Size(max = 64, message = "Model must be up to 64 characters long")
    @NotBlank(message = "Model cannot be empty")
    private String model;

    @NotNull
    @Min(value = 0, message = "Year cannot be less than 0")
    private int year;

    @NotNull
    @Size(max = 64, message = "Number must be up to 64 characters long")
    @NotBlank(message = "Number cannot be empty")
    private String number;

    @NotNull
    @Size(max = 64, message = "Vin Code must be up to 64 characters long")
    @NotBlank(message = "Vin Code cannot be empty")
    private String vinCode;

    @NotNull
    private Fuel fuel;

    @NotNull
    private Transmission transmission;

    @NotNull
    private DriveType driveType;
}
