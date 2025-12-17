package jang.app.movie.reservations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@Slf4j
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/bookingModal")
    public String getReservation() {
        return "template/bookingModal";
    }

    @PostMapping("reservation")
    public void postReservation(@RequestBody ReservationRequest request) {
        log.info(">>>>>>>>>>>>> Client로부터 요청 <<<<<<<<<<<<<<<<");
        ReservationDTO reservationDTO = request.toDTO();
        reservationService.postReservation(reservationDTO);
    }

    @GetMapping("/reservation/find")
    public ResponseEntity<Object> getFind(@RequestParam(name = "movieTitle") String movieTitle) {
        List<Object[]> result = reservationService.findReservation(movieTitle);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
