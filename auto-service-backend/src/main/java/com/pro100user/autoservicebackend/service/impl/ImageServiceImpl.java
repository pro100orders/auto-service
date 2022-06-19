package com.pro100user.autoservicebackend.service.impl;

import com.pro100user.autoservicebackend.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final String filepath;

    public ImageServiceImpl() {
        try {
            filepath = ResourceUtils.getFile("classpath:").getPath() + "/static/files/";
            File directory = new File(filepath);
            if (!directory.exists()) {
                Files.createDirectories(directory.toPath());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String save(MultipartFile file, Long serviceId) {
        try {
            File avatar = new File(filepath + serviceId.toString() + file.getOriginalFilename());
            if (!avatar.exists()) {
                avatar.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(avatar));
            bos.write(file.getBytes());
            bos.flush();
            bos.close();
            return serviceId.toString() + file.getOriginalFilename();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String update(String filename, MultipartFile file, Long serviceId) {
        try {
            File avatar;
            if(filename == null)
                avatar = new File(filepath + serviceId.toString() + file.getOriginalFilename());
            else
                avatar = new File(filepath + filename);
            if (!avatar.exists()) {
                avatar.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(avatar));
            bos.write(file.getBytes());
            bos.flush();
            bos.close();
            return avatar.getName();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean delete(String filename) {
        File avatar = new File(filepath + filename);
        if (avatar.exists()) {
            return avatar.delete();
        }
        return false;
    }
}
