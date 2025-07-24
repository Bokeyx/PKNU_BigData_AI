package com.pknu202502.java_base.day55_java_base_04.src.day5501;

public class Circle extends Shape {
    double radius;

    /**
     * 자식 클래스 생성자 선언 및 정의하기
     * @param radius
     * @param color
     */
    public Circle(double radius, String color) {
        // 부모 일반 멤버 변수
        this.color = color;
        // 자식 일반 멤버 변수
        this.radius = radius;
    }

    /**
     * 부모 추상 클래스의 추상 메소드 재정의(Override) 하기
     *  - 원의 넓이를 계산해서 반환하는 메소드
     */
    @Override
    public double getArea() {
        // 원의 넓이를 계산하기
        return Math.PI * this.radius * this.radius;
    }
 }
