package com.pknu202502.java_base.day52_java_base_01.src;

/***
 * Day5203_Operators 자바 파일 신규 생성
 *  - 연산자 실습  
 */

public class Day5203_Operators{
    public static void main(String[] args) {
        System.out.println("<Dat5203_Operators> Class");

        /**
         * <연산자 종류>
         *  - 산술연산자, 비교(조건)연산자, 논리연산자, 3항연산자(잘 사용안함)
         *  - 산술연산자: + - * / % ++ --
         *  - 비교(조건)연산자: > < >= <= == !=
         *   -> 문자열 및 객체 비교시 == 및 !=을 사용하면 "주소" 값에 대한 비교를 수행함
         *   -> 문자열 중 일반변수 타입에 대해서는 equals()라는 함수를 사용해야함
         *      "문자열".equals("비교문자열")라는 함수를 사용해야함
         *      (문자열 비교함수)
         *  - 논리연산자: && || !(not 연산자, 반대개념, 참이면 거짓으로, 거짓이면 참으로)
         */

        // 정수형 변수 a와 b 선언, 값은 a에는 10과 b에는 3을 넣어주세요.
        int a = 10;
        int b = 3;
        int c = 10, d = 3;

        System.out.println("a % 2 = " + (a % 2));

        // 비교 연산자
        System.out.println("a > b: " + (a > b));

        // 논리 연산자
        System.out.println("a > b && d > c: " + ((a > b) && (d > c)));
        System.out.println("a > b || d > c: " + ((a > b) || (d > c)));

        /**
         * 문자열 비교하기
         */
        String name = "홍길동";
        String name2 = "홍길동";
        System.out.println("name == name2 은 " + (name == name2));

        name2 = name;
        System.out.println("name == name2 은 " + (name == name2));

        String name3 = "홍길동";
        System.out.println("name.equals(name3) 은 " + name.equals(name3));

        String name4 = new String("홍길동");
        System.out.println("name3 == name4 은 " + (name3 == name4));

        String name5 = name4;
        System.out.println("name4 == name5 은 " + (name4 == name5));

        name5 = name4;
        System.out.println("name4 == name5 은 " + (name4 == name5));

        /**
         * Day5204_IfElse 자바 파일 신규 생성
         *  - 파일 잘 실행되는지 "잘 실행됩니다" 출력해서 확인..
         */
    }
}