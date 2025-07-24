package com.pknu202502.java_base.day52_java_base_01.src;

/***
 * Day5202_variable Java 파일 신규 생성
 *  - 일반 변수 사용 방법...
 */
public class Day5202_variable {

    public static void main(String[] args) {
        System.out.println("잘 실행됩니다");

        /**
         * <일반 변수 타입>
         * - 정수(int), 실수(double), 문자 한글자(char), 문자열(String), 논리형(boolean)
         * 
         * <변수 생성 규칙>
         *  - 타입 변수명;      // 변수 선언
         *  - 타입 변수명 = 값; // 변수 선언 및 정의
         */

        // 정수형 변수 선언 및 정의(초기화)
        //  - 해당 변수에는 정수값만 입력 가능합니다.
        //    (다른 타입을 넣으면 오류 납니다.) 
        int age = 25;

        // 실수형 변수 선언 및 정의(초기화)
        double height = 123.543;

        // 문자형 변수 선언 및 정의(초기화)
        //  - 문자는 보통 작은따옴표를 사용(많이 사용되지는 않는 타입임)
        char grade = 'A';

        // 문자열 변수 선언 및 정의(초기화)
        //  - 쌍따옴표로 묶는것이 일반적임
        //  - 문자열 타입은 String 객체 타입을 사용합니다.
        //  - 아래는 일반 문자열 타입으로 사용됨(주소를 가지지 않음, 일반 변수와 동일)
        String name = "홍길동";

        // 아래는 객체를 생성한 문자객체 입니다.(name2는 주소값을 가집니다.)
        String name2 = new String("홍길동");


        // 논리형 변수 선언 및 정의(초기화)
        //  - true 또는 false의 값을 가지며, 소문자로 작성합니다.
        boolean isState = true;

        /**
         * 위 변수들 각각 출력해보기
         */
        System.out.println("이름: " + name);
        System.out.println("이름2: " + name2);
        System.out.println("나이: " + age);
        System.out.println("키: " + height);
        System.out.println("학점: " + grade);
        System.out.println("학생 여부: " + isState);
    }

    /**
     * Day5203_Operators 자바 파일 신규 생성
     *  - 파일 잘 실행되는지 "잘 실행됩니다" 출력해서 확인..
     */
}
