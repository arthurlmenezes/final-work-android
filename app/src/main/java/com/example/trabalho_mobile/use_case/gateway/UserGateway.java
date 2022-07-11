package com.example.trabalho_mobile.use_case.gateway;

import com.example.trabalho_mobile.entity.User;
import com.example.trabalho_mobile.external.database.model.UserData;

import java.util.List;

public interface UserGateway {
    UserData add(User user);
    List<UserData> findAll();
    UserData findById(String id);
}
