package com.example.bbs_llsif;

/**
 * Created by 傻李隽 on 2017/11/21.
 */

public class Sub_List_1 {
   private int pid;//帖子id
   private int user_id;//
   private String user_name;//
   private String title;//帖子标题
   private String insert_date;//发帖时间
   private String last_reply;//最近回帖时间
   private String summary;//帖子概要

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getLast_reply() {
        return last_reply;
    }

    public void setLast_reply(String last_reply) {
        this.last_reply = last_reply;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
