package com.pknu.project.controller.board;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pknu.project.dto.board.BoardDTO;
import com.pknu.project.dto.board.BoardFileDTO;
import com.pknu.project.model.board.BoardFile;
import com.pknu.project.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor // 서버실행시에 미리 생성해야하는 생성자를 호출하여 생성시킴
public class BoardController {
    private final BoardService boardService;
    private final String FILE_DOWNLOAD_DIR = "uploads/board";

    // @Autowired
    // public BoardController(BoardService boardService) {
    //     this.boardService = boardService;
    // }

    /**
     * 전체 게시글 목록 조회
     * @return 게시글 리스트
     */
    @GetMapping(path="/list")
    public ResponseEntity<List<BoardDTO>> getBoardList() {
        List<BoardDTO> boards = boardService.getBoardList();
        return ResponseEntity.ok(boards); // HTTP 200 OK 와 함께 게시글 리스트 반환
    }


    /**
     * 신규 게시물 저장
     * @param boardDTO
     * @param files
     * @return
     */
    @PostMapping(path="/insert",
                 // * consumes : 이 컨트롤러 메서드가 처리할 수 있는 요청의 Content-Type을 지정
                 // * MediaType.MULTIPART_FORM_DATA_VALUE : 
                 //  - HTTP 요청의 Content-Type이 어떤 형식인인지를 명시적으로 지정
                 //  - 클라이언트에서 "multipart/form-data" 형식으로 전송시 사용용
                 //  - 보통 파일 업로드에 사용됩니다.
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createBoard(
            @RequestPart("title") String title,
            @RequestPart("content") String content,
            @RequestPart("writer") String writer,
            @RequestPart(name = "files", required = false) List<MultipartFile> files
    ) {
        try {
            boardService.setBoard(title, content, writer, files);
            return ResponseEntity.ok("게시글 작성 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시글 작성 실패: " + e.getMessage());
        }
    }


    /***
     * 게시물 상세조회
     * @param board_id
     * @return
     */
    @GetMapping(path="/view/{board_id}")
    public ResponseEntity<BoardDTO> getBoardView(@PathVariable("board_id") Long board_id) {

        // 서비스 클래스에게 상세조회 메소드 호출하여 처리시키기..       
        return ResponseEntity.ok(this.boardService.getBoardWithFiles(board_id));
    }


    /***
     * 파일 한건(개별) 다운로드
     * @param file_id
     * @return
     * @throws IOException
     */
    @GetMapping("/download/{file_id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long file_id) throws IOException {
        BoardFileDTO boardFileDTO = this.boardService.getBoardFile(file_id);

        Path filePath = Paths.get(FILE_DOWNLOAD_DIR)
                             .resolve(boardFileDTO.getStored_filename())
                             .normalize();

        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + URLEncoder.encode(boardFileDTO.getOriginal_filename(), 
                                                                       "UTF-8") + "\"")
                .body(resource);
    }


    /***
     * 전체 파일 한번에 zip파일로 다운로드 처리
     * @param board_id
     * @return
     * @throws IOException
     */
    @GetMapping("/download-all/{board_id}")
    public ResponseEntity<Resource> downloadAllFiles(@PathVariable Long board_id) throws IOException {
        List<BoardFileDTO> list_file_dto = this.boardService.getBoardFilesByBoardId(board_id);

        // ZIP 파일 임시 생성
        String zipFileName = "attachments_" + board_id + ".zip";
        Path zipPath = Files.createTempFile("board_", ".zip");

        // 개별 파일을 -> zip 파일로 묶기
        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            for (BoardFileDTO dto : list_file_dto) {
                
                Path filePath = Paths.get(FILE_DOWNLOAD_DIR).resolve(dto.getStored_filename());

                // 개별 파일이 있으면 zip 파일로 만들기
                if (Files.exists(filePath)) {
                    zipOut.putNextEntry(new ZipEntry(dto.getOriginal_filename()));
                    Files.copy(filePath, zipOut);
                    zipOut.closeEntry();
                }
            }
        }

        Resource resource = new UrlResource(zipPath.toUri());

        // 사용자 PC에 Download 하기
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zipFileName + "\"")
                .body(resource);
    }


    /***
     * 게시물 + 파일 수정 처리
     * @param board_id
     * @param boardDTO
     * @param files
     * @param deleteFileIds
     * @return
     * @throws IOException
     */
    @PutMapping(path="/update/{board_id}",
                 // * consumes : 이 컨트롤러 메서드가 처리할 수 있는 요청의 Content-Type을 지정
                 // * MediaType.MULTIPART_FORM_DATA_VALUE : 
                 //  - HTTP 요청의 Content-Type이 어떤 형식인인지를 명시적으로 지정
                 //  - 클라이언트에서 "multipart/form-data" 형식으로 전송시 사용용
                 //  - 보통 파일 업로드에 사용됩니다.
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateBoard(
                                @PathVariable Long board_id,
                                @RequestPart("board") BoardDTO boardDTO,
                                @RequestPart(value = "files", required = false) MultipartFile[] files,
                                @RequestParam(value = "deleteFileIds", required = false) List<Long> deleteFileIds
                                ) throws IOException {

        this.boardService.setUpdateBoard(board_id, boardDTO, files, deleteFileIds);
        return ResponseEntity.ok("ok");
    }


    /***
     * 삭제 처리하기
     * @param board_id
     * @return
     * @throws IOException
     */
    @DeleteMapping("/delete/{board_id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long board_id) 
                                                    throws IOException {
        boardService.deleteBoard(board_id); 
        return ResponseEntity.ok("ok"); 
    }

}
