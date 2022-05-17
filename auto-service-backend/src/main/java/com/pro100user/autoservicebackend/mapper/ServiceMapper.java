package com.pro100user.autoservicebackend.mapper;

import com.pro100user.autoservicebackend.dto.ServiceCreateDTO;
import com.pro100user.autoservicebackend.dto.ServiceDTO;
import com.pro100user.autoservicebackend.dto.ServiceUpdateDTO;
import com.pro100user.autoservicebackend.entity.AutoService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ServiceMapper {

    AutoService toEntity(ServiceCreateDTO dto);
    AutoService toEntity(ServiceUpdateDTO dto);

    ServiceDTO toServiceDTO(AutoService service);
    List<ServiceDTO> toListServiceDTO(List<AutoService> services);
}
