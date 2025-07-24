package com.pknu202502.java_base.day54_java_base_03.src;

/***
 * 상속 : 메소드 오버라이드 (재정의)
 */
public class Day5405_Inheritance_Override {

    /**
     * <메소드 오버라이드(재정의)>
     *  - 부모 클래스의 메소드를 재정의하여 사용할 때 사용되는 개념을 오버라이드라고 칭함
     * 
     *  @ 규칙
     *   - 부모 클래스의 원형을 그대로 사용함
     *     -> 반환 타입, 메소드명, 매개변수 타입 및 갯수가 모두 부모의 원형을 따릅니다.
     *   - 재정의 범위 : 메소드 내에서 처리하는 프로그램만 수정하여 사용합니다.
     *   - 자식 클래스에서 부모 클래스의 메소드를 재정의하게 되며,
     *     --> 재정의 시에 재정의할 메소드 위에 "@Override" 어노테이션을 정의해야함
     *     --> "@Override" 어노테이션이 정의된 메소드들은 모두 부모 클래스의 메소드라는 의미
     *         즉, 재정의 한다는 의미로 해석합니다.
     *   - 자식클래스에서 부모클래스의 메소드를 재정의(오버라이드)하면,
     *     자식쪽에서는 더이상 재정의한 부모의 메소드는 사용하지 않고,
     *     자식이 재정의한 자신의 메소드를 호출하여 사용하게됨
     *   - 부모쪽에 있는 멤버들의 이름과 동일하게 자식쪽에서도 선언 및 생성은 가능함
     *     --> 상속은 자식이 부모의 자원에 접근해서 사용한다는 개념임
     */

    public static void main(String[] args) {
        System.out.println("[상속 : 메소드 오버라이드(재정의)]");
        System.err.println("--------------------------------");

        Dog dog = new Dog(2025);
        Cat cat = new Cat();

        System.out.println("Dog를 통한 부모의 printView1() 메소드 호출");
        dog.printView1();
        dog.printOverride();
        
        System.out.println("Cat를 통한 부모의 printView1() 메소드 호출");
        cat.printView1();
        cat.printOverride();

        // dog 객체를 이용해서 부모의 name, age값을 변경하기
        //  - 값은 임의의 값으로
        //  - 변경 후 dog 및 cat을 통해서 name과 age의 값을 출력해 주세요.
        dog.setName("name의 값 변경");
        dog.setAge(1111111);
        System.out.println("dog에서 name=%s, age=%d".formatted(dog.getName(),
                                                               dog.getAge()));
        System.out.println("cat에서 name=%s, age=%d".formatted(cat.getName(),
                                                               cat.getAge()));
    }
}

/**
 * 부모 클래스
 */
class Animal{
    private String name;
    private int age;

    // 생성자 메소드 정의
    public Animal(){
        // 기본값으로 멤버 변수들 정의
        this.name = "사자";
        this.age  = 33;
    }

    // 출력 메소드
    public void printView1(){
        System.out.println("부모 클래스 ::: printView1() 메소드 호출");
    }

    // 출력 메소드2
    public void printOverride(){
        System.out.println("부모 클래스 ::: printOverride() 메소드 호출");
    }

    // 멤버 변수에 대한 getter 및 setter 생성하기
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}

/**
 * Dog 자식 클래스 생성하기
 *  - 부모 Animal 상속 받기
 */
class Dog extends Animal{
    int year;

    // 생성자 메소드
    public Dog(int year){
        this.year = year;
        this.setName("자식1");
        this.setAge(11);
    }

    // 부모 클래스의 printOverride() 메소드 재정의 하기
    @Override
    public void printOverride(){
        System.out.println("Dog 자식 클래스 ::: printOverride() 메소드 호출");
        System.out.println("이름/나이/년도 : %s / %d / %d".formatted(this.getName(),
                                                                     this.getAge(),
                                                                     this.year));
    }
}

/**
 * Cat 자식 클래스 만들기
 */
class Cat extends Animal{    
    public void printOverride(){
        System.out.println("자식클래스 Cat : 자식의 printOverride() 함수..재정의 되었음");
    }
}