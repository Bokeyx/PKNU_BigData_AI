package com.pknu202502.java_base.day57_java_base_06;

/***
 * 예외처리(Exception)
 */
public class Day5701_Exception_Handling {

/**
 * <예외처리(Exception Handling)>
 *  - 프로그램이 비정상적으로 종료되는 것을 방지하고,
 *  - 문제 발생 시 안전하게 처리하기 위해 필수적으로 사용되는 처리방법
 *  - Java에서는 특정 라이브러리(클래스)를 사용할 경우
 *   -> 예외처리를 필수적으로 강제하고 있음
 *  - 예외 처리 방법
 *   -> JDK 라이브러리에서 제공하는 예외 클래스 사용
 *   -> 사용자가 직접 정의하여 처리(사용자 정의 예외 클래스 작성하여 사용)
 *  - 예외 처리 발생 시점
 *   -> 오류(Error)는 프로그램 작성 시 발생하는 코드상의 문제를 의미함
 *      (JDK 라이브러리에서 제공하는 문법 규칙을 어겼을 때, 또는 오탈자 등등)
 *   -> 예외(Exception)는 실행 시점에 외부 요인에 의해 발생하는 문제를 의미함
 *  - 예외 처리가 필요한 시점 예시
 *   -> 파일이 존재하지 않을 경우에 대한 대비 처리
 *   -> DB 연결에 실패했을 경우에 대한 대비 처리
 *   -> 0으로 나누는 연산이 발생했을 경우에 대한 대비 처리
 *   -> 배열의 인덱스를 잘못 접근했을 경우에 대한 대비 처리
 *   -> 사용자 입력이 잘못되었을 때에 대한 -> 사용자 정의 예외 처리
 *   -> 기타 등등..
 *  - 예외 처리를 하는 최종 목적
 *   -> 서비스 또는 운영 중인 프록르매의 무중단(멈추지 않게) 시스템을 만들기 위해
 */

    public static void main(String[] args) {
        System.out.println("[예외처리(Exception)]");
        System.out.println("--------------------------------------------");

        /***
         * 예외 발생시켜보기
         *  - 0으로 나누기
         */
        // int result = 10 / 0;
        // System.out.println("[예외 발생] 숫자 0으로 나눌 때 발생 -> " + result);
        
        /**
         * 어떠한 예외가 발생할지 모를떄의 예외처리 방법
         *  - 최상위 예외 클래스는 Exception 클래스임
         *  - 어떤 오류가 발생할지 모를 경우에는 최상위 예외 클래스를 우선 적용합니다.
         *  - 또는 예외를 직접 발생시켜서 예외 클래스 이름을 확인합니다.
         *  - 예외 클래스는 catch(예외클래스){예외 처리 영역}
         *  - try{}: 예외가 발생한 것으로 예상되는 프로그램 일부분 try 블록(영역)으로 묶어서
         *         : 해당 영역에서 예외가 발생할 경우 catch 하겠다는 의미
         */
        
        try {
            int result = 10 / 0;
            System.out.println("[예외 발생] 숫자 0으로 나눌 때 발생 -> " + result);            
        } catch (Exception e) {
            System.out.println("<발생한 예외 확인하기(toString()): " + e.toString());
            System.out.println("<발생한 예외 확인하기(getMessage()): " + e.getMessage());
        }
        
        System.out.println("--------------------------------------------");
        
        /**
         * 어떤 예외가 발생할지를 알고 있는 경우
         *  - 즉, 사용할 예외 클래스를 알고 있는 경우를 의미함.
         */
        
        try {
            int result = 10 / 0;
            System.out.println("[예외 발생] 숫자 0으로 나눌 때 발생 -> " + result);            
        } catch (ArithmeticException e) {
            System.out.println("0으로 숫자를 나눌 수 없습니다. 정확한 정수값을 입력하여 주세요!!!");
            // 이때, 다른 처리하는 클래스의 메소드를 통해서 처리하고자 한다면, 호출도 가능..
            // 단, 별도의 처리가 필요함
        } catch (Exception e) {
        System.out.println("<위에 예외 이외 모두 확인하기(toString()): " + e.toString());
        System.out.println("<위에 예외 이외 모두 확인하기(getMessage()): " + e.getMessage());
        }
        
        /**
         * finally 사용하기
         *  - try{} catch(){} finally{}
         *  - finally: try 또는 catch 이후에 무조건 마지막에 처리할 것이 있는 경우 처리
         *           : 즉, 예외 발생 여부와 상관없이 항상 실행됩니다.
         *  - 보통 외부 자원과의 연결이 발생한 상태에서 예외가 발생한 경우...
         *   -> 자원을 강제적으로 반납해야하는 경우가 발생할 경우에 사용되는 경우가 많음
         */

        System.out.println("--------------------------------------------");


        try {
            int result = 10 / 0;
            System.out.println("[예외 발생] 숫자 0으로 나눌 때 발생 -> " + result);            
        } catch (ArithmeticException e) {
            System.out.println("0으로 숫자를 나눌 수 없습니다. 정확한 정수값을 입력하여 주세요!!!");
            // 이때, 다른 처리하는 클래스의 메소드를 통해서 처리하고자 한다면, 호출도 가능..
            // 단, 별도의 처리가 필요함
        } catch (Exception e) {
        System.out.println("<위에 예외 이외 모두 확인하기(toString()): " + e.toString());
        System.out.println("<위에 예외 이외 모두 확인하기(getMessage()): " + e.getMessage());
        } finally {
            System.out.println("<finally>항상 실행되는 영역입니다. ===예외처리 종료===");
        }

        System.out.println("--------------------------------------------");

        /**
         * <사용자 정의 예외 처리>
         *  - 사용자는 프로그래머(개발자)를 의미합니다.
         *  - 사용자가 예외를 직접 정의하여 사용하는 방법
         *    즉, 예외를 임의적으로 발생시킬 때 사용
         *  - 사용자가 예외 처리할 클래스를 만들어 놓아야 하빈다.
         *
         *  - 사용자 정의 예외 처리 시점
         *   -> 프로그램 중에 특정 조건에 만족할 경우 예외를 발생시킵니다.
         *    : 특정 조건: 조건문(if문)
         * 
         *  <사용 방법>
         *   1. 사용자 정의 예외 클래스를 선언 및 정의 하기
         *    - 클래스 이름은 사용자가 정합니다.
         *    - 최상위 예외 클래스를 상속(extends) 받아야 합니다.
         *    - 예외를 처리할 메소드(기능)을 선언 및 정의합니다.
         *     -> 처리할 예외 메세지를 부모(최상위 예외 클래스)에게 전던하여 처리..
         *   class UserException extends Exception {
         *      public UserException(String exceptionMsg) {
         *          super(exceptionMsg);
         *      }
         *   }
         *
         * 
         *   2. 프로그램 내에서 특정 조건일 때 예외 발생 시키기
         *    - 프로그램 작성 메소드 내에 조건(if)으로 주로 발생시킴
         *    - 메소드 선언시에 오른쪽에 throws 명령을 통해서 사용자 정의 예외 클래스(UserException) 정의
         *     -> throws(예외가 발생하면) -> 예외클래스(UserException)에게 전달하겠다는 의미적 선언
         *    - 메소드 내에서 예외 발생 시(조건에 만족할 경우) 처리 방법
         *     -> throw 명령을 통해서 사용자 정의 예외 클래스를 생성하면 됨
         *     -> 사용자 정의 예외 클래스로 던진다(throw)는 표현을 합니다.
         */

        // 나이 데이터 검증처리하기
        UserValidation uv = new UserValidation();
        
        // 예외를 발생시키기 때문에 try {} catch(사용자정의 예외 클래스) {} 정의
        try {
            uv.validateAge(150);
        } catch (UserDefineException e) {
            System.out.println("사용자 정의 예외가 발생하였습니다: " + e.toString());
            System.out.println("사용자 정의 예외가 발생하였습니다: " + e.getMessage());
        }
        
        System.out.println("예외 처리가 완료 되었습니다");
    }
}

/***
 * <사용자 정의 예외 클래스 선언 및 정의하기
 *  - 클래스 이름: UserDefineException
 *  - 처리 프로그램: 생성과 동시에 문자열을 받아서 부모클래스에게 넘길겁니다.
 */

class UserDefineException extends Exception {
    public UserDefineException(String Msg) {
        // 상속받은 Exception으로 메시지 전달
        super(Msg);
    }
}

/***
 * 나이 검증 처리를 위한 클래스 선언 및 정의하기
 *  - 클래스명: UserValidation
 *  - 생성자: 디폴트 생성자 사용
 *  - 멤버 메소드 선언 및 정의
 *   -> 메소드명: validateAge
 *   -> 리턴 없음, 매개변수는 정수값(나이) 정의
 *   -> 처리 프로그램
 *    1. 나이가 0보다 작거나, 130을 초과하는 경우에는 
 *      -> 사용자 정의 예외 처리
 *      -> 예외 메시지: "유효하지 않은 나이입니다. 다시 입력해 주세요."
 * 
 *    2. 나이가 정상인 경우에는
 *      -> "나이 입력 성공" 출력
 */

class UserValidation {
    public void validateAge(int age) throws UserDefineException{
        if (age < 0 || age > 130) {
            throw new UserDefineException("유효하지 않은 나이입니다. 다시 입력해 주세요.");
        } else {
            System.out.println("나이 입력 성공");
        }
    }
}