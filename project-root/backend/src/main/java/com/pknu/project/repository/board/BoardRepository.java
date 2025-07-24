package com.pknu.project.repository.board;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pknu.project.model.board.Board;

// **interface로 클래스 정의
// DB와 물리적인 처리(CRUD)를 진행함
//  - 처리결과 중 조회의 경우 model 클래스에 저장하는 역할도 담당
//  - 실제 물리적 처리와 model에 데이터를 setting하는 역할은 부모클래스(JpaRepository)가 담당함
//  - 부모클래스(JpaRepository)를 상속 받아야 합니다.
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
    // JpaRepository 기본 메서드를 사용하여 findAll(), findById(), save() 등 자동 구현됨

}
