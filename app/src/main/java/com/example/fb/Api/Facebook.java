package com.example.fb.Api;

import com.example.fb.Model.ApiUser;
import com.example.fb.Model.LoadData;
import com.example.fb.response.ImageResponse;
import com.example.fb.response.SignupResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Facebook {


    @Multipart
    @POST("imageinsert")
    Call<ImageResponse> uploadProfileImage(@Part MultipartBody.Part img);


    @POST("registeruser")
    Call<Void> registerUser(@Body ApiUser user);


    @FormUrlEncoded
    @POST("login")
    Call<SignupResponse> checkUser(@Field("email") String email, @Field("password") String password);

    @GET("users")
    Call<ApiUser> getUserDetails(@Header("Authorization") String token);


    @GET("loadall")
    Call<List<LoadData>> getTimelines(@Header("Authorization") String token);


}
