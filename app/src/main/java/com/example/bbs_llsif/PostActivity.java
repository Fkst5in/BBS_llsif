package com.example.bbs_llsif;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

public class PostActivity extends AppCompatActivity {
    EditText et_title,et_content;
    Button btn_post;
    static Context postcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        et_title = (EditText) findViewById(R.id.et_title);
        et_content = (EditText) findViewById(R.id.et_content);
        btn_post = (Button) findViewById(R.id.btn_post);

        postcontext=this;

        Intent intent = getIntent();
        final String user_id = intent.getStringExtra("user_id");
        final String session = intent.getStringExtra("session");

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String content = et_content.getText().toString();

                PostTask postTask = new PostTask();
                postTask.execute(title, content, user_id, session);

            }
        });
    }
}

class PostTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... strings) {
        Gson gson = new Gson();
        PostJson postJson = new PostJson();
        postJson.setTitle(strings[0]);
        postJson.setContent(strings[1]);

        String sendJson = gson.toJson(postJson);
        System.out.println("result::"+sendJson.toString());
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("User-ID", strings[2]);
        header.put("Session", strings[3]);

        return Poster.post("https://bbs.llsif.cn/main.php/post", sendJson, header);
    }

    //解释返回的信息
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Gson backjson = new Gson();
        PostBackJson postBackJson = backjson.fromJson(s, PostBackJson.class);
        //获得context
        Context contenx=PostActivity.postcontext;

        //通过Success 判断
        if (postBackJson.getSuccess() == true) {
            Toast.makeText(contenx,"发帖成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(contenx,postBackJson.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}


