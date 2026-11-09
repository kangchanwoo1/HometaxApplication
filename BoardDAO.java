package connector.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

	public class BoardDAO {
//[CREATE] 게시글 등록 메서드 (INSERT)
		public void insertBoard(BoardDTO dto) {
    String sql = "INSERT INTO board (title, content, writer) VALUES (?, ?, ?)"; // VALUES -> 사용자가 입력할 데이터 자리

    // DBConnection 클래스를 빌려와서 커넥션을 맺음!
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, dto.getTitle());
        pstmt.setString(2, dto.getContent());
        pstmt.setString(3, dto.getWriter());

        pstmt.executeUpdate();
        System.out.println("🎉 게시글 등록 성공!");

    } catch (Exception e) {
        System.out.println("❌ 게시글 등록 실패...");
        e.printStackTrace();
    }
}

// [READ] 게시글 목록 조회 메서드 (SELECT)
public List<BoardDTO> getBoardList() {
    List<BoardDTO> list = new ArrayList<>();
    String sql = "SELECT board_id, title, content, writer, reg_date FROM board ORDER BY board_id DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            BoardDTO dto = new BoardDTO();
            dto.setBoardId(rs.getInt("board_id"));
            dto.setTitle(rs.getString("title"));
            dto.setContent(rs.getString("content"));
            dto.setWriter(rs.getString("writer"));
            dto.setRegDate(rs.getTimestamp("reg_date"));

            list.add(dto);
        }

    } catch (Exception e) {
        System.out.println("❌ 게시글 목록 조회 실패...");
        e.printStackTrace();
    }

    return list;
}
}