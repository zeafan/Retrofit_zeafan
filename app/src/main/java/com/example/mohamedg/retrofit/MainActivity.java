package com.example.mohamedg.retrofit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    final String baseUrl = "https://api.github.com/";
    TextView post,git,news;
    final String baseUrl2="https://jsonplaceholder.typicode.com/";
    final String baseUrl_news="https://newsapi.org/v2/";
    ///v2/top-headlines?country=us&yh=business&apiKey=1ee192ab057f4901800d173e1d00a161";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        post = findViewById(R.id.txt_post);
        git=findViewById(R.id.txt_git);
        news=findViewById(R.id.txt_news);
    }
    public void GetGitRepos(View view) {
        final ProgressDialog progressDialog=CreateDialog(MainActivity.this);



 Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        Iretrofit iretrofit = retrofit.create(Iretrofit.class);
        iretrofit.GetRepos("zeafan").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    git.setText(response.body().string());
                    progressDialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ProgressDialog CreateDialog(MainActivity mainActivity) {
        ProgressDialog progressDialog=new ProgressDialog(mainActivity);
       progressDialog.setTitle("Downloading..");
       progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        return progressDialog;
    }

    //https://newsapi.org/v2/top-headlines?country=eg&category=business&apiKey=1ee192ab057f4901800d173e1d00a161
    public void GetNews(View view) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl_news).build();
        final ProgressDialog progressDialog=CreateDialog(MainActivity.this);
        Iretrofit iretrofit = retrofit.create(Iretrofit.class);
        HashMap map=new HashMap();
        map.put("apiKey","1ee192ab057f4901800d173e1d00a161");
        map.put("country","us");
        map.put("category","business");

        iretrofit.getNews2(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    news.setText(response.body().string());
                    progressDialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                   Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();

            }
        });
//            iretrofit.getNews("1ee192ab057f4901800d173e1d00a161", "us", "business").enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    try {
//                        news.setText(response.body().string());
//                        progressDialog.dismiss();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//                }
//            });
    }


    public void Getpost(View view) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl2).build();
        final ProgressDialog progressDialog=CreateDialog(MainActivity.this);

        Iretrofit iretrofit = retrofit.create(Iretrofit.class);
        try {
            iretrofit.getPosts().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        post.setText(response.body().string());
                        progressDialog.dismiss();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception ex)
        {
            Toast.makeText(this, ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void PostData(View view) {
        HashMap map=new HashMap();
        map.put("body","ullam et saepe reiciendis voluptatem adipisci\\nsit amet autem assumenda provident rerum culpa\\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\\nquis sunt voluptatem rerum illo velit");
        map.put("userId","1");
        map.put("title","eum et est occaecati");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl2).build();
        Iretrofit iretrofit = retrofit.create(Iretrofit.class);
        try {
            iretrofit.getNews_post(map).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        post.setText(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception ex)
        {
            Toast.makeText(this, ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
