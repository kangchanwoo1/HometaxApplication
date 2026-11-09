package connector.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import connector.test.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 1. 목록 조회: /board/list
    @GetMapping("/list")
    public List<BoardDTO> getBoardList() {
        System.out.println("📥 웹 브라우저에서 게시글 목록 조회 요청이 들어왔습니다!");
        return boardService.getBoardList();
    }

    // 2. 상세 조회: /board/detail?id=1
    @GetMapping("/detail")
    public BoardDTO getBoardDetail(@RequestParam("id") int boardId) {
        System.out.println("📥 웹 브라우저에서 " + boardId + "번 게시글 상세 조회 요청이 들어왔습니다!");
        return boardService.getBoardById(boardId);
    }

    // 3. 게시글 등록: /board/write
    @PostMapping("/write")
    public String writeBoard(@RequestParam("title") String title,
                               @RequestParam("content") String content,
                               @RequestParam("writer") String writer) {
        System.out.println("📥 웹 브라우저에서 글 등록 요청이 들어왔습니다!");
        boardService.insertBoard(title, content, writer);
        return "게시글 등록 성공! /board/list에서 확인해보세요.";
    }

    // 4. 게시글 삭제: /board/delete?id=1
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBoard(@RequestParam("id") int boardId) {
        System.out.println("📥 웹 브라우저에서 " + boardId + "번 글 삭제 요청이 들어왔습니다!");
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok("삭제 성공");
    }

    // 5. 게시글 수정: /board/update (PUT 방식)
    @PutMapping("/update")
    public ResponseEntity<String> updateBoard(@RequestParam("id") int boardId,
                                              @RequestParam("title") String title,
                                              @RequestParam("content") String content) {
        System.out.println("📥 웹 브라우저에서 " + boardId + "번 글 수정 요청이 들어왔습니다!");
        try {
            boardService.updateBoard(boardId, title, content);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("fail");
        }
    }
}