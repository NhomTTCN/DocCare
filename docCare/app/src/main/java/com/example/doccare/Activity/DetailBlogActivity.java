package com.example.doccare.Activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.doccare.Model.Blog;
import com.example.doccare.R;

public class DetailBlogActivity extends AppCompatActivity {
    ImageView btn_back;
    TextView tv_title, tv_author, tv_content;
    ImageView img_blog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailblog);

        setUI();
    }

    private void setUI() {
        btn_back = findViewById(R.id.btn_backtoblog);
        tv_title = findViewById(R.id.tv_titledetail);
        tv_author = findViewById(R.id.tv_blogwritterdetail);
        tv_content = findViewById(R.id.tv_content);
        img_blog = findViewById(R.id.img_blogdetail);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title.setText(getIntent().getStringExtra("title"));
        tv_author.setText(getIntent().getStringExtra("author"));
        tv_content.setText(Html.fromHtml(getIntent().getStringExtra("content")));
        Glide.with(this)
                .load(getIntent().getStringExtra("img"))
                .placeholder(R.drawable.placeholder)
                .into(img_blog);
    }
}
