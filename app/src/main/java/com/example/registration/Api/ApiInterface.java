package com.example.registration.Api;

import com.example.registration.activities.otp.OtpCredentials;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("api/post")
    Call<Credentials>signUp(@Body HashMap<String,String>map);

    @POST("api/otp")
    Call<OtpCredentials>otpCheck(@Body HashMap<String,String>map);
}
