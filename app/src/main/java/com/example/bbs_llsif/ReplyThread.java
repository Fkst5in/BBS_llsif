package com.example.bbs_llsif;

import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 傻李隽 on 2017/11/22.
 */

public class ReplyThread extends Thread {


    Map<String,String> header;
   private String body;
   private String url="https://bbs.llsif.cn/main.php/post_detail";

   private List<Sub_Reply_1> dataS=new ArrayList<>();

   android.os.Handler replyHandler;

   private ListView listView;
   private ReplyAdapter adapter;

    public ReplyThread( String body, HashMap<String, String> header, android.os.Handler replyHandler,ListView listView,ReplyAdapter adapter) {

        this.body = body;
        this.header = header;
        this.replyHandler = replyHandler;
        this.adapter = adapter;
        this.listView = listView;
    }

    private void doPost() {

         String result = Poster.post(url, body, header);
         System.out.println("result: "+result);
        Sub_Reply_0 sub_reply_0 = new Gson().fromJson(result, Sub_Reply_0.class);
        //Sub_Reply_1 sub_reply_1 =sub_reply_0.getPost();
        //sub_reply_1.setRid(0);
        dataS.add(sub_reply_0.getPost());
        dataS.addAll(sub_reply_0.getReplies());
        System.out.println("dataS: "+dataS.size());
        replyHandler.post(new Runnable() {
            @Override
            public void run() {
                adapter.setData(dataS);
                System.out.println("OK");
                listView.setAdapter(adapter);
                System.out.println("wancheng");
            }
        });

    }

    @Override
    public void run() {
        super.run();
        doPost();
    }
}
