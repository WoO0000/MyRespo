package com.example.classsign_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.classsign_in.ui.dashboard.DashboardFragment;
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
public class MainActivitySignIn extends AppCompatActivity implements View.OnClickListener  {

    private final Gson gson = new Gson();
    private EditText Signcode;
    String LgSigncode;
    private Button singin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_in);
        Signcode = findViewById(R.id.Signcode);
        singin = findViewById(R.id.btn_signin);
        singin.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signin:
                LgSigncode = Signcode.getText().toString().trim();
                post();
                break;
            default:
                break;
        }
    }
    private void post(){
        new Thread(() -> {

            // url??????
            String url = "http://47.107.52.7:88/member/sign/course/teacher/initiate";

            // ?????????
            Headers headers = new Headers.Builder()
                    .add("appId", "7bd38faebc70435b878ad739e76ab60a")
                    .add("appSecret", "5181026cb05ab5d6e4f94959a64ec33c36761")
                    .add("Accept", "application/json, text/plain, */*")
                    .build();

            // ?????????
            // PS.??????????????????????????????????????????????????????????????????fastjson???????????????json???
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("beginTime", "1665754440000");
            bodyMap.put("courseAddr", "??????");
            bodyMap.put("courseId", "298");
            bodyMap.put("courseName", "??????1");
            bodyMap.put("endTime", "1665840840000");
            bodyMap.put("signCode", LgSigncode);
            bodyMap.put("total", "1");
            bodyMap.put("userId", "434");
            // ???Map??????????????????????????????????????????
            String body = gson.toJson(bodyMap);

            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            //??????????????????
            Request request = new Request.Builder()
                    .url(url)
                    // ???????????????????????????
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, body))
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //?????????????????????callback????????????
                client.newCall(request).enqueue(callback);
            }catch (NetworkOnMainThreadException ex){
                ex.printStackTrace();
            }
        }).start();
    }

    /**
     * ??????
     */
    private final Callback callback = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, IOException e) {
            //TODO ??????????????????
            e.printStackTrace();
        }
        @Override
        public void onResponse(@NonNull Call call, Response response) throws IOException {
            //TODO ??????????????????
            Type jsonType = new TypeToken<ResponseBody<Object>>() {
            }.getType();
            // ??????????????????json???
            String body = response.body().string();
            Log.d("info", body);
            // ??????json???????????????????????????
            ResponseBody<Object> dataResponseBody = gson.fromJson(body, jsonType);
            Log.d("info", dataResponseBody.toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //????????????
                    try {
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(dataResponseBody.getCode() == 200){
                                Toast.makeText(getApplicationContext(),"????????????",Toast.LENGTH_SHORT).show();
                                Log.d("info", "Sign onResponse: ????????????");
                                Intent intent=new Intent();
                                intent.setClass(MainActivitySignIn.this,MainActivityDetail.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"????????????",Toast.LENGTH_SHORT).show();
                                Log.d("info", "Sign onResponse: ????????????");
                            }
                        }
                    });

                }
            }).start();
        }
    };

    /**
     * http????????????????????????
     * @param <T> ??????
     */
    public static class ResponseBody <T> {

        /**
         * ???????????????
         */
        private int code;
        /**
         * ??????????????????
         */
        private String msg;
        /**
         * ????????????
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