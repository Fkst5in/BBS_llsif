package com.example.bbs_llsif;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    String user_id;
    String session;

    ListView lv_sub;
    ArrayList<HashMap<String, Object>> mData = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        mData = getData();
        MyAdapter mAdapter = new MyAdapter(this);
        lv_sub.setAdapter(mAdapter);


        //按钮 转向PostActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
    //方法 getData -> 便利的获取数据
    private ArrayList<HashMap<String,Object>> getData(){
        ArrayList<HashMap<String,Object>> listitem = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        for(int i=0;i<20;i++) {
            map.put("ItemTitle", "第"+i+"行");
            map.put("ItemContent", "第"+i+"行");
            map.put("ItemUsername", "第"+i+"行");
            map.put("ItemTimePost", "第"+i+"行");
            map.put("ItemTimeReply", "第"+i+"行");
            listitem.add(map);
        }
        return listitem;
    }

    private class MyAdapter extends BaseAdapter{

        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return getData().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item, null);
                holder = new ViewHolder();
                holder.it_title = (TextView) convertView.findViewById(R.id.it_title);
                holder.it_content = (TextView) convertView.findViewById(R.id.it_content);
                holder.it_username = (TextView) convertView.findViewById(R.id.it_username);
                holder.it_timePost = (TextView) convertView.findViewById(R.id.it_timePost);
                holder.it_timeReply = (TextView) convertView.findViewById(R.id.it_timeReply);
                holder.it_BtnDetail = (Button) convertView.findViewById(R.id.it_detail);
                convertView.setTag(holder);
            } else{
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }

            holder.it_title.setText(String.valueOf(mData.get(position).get("ItemTitle")));
            holder.it_title.setText(String.valueOf(mData.get(position).get("ItemTitle")));
            holder.it_content.setText(String.valueOf(mData.get(position).get("ItemContent")));
            holder.it_username.setText(String.valueOf(mData.get(position).get("ItemUsername")));
            holder.it_timePost.setText(String.valueOf(mData.get(position).get("ItemTimePost")));
            holder.it_timeReply.setText(String.valueOf(mData.get(position).get("ItemTimeReply")));

            holder.it_BtnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detail = new Intent(getApplicationContext(), DetailActivity.class);
                    detail.putExtra("message", "lalalalalalala");
                    startActivity(detail);
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder{
        public TextView it_title;
        public TextView it_content;
        public TextView it_username;
        public TextView it_timePost;
        public TextView it_timeReply;
        public Button it_BtnDetail;


    }

    private void init() {
        lv_sub = (ListView) findViewById(R.id.lv_sub);
    }

}
