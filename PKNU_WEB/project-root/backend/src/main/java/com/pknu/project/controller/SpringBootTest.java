package com.pknu.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SpringBootTest {

    // SpringBoot 연결이 잘되는지 확인을 위한 메소드
    @GetMapping(path="/spring_test")
    public ResponseEntity<String> getCartList() {    

        // 해당 메소드가 잘 호출되면 -> 성공 메시지 응답해주기
        return ResponseEntity.ok("SpringBoot로 연결이 잘 되었습니다.!!");
    }
}
