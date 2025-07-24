package com.pknu.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pknu.project.model.Prod;

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
public interface ProdRepository extends JpaRepository<Prod, String>{
    /***
     * <기본적인 CRUD 메소드>
     *  - 전체 조회 : findAll()
     *  - 상세조회  : findById(pk값), PK를 이용한 조회
     *  - 입력 및 수정 : save(값들...)
     *  - 삭제 : deleteById(삭제할pk값)
     */
}
