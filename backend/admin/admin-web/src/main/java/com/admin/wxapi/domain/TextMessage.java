package com.admin.wxapi.domain;
/**
作者：fengchase
时间：2018年7月12日
文件：TextMessage.java
项目：admin-web
*/
public class TextMessage extends BaseMessage{

    private String Content;
    private String MsgId;

    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }
    public String getMsgId() {
        return MsgId;
    }
    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}

