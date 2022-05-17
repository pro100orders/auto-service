package com.pro100user.autoservicebackend.dto;

import com.pro100user.autoservicebackend.entity.Car;
import com.pro100user.autoservicebackend.entity.enums.Status;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderDTO {

    private Long id;

    private CarDTO car;

    private ServiceDTO service;

    private Status status;

    private LocalDateTime createdAt;
}
