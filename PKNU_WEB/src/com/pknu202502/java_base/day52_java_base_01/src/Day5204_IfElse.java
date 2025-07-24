package com.pknu202502.java_base.day52_java_base_01.src;

/***
 * Day5204_IfElse 클래스
 *  - 조건문 실습
 */
public class Day5204_IfElse {
    public static void main(String[] args) {
        System.out.println("Day5204_IfElse 클래스 : 조건문 실습");

        /**
         * 조건문(if문)
         * if(조건1){
         * }else if(조건2){
         * }else if(조건3){
         * }else{
         * }
         */

        /**
         * 1. score 변수 생성하기: 임의 값으로 정의하기
         * 2. score의 값이 90이상이면: A학점 입니다. 출력
         *                 80이상이면: B학점 입니다. 출력
         *                 나머지는  : F학점 입니다. 출력
         */
        
        int score = 95;

        if(score >=90 ){
            System.out.println("A학점 입니다.");
        }else if(score >= 80){
            System.out.println("B학점 입니다.");
        }else{
            System.out.println("F학점 입니다.");
        }

        
        char grade;
        if(score >= 90){
            grade = 'A';
        }else if(score >= 80){
            grade = 'B';
        }else{
            grade = 'F';
        }
        System.out.println(grade + "학점 입니다.");
        System.out.println("입력하신 점수는 %s학점 입니다.".formatted(grade));
        System.out.println("입력하신 점수는(%d)는 %s학점 입니다.".formatted(score, grade));

        System.out.println("\n -------------------------------------------------- \n");

        /**
         * 임의 변수에 정수값을 저의한 후
         *  - 해당 변수의 값이 짝수이면, 짝수 입니다. 출력
         *                     홀수이면, 홀수 입니다. 출력
         */

        int num = 19;
        if((num & 2) == 0){
            System.out.println("짝수 입니다.");
        } else {
            System.out.println("홀수 입니다.");
        }

        if(num % 2 == 0) System.out.println("짝수 입니다.");
        else System.out.println("홀수 입니다");

        // 3항 연산자로 짝수/홀수 처리하기
        // -(조건) > 참인결과값 : 거짓인결과값
        String result = (num % 2 == 0) ? "짝수" : "홀수";
        System.out.println(result + " 입니다.");
    }

    /**
     * Day5205_Switch 자바 파일 신규 생성
     *  - 파일 잘 실행되는지 "잘 실행됩니다" 출력해서 확인..
     */
}
