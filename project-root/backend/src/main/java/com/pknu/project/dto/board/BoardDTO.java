package com.pknu.project.dto.board;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// getter, setter 모두 포함되어 있음
@Data
// 디폴트 생성자 생성
@NoArgsConstructor
// 모든 컬럼을 사용한 생성자 생성
@AllArgsConstructor
// CartService.java 내에서 DTO를 Entity로, Entity를 DTO로 변환할 때 사용
@Builder
public class BoardDTO {
    private Long board_id;             // 게시글 고유 ID
    private String title;             // 게시글 제목
    private String writer;            // 작성자 이름
    private String content;           // 게시글 내용
    private LocalDateTime created_at = LocalDateTime.now();;  // 작성일시
    private LocalDateTime updated_at = LocalDateTime.now();; // 수정일시
    private List<BoardFileDTO> files;
}
