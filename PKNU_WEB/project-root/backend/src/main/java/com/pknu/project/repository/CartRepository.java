package com.pknu.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pknu.project.dto.CartMemberDTO;
import com.pknu.project.dto.CartProdDTO;
import com.pknu.project.model.Cart;
import com.pknu.project.model.CartPk;

/***
 * 실제 데이터베이스에 요청을 수행하는 클래스
 *  - 입력/수정/삭제/조회 등의 세부작업을 수행하는 클래스 입니다.
 *  - Repository 클래스는 interface 클래스로 만들어야 합니다.
 *  - JpaRepository 상위 클래스를 상속받아서 사용하게 됩니다.
 *   -- JPA(Jakarta Persitence API or Java Persitence API)
 *     : 물리적인 DB 처리수행 및 모델클래스에 데이터 저장시키는 작업을 수행
 *     : CRUD 처리 -> 부모클래스에 CRUD 처리 메소드가 정의되어 있음
 *                 -> 부모의 메소드에 접근하여 호출하여 사용 
 *                 -> 재정의(Override)가 필요한 경우 재정의하여 사용
 */

// DB와 물리적인 처리(CRUD)를 진행함
//  - 처리결과 중 조회의 경우 model 클래스에 저장하는 역할도 담당
//  - 실제 물리적 처리와 model에 데이터를 setting하는 역할은 부모클래스(JpaRepository)가 담당함
//  - 부모클래스(JpaRepository)를 상속 받아야 합니다.
@Repository
// public interface CartRepository extends JpaRepository<Cart, String>{
public interface CartRepository extends JpaRepository<Cart, CartPk>{
    /***
     * <기본적인 CRUD 메소드>
     *  - 전체 조회 : findAll()
     *  - 상세조회  : findById(pk값), PK를 이용한 조회
     *  - 입력 및 수정 : save(값들...)
     *  - 삭제 : deleteById(삭제할pk값), delete(모델)
     */
    
    /**
     * <JPQL>
     * 주문내역과 회원정보를 조회하기 위한 메소드 정의하기
     *  - 조회 결과를 DTO에 담는 역할을 수행
     */
    @Query("Select new com.pknu.project.dto.CartMemberDTO(c.cart_no, c.cart_prod, c.cart_qty, m.mem_id, m.mem_name)" + 
           "From Cart c Inner Join c.member m")
    List<CartMemberDTO> findCartMemberData();
    
    /**
     * 주문내역과 상품정보를 조회하기 위한 메소드 정의하기
     * @return
     */
    @Query("SELECT new com.pknu.project.dto.CartProdDTO(" +
           "c.cart_no, c.cart_prod, c.cart_qty, c.cart_member, " +
           "p.prod_name, p.prod_price, p.prod_sale) " +
           "FROM Cart c INNER JOIN Prod p ON c.cart_prod = p.prod_id")
    List<CartProdDTO> findAllWithProduct();

    /**
     * 주문내역과 상품정보를 조회하기 위한 메소드 정의하기
     * 단, 전달 받은 상품코드에 대해서만 조회
     * @return
     */
    @Query("SELECT new com.pknu.project.dto.CartProdDTO(" +
           "c.cart_no, c.cart_prod, c.cart_qty, c.cart_member, " +
           "p.prod_name, p.prod_price, p.prod_sale) " +
           "FROM Cart c INNER JOIN Prod p ON c.cart_prod = p.prod_id " +
           "Where p.prod_id = :prodId")
    List<CartProdDTO> findByProdId(String prodId);

    /**
     * 주문정보, 회원정보, 상품정보 조회하기
       - 조회컬럼 : 주문번호, 회원아이디, 주문상품아이디, 주문수량, 
                     회원이름, 회원이메일, 
                     상품명, 상품판매가격
       - 조건 없음
       - DTO 클래스 이름 : CartMemberProdDTO
       - Repository 메소드 이름 : findCartMemberProdJoinAll()

       private String cartNo;
    private String cartProd;
    private int cartQty;
    private String cartMember;

    private String memberName;
    private String memberEmail;

    private String prodName;
    private String prodSale;
     */
    @Query("SELECT new com.pknu.project.dto.CartMemberProdDTO(" +
           "c.cart_no, c.cart_prod, c.cart_qty, c.cart_member, " +
           "m.mem_name, m.mem_mail, " + 
           "p.prod_name, p.prod_sale) " +
           "FROM Cart c INNER JOIN Prod p ON c.cart_prod = p.prod_id " + 
           "INNER JOIN Day6001_Member m ON c.cart_member = m.mem_id")
    List<CartProdDTO> findCartMemberProdJoinAll();
} 
