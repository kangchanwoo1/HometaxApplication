package connector.test.service;

import connector.test.BoardDAO;
import connector.test.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 스프링이 이 클래스를 비즈니스 서비스로 인식하게 해주는 핵심 어노테이션
public class BoardService {

    @Autowired
    private BoardDAO boardDAO;

    /**
     * 게시글 목록 조회 로직
     */
    public List<BoardDTO> getBoardList() {
        // 비즈니스 로직(데이터 가공, 검증 등)이 필요하면 이 안에서 처리합니다.
        return boardDAO.getBoardList();
    }

    /**
     * 게시글 등록 로직
     */
    public void insertBoard(String title, String content, String writer) {
        // 간단한 입력값 검증 예시
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 비어있을 수 없습니다.");
        }
        
        boardDAO.insertBoard(title, content, writer);
    }
    public BoardDTO getBoardById(int id) {
        // 만약 DAO에도 이 메서드가 아직 없다면 DAO에도 만들어주어야 합니다.
        return boardDAO.getBoardById(id);
}
 // 삭제 비즈니스 로직 메서드 추가
    public void deleteBoard(int boardId) {
        System.out.println("⚙️ 서비스 계층: " + boardId + "번 글 삭제 요청을 처리합니다.");
        
        // 필요하다면 "이 글이 실제로 존재하는가?" 검증 로직을 넣을 수도 있습니다.
        
        boardDAO.deleteBoard(boardId);
}
    public void updateBoard(int boardId, String title, String content) {
    	System.out.println("⚙️ 서비스 계층: " + boardId + "번 글 수정 요청을 처리합니다.");
        // DAO에게 실제 DB 업데이트를 지시합니다.
        boardDAO.updateBoard(boardId, title, content);
    }
}