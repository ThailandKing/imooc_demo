package com.it.shw.zxing;

public class Client {
    public static void main(String[] args) {
        String path="D:\\bd.png";
        QRCodeUtil.createQRCode(400,400,"http://www.baidu.com",path);
        QRCodeUtil.decodeQRCode(path);
    }
}
