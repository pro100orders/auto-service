package com.pro100user.autoservicebackend.service;

import com.pro100user.autoservicebackend.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AutoServiceService {

    ServiceDTO create(ServiceCreateDTO dto);
    ServiceDTO getById(Long serviceId);
    ServiceDTO update(ServiceUpdateDTO dto);
    boolean delete(Long serviceId);
    List<ServiceDTO> getAll();

    ServiceDTO setImage(MultipartFile file, Long serviceId);
    boolean updateImage(MultipartFile file, Long serviceId);
    boolean deleteImage(Long serviceId);
}
