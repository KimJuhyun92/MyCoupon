package com.skuniv.juhyun.mycoupon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRActivity extends AppCompatActivity {
    private Button reader;
    private Button generater;
    private EditText makeQRCode;
    private ImageView ivQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        reader = (Button)findViewById(R.id.btn_start_qrcode_reader);
        generater = (Button)findViewById(R.id.btn_start_qrcode_generate);
        makeQRCode = (EditText)findViewById(R.id.edt_qrcode_content);
        ivQRCode = (ImageView)findViewById(R.id.iv_generated_qrcode);

        reader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQRCode();
            }
        });

        generater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = makeQRCode.getText().toString();
                if (content.isEmpty()) {
                    Toast.makeText(QRActivity.this, "문자를 입력해주세요", Toast.LENGTH_LONG).show();
                } else {
                    generateRQCode(content);
                }

            }
        });

    }

    public void startQRCode() {
        new IntentIntegrator(this).initiateScan();
    }

    public void generateRQCode(String contents) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            Bitmap bitmap = toBitmap(qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, 200, 200));
            ivQRCode.setImageBitmap(bitmap);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
