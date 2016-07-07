package com.example.administrator.readsms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 短信适配器
 * 显示联系人之间对话的listview的适配器
 * Created by Administrator on 2016/7/6.
 */
public class smsListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private View myview;
    private Context context;
    private List<smsInfo> list;
    public smsListAdapter(List<smsInfo> list,Context context){
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
        ViewHolder viewHolder=null;
  //      if(convertView==null){

            LayoutInflater inflater=LayoutInflater.from(context);
            if(list.get(position).getType().equals("receive")){
                viewHolder=new ViewHolder();
                convertView=inflater.inflate(R.layout.reciveitem,null);
                viewHolder.name=(TextView)convertView.findViewById(R.id.messagedetail_row_name);
                viewHolder.date=(TextView)convertView.findViewById(R.id.messagedetail_row_date);
                viewHolder.content=(TextView)convertView.findViewById(R.id.messagedetail_row_text);

            }else if(list.get(position).getType().equals("send")){
                viewHolder=new ViewHolder();
                convertView=inflater.inflate(R.layout.sentitem,null);
                viewHolder.name=(TextView)convertView.findViewById(R.id.messagedetail_row_name);
                viewHolder.date=(TextView)convertView.findViewById(R.id.messagedetail_row_date);
                viewHolder.content=(TextView)convertView.findViewById(R.id.messagedetail_row_text);
            }
//            viewHolder.number=(TextView)convertView.findViewById(R.id.sms_name);
//            viewHolder.body=(TextView)convertView.findViewById(R.id.sms_body);
            convertView.setTag(viewHolder);
     //   }else{
     //       viewHolder=(ViewHolder)convertView.getTag();
      //  }
        viewHolder.name.setText(list.get(position).getContact());
        viewHolder.content.setText(list.get(position).getMSGContent());
        viewHolder.date.setText(list.get(position).getDate());
        return convertView;
    }
    public class ViewHolder{
        TextView name;
        TextView content;
        TextView date;
    }
}
