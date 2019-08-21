package com.art.beans.famous;

import java.io.Serializable;

/**
 * 评论信息bean
 * @author wslixiaoliang
 */
public class CommentInformation implements Serializable
{

    private static final long serialVersionUID = -6367242284441943508L;
    private String commentContent;//评论内容
    private String commentTime;//评论时间
    private String consumerName;//评论人名字
    private String consumerPortraitName;//评论人头像

    //无参构造方法
    public CommentInformation() {
    }

    //有参构造方法
    public CommentInformation(String commentContent, String commentTime, String consumerName, String consumerPortraitName) {
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.consumerName = consumerName;
        this.consumerPortraitName = consumerPortraitName;
    }

    //getters and setters
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerPortraitName() {
        return consumerPortraitName;
    }

    public void setConsumerPortraitName(String consumerPortraitName) {
        this.consumerPortraitName = consumerPortraitName;
    }
}
