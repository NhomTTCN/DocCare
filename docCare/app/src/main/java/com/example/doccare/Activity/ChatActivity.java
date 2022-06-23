package com.example.doccare.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doccare.Adapter.MessageAdapter;
import com.example.doccare.DoctorSpace.DoctorMainActivity;
import com.example.doccare.Model.Message;
import com.example.doccare.R;
import com.example.doccare.ViewModel.InfoViewModel;
import com.example.doccare.common.HideKeyBoard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText edt_message;
    ImageButton btn_send;
    MessageAdapter messageAdapter;
    TextView tv_chater;
    String id_chater;
    String name_chater;
    InfoViewModel infoViewModel;
    ImageButton btn_back;
    CollectionReference docRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //getIntent
        id_chater = getIntent().getStringExtra("id");
        name_chater = getIntent().getStringExtra("name");
        infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
        mapping();
        Log.d("check_info_chat", infoViewModel.getLiveInfo().getValue().getInfo().getId());

        //fake data
        getListMessage();

        docRef = FirebaseFirestore.getInstance().collection("listchats").document(infoViewModel.getLiveInfo().getValue().getInfo().getId())
                .collection("with")
                .document(id_chater).collection("messages");


        docRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                Log.d("change", "change");
                List<Message> listMessages = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : value) {
                    Message message = documentSnapshot.toObject(Message.class);
                    Log.d("mess", message.toString());
                    if (message.getReceiver().equals(id_chater)) {
                        message.setMe(true);
                    } else {
                        message.setMe(false);
                    }
                    listMessages.add(message);
                    listMessages.sort(Comparator.comparing(Message::getTime));
                }
                Log.d("listmesages", String.valueOf(listMessages.size()));
                messageAdapter = new MessageAdapter(listMessages, getApplicationContext());
                recyclerView.setAdapter(messageAdapter);
            }
        });
    }

    private void getListMessage() {
        FirebaseFirestore.getInstance()
                .collection("listchats")
                .document(infoViewModel.getLiveInfo().getValue().getInfo().getId())
                .collection("with")
                .document(id_chater)
                .collection("messages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Message> listMessages = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Message message = documentSnapshot.toObject(Message.class);
                                Log.d("mess", message.toString());
                                if (message.getReceiver().equals(id_chater)) {
                                    message.setMe(true);
                                } else {
                                    message.setMe(false);
                                }
                                listMessages.add(message);
                                listMessages.sort(Comparator.comparing(Message::getTime));
                            }
                            Log.d("listmesages", String.valueOf(listMessages.size()));
                            messageAdapter = new MessageAdapter(listMessages, getApplicationContext());
                            recyclerView.setAdapter(messageAdapter);
                        }
                    }
                });
    }

    private void mapping() {
        recyclerView = findViewById(R.id.recy_messageList);
        edt_message = findViewById(R.id.txtMessage);
        btn_send = findViewById(R.id.btnSend);
        tv_chater = findViewById(R.id.tv_chater);
        btn_back = findViewById(R.id.btn_backtomessenger);

        tv_chater.setText(name_chater);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoViewModel.getLiveInfo().getValue().getRole().equals("USER")) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), DoctorMainActivity.class);
                    startActivity(intent);
                }

            }
        });


    }

    private void sendMessage() {
        if (edt_message.getText().toString().isEmpty())
            return;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String current_time = formatter.format(date.getTime());

        String username = "";
        String doctorname = "";

        if (infoViewModel.getLiveInfo().getValue().getRole().equals("USER")) {
            username = infoViewModel.getLiveInfo().getValue().getInfo().getName();
            doctorname = name_chater;
        } else {
            doctorname = infoViewModel.getLiveInfo().getValue().getInfo().getDoctor_name();
            username = name_chater;

        }

        Map<String, Object> message = new HashMap<>();
        message.put("message", edt_message.getText().toString());
        message.put("receiver", id_chater);
        message.put("sender", infoViewModel.getLiveInfo().getValue().getInfo().getId());
        message.put("time", current_time);
        message.put("username", username);
        message.put("idWith", id_chater);
        message.put("doctorname", doctorname);


        Map<String, Object> message1 = new HashMap<>();
        message1.put("message", edt_message.getText().toString());
        message1.put("receiver", id_chater);
        message1.put("sender", infoViewModel.getLiveInfo().getValue().getInfo().getId());
        message1.put("time", current_time);
        message1.put("username", username);
        message1.put("idWith", infoViewModel.getLiveInfo().getValue().getInfo().getId());
        message1.put("doctorname", doctorname);


        FirebaseFirestore.getInstance()
                .collection("listchats")
                .document(infoViewModel.getLiveInfo().getValue().getInfo().getId())
                .collection("with")
                .document(id_chater)
                .collection("messages")
                .add(message)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        edt_message.setText("");
                        new HideKeyBoard().hideKeyboard(ChatActivity.this);
                        getListMessage();
                    }
                });

        FirebaseFirestore.getInstance()
                .collection("listchats")
                .document(id_chater)
                .collection("with")
                .document(infoViewModel.getLiveInfo().getValue().getInfo().getId())
                .collection("messages")
                .add(message1)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                });

        FirebaseFirestore.getInstance()
                .collection("listmessengers")
                .document(infoViewModel.getLiveInfo().getValue().getInfo().getId())
                .collection("with")
                .document(id_chater)
                .set(message)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

        FirebaseFirestore.getInstance()
                .collection("listmessengers")
                .document(id_chater)
                .collection("with")
                .document(infoViewModel.getLiveInfo().getValue().getInfo().getId())
                .set(message1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

    }


}
