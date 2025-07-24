package com.pknu.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/***
 * 테이블 정보를 담을 클래스
 *  - getter 및 setter가 정의됨
 *  - 멤버 변수들만 private으로 정의함
 *    --> 멤버 변수의 이름은 실제 사용할 테이블의 컬럼명과 동일하게 합니다.
 *    --> 타입은 문자열(String), 숫자(int), 날짜(LocalDate), Blob or Clob(Clob)
 */

/**
 * VS-Code 확장팩 설치
 *  - Lombok Annotations Support for VS Code
 *  - @getter, @setter 지원
 */

// 데이터베이스의 member테이블과 매핑될 엔티티(모델) 클래스임을 정의합니다.
@Entity
@Table(name="member")// 어떤 테이블과 매핑될 것이지를 정의합니다.(실제 테이블명을 정의합니다.)
@NoArgsConstructor// Day6001_Member 클래스의 디폴트 생성자로 생성시키지(Lombok 디펜던시 사용)
@AllArgsConstructor// 모든 멤버변수(필드라고 치함)를 매개변수로 받는 전체 생성자 생성(Lombok 디펜던시 사용)
@Getter// getter 자동 생성(Lombok 디펜던시 사용)
@Setter// setter 자동 생성(Lombok 디펜던시 사용)
@ToString// 모든 getter의 출력결과는 문자열로 하고자 할 때..
public class Day6001_Member {
    // 실제 테이블에서 고유한값을 가지는 PK 컬럼을 지정합니다.
    @Id
    @Column(name="mem_id")
    private String mem_id;

    @Column(nullable=false)
    private String mem_pass;
    
    private String mem_name;
    private String mem_regno1;
    private String mem_regno2;

    // 날자타입 클래스
    private LocalDate mem_bir;

    private String mem_zip;
    private String mem_add1;
    private String mem_add2;
    private String mem_hometel;
    private String mem_comtel;
    private String mem_hp;
    private String mem_mail;
    private String mem_job;
    private String mem_like;
    private String mem_memorial;

    // 날자타입 클래스
    private LocalDate mem_memorialday;

    private int mem_mileage;
    private String mem_delete;

    /**
     * Cart(자식) 테이블과 연결(매핑)하기
     */
    @OneToMany(mappedBy="member")
    private List<Cart> carts = new ArrayList<>();
}
