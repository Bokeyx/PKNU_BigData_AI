package com.pknu.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name="cart")// 어떤 테이블과 매핑될 것이지를 정의합니다.(실제 테이블명을 정의합니다.)
@IdClass(CartPk.class) // 복합키 클래스 어노테이션에 정의하기
@NoArgsConstructor// Day6001_Member 클래스의 디폴트 생성자로 생성시키지(Lombok 디펜던시 사용)
@AllArgsConstructor// 모든 멤버변수(필드라고 치함)를 매개변수로 받는 전체 생성자 생성(Lombok 디펜던시 사용)
@Getter// getter 자동 생성(Lombok 디펜던시 사용)
@Setter// setter 자동 생성(Lombok 디펜던시 사용)
@ToString// 모든 getter의 출력결과는 문자열로 하고자 할 때..
// CartService.java에서 DTO를 Entity로, Entity를 DTO로 변환할 때 사용
@Builder
public class Cart {
    // 실제 테이블에서 고유한값을 가지는 PK 컬럼을 지정합니다.
    @Id
    @Column(name="cart_no")
    private String cart_no;

    @Id
    private String cart_prod;
    
    private String cart_member;
    private int    cart_qty;

    /**
     * Member 클래스와 관계 연결하기(pk = fk)...................
     */
     
    // ManyToOne : N : 1의 관계를 의미함(자식(Cart) : 부모(Member))
    // - FetchType.LAZY : 연관된 모델(엔티티)를 즉시 로딩(실행)하지 않고,
    //                  : 실제로 조인을 사용하는 시점에 로딩(실행)할 수 있도록 설정
    // - FetchType.EAGER : 연관된 모델(엔티티)를 서버 실행 시점에 즉시 로딩(실행) 한다는 의미
    //                   : 미리 연관된 모델(엔티티)들을 실행해 놓는 것을 의미함
    @ManyToOne(fetch=FetchType.LAZY)
    // name : 현재 모델(엔티티), 즉 자식 테이블의 FK 컬럼명 정의
    // referencedColumnName : 부모 테이블의 PK 컬럼명 정의
    // insertable : JPA가 Insert 시에 해당 컬럼을 포함(true)할지/제외(false)할지 설정 (조회 전용)
    // updatable : JPA가 Update 시에 해당 컬럼을 포함(true)할지/제외(false)할지 설정 (조회 전용)
    @JoinColumn(name="cart_member", referencedColumnName="mem_id", 
                insertable=false, updatable=false)
    private Day6001_Member member;

    /**
     * 상품 테이블과 관계 연결하기 ...............
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cart_prod", referencedColumnName="prod_id",
                insertable=false, updatable=false)
    private Prod prod;
}


        