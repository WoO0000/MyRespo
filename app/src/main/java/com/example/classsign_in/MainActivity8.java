package com.example.classsign_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.classsign_in.databinding.ActivityMain8Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity8 extends AppCompatActivity {

    private ActivityMain8Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain8Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView1 = findViewById(R.id.nav_view1);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home1, R.id.navigation_dashboard1, R.id.navigation_notifications1)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main8);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView1, navController);
    }
    public void GoSelect(View v){
        // Intent 去设置要跳转的页面
        Intent intent = new Intent(this, MainActivityAdd.class);
        // 进行跳转
        startActivity(intent);
    }
    public void GoWithdraw(View v){
        // Intent 去设置要跳转的页面
        Intent intent = new Intent(this, MainActivityDelete.class);
        // 进行跳转
        startActivity(intent);
    }
    public void GoSecond3(View v){
        // Intent 去设置要跳转的页面
        Intent intent = new Intent(this,MainActivity4.class);
        // 进行跳转
        startActivity(intent);
    }
    public void GoSecond4(View v){
        // Intent 去设置要跳转的页面
        Intent intent = new Intent(this, MainActivityNonconclude.class);
        // 进行跳转
        startActivity(intent);
    }
}