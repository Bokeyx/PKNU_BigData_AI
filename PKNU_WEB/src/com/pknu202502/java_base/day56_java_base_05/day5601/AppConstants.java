package com.pknu202502.java_base.day56_java_base_05.day5601;

/***
 * 모든 프로그램에서 사용할 수 있도록(전역)
 *  - static 적용
 *  
 * 수정 불가능하게 처리
 *  - final 적용
 */
public class AppConstants {
    /**
     * static을 사용하는 경우들..
     *  - 환경설정 값들을 정의하는 경우(DB 접속 정보 등..)
     *  - 특정 연산에 필요한 값의 범위 지정(min, max)
     *  - 공통으로 사용하는 사이트 주소(url의 도메인명)
     *  - 기타 등등
     */

    // 프로그램 이름(외부에서 변경 불가)
    //  - 선언과 동시에 최초에 값을 정의해야 합니다.
    public static final String APP_NAME = "MyJavaApp";

    // 최대 사용자 수
    public static final int MAX_USERS = 100;

    // 데이터베이스 접속 드라이버 정보
    public static final String DB_USER = "jdbc:oracle:thin:@localhost:1521:xe";

    // 사이트 root 메일 계정
    public static final String ADMIN_MAIL = "admin@pknu.ac.kr";

    // final이 아닌 static 변수 정의
    public static String user_name = "홍길동";
}
