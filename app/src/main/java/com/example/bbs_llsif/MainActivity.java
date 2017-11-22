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

    ListView lv_sub;
    Toolbar toolbar;
    FloatingActionButton fab;
    Button btn_MainUpdate;

    private Handler listHandler = new Handler();


    ArrayList<HashMap<String, Object>> mData = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //获得用户数据（主要是 User_id 和 Session）
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        session = intent.getStringExtra("session");
        Boolean skip = intent.getBooleanExtra("skip", false);

        //结束登录Activity
        if(!skip) {
            Regist.sentence.finish();
        }

        init();

        //获得帖子上线的json文件。。。。。汗
        Gson gson = new Gson();
        Limit limit = new Limit();
        limit.setLimit(30);
        final String body = gson.toJson(limit, Limit.class);
        //获得http header文件//以HashMap<String, String> header形式
         final HashMap<String, String> header = new HashMap<String, String>();
        header.put("User-ID", user_id);
        header.put("Session", session);
        //获得mAdapter对象
        MyAdapter mAdapter = new MyAdapter(this);
        //建立http链接，并在其中的handler.post 中完成绑定和更新 ListView lv_Sub
        new ListThread(body,header,listHandler,mAdapter,lv_sub).start();
        System.out.println("ok");

        btn_MainUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter mAdapter = new MyAdapter(getApplicationContext());
                //建立http链接，并在其中的handler.post 中完成绑定和更新 ListView lv_Sub
                new ListThread(body,header,listHandler,mAdapter,lv_sub).start();
                System.out.println("ok");
            }
        });

    }

    public void init() {

        lv_sub = (ListView) findViewById(R.id.lv_sub);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        btn_MainUpdate = (Button) findViewById(R.id.btn_MainUpdate);





        setSupportActionBar(toolbar);

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

