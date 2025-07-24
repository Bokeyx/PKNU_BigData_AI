package com.pknu202502.java_base.day55_java_base_04.src;

import com.pknu202502.java_base.day55_java_base_04.src.day5501.Circle;


/***
 * <추상(Abstract) 클래스>
 */
public class Day5501_Abstract {
    /**
     * <추상(Abstract) 클래스>
     * - 상속(Inheritance)의 개념을 그대로 적용함
     *  -> 단, 추상 클래스는 외부에서 객체를 생성할 수 없음
     * - 추상 클래스 선언시 class 왼쪽에 abstract를 명시하여야함
     * - 추상 클래스는 추상 메소드가 1개 이상 존재해야함
     * - 추상 메소드: 메소드 정의만 되어있고, 내부 처리 프로그램이 없는 상태
     *              : 메소드 선언시 왼쪽에 abstract를 명시해야함
     * - 추상 클래스 작성 방법: abstract class 클래스명{}
     * - 추상 메소드 작성 방법: abstract 반환타입 메소드명(매개변수);
     * 
     * - 자식 클래스에서 추상 클래스(부모 클래스가 됨)를 상속(extends) 받아서 사용
     * - 자식 클래스에서는 추상 클래스의 추상 메소드를 재정의(Override)하여 사용해야함
     *  -> 반드시 추상 메소드를 재정의해야 합니다.
     * 
     * - 사용시점
     *  -> 상속의 개념과 동일하게, 부모 클래스에서 공통된 로직은 정의하되,
     *  -> 하위 클래스마다 다른 동작(기능, 메소드를 의미)을 해야할 경우에
     *   -> 하위 클래스들에서 같은 이름을 사용하여 재정의하여 사용할 수 있도록 함
     */ 
    public static void main(String[] args) {
        System.out.println("[추상(Abstract) 클래스]");
        System.out.println("----------------------------------------");

        // 추상 클래스 부모를 생성해보기
        //  - 추상 클래스는 객체(인스턴스)를 생성할 수 없음
        //  - 자식을 통해서 생성 후 -> 추상 메소드 재정의 후 사용 가능
        // Shape shape = new Shape();

        // 자식 클래스 생성하기
        Circle c = new Circle(3.0, "Red");

        // 도형 색상 출력하기
        //  - 부모의 공통 메소드 자원을 활용
        c.printColor();

        // 원의 면적 출력하기
        System.out.println("원의 면적: %.3f".formatted(c.getArea()));
    }
}

// /**
//  * 추상 클래스 선언 및 정의하기
//  *  - 클래스명: Shape
//  *  - 멤버 일반 변수명: 문자열 color
//  *
//  *  - 멤버 추상 메소드 생성: 접근제한자 없이 선언
//  *   -> 리턴타입 double, 메소드명 getArea, 매개변수 없음
//  * 
//  *  - 멤버 일반 메소드 생성: 접근제한자 public 사용
//  *   -> 리턴타입 없음, 매개변수 없음, 메소드명 printColor()
//  *   -> 처리 프로그램: "도형 색상: color색상" 출력만 하기
//  */

// abstract class Shape {
//     // 일반 멤버 변수
//     String color;

//     // 추상 메소드 선언(정의는 하지 않음, 실체 없음)
//     abstract double getArea();

//     // 일반 멤버 메소드 선언 및 정의하기
//     //  - 자식들에게 공통으로 사용하게 하기위한 메소드로 사용
//     public void printColor() {
//         System.out.println("도형 색상: %s".formatted(this.color));
//     }
// }

// /***
//  * 자식 클래스 선언 및 정의하기
//  *  - 자식 클래스 이름: Circle
//  *  - 추상 클래스(부모 클래스) 상속 받아서 사용하기
//  *  - 일반 멤버 변수 선언하기: 변수타입은 double, 변수명: radius
//  *  
//  *  - 자식 클래스 생성자 메소드 선언 및 정의하기
//  *   -> 매개변수 변수명: double radius, String color
//  *   -> 매개변수를 이용해서, 자식의 radius 및 부모의 color 멤버 변수에 넣기
//  * 
//  *  - 부모의 추상 메소드 정의하기(실체 만들기)
//  *   -> 원의 넓이 구하는 공식 적용: 3.141592 * 반지름 * 반지름
//  *   -> 파이값은 Math.PI 라이브러리 사용
//  *   -> 반지름은 radius 변수임
//  *   -> 원의 넓이를 계산 후 리턴하기
//  */

// class Circle extends Shape {
//     double radius;

//     /**
//      * 자식 클래스 생성자 선언 및 정의하기
//      * @param radius
//      * @param color
//      */
//     public Circle(double radius, String color) {
//         // 부모 일반 멤버 변수
//         this.color = color;
//         // 자식 일반 멤버 변수
//         this.radius = radius;
//     }

//     /**
//      * 부모 추상 클래스의 추상 메소드 재정의(Override) 하기
//      *  - 원의 넓이를 계산해서 반환하는 메소드
//      */
//     @Override
//     public double getArea() {
//         // 원의 넓이를 계산하기
//         return Math.PI * this.radius * this.radius;
//     }
//  }


// /**
//  * 메인 클래스에서 Shape 및 Circle 클래스 외부 파일로 분리하기
//  *  - 외부에 폴더 생성: 현재 위치에 day5501 폴더 생성(소문자로 이름 작성)
//  *  - day5501에 부모 및 자식 클래스에 대한 java 파일 생성하기
//  *  - 메인 클래스에서 불러들여서 실행해 보기
//  *  - 메인 클래스에 정의 해놓은 부모 및 자식 클래스는 모두 주석처리
//  */