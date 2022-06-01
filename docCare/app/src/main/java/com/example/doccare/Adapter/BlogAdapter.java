package com.example.doccare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doccare.Activity.DetailBlogActivity;
import com.example.doccare.Model.Blog;
import com.example.doccare.R;

import java.io.Serializable;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {
    List<Blog> listblogs;
    Context context;

    public BlogAdapter(List<Blog> listblogs, Context context) {
        this.listblogs = listblogs;
        this.context = context;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        holder.tv_title.setText(listblogs.get(position).getTitle());
        holder.tv_desc.setText(listblogs.get(position).getDesc());
        holder.tv_writter.setText(listblogs.get(position).getDoctor().getFullName());

        Glide.with(holder.tv_desc.getContext())
                .load(listblogs.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.img_blog);
        Blog blog = listblogs.get(position);
        holder.tv_readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.tv_desc.getContext(), DetailBlogActivity.class);
                intent.putExtra("title", blog.getTitle());
                intent.putExtra("author", blog.getDoctor().getFullName());
                intent.putExtra("img", blog.getImage());
                intent.putExtra("content", blog.getContent());
                holder.tv_desc.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listblogs.size();
    }

    class BlogViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title, tv_desc, tv_writter, tv_readmore;
        ImageView img_blog;
        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_titleblog);
            tv_desc = itemView.findViewById(R.id.tv_descblog);
            tv_writter = itemView.findViewById(R.id.tv_blogwritter);
            img_blog = itemView.findViewById(R.id.img_blog);
            tv_readmore = itemView.findViewById(R.id.tv_readmore);
        }
    }
}
