package com.itbh.hevent.services;

import com.itbh.hevent.dtos.UserDTO;
import com.itbh.hevent.dtos.records.CreateUserDTO;
import com.itbh.hevent.dtos.records.UpdateUserDTO;
import com.itbh.hevent.dtos.records.UserPasswordDTO;

public interface UserService {
    UserDTO createUser(CreateUserDTO createUserDTO);

    UserDTO getAuthUser();

    UserDTO updateUser(UpdateUserDTO updateUserDTO);

    UserDTO uploadUserPic(String userPicUrl);

    void changePassword(UserPasswordDTO userPasswordDTO);
}
