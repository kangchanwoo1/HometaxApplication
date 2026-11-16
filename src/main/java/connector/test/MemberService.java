package connector.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    // 회원가입 로직 처리 메서드
    public boolean registerMember(MemberDTO member) {
        // 나중에 여기에 비밀번호 암호화(BCrypt 등) 로직이 들어갈 수 있습니다.
        int result = memberDAO.insertMember(member);
        
        // INSERT가 성공하여 1개 이상의 행이 추가되었다면 true 반환
        return result > 0;
    }
}
