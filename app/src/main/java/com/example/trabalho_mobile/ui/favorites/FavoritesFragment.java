package com.example.trabalho_mobile.ui.favorites;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trabalho_mobile.R;
import com.example.trabalho_mobile.databinding.FragmentCategoryBinding;
import com.example.trabalho_mobile.databinding.FragmentFavoritesBinding;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FavoritesViewModel model = new ViewModelProvider(this).get(FavoritesViewModel.class);
        model.carregarFavoritos().observe(getViewLifecycleOwner(), favoritosLista -> {
            favoritosLista.get(0);
        });
        return root;

    }

}