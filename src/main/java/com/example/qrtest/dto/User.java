package com.example.qrtest.dto;

import lombok.Data;

import java.io.ByteArrayOutputStream;

@Data
public class User {

    private int num;
    private String username;
    private ByteArrayOutputStream qrCode;
}
