package com.example.mohamedg.retrofit;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Iretrofit {
    @GET("/users/{nameuser}/repos")
    Call<ResponseBody>GetRepos(@Path("nameuser")String name);

    @GET("posts")
    Call<ResponseBody> getPosts();

    @GET("top-headlines")
    Call<ResponseBody>  getNews2(@QueryMap Map<String,String> muInfo);
  //  Call<ResponseBody> getNews(@Query("apiKey") String apikey,@Query("country") String country,@Query("category") String category);
  @POST("posts")
  @FormUrlEncoded
  Call<ResponseBody> getNews_post(@FieldMap Map<String,String> muInfo2);
}

