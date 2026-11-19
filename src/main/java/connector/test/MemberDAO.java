package connector.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 회원가입 (INSERT) 메서드
    public int insertMember(MemberDTO member) {
        String sql = "INSERT INTO member (username, password, name) VALUES (?, ?, ?)";
        
        return jdbcTemplate.update(sql, 
                member.getUsername(), 
                member.getPassword(), 
                member.getName());
    }
 // 2. 로그인용 회원 조회 (SELECT) 메서드 추가 👇
    public MemberDTO findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM member WHERE username = ? AND password = ?";
        
        try {
            // 일치하는 회원이 있으면 MemberDTO 객체로 매핑해서 반환
            return jdbcTemplate.queryForObject(sql, 
                    new BeanPropertyRowMapper<>(MemberDTO.class), 
                    username, password);
        } catch (Exception e) {
            // 일치하는 회원이 없으면 예외(EmptyResultDataAccessException 등)가 발생하므로 null 반환
            return null;
        }
}
   
}