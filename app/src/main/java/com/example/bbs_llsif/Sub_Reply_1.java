package com.example.bbs_llsif;

/**
 * Created by 傻李隽 on 2017/11/23.
 */

public class Sub_Reply_1 {
    int rid=0;
    int to_rid=0;
    int pid=0;
    int user_id=0;
    String user_name=null;
    String title=null;
    String content=null;
    String insert_date=null;
    String last_reply=null;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getTo_rid() {
        return to_rid;
    }

    public void setTo_rid(int to_rid) {
        this.to_rid = to_rid;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
