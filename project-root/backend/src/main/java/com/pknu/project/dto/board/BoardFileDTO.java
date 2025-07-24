package com.pknu.project.dto.board;

import java.time.LocalDateTime;

import com.pknu.project.model.board.Board;

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
public class BoardFileDTO {
    private Long file_id;             // 파일 고유 ID
    private Board board;             // 외래키(board 테이블의 board_id)
    private String original_filename;             // 원본 파일명
    private String stored_filename;            // 저장 파일명
    private String mime_type;           // 파일 확장자 타입
    private long file_size;  // 파일 사이즈
    private LocalDateTime upload_time = LocalDateTime.now(); // 수정일시
}
