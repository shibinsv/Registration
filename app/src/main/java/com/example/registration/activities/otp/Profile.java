package com.example.registration.activities.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.Api.ApiClient;
import com.example.registration.Api.ApiInterface;
import com.example.registration.R;
import com.example.registration.activities.End;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    private TextView otp1,otp2,otp3,otp4,otp5,otp6,textMobile;
    private Button button_OTP;
    private ApiInterface api;
    ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        setUpOTPInputs();
        workup();

    }

    private void workup() {
        button_OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String,String>map2=new HashMap<>();
                map2.put("otp1",otp1.getText().toString());
                map2.put("otp2",otp2.getText().toString());
                map2.put("otp3",otp3.getText().toString());
                map2.put("otp4",otp4.getText().toString());
                map2.put("otp5",otp5.getText().toString());
                map2.put("otp6",otp6.getText().toString());

                //Calling
                Call<OtpCredentials>call2=api.otpCheck(map2);
                Gson gson=new Gson();

                call2.enqueue(new Callback<OtpCredentials>() {
                    @Override
                    public void onResponse(@NotNull Call<OtpCredentials> call2, @NotNull Response<OtpCredentials> response) {

                        if(response.code()==200){
                            Intent i2=new Intent(Profile.this, End.class);
                            startActivity(i2);
                            Toast.makeText(Profile.this,"OTP Verified",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Profile.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<OtpCredentials> call2, Throwable t) {

                        Toast.makeText(Profile.this,t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }

    private void setUpOTPInputs() {

        textMobile=findViewById(R.id.textMobile);
        String NumberTwo=getIntent().getStringExtra("Number");
        textMobile.setText(NumberTwo);


        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    button_OTP.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initView() {
        otp1=findViewById(R.id.otp1);
        otp2=findViewById(R.id.otp2);
        otp3=findViewById(R.id.otp3);
        otp4=findViewById(R.id.otp4);
        otp5=findViewById(R.id.otp5);
        otp6=findViewById(R.id.otp6);
        button_OTP=findViewById(R.id.button_OTP);

        //Linking baseUrl
        api = ApiClient.getClient().create(ApiInterface.class);
    }
}