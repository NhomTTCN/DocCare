package com.example.doccare.Adapter;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doccare.Model.Review;
import com.example.doccare.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewDoctorAdapter extends RecyclerView.Adapter<ReviewDoctorAdapter.ReViewDoctorViewHolder> {
    List<Review> listreviews;
    Context context;

    public ReviewDoctorAdapter(List<Review> listreviews, Context context) {
        this.listreviews = listreviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ReViewDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_doctor, parent, false);
        return new ReViewDoctorViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listreviews.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ReViewDoctorViewHolder holder, int position) {
        holder.tv_name.setText(listreviews.get(position).getUser().getName());
        holder.rating.setRating(listreviews.get(position).getRate());
        holder.tv_cmt.setText(listreviews.get(position).getComment());

        Glide.with(context)
                .load(listreviews.get(position).getUser().getAvatar())
                .placeholder(R.drawable.placeholder)
                .into(holder.img_avatar);

    }

    class ReViewDoctorViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img_avatar;
        TextView tv_name;
        RatingBar rating;
        TextView tv_cmt;

        public ReViewDoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            img_avatar = itemView.findViewById(R.id.img_reviewer_avatar);
            tv_name = itemView.findViewById(R.id.tv_reviewer_name);
            rating = itemView.findViewById(R.id.rating_reviewer);
            tv_cmt = itemView.findViewById(R.id.tv_cmt_reviewer);
        }
    }
}
