package com.example.httptest02;


import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RegistThread extends Thread {

    private String url;
    private  String name;
    private  String password;


    public RegistThread(String url, String name, String password){
        this.url=url;
        this.name=name;
        this.password=password;
    }


    private void doPost(){
        try{
            URL httpurl=new URL(url);

            HttpURLConnection con = (HttpURLConnection)httpurl.openConnection();
            con.setRequestMethod("POST");
            con.setReadTimeout(6000);

            OutputStream out=con.getOutputStream();

            Gson gson = new Gson();
            RegistSendJson registSendJson = new RegistSendJson();
            registSendJson.setName(name);
            registSendJson.setPassword(password);
            String sendjson = gson.toJson(registSendJson);

            out.write(sendjson.getBytes());

            BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response =new StringBuffer();
            String line;

            while((line=reader.readLine())!=null){
                response.append(line);
            }

            System.out.println("result::"+response.toString());
            /*
            Message msg=new Message();
            msg.obj = response;
            MainActivity.handler.sendMessage(msg);
            */

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        super.run();
        doPost();
    }
}
