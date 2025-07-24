/**********************************************************************
                              [게시물 관리]
 **********************************************************************
 - 글 하나(BOARD)에 여러 개의 파일(BOARD_FILES)이 첨부될 수 있어야 함 (1:N 관계)
   -> 부모(board) -> 자식(board_files)
   
 - ID는 자동 증가 시퀀스를 내장 방식으로 제공 (Oracle의 IDENTITY 기능 사용)
   -> PRIMARY KEY는 GENERATED ALWAYS AS IDENTITY 사용
   -> GENERATED ALWAYS AS IDENTITY : 자동 증가값 사용
     --> Oracle DB가 내부적으로 Sequence를 생성하여 자동 증가 처리 수행함
***********************************************************************/

/*
 게시물 관리
*/
CREATE TABLE board (
    board_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- 게시글 고유 ID
    title VARCHAR2(255) NOT NULL,                             -- 게시글 제목
    content CLOB,                                              -- 게시글 내용
    writer VARCHAR2(100),                                      -- 작성자
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,            -- 작성일시
    updated_at TIMESTAMP                                       -- 수정일시
);

/*
 - 게시물 하나당 -> 파일 여러개 관리 테이블
  - 게시글 삭제 시 관련 파일도 자동 삭제 처리 (ON DELETE CASCADE)
*/
CREATE TABLE board_files (
    file_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,   -- 파일 고유 ID
    board_id NUMBER NOT NULL,                                  -- 게시글 ID (외래키)
    original_filename VARCHAR2(255) NOT NULL,                  -- 업로드된 원본 파일명
    stored_filename VARCHAR2(255) NOT NULL,                    -- 서버에 저장된 파일명
    mime_type VARCHAR2(100),                                   -- MIME 타입 (e.g., image/png)
    file_size NUMBER,                                          -- 파일 크기 (바이트)
    upload_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,           -- 업로드 시각
    CONSTRAINT fk_board_files_board FOREIGN KEY (board_id)
        REFERENCES board(board_id) ON DELETE CASCADE           -- 게시글 삭제 시 파일도 삭제
);

/*
 - 인덱스 추가 (성능 향상을 위함)
 - board_files 테이블을 board 테이블과 Join 없이 단독으로 사용하여
   -> board_files 테이블의 board_id 컬럼을 이용하여
      where 조건으로 사용할 경우에 빠른 검색(조회)을 위해 사용
*/
CREATE INDEX idx_board_files_board_id ON board_files(board_id);