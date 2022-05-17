package com.pro100user.autoservicebackend.mapper;

import com.pro100user.autoservicebackend.dto.UserCreateDTO;
import com.pro100user.autoservicebackend.dto.UserDTO;
import com.pro100user.autoservicebackend.dto.UserUpdateDTO;
import com.pro100user.autoservicebackend.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toEntity(UserCreateDTO dto);
    User toEntity(UserUpdateDTO dto);

    UserDTO toUserDTO(User user);
}
