package com.pknu.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 예외를 전역(서버내 모든 곳) 처리하는 어노테이션 클래스
//  - 예외가  발생하면 이곳을 통해서 사용자 정의 예외 클래스가 사용됨
//  - 예외 처리는 기존 Service에서 했던 처리를 그대로 유지
@ControllerAdvice
public class GlobalExceptionHandler {
    
    // ExceptionHandler() : 예외가 발생하면 자동으로 호출되는 클래스
    //  - 사용자가 정의한 예외가 발생한 경우 -> 사용자 정의 예외 클래스로 처리 진행
    @ExceptionHandler(ResourceNotFoundException.class)
    // 사용자 정의 예외 클래스를 매개변수로 받아옴(자동으로 받아서 처리됩니다.)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex){
        // HttpStatus.NOT_FOUND : 예외 발생 시 상태 코드를 추출합니다. : 404 오류
        //  - 예외 메시지를 반환합니다.
        // ResponseEntity : 사용자에게 응답을 처리하는 객체
        //                : 사용자 브라우저에 오류를 발생(서비스 오류)시키지 않고,
        //                   -> 응답 메시지로 보여주는 방식으로 사용됨
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
