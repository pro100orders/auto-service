package com.pro100user.autoservicebackend.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ServiceUpdateDTO {

    @Min(value = 0, message = "Service id cannot be less than 0")
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Min(value = 0, message = "Price cannot be less than 0")
    private double price;

    @NotBlank(message = "Time cannot be empty")
    private String time;

    @NotBlank(message = "Description cannot be empty")
    private String description;
}
