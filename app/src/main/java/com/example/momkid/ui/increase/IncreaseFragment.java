package com.example.momkid.ui.increase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.momkid.databinding.FragmentIncreaseBinding;

public class IncreaseFragment extends Fragment {

    private FragmentIncreaseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        IncreaseViewModel increaseViewModel =
                new ViewModelProvider(this).get(IncreaseViewModel.class);

        binding = FragmentIncreaseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textIncrease;
        increaseViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}