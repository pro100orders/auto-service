package com.pro100user.autoservicebackend.controller;

import com.pro100user.autoservicebackend.dto.ServiceCreateDTO;
import com.pro100user.autoservicebackend.dto.ServiceDTO;
import com.pro100user.autoservicebackend.dto.ServiceUpdateDTO;
import com.pro100user.autoservicebackend.entity.AutoService;
import com.pro100user.autoservicebackend.service.AutoServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("services")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceController {

    public final AutoServiceService autoService;

    @GetMapping
    public List<ServiceDTO> services() {
        return autoService.getAll();
    }

    @GetMapping("{id}")
    public ServiceDTO details(
            @PathVariable("id") Long id
    ) {
        return autoService.getById(id);
    }

    @PostMapping
    public ServiceDTO create(
            @RequestBody ServiceCreateDTO dto
    ) {
        return autoService.create(dto);
    }

    @PutMapping
    public ServiceDTO update(
            @RequestBody ServiceUpdateDTO dto
    ) {
        return autoService.update(dto);
    }

    @DeleteMapping("{id}")
    public boolean delete(
            @PathVariable("id") Long id
    ) {
        return autoService.delete(id);
    }

    @PostMapping("{id}")
    public ServiceDTO setImage(
            @PathVariable("id") Long serviceId,
            @RequestParam("image") MultipartFile image
    ) {
        return autoService.setImage(image, serviceId);
    }

    @PutMapping("{id}")
    public boolean updateImage(
            @PathVariable("id") Long serviceId,
            @RequestParam("image") MultipartFile image
    ) {
        return autoService.updateImage(image, serviceId);
    }
}
