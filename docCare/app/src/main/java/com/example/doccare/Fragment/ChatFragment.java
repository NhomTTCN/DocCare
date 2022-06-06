package com.example.doccare.Fragment;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doccare.Adapter.MessengerAdapter;
import com.example.doccare.Model.Message;
import com.example.doccare.Model.Messenger;
import com.example.doccare.R;
import com.example.doccare.ViewModel.InfoViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChatFragment extends Fragment {
    RecyclerView recy_messengers;
    MessengerAdapter messengerAdapter;
    InfoViewModel infoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_chat, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
        recy_messengers = view.findViewById(R.id.recy_messengers);

        getListMessengers();


    }

    private void getListMessengers() {
        FirebaseFirestore.getInstance()
                .collection("listmessengers")
                .document(infoViewModel.getLiveInfo().getValue().getInfo().getId())
                .collection("with")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Messenger> listMessgeners = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Messenger messenger = documentSnapshot.toObject(Messenger.class);
                                if (infoViewModel.getLiveInfo().getValue().getRole().equals("USER")) {
                                    messenger.setDoctor(false);
                                } else {
                                    messenger.setDoctor(true);
                                }
                                listMessgeners.add(messenger);
                                listMessgeners.sort(Comparator.comparing(Messenger::getTime).reversed());
                            }
                            messengerAdapter = new MessengerAdapter(getContext(), listMessgeners);
                            recy_messengers.setAdapter(messengerAdapter);
                        }
                    }
                });
    }


}
