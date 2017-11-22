package com.example.bbs_llsif;

import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by 傻李隽 on 2017/11/22.
 */

public class ListThread extends Thread {


    Map<String,String> header;
    String body;
    String url="https://bbs.llsif.cn/main.php/post_list";
    android.os.Handler listHandler;
    ListView listView;
    MyAdapter adapter;



    public ListThread(String body, HashMap<String, String> header, android.os.Handler listHandler, MyAdapter adapter, ListView listView) {

        this.body = body;
        this.header = header;
        this.listHandler = listHandler;
        this.adapter = adapter;
        this.listView = listView;
    }

    private String dopsot() {
        String result = "";
        try {
            URL url_ = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) url_.openConnection();
            con.setReadTimeout(6000);
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> entry:header.entrySet()){
               con.setRequestProperty(entry.getKey(), entry.getValue());
            }
            OutputStream out = con.getOutputStream();
            out.write(body.getBytes());
            out.flush();
            out.close();
            InputStream in = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            while ((line = reader.readLine()) != null) {
                result = line;
            }

            System.out.println("result::"+result.toString());

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void run() {
        super.run();
        String message=dopsot();
        Sub_List_0 sub_list_0 = new Gson().fromJson(message, Sub_List_0.class);
        final List<Sub_List_1> data = sub_list_0.getPosts();

        listHandler.post(new Runnable() {
            @Override
            public void run() {
                adapter.setData(data);
                listView.setAdapter(adapter);
            }
        });
    }
}
