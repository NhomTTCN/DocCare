package com.example.doccare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doccare.Model.Message;
import com.example.doccare.R;

import org.w3c.dom.Text;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private static int VIEW_TYPE_MY_MESSAGE = 1;
    private static int VIEW_TYPE_OTHER_MESSAGE = 2;
    List<Message> listMessage;
    Context context;

    public MessageAdapter(List<Message> listMessage, Context context) {
        this.listMessage = listMessage;
        this.context = context;
    }

    public void addMessage(Message message) {
        listMessage.add(message);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        Message message = listMessage.get(position);
        if (message.getMe()) {
            return VIEW_TYPE_MY_MESSAGE;
        } else {
            return VIEW_TYPE_OTHER_MESSAGE;
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_MY_MESSAGE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_my_message, parent, false);
            return new MyMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_other_message, parent, false);
            return new OtherMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = listMessage.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

        }
        public void bind (Message message){

        }
    }

    class MyMessageViewHolder extends MessageViewHolder {
        TextView tv_mymessage;
        TextView tv_time;

        public MyMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_mymessage = itemView.findViewById(R.id.txtMyMessage);
            tv_time = itemView.findViewById(R.id.txtMyMessageTime);
        }

        @Override
        public void bind(Message message) {
            super.bind(message);
            tv_mymessage.setText(message.getMessage());
            tv_time.setText(message.getTime());
        }
    }

    class OtherMessageViewHolder extends MessageViewHolder {
        TextView tv_othermessage;
        TextView tv_time;

        public OtherMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_othermessage = itemView.findViewById(R.id.txtOtherMessage);
            tv_time = itemView.findViewById(R.id.txtOtherMessageTime);
        }

        @Override
        public void bind(Message message) {
            super.bind(message);
            tv_othermessage.setText(message.getMessage());
            tv_time.setText(message.getTime());
        }
    }
}
