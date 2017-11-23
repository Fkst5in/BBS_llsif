package com.example.bbs_llsif;

/**
 * Created by 傻李隽 on 2017/11/22.
 */

public class ReplyBackJson {
    boolean success;
    int rid;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int pid) {
        this.rid = rid;
    }
}
