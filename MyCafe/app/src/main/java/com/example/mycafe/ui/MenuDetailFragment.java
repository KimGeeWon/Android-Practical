package com.example.mycafe.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycafe.MainViewModel;
import com.example.mycafe.databinding.FragmentHomeBinding;
import com.example.mycafe.databinding.FragmentMenuDetailBinding;

public class MenuDetailFragment extends Fragment {

    private MainViewModel viewModel;
    private FragmentMenuDetailBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentMenuDetailBinding.inflate(inflater);
        return binding.getRoot();
    }
}