package jang.app.movie.members;

import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/login")
    public String postLogin(MemberDTO memberDTO, HttpSession session) {
        log.info("==== 사용자 입력 아이디 값 : {} ", memberDTO.getLoginId());
        log.info("==== 사용자 입력 비밀번호 값 : {} ", memberDTO.getMemberPassword());

        boolean login = memberService.loginMember(memberDTO);
        log.info("======= 서비스 왔다가 return 값 : {}", login);
        if (login) {
            log.info("===== 로그인이 되었습니다 =====");
            session.setAttribute("loggedInId", memberDTO.getLoginId());
            return "redirect:/";
        } else log.info("==== 로그인 실패 =====");
        return "redirect:/member/login";
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

    @GetMapping("/logout") // 로그아웃 요청을 처리할 새로운 경로
    public String logout(HttpSession session) {

        // 🚨 세션에서 'loggedInId' 속성만 제거
        session.removeAttribute("loggedInId");

        // 로그아웃 후 메인 페이지로 리다이렉트
        return "redirect:/";
    }
}
