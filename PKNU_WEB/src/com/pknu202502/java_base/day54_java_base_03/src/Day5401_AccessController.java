package com.pknu202502.java_base.day54_java_base_03.src;

import com.pknu202502.java_base.day54_java_base_03.src.java_day54.Student;

/***
 * 접근제어: public, protected, private
 *  - main 메소드가 있는 클래스를 --> 메인 클래스라고 칭하빈다.
 */
public class Day5401_AccessController {
    public static void main(String[] args) {
        System.out.println("[접근제어: public, protected, private]");
        System.out.println("\n----------------------------------------\n");

        /**
         * <접근 제어자>
         * - 외부 클래스에서 현재 클래스로 접근을 제어할 때 사용함
         * - 접근 제어자는 멤버 변수, 멤버 메소드, 클래스 선언 시 가장 첫번째(왼쪽)에 작성
         * - 접근 제어자 종류: public, protected, private
         * - public: 외부 클래스 어느 위치에서든지 접근 가능하도록함
         * - protected: 패키지(package로 정의된 범위) 내에 속한 클래스들만 접근 가능하도록 함
         * - private: 외부에서는 접근을 못하도록 함
         * - 일반적으로 -> 멤버 변수들은 private을 주로 사용
         *              -> 멤버 메소드들은 public을 주로 사용
         *              -> protected는 많이 사용 안하는 편임
         * - 멤버 변수, 멤버 메소드, 클래스 선언 시 접근 제어자를 작성 안하면,
         *   기본적으로 public으로 간주함(디폴트 접근제어자는 public)
         */

        /*
         * 현재 클래스 밖에(하단에) 새로운 클래스 만들기
         * - 클래스명: Student
         * -         : 접근제한자
         * - 멤버 변수: 문자열타입의 name, 정수타입의 age 변수명 생성
         *            : 외부에서 접근 못하게 정의
         */

        /*
         * Student 클래스의 name 및 age에 임의 값을 넣은 후,
         * name과 age값을 추출해주세요
         */

         // 클래스 생성하기: 객체가 만들어짐
        Student student = new Student();

        // name과 age값 저장하기
        student.setName("김보경");
        student.setAge(26);

        // name과 age값 받아오기
        String name = student.getName();
        int age = student.getAge();

        System.out.println("이름: %s | 나이: %d".formatted(name, age));
    }
}

// 아래 Student 클래스를 외부.java 파일로 분리하기
// - 파일명: Student.java(클래스 이름과 동일해야 합니다.)
// - 위치: 현재 위치에 파일 생성하고 아래 코드 복사해서 넣으시면 됩니다.
/**
 * @외부 파일 사용법
 * 1. 현재 위치에 외부 클래스 파일을 위치한 경우
 *  - import하지 않아도자동으로 포함해서 사용할 수 있음
 * 
 * 2. 현재 위치가 아닌, 다른 폴더 위치에 외부 클래스 파일을 위치한 경우
 *  - package명에 작성된 폴더 위치를 기준으로 import 합니다. 
 */

/***
 * 학생 정보 관리 클래스
 */
// class Student {
//     // 멤버 변수: 접근 못하게 private
//     private String name;
//     private int age;

//     public String getName() {
//         return this.name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public int getAge() {
//         return this.age;
//     }

//     public void setAge(int age) {
//         this.age = age;
//     }

    /***
     * 자동으로 getter 및 setter를 
     * 
     */

    /**
     * 멤버 변수에는 외부에서 접근이 안되기에
     * - public 메소드를 생성하여 멤버 변수의 값을 처리 사용
     */
    
    /***
     * 멤버 name 변수에 값을 저장하는 기능
     * - 리턴 타입없음, 매개변수 값 받아오기, 멤버변수에 값 저장
     * - 메소드 이름은 set으로 시작, 뒤에 이름은 실제 멤버 이름으로
     *  -> 멤버 이름의 첫글자는 대문자로 (SetName) 메소드 이름 정의
     * - 메소드 이름규칙은 Java에서 규칙처럼 사용되는 방식으로
     *  -> DB와의 데이터 연동 시에 자동바인딩 라이브러리를 사용하여 처리 될 때 주로 사용
     *  -> Java Beans 및 Servlet 라이브러리 개념이 적용됨
     *  -> VO, DAO, DTO라는 이름으로도 주로 폴더구조가 되어 있음(회사마다 다름)
     * @param name
     */
    // public void setName(String name) {
    //     this.name = name;
    // }

    // public String getName() {
    //     return this.name;
    // }

    // /**
    //  * 멤버 age 변수에 값 저장하는 기능
    //  * @param age
    //  */
    // public void setAge(int age) {
    //     this.age = age;
    // }

    // /**
    //  * 멤버 age 변수의 값을 조회하는 기능
    //  * @return int
    //  */
    // public int getAge() {
    //     return this.age;
    // }
// }
