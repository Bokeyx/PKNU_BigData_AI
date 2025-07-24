package com.pknu202502.java_base.day56_java_base_05;

import com.pknu202502.java_base.day56_java_base_05.day5601.AppConstants;
import com.pknu202502.java_base.day56_java_base_05.day5601.MathUtils;
import com.pknu202502.java_base.day56_java_base_05.day5601.Person;

/***
 * static 및 final
 */
public class Day5601_Static_Final {
    /**
     * <static>
     *  - 정적 바인딩이라고 칭함
     *  - static이라는 명령어를 변수 또는 메소드 왼쪽에 정의할 수 있음
     *    -> 정적변수, 정적메소드라고 칭합니다.
     *  - static으로 정의된 변수 또는 메소드는 
     *    - 실행 시점에 메모리에 자동으로 만들어집니다.
     *      (클래스를 생성하여 접근하지 않아도 됨...)
     *    - 클래스 생성 후 static 변수 또는 메소드에 접근은 가능하지만
     *      이렇게 사용하는 경우는 없음(의미가 없음)
     *  - 공통적으로 하나의 메모리를 공유하여 사용되어야 하는 경우에 주로 사용됨
     *  - 외부에서 static 변수의 값은 변경 가능
     *  - 외부에서 static 메소드의 재정의는 할 수 없음
     *    (자기 자신의 클래스에만 속한다고해서 정적, 
     *     즉 외부 클래스에서 재정의 불가) 
     *  - static 변수 및 메소드에 접근할 때는 
     *    -> 클래스이름.정적변수, 클래스이름.정적메소드 로 접근가능
     * 
     * <final>
     *  - 변수 또는 메소드에 대하여 변경할 수 없도록 처리하기 위해 사용
     *  - 변수의 경우 "상수"라고 칭하기도 합니다.
     *  - 보통 final이 붙는 변수의 이름은 모두 대문자로 작성합니다.(규칙은 아님)
     *  - 상수 변수는 외부에서 값을 변경 할 수 없습니다.
     *  - 상수 메소드의 경우에도 외부에서 재정의(Override) 할 수 없음
     *  - 클래스에도 final을 붙일 수 있으며,
     *    상수 클래스가 되는 경우에는 -> 상속 할 수 없음
     */

    public static void main(String[] args) {
        System.out.println("[static 및 final]");
        System.out.println("------------------------");

        /**
         * static이면서 final인 변수 사용하기
         *  - 객체 생성 없이 사용 :static 개념 적용
         *  - 클래스 이름으로 접근
         *  - 변수의 값 수정 불가 : final 개념 적용
         */
        // 프로그램 이름 변수데이터 추출
        String app_name = AppConstants.APP_NAME;
        System.out.println("프로그램 이름 : " + app_name);

        // 최대 사용자 수
        int max_user = AppConstants.MAX_USERS;
        System.out.println("최대 사용자 수 : " + max_user);

        // 데이터베이스 접속 드라이버 정보
        String db_user = AppConstants.DB_USER;
        System.out.println("데이터베이스 접속 드라이버 정보 : " + db_user);

        // 사이트 root 메일 계정
        String admin_mail = AppConstants.ADMIN_MAIL;
        System.out.println("사이트 Root 메일 계정 : " + admin_mail);

        // final로 선언된 변수의 값 수정해 보기 : 수정 불가
        // AppConstants.ADMIN_MAIL = "site@pknu.ac.kr";

        /**
         * static 메소드 사용하기
         *  - 객체 생성 없이 사용
         *  - 실제 클래스 이름으로 직접 접근
         *  - 외부 폴더에 MathUtils 클래스 생성하기
         *    --> add 메소드 생성 : 리턴타입 정수형, 
         *                        : 매개변수 정수타입으로 a, b 두개 사용
         *                        : 처리는 a와 b의 합한값 리턴
         *                        : 정적 메소드로 선언(final 사용 안함)
         *    --> square 메소드 생성  : 리턴타입 정수형, 
         *                        : 매개변수 정수타입으로 a 사용
         *                        : 처리는 a의 제곱한값 리턴
         *                        : 정적 메소드로 선언(final 사용 안함)
         */
        int rst = MathUtils.add(3, 5);
        System.out.println("정적 메소드 add 호출 : " + rst);

        rst = MathUtils.square(5);
        System.out.println("정적 메소드 square 호출 : " + rst);

        /**
         * final 메소드 사용하기
         *  - 클래스 새로 생성 : Person
         *  - 멤버 변수 name 선언 : 문자타입
         *  - 클래스 생성 시 name의 값을 정의합니다.(임의 값 사용)
         *  - final 메소드 선언 및 정의
         *    -> 메소드명 : getName  (name 변수의 값을 리턴 할 겁니다.)
         *  - 일반 멤버 메소드 선언 및 정의
         *    -> 메소드명 : setName  (name 변수에 임의값 넣을 겁니다.)
         */

        // 생성자를 통해서 name의 값 넣은 후, name 출력
        Person person = new Person("홍길동");
        System.out.println("name의 값 : " + person.getName());
        
        // setName 메소드를 통해서 name의 값 넣은 후, name 출력
        person.setName("이순신");
        System.out.println("name의 값 : " + person.getName());

        /***
         * 클래스를 생성해서 static 멤버들 접근하기
         */
        AppConstants ac1 = new AppConstants();
        System.out.println("[ac1으로 접근] : user_name = " + ac1.user_name);
        
        ac1.user_name = "이순신으로 수정";
        System.out.println("[ac1으로 접근] : user_name = " + ac1.user_name);
        
        System.out.println("[AppConstants으로 접근] : user_name = " + AppConstants.user_name);
        
        AppConstants ac2 = new AppConstants();
        System.out.println("[ac2으로 접근] : user_name = " + ac2.user_name);
        
        AppConstants.user_name = "AppConstants으로 접근해서 수정 했습니다.";
        System.out.println("[ac1으로 접근] : user_name = " + ac1.user_name);
        System.out.println("[ac2으로 접근] : user_name = " + ac2.user_name);


    }
}
