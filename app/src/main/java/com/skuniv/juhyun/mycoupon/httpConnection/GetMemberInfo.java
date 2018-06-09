package com.skuniv.juhyun.mycoupon.httpConnection;

import android.os.AsyncTask;


import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Juhyun on 2018-05-07.
 */

/**
 * Created by 김주현 on 2017-11-18.
 */

public class GetMemberInfo extends AsyncTask<Void, Void, String> {
    String answer;
    String url;
    String id;
    String cafeCode;

    public GetMemberInfo(String url, String id, String cafeCode) {
        this.url = url;
        this.id = id;
        this.cafeCode = cafeCode;
    }

    @Override
    protected String doInBackground(Void... params) {

        OkHttpClient client = new OkHttpClient();
        Response response;
        RequestBody requestBody = null;
        requestBody = new FormBody.Builder().add("id", id).add("cafeCode", cafeCode).build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            response = client.newCall(request).execute();
            answer = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("answer", "" + answer);
        return answer;
    }

    @Override
    protected void onPostExecute(String s) {
        //super.onPostExecute(s);
        //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
    }

    public RequestBody selectStudentList(RequestBody requestBody) {
        return requestBody = new FormBody.Builder().build();
    }
}
