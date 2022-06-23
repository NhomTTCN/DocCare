package com.example.doccare.Network;

import android.widget.EditText;

import com.example.doccare.Model.Account;
import com.example.doccare.Model.BlogListResponse;
import com.example.doccare.Model.EditDoctorRequest;
import com.example.doccare.Model.Insurance;
import com.example.doccare.Model.LoginResponse;
import com.example.doccare.Model.ReViewResponse;
import com.example.doccare.Model.ReviewDoctor;
import com.example.doccare.Model.SearchDoctorResponse;
import com.example.doccare.Model.SignUpResponse;
import com.example.doccare.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://doctorz.ga/api/v1/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    // sign up
    @POST("user/signup")
    Call<SignUpResponse> signup(@Body User user);


    @FormUrlEncoded
    @POST("account/login")
    Call<LoginResponse> login(@Field("username") String username,
                              @Field("password") String password);


    @Multipart
    @PUT("user/edit_own_profile")
    Call<ResponseBody> editownprofile(@Header("Authorization") String token,
                                      @Part("name") RequestBody name,
                                      @Part("phone") RequestBody phone,
                                      @Part("birthday") RequestBody birthday,
                                      @Part("gender") RequestBody gender,
                                      @Part("career") RequestBody career,
                                      @Part MultipartBody.Part avatar);


    @GET("doctor/search/")
    Call<SearchDoctorResponse> searchdoc(
            @Query("q") String string,
            @Query("page") int page,
            @Query("page_size") int page_size);

    @GET("doctor/review/get_by_doctor_id/")
    Call<ReViewResponse> getReviews(
            @Header("Authorization") String token,
            @Query("doctor_id") String doctor_id
    );

    @POST("doctor/review")
    Call<ResponseBody> reviewDoctor(
            @Header("Authorization") String token,
            @Body ReviewDoctor reviewDoctor
    );

    @PUT("doctor/review/{review_id}")
    Call<ResponseBody> editReviewDoctor(
            @Header("Authorization") String token,
            @Path("review_id") String review_id,
            @Body ReviewDoctor reviewDoctor
    );

    @GET("blog/")
    Call<BlogListResponse> getBlogList(
            @Query("q") String string
    );

    //http://doctorz.cf/api/v1/doctor/edit_own_profile
    @PUT("doctor/edit_own_profile")
    Call<ResponseBody> editowndoctorprofile(
            @Body EditDoctorRequest editDoctorRequest
    );


}
