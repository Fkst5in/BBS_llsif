package com.example.bbs_llsif;

import android.annotation.SuppressLint;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        tv_regist = (TextView) findViewById(R.id.tv_regist);
        init();
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
                RegistTask registTask = new RegistTask();
                registTask.execute(name, password);
            }
        });
    }
}

class RegistTask extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... strings) {
        Gson registgson = new Gson();
        RegistJson registJson = new RegistJson();
        registJson.setName(strings[0]);
        registJson.setPassword(strings[1]);
        String sendjson = registgson.toJson(registJson);
        return this.doPost("https://bbs.llsif.cn/main.php/regist", sendjson);
    }

    public String doPost(String url, String Json){
        String result = "";
        try {
            URL realurl = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) realurl.openConnection();
            con.setReadTimeout(6000);
            con.setRequestMethod("POST");
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

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Regist.tv_regist.setText(String.valueOf(s));
    }
}

