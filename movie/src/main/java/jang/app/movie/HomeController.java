package jang.app.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("==== Controller ===");
        String a = "Test";
        Home test = homeService.test(a);
        System.out.println("test = " + test);

        List<Home> userList = homeService.findAllUsers();
        model.addAttribute("users", userList);

        return "home";
    }
}
