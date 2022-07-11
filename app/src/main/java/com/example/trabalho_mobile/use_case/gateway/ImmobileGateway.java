package com.example.trabalho_mobile.use_case.gateway;

import com.example.trabalho_mobile.entity.Immobile;
import com.example.trabalho_mobile.entity.User;
import com.example.trabalho_mobile.external.database.model.PropertyData;
import com.example.trabalho_mobile.external.database.model.UserData;

import java.util.List;

public interface ImmobileGateway {
    PropertyData add(Immobile immobile);
    List<PropertyData> findAll(User user);
    PropertyData findById(User user, String id);
}
