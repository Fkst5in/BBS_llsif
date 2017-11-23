package com.example.bbs_llsif;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 傻李隽 on 2017/11/22.
 */

public class MyAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private java.util.List<Sub_List_1> List;
    private Context context;
    private HashMap<String, String> header;

    public MyAdapter(Context context,HashMap<String, String> header) {
        this.header = header;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }
    public void setData(List<Sub_List_1> data) {
        this.List = data;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.main_item, null);
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

        final Sub_List_1 sub_list_1 = List.get(position);
        holder.it_title.setText(String.valueOf(sub_list_1.getTitle())+" ");
        holder.it_content.setText(String.valueOf(sub_list_1.getSummary()));
        holder.it_username.setText("by: "+String.valueOf(sub_list_1.getUser_name()));
        holder.it_timePost.setText(String.valueOf(sub_list_1.getInsert_date()));
        holder.it_timeReply.setText("最新： "+String.valueOf(sub_list_1.getLast_reply()));

        holder.it_BtnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, DetailActivity.class);
                //System.out.println("user_id:"+sub_list_1.getUser_id()+"\npid:"+sub_list_1.getPid());
                detail.putExtra("pid", sub_list_1.getPid());
                detail.putExtra("Uid", sub_list_1.getUser_id()); //楼主ID
                detail.putExtra("user_id", header.get("User_ID")); //用户ID
                detail.putExtra("session", header.get("Session"));
                context.startActivity(detail);
            }
        });
        return convertView;
    }
}

final class ViewHolder{
    public TextView it_title;
    public TextView it_content;
    public TextView it_username;
    public TextView it_timePost;
    public TextView it_timeReply;
    public Button it_BtnDetail;

}