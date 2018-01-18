package com.example.bbs_llsif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 傻李隽 on 2017/11/23.
 */

public class ReplyAdapter extends BaseAdapter {
    private static final int TYPE_COUNT = 2;//item类型的总数
    private static final int TYPE_Main = 0;//楼主
    private static final int TYPE_Reply = 1;//回复


    private LayoutInflater mInflater;
    private java.util.List<Sub_Reply_1> List;
    private Context context;

    private int currentType;


    public ReplyAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }
    public void setData(java.util.List<Sub_Reply_1> dataS) {
        this.List = dataS;
    }
    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int position) {
        return List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        if (List.get(position).getRid()==0) {
            return TYPE_Main;// 楼主
        } else if (List.get(position).getRid()>0) {
            return TYPE_Reply;// 楼下
        } else {
            return 100;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View setView = null;
        View MainView = null;
        View ReplyView = null;

        Sub_Reply_1 data = List.get(position);
        currentType = getItemViewType(position);
        if (currentType==TYPE_Main) {
            MainHolder mainHolder = null;
            if (convertView == null) {
                mainHolder = new MainHolder();
                MainView = LayoutInflater.from(context).inflate(R.layout.detail_item, null);
                mainHolder.tv_title = (TextView) MainView.findViewById(R.id.it_title);
                mainHolder.tv_username = (TextView) MainView.findViewById(R.id.it_username);
                mainHolder.tv_timePost = (TextView) MainView.findViewById(R.id.it_timePost);
                mainHolder.tv_content = (TextView) MainView.findViewById(R.id.it_content);
                mainHolder.tv_timeReply = (TextView) MainView.findViewById(R.id.it_timeReply);
                MainView.setTag(mainHolder);
                convertView = MainView;
            }
            else if(currentType==TYPE_Main){
                mainHolder = (MainHolder) convertView.getTag();
            }
            mainHolder.tv_title.setText(data.getTitle());
            mainHolder.tv_username.setText(data.getUser_name());
            mainHolder.tv_timePost.setText(data.getInsert_date());
            mainHolder.tv_content.setText(data.getContent());
            mainHolder.tv_timeReply.setText(data.getLast_reply());

        } else{
            ReplyHolder replyHolder = null;
            if (convertView == null) {
                replyHolder = new ReplyHolder();
                ReplyView = LayoutInflater.from(context).inflate(R.layout.reply_item, null);
                replyHolder.tv_username = (TextView) ReplyView.findViewById(R.id.rit_name);
                replyHolder.tv_IdTime = (TextView) ReplyView.findViewById(R.id.rit_IdTime);
                replyHolder.tv_content = (TextView) ReplyView.findViewById(R.id.rit_content);
                replyHolder.btn_reply = (Button) ReplyView.findViewById(R.id.rit_reply);
                ReplyView.setTag(replyHolder);
                convertView = ReplyView;
            } else {
                replyHolder = (ReplyHolder) convertView.getTag();
            }
            replyHolder.tv_username.setText(data.getUser_name());
            replyHolder.tv_IdTime.setText((data.getRid()+1)+"楼 | "+data.getInsert_date());
            replyHolder.tv_content.setText(data.getContent());

            replyHolder.btn_reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "暂时不支持此功能", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }

}

class MainHolder {

    TextView tv_title;
    TextView tv_username;
    TextView tv_timePost;
    TextView tv_timeReply;
    TextView tv_content;

}

class ReplyHolder {
    TextView tv_username;
    TextView tv_IdTime;
    TextView tv_content;
    Button btn_reply;
}

