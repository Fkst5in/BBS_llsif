package com.example.bbs_llsif;

import android.content.Context;
import android.content.Intent;
import android.media.MediaCas;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PostActivity extends AppCompatActivity {
    EditText et_title,et_content;
    Button btn_post;
    static Context postcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        et_title = (EditText) findViewById(R.id.et_title);
        et_content = (EditText) findViewById(R.id.et_title);
        btn_post = (Button) findViewById(R.id.btn_post);

        postcontext=this;

        Intent intent = getIntent();
        String user_message_json = intent.getStringExtra("user_message");
        Gson gson = new Gson();
        final RegistBackJson user_message = gson.fromJson(user_message_json, RegistBackJson.class);

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String content = et_content.getText().toString();

                PostTask postTask = new PostTask();
                postTask.execute(title, content, user_message.getUser_id(), user_message.getSession());

            }
        });
    }
}

class PostTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... strings) {
        String url="https://bbs.llsif.cn/main.php/post";
        Gson gson = new Gson();
        PostJson postJson = new PostJson();
        postJson.setTitle(strings[0]);
        postJson.setContent(strings[1]);
        String sendJson = gson.toJson(postJson);
        return this.doPost(url,strings[2],strings[3],sendJson);
    }
    public String doPost(String url,String User_id,String Session, String Json){
        String result = "";
        try {
            URL realurl = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) realurl.openConnection();
            con.setReadTimeout(6000);
            con.setRequestMethod("POST");
            con.setRequestProperty("User_id",User_id);
            con.setRequestProperty("Session",Session);
            OutputStream out = con.getOutputStream();
            out.write(Json.getBytes());
            out.flush();
            out.close();
            InputStream in = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            while ((line =reader.readLine())!=null) {
                result = line;
            }
        } catch (MalformedURLException eio) {
            eio.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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
