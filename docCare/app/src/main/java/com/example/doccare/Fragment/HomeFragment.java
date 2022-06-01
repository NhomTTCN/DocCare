package com.example.doccare.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doccare.Adapter.BlogAdapter;
import com.example.doccare.Model.Blog;
import com.example.doccare.Model.BlogListResponse;
import com.example.doccare.Model.DoctorInfo;
import com.example.doccare.Network.ApiService;
import com.example.doccare.R;
import com.example.doccare.ViewModel.InfoViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    List<Blog> bloglist = new ArrayList<>();
    BlogAdapter blogAdapter;
    RecyclerView recyview_blog;
    TextView tv_name;
    InfoViewModel infoViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infoViewModel = new ViewModelProvider(getActivity()).get(InfoViewModel.class);

        recyview_blog = view.findViewById(R.id.recyview_blogs);
        tv_name = view.findViewById(R.id.tv_welcome_name);

        tv_name.setText("Hi, " + infoViewModel.getLoginResponse().getInfo().getName());

        //fake data
        bloglist.add(new Blog("aa","ada", new DoctorInfo(), true, "title...", "desc...", "avc", "bd", "a"));
        bloglist.add(new Blog("aa","ada", new DoctorInfo(), true, "title...", "desc...", "avc", "bd", "a"));
        bloglist.add(new Blog("aa","ada", new DoctorInfo(), true, "title...", "desc...", "avc", "bd", "a"));
        blogAdapter = new BlogAdapter(bloglist, view.getContext());
        recyview_blog.setAdapter(blogAdapter);

        // get bloglist
        getBlogList();

    }

    private void getBlogList() {
        ApiService.apiService.getBlogList("").enqueue(new Callback<BlogListResponse>() {
            @Override
            public void onResponse(Call<BlogListResponse> call, Response<BlogListResponse> response) {
                Log.d("BLOG", response.message().toString());
                bloglist = response.body().getResults();
                blogAdapter = new BlogAdapter(bloglist, getContext());
                recyview_blog.setAdapter(blogAdapter);
            }

            @Override
            public void onFailure(Call<BlogListResponse> call, Throwable t) {

            }
        });
    }
}
