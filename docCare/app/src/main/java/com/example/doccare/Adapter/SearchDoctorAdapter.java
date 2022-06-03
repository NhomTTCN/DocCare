package com.example.doccare.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doccare.Activity.ChatActivity;
import com.example.doccare.Activity.ReviewDoctorActivity;
import com.example.doccare.Model.DoctorInfo;
import com.example.doccare.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchDoctorAdapter extends RecyclerView.Adapter<SearchDoctorAdapter.DoctorViewHolder> {
    List<DoctorInfo> listdoctors;
    Context context;

    public SearchDoctorAdapter(List<DoctorInfo> listdoctors,Context context) {
        this.listdoctors = listdoctors;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_doctor, parent, false);
        Log.d("viewholde", "viewholder");
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name.setText( listdoctors.get(position).getFullName());
        holder.tv_hospitalname.setText(  listdoctors.get(position).getHospitalName());
        holder.tv_email.setText("Email: " + listdoctors.get(position).getEmail());
        holder.tv_department.setText("Department: " + listdoctors.get(position).getDepartmentName());
        holder.tv_clinic.setText("Clinic: " + listdoctors.get(position).getClinicName());

        holder.ln_expanded.setVisibility(View.GONE);

        listdoctors.get(position).setIs_expanded(true);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listdoctors.get(position).isIs_expanded()){
                    listdoctors.get(position).setIs_expanded(false);
                    holder.ln_expanded.setVisibility(View.VISIBLE);
                } else {
                    listdoctors.get(position).setIs_expanded(true);
                    holder.ln_expanded.setVisibility(View.GONE);
                }
            }
        });

        holder.btn_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.container.getContext(), ReviewDoctorActivity.class);
                intent.putExtra("id", listdoctors.get(position).getId());
                Log.d("TESTID", listdoctors.get(position).getId());
                intent.putExtra("name", listdoctors.get(position).getFullName());
                intent.putExtra("hospital", listdoctors.get(position).getHospitalName());
                holder.container.getContext().startActivity(intent);
            }
        });

        holder.btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.container.getContext(), ChatActivity.class);
                intent.putExtra("id", listdoctors.get(position).getId());
                intent.putExtra("name", listdoctors.get(position).getFullName());
                holder.container.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdoctors.size();
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        TextView tv_name, tv_email, tv_hospitalname, tv_department, tv_clinic;
        LinearLayout ln_expanded;
        ConstraintLayout container;
        ImageView btn_rating;
        ImageView btn_chat;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.img_doctor);
            tv_name = itemView.findViewById(R.id.tv_doctorname);
            tv_email = itemView.findViewById(R.id.tv_emaildoctor);
            tv_hospitalname = itemView.findViewById(R.id.tv_hostpitalname);
            tv_department = itemView.findViewById(R.id.tv_department);
            tv_clinic = itemView.findViewById(R.id.tv_clinic);
            ln_expanded = itemView.findViewById(R.id.ln_expanded);
            container = itemView.findViewById(R.id.doctor_container);
            btn_rating = itemView.findViewById(R.id.btn_rating);
            btn_chat = itemView.findViewById(R.id.btn_chat);
        }
    }
}
