package com.pknu202502.java_base.day56_java_base_05.day5601;

public class MathUtils {
    /**
    *  - 외부 폴더에 MathUtils 클래스 생성하기
    *    --> add 메소드 생성 : 리턴타입 정수형, 
    *                        : 매개변수 정수타입으로 a, b 두개 사용
    *                        : 처리는 a와 b의 합한값 리턴
    *                        : 정적 메소드로 선언(final 사용 안함)
    *    --> square 메소드 생성  : 리턴타입 정수형, 
    *                        : 매개변수 정수타입으로 a 사용
    *                        : 처리는 a의 제곱한값 리턴
    *                        : 정적 메소드로 선언(final 사용 안함)
    */

    public static int add(int a, int b){
        return a + b;
    }

    public static int square(int a){
        return a * a;
    } 
}
