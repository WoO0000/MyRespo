package com.example.classsign_in.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.classsign_in.databinding.FragmentDashboardBinding;

public class DashboardFragment1 extends Fragment {

private @NonNull FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        Log.d("TEST_DEBUG","DashboardFragment1");
        DashboardViewModel1 dashboardViewModel1 =
                new ViewModelProvider(this).get(DashboardViewModel1.class);

    binding = FragmentDashboardBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}