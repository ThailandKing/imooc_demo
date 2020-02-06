package com.it.shw.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

/**
 *@author thailandking
 *@date 2019/7/14 22:51
 *@desc 二维码工具类
 */
public class QRCodeUtil {
    private final static String format="png";

    //生成二维码
    public static void createQRCode(int width,int height,String content,String path){
        //定义二维码参数
        HashMap<EncodeHintType,Object> hints=new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN,2);
        //生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format,new FileOutputStream(new File(path)));
            System.out.println("二维码生成成功。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //二维码解析
    public static void decodeQRCode(String path){
        try {
            //读取二维码
            MultiFormatReader formatReader=new MultiFormatReader();
            File file=new File(path);
            BufferedImage image= ImageIO.read(file);
            //解析
            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            HashMap<DecodeHintType,Object> hints=new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET,"utf-8");
            Result result = formatReader.decode(binaryBitmap, hints);
            //获取结果
            System.out.println("解析结果："+result.toString());
            System.out.println("格式："+result.getBarcodeFormat());
            System.out.println("文本内容："+result.getText());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
