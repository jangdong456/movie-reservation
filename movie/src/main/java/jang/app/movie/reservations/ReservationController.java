package jang.app.movie.reservations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ReservationController {

    @GetMapping("/bookingModal")
    public String getReservation() {
        return "template/bookingModal";
    }
}
