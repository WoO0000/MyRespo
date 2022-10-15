package com.example.classsign_in;

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

public class MainActivitySignup extends AppCompatActivity implements View.OnClickListener  {
    private EditText newUsername;
    private EditText newPassword;
    private EditText newUserId;
    private Button singup;
    String Lgusername;
    String Lgpassword;
    String LgUserId;

    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        newUsername = findViewById(R.id.et_newAccount);
        newPassword = findViewById(R.id.et_newpwd);
        newUserId = findViewById(R.id.et_newId);

        singup = findViewById(R.id.btn_signup);
        singup.setOnClickListener(this);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_return:
                Intent intent=new Intent();
                intent.setClass(MainActivitySignup.this, MainActivityLogin_Student.class);
                startActivity(intent);
                break;
            case R.id.btn_signup:
                if (newUsername.equals("") || newPassword.equals("")) {
                    Toast.makeText(MainActivitySignup.this, "账号或密码输入不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    Lgusername = newUsername.getText().toString().trim();
                   //LgUserId = LgUserId.getText().toString().trim();
                    Lgpassword = newPassword.getText().toString().trim();
                    post();
                }
            default:
                break;
        }
    }
    private void post(){
        new Thread(() -> {

            // url路径
            String url = "http://47.107.52.7:88/member/sign/user/register";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("appId", "7bd38faebc70435b878ad739e76ab60a")
                    .add("appSecret", "5181026cb05ab5d6e4f94959a64ec33c36761")
                    .add("Accept", "application/json, text/plain, */*")
                    .build();

            // 请求体
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("password", Lgpassword);
            bodyMap.put("roleId", "0");
            bodyMap.put("userName", Lgusername);
            // 将Map转换为字符串类型加入请求体中
            String body = gson.toJson(bodyMap);

            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, body))
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //发起请求，传入callback进行回调
                client.newCall(request).enqueue(callback);
            }catch (NetworkOnMainThreadException ex){
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
            Type jsonType = new TypeToken<ResponseBody<Object>>(){}.getType();
            // 获取响应体的json串
            String body = response.body().string();
            Log.d("info", body);
            // 解析json串到自己封装的状态
            MainActivitySignup.ResponseBody<Object> dataResponseBody = gson.fromJson(body,jsonType);
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
                                Log.d("info", "Sign onResponse: 注册成功");
                                Intent intent=new Intent();
                                intent.setClass(MainActivitySignup.this,MainActivityLogin_Teacher.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),dataResponseBody.getMsg(),Toast.LENGTH_SHORT).show();
                                Log.d("info", "Sign onResponse: 用户名已存在");
                            }
                        }
                    });

                }
            }).start();
        }
    };

    /**
     * http响应体的封装协议
     * @param <T> 泛型
     */
    public static class ResponseBody <T> {

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

        public ResponseBody(){}

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