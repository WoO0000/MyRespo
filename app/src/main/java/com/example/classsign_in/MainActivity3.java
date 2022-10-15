package com.example.classsign_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.classsign_in.ui.dashboard.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.classsign_in.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityMain3Binding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main3);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
    public void GoAdd(View v){
        // Intent 去设置要跳转的页面
        Intent intent = new Intent(this, MainActivityAdd.class);
        // 进行跳转
        startActivity(intent);
    }
    public void GoDelete(View v){
        // Intent 去设置要跳转的页面
        Intent intent = new Intent(this, MainActivityDelete.class);
        // 进行跳转
        startActivity(intent);
    }
    public void GoConclude(View v){
        // Intent 去设置要跳转的页面
        Intent intent = new Intent(this,MainActivity4.class);
        // 进行跳转
        startActivity(intent);
    }
    public void GoNonConclude(View v){
        // Intent 去设置要跳转的页面
        Intent intent = new Intent(this, MainActivityNonconclude.class);
        // 进行跳转
        startActivity(intent);
    }
    public void Goturn(View v){
        // Intent 去设置要跳转的页面
        Intent intent = new Intent(this, MainActivitySignIn.class);
        // 进行跳转
        startActivity(intent);
    }

}