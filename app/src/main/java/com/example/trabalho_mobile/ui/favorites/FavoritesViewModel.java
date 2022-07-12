package com.example.trabalho_mobile.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabalho_mobile.entity.Ad;
import com.example.trabalho_mobile.entity.Immobile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FavoritesViewModel extends ViewModel {
    private MutableLiveData<List<Ad>> favorites;

    public LiveData<List<Ad>> carregarFavoritos(){
        ArrayList<Ad> favorites = new ArrayList<>();
        Immobile immobile = Immobile.create(UUID.randomUUID(),"Casa da mãe Joana","Casa boa demais",null,null);
        Ad ad = Ad.create(UUID.randomUUID(),null,200000);
        favorites.add(ad);
        return new MutableLiveData<List<Ad>>(favorites);
    }
}