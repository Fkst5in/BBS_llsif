package com.example.bbs_llsif;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    TextView text;
    String user_message_json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获得用户数据（主要是 User_id 和 Session）
        Intent intent = getIntent();
        user_message_json = intent.getStringExtra("user_message");

        //结束登录Activi
        Regist.sentence.finish();

        init();

        doit();
    }

    public void init() {
        text = (TextView) findViewById(R.id.text);
    }

    public void doit() {
        Gson gson = new Gson();
        RegistBackJson user_message = gson.fromJson(user_message_json, RegistBackJson.class);
        text.setText("User_id:" + user_message.getUser_id() + "\n" + "Session:" + user_message.getSession());
    }


}
