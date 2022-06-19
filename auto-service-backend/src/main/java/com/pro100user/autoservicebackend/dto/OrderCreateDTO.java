package com.pro100user.autoservicebackend.dto;

import com.pro100user.autoservicebackend.entity.enums.Status;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderCreateDTO {

    @NotNull
    @Min(value = 1, message = "Car id cannot be less than 1")
    private Long carId;

    @NotNull
    @Min(value = 1, message = "Service id cannot be less than 1")
    private Long serviceId;
}
