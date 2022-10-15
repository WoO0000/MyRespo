package com.example.classsign_in.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.classsign_in.databinding.FragmentNotificationsBinding;

public class NotificationsFragment1 extends Fragment {

private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel1 notificationsViewModel1 =
                new ViewModelProvider(this).get(NotificationsViewModel1.class);

    binding = FragmentNotificationsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}