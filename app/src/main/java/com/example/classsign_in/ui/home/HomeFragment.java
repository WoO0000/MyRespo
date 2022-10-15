package com.example.classsign_in.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.classsign_in.MainActivity4;
import com.example.classsign_in.R;
import com.example.classsign_in.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        Log.d("TEST_DEBUG","HomeFragment");

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
        return root;

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
