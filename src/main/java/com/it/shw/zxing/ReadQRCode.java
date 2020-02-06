package com.it.shw.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class ReadQRCode {
    public static void main(String[] args) throws Exception{
        MultiFormatReader formatReader=new MultiFormatReader();
        File file=new File("D:\\code.png");
        BufferedImage image= ImageIO.read(file);
        BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        HashMap<DecodeHintType,Object> hints=new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET,"utf-8");
        Result result = formatReader.decode(binaryBitmap, hints);
        System.out.println("解析结果："+result.toString());
        System.out.println("格式："+result.getBarcodeFormat());
        System.out.println("文本内容："+result.getText());
    }
}
