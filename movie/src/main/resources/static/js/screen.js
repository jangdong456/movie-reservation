function resetTotalPriceDisplay() {
        $('#total-price').text('0원');
    }

/**
 * 총 가격을 계산하고 화면에 업데이트합니다.
 * (이 함수는 로직이 복잡하므로 변경하지 않습니다.)
 */
function updateTotalPrice() {
    let totalPrice = 0;

    totalPrice += peopleCounts.adult * TICKET_PRICES.adult;
    totalPrice += peopleCounts.teen * TICKET_PRICES.teen;
    totalPrice += peopleCounts.senior * TICKET_PRICES.senior;
    totalPrice += peopleCounts.disabled * TICKET_PRICES.disabled;

    const formattedPrice = totalPrice.toLocaleString('ko-KR');
    $('#total-price').text(`${formattedPrice}원`);
}

// JS 파일의 $(document).ready(function() { ... }); 내부에 위치

    const MAX_PEOPLE = 8;
    // ⭐️ 인원별 가격 정의 (중복 제거) ⭐️
    const TICKET_PRICES = {
        adult: 12000,
        teen: 8000,
        senior: 5000,
        disabled: 5000
    };
    // ⭐️ 인원수 상태를 저장하는 객체 (중복 제거) ⭐️
    let peopleCounts = {
        adult: 0,
        teen: 0,
        senior: 0,
        disabled: 0
    };
    // ⭐️ 선택된 좌석 ID 목록 ⭐️
    let selectedSeats = [];

    // ----------------------------------------------------
    // ⭐️ 함수 정의 (중복 제거) ⭐️
    // ----------------------------------------------------

    function resetTotalPriceDisplay() {
        $('#total-price').text('0원');
    }

    function calculateTotalPeople() {
        return peopleCounts.adult + peopleCounts.teen + peopleCounts.senior + peopleCounts.disabled;
    }

    function updateTotalPrice() {
        let totalPrice = 0;

        totalPrice += peopleCounts.adult * TICKET_PRICES.adult;
        totalPrice += peopleCounts.teen * TICKET_PRICES.teen;
        totalPrice += peopleCounts.senior * TICKET_PRICES.senior;
        totalPrice += peopleCounts.disabled * TICKET_PRICES.disabled;

        const formattedPrice = totalPrice.toLocaleString('ko-KR');
        $('#total-price').text(`${formattedPrice}원`);
    }

// ----------------------------------------------------
// ⭐️ 인원 증감 버튼 클릭 이벤트 핸들러 (순서 수정) ⭐️
// ----------------------------------------------------
$(document).on('click', '.btn-plus, .btn-minus', function() {
    const $button = $(this);
    const type = $button.data('type');
    const $input = $(`.count-input[data-type="${type}"]`);

    let currentCount = peopleCounts[type];
    const totalPeople = calculateTotalPeople();

    let newCount = currentCount;
    let countChanged = false; // 실제로 인원수가 변경되었는지 체크

    if ($button.hasClass('btn-plus')) {
        if (totalPeople < MAX_PEOPLE) {
            newCount = currentCount + 1;
            countChanged = true;
        } else {
            alert(`인원은 최대 ${MAX_PEOPLE}명까지만 선택할 수 있습니다. (현재: ${totalPeople}명)`);
            return;
        }
    } else {
        if (currentCount > 0) {
            newCount = currentCount - 1;
            countChanged = true;
        } else {
            return;
        }
    }

    // ⭐️ 최종 상태 업데이트 ⭐️
    if (countChanged) {
        peopleCounts[type] = newCount;
        $input.val(newCount); // 화면의 input 값 업데이트

        // ⭐️ 인원수가 변경되었으므로 가격 초기화 및 좌석 강제 해제 ⭐️
        resetTotalPriceDisplay();
        selectedSeats = [];
        $('.seat-item.selected').removeClass('selected');

        // ⭐️ 인원수가 바뀌면 잠금 상태를 해제해야 합니다. ⭐️
        updateSeatAvailability();
    }

    console.log(`[${type}] 변경 후: ${newCount}명, 총 인원: ${calculateTotalPeople()}명`);
});


// ----------------------------------------------------
// ⭐️ 좌석 선택/해제 토글 로직 (유지) ⭐️
// ----------------------------------------------------
$(document).on('click', '.seat-item.available', function() {
    const $seat = $(this);
    const seatId = $seat.data('seat-id');
    const totalPeople = calculateTotalPeople();

    if (totalPeople === 0) {
        alert("먼저 인원수를 선택해 주세요.");
        return;
    }

    if ($seat.hasClass('selected')) {
        // 해제
        $seat.removeClass('selected');
        selectedSeats = selectedSeats.filter(id => id !== seatId);
        resetTotalPriceDisplay(); // 좌석이 해제되었으므로 가격 0원 초기화

    } else {
        // 선택
        if (selectedSeats.length < totalPeople) {
            $seat.addClass('selected');
            selectedSeats.push(seatId);

            // ⭐️ 선택이 완료되었을 때만 가격 계산 ⭐️
            if (selectedSeats.length === totalPeople) {
                updateTotalPrice();
            }
        } else {
            alert(`선택 인원(${totalPeople}명)을 초과하여 좌석을 선택할 수 없습니다.`);
        }
    }
    // ⭐️ 좌석 선택 상태 변경 후 잠금/해제 상태를 업데이트합니다. ⭐️
    updateSeatAvailability();
});

// 문서 로드 시 초기 가격 설정
resetTotalPriceDisplay();


// ⭐️ 새로 추가: 좌석 선택 완료 시 나머지 좌석을 잠그는 함수 ⭐️
function updateSeatAvailability() {
    const totalPeople = calculateTotalPeople();
    const selectedCount = selectedSeats.length;

    if (selectedCount === totalPeople && totalPeople > 0) {
        // 인원수만큼 선택이 완료되었을 경우:
        // 선택되지 않은 나머지 좌석(available 상태)을 잠금 상태(locked)로 변경합니다.
        $('.seat-item.available:not(.selected)')
            .addClass('locked') // 잠금 스타일 적용
            .removeClass('available') // available 상태 해제
            .prop('disabled', true); // 클릭 불가능하게 처리
    } else {
        // 인원수보다 적게 선택되었거나, 인원수가 0인 경우:
        // 모든 잠금 상태를 해제하고 available 상태로 되돌립니다.
        $('.seat-item.locked')
            .addClass('available')
            .removeClass('locked')
            .prop('disabled', false);
    }
}

// ----------------------------------------------------
    // ⭐️ 결제 버튼 클릭 이벤트 핸들러 ⭐️
    // ----------------------------------------------------
    $(document).on('click', '#payment-button', function() {
        const totalPeople = calculateTotalPeople();
        const selectedCount = selectedSeats.length; // 현재 선택된 좌석 수

        // 1. 인원수가 0명인지 체크 (예외 상황)
        if (totalPeople === 0) {
            alert("먼저 관람하실 인원수를 선택해 주세요.");
            return;
        }

        // 2. ⭐️ 좌석 선택 완료 여부 체크 (핵심 로직) ⭐️
        if (selectedCount === 0) {
            alert(`선택된 좌석이 없습니다. ${totalPeople}명분의 좌석을 선택해 주세요.`);
            return;
        }

        if (selectedCount !== totalPeople) {
            // 인원수는 있지만, 좌석 수가 인원수와 다를 때
            const missingSeats = totalPeople - selectedCount;
            alert(`총 ${totalPeople}명분의 좌석을 선택해야 합니다. ${missingSeats}개의 좌석을 더 선택해주세요.`);
            return;
        }

        // 2. ⭐️ 유효성 검사 통과: 서버로 전송할 데이터 취합 ⭐️
            const showtimeId = 1
            const screenName = SCREEN_NAME_FROM_JSP;
            const cinema = CINEMA_NAME_FROM_JSP;
            const movieTitle = MOVIE_TITLE_FROM_JSP;
            const memberId = 22
            const totalPriceText = $('#total-price').text().replace(/[^0-9]/g, ''); // '35,000원' -> '35000'

        const paymentData = {
                "showtimeId": showtimeId,
                "memberId": memberId,
                "totalPrice": parseInt(totalPriceText),
                "screen": screenName, // ⭐️ 수정 완료 ⭐️
                "cinema": cinema,
                "movieTitle": movieTitle,
                "peopleCounts": peopleCounts,
                "selectedSeats": selectedSeats
            };

        // 3. 유효성 검사 통과 시 (결제 진행 로직)
        alert(`총 ${selectedCount}개의 좌석 (${selectedSeats.join(', ')})으로 ${$('#total-price').text()} 결제를 진행합니다.`);

        // 여기에 실제로 서버에 데이터를 전송하는 AJAX 코드를 작성하면 됩니다.
        // 3. AJAX 전송 함수 호출
        sendReservationData(paymentData);
    });

    // ----------------------------------------------------
    // ⭐️ AJAX 전송 함수 정의 ⭐️
    // ----------------------------------------------------
    function sendReservationData(data) {
        // ⚠️ TODO: 실제 서버 엔드포인트 URL로 변경해야 합니다.
        const url = "/reservation";

        // 사용자에게 결제 시작을 알림
        alert(`총 ${data.totalPrice.toLocaleString('ko-KR')}원 결제를 서버로 요청합니다.`);

        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json",
            data: JSON.stringify(data),

            beforeSend: function() {
                // 로딩 스피너 등을 표시할 수 있습니다.
                $('#payment-button').prop('disabled', true).text('결제 처리 중...');
            },
            success: function(response) {
                $('#payment-button').prop('disabled', false).text('결제하기');

                if (response.status === 'success') {
                    alert("결제가 완료되었으며 예매가 성공적으로 등록되었습니다!");
                    // 예: 결제 완료 페이지로 이동
                    // window.location.href = '/reservation/complete?id=' + response.reservationId;
                } else {
                    // 좌석 선점 실패 (다른 사용자 동시 예매 등)
                    alert("예매 실패: " + (response.message || "알 수 없는 오류가 발생했습니다."));
                    // 실패 시 좌석 선택을 초기화하고 다시 선택하도록 유도할 수 있습니다.
                }
            },
            error: function(xhr, status, error) {
                $('#payment-button').prop('disabled', false).text('결제하기');
                alert("서버 통신 중 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.");
                console.error("AJAX Error: ", status, error);
            }
        });
    }