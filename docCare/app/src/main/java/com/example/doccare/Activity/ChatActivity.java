package com.example.doccare.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doccare.Adapter.MessageAdapter;
import com.example.doccare.Model.Message;
import com.example.doccare.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText edt_message;
    ImageButton btn_send;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mapping();
        //fake data
        List<Message> listMessage = new ArrayList();
        listMessage.add(new Message(true, "i love ui lovi love ui love ui love ui love ui love ui love ui love u", "abc", "abc", "abc", "12:00", true));
        listMessage.add(new Message(true, "so much", "abc", "abc", "abc", "12:01", true));
        listMessage.add(new Message(true, "oke", "abc", "abc", "abc", "22:00", false));
        listMessage.add(new Message(true, "yep", "abc", "abc", "abc", "22:01", true));
        messageAdapter = new MessageAdapter(listMessage, this);

        recyclerView.setAdapter(messageAdapter);
    }

    private void mapping() {
        recyclerView = findViewById(R.id.recy_messageList);
        edt_message = findViewById(R.id.txtMessage);
        btn_send = findViewById(R.id.btnSend);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
