package com.example.administrator.readsms;

/**
 * 短信结构类，
 * Created by Administrator on 2016/7/6.
 */
public class smsInfo {
    private String ID;
    private String contact;
    private String msgContent;
    private String date;
    private String type;
    public String getID(){
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getContact() {
        return contact;
    }



    public void setContact(String contact) {
        this.contact = contact;
    }



    public String getDate() {
        return date;
    }



    public void setDate(String date) {
        this.date = date;
    }



    public String getMSGContent() {
        return msgContent;
    }



    public void setMSGContent(String msgContent) {
        this.msgContent = msgContent;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
