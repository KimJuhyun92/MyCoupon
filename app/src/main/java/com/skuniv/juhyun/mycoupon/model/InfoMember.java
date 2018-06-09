package com.skuniv.juhyun.mycoupon.model;

/**
 * Created by Juhyun on 2018-05-07.
 */

public class InfoMember {
    String id;
    String pw;
    String cafeCode;
    String qrCode;
    int stamp;
    int useable_coupon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getCafeCode() {
        return cafeCode;
    }

    public void setCafeCode(String cafeCode) {
        this.cafeCode = cafeCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getStamp() {
        return stamp;
    }

    public void setStamp(int stamp) {
        this.stamp = stamp;
    }

    public int getUseable_coupon() {
        return useable_coupon;
    }

    public void setUseable_coupon(int useable_coupon) {
        this.useable_coupon = useable_coupon;
    }
}
