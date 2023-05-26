package com.example.momkid.chatgpt;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkid.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatGPTActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView welcomeTxt;
    EditText messageEdt;
    ImageButton sendBtn;
    List<ChatGPTModel> messageList;
    ChatGPTAdapter chatGPTAdapter;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

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
            messageEdt.setText("");
            callAPI(question);
            welcomeTxt.setVisibility(View.GONE);
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

    void addResponse (String response){
        messageList.remove(messageList.size()-1);
        addToChat(response,ChatGPTModel.SENT_BY_BOT);

    }

    void callAPI(String question){
        //okhttp

        messageList.add(new ChatGPTModel("...",ChatGPTModel.SENT_BY_BOT));

        JSONObject jsonBoby = new JSONObject();
        try {
            jsonBoby.put("model","text-davinci-003");
            jsonBoby.put("prompt",question);
            jsonBoby.put("max_tokens",4000);
            jsonBoby.put("temperature",0);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        RequestBody body = RequestBody.create(jsonBoby.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization","Bearer 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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Lỗi chatGPT "+ e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }else {
                    addResponse("Lỗi chatGPT "+ response.body().toString());
                }
            }
        });

    }
}
