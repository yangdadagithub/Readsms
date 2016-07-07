package com.example.administrator.readsms;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法类用来获取联系人之间对话
 * 有两个方法
 * readSMS为测试方法，可删除
 * readSMS2List读取会话后，返回list
 * Created by Administrator on 2016/7/6.
 */
public class getMessageInfo {
    List<smsInfo> infos;
    //全部短信
    private static final String SMS_ALL   = "content://sms/";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Uri uri;
    private Activity activity;
    private Context context;
    public getMessageInfo(Context context){
        infos=new ArrayList<smsInfo>();
        this.context=context;
    }
    public  void  readSMS(){
        ContentResolver resolver=context.getContentResolver();
        Uri uri=Uri.parse(SMS_ALL);
        String[] projection={"thread_id AS group_id",
                "address AS contact",
                "body AS msg_content",
                "date",
                "type"};
        Cursor cursor=resolver.query(uri,
                projection,
                "thread_id"+"=?",
                new String[]{"7"},
                "date DESC");
        while(cursor.moveToNext()){
            String groupId = "groupId: " + cursor.getInt(cursor.getColumnIndex("group_id"));
            String contact = "contact: " + cursor.getString(cursor.getColumnIndex("contact"));
            String msgContent = "msgContent: " + cursor.getString(cursor.getColumnIndex("msg_content"));
            String date = "date: " + dateFormat.format(cursor.getLong(cursor.getColumnIndex("date")));
            String type = "type: " + getTypeById(cursor.getInt(cursor.getColumnIndex("type")));
            printLog(groupId, contact, msgContent, date, type, "---------------END---------------");
        }
        cursor.close();
    }

    public List<smsInfo> readSMS2List(String lookupid){
        ContentResolver resolver=context.getContentResolver();
        Uri uri=Uri.parse(SMS_ALL);
        String[] projection={"thread_id AS group_id",
                "address AS contact",
                "body AS msg_content",
                "date",
                "type"};
        Cursor cursor=resolver.query(uri,
                projection,
                "thread_id"+"=?",
                new String[]{lookupid},
                "date");//DESC ACS
        while(cursor.moveToNext()){
            smsInfo sif=new smsInfo();
            String groupId = cursor.getInt(cursor.getColumnIndex("group_id"))+"";
            String contact = cursor.getString(cursor.getColumnIndex("contact"));
            String msgContent =cursor.getString(cursor.getColumnIndex("msg_content"));
            String date =dateFormat.format(cursor.getLong(cursor.getColumnIndex("date")));
            String type =getTypeById(cursor.getInt(cursor.getColumnIndex("type")));
            sif.setType(type);
            sif.setDate(date);
            sif.setContact(contact);
            sif.setID(groupId);
            sif.setMSGContent(msgContent);
            infos.add(sif);
        }
        cursor.close();
        return infos;
    }

    private String getTypeById(int typeId) {
        switch (typeId) {
            case 1: return "receive";
            case 2: return "send";
            case 3: return "draft";
            default: return "none";
        }
    }
    private void printLog(String...strings) {
        for (String s : strings) {
            Log.i("SMStest", s == null ? "NULL" : s);
        }
    }
}
