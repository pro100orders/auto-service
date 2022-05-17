package com.pro100user.autoservicebackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ServiceDTO {

    private Long id;

    private String name;

    private String image;

    private double price;

    private String time;

    private String description;
}
