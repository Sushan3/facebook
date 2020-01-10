package com.example.fb.Bll;


import com.example.fb.Api.Facebook;
import com.example.fb.response.SignupResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginCheck {

    boolean isSuccess = false;
    private Retrofit retrofit;
    private Facebook facebook;
    public static String token = "Bearer ";

    public boolean checkUser(String email, String password) {


        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        facebook = retrofit.create(Facebook.class);

        Call<SignupResponse> usersCall = facebook.checkUser(email, password);


        try {
            Response<SignupResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}