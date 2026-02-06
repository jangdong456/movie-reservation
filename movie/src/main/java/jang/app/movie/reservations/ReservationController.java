package jang.app.movie.reservations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/reservation/total")
    public ResponseEntity<ReservationStats> getTotal(@RequestParam(name = "loginId") String loginId) {
        ReservationStats stats = reservationService.getTotal(loginId);
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/reservation/lock")
    @ResponseBody
    public String postLock(@RequestBody Map<String, String> requestData) {
        String seatId = requestData.get("seatId");

        // 서버 콘솔에 찍히는지 확인 (중요!)
        System.out.println("프론트에서 넘어온 좌석 ID: " + seatId);

        // 락 로직 없이 일단 성공 메시지만 반환
        return "Seat " + seatId + " received on server!";
    }

    @PostMapping("/reservation/unlock")
    @ResponseBody
    public String postUnLock(@RequestBody Map<String, String> requestData) {
        String seatId = requestData.get("seatId");

        // DB 로직: UPDATE SEATS SET STATUS = 'AVAILABLE', RESERVED_AT = NULL
        // WHERE SEAT_ID = #{seatId} AND STATUS = 'RESERVED'

        System.out.println("좌석 선점 취소: " + seatId);
        return "Unlocked";
    }
}
