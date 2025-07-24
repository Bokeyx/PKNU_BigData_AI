package com.pknu202502.java_base.day54_java_base_03.src.java_day54;

/***
 * 학생 정보 관리 클래스
 */
public class Student {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * 멤버 age 변수에 값 저장하는 기능
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 멤버 age 변수의 값을 조회하는 기능
     * @return int
     */
    public int getAge() {
        return this.age;
    }
}
