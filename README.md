---
title: 基于Zxing的二维码生成和识别
date: 2020-02-06 15:12:53
tags: [zxing]
---

## 一、添加依赖

```xml
<!--导入zxing依赖-->
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.2.1</version>
</dependency>
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>javase</artifactId>
    <version>3.2.1</version>
</dependency>
```

## 二、书写工具类

```java
public class QRCodeUtil {
    // 指定图片生成文件格式
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
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, 				                                        BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format,
                                              new FileOutputStream(new File(path)));
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
            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new                                                     BufferedImageLuminanceSource(image)));
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
```

## 三、测试使用

```java
public class Client {
    public static void main(String[] args) {
        String path="D:\\bd.png";
        QRCodeUtil.createQRCode(400,400,"http://www.baidu.com",path);
        QRCodeUtil.decodeQRCode(path);
    }
}
```

