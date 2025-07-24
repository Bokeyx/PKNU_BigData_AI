package com.pknu202502.java_base.day55_java_base_04.src;

/***
 * 인터페이스(interface) 클래스
 */
public class Day5502_Interface {

    /**
     * <인터페이스(interface) 클래스>
     *  - 추상클래스의 개념을 그대로 적용합니다.
     *  - 일반 멤버(변수, 메소드), 즉 선언은 가능하지만,
     *    정의(실체가 없음)하여 사용할 수 없습니다.
     *  - 메소드 선언만 가능합니다.
     *  - 인터페이스 클래스를 -> 기능(메소드)을 모아놓은 클래스라고 해서, 
     *                            기능클래스라고도 합니다.
     *  - 기능만 모아 놓았으나, 선언만하며, 정의는 하지 않습니다.
     *  - 자식 클래스에서 모든 추상 메소드는 무조건 재정의(Override)하여 사용해야 합니다.
     *  
     *  - 인터페이스 내에 메소드 생성 규칙
     *    -> 일반 멤버 변수처럼 선언만 하면 됩니다.
     *    -> 일반 멤버 변수는 사용하지 않습니다.
     * 
     *  - 인터페이스 클래스 생성 방법
     *    : 클래스 선언 시 왼쪽에 interface 명칭을 붙입니다.
     *    : interface 클래스에는 class명을 붙이지 않습니다.(문법 입니다.)
     *      -> 자동으로 추상클래스의 개념이 적용된 class로 생성이 됩니다.
     *      -> 완전체가 아니기 때문에 class로 정의하지는 않습니다.
     *      -> 자식쪽에서 완전체를 만들어서 재정의해야 사용가능한 클래스 입니다.
     *    : interface 클래스명{
     *        // 메소드 선언 시 왼쪽에는 아무것도 없이 일반 메소드 선언 방법 따름
     *        //  - 인터페이스 클래스 내에 있는 모든 메소드는 
     *               --> 자동으로 추상메소드가 됩니다.(abstract 생략)
     *        void method1();
     *        String method2();
     *      }
     *  
     *  - 자식이 인퍼페이스 클래스 연결 방법
     *    : 클래스 선언 시 오른쪽에 implements 붙인 후 추상클래스명을 붙입니다.
     *    : public class test implements 추상클래스명{}
     * 
     *  - 사용 시점
     *    : 주로 기능(메소드) 위주의 설계가 필요한 경우에 사용함
     *    : 기능(메소드 이름과 처리 개념(메소드 성격))을 약속한 후,
     *      프로그램 개발자들이 약속한 기능의 이름과 성격에 맞게 
     *      재정의(Override)하여 사용
     */

    public static void main(String[] args) {
        System.out.println("[인터페이스(interface) 클래스]");
        System.out.println("---------------------------------");

        // 자식 클래스 생성하기
        Bird bird = new Bird();

        // fly 메소드 호출
        bird.fly();

        // 전체 조회 메소드 호출
        String[] msg = bird.getMemberAllSelect();
        System.out.println("전체 조회 메소드 호출 : %s".formatted(msg[1]));
    }
}

/***
 * <인터페이스 클래스 정의하기>
 *  - 클래스 이름 : Flyable
 *  - 추상메소드명 : fly (리턴 없음, 매개변수 없음)
 */
interface Flyable{
    void fly();

    // 저장하기 메소드
    int setMemberInsert();

    // 수정하기 메소드
    int setMemberUpdate();

    // 삭제하기 메소드
    int setMemberDelete();

    // 전체 조회하기 메소드
    String[] getMemberAllSelect();

    // 한건 조회하기 메소드
    String[] getMemberOneSelect(int pk);
}

/***
 * <자식 클래스 정의하기>
 *  - 클래스명 : Bird
 *  - 인터페이스 클래스를 연결하기
 *  - fly 메소드 재정의하기
 *    -> 처리 프로그램은 : "새가 날아가요~~훨~~훨~~" 출력만 하기..
 */
class Bird implements Flyable{
    // 인터페이스 클래스의 fly() 메소드 재정의하기
    @Override
    public void fly(){
        System.out.println("새가 날아가요~~훨~훨~~");
    }

    // 저장하기 메소드
    @Override
    public int setMemberInsert(){
        return 1;
    }

    // 수정하기 메소드
    @Override
    public int setMemberUpdate(){
        return 1;
    }

    // 삭제하기 메소드
    @Override
    public int setMemberDelete(){
        return 1;
    }

    // 전체 조회하기 메소드
    @Override
    public String[] getMemberAllSelect(){
        String[] msg = {"test1", "test2", "test3"};
        return msg;
    }

    // 한건 조회하기 메소드
    @Override
    public String[] getMemberOneSelect(int pk){
        String[] msg = {"test4", "test5", "test6"};
        return msg;
    }
}