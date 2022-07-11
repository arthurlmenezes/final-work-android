package com.example.trabalho_mobile.use_case.gateway;

import com.example.trabalho_mobile.entity.Ad;
import com.example.trabalho_mobile.entity.User;
import com.example.trabalho_mobile.external.database.model.AdData;

import java.util.List;

public interface AdGateway {
    AdData add(Ad ad, User user);
    List<AdData> findAll(User user);
    AdData findById(String id, User user);
}
