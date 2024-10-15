package com.itbh.hevent.mappers;

import com.itbh.hevent.dtos.UserDTO;
import com.itbh.hevent.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}