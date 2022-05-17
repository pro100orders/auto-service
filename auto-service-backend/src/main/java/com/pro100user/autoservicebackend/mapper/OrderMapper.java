package com.pro100user.autoservicebackend.mapper;

import com.pro100user.autoservicebackend.dto.OrderCreateDTO;
import com.pro100user.autoservicebackend.dto.OrderDTO;
import com.pro100user.autoservicebackend.dto.OrderUpdateDTO;
import com.pro100user.autoservicebackend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(uses = {CarMapper.class, ServiceMapper.class})
public interface OrderMapper {

    @Mappings({
            @Mapping(source = "carId", target = "car.id"),
            @Mapping(source = "serviceId", target = "service.id")
    })
    Order toEntity(OrderCreateDTO dto);
    @Mappings({
            @Mapping(source = "carId", target = "car.id"),
            @Mapping(source = "serviceId", target = "service.id")
    })
    Order toEntity(OrderUpdateDTO dto);

    OrderDTO toOrderDTO(Order order);
    List<OrderDTO> toListOrderDTO(List<Order> orders);
}
