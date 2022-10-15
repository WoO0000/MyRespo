package com.example.classsign_in.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.classsign_in.databinding.FragmentHomeBinding;

public class HomeFragment1 extends Fragment {

private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        Log.d("TEST_DEBUG","HomeFragment1");
        HomeViewModel1 homeViewModel1 =
                new ViewModelProvider(this).get(HomeViewModel1.class);

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
