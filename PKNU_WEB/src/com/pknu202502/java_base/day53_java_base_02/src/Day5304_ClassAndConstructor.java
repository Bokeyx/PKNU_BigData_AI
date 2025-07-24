package com.pknu202502.java_base.day53_java_base_02.src;

/***
 * 클래스 생성자 사용하기
 *  - 생성자 정의시 -> 오버로드(Overload) 개념을 적용할 수 있음
 */
public class Day5304_ClassAndConstructor {
    String carName;
    int carYear;

    int a;
    int b;
    int tot;
    /*
     * 멤버 변수 선언하기..
     * - 자동차 이름, 자동차 연식
     */

    /*
     * 생성자 정의하기
     *  - 자동차 이름과, 연식을 매개변수로 전달 받아서
     *  - 멤버 변수(자동차 이름과 연식)에 저장하기
     *  - **(중요) 매개변수 또는 프로그램 처리된 생성자가 작성된 경우에는 
     *             디폴트 생성자에 대한 처리는 사용할 수 없습니다.
     *             만약, 사용하자 한다면, 정의해야 합니다.
     */
    
    public Day5304_ClassAndConstructor(){}
    public Day5304_ClassAndConstructor(int a, int b){
        this.a = a;
        this.b = b;
        this.tot = a * b;
    }
    public Day5304_ClassAndConstructor(String carName, int carYear){
        // 멤버 메소드 내에서 멤버 변수에 접근할 때는 -> this를 사용
        // - 파이썬에서는 self를 사용했었음.
        this.carName = carName;
        this.carYear = carYear;
    }

    public static void main(String[] args) {
        System.out.println("클래스 생성자를 이용해서 멤버 변수에 값 정의하기");

        // 클래스 생성 후 자동차 이름과 년식을 출력해 주세요.
        Day5304_ClassAndConstructor dc1 = new Day5304_ClassAndConstructor();
        System.out.println("차량명[%s], 연식[%d]".formatted(dc1.carName, dc1.carYear));

        Day5304_ClassAndConstructor dc2 = new Day5304_ClassAndConstructor("GMC Sierra", 2016);
        System.out.println("차량명[%s], 연식[%d]".formatted(dc2.carName, dc2.carYear));

        /**
         * <클래스 내에서 처리>
         * 1. 멤버변수 1개 추가하기: 변수명 자유롭게
         * 2. 정수타입의 매개변수 2개를 전달 받아서,
         *    2개의 매개변수를 곱한 값을 멤버 변수에 저장하기
         * 
         * <main 메소드에서 처리>
         * 3. 곱한값을 출력하기
         */
        
        Day5304_ClassAndConstructor dc3 = new Day5304_ClassAndConstructor(12, 25);
        System.out.println("a x b = %d".formatted(dc3.tot));
        System.out.println("%d x %d = %d".formatted(dc3.a, dc3.b, dc3.a * dc3.b));

        
    }
}
