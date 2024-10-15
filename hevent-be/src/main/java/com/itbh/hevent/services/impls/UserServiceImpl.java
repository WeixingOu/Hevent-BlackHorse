package com.itbh.hevent.services.impls;

import com.itbh.hevent.Repositories.UserRepository;
import com.itbh.hevent.dtos.UserDTO;
import com.itbh.hevent.dtos.records.CreateUserDTO;
import com.itbh.hevent.dtos.records.UpdateUserDTO;
import com.itbh.hevent.dtos.records.UserPasswordDTO;
import com.itbh.hevent.mappers.UserMapper;
import com.itbh.hevent.models.User;
import com.itbh.hevent.services.commons.EmailService;
import com.itbh.hevent.services.UserService;
import com.itbh.hevent.utils.AuthenticationFacadeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationFacadeImpl authenticationFacade;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, AuthenticationFacadeImpl authenticationFacade, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.authenticationFacade = authenticationFacade;
        this.emailService = emailService;
    }

    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        User user = new User(createUserDTO.username(), passwordEncoder.encode(createUserDTO.password()));
        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getAuthUser() {
        return userMapper.toUserDTO(AuthenticationFacadeUtil.getAuthenticatedUser(authenticationFacade,userRepository));
    }

    @Override
    public UserDTO updateUser(UpdateUserDTO updateUserDTO) {
        User user = AuthenticationFacadeUtil.getAuthenticatedUser(authenticationFacade,userRepository);

        user.setNickname(updateUserDTO.nickname());
        user.setEmail(updateUserDTO.email());

        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO uploadUserPic(String userPicUrl) {
        User user = AuthenticationFacadeUtil.getAuthenticatedUser(authenticationFacade,userRepository);

        user.setUserPic(userPicUrl);

        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public void changePassword(UserPasswordDTO userPasswordDTO) {
        User user = AuthenticationFacadeUtil.getAuthenticatedUser(authenticationFacade,userRepository);

        if (!passwordEncoder.matches(userPasswordDTO.currentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        if (!userPasswordDTO.newPassword().equals(userPasswordDTO.rePassword())) {
            throw new IllegalArgumentException("New password and confirmation do not match");
        }

        user.setPassword(passwordEncoder.encode(userPasswordDTO.newPassword()));
        userRepository.save(user);

        //emailService.sendEmail(user.getEmail(), "Password Changed", "Your password has been changed successfully.");
    }
}
