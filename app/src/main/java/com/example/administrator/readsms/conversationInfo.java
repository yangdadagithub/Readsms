package com.example.administrator.readsms;

/**
 * 会话类，用会话对象来保存每个会话
 * Created by Administrator on 2016/7/7.
 */
public class conversationInfo {
    private String groupid;
    private String msgcount;
    private String lastmsg;
    private String contact;
    private String lastdate;
    public String getGroupId(){
        return groupid;
    }

    public void setGroupId(String groupid) {
        this.groupid = groupid;
    }
    public String getMsgCount() {
        return msgcount;
    }



    public void setMsgCount(String msgcount) {
        this.msgcount = msgcount;
    }



    public String getLastMsg() {
        return lastmsg;
    }



    public void setLastMsg(String lastmsg) {
        this.lastmsg = lastmsg;
    }



    public String getContact() {
        return contact;
    }



    public void setContact(String contact) {
        this.contact = contact;
    }



    public String getLastDate() {
        return lastdate;
    }

    public void setLastDate(String lastdate) {
        this.lastdate = lastdate;
    }
}
