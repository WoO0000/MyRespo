package com.example.classsign_in;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.classsign_in.Adapter.BaseResponse;
import com.example.classsign_in.Adapter.Constants;
import com.example.classsign_in.Adapter.Course;
import com.example.classsign_in.Adapter.CourseAdapter;
import com.example.classsign_in.Adapter.NewsRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivityNonconclude extends AppCompatActivity {
    private ListView listView;
    private List<Course> courseListData;

    private int page = 1;

    private int mCurrentColIndex = 0;

    private int[] mCols = new int[]{Constants.NEWS_COL5,
            Constants.NEWS_COL7, Constants.NEWS_COL8,
            Constants.NEWS_COL10, Constants.NEWS_COL11};
    private CourseAdapter adapter;
    private okhttp3.Callback callback = new okhttp3.Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(TAG, "Failed to connect server!");
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response)
                throws IOException {
            if (response.isSuccessful()) {
                final String body = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Type jsonType =
                                new TypeToken<BaseResponse<List<Course>>>() {}.getType();
                        BaseResponse<List<Course>> newsListResponse =
                                gson.fromJson(body, jsonType);
                        for (Course news:newsListResponse.getData()) {
                            adapter.add((Course) courseListData);
                        }

                        adapter.notifyDataSetChanged();
                    }
                });
            } else {
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonconclude);


    }
    private void initView() {
        listView = findViewById(R.id.lv_news_list);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int i, long l) {

                        Intent intent = new Intent(MainActivityNonconclude.this,
                                MainActivity3.class);

                        Course courses = adapter.getItem(i);
                        intent.putExtra(Constants.NEWS_DETAIL_URL_KEY,
                                courses.getCoursePhoto());

                        startActivity(intent);
                    }
                });
    }
    private void initData() {
        courseListData = new ArrayList<>();
        adapter = new CourseAdapter(MainActivityNonconclude.this,
                R.layout.activity_nonconclude, courseListData);
        listView.setAdapter(adapter);

        refreshData(1);
    }

    private void refreshData(final int page) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                NewsRequest requestObj = new NewsRequest();

                requestObj.setCol(mCols[mCurrentColIndex]);
                requestObj.setNum(Constants.NEWS_NUM);
                requestObj.setPage(page);
                String urlParams = requestObj.toString();

                Request request = new Request.Builder()
                        .url(Constants.GENERAL_NEWS_URL + urlParams)
                        .get().build();
                try {
                    OkHttpClient client = new OkHttpClient();
                    client.newCall(request).enqueue(callback);
                } catch (NetworkOnMainThreadException ex) {

                    ex.printStackTrace();
                }
            }
        }).start();
    }
}