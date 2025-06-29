package com.study.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    /**
     * 生成二维码并保存为图片文件
     * @param data 要编码的数据
     * @param filePath 输出文件路径
     * @param width 二维码宽度
     * @param height 二维码高度
     * @throws WriterException 如果编码失败
     * @throws IOException 如果文件写入失败
     */
    public static void generateQRCode(String data, String filePath, int width, int height) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hints);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void main(String[] args) {
        try {
            // 保存到当前工作目录
            generateQRCode("Hello, World!", "C:\\SpringBoot\\TicketSystem\\src\\main\\resources\\static\\img\\hello_world.png", 300, 300);
            System.out.println("QR Code generated successfully in current directory!");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
