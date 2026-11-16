package connector.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
