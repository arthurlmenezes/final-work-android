package com.example.trabalho_mobile.use_case;

import com.example.trabalho_mobile.entity.User;
import com.example.trabalho_mobile.use_case.gateway.UserGateway;
import com.example.trabalho_mobile.use_case.port.RegisterUserInput;

import java.util.ArrayList;
import java.util.UUID;

public class RegisterUser {

    private final UserGateway userRepository;

    public RegisterUser(UserGateway userRepository) {
        this.userRepository = userRepository;
    }

    public void run(RegisterUserInput registerUserInput) {
        User user = User.create(
                UUID.randomUUID(),
                registerUserInput.name,
                registerUserInput.email,
                registerUserInput.phoneNumber,
                new ArrayList<>(),
                new ArrayList<>()
        );
        this.userRepository.add(user);
    }
}
