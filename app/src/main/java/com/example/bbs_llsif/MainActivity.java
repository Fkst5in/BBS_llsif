package com.example.bbs_llsif;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    TextView text;
    String user_message_json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获得用户数据（主要是 User_id 和 Session）
        Intent intent = getIntent();
        user_message_json = intent.getStringExtra("user_message");

        //结束登录Activi
        Regist.sentence.finish();

        init();

        //按钮 转向PostActivi
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postIntent = new Intent(getApplicationContext(), PostActivity.class);
                postIntent.putExtra("user_message", user_message_json);
                startActivity(postIntent);
            }
        });

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
