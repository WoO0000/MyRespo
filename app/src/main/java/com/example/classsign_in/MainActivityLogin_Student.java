package com.example.classsign_in;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;



import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivityLogin_Student extends AppCompatActivity implements View.OnClickListener {
    private EditText Username;
    private EditText Password;
    private Button login;
    private Button register;
    private Button change;
    private CheckBox cbRememberPwd;
    ImageView imageView;
    Boolean bPwdSwitch = false;
    String Lgusername;
    String Lgpassword;

    private final Gson gson = new Gson();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        cbRememberPwd = findViewById(R.id.cb_remember_pwd);
        register = findViewById(R.id.tv_sign_up);
        login = findViewById(R.id.Login);
        change = findViewById(R.id.Changeteacher);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        change.setOnClickListener(this);

        //保存密码，查看密码
        String spFileName = getResources()
                .getString(R.string.shared_preferences_file_name);
        String accountKey = getResources()
                .getString(R.string.login_account_name);
        String passwordKey = getResources()
                .getString(R.string.login_password);
        String rememberPasswordKey = getResources()
                .getString(R.string.login_remember_password);//其中value为true是开始记录

        SharedPreferences spFile = getSharedPreferences(
                spFileName,
                MODE_PRIVATE);
        String account = spFile.getString(accountKey, null);
        String password = spFile.getString(passwordKey, null);
        Boolean rememberPassword = spFile.getBoolean(
                rememberPasswordKey,
                false);

        if (account != null && !TextUtils.isEmpty(account)) {
            Username.setText(account);
        }

        if (password != null && !TextUtils.isEmpty(password)) {
            Password.setText(password);
        }

        cbRememberPwd.setChecked(rememberPassword);
        imageView = findViewById(R.id.iv_pwd_switch);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bPwdSwitch = !bPwdSwitch;
                if (bPwdSwitch) {
                    imageView.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    imageView.setImageResource(R.drawable.ic_baseline_visibility_24);
                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    Password.setTypeface(Typeface.DEFAULT);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_up:
                Intent intent=new Intent();
                intent.setClass(MainActivityLogin_Student.this, MainActivitySignup.class);
                startActivity(intent);
                break;
            case R.id.Changeteacher:
                Intent intent1=new Intent();
                intent1.setClass(MainActivityLogin_Student.this, MainActivityLogin_Teacher.class);
                startActivity(intent1);
                break;
            case R.id.Login:
                if (Username.equals("") || Password.equals("")) {
                    Toast.makeText(MainActivityLogin_Student.this, "账号或密码为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    Lgusername = Username.getText().toString().trim();
                    Lgpassword = Password.getText().toString().trim();
                    post();
                }
            default:
                break;
        }
    }
    private void post() {
        new Thread(() -> {

            // url路径
            String url = "http://47.107.52.7:88/member/sign/user/login?"+"password="+Lgpassword+"&username="+Lgusername;

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("appId", "7bd38faebc70435b878ad739e76ab60a")
                    .add("appSecret", "5181026cb05ab5d6e4f94959a64ec33c36761")
                    .add("Accept", "application/json, text/plain, */*")
                    .add("Content-Type", "application/json")
                    .build();


            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, ""))
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //发起请求，传入callback进行回调
                client.newCall(request).enqueue(callback);
            } catch (NetworkOnMainThreadException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    /**
     * 回调
     */
    private final Callback callback = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, IOException e) {
            //TODO 请求失败处理
            e.printStackTrace();
        }

        @Override
        public void onResponse(@NonNull Call call, Response response) throws IOException {
            //TODO 请求成功处理
            Type jsonType = new TypeToken<MainActivityLogin_Teacher.ResponseBody<Object>>() {
            }.getType();
            // 获取响应体的json串
            String body = response.body().string();
            Log.d("info", body);
            // 解析json串到自己封装的状态
            MainActivityLogin_Teacher.ResponseBody<Object> dataResponseBody = gson.fromJson(body, jsonType);
            Log.d("info", dataResponseBody.toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //延迟两秒
                    try {
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(dataResponseBody.getCode() == 200){
                                Toast.makeText(getApplicationContext(),dataResponseBody.getMsg(),Toast.LENGTH_SHORT).show();
                                Log.d("info", "Sign onResponse: 登录成功");
                                Intent intent=new Intent();
                                intent.setClass(MainActivityLogin_Student.this,MainActivity8.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),dataResponseBody.getMsg(),Toast.LENGTH_SHORT).show();
                                Log.d("info", "Sign onResponse: 登录失败");
                            }
                        }
                    });

                }
            }).start();
        }
    };

    /**
     * http响应体的封装协议
     *
     * @param <T> 泛型
     */
    public static class ResponseBody<T> {

        /**
         * 业务响应码
         */
        private int code;
        /**
         * 响应提示信息
         */
        private String msg;
        /**
         * 响应数据
         */
        private T data;

        public ResponseBody() {
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public T getData() {
            return data;
        }

        @NonNull
        @Override
        public String toString() {
            return "ResponseBody{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
}