package com.pknu202502.java_base.day56_java_base_05;

import com.pknu202502.java_base.day56_java_base_05.day5601.AppConstants;
import com.pknu202502.java_base.day56_java_base_05.day5601.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * 컬렉션 프레임워크
 *  - 컬렉션 인터페이스 객체    : List,      Set,     Map
 *  - 컬렉션 실제 사용되는 객체 : ArrayList, HashSet, HashMap
 */
public class Day5602_Collection_Framework {

    /**
     * <컬렉션 객체>
     *  - 여러개의 값(변수, 객체)들을 저장하기 위한 객체
     *  - 컬렉션 객체에는 인터페이스 클래스와 실제 사용되는 클래스로 구분됩니다.
     *  - 인터페이스 클래스 : List, Set, Map
     *  - 실체 클래스 : List의 실체는 ArrayList, 
     *                : Set의 실체는 HashSet
     *                : Map의 실체는 HashMap
     *  - List : 순서대로 여러 데이터(변수, 객체)를 순서대로 저장 관리함
     *         : 중복된 값을 허용합니다
     *         : 인덱스 번호로 관리됨(배열과 유사)
     *         : 저장되는 갯수의 제한은 없습니다.(배열과 차이점)
     *  - Set  : List와 동일한하게 사용됨
     *         : (List와 차이점) 중복된 값은 자체적으로 제거합니다.
     *  - Map  : key와 value를 쌍으로 데이터로 관리됨(파이썬의 딕셔너리와 동일) 
     *         : key를 통해서 value값을 추출합니다.
     *         : 순서에 의미를 두지 않습니다.
     */

    public static void main(String[] args) {
        System.out.println("[컬렉션 프레임워크 : List, Set, Map]");
        System.out.println("----------------------------------------");

        /**
         * <List 인터페이스 및 ArrayList>
         *  - 작성 방법 : List<제네릭타입> 변수명 = new ArrayList<>();
         *  - 제네릭타입 : 어떠한 타입도 사용가능한 타입
         *  - <Object> : 어떤 타입이든 객체면 모두 담을 수 있도록 하겠다는 "의미적 타입".
         */
        List<Object> students = new ArrayList<>(); 
        students.add("홍길동1");
        students.add(new Person("홍길동2"));
        students.add(new AppConstants());

        System.out.println("학생 수 : " + students.size());

        // Person에 저장된 name값 출력해주세요...
        // - Object 타입으로 정의된 값 추출하는 방법
        // - 잘못된 사용예 : students[1]
        // - 객체를 추출할 경우에는 get(인덱스번호) 함수를 통해서 추출해야 합니다.
        // - 일반변수가 아닌 클래스 객체인 경우에는 추출한 결과를 원래 클래스로 형변환 해야함
        //   --> (원래클래스명)students.get(1);
        Person p = (Person)students.get(1);
        System.out.println("Person에서 이름 추출 : " + p.getName());

        // 문자열 타입의 List 객체 생성하기
        //  - 변수명 : s
        //  - 임의 이름 3개 넣어주세요..
        //  - 반복문 이용해서 값 추출해 주세요..
        List<String> s = new ArrayList<>();

        s.add("홍길동");
        s.add("이순신");
        s.add("김유신");

        // 삭제하기..
        s.remove(1);

        // 전체 리스트 메모리 비우기..
        s.clear();

        for (String name : s) {
            System.out.println("이름 : " + name);
        }

        /**
         * Set 인터페이스 클래스
         *  - 실체 클래스는 HashSet 사용
         *  - List와 동일하게 사용됨 
         *  - 순서를 따르지 않음..
         */
        Set<String> names = new HashSet<>();
        names.add("홍길동");
        names.add("이순신");
        names.add("이순신");
        names.add("이순신");
        names.add("김유신");
        
        System.out.println("전체 이름 정보 : " + names);
        System.out.println("전체 데이터 갯수 : " + names.size());

        for(String name : names) {
            System.out.println("이름 : " + name);
        }

        /**
         * Map 인터페이스 
         *  - 실체 클래스는 HashMap을 사용
         */
        // key는 문자열 타입, value는 정수타입(Integer 타입의 원형 클래스를 사용)
        Map<String, Integer> score = new HashMap<>();

        // 값을 추가할 때는 put()함수 사용
        score.put("홍길동", 90);
        score.put("김유신", 80);
        score.put("김유신", 45);
        score.put("이순신", 60);

        // 전체 데이터 정보 확인
        System.out.println("전체 데이터 정보 : " + score);

        // 전체 key값 정보 확인
        System.out.println("전체 key값 정보 : " + score.keySet());

        // 전체 value값 정보 확인
        System.out.println("전체 value값 정보 : " + score.values());

        // 전체 데이터 갯수
        System.out.println("전체 데이터 갯수 : " + score.size());

        // 반복문 이용해서 value값만 추출해 주세요...
        // - key를 이용해서 추출해 주세요..
        for (String key : score.keySet()) {
            System.out.println("점수 : " + score.get(key));
        }

        /**
         * 1. MemberInfo 클래스 생성하기 (아래 쪽에 생성해도 됩니다.)
         * 2. 멤버 변수 2개 선언만
         *    - 아이디(id) : 타입은 문자열, 외부에서 접근 못하게 처리
         *    - 패스워드(pw) : 타입은 문자열, 외부에서 접근 못하게 처리
         * 3. 나머지는 자유롭게 클래스 내에서 사용...
         * ----------------------------
         * <main 클래스에서 처리>
         *  1. MemberInfo 클래스 3개 생성
         *  2. 각각 생성한 클래스를 List에 담기(각각 생성할 때 마다 담기)
         *  3. 반복문을 이용하여 아이디와 패스워드 출력하기
         *    - "아이디 : ??? / 패스워드 : ???"
         */
        MemberInfo m1 = new MemberInfo("a001", "a001pw");
        MemberInfo m2 = new MemberInfo("b001", "b001pw");
        MemberInfo m3 = new MemberInfo("c001", "c001pw");

        List<MemberInfo> memList = new ArrayList<>();
        memList.add(m1);
        memList.add(m2);
        memList.add(m3);

        for (MemberInfo m : memList) {
            System.out.println("아이디 : %s / 패스워드 : %s".formatted(m.getId(), m.getPw()));
        }
    }
}

class MemberInfo{
    private String id;
    private String pw;

    public MemberInfo(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

    public String getId(){
        return this.id;
    }

    public String getPw(){
        return this.pw;
    }
}