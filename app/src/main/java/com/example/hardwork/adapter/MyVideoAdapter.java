package com.example.hardwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hardwork.Bean.Study;
import com.example.hardwork.R;

import java.util.List;

public class MyVideoAdapter extends BaseAdapter {
    List<Study> mList;
    Context mContext;

    public MyVideoAdapter(Context context, List<Study> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    MyVideoAdapter.ViewHolder mHolder;


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        mHolder = new MyVideoAdapter.ViewHolder();
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.video_list, null);
            mHolder.tv1 = (TextView) view.findViewById(R.id.video_name);
            mHolder.tv2 = (TextView) view.findViewById(R.id.video_time);
            view.setTag(mHolder);
        } else {
            mHolder = (MyVideoAdapter.ViewHolder) view.getTag();
        }

        mHolder.tv1.setText(mList.get(position).getTitle());
        mHolder.tv2.setText(mList.get(position).getDate());
        return view;
    }

    class ViewHolder {
        TextView tv1;
        TextView tv2;
    }
}
