package com.itbh.hevent.controllers;

import com.itbh.hevent.dtos.UserDTO;
import com.itbh.hevent.dtos.records.CreateUserDTO;
import com.itbh.hevent.dtos.records.UpdateUserDTO;
import com.itbh.hevent.dtos.records.UserPasswordDTO;
import com.itbh.hevent.exceptions.api.ApiResponse;
import com.itbh.hevent.services.UserService;
import com.itbh.hevent.utils.JwtUtil;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return new ResponseEntity<>(ApiResponse.success(userService.createUser(createUserDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/users/auth")
    public ResponseEntity<ApiResponse<UserDTO>> getAuthUser() {
        return new ResponseEntity<>(ApiResponse.success(userService.getAuthUser()), HttpStatus.OK);
    }

    @PatchMapping("/users/auth")
    public ResponseEntity<ApiResponse<String>> changePassword(@Valid @RequestBody UserPasswordDTO userPasswordDTO, @RequestHeader("Authorization") String header) {
        userService.changePassword(userPasswordDTO);

        String token = header.startsWith("Bearer ") ? header.substring(7) : header;

        jwtUtil.invalidateToken(token);
        return new ResponseEntity<>(ApiResponse.success("Password updated successfully. Please log in again."),HttpStatus.OK);
    }

    @PutMapping("/users/me")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO) {
        return new ResponseEntity<>(ApiResponse.success(userService.updateUser(updateUserDTO)), HttpStatus.OK);
    }

    @PatchMapping("/users/me")
    public ResponseEntity<ApiResponse<UserDTO>> uploadUserPic(@RequestParam @URL String userPicUrl) {
        return new ResponseEntity<>(ApiResponse.success(userService.uploadUserPic(userPicUrl)), HttpStatus.OK);
    }
}
