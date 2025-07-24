package com.pknu202502.java_base.day55_java_base_04.src.day5501;

public abstract class Shape {
    public String color;

    public abstract double getArea();

    public void printColor() {
        System.out.println("도형 색상: %s".formatted(this.color));
    }
}

