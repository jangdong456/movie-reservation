package jang.app.movie.reservations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class ReservationController {

    @GetMapping("/bookingModal")
    public String getReservation() {
        return "template/bookingModal";
    }

    @PostMapping("reservation")
    public void postReservation(@RequestBody ReservationRequest request) {
        log.info(">>>>>>>>>>>>> Client로부터 요청 <<<<<<<<<<<<<<<<");
        // ⭐️ 전달받은 데이터를 확인합니다. ⭐️
        log.info("Member ID: {}", request.getMemberId());
        log.info("Showtime ID: {}", request.getShowtimeId());
        log.info("Total Price: {}", request.getTotalPrice());
        log.info("Selected Seats: {}", request.getSelectedSeats());
        log.info("Adult Count: {}", request.getPeopleCounts());
        log.info("Cinema: {}", request.getCinema());
        log.info("MovieTitle: {}", request.getMovieTitle());
        log.info("Screen: {}", request.getScreen());


    }
}
