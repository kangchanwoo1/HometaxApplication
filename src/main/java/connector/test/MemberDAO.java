package connector.test;

import org.springframework.beans.factory.annotation.Autowired;
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
}