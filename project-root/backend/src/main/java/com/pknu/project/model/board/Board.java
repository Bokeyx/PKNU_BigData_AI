package com.pknu.project.model.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="board")// 어떤 테이블과 매핑될 것이지를 정의합니다.(실제 테이블명을 정의합니다.)
@NoArgsConstructor// Board 클래스의 디폴트 생성자로 생성시키지(Lombok 디펜던시 사용)
@AllArgsConstructor// 모든 멤버변수(필드라고 치함)를 매개변수로 받는 전체 생성자 생성(Lombok 디펜던시 사용)
@Getter// getter 자동 생성(Lombok 디펜던시 사용)
@Setter// setter 자동 생성(Lombok 디펜던시 사용)
@ToString// 모든 getter의 출력결과는 문자열로 하고자 할 때..
// CartService.java에서 DTO를 Entity로, Entity를 DTO로 변환할 때 사용
@Builder
public class Board {
    @Id
    // Oracle IDENTITY 컬럼 자동 증가 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long board_id;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime created_at = LocalDateTime.now();

    private LocalDateTime updated_at = LocalDateTime.now();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardFile> files = new ArrayList<>();;
}
