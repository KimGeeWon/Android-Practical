package com.example.mycafe.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycafe.MainViewModel;
import com.example.mycafe.databinding.FragmentHomeBinding;
import com.example.mycafe.databinding.FragmentOrderBinding;

public class OrderFragment extends Fragment {

    private MainViewModel viewModel;
    private FragmentOrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentOrderBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getUserId().observe(getViewLifecycleOwner(), userid->{
            if(userid!=null)
                binding.textViewUserID.setText(userid);
        });
    }
}