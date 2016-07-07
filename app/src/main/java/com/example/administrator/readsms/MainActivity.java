package com.example.administrator.readsms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //会话
    private static final String CONVERSATIONS="content://sms/conversations/";
    //查询联系人
    private static final String CONTACTS_LOOKUP="content://com.android.contacts/phone_lookup/";
//    所有短信
    public static final String SMS_URI_ALL="content://sms/";
    //收件箱短信
    public static final String SMS_URI_INBOX="content://sms/inbox";
    //已发送短信
    public static final String SMS_URI_SEND="content://sms/sent";
//草稿箱
    public static final String SMS_URI_DRAFT="content://sms/draft";

    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ListView listView;
    private conversationListAdapter cadapter;
    private List<conversationInfo> clist;
    private GetConversation conversation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Uri uri=Uri.parse(SMS_URI_INBOX);
//        getMessageInfo gmi=new getMessageInfo(this,uri);
//        smslist=gmi.getSmsInfo();
//        adapter=new smsListAdapter(smslist,this);
//        listView=(ListView)findViewById(R.id.sms_listview);
//        listView.setAdapter(adapter);

        conversation=new GetConversation(this);
      //  conversation.readConversations();
        clist=conversation.readConversations2List();
        cadapter=new conversationListAdapter(clist,this);
        listView=(ListView)findViewById(R.id.sms_listview);
        listView.setAdapter(cadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,ChatActivity.class);
                String lookupid =clist.get(position).getGroupId();
                Bundle bundle=new Bundle();
                bundle.putString("lookupid",lookupid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
