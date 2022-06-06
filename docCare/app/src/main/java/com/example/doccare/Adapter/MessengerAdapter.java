package com.example.doccare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doccare.Activity.ChatActivity;
import com.example.doccare.Model.Messenger;
import com.example.doccare.R;
import com.google.android.material.imageview.ShapeableImageView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class MessengerAdapter extends RecyclerView.Adapter<MessengerAdapter.MessengerViewHolder> {
    Context context;
    List<Messenger> listMessgers;

    public MessengerAdapter(Context context, List<Messenger> listMessgers) {
        this.context = context;
        this.listMessgers = listMessgers;
    }

    @NonNull
    @Override
    public MessengerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_messenger, parent, false);
        return new MessengerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessengerViewHolder holder, int position) {
        holder.tv_time.setText(listMessgers.get(position).getTime());
        String toPerson = "";
        if (listMessgers.get(position).getDoctor()) {
            toPerson = listMessgers.get(position).getUser_name();
        } else {
            toPerson = listMessgers.get(position).getDoctorname();
        }
        holder.tv_name.setText(toPerson);
        holder.tv_last_message.setText(listMessgers.get(position).getMessage());
        int pos = position;
        String finalToPerson = toPerson;
        holder.messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.messenger.getContext(), ChatActivity.class);
                intent.putExtra("id", listMessgers.get(pos).getIdWith());
                intent.putExtra("name", finalToPerson);
                holder.messenger.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMessgers.size();
    }


    class MessengerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_last_message;
        TextView tv_time;
        CardView messenger;
        ShapeableImageView img_avatar;

        public MessengerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_messenger_name);
            tv_last_message = itemView.findViewById(R.id.tv_last_message);
            tv_time = itemView.findViewById(R.id.tv_time);
            messenger = itemView.findViewById(R.id.messager);
            img_avatar = itemView.findViewById(R.id.img_avatar);
        }
    }
}
