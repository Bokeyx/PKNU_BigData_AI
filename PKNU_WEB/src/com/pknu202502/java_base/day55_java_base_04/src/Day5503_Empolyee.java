package com.pknu202502.java_base.day55_java_base_04.src;

/***
 * 다형성(Employee)
 */
public class Day5503_Empolyee {

    /**
     * <다형성(Employee)>
     *  - 다형성은 개념적 용어 입니다.
     *  - 추상 클래스, 인터페이스 클래스 처럼 특정 키워드가 있지는 않음
     *  - 상속의 개념을 그대로 사용함
     *  - 다형성 처리 방법
     *   -> 오버라이드(Override), 업캐스팅(Upcasting), 동적바인딩(Dynamic Binding)의 기능으로 처리됨
     *  - 다형성은 클래스를 생성(new)하는 시점에 다형성의 개념이 적용됨
     *    즉 다형성 처리 방법으로 생성(new) 됨
     *  - 다형성 생성 방법
     *   : 부모클래스 변수명 = new 자식 클래스명();
     *   : 자식 클래스는 부모 타입으로 자동으로 변환되어 사용됩니다.
     *    -> 이를 업캐스팅(Upcasting, 형변환) 이라고 칭합니다.
     *  - 다형성은 상속, 인터페이스, 추상의 모든 개념을 적용하여
     *    클래스를 선언 및 정의할 수 있기에 오버라이드(Override) 메소드 사용 가능
     * 
     *  - 사용 시점
     *   -> 공통된 인터페이스나 상속 받은 부모 클래스로
     *    -> 여러 자식 객체를 같은 부모 타입의 형태로 변환하여 사용하고자 할 때 사용
     *  
     *  - 다형성 핵심: 부모의 자원을 자식들이 공유해서 사용됨
     */

    public static void main(String[] args) {
        System.out.println("[다형성(Employee)]");
        System.out.println("---------------------------------");

        //------------------------------
        // 부모 클래스 생성해보기
        Employee emp = new Employee();
        
        // 다형성 개념을 적용하여 생성하기
        // - 자식 클래스 생성하기
        // - 부모의 타입으로 자식을 생성(new)
        //  -> 이때 자식은 부모의 타입으로 변환됩니다.
        //  -> 이를 업캐스팅(Upcasting)되었다고 합니다.
        Employee emp2 = new Manager();

        // 부모 객체를 이용해서 work 메소드 호출하기
        emp.work();
        // 다형성 부모 객체를 이용해서 work 메소드 호출하기
        emp2.work();
        
        System.out.println("----------------------------------------");
        //------------------------------
        // 부모 클래스 생성해보기
        Animal2 animal2 = new Animal2();

        // 다형성이 적용된 자식
        Animal2 animal_dog = new Dog2();
        Animal2 animal_cat = new Cat2();

        // 실행(Run)시점에 부모의 메소드의 결과를 자식의 결과로 잠시 덮어쓰고
        // 처리 후 메모리 소멸..(부모의 자원을 자식의 자원으로 덮어쓰는 형태입니다.)
        animal2.sound_print();
        animal_dog.sound_print();
        animal_cat.sound_print();
    }
}

/***
 * 부모 클래스 생성하기
 *  - 클래스명: Employee
 *  - 일반 멤버 메소드: work(리턴없음, 매개변수 없음)
 *   -> 처리: "[상속 개념 적용] 직원이 일합니다." 출력하는 메소드
 */
class Employee {
    public void work() {
        System.out.println("[상속 개념 적용] 직원이 일합니다.");
    }
}

/***
 * 자식 클래스 생성하기
 *  - 클래스명: Manager
 *  - 부모 클래스 상속 받기
 *  - 부모 멤버 메소드 재정의 하기
 *   -> "[상속 개념 적용] 관리자가 프로젝트를 관리합니다." 출력하는 메소드
 */
class Manager extends Employee {
    @Override
    public void work() {
        System.out.println("[상속 개념 적용] 관리자가 프로젝트를 관리합니다.");
    }
}

//----------------------------------------
/**
 * 부모 클래스 정의하기
 *  - 클래스 이름: Animal2
 *  - 메소드명: sound_print (리턴 및 매개변수 없음)
 *            : 처리 -> "소리를 냅니다" 출력
 */

class Animal2 {
    public void sound_print() {
        System.out.println("소리를 냅니다");
    }
}

/**
 * 자식 클래스 2개 생성하기
 *  - Dog2 클래스, Cat2 클래스 각각 정의하기
 *  - Don2 및 Cat2 클래스는 부모 Animal2를 상속 받습니다.
 *  - 각 자식 클래스에서 부모 클래스의 sound-print() 메소드 재정의하기
 *  - 출력값은 자유롭게.
 *   -> Dog2의 출력값 예시: 멍멍~
 *   -> Cat2의 출력값 예시: 야~옹
 */

class Dog2 extends Animal2 {
    @Override
    public void sound_print() {
        System.out.println("멍멍~");
    }
}

class Cat2 extends Animal2 {
    @Override
    public void sound_print() {
        System.out.println("야~옹");
    }
}