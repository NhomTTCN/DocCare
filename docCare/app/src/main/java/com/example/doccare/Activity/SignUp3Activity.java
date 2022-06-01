package com.example.doccare.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doccare.Model.Account;
import com.example.doccare.Model.Insurance;
import com.example.doccare.Model.SignUpResponse;
import com.example.doccare.Model.User;
import com.example.doccare.Network.ApiService;
import com.example.doccare.R;
import com.google.gson.JsonObject;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp3Activity extends AppCompatActivity implements View.OnClickListener{

    EditText edt_firstname, edt_lastname, edt_phone, edt_career;
    RadioGroup radio_gender;
    TextView tv_birthday;
    ImageView btn_birthday;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup3);


        mapping();
    }


    private void mapping() {
        edt_firstname = findViewById(R.id.edt_firstname);
        edt_lastname = findViewById(R.id.edt_lastname);
        edt_phone = findViewById(R.id.edt_phone);
        edt_career = findViewById(R.id.edt_career);
        radio_gender = findViewById(R.id.radio_gender);
        tv_birthday = findViewById(R.id.tv_birthday);
        btn_birthday = findViewById(R.id.btn_birthday);
        btn_signup = findViewById(R.id.btn_signup);

        btn_birthday.setOnClickListener(this);
        btn_signup.setOnClickListener(this);
        radio_gender.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_birthday:
                pickbirthday();
                break;
            case R.id.btn_signup:
                signup();
                break;
        }
    }


    private void pickbirthday() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1++;
                tv_birthday.setText(i +"-" + i1 +"-" + i2);

            }
        },year, month, day);
        datePickerDialog.show();
    }
    private void signup(){
        if(!checkblank()) return;
        Intent intent = getIntent();
        Account account1 = (Account) intent.getSerializableExtra("account");
        Insurance insurance1 = (Insurance) intent.getSerializableExtra("insurance");
//
        User user = new User();
        user.setAccount(account1);
        user.setInsurance(insurance1);
        user.setCreatedAt(account1.getCreatedAt());
        Log.d("CrateAt", account1.getCreatedAt());
        user.setActivate(false);
        user.setName(edt_firstname.getText().toString()
                + edt_lastname.getText().toString());
        user.setPhone(edt_phone.getText().toString());
        user.setBirthday(tv_birthday.getText()); //this
        user.setGender(getGender());
        user.setCareer(edt_career.getText().toString());
        user.setUsername(user.getAccount().getEmail());
        user.setPassword(user.getAccount().getPassword());

        //retrofit call register-API
        ApiService.apiService.signup(user).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
               Log.d("checkmess",response.message().toString());
                if(response.message().equals("Created")){
                    SignUpResponse signUpResponse = response.body();
                    Toast.makeText(SignUp3Activity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp3Activity.this, LoginActivity.class);
                    intent.putExtra("regis_username", user.getUsername());
                    intent.putExtra("regis_password", user.getPassword());
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUp3Activity.this, "Tài khoản đã có người đăng kí", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });




    }

    private String getGender() {
        if(radio_gender.getCheckedRadioButtonId()==R.id.radio_male)
            return "Male";
        return "Female";
    }

    private boolean checkblank() {
        if(edt_firstname.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.first_nameisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edt_lastname.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.last_nameisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edt_phone.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.phoneisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tv_birthday.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.birthdayisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edt_career.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.careerisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}