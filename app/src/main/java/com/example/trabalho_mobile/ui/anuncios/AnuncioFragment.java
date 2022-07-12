package com.example.trabalho_mobile.ui.anuncios;

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
import com.example.trabalho_mobile.databinding.FragmentAnuncioBinding;
import com.example.trabalho_mobile.databinding.FragmentCategoryBinding;
import com.example.trabalho_mobile.ui.category.CategoryViewModel;
import com.google.android.material.snackbar.Snackbar;

public class AnuncioFragment extends Fragment {

    private FragmentAnuncioBinding binding;

    public static AnuncioFragment newInstance() {
        return new AnuncioFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AnuncioViewModel anuncioViewModel =
                new ViewModelProvider(this).get(AnuncioViewModel.class);

        binding = FragmentAnuncioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
