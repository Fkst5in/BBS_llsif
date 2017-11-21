package com.example.bbs_llsif;

/**
 * Created by 傻李隽 on 2017/11/20.
 */

public class PostBackJson {
    Boolean success;
    String pid;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
