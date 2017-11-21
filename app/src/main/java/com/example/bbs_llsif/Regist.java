package com.example.bbs_llsif;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Regist extends AppCompatActivity {

    EditText et_username,et_password;
    Button btn_regist,btn_longin;
    public static TextView tv_regist;
    static  Context context;
    static Regist sentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        tv_regist = (TextView) findViewById(R.id.tv_regist);
        init();
        context = this;
        sentence = this;
    }

    public void init(){
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_regist = (Button) findViewById(R.id.btn_regist);
        btn_longin = (Button) findViewById(R.id.btn_login);


        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=String .valueOf(et_username.getText());
                String password=String .valueOf(et_password.getText());

                //注册
                RegistTask registTask = new RegistTask();
                registTask.execute(name, password);

            }
        });

        btn_longin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=String .valueOf(et_username.getText());
                String password=String .valueOf(et_password.getText());
                Toast.makeText(Regist.this,"登录中···",Toast.LENGTH_SHORT).show();

                //登录
                LoginTask loginTask = new LoginTask();
                loginTask.execute(name, password);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
//异步任务 注册
class RegistTask extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... strings) {

        //JSON转换
        Gson registgson = new Gson();
        RegistJson registJson = new RegistJson();
        registJson.setName(strings[0]);
        registJson.setPassword(strings[1]);
        String sendjson = registgson.toJson(registJson);
        String[] header = {};
        return Poster.post("https://bbs.llsif.cn/main.php/regist", sendjson, header);
    }

    //解释返回的信息
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Gson backjson = new Gson();
        RegistBackJson registBackJson = backjson.fromJson(s, RegistBackJson.class);
        //获得context
        Context contenx=Regist.context;

        //通过Success 判断
        if (registBackJson.getSuccess() == true) {
            Toast.makeText(contenx,"注册成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(contenx,registBackJson.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
//异步任务 登录 （大体与注册一直）
class LoginTask extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... strings) {
        Gson registgson = new Gson();
        RegistJson registJson = new RegistJson();
        registJson.setName(strings[0]);
        registJson.setPassword(strings[1]);
        String sendjson = registgson.toJson(registJson);

        String[] header = {};
        return Poster.post("https://bbs.llsif.cn/main.php/login", sendjson, header);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Gson backjson = new Gson();
        RegistBackJson registBackJson = backjson.fromJson(s, RegistBackJson.class);
        Context contenx=Regist.context;

         if (registBackJson.getSuccess() == true) {
             Toast.makeText(contenx, "登录成功", Toast.LENGTH_SHORT).show();

             //跳转到MainActivi，并发送用户数据（主要是 User_id 和 Session ）
             Intent login = new Intent(contenx, MainActivity.class);
             login.putExtra("user_message",s);
             contenx.startActivity(login);
         } else {
             Toast.makeText(contenx,registBackJson.getMessage().toString(),Toast.LENGTH_SHORT).show();
         }

    }
}


