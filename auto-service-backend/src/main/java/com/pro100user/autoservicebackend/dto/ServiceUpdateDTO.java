package com.pro100user.autoservicebackend.dto;

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
public class ServiceUpdateDTO {

    @NotNull
    @Min(value = 1, message = "Service id cannot be less than 1")
    private Long id;

    @NotNull
    @Size(max = 64, message = "Name must be up to 64 characters long")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull
    @Min(value = 0, message = "Price cannot be less than 0")
    private double price;

    @NotNull
    @Size(max = 64, message = "Time must be up to 64 characters long")
    @NotBlank(message = "Time cannot be empty")
    private String time;

    @NotNull
    @NotBlank(message = "Description cannot be empty")
    private String description;
}
