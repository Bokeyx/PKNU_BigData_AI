package com.pknu202502.java_base.day54_java_base_03.src;

/**
 * 클래스 상속
 */

public class Day5404_Inheritance {

    /**
     * <클래스 상속>
     *  - 자식 클래스를 확장하여 부모의 자원을 자유롭게 사용하는 객체지향 프로그래밍 개념
     *  - 자식 클래스가 부모 클래스의 멤버 자원을 자유롭게 사용
     *  - 자식 쪽에서 this로 부모 자원에 접근 가능
     *  - 부모 클래스에 직접 접근할 때는 this가 아닌, super를 사용함
     * @ 상속의 장점
     *  - 코드의 재사용성: 부모 자원을 재사용 및 재정의(오버라이드-Override)가능함
     *  - 중복성 제거: 공통으로 사용되는 변수 또는 메소드(기능)을 한곳에서 관리할 수 있음
     *               : 코드가 줄어듬
     *  - 공통된 자원을 사용하기 때문에 유지보수성이 매우 좋음
     */

    public static void main(String[] args) {
        System.out.println("[클래스 상속(Inheritance)]");
        System.out.println("\n----------------------------------------\n");

        // 부모 클래스 생성하기
        ParentBook pb = new ParentBook();
        
        // 자식 클래스 생성하기
        String name = "나의 라임오렌지 나무";
        int price = 13500;
        ChildBook cb = new ChildBook(name, price);

        name = "종의 기원";
        price = 19500;
        int year = 2025;
        ChildBook2 cb2 = new ChildBook2(name, price, year);

        //부모 객체를 이용해서 print함수 호출하기
        pb.printView();

        //자식 객체를 이용해서 print함수 호출하기
        cb.printView();

        //자식2 객체를 이용해서 print함수 호출하기
        cb2.printView();
    }
}

/***
 * 부모 클래스
 */
class ParentBook{
    //멤버 변수
    String name;
    int price;

    //출력하는 기능 메소드
    public void printView() {
        System.out.println("책의 이름과 가격: %s | %d".formatted(this.name, this.price));
    }
}


/***
 * 자식 클래스 (부모님의 자원을 상속받을 거에요.)
 */
class ChildBook extends ParentBook{
    /**
     * 생성자 메소드만 만들기
     * @param name
     * @param price
     */
    public ChildBook(String name, int price){
        // - 전달받은 name과 price의 값을 
        // => 부모님의 자원에, 즉 멤버 변수인 name과 price에 저장시키기
        // - 부모 클래스의 자원을 자유롭게 사용하기 위해서는
        //  -> 상속을 받아야 합니다.
        //     (상속을 class 선언 시 부모로부터 상속(extends)받는다는 정의를 해야 합니다.)
        this.name = name;
        this.price = price;
    }
}



/***
 * 자식 클래스2 (부모님의 자원을 상속받을 거에요.)
 */
class ChildBook2 extends ParentBook{
    int year;
    /**
     * 생성자 메소드만 만들기
     * @param name
     * @param price
     */
    public ChildBook2(String name, int price, int year){
        // - 전달받은 name과 price의 값을 
        // => 부모님의 자원에, 즉 멤버 변수인 name과 price에 저장시키기
        // - 부모 클래스의 자원을 자유롭게 사용하기 위해서는
        //  -> 상속을 받아야 합니다.
        //     (상속을 class 선언 시 부모로부터 상속(extends)받는다는 정의를 해야 합니다.)
        this.name = name;
        this.price = price;
        this.year = year;
    }
}