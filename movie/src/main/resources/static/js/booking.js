$(document).ready(function() {
    // ⭐️ 1. 선택된 정보를 저장할 변수 ⭐️
        let selectedCinema = '';
        let selectedMovie = ''; // 상영영화 정보도 저장할 변수 추가

        // ⭐️ [수정] 두 정보를 모두 표시하는 함수로 변경 ⭐️
        /**
         * 컬럼 3 상단(#latest-selection-display)에 선택된 영화관과 영화를 모두 표시합니다.
         */
        function updateSelectionDisplay() {

            const cinemaText = selectedCinema || '<span class="text-muted">선택 필요</span>';
            const movieText = selectedMovie || '<span class="text-muted">선택 필요</span>';

            const htmlContent = `
                <div class="selection-line small">
                    <strong>영화관:</strong> ${cinemaText}
                </div>
                <div class="selection-line small">
                    <strong>상영영화:</strong> ${movieText}
                </div>
            `;

            // ⭐️ HTML에서 변경된 ID를 사용합니다. ⭐️
            $('#latest-selection-display').html(htmlContent);
        }


    // ⭐️ 여기에 임의의 테스트 데이터를 하드코딩합니다. ⭐️
    const cinemaData = [
        // 서울 지점
        { name: "CGV 강남", region: "서울" },
        { name: "CGV 홍대", region: "서울" },
        { name: "CGV 신촌아트레온", region: "서울" },

        // 인천 지점
        { name: "CGV 인천터미널", region: "인천" },
        { name: "CGV 송도", region: "인천" },

        // 기타 지역 (테스트용)
        { name: "CGV 서면", region: "부산" },
        { name: "CGV 대구한일", region: "대구" }
    ];

    // ----------------------------------------------------
        // 컬럼 1: 지역명 (서울/인천) 클릭 이벤트
        // ----------------------------------------------------
        $('.region-name').on('click', function(e) {
            e.preventDefault();

            // **이전 선택 해제 및 현재 항목 선택**
            $('.region-name').removeClass('selected-item');
            $(this).addClass('selected-item');

            // 기존의 지점 목록 필터링 및 출력 로직 유지
            const selectedRegion = $(this).data('region');
            // ... (지점 목록 출력 로직) ...

            // 🚨 중요: 새 지역을 선택했으니, 영화관과 영화 선택을 초기화해야 합니다.
            // $('.cinema-list-area a').removeClass('selected-item');
            // $('.column-content.scrollable-content a').removeClass('selected-item');
        });

        // ----------------------------------------------------
        // 컬럼 1: 영화관 지점 목록 클릭 이벤트 (나중에 생성된 a 태그)
        // ----------------------------------------------------
        $(document).on('click', '#cinema-list a', function(e) {
            e.preventDefault();

            // **이전 선택 해제 및 현재 항목 선택**
            $('#cinema-list a').removeClass('selected-item');
            $(this).addClass('selected-item');

            // ⭐️ 추가된 로직 1: 선택된 영화관 이름 저장 ⭐️
            selectedCinema = $(this).text();

            // ⭐️ [수정] 두 정보를 모두 표시하는 함수 호출 ⭐️
            updateSelectionDisplay();

            // 🚨 중요: 지점을 선택했으니, 영화 선택과 시간표를 초기화해야 합니다.
            // $('.column-content.scrollable-content a').removeClass('selected-item');
            // $('#showtime-list').empty().append('<p>영화와 날짜를 선택해주세요.</p>');

            console.log("선택된 영화관:", $(this).text());
            // ⭐️ 다음 단계 (영화 목록 업데이트) 로직이 여기에 추가됩니다. ⭐️
        });

        // ----------------------------------------------------
        // 컬럼 2: 상영영화 목록 클릭 이벤트
        // ----------------------------------------------------
        $(document).on('click', '.column-content.scrollable-content ul a', function(e) {
            e.preventDefault();

            // **이전 선택 해제 및 현재 항목 선택**
            $('.column-content.scrollable-content ul a').removeClass('selected-item');
            $(this).addClass('selected-item');

            // ⭐️ 추가된 로직 1: 선택된 영화 제목 저장 ⭐️
            selectedMovie = $(this).text();

            // ⭐️ [수정] 두 정보를 모두 표시하는 함수 호출 ⭐️
            updateSelectionDisplay();

            console.log("선택된 영화:", selectedMovie);
            // ⭐️ 다음 단계 (선택된 날짜 기준으로 시간표 업데이트) 로직이 여기에 추가됩니다. ⭐️

            console.log("선택된 영화:", $(this).text());
            // ⭐️ 다음 단계 (선택된 날짜 기준으로 시간표 업데이트) 로직이 여기에 추가됩니다. ⭐️
        });

    // 이 데이터로 클릭 이벤트를 처리합니다.
    $('.region-name').on('click', function(e) {
        e.preventDefault();

        const selectedRegion = $(this).data('region');

        // 1. 해당 지역의 지점만 필터링합니다.
        const filteredCinemas = cinemaData.filter(cinema => cinema.region === selectedRegion);

        // 2. HTML을 생성합니다.
        let listHtml = '';
        if (filteredCinemas.length > 0) {
            listHtml += '<ul class="list-unstyled">'; // list-unstyled 클래스 유지
            filteredCinemas.forEach(cinema => {
                // name 대신 cinemaName 필드를 사용한다면 name을 cinemaName으로 바꿔주세요.
                listHtml += `<li><a href="#">${cinema.name}</a></li>`;
            });
            listHtml += '</ul>';
        } else {
            listHtml = '<p>지점 정보가 없습니다.</p>';
        }

        // 3. 지점 목록 영역(#cinema-list)에 HTML을 삽입합니다.
        $('#cinema-list').html(listHtml);

        // 4. 클릭된 지역명에 하이라이트 효과
        $('.region-name').removeClass('active');
        $(this).addClass('active');
    });

    // 초기 로드시 '서울' 지점 목록을 보여줍니다.
    $('.region-name[data-region="서울"]').trigger('click');

    // 현재 날짜 상태를 관리할 변수
    let currentDate = new Date();

    // 요일 이름 배열
    const dayNames = ['일', '월', '화', '수', '목', '금', '토'];

    /**
     * 주어진 날짜(startDate)를 기준으로 일주일치 날짜를 생성하고 화면에 표시합니다.
     */
    function renderDates(startDate) {
        let html = '';
        const today = new Date();
        today.setHours(0, 0, 0, 0); // 시간 정보를 제거하여 오늘 날짜만 비교

        // 주의: JS의 Date 객체는 월을 0부터 세기 때문에 1월이 0입니다.

        for (let i = 0; i < 7; i++) {
            const date = new Date(startDate);
            date.setDate(startDate.getDate() + i); // 7일치 계산

            const dayOfWeek = dayNames[date.getDay()];
            const dayOfMonth = date.getDate();

            // 데이터 속성에 날짜 저장 (나중에 서버에 보낼 때 사용)
            const dateString = date.toISOString().split('T')[0];

            let classes = 'date-item';

            // 초기 로드시 오늘 날짜 강조
            if (date.toDateString() === today.toDateString()) {
                 classes += ' selected';
            }

            html += `
                <div class="${classes}" data-date="${dateString}">
                    <span>${dayOfWeek}</span><br>
                    <span>${dayOfMonth}</span>
                </div>
            `;
        }
        $('#date-list').html(html);
    }

    // ----------------------------------------------------
    // 이벤트 핸들러
    // ----------------------------------------------------
    $('#prev-week-btn').on('click', function() {
        // 현재 날짜를 7일 뒤로 이동
        currentDate.setDate(currentDate.getDate() - 7);
        renderDates(currentDate);
    });

    $('#next-week-btn').on('click', function() {
        // 현재 날짜를 7일 앞으로 이동
        currentDate.setDate(currentDate.getDate() + 7);
        renderDates(currentDate);
    });


    // ----------------------------------------------------
    // 초기 실행
    // ----------------------------------------------------
    // 페이지 로드 시 오늘 날짜를 기준으로 날짜 선택기를 초기화
    renderDates(currentDate);

    // booking.js 파일의 $(document).ready(function() { ... }) 안에 추가

    // ----------------------------------------------------
    // 상영 시간표 클릭 이벤트 핸들러 (컬럼 3)
    // ----------------------------------------------------
    $(document).on('click', '.showtime-box', function(e) {
        // 1. 매진 여부 확인 (CSS의 pointer-events: none; 덕분에 이 코드는 매진 시 실행되지 않음)
        //    만약을 위해 클래스를 한 번 더 체크할 수 있습니다.
        if ($(this).hasClass('sold-out')) {
            e.preventDefault(); // 기본 링크 동작 막기
            alert("죄송합니다. 해당 회차는 매진되었습니다.");
            return;
        }

        // 2. 선택된 항목 강조
        $('.showtime-box').removeClass('selected-time');
        $(this).addClass('selected-time');

        // 3. (나중에 구현) 다음 단계 (예: 좌석 선택 페이지로 이동 또는 좌석 레이아웃 표시)
        const selectedTime = $(this).find('.time-main').text();
        console.log("선택된 시간:", selectedTime);

        // 예: 서버에 선택 정보 전송 후 좌석 페이지로 이동
        // window.location.href = `/booking/seat?time=${selectedTime}&...`;
    });
    // ----------------------------------------------------
        // 초기 실행 시 업데이트 함수 호출
        // ----------------------------------------------------
        // 페이지 로드 시 '선택 필요' 메세지를 표시
        updateSelectionDisplay();
});

