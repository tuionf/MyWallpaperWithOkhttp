package com.example.tuion.mywallpaperwithokhttp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private int i = 0;
    private List<View> mViews = new ArrayList<View>();
    private String[] ss = new String[3];
    private String[] realUrl = new String[3];
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okHttpClient = new OkHttpClient();
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        initData();
    }


    /*
     * 添加图片资源  初始化数据
     * */
    private void initData(){

        //没有类似的数据源 智能先这样凑合一下
        ss[0] = "http://guolin.tech/api/bing_pic";
//        ss[1] = "https://bing.ioliu.cn/v1/rand";
//        ss[2] = "https://api.i-meto.com/bing?cat=A";



        for (i = 0; i < 3; i++) {

            final ImageView iv = new ImageView(MainActivity.this);
            Request request = new Request.Builder().url(ss[0]).build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    final String url = response.body().string();
//                    Log.e("hhp","------"+response.body().string());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(MainActivity.this).load(url).into(iv);
                        }
                    });
                }
              });

            mViews.add(iv);
        }

        ImageAdapter vpAdapter = new ImageAdapter(MainActivity.this,mViews);
        viewPager.setAdapter(vpAdapter);

    }

}