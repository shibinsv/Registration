 package com.example.registration.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.registration.Api.ApiClient;
import com.example.registration.Api.ApiInterface;
import com.example.registration.Api.Credentials;
import com.example.registration.R;
import com.example.registration.activities.otp.Profile;
import com.example.registration.activities.roomdatabase.UserDao;
import com.example.registration.activities.roomdatabase.UserDatabase;
import com.example.registration.activities.roomdatabase.UserEntity;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;

 public class MainActivity extends AppCompatActivity {

    //Initialize variable
    private AwesomeValidation awesomeValidation;
    EditText firstname,lastname,MobileNumber,Email,password,ConfirmPassword;
    Button Register_button;

    private Retrofit retrofit;
    private ApiInterface apiInterface;
    ApiClient apiClient;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        onClick();

    }

    private void onClick() {

        findViewById(R.id.Register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobileStr;
                mobileStr=MobileNumber.getText().toString();
                handle_Sign_up_Dialog();


            }
        });
    }

    private void initView() {

        //Assign variables

        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        MobileNumber=findViewById(R.id.MobileNumber);
        Email=findViewById(R.id.Email);
        password=findViewById(R.id.password);
        ConfirmPassword=findViewById(R.id.ConfirmPassword);
        Register_button=findViewById(R.id.Register_button);

        //Linking baseUrl
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    private void handle_Sign_up_Dialog() {

        //Initialize validation style

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.firstname,RegexTemplate.NOT_EMPTY,R.string.invalid_first_name);
        awesomeValidation.addValidation(this,R.id.lastname,RegexTemplate.NOT_EMPTY,R.string.invalid_last_name);
        awesomeValidation.addValidation(this,R.id.Email,Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.MobileNumber,"[5-9]{1}[0-9]{9}$",R.string.invalid_mobile_no);
        awesomeValidation.addValidation(this,R.id.password,regexPassword,R.string.password);
        awesomeValidation.addValidation(this,R.id.ConfirmPassword,R.id.password,R.string.invalid_confirm_password);


        if (awesomeValidation.validate()) {
            //check validation
                HashMap<String, String> map = new HashMap<>();
                map.put("firstname",firstname.getText().toString());
                map.put("lastname", lastname.getText().toString());
                map.put("MobileNumber", MobileNumber.getText().toString());
                map.put("Email", Email.getText().toString());
                map.put("password", password.getText().toString());
                map.put("ConfirmPassword", ConfirmPassword.getText().toString());

                    //Calling function
                    Call<Credentials> call=apiInterface.signUp(map);
                    Gson gson = new Gson();
                    String s = gson.toJson(map);
                    System.out.println("RequestJson" +s);
                   // JsonObject jsonObject = new JsonObject();


                    call.enqueue(new Callback<Credentials>() {
                        @Override
                        public void onResponse(@NotNull Call<Credentials> call, @NotNull Response<Credentials> response) {


                            Toast.makeText(MainActivity.this.getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();

                            Intent i1=new Intent(MainActivity.this,Profile.class);
                            i1.putExtra("Number",MobileNumber.getText().toString());
                            startActivity(i1);

                        }

                        @Override
                        public void onFailure(@NotNull Call<Credentials> call, Throwable t) {
                            Toast.makeText(MainActivity.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    //On failure
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }



}