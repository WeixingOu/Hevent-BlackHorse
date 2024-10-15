package com.itbh.hevent.utils;

import com.itbh.hevent.Repositories.UserRepository;
import com.itbh.hevent.models.User;
import com.itbh.hevent.services.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationFacadeUtil {
    private AuthenticationFacadeUtil() {}

    public static User getAuthenticatedUser( AuthenticationFacade authenticationFacade, UserRepository userRepository) {
        return userRepository.findByUsername(authenticationFacade.getAuthentication().getName())
            .orElseThrow(() -> new RuntimeException("User not found with username: " + authenticationFacade.getAuthentication().getName()));
    }
}
