package com.skuniv.juhyun.mycoupon;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.skuniv.juhyun.mycoupon.httpConnection.GetMemberInfo;
import com.skuniv.juhyun.mycoupon.model.InfoMember;

import java.util.concurrent.ExecutionException;


/**
 * Created by Juhyun on 2018-05-03.
 */

public class Fragment1 extends Fragment {
    private ImageView ivCard;
    private ImageView ivQRcode;
    private GridView gv;
    private int state = 0;
    private String qrCode;
    private int stamp = 0;

    public Fragment1() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.fragment_first, container, false);
        gv = (GridView) layout.findViewById(R.id.gridView1);
        int[] img = new int[15];

        ///////통신으로 쿠폰 정보 받아오는 부분

        GetMemberInfo getMemberInfo;
        getMemberInfo = new GetMemberInfo("http://117.17.142.135:8080/MyCoupon/getStampList", "kim", "0");

        try {
            String result = getMemberInfo.execute().get();
            Log.d("result", result);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            InfoMember infoMember = gson.fromJson(result, InfoMember.class);

            qrCode = infoMember.getQrCode();
            stamp = infoMember.getStamp();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


//      db에서 쿠폰도장 갯수와 매칭
        for (int i = 0; i < 15; i++) {
            if (state != stamp) {
                img[i] = R.drawable.check_coupon;
                state++;
            } else
                img[i] = R.drawable.empty_coupon;
        }

        for (int i = 0; i < 15; i++) {
            Log.d("test", String.valueOf(img[i]));
        }

        // 커스텀 어댑터 생성
        CouponAdater adapter = new CouponAdater(
                getActivity(),
                R.layout.gridview_coupon,       // GridView 항목의 레이아웃 row.xml
                img);    // 데이터

        gv.setAdapter(adapter);  // 커스텀 아답타를 GridView 에 적용

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_qr);
                dialog.setTitle("QR CODE");

                ivQRcode = (ImageView) dialog.findViewById(R.id.iv_qrcode);

                generateRQCode(qrCode);
                dialog.show();
            }
        });

        adapter.notifyDataSetChanged();
        return layout;


    }

    public void generateRQCode(String contents) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            Bitmap bitmap = toBitmap(qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, 200, 200));
            ivQRcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap toBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }
}

