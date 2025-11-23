package jang.app.movie.Home;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@Tag(name = "회원관리")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("==== Controller ===");

        List<Home> userList = homeService.findAllUsers();
        model.addAttribute("users", userList);

        return "home";
    }

    @ResponseBody
    @Operation(summary = "회원 데이터 저장", description = "AJAX 통신으로 회원 정보를 저장합니다.")
    @PostMapping("/members")
    public void saveHome(@RequestBody HomeDTO homedto) {
        homeService.createMember(homedto);
    }
}
