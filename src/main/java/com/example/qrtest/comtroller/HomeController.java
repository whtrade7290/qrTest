package com.example.qrtest.comtroller;

import com.sun.tools.javac.Main;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@Slf4j
public class HomeController {


    @GetMapping("/")
    public String Home() {

        return "index";
    }
    @GetMapping("/productList/{userId}")
    public String ProductList(@PathVariable(required = false) int userId, Model model) {

        // 외부 서비스로부터 데이터를 요청하고 JSON 형식으로 받아오는 예시
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/?userId=" + userId; // 외부 API의 엔드포인트
        Object responseData = restTemplate.getForObject(apiUrl, Object.class);

        model.addAttribute("responseData", responseData);

        return "productList";
    }
}
