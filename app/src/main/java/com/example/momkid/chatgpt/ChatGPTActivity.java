package com.example.momkid.chatgpt;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkid.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ChatGPTActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView welcomeTxt;
    EditText messageEdt;
    ImageButton sendBtn;
    List<ChatGPTModel> messageList;
    ChatGPTAdapter chatGPTAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgpt);
        messageList = new ArrayList<>();

        recyclerView=findViewById(R.id.recycler_view);
        welcomeTxt= findViewById(R.id.welcome_text);
        messageEdt= findViewById(R.id.edt_message);
        sendBtn= findViewById(R.id.btn_send);

        //setup recycler view
        chatGPTAdapter = new ChatGPTAdapter(messageList);
        recyclerView.setAdapter(chatGPTAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        sendBtn.setOnClickListener((v)->{
            String question = messageEdt.getText().toString().trim();
            addToChat(question,ChatGPTModel.SENT_BY_ME);
        });
    }

    void addToChat(String message,String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new ChatGPTModel(message,sentBy));
                chatGPTAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(chatGPTAdapter.getItemCount());
            }
        });
    }
}
