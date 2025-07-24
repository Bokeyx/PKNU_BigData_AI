package com.pknu202502.java_base.day55_java_base_04.src;

/***
 * 객체 배열(Array)을 활용한 다형성(Employee) 예시
 */
public class Day5504_Employee_ObjectArray {
    public static void main(String[] args) {
        System.out.println("[객체 배열(Array)을 활용한 다형성(Employee)]");
        System.out.println("-------------------------------------------");

        /***
         * 다형성 객체 배열 선언 및 정의하기
         *  - [부모 클래스] 타입으로 배열 객체 선언 및 자식 2개를 배열에 넣으면서 생성하기
         *    부모 = {자식1생성, 자식2생성} 이 방식으로 코드 작성(배열에 여러개 넣기)
         * 
         *  - 부모 배열에 있는 자식들의 객체를 반복문을 이용하여 추출 후
         *   -> pay(값 전달) 메소드 호출
         * 
         *  - 실행 후 자식들이 출력한 pay() 결과값 확인하기
         */

         Payment[] payment = {new CreditCard(), new Paypal()};

        for (int i = 0; i < payment.length; i++) {
            payment[i].pay(1500);
        }
    }
}

/***
 * [공통]으로 사용할 클래스 정의하기
 *  - 클래스명: PublicClass
 *  - 메소드 2개 정의
 *  - 메소드 첫번째명: printView (리턴없음, 매개변수 double tot 사용)
 *   -> 처리 기능: "[PublicClass] 신용카드로 tot원 결제됨" 출력
 * 
 *  - 메소드 두번째명: printView (리턴없음, 매개변수 int tot 사용)
 *   -> 처리 기능: "[PublicClass] 신용카드로 tot원 결제됨" 출력
 */
class PublicClass {
    public void printView(double tot) {
        System.out.println("[PublicClass] 신용카드로 %.3f원 결제됨".formatted(tot));
    }
    public void printView(int tot) {
        System.out.println("[PublicClass] 신용카드로 %d원 결제됨".formatted(tot));
    }
}

/***
 * [부모 인터페이스(interface) 클래스 생성하기]
 *  - 결제 인터페이스 메소드 정의
 *  - 클래스명: Payment
 *  - 메소드명: pay (리턴 없음, 매개변수 정수타입의 amount 사용)
 */
interface Payment {
    void pay(int amount);
}

/***
 * [자식클래스1] 신용카드 결제 클래스
 *  - 클래스명: CreditCard
 *  - [공통] 클래스는 상속(extends) 받기
 *  - [부모] 클래스는 implements 하기
 *  - [부모] 클래스의 pay 메소드 재정의 하기
 *   -> 처리 기능: 결제값 계산 = 전달 받은 매개변수(amount)에 0.95 곱하기
 *               : 결제값을 [공통] 클래스의 printView 메소드에 전달하여 출력하기
 *               : 부모클래스의 메소드 호출시에는 super. 으로 접근
 */
class CreditCard extends PublicClass implements Payment {
    @Override
    public void pay(int amount) {
        double discountedprice  = amount * 0.95;
        super.printView(discountedprice);
    }
}

/***
 * [자식클래스2] 페이팔 결제 클래스
 *  - 클래스명: Paypal
 *  - [공통] 클래스는 상속(extends) 받기
 *  - [부모] 클래스는 implements 하기
 *  - [부모] 클래스의 pay 메소드 재정의 하기
 *   -> 처리 기능: 결제값 계산 = 전달 받은 매개변수(amount)에 0.9 곱하기
 *               : 결제값을 [공통] 클래스의 printView 메소드에 전달하여 출력하기
 *               : 부모클래스의 메소드 호출시에는 super. 으로 접근
 */
class Paypal extends PublicClass implements Payment {
    @Override
    public void pay(int amount) {
        double discountedprice  = amount * 0.9;
        super.printView(discountedprice);
    }
}

