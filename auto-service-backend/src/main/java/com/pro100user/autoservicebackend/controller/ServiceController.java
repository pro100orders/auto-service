package com.pro100user.autoservicebackend.controller;

import com.pro100user.autoservicebackend.entity.AutoService;
import com.pro100user.autoservicebackend.service.AutoServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("services")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceController {

    public final AutoServiceService autoService;
}
