package com.example.administrator.readsms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
private ListView lv;
    private EditText et;
    private Button btn;
    private smsListAdapter adapter;
    private List<smsInfo> list;
    private String lookupid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailtalk);
        Bundle bundle=getIntent().getExtras();
         lookupid=bundle.getString("lookupid");
         getMessageInfo messageInfo=new getMessageInfo(this);
        //messageInfo.readSMS();
        list=messageInfo.readSMS2List(lookupid);
        adapter=new smsListAdapter(list,this);
        lv=(ListView)findViewById(R.id.list);
        lv.setAdapter(adapter);
        lv.setSelection(adapter.getCount() - 1);
        et=(EditText)findViewById(R.id.et_intext);
        btn=(Button)findViewById(R.id.bt_talk_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsInfo sif=new smsInfo();
                String telNum= list.get(0).getContact();
                String content=et.getText().toString();
                SmsManager smsManager=SmsManager.getDefault();
                ArrayList<String> texts=smsManager.divideMessage(content);
                for(String text:texts){
                    smsManager.sendTextMessage(
                            telNum,  //目的号码
                            null,  //sc address
                            text, //短信内容
                            null, //发送状态
                            null); //接收状态
                    sif.setContact(telNum);
                    sif.setMSGContent(text);
                    sif.setType("send");
                    list.add(sif);
                    adapter.notifyDataSetChanged();
                }
                Toast.makeText(getApplicationContext(),"发送完成",Toast.LENGTH_SHORT).show();
                et.setText("");
            }
        });
    }
}
