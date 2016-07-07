package com.example.administrator.readsms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 会话列表适配器，会话列表的适配器
 * Created by Administrator on 2016/7/7.
 */
public class conversationListAdapter extends BaseAdapter{
    private Context context;
    private List<conversationInfo> list;
    public  conversationListAdapter(List<conversationInfo> list,Context context){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.smslistitem,null);
            viewHolder.contact=(TextView)convertView.findViewById(R.id.sms_contact);
            viewHolder.lastmsg=(TextView)convertView.findViewById(R.id.sms_lastmsg);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.contact.setText(list.get(position).getContact()+"("+list.get(position).getMsgCount()+")");
        viewHolder.lastmsg.setText(list.get(position).getLastMsg());
        return convertView;
    }
   public class ViewHolder{
        TextView contact;
       TextView lastmsg;
    }
}
