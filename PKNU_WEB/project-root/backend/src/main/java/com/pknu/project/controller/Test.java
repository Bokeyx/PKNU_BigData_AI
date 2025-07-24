package com.pknu.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Rest API 전용 라이브러리 사용
//  -> 사용자의 요청과 응답을 담당하는 라이브러리
@RestController
public class Test {

    // http://localhost:8080/ : path의 /경로를 의미함
    // 사용자가 : /로 요청이 오면 -> root() 메소드를 호출하겠다는 정의
    //  -> URL Mapping 이라고 칭합니다.
    //  -> return을 하게되면 -> 사용자 브라우저에 return의 결과를 전달함
    //  -> 전달 방식 : Rest API 방식으로 전달하게됨(class 위에 정의되어 있음)
    // @GetMapping(path="/test/login_root")
    // public String root2222(){
    //     return "스프링부트 웹 성공!!";
    // }

    /**
     * http://localhost:8080/test/login
     */
    @GetMapping(path="/test/login")
    public String login(){
        String msg = "<h1>";
               msg += " <a href='/'>[홈 바로가기].</a> ";
               msg += "</h1>";
        System.out.println("터미널 영역에 데이터 결과 확인해보기 : " + msg);
        return msg;
    }
}
/*
 * controller 폴더에 Day5901_Index.java 파일 생성하기
 *  - /index 경로에 대해서 -> root() 메소드 호출하기
 *  - 응답은 "<h1>스프링부트 메인 메이지 입니다.</h1>"
 *  - Test.java 파일 내에 root() 메소드 부분은 주석 처리
 */