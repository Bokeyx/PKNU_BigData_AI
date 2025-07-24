package com.pknu.project.exception;

/***
 * <사용자 정의 예외처리 클래스>
 *  - 실행(Runtime) 시에 발생하는 오류를 처리
 *  - Runtime 시 발생하는 오류 : RuntimeExceptin 클래스 상속 받아야함
 *  - 상세조회, 입력, 수정, 삭제와 같이 특정 PK를 이용해서 처리하는 경우에
 *     -> 해당 PK에 대한 데이터가 존재하지 않으면 Resource가 없다고해서 오류가 발생함
 *  - 이에 대한 예외처리가 필요함
 *  - RESTful API 방식을 사용할 경우에는 Service 클래스에서 
 *     Repository 처리와 동시에 예외처리를 진행하여야 코드량을 줄일 수 있음
 */
public class ResourceNotFoundException extends RuntimeException {
    // 생성자 메소드 정의하기
    //  - 예외를 처리하는 곳에서 생성시킨 후 예외 메시지를 넘겨주도록 처리
    public ResourceNotFoundException(String message){
        // 부모 예외 클래스(RuntimeException)에 메시지 전달하기
        //  - 부모 예외 클래스가 처리해줌
        super(message);
    }
}
