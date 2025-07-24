package com.pknu202502.java_base.day53_java_base_02.src;


import java.util.Scanner;

/***
 * 메소드 오버로드(Overload)
 */

public class Day5306_MethodOverload {

    /**
     * <메소드 오버로드(Overload) 규칙>
     *  1. 메소드 이름은 동일
     *  2. 매개변수 갯수가 다른 경우 사용 가능
     *  3. 매개변수 타입가 다른 경우 사용 가능
     *  4. 2~4번 중에 하나라도 다른 경우 사용 가능
     */

    // 멤버 메소드 생성하기(오버로드 적용)
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    // public String add(int a, int b, int c) {
    //     return "%d x %d = %d".formatted(a, b, a*b);
    // }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            System.out.println("0으로 나눌 수 없습니다.");
            return 0;
        }
        return a / b;
    }

    public boolean even(int a) {
        return a % 2 == 0;
    }

    public boolean odd(int a) {
        return a % 2 != 0;
    }


    public static void main(String[] args) {
        System.out.println("[오버로드(Overload)] 실습");

        // 위 2개 멤버 메소드의 결과 출력하기
        Day5306_MethodOverload dm = new Day5306_MethodOverload();
        System.out.println("int add() 함수 호출 결과: %d".formatted(dm.add(5, 8)));
        System.out.println("double add() 함수 호출 결과: %f".formatted(dm.add(5.5, 8.8)));

        System.out.println("\n------------------------------\n");

        /**
         * - 오버로드 메소드를 이용해서...
         * - 더하기, 빼기, 곱하기, 나누기, 짝수or홀수 결과값을 리턴하기
         * - 멤버 메소드의 갯수는 자유롭게..
         * 
         * - main 메소드에서 출력하기
         */
        
        Scanner sc = new Scanner(System.in);
        System.out.print("x의 정수값을 입력하여 주세요: ");
        int x = sc.nextInt();
        System.out.print("y의 정수값을 입력하여 주세요: ");
        int y = sc.nextInt();
        System.out.println("사용자로부터 입력 받은 값: x = %d / y = %d".formatted(x,y));
        System.out.println("------------------------------");
        System.out.println("int add() 함수 호출 결과: %d + %d = %d".formatted(x, y, dm.add(x, y)));
        System.out.println("------------------------------");
        System.out.println("int substract() 함수 호출 결과: %d - %d = %d".formatted(x, y, dm.subtract(x, y)));
        System.out.println("------------------------------");
        System.out.println("int multiply() 함수 호출 결과: %d * %d = %d".formatted(x, y, dm.multiply(x, y)));
        System.out.println("------------------------------");
        System.out.println("int divide() 함수 호출 결과: %d / %d = %d".formatted(x, y, dm.divide(x, y)));
        System.out.println("------------------------------");
        System.out.println("boolean even() 함수 호출 결과: %s로 짝수입니다".formatted(dm.even(x)));
        System.out.println("------------------------------");
        System.out.println("boolean odd() 함수 호출 결과: %s로 홀수입니다".formatted(dm.odd(x)));
        System.out.println("------------------------------");
        sc.close();
    }
}
