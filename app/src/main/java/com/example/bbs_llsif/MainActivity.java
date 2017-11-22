package com.example.bbs_llsif;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String user_id;
    String session;
    List<Sub_List_1> Sub_List;
    static Context listContext;
    private Handler listHandler = new Handler();

    ListView lv_sub;
    ArrayList<HashMap<String, Object>> mData = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_sub = (ListView) findViewById(R.id.lv_sub);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获得用户数据（主要是 User_id 和 Session）
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        session = intent.getStringExtra("session");
        Boolean skip = intent.getBooleanExtra("skip", false);

        //结束登录Activity
        if(!skip) {
            Regist.sentence.finish();
        }
        listContext = this;


        MyAdapter mAdapter = new MyAdapter(this);

        Gson gson = new Gson();
        Limit limit = new Limit();
        limit.setLimit(30);
        String body = gson.toJson(limit, Limit.class);

        HashMap<String, String> header = new HashMap<String, String>();
        header.put("User-ID", user_id);
        header.put("Session", session);

        new ListThread(body,header,listHandler,mAdapter,lv_sub).start();
        System.out.println("ok");

        //按钮 转向PostActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postIntent = new Intent(getApplicationContext(), PostActivity.class);
                postIntent.putExtra("user_id", user_id);
                postIntent.putExtra("session", session);
                startActivity(postIntent);
            }
        });


    }
}

