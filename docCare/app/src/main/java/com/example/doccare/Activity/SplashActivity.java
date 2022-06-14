package com.example.doccare.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doccare.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView tv_doccare = findViewById(R.id.tv_doccare);


        Animation animationFade = AnimationUtils.loadAnimation(this, R.anim.anim_doccare);
        tv_doccare.startAnimation(animationFade);

        boolean handler = new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        }, 3000);
    }

}

