package com.example.bbs_llsif;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.URL;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
    int pid;
    int lz_id;
    String user_id;
    String session;
    String body;

    Button btn_replyMain;
    Button btn_replyUpdate;
    EditText et_replyMain;
    ListView lv_reply;

    HashMap<String, String> header;

    Handler replyHandler = new Handler();

    static Context ReplyContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", -1);
        lz_id = intent.getIntExtra("Uid", -1);
        user_id = intent.getStringExtra("user_id");
        session = intent.getStringExtra("session");
        header = new HashMap<String, String>();
        header.put("User-ID", user_id);
        header.put("Session", session);

        ReplyContext = this;


        init();

        doIt();

        DetailJson detailJson = new DetailJson();
        detailJson.setPage(0);
        detailJson.setPid(pid);
        body = new Gson().toJson(detailJson, DetailJson.class);

        ReplyAdapter adapter = new ReplyAdapter(getApplicationContext());
        new ReplyThread(body, header,replyHandler, lv_reply,adapter).start();
    }


    public void init() {
        btn_replyMain = (Button) findViewById(R.id.btn_replyMain);
        btn_replyUpdate = (Button) findViewById(R.id.btn_replyUpdate);
        et_replyMain = (EditText) findViewById(R.id.et_replyMain);
        lv_reply = (ListView) findViewById(R.id.lv_reply);

    }

    private void doIt() {


        btn_replyMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_replyMain.length() != 0) {

                    ReplyJosn replyJosn = new ReplyJosn();
                    replyJosn.setPid(pid);
                    replyJosn.setTo_rid(0);
                    replyJosn.setContent(et_replyMain.getText().toString());
                    System.out.println("content:"+et_replyMain.getText().toString());
                    System.out.println("pid: " + pid);
                    String body = new Gson().toJson(replyJosn, ReplyJosn.class);

                    ReplyTask replyTask = new ReplyTask();
                    replyTask.execute(body, user_id, session);

                } else {
                    Toast.makeText(getApplicationContext(), "元芳？你肿么了？",Toast.LENGTH_SHORT).show();
                }

                et_replyMain.setText(null);
            }
        });

        btn_replyUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReplyAdapter adapter = new ReplyAdapter(getApplicationContext());
                new ReplyThread(body, header,replyHandler, lv_reply,adapter).start();

                Toast.makeText(getApplicationContext(),"刷新成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

}

//异步任务 回复
class ReplyTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... strings) {
        System.out.println("body: " + strings[0]);

        HashMap<String, String> header = new HashMap<String, String>();
        header.put("User-ID", strings[1]);
        header.put("Session", strings[2]);

        return Poster.post("https://bbs.llsif.cn/main.php/reply", strings[0], header);
    }

    //解释返回的信息
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Context contenx = DetailActivity.ReplyContext;

        if (s != null) {

            ReplyBackJson replyBackJson = new Gson().fromJson(s, ReplyBackJson.class);

            //通过Success 判断
            if (replyBackJson.isSuccess() == true) {
                int lcs = replyBackJson.getRid() + 1;
                if (replyBackJson.getRid() < 10) {
                    Toast.makeText(contenx, "发帖成功 " + lcs + "楼 " + "!!!(•'╻'• )꒳ᵒ꒳ᵎᵎᵎ", Toast.LENGTH_SHORT).show();
                } else if (replyBackJson.getRid() < 30) {
                    Toast.makeText(contenx, "发帖成功 " + lcs + "楼 " + "ԅ(✧_✧ԅ)", Toast.LENGTH_SHORT).show();
                } else if (replyBackJson.getRid() < 60) {
                    Toast.makeText(contenx, "发帖成功 " + lcs + "楼 " + "⁽⁽ଘ( ˊᵕˋ )ଓ⁾⁾", Toast.LENGTH_SHORT).show();
                } else if (replyBackJson.getRid() < 100) {
                    Toast.makeText(contenx, "发帖成功 " + lcs + "楼 " + "(*￣3￣)╭", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(contenx, "发帖成功 " + lcs + "楼 " + "( ๑ŏ ﹏ ŏ๑ )", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(contenx, replyBackJson.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(contenx, "ta，可能迷失在了这片虚伪星空", Toast.LENGTH_SHORT).show();
        }
    }
}