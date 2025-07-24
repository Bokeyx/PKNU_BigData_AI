package com.pknu202502.java_base.day56_java_base_05.day5601;

public class Person {
    /**
     *  - 멤버 변수 name 선언 : 문자타입
     *  - 클래스 생성 시 name의 값을 정의합니다.(임의 값 사용)
     *  - final 메소드 선언 및 정의
     *    -> 메소드명 : getName  (name 변수의 값을 리턴 할 겁니다.)
     *  - 일반 멤버 메소드 선언 및 정의
     *    -> 메소드명 : setName  (name 변수에 임의값 넣을 겁니다.)
     */
    private String name;

    public Person(String name){
        this.name = name;
    }

    // 클래스는 상속될 수 있지만, 
    //  - final 메소드는 재정의 불가
    public final String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
