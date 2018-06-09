package com.skuniv.juhyun.mycoupon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.skuniv.juhyun.mycoupon.model.InfoMember;

import java.util.List;

/**
 * Created by Juhyun on 2018-05-06.
 */

public class CouponAdater extends BaseAdapter {

    Context context;
    int layout;
    int img[];
//    int state;
    LayoutInflater inf;
//    List<InfoMember> infoMembers;

    public CouponAdater(Context context, int layout, int[] img) {
        this.context = context;
        this.layout = layout;
        this.img = img;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return img[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        ImageView iv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null)
//            convertView = inf.inflate(layout, null);
//        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView1);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inf.inflate(layout, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.imageView1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.iv.setImageResource(img[position]);

        return convertView;
    }
}
