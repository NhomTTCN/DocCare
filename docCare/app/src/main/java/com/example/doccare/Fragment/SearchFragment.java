package com.example.doccare.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doccare.Adapter.SearchDoctorAdapter;
import com.example.doccare.Model.DoctorInfo;
import com.example.doccare.Model.SearchDoctorResponse;
import com.example.doccare.Network.ApiService;
import com.example.doccare.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    EditText edt_search;
    ImageView btn_search;
    RecyclerView recycleview_doctor;
    List<DoctorInfo> listDoctors;
    SearchDoctorAdapter searchDoctorAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_search, container, false);

        mapping(view);
        listDoctors = new ArrayList<>();
        getAllData();
        Log.d("adapter", "adapter");

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_doctor();
            }
        });


        return view;
    }

    private void search_doctor(){
        if(!edt_search.getText().toString().isEmpty()){
            callAPI(edt_search.getText().toString().trim());
            Log.d("search", "search");
        } else {
            callAPI("");
        }
    }

    private void getAllData() {
        callAPI("");
    }

    private void callAPI(String query) {
        ApiService.apiService.searchdoc(query, 1, 0 ).enqueue(new Callback<SearchDoctorResponse>() {
            @Override
            public void onResponse(Call<SearchDoctorResponse> call, Response<SearchDoctorResponse> response) {
                if (response.message().equals("OK")){
                    SearchDoctorResponse searchDoctorResponse = response.body();
                    listDoctors = searchDoctorResponse.getResults();
                    searchDoctorAdapter = new SearchDoctorAdapter(listDoctors, getContext());
                    recycleview_doctor.setAdapter( searchDoctorAdapter );
                }
            }

            @Override
            public void onFailure(Call<SearchDoctorResponse> call, Throwable t) {

            }
        });
    }

    private void mapping(View view) {
        edt_search = view.findViewById(R.id.edt_search);
        btn_search = view.findViewById(R.id.img_search);
        recycleview_doctor = view.findViewById(R.id.recycleview_searchdoctor);
    }
}
