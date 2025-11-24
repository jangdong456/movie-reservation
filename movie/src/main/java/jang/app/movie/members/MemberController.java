package jang.app.movie.members;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String saveMember(MemberDTO memberDTO) {
        log.info("새로운 회원가입 요청 데이터: {}", memberDTO.toString());
        log.info(
                "아이디: {}, 비밀번호: {}, 전화번호: {}, 이름: {}",
                memberDTO.getLoginId(), memberDTO.getMemberPassword(),
                memberDTO.getMemberPhone(), memberDTO.getMemberName());
        memberService.createMember(memberDTO);
        return "redirect:/member/login";
    }
}
