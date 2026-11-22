package connector.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import connector.test.BoardDAO;
import connector.test.BoardDTO;

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
        BoardDTO dto = new BoardDTO();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setWriter(writer);
        
        boardDAO.insertBoard(dto);
    }
    public BoardDTO getBoardById(int boardId) {
    	boardDAO.updateViewCount(boardId); // 1. 조회수 1 증가
        return boardDAO.getBoardById(boardId); // 2. 상세 정보 가져오기
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
    public BoardDTO likeBoard(int boardId) {
        int updated = boardDAO.updateLikeCount(boardId);
        if (updated == 0) {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다. id=" + boardId);
        }
        return boardDAO.getBoardById(boardId);
    }
    
}