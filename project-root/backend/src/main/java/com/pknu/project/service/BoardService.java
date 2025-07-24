package com.pknu.project.service;

// import static org.junit.jupiter.api.DynamicTest.stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pknu.project.dto.board.BoardDTO;
import com.pknu.project.dto.board.BoardFileDTO;
import com.pknu.project.exception.ResourceNotFoundException;
import com.pknu.project.model.board.Board;
import com.pknu.project.model.board.BoardFile;
import com.pknu.project.repository.board.BoardFileRepository;
import com.pknu.project.repository.board.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 서비스클래스임을 정의함
@Service
@RequiredArgsConstructor // 서버실행시에 미리 생성해야하는 생성자를 호출하여 생성시킴
@Slf4j // 터미널 창에 진행중인 로그 남기기
public class BoardService {

    
    // Repository 클래스 선언만 해놓기, final로 선언
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    
    // 파일이 저장될 경로 지정
    private final String FILE_UPLOAD_DIR = "uploads/board";
    
    // 생성자 주입
    // @Autowired
    // public BoardService(BoardRepository boardRepository, 
                            // BoardFileRepository boardFileRepository) {
    //     this.boardRepository = boardRepository;
            // this.boardFileRepository = boardFileRepository;
    // }

    // DTO를 Entiry로 변환하는 메소드 정의
    private Board convertToEntity(BoardDTO dto){
        // .builder() : Cart 클래스에서 정의한 Builder 클래스 자원 활용(생성자)
        return Board.builder()
                .board_id(dto.getBoard_id())
                .title(dto.getTitle())
                .writer(dto.getWriter())
                .content(dto.getContent())
                .created_at(dto.getCreated_at())
                .updated_at(dto.getUpdated_at())
                .build();
    }

    // Entiry를 DTO로 변환하는 메소드 정의
    private BoardDTO convertToDTO(Board board){
        // .builder() : Cart 클래스에서 정의한 Builder 클래스 자원 활용(생성자)
        return BoardDTO.builder()
                .board_id(board.getBoard_id())
                .title(board.getTitle())
                .writer(board.getWriter())
                .content(board.getContent())
                .created_at(board.getCreated_at())
                .updated_at(board.getUpdated_at())
                .build();
    }

    // DTO를 Entiry로 변환하는 메소드 정의
    private BoardFile convertToEntityFile(BoardFileDTO dto){
        // .builder() : Cart 클래스에서 정의한 Builder 클래스 자원 활용(생성자)
        return BoardFile.builder()
                .file_id(dto.getFile_id())
                .board(dto.getBoard())
                .original_filename(dto.getOriginal_filename())
                .stored_filename(dto.getStored_filename())
                .mime_type(dto.getMime_type())
                .file_size(dto.getFile_size())
                .upload_time(dto.getUpload_time())
                .build();
    }

    // Entiry를 DTO로 변환하는 메소드 정의
    private BoardFileDTO convertToDTOFile(BoardFile board){
        // .builder() : Cart 클래스에서 정의한 Builder 클래스 자원 활용(생성자)
        return BoardFileDTO.builder()
                .file_id(board.getFile_id())
                .board(board.getBoard())
                .original_filename(board.getOriginal_filename())
                .stored_filename(board.getStored_filename())
                .mime_type(board.getMime_type())
                .file_size(board.getFile_size())
                .upload_time(board.getUpload_time())
                .build();
    }

    /**
     * 전체 게시글 목록 조회
     * @return BoardDTO 리스트
     */
    public List<BoardDTO> getBoardList() {
        // 모든 게시글을 DB에서 조회하고, DTO로 변환하여 리턴
        return boardRepository.findAll()
                // - stream() 메소드
                //   : 컬렉션(Collection) 또는 배열(Array)들을
                //      -> 함수형 방식으로 처리할 수 있도록 해주는 자원이 있음
                //      -> 함수형 방식을 사용하겠다는 의미적 선언임
                //   : 문자열 스트리밍(streaming)과는 무관함
                .stream()
                // 함수형 방식 시작
                //  - map()함수 내에 처리하고자하는 메소드(기능) 정의
                //  - 실무에서 가장 많이 사용되는 방식(DTO <-> Entity)
                // - this::는 현재 클래스 내에 있는 멤버를 의미합니다.
                // - fineAll()의 결과는 List<Cart>로 리스트 내에 있는 
                //   -> 모든 Board들을 DTO로 변환하는 작업을 합니다.
                .map(this::convertToDTO)
                // - 변환된 모든 DTO들을 List로 다시 묶는 작업을 합니다.
                .collect(Collectors.toList());
    }

    /**
     * 신규 게시글 저장하기
     */
    /**
     * 게시글 저장 + 다중 파일 업로드 처리
     */
    /**
     * 게시글 저장 + 다중 파일 업로드 처리
     */
    public void setBoard(String title, String content, String writer, 
                            List<MultipartFile> files) throws Exception {
        // 1. 게시글 엔티티 생성 및 저장
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setWriter(writer);
        board.setCreated_at(LocalDateTime.now());
        Board savedBoard = boardRepository.save(board); // board_id 생성됨

        // 2. 업로드된 파일이 있을 경우 처리
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // 저장할 파일명 UUID + 확장자
                    String originalName = file.getOriginalFilename();
                    String fileExtension = originalName.substring(originalName.lastIndexOf("."));
                    String storedName = UUID.randomUUID().toString() + fileExtension;

                    // 저장할 디렉토리 경로 생성
                    File dir = new File(FILE_UPLOAD_DIR);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    // 실제 파일 저장
                    Path savePath = Paths.get(FILE_UPLOAD_DIR, storedName);
                    Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);

                    // board_files 테이블에 저장
                    BoardFile boardFile = new BoardFile();
                    boardFile.setBoard(savedBoard); // 외래키 연결
                    boardFile.setOriginal_filename(originalName);
                    boardFile.setStored_filename(storedName);
                    boardFile.setMime_type(file.getContentType());
                    
                    boardFileRepository.save(boardFile);
                }
            }
        }
    }


    /***
     * 게시물 상세정보 조회
     * @param board_id
     * @return
     */
    public BoardDTO getBoardWithFiles(Long board_id) {
        // board 데이터 조회하기
        Board board = this.boardRepository.findById(board_id)
                        // 조회 결과가 있으면, Entity를 반환하고, 
                        // 조회 결과가 없으면, 예외를 발생시킴
                        .orElseThrow(() -> new ResourceNotFoundException
                            ("### [해당 정보가 존재하지 않습니다.] : getBoardWithFiles() ###".formatted(board_id)));

        List<BoardFileDTO> fileDTOList = board.getFiles().stream().map(file -> {
            BoardFileDTO fileDTO = new BoardFileDTO();
            fileDTO.setFile_id(file.getFile_id());
            fileDTO.setOriginal_filename(file.getOriginal_filename());
            fileDTO.setStored_filename(file.getStored_filename());
            fileDTO.setFile_size(file.getFile_size());
            fileDTO.setMime_type(file.getMime_type());
            return fileDTO;            
        }).collect(Collectors.toList());

        BoardDTO boardDTO = convertToDTO(board);
        boardDTO.setFiles(fileDTOList);

        return boardDTO;
    }

    /***
     * 파일 한건 상세정보 조회하기 (파일 한건 다운로드시 사용)
     * @param file_id
     * @return
     */
    public BoardFileDTO getBoardFile(Long file_id) {
        BoardFile boardFile = this.boardFileRepository.findById(file_id)
                        // 조회 결과가 있으면, Entity를 반환하고, 
                        // 조회 결과가 없으면, 예외를 발생시킴
                        .orElseThrow(() -> new ResourceNotFoundException
                            ("### [해당 정보가 존재하지 않습니다.] : getBoardFile() ###".formatted(file_id)));
        
        return convertToDTOFile(boardFile);
    }


    /***
     * 파일 여러건 조회하기 (파일 여러건 다운로드시 사용)
     * @param board_id
     * @return
     */
    public List<BoardFileDTO> getBoardFilesByBoardId(Long board_id) {
        
        return boardFileRepository.findAll()
                .stream()
                .filter(f -> f.getBoard().getBoard_id().equals(board_id))
                .map(this::convertToDTOFile)
                .collect(Collectors.toList());
    }


    /***
     * 게시물 수정하기 + 파일 수정
     * @param board_id
     * @param boardDTO
     * @param files
     * @param deleteFileIds
     * @throws IOException
     */
    public void setUpdateBoard(Long board_id, BoardDTO boardDTO, 
                               MultipartFile[] files, 
                               List<Long> deleteFileIds) throws IOException {
        
        // 수정할 게시물 조회하기
        Board board = boardRepository.findById(board_id)
                        .orElseThrow(() 
                            -> new RuntimeException("수정할 데이터가 없습니다."));

        // 1. 게시글 내용 수정
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        boardRepository.save(board);


        // 2. 삭제할 파일 ID 목록이 있다면 삭제 처리
        if (deleteFileIds != null) {
            for (Long file_id : deleteFileIds) {
                Optional<BoardFile> optionalFile = boardFileRepository.findById(file_id);
                if (optionalFile.isPresent()) {
                    BoardFile file = optionalFile.get();
                    Files.deleteIfExists(Paths.get(FILE_UPLOAD_DIR)
                                              .resolve(file.getStored_filename()));
                    boardFileRepository.deleteById(file_id);
                }
            }
        }

        // 3. 새 파일 업로드
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String originalFileName = file.getOriginalFilename();
                    String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                    String mimeType = file.getContentType();

                    Path uploadPath = Paths.get(FILE_UPLOAD_DIR);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    Files.copy(file.getInputStream(), 
                                uploadPath.resolve(storedFileName), 
                                StandardCopyOption.REPLACE_EXISTING);

                    BoardFile boardFile = new BoardFile();
                    boardFile.setOriginal_filename(originalFileName);
                    boardFile.setStored_filename(storedFileName);
                    boardFile.setMime_type(mimeType);
                    boardFile.setBoard(board);

                    boardFileRepository.save(boardFile);
                }
            }
        }
    }


    /**
     * 삭제 처리
     * @param board_id
     * @throws IOException
     */
    @Transactional
    public void deleteBoard(Long board_id) throws IOException {
         // board 데이터 조회하기
        Board board = this.boardRepository.findById(board_id)
                        // 조회 결과가 있으면, Entity를 반환하고, 
                        // 조회 결과가 없으면, 예외를 발생시킴
                        .orElseThrow(() -> new ResourceNotFoundException
                            ("### [해당 정보가 존재하지 않습니다.] : getBoardWithFiles() ###".formatted(board_id)));

        List<BoardFileDTO> fileDTOList = board.getFiles().stream().map(file -> {
            BoardFileDTO fileDTO = new BoardFileDTO();
            fileDTO.setFile_id(file.getFile_id());
            fileDTO.setOriginal_filename(file.getOriginal_filename());
            fileDTO.setStored_filename(file.getStored_filename());
            fileDTO.setFile_size(file.getFile_size());
            fileDTO.setMime_type(file.getMime_type());
            return fileDTO;            
        }).collect(Collectors.toList());

        // 실제 파일 삭제
        for (BoardFileDTO file : fileDTOList) {
            Path path = Paths.get(FILE_UPLOAD_DIR).resolve(file.getStored_filename());
            Files.deleteIfExists(path); 
        }

        // DB에서 해당 게시물에 대한 파일 데이터 모두 삭제 처리
        List<BoardFile> boardFiles = fileDTOList
                                        .stream()
                                        .map(this::convertToEntityFile)
                                        .collect(Collectors.toList());
        boardFileRepository.deleteAll(boardFiles); 

        // 2. DB에서 해당 게시물에 대한 데이터 삭제
        boardRepository.delete(board);
    }
}
