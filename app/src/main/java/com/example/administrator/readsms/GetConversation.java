package com.example.administrator.readsms;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法类用来获取会话
 * 有两个方法
 * readConversations（）为测试方法可删除
 * readConversations2List（）返回list
 * Created by Administrator on 2016/7/7.
 */
public class GetConversation {
    private static final String CONVERSATIONA="content://sms/conversations/";
    private static final String CONTACTS_LOOKUP="content://com.android.contacts/phone_lookup/";
    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Context context;
    List<conversationInfo> list;
    public GetConversation(Context context){
        list=new ArrayList<conversationInfo>();
        this.context=context;
    }
    public void readConversations(){
        ContentResolver resolver=context.getContentResolver();
        Uri uri=Uri.parse(CONVERSATIONA);
        String[] projection=new String[]{"groups.group_thread_id AS group_id",
                "groups.msg_count AS msg_count",
                "groups.group_date AS last_date",
                "sms.body AS last_msg",
                "sms.address AS contact"};
        Cursor thinc=resolver.query(uri,
                projection,
                null,
                null,
                "groups.group_date DESC");
        Cursor richc=new CursorWrapper(thinc){
            @Override
            public String getString(int columnIndex) {
                if(super.getColumnIndex("contact")==columnIndex){
                    String contact=super.getString(columnIndex);
                    Uri uri=Uri.parse(CONTACTS_LOOKUP + contact);
                    Cursor cursor=context.getContentResolver().query(uri,null,null,null,null);
                    if(cursor.moveToFirst()){
                        String contactName=cursor.getString(cursor.getColumnIndex("display_name"));
                        return contactName;
                    }
                    return  contact;
                }
                return super.getString(columnIndex);
            }
        };
        while (richc.moveToNext()){
            String groupId = "groupId: " + richc.getInt(richc.getColumnIndex("group_id"));
            String msgCount = "msgCount: " + richc.getLong(richc.getColumnIndex("msg_count"));
            String lastMsg = "lastMsg: " + richc.getString(richc.getColumnIndex("last_msg"));
            String contact = "contact: " + richc.getString(richc.getColumnIndex("contact"));
            String lastDate = "lastDate: " + dateFormat.format(richc.getLong(richc.getColumnIndex("last_date")));
            Log.i("conversations:",groupId+" "+msgCount+" "+lastMsg+" "+contact+" "+lastDate);
           // printLog(groupId, contact, msgCount, lastMsg, lastDate, "---------------END---------------");
        }
        richc.close();

    }
    public List<conversationInfo> readConversations2List(){
        ContentResolver resolver=context.getContentResolver();
        Uri uri=Uri.parse(CONVERSATIONA);
        String[] projection=new String[]{"groups.group_thread_id AS group_id",
                "groups.msg_count AS msg_count",
                "groups.group_date AS last_date",
                "sms.body AS last_msg",
                "sms.address AS contact"};
        Cursor thinc=resolver.query(uri,projection,null,null,"groups.group_date DESC");
        Cursor richc=new CursorWrapper(thinc){
            @Override
            public String getString(int columnIndex) {
                if(super.getColumnIndex("contact")==columnIndex){
                    String contact=super.getString(columnIndex);
                    Uri uri=Uri.parse(CONTACTS_LOOKUP + contact);
                    Cursor cursor=context.getContentResolver().query(uri,null,null,null,null);
                    if(cursor.moveToFirst()){
                        String contactName=cursor.getString(cursor.getColumnIndex("display_name"));
                        return contactName;
                    }
                    return  contact;
                }
                return super.getString(columnIndex);
            }
        };
        while (richc.moveToNext()){
            conversationInfo cif=new conversationInfo();
            String groupId =richc.getInt(richc.getColumnIndex("group_id"))+"";
            String msgCount =richc.getLong(richc.getColumnIndex("msg_count"))+"";
            String lastMsg =richc.getString(richc.getColumnIndex("last_msg"));
            String contact =richc.getString(richc.getColumnIndex("contact"));
            String lastDate =dateFormat.format(richc.getLong(richc.getColumnIndex("last_date")));
            cif.setGroupId(groupId);
            cif.setContact(contact);
            cif.setLastDate(lastDate);
            cif.setMsgCount(msgCount);
            cif.setLastMsg(lastMsg);
            list.add(cif);
        }
        richc.close();
return list;
    }
}