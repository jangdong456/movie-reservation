package jang.app.movie.screens;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScreenController {


    @GetMapping("/screen/seat")
    public String getScreen(
            @RequestParam("cinema") String cinema,
            @RequestParam("movie") String movie,
            @RequestParam("time") String time,
            @RequestParam("date") String date,
            Model model
    ) {
        // 1. 전달받은 4가지 핵심 정보를 Model에 담아 choiceSeat.jsp로 전달
        model.addAttribute("selectedCinema", cinema);
        model.addAttribute("selectedMovie", movie);
        model.addAttribute("selectedTime", time);
        model.addAttribute("selectedDate", date);

        // 2. (선택 사항) 좌석 선택에 필요한 추가 정보 (예: 해당 상영 스케줄의 ID, 상영관 좌석 배치도 등)
        //    이 로직에서 DB 조회를 통해 Schedule ID를 찾아 Model에 추가해야 합니다.

        return "/screen/choiceSeat";
    }
}
