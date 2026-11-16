package connector.test;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository // 스프링 부트가 이 클래스를 DAO 컴포넌트로 자동 등록합니다.
public class BoardDAO {

    @Autowired // application.properties 기반으로 생성된 DB 연결 객체를 자동으로 주입받습니다.
    private JdbcTemplate jdbcTemplate;

    // [CREATE] 게시글 등록 메서드 (INSERT)
    public void insertBoard(BoardDTO dto) {
    	String sql = "INSERT INTO board (title, content, writer, reg_date) VALUES (?, ?, ?, NOW())";

        int result = jdbcTemplate.update(sql, dto.getTitle(), dto.getContent(), dto.getWriter());

        if (result > 0) {
            System.out.println("🎉 게시글 등록 성공!");
        } else {
            System.out.println("❌ 게시글 등록 실패...");
        }
    }

    // [READ] 게시글 목록 조회 메서드 (SELECT)
    public List<BoardDTO> getBoardList() {
        String sql = "SELECT board_id, title, content, writer, reg_date, view_count FROM board ORDER BY board_id DESC";

        // ResultSet 결과를 BoardDTO 객체로 매핑해줍니다.
        RowMapper<BoardDTO> rowMapper = (rs, rowNum) -> {
            BoardDTO dto = new BoardDTO();
            dto.setBoardId(rs.getInt("board_id"));
            dto.setTitle(rs.getString("title"));
            dto.setContent(rs.getString("content"));
            dto.setWriter(rs.getString("writer"));
            dto.setRegDate(rs.getTimestamp("reg_date"));
            dto.setViewCount(rs.getInt("view_count"));
            return dto;
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
 // 1. 조회수만 1 증가시키는 메서드
    public void updateViewCount(int boardId) {
        String sql = "UPDATE board SET view_count = COALESCE(view_count, 0) + 1 WHERE board_id = ?";
        jdbcTemplate.update(sql, boardId);
    }

    // 2. 게시글 상세 정보를 조회하는 메서드 (SELECT)
    public BoardDTO getBoardById(int boardId) {
        String selectSql = "SELECT board_id, title, content, writer, reg_date, view_count FROM board WHERE board_id = ?";
        
        return jdbcTemplate.queryForObject(selectSql, (rs, rowNum) -> {
            BoardDTO board = new BoardDTO();
            board.setBoardId(rs.getInt("board_id"));
            board.setTitle(rs.getString("title"));
            board.setContent(rs.getString("content"));
            board.setWriter(rs.getString("writer"));
            board.setRegDate(rs.getTimestamp("reg_date"));
            board.setViewCount(rs.getInt("view_count")); // 👈 조회수 대입
            return board;
        }, boardId);
    }

    // 3. 게시글 삭제 메서드
    public void deleteBoard(int boardId) {
        String sql = "DELETE FROM board WHERE board_id = ?";
        jdbcTemplate.update(sql, boardId);
        System.out.println("🗑️ DB에서 " + boardId + "번 게시글이 삭제되었습니다.");
    }

    // 4. 게시글 수정 메서드
    public int updateBoard(int boardId, String title, String content) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE board_id = ?";
        return jdbcTemplate.update(sql, title, content, boardId);
    }
    
}