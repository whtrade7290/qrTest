package com.example.qrtest.api;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class RestApiController {

    @GetMapping("/create_qr")
    public ResponseEntity<byte[]> createQrCode(@RequestParam(required = false, defaultValue = "1") int userId) {
        int width = 200;
        int height = 200;
        String url = "http://192.168.0.6:8080/productList/" + userId;

        try {
            BitMatrix encode = new MultiFormatWriter().encode(url,BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(encode, "PNG", out);

            byte[] imageBytes = out.toByteArray();

            // HTTP 응답에 이미지 데이터를 담아서 반환
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(imageBytes.length);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
