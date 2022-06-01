package com.example.doccare.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doccare.Activity.LoginActivity;
import com.example.doccare.Activity.SignUp2Activity;
import com.example.doccare.Model.Account;
import com.example.doccare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_email, edt_password, edt_checkpassword;
    Button btn_continue, btn_backtologin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //full screen UI
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup);

        mapping();
    }

    private void mapping() {
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_checkpassword = findViewById(R.id.edt_confirmpassword);
        btn_continue = findViewById(R.id.btn_continue);
        btn_backtologin = findViewById(R.id.btn_backtologin);

        btn_continue.setOnClickListener(this);
        btn_backtologin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_continue:
                continuetosignup();
                break;
            case R.id.btn_backtologin:
                backtologin();
                break;

        }
    }

    private void backtologin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    private void continuetosignup() {
        if(edt_email.getText().toString().isEmpty() || edt_password.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.pleasefillfull,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(edt_password.getText().toString().length()<6){
            Toast.makeText(this,
                    R.string.minpassword,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(!edt_password.getText().toString().equals(edt_checkpassword.getText().toString())){
            Toast.makeText(this,
                    R.string.wrongconfirmpassword,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Account account = new Account();

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String cur_time = format.format(Date.parse(currentTime.toString()));



        account.setLastLogin(cur_time);
        account.setCreatedAt(cur_time);
        account.setIsActive(true);
        account.setUsername("username");
        account.setEmail(edt_email.getText().toString());
        account.setPassword(edt_password.getText().toString());
        account.setAvatar("non.png");
        account.setIsActivate(true);
        account.setRole(null);

        Intent intent = new Intent(this, SignUp2Activity.class);
        intent.putExtra("account", account);
        startActivity(intent);
    }
}
