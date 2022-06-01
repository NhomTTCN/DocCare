package com.example.doccare.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doccare.DoctorSpace.DoctorMainActivity;
import com.example.doccare.Model.LoginResponse;
import com.example.doccare.Network.ApiService;
import com.example.doccare.R;
import com.example.doccare.ViewModel.InfoViewModel;
import com.example.doccare.ViewModel.TokenViewModel;
import com.google.firebase.firestore.FirebaseFirestore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_email, edt_password;
    Button btn_login, btn_createnewaccount;
    TextView tv_forgetpassword;
    TokenViewModel tokenViewModel;
    InfoViewModel infoViewModel;
    ProgressDialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tokenViewModel = new ViewModelProvider(this).get(TokenViewModel.class);
        infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
        progressDialog = new ProgressDialog(this);

        mapping();
        get_regis();

    }

    private void get_regis() {
        edt_email.setText(getIntent().getStringExtra("regis_username"));
        edt_password.setText(getIntent().getStringExtra("regis_password"));
    }

    private void mapping() {
        edt_email = findViewById(R.id.edt_login_email);
        edt_password = findViewById(R.id.edt_login_password);
        btn_login = findViewById(R.id.btn_login);
        btn_createnewaccount = findViewById(R.id.btn_createaccount);
        tv_forgetpassword = findViewById(R.id.tv_forgetpassword);

        btn_login.setOnClickListener(this);
        btn_createnewaccount.setOnClickListener(this);
        tv_forgetpassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_createaccount:
                register();
                break;
            case R.id.tv_forgetpassword:
                forgotpassword();
                break;
        }
    }

    private void forgotpassword() {
    }

    private void register() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void login() {


        if(!checkblank())
            return;
        progressDialog.show();
        progressDialog.setMessage(getString(R.string.please_wait));
        ApiService.apiService.login(edt_email.getText().toString(),
                edt_password.getText().toString()).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.message().equals("OK")) {
                    LoginResponse loginResponse = response.body();
                    Log.d("LOGIN", loginResponse.getAccessToken().toString());

                    //set access_token
                    tokenViewModel.setAccesstoken(loginResponse.getAccessToken());

                    if(loginResponse.getRole().equals("USER")) {

                        // set userviewmodel
                        infoViewModel.setLiveInfo(loginResponse);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(LoginActivity.this, DoctorMainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    progressDialog.cancel();
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
                //    Log.d("result", response.body().toString());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });



    }


    private boolean checkblank() {
        if (edt_email.getText().toString().isEmpty() || edt_password.getText().toString().isEmpty()) {
            return false;
        }
            return true;
    }
}