package com.pro100user.autoservicebackend.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String save(MultipartFile file, Long serviceId);

    String update(String filepath, MultipartFile file, Long serviceId);

    boolean delete(String filepath);
}
