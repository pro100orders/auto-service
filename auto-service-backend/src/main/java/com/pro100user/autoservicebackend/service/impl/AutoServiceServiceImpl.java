package com.pro100user.autoservicebackend.service.impl;

import com.pro100user.autoservicebackend.dto.ServiceCreateDTO;
import com.pro100user.autoservicebackend.dto.ServiceDTO;
import com.pro100user.autoservicebackend.dto.ServiceUpdateDTO;
import com.pro100user.autoservicebackend.entity.AutoService;
import com.pro100user.autoservicebackend.mapper.ServiceMapper;
import com.pro100user.autoservicebackend.repository.ServiceRepository;
import com.pro100user.autoservicebackend.service.AutoServiceService;
import com.pro100user.autoservicebackend.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutoServiceServiceImpl implements AutoServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    private final ImageService imageService;

    @Override
    public ServiceDTO create(ServiceCreateDTO dto) {
        AutoService entity = serviceMapper.toEntity(dto);
        return serviceMapper.toServiceDTO(
                serviceRepository.save(entity)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceDTO getById(Long serviceId) {
        return serviceMapper.toServiceDTO(
                serviceRepository.findById(serviceId).orElseThrow()
        );
    }

    @Override
    public ServiceDTO update(ServiceUpdateDTO dto) {
        AutoService entity = serviceMapper.toEntity(dto);
        return serviceMapper.toServiceDTO(
                serviceRepository.save(entity)
        );
    }

    @Override
    public boolean delete(Long serviceId) {
        serviceRepository.deleteById(serviceId);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> getAll() {
        return serviceMapper.toServiceDTO(
                serviceRepository.findAll()
        );
    }

    @Override
    public ServiceDTO setImage(MultipartFile file, Long serviceId) {
        AutoService entity = serviceRepository.findById(serviceId).orElseThrow();
        entity.setImage(imageService.save(file, serviceId));
        return serviceMapper.toServiceDTO(
                serviceRepository.save(entity)
        );
    }

    @Override
    public boolean updateImage(MultipartFile file, Long serviceId) {
        AutoService entity = serviceRepository.findById(serviceId).orElseThrow();
        entity.setImage(imageService.update(entity.getImage(), file, serviceId));
        serviceRepository.save(entity);
        return true;
    }

    @Override
    public boolean deleteImage(Long serviceId) {
        AutoService service = serviceRepository.findById(serviceId).orElseThrow();
        return imageService.delete(service.getImage());
    }
}
