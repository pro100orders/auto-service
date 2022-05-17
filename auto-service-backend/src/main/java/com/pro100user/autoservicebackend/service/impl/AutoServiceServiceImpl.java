package com.pro100user.autoservicebackend.service.impl;

import com.pro100user.autoservicebackend.dto.ServiceCreateDTO;
import com.pro100user.autoservicebackend.dto.ServiceDTO;
import com.pro100user.autoservicebackend.dto.ServiceUpdateDTO;
import com.pro100user.autoservicebackend.entity.AutoService;
import com.pro100user.autoservicebackend.mapper.ServiceMapper;
import com.pro100user.autoservicebackend.repository.ServiceRepository;
import com.pro100user.autoservicebackend.service.AutoServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutoServiceServiceImpl implements AutoServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

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
    public List<ServiceDTO> getAll() {
        return serviceMapper.toListServiceDTO(
                serviceRepository.findAll()
        );
    }
}
