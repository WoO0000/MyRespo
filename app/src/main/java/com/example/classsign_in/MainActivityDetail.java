package com.example.classsign_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import android.os.Bundle;

public class MainActivityDetail extends AppCompatActivity {
    private final Gson gson = new Gson();
    private String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_signin);
        get();
    }
    private void get(){
        new Thread(() -> {

            // url路径
            String url = "http://47.107.52.7:88/member/sign/course/teacher/page?courseId=298&userId=434";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("appId", "7bd38faebc70435b878ad739e76ab60a")
                    .add("appSecret", "5181026cb05ab5d6e4f94959a64ec33c36761")
                    .add("Accept", "application/json, text/plain, */*")
                    .build();

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .get()
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
            ResponseBody<Object> dataResponseBody = gson.fromJson(body,jsonType);
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
                                return ;
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"发送失败",Toast.LENGTH_SHORT).show();
                                Log.d("info", "Sign onResponse: 发送失败");
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