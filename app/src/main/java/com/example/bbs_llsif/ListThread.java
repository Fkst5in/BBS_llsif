package com.example.bbs_llsif;

import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
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
    List<Sub_List_1> data;


    public ListThread(String body, HashMap<String, String> header, android.os.Handler listHandler, MyAdapter adapter, ListView listView) {

        this.body = body;
        this.header = header;
        this.listHandler = listHandler;
        this.adapter = adapter;
        this.listView = listView;
    }

    private void dopsot() {
         String result = Poster.post(url, body, header);

         Sub_List_0 sub_list_0 = new Gson().fromJson(result, Sub_List_0.class);
         data= sub_list_0.getPosts();
         listHandler.post(new Runnable() {
             @Override
             public void run() {
                 adapter.setData(data);
                 listView.setAdapter(adapter);
             }
         });
    }

    @Override
    public void run() {
        super.run();
        dopsot();
    }
}
