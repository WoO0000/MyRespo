package com.example.classsign_in.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.classsign_in.MainActivityAdd;
import com.example.classsign_in.MainActivityLogin_Teacher;
import com.example.classsign_in.MainActivitySignIn;
import com.example.classsign_in.MainActivitySignup;
import com.example.classsign_in.R;
import com.example.classsign_in.databinding.FragmentDashboardBinding;
import com.google.gson.Gson;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class DashboardFragment extends Fragment {
    private final Gson gson = new Gson();
private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
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
