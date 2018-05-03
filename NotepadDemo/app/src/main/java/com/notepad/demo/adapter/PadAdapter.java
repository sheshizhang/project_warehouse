package com.notepad.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.notepad.demo.R;
import com.notepad.demo.Utils.Utils;
import com.notepad.demo.mode.PadApp;

import java.util.List;

/**
 * Created by feiran.zhang on 2018/4/30.
 */

public class PadAdapter extends BaseAdapter {
    private Context mContext;
    private List<PadApp>mList;

    public PadAdapter(){
    }

    public PadAdapter(Context context,List<PadApp>list){
        mContext=context;
        mList=list;
    }
    public void setData(List<PadApp>list){
        mList=list;
    }
    public void removeData(int position){
        mList.remove(position);

        notifyDataSetInvalidated();
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler=null;

        if (convertView==null||convertView.getTag()==null){
            viewHoler=new ViewHoler();
            convertView=LayoutInflater.from(mContext).inflate(R.layout.pad_adapter,null);

            viewHoler.textView=convertView.findViewById(R.id.pad_textView);
            viewHoler.timer_text=convertView.findViewById(R.id.timer_text);

            convertView.setTag(viewHoler);
        }else{
            viewHoler= (ViewHoler) convertView.getTag();
        }

        viewHoler.textView.setText(mList.get(position).getContent_title());
        viewHoler.timer_text.setText(mList.get(position).getEdit_timer());

        return convertView;
    }


    class ViewHoler{
        private TextView textView;
        private TextView timer_text;
    }

}
