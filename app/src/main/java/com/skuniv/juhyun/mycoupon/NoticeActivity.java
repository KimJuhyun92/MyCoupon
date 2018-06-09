package com.skuniv.juhyun.mycoupon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skuniv.juhyun.mycoupon.httpConnection.GetNoticeInfo;
import com.skuniv.juhyun.mycoupon.model.InfoNotice;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.ExecutionException;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        ListView listview = (ListView) findViewById(R.id.noticeListView);
        NoticeAdapter adapter = new NoticeAdapter(NoticeActivity.this);
        listview.setAdapter((ListAdapter) adapter);

        GetNoticeInfo getNoticeInfo = new GetNoticeInfo("http://"+URLPath.main+":8080/MyCoupon/getNoticeList");
        try {
            String result = getNoticeInfo.execute().get();
            Log.d("result json",result);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            InfoNotice[] infoNotices = gson.fromJson(result, InfoNotice[].class);
            for (InfoNotice infoNotice : infoNotices) {
                try {
                    String index = infoNotice.getNotice_index();
                    infoNotice.setNotice_index(index);

                    String date = infoNotice.getNotice_date();
                    infoNotice.setNotice_date(date);

                    String title = infoNotice.getNotice_title();
                    title = URLDecoder.decode(title, "UTF-8");
                    infoNotice.setNotice_title(title);

                    String content = infoNotice.getNotice_content();
                    content = URLDecoder.decode(content, "UTF-8");
                    infoNotice.setNotice_content(content);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                adapter.add(infoNotice);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();



    }
}
