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
            const memberId = 1
            const totalPriceText = $('#total-price').text().replace(/[^0-9]/g, ''); // '35,000원' -> '35000'
            const now = new Date();
            const currentDateTime = now.toISOString();

        const paymentData = {
                "showtimeId": showtimeId,
                "memberId": memberId,
                "totalPrice": parseInt(totalPriceText),
                "screen": screenName, // ⭐️ 수정 완료 ⭐️
                "cinema": cinema,
                "movieTitle": movieTitle,
                "peopleCounts": peopleCounts,
                "selectedSeats": selectedSeats,
                "currentDateTime": currentDateTime
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

// 현재 설정된 총 인원수 구하기
function getTotalPersonCount() {
    let total = 0;
    document.querySelectorAll('.count-input').forEach(input => {
        total += parseInt(input.value) || 0;
    });
    return total;
}

// 1. 좌석 영역의 부모 요소(예: .seat-container)에 이벤트를 하나만 겁니다.
// 부모 요소를 찾기 어렵다면 document에 걸어도 작동합니다.
document.addEventListener('click', function(event) {
    // 클릭된 요소가 .seat-item인지 확인
    const seat = event.target.closest('.seat-item');
    if (!seat || seat.classList.contains('reserved')) return;

    const seatId = seat.getAttribute('data-seat-id');
    const totalPeople = calculateTotalPeople(); // 기존에 만들어둔 총 인원수 계산 함수

    // [케이스 A] 이미 선택한 좌석을 다시 클릭 (취소/Unlock)
    if (seat.classList.contains('selected')) {
        fetch('/reservation/unlock', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ seatId: seatId })
        })
        .then(response => {
            if (response.ok) {
                seat.classList.remove('selected');
                seat.classList.add('available');

                // 전역 배열 selectedSeats 업데이트 (바닐라 JS 방식)
                selectedSeats = selectedSeats.filter(id => id !== seatId);

                resetTotalPriceDisplay();
                syncSeatStatus(); // 상태 동기화
            }
        });
        return;
    }

    // [케이스 B] 빈 좌석을 클릭 (선택/Lock)
    if (seat.classList.contains('available')) {
        if (totalPeople === 0) {
            alert("먼저 인원수를 선택해 주세요.");
            return;
        }

        if (selectedSeats.length >= totalPeople) {
            alert(`선택 인원(${totalPeople}명)을 모두 선택하셨습니다.`);
            return;
        }

        fetch('/reservation/lock', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ seatId: seatId })
        })
        .then(response => {
            if (response.ok) {
                seat.classList.add('selected');
                seat.classList.remove('available');
                selectedSeats.push(seatId);

                if (selectedSeats.length === totalPeople) {
                    updateTotalPrice();
                }
                syncSeatStatus();
            } else {
                alert("이미 다른 사용자가 선점한 좌석입니다.");
            }
        })
        .catch(error => console.error('Error:', error));
    }
});

/**
 * ⭐️ 바닐라 JS용 상태 관리 함수
 */
function syncSeatStatus() {
    const totalPeople = calculateTotalPeople();
    const selectedCount = selectedSeats.length;

    // 현재 선택되지 않은 모든 빈 좌석들 추출
    const availableSeats = document.querySelectorAll('.seat-item.available:not(.selected)');

    if (selectedCount >= totalPeople && totalPeople > 0) {
        // 인원 다 찼으면 나머지 좌석 비활성화
        availableSeats.forEach(s => {
            s.style.opacity = '0.5';
            s.style.pointerEvents = 'none';
        });
    } else {
        // 인원 남았으면 모든 빈 좌석 활성화
        availableSeats.forEach(s => {
            s.style.opacity = '1';
            s.style.pointerEvents = 'auto';
        });
    }
}
