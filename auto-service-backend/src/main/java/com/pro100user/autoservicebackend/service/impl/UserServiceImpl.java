package com.pro100user.autoservicebackend.service.impl;

import com.pro100user.autoservicebackend.dto.UserCreateDTO;
import com.pro100user.autoservicebackend.dto.UserDTO;
import com.pro100user.autoservicebackend.dto.UserUpdateDTO;
import com.pro100user.autoservicebackend.entity.User;
import com.pro100user.autoservicebackend.entity.enums.Role;
import com.pro100user.autoservicebackend.mapper.UserMapper;
import com.pro100user.autoservicebackend.repository.UserRepository;
import com.pro100user.autoservicebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean create(UserCreateDTO dto) {
        if(findByEmail(dto.getEmail()) != null) {
            throw new BadCredentialsException("email");
        }
        if(findByPhone(dto.getPhone()) != null) {
            throw new BadCredentialsException("phone");
        }
        User entity = userMapper.toEntity(dto);
        entity.setRoles(Set.of(Role.ROLE_USER));
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setEnabled(true);
        userRepository.save(entity);
        return true;
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
        User entity = userMapper.toEntity(dto);
        User user = userRepository.findById(entity.getId()).orElseThrow();

        if(!user.getEmail().equals(entity.getEmail()) && findByEmail(dto.getEmail()) != null) {
            throw new BadCredentialsException("email");
        }
        if(!user.getPhone().equals(entity.getPhone()) && findByPhone(dto.getPhone()) != null) {
            throw new BadCredentialsException("phone");
        }
        if(dto.getNewPassword() != null) {
            if(passwordEncoder.matches(user.getPassword(), entity.getPassword())){
                entity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            }
        }
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
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone).orElse(null);
    }
}
