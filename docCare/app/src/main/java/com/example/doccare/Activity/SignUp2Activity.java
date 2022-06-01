package com.example.doccare.Activity;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doccare.Model.Account;
import com.example.doccare.Model.Insurance;
import com.example.doccare.R;

import java.util.Calendar;

public class SignUp2Activity extends AppCompatActivity implements View.OnClickListener{

    EditText edt_insurancename, edt_insurancecode;
    TextView tv_createddate, tv_expireddate;
    ImageView btn_createddate, btn_expireddate;
    Button btn_continue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup2);

        mapping();
    }


    private void mapping() {
        edt_insurancename = findViewById(R.id.edt_insurancename);
        edt_insurancecode = findViewById(R.id.edt_insurancecode);
        tv_createddate = findViewById(R.id.tv_createddate);
        tv_expireddate = findViewById(R.id.tv_expireddate);
        btn_createddate = findViewById(R.id.btn_createddate);
        btn_expireddate = findViewById(R.id.btn_expireddate);
        btn_continue = findViewById(R.id.btn_continueto3);

        btn_createddate.setOnClickListener(this);
        btn_expireddate.setOnClickListener(this);
        btn_continue.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_createddate:
                pickcreateddate();
                break;
            case R.id.btn_expireddate:
                pickexpireddate();
                break;
            case R.id.btn_continueto3:
                continuetosignup();
        }
    }

    private void continuetosignup() {
        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        Log.d("Account", account.getPassword());

        if(!checkblank()) return;
        Insurance insurance = new Insurance();

        insurance.setCreatedAt(account.getCreatedAt());
        insurance.setIsActivate(true);
        insurance.setCode(edt_insurancecode.getText().toString());
        insurance.setName(edt_insurancename.getText().toString());
        insurance.setExpiredDate(tv_expireddate.getText().toString());
        insurance.setCreatedDate(tv_createddate.getText().toString());


//        "created_at": "2022-10-7 2:2:2",
//                "is_activate": false,
//                "code": "321312",
//                "name": "vaaawa",
//                "expired_date": "2022-01-10",
//                "created_date": "2022-02-10"




        Intent i_intent = new Intent(this, SignUp3Activity.class);
        i_intent.putExtra("account", account);
        i_intent.putExtra("insurance", insurance);
        finish();
        startActivity(i_intent);
    }

    private boolean checkblank() {
        if(edt_insurancename.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.insurancenameisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edt_insurancecode.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.insurancecodeisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tv_createddate.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.createddateisnotempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tv_expireddate.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.expireddateisontempty,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void pickcreateddate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1++;
                tv_createddate.setText(i +"-" + i1 +"-" + i2);

            }
        },year, month, day);
        datePickerDialog.show();
    }

    private void pickexpireddate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1++;
                tv_expireddate.setText(i +"-" + i1 +"-" + i2);

            }
        },year, month, day);
        datePickerDialog.show();
    }
}
