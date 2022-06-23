package com.example.doccare.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doccare.Adapter.ReviewDoctorAdapter;
import com.example.doccare.Model.ReViewResponse;
import com.example.doccare.Model.Review;
import com.example.doccare.Model.ReviewDoctor;
import com.example.doccare.Model.Reviewer;
import com.example.doccare.Network.ApiService;
import com.example.doccare.R;
import com.example.doccare.ViewModel.TokenViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewDoctorActivity extends AppCompatActivity {
    TextView tv_doctor_name, tv_hospital_name;
    RatingBar ratingBar;
    EditText edt_review;
    Button btn_review;
    RecyclerView recyclerView_listreview;
    ImageView btn_back;
    TokenViewModel tokenViewModel;
    List<Review> listReviews = new ArrayList();
    ReviewDoctorAdapter reviewDoctorAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewdoctor);

        setUI();
    }

    private void setUI() {
        tv_doctor_name = findViewById(R.id.tv_name_revieweddoctor);
        tv_hospital_name = findViewById(R.id.tv_hostpitalname_revieweddoctor);
        ratingBar = findViewById(R.id.rating_doctor);
        edt_review = findViewById(R.id.edt_review);
        btn_review = findViewById(R.id.btn_review);
        recyclerView_listreview = findViewById(R.id.recycleview_listreview);
        btn_back = findViewById(R.id.btn_backtoreview);

        tv_doctor_name.setText(getIntent().getStringExtra("name"));
        tv_hospital_name.setText(getIntent().getStringExtra("hospital"));
        tokenViewModel = new ViewModelProvider(this).get(TokenViewModel.class);

        showReview();

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickReviewButton();

                //fake data
                edt_review.setText("");
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showReview() {
//        Log.d("review_token", tokenViewModel.getAccessToken());
        String token = "Bearer " + tokenViewModel.getAccessToken();
        ApiService.apiService.getReviews(token,getIntent().getStringExtra("id")).enqueue(new Callback<ReViewResponse>() {
            @Override
            public void onResponse(Call<ReViewResponse> call, Response<ReViewResponse> response) {
                listReviews = response.body().getResults();
                Log.d("REVIEW_RESPONSE", response.message().toString());
                Log.d("REVIEW_RESPONSE2", String.valueOf(response.body().getResults().size()));
                reviewDoctorAdapter = new ReviewDoctorAdapter(listReviews, getApplicationContext());
                recyclerView_listreview.setAdapter(reviewDoctorAdapter);
            }

            @Override
            public void onFailure(Call<ReViewResponse> call, Throwable t) {
            }
        });
    }

    private void clickReviewButton() {
        String comment = edt_review.getText().toString();
        Log.d("cmt", comment);
        int rating = (int) ratingBar.getRating();
        Log.d("rating", String.valueOf(rating));
        String doctorId = getIntent().getStringExtra("id");
        Log.d("DOCTOR_ID", doctorId);
        if(comment.isEmpty() || ratingBar.getRating() == 0)
            return;
        String token = "Bearer " + tokenViewModel.getAccessToken();
        Log.d("token", tokenViewModel.getAccessToken());
        ReviewDoctor reviewDoctor = new ReviewDoctor(comment, rating, doctorId);

        ApiService.apiService.reviewDoctor(token, reviewDoctor).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("review", response.message().toString() + response.raw().toString());
                Log.d("review", call.toString());
                Log.d("ID", listReviews.get(0).getId());
                if(response.code() == 400 ){
                    ApiService.apiService.editReviewDoctor(token, listReviews.get(0).getId(), reviewDoctor).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.d("editREVIEW", response.message());
                            showReview();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
                showReview();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        edt_review.setText("");
        ratingBar.setRating(0);


    }
}
