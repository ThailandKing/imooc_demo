package com.it.shw.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.HashMap;

public class CreateQRCode {
    public static void main(String[] args) {
        //声明二维码参数
        int width=300;
        int height=300;
        String format="png";
        String content="http://www.imooc.com";

        //定义二维码参数
        HashMap<EncodeHintType,Object> hints=new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN,2);
        //生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path file=new File("D:\\code.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix,format,file);
            MatrixToImageWriter.writeToStream(bitMatrix, format,new FileOutputStream(new File("D:\\img.png")));
            System.out.println("二维码生成成功。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
