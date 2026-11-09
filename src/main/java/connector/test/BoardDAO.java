package connector.test;

import java.util.List;

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
        String sql = "INSERT INTO board (title, content, writer) VALUES (?, ?, ?)";

        int result = jdbcTemplate.update(sql, dto.getTitle(), dto.getContent(), dto.getWriter());

        if (result > 0) {
            System.out.println("🎉 게시글 등록 성공!");
        } else {
            System.out.println("❌ 게시글 등록 실패...");
        }
    }

    // [READ] 게시글 목록 조회 메서드 (SELECT)
    public List<BoardDTO> getBoardList() {
        String sql = "SELECT board_id, title, content, writer, reg_date FROM board ORDER BY board_id DESC";

        // ResultSet 결과를 BoardDTO 객체로 매핑해줍니다.
        RowMapper<BoardDTO> rowMapper = (rs, rowNum) -> {
            BoardDTO dto = new BoardDTO();
            dto.setBoardId(rs.getInt("board_id"));
            dto.setTitle(rs.getString("title"));
            dto.setContent(rs.getString("content"));
            dto.setWriter(rs.getString("writer"));
            dto.setRegDate(rs.getTimestamp("reg_date"));
            return dto;
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
 // 특정 글 번호(boardId)에 해당하는 게시글 1개를 조회하는 메서드
    public BoardDTO getBoardById(int boardId) {
        String sql = "SELECT board_id, title, content, writer, reg_date FROM board WHERE board_id = ?";
        
        return jdbcTemplate.queryForObject(sql, new Object[]{boardId}, (rs, rowNum) -> {
            BoardDTO board = new BoardDTO();
            board.setBoardId(rs.getInt("board_id"));
            board.setTitle(rs.getString("title"));
            board.setContent(rs.getString("content"));
            board.setWriter(rs.getString("writer"));
            board.setRegDate(rs.getTimestamp("reg_date")); // 아까 수정했던 세터 이름(setRegDate) 확인!
            return board;
        });
    }
    public void insertBoard(String title, String content, String writer) {
        // DB의 board 테이블에 데이터를 삽입하는 SQL 쿼리
        String sql = "INSERT INTO board (title, content, writer, reg_date) VALUES (?, ?, ?, NOW())";
        
        // JdbcTemplate을 이용해 쿼리 실행 (update는 데이터가 변할 때 사용합니다)
        jdbcTemplate.update(sql, title, content, writer);
        
        System.out.println("💾 DB에 새로운 게시글이 성공적으로 저장되었습니다!");
    }
    public void deleteBoard(int boardId) {
        String sql = "DELETE FROM board WHERE board_id = ?";
        
        // 물음표(?) 자리에 파라미터로 받은 boardId를 쏙 집어넣어 실행합니다.
        jdbcTemplate.update(sql, boardId);
        System.out.println("🗑️ DB에서 " + boardId + "번 게시글이 삭제되었습니다.");
    }
    public int updateBoard(int boardId, String title, String content) {
    	String sql = "UPDATE board SET title = ?, content = ? WHERE board_id = ?";
        return jdbcTemplate.update(sql, title, content, boardId);
    }

}