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
public class OrderUpdateDTO {

    @Min(value = 0, message = "Order id cannot be less than 0")
    private Long id;

    @Min(value = 0, message = "Car id cannot be less than 0")
    private Long carId;

    @Min(value = 0, message = "Service id cannot be less than 0")
    private Long serviceId;

    @NotNull
    private Status status;
}
