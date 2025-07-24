package com.pknu.project.model.board;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="board_files")// 어떤 테이블과 매핑될 것이지를 정의합니다.(실제 테이블명을 정의합니다.)
@NoArgsConstructor// Board 클래스의 디폴트 생성자로 생성시키지(Lombok 디펜던시 사용)
@AllArgsConstructor// 모든 멤버변수(필드라고 치함)를 매개변수로 받는 전체 생성자 생성(Lombok 디펜던시 사용)
@Getter// getter 자동 생성(Lombok 디펜던시 사용)
@Setter// setter 자동 생성(Lombok 디펜던시 사용)
@ToString// 모든 getter의 출력결과는 문자열로 하고자 할 때..
// CartService.java에서 DTO를 Entity로, Entity를 DTO로 변환할 때 사용
@Builder
public class BoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_id;

    // 게시글 외래키 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String original_filename;

    private String stored_filename;

    private String mime_type;

    private long file_size;

    private LocalDateTime upload_time = LocalDateTime.now();
}
