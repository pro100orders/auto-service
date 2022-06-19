package com.pro100user.autoservicebackend.service.impl;

import com.pro100user.autoservicebackend.dto.*;
import com.pro100user.autoservicebackend.entity.Order;
import com.pro100user.autoservicebackend.entity.User;
import com.pro100user.autoservicebackend.entity.enums.Role;
import com.pro100user.autoservicebackend.entity.enums.Status;
import com.pro100user.autoservicebackend.mapper.CarMapper;
import com.pro100user.autoservicebackend.mapper.OrderMapper;
import com.pro100user.autoservicebackend.mapper.UserMapper;
import com.pro100user.autoservicebackend.repository.OrderRepository;
import com.pro100user.autoservicebackend.repository.UserRepository;
import com.pro100user.autoservicebackend.service.CarService;
import com.pro100user.autoservicebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final CarService carService;
    private final CarMapper carMapper;

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO create(UserCreateDTO dto) {
        if (findByEmail(dto.getEmail()) != null) {
            throw new BadCredentialsException("email");
        }
        if (findByPhone(dto.getPhone()) != null) {
            throw new BadCredentialsException("phone");
        }
        User entity = userMapper.toEntity(dto);
        entity.setRoles(List.of(Role.ROLE_USER));
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setEnabled(true);
        return userMapper.toUserDTO(
                userRepository.save(entity)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getById(Long userId) {
        return userMapper.toUserDTO(
                userRepository.findById(userId).orElseThrow()
        );
    }

    @Override
    public UserDTO update(UserUpdateDTO dto) {
        User userDTO = userMapper.toEntity(dto);
        User entity = userRepository.findById(userDTO.getId()).orElseThrow();

        if (!entity.getEmail().equals(userDTO.getEmail()) && findByEmail(dto.getEmail()) != null) {
            throw new BadCredentialsException("email");
        }
        if (!entity.getPhone().equals(userDTO.getPhone()) && findByPhone(dto.getPhone()) != null) {
            throw new BadCredentialsException("phone");
        }
        entity = entity.toBuilder()
                .surname(userDTO.getSurname() == null ? entity.getSurname() : userDTO.getSurname())
                .name(userDTO.getName() == null ? entity.getName() : userDTO.getName())
                .email(userDTO.getEmail() == null ? entity.getEmail() : userDTO.getEmail())
                .phone(userDTO.getPhone() == null ? entity.getPhone() : userDTO.getPhone())
                .build();
        return userMapper.toUserDTO(
                userRepository.save(entity)
        );
    }

    @Override
    public boolean delete(Long userId) {
        User entity = userRepository.findById(userId).orElseThrow();
        entity.setEnabled(false);
        userRepository.save(entity);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        return userMapper.toUserDTO(
                userRepository.findAll()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone).orElse(null);
    }

    @Override
    public List<CarDTO> getCars(Long userId) {
        return carMapper.toCarDTO(
                userRepository.findById(userId).orElseThrow().getCars()
        );
    }


    @Override
    public List<OrderDTO> orders(Long userId) {
        return orderMapper.toOrderDTO(
                userRepository.findById(userId).orElseThrow().getCars().stream()
                        .map(car -> car.getOrders())
                        .flatMap(List::stream)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public OrderDTO toOrder(OrderCreateDTO dto) {
        Order order = orderMapper.toEntity(dto);
        order.setStatus(Status.Оформлено);
        return orderMapper.toOrderDTO(
                orderRepository.save(order)
        );
    }
}
