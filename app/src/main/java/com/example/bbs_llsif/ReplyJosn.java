package com.example.bbs_llsif;

/**
 * Created by 傻李隽 on 2017/11/22.
 */

public class ReplyJosn {
    int pid;
    String content;
    int to_rid;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
