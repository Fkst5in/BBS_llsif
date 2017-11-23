package com.example.bbs_llsif;

import java.util.List;

/**
 * Created by 傻李隽 on 2017/11/23.
 */

public class Sub_Reply_0 {
    boolean success;
    Sub_Reply_1 post;
    List<Sub_Reply_1> replies;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Sub_Reply_1 getPost() {
        return post;
    }

    public void setPost(Sub_Reply_1 post) {
        this.post = post;
    }

    public List<Sub_Reply_1> getReplies() {
        return replies;
    }

    public void setReplies(List<Sub_Reply_1> replies) {
        this.replies = replies;
    }
}
