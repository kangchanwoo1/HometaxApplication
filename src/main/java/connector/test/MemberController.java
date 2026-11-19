package connector.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import connector.test.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 회원가입 요청 처리 (POST: /member/signup)
    @PostMapping("/signup")
    public String signup(@RequestBody MemberDTO member) {
        System.out.println("📥 회원가입 요청 들어옴 - 아이디: " + member.getUsername());
        
        boolean success = memberService.registerMember(member);
        
        if (success) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }
 // 로그인 요청 처리 (POST: /member/login) 👇
    @PostMapping("/login")
    public String login(@RequestBody MemberDTO member) {
        System.out.println("🔑 로그인 시도 - 아이디: " + member.getUsername());
        
        // 서비스 호출해서 회원 정보 조회
        MemberDTO loginUser = memberService.login(member.getUsername(), member.getPassword());
        
        if (loginUser != null) {
            System.out.println("✅ 로그인 성공: " + loginUser.getName() + "님 환영합니다.");
            return "SUCCESS";
        } else {
            System.out.println("❌ 로그인 실패: 아이디 또는 비밀번호 불일치");
            return "FAIL";
        }
}
}