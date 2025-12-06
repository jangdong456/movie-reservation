$(document).ready(function() {
    // ⭐️ 1. 선택된 정보를 저장할 변수 ⭐️
        let selectedCinema = '';     // 선택된 영화관 이름 (예: CGV 강남)
        let selectedMovie = '';      // 선택된 상영영화 제목
        let selectedTime = '';       // ⭐️ [추가] 선택된 상영 시간 (예: 14:30) ⭐️

        // ⭐️ [수정] 세 정보를 모두 표시하는 함수로 변경 ⭐️
        /**
         * 컬럼 3 상단(#latest-selection-display)에 선택된 영화관, 영화, 시간을 모두 표시합니다.
         */
        function updateSelectionDisplay() {

            const cinemaText = selectedCinema || '<span class="text-muted">선택 필요</span>';
            const movieText = selectedMovie || '<span class="text-muted">선택 필요</span>';
            const timeText = selectedTime || '<span class="text-muted">시간 선택 필요</span>';

            const htmlContent = `
                <div class="selection-line">
                    <strong>영화관:</strong> ${cinemaText}
                </div>
                <div class="selection-line">
                    <strong>상영영화:</strong> ${movieText}
                </div>
                <div class="selection-line">
                    <strong>시간:</strong> ${timeText}
                </div>
            `;

            // ⭐️ HTML에서 변경된 ID를 사용합니다. ⭐️
            $('#latest-selection-display').html(htmlContent);

            // ⭐️ 선택 완료 시 버튼 활성화 (영화관, 영화, 시간 3가지가 모두 선택되었을 때) ⭐️
            if (selectedCinema && selectedMovie && selectedTime) {
                $('#select-button').prop('disabled', false);
            } else {
                $('#select-button').prop('disabled', true);
            }
        }


    // ----------------------------------------------------
    // 컬럼 1: 지역명 (서울/인천) 클릭 이벤트
    // ----------------------------------------------------
    $('.region-name').on('click', function(e) {
        e.preventDefault();

        // **이전 선택 해제 및 현재 항목 선택**
        $('.region-name').removeClass('selected-item');
        $(this).addClass('selected-item');

        // 🚨 중요: 새 지역을 선택했으니, 영화관과 영화, 시간 선택을 초기화해야 합니다.
        // 현재는 지점 목록 출력 로직만 남겨둠


        // 1. 해당 지역의 지점만 필터링합니다.
        const selectedRegion = $(this).data('region');

        const filteredCinemas = cinemaData.filter(cinema => cinema.region === selectedRegion);

        // 2. HTML을 생성합니다.
        let listHtml = '';
        if (filteredCinemas.length > 0) {
            listHtml += '<ul class="list-unstyled">'; // list-unstyled 클래스 유지
            filteredCinemas.forEach(cinema => {
                listHtml += `<li class="cinema-item" data-region="${selectedRegion}">
                                            <a href="#" data-name="${cinema.name}">${cinema.name}</a>
                                         </li>`;
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

        // 5. [추가] 하위 선택 초기화
        selectedCinema = '';
        selectedMovie = '';
        selectedTime = '';
        updateSelectionDisplay();
        // 실제로는 #cinema-list와 영화 목록 UI도 초기화해야 합니다.
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
//        selectedCinema = $(this).data('name');

        // 🚨 중요: 지점을 선택했으니, 영화 선택과 시간표를 초기화해야 합니다.
        selectedMovie = '';
        selectedTime = '';

        // ⭐️ [수정] 세 정보를 모두 표시하는 함수 호출 ⭐️
        updateSelectionDisplay();

//        console.log("선택된 영화관:", $(this).text());
        console.log("선택된 영화관:", selectedCinema);
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

        // 🚨 중요: 영화를 선택했으니, 시간표 선택을 초기화해야 합니다.
        selectedTime = '';

        // ⭐️ [수정] 세 정보를 모두 표시하는 함수 호출 ⭐️
        updateSelectionDisplay();

        console.log("선택된 영화:", selectedMovie);
        // ⭐️ 다음 단계 (선택된 날짜 기준으로 시간표 업데이트) 로직이 여기에 추가됩니다. ⭐️
    });

    // ----------------------------------------------------
    // 상영 시간표 클릭 이벤트 핸들러 (컬럼 3) - [수정] 선택된 시간 저장
    // ----------------------------------------------------
    $(document).on('click', '.showtime-box:not(.sold-out)', function(e) {
        e.preventDefault();

        // 1. 선택된 항목 강조
        $('.showtime-box').removeClass('selected-time');
        $(this).addClass('selected-time');

        // 2. 선택된 시간 정보 저장
        selectedTime = $(this).find('.time-main').text();
        selectedScreen = $(this).find('.screen-info').text();

        // 3. 선택 표시 및 버튼 활성화 체크
        updateSelectionDisplay();

        console.log("선택된 시간:", selectedTime);
    });

    // ----------------------------------------------------
    // ⭐️ [새 로직] '선택' 버튼 클릭 이벤트 핸들러 (최종 전송) ⭐️
    // ----------------------------------------------------
    $('#select-button').on('click', function(e) {
        e.preventDefault();

        if (!selectedCinema || !selectedMovie || !selectedTime) {
            // 버튼이 disabled 상태이므로 보통 실행될 일은 없지만 안전을 위해 추가
            alert("영화관, 영화, 시간을 모두 선택해 주세요.");
            return;
        }

        // 현재 날짜 선택기에서 선택된 날짜 (data-date)를 가져와야 합니다.
        // 현재 코드에서는 날짜 선택 로직이 구현되어 있지 않아, 임시로 오늘 날짜를 사용하거나
        // 날짜 선택기를 구현해야 합니다.
        // *****************************************************************
        // ❗️ 현재는 날짜 선택 로직이 없으므로, 날짜 정보를 가져올 수 없습니다.
        // ❗️ 가장 마지막에 선택된 날짜 (CSS .date-item.selected)에서 data-date를 가져온다고 가정합니다.
        // *****************************************************************

        const selectedDate = $('.date-item.selected').data('date');

        if (!selectedDate) {
             alert("날짜를 선택해주세요.");
             return;
        }

        // 1. 쿼리 스트링(URL 파라미터) 구성
        // 서버 측에서 정보를 받을 때 공백 문자 등이 문제 될 수 있으므로 인코딩합니다.
        const queryParams = new URLSearchParams({
            cinema: selectedCinema,
            movie: selectedMovie,
            time: selectedTime,
            screen: selectedScreen,
            date: selectedDate
        }).toString();

        // 2. choiceSeat.jsp를 렌더링할 컨트롤러 URL로 이동
        // 서버에서 이 URL을 처리하도록 구현해야 합니다.
        window.location.href = `/screen/seat?${queryParams}`;

        console.log("다음 페이지로 이동:", `/screen/seat?${queryParams}`);
    });

    // 이 데이터로 클릭 이벤트를 처리합니다. (기존 지역 클릭 이벤트는 위에서 수정함)
    // ... (기존 지역 클릭 이벤트 하단 로직) ...

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

            // 초기 로드시 오늘 날짜 강조 및 선택
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

        // ⭐️ [추가] 날짜 항목 클릭 이벤트 ⭐️
        $('#date-list').off('click', '.date-item').on('click', '.date-item', function() {
            $('#date-list .date-item').removeClass('selected');
            $(this).addClass('selected');

            // 날짜 선택 시에도 시간 선택은 초기화되어야 합니다. (시간표 로딩 로직이 필요)
            selectedTime = '';
            updateSelectionDisplay();

            // ⭐️ 날짜 선택 후 상영 시간표 다시 로딩하는 AJAX 로직이 여기에 추가되어야 합니다. ⭐️
        });
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

    // 초기 실행 시 업데이트 함수 호출
    updateSelectionDisplay();
});