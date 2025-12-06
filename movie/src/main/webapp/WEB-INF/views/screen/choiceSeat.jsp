    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
        <head>
        <c:import url="/WEB-INF/views/template/header.jsp"></c:import>
        <link rel="stylesheet" href="/css/screen.css">
        </head>

        <body class="body">
        <c:import url="/WEB-INF/views/template/nav.jsp"></c:import>

        <!-- 바디 시작 부분 -->
        <div class="row mt-4">
                <div class="col-12 text-center">
                    <h2>인원/좌석 선택</h2>
                </div>
            </div>

            <div class="row mt-2">
                <div class="col-12 text-right">
                    <p class="pr-4 text-danger">인원은 최대 8명까지 가능합니다.</p>
                </div>
            </div>

            <div class="row mt-4 mb-5 p-3 border rounded bg-light align-items-center no-gutters">
                <div class="col-6 col-lg-2 d-flex flex-column info-summary-area ml-5 pr-0">
                    <p class="mb-1"><strong>영화관:</strong> <span class="text-primary">${selectedCinema}</span></p>
                    <p class="mb-1"><strong>선택된 영화:</strong> <span class="text-primary">${selectedMovie}</span></p>
                    <p class="mb-0"><strong>상영관:</strong> <span class="text-primary">${selectedScreen}</span></p>
                    <p class="mb-0"><strong>시간:</strong> <span class="text-success">${selectedDate} ${selectedTime}</span></p>
                </div>

                <div class="col-6 col-lg-9 person-count-area">
                    <div class="row">
                        <div class="col-6 col-md-3 mb-2 text-center">
                            <label>성인</label>
                            <div class="input-group input-group-sm justify-content-center">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-secondary btn-minus" type="button" data-type="adult">-</button>
                                </div>
                                <input type="text" class="form-control text-center count-input" value="0" readonly data-type="adult">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary btn-plus" type="button" data-type="adult">+</button>
                                </div>
                            </div>
                        </div>

                        <div class="col-6 col-md-3 mb-2 text-center">
                            <label>청소년</label>
                            <div class="input-group input-group-sm justify-content-center">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-secondary btn-minus" type="button" data-type="teen">-</button>
                                </div>
                                <input type="text" class="form-control text-center count-input" value="0" readonly data-type="teen">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary btn-plus" type="button" data-type="teen">+</button>
                                </div>
                            </div>
                        </div>

                        <div class="col-6 col-md-3 mb-2 text-center">
                            <label>경로</label>
                            <div class="input-group input-group-sm justify-content-center">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-secondary btn-minus" type="button" data-type="senior">-</button>
                                </div>
                                <input type="text" class="form-control text-center count-input" value="0" readonly data-type="senior">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary btn-plus" type="button" data-type="senior">+</button>
                                </div>
                            </div>
                        </div>

                        <div class="col-6 col-md-3 mb-2 text-center">
                            <label>장애인</label>
                            <div class="input-group input-group-sm justify-content-center">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-secondary btn-minus" type="button" data-type="disabled">-</button>
                                </div>
                                <input type="text" class="form-control text-center count-input" value="0" readonly data-type="disabled">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary btn-plus" type="button" data-type="disabled">+</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mt-5">
                <div class="col-12 text-center screen-area">
                    <h2>S  C  R  E  E  N<h2>
                </div>
            </div>

            <%-- 스크린 좌석 부분--%>
            <div class="row mt-5 justify-content-center">
                <div class="col-12 text-center seat-map-container">

                    <div id="seat-map" class="d-inline-block">
                        <c:set var="rows" value="A,B,C,D,E,F" />
                        <c:forTokens items="${rows}" delims="," var="rowLabel">
                            <div class="seat-row" data-row="${rowLabel}">
                                <span class="row-label">${rowLabel}</span>

                                <c:forEach begin="1" end="9" var="col">
                                    <button type="button" class="seat-item available" data-seat-id="${rowLabel}${col}">${col}</button>
                                </c:forEach>

                                <span class="aisle-space"></span>

                                <c:forEach begin="10" end="13" var="col">
                                    <button type="button" class="seat-item available" data-seat-id="${rowLabel}${col}">${col}</button>
                                </c:forEach>
                            </div>
                        </c:forTokens>
                    </div>


                    <div class="seat-legend-area mt-3 mb-2">
                        <div class="d-inline-flex align-items-center">
                            <div class="d-flex align-items-center mr-4">
                                <span class="seat-legend available-legend mr-2"></span>
                                <span class="text-white">선택 가능</span>
                            </div>
                            <div class="d-flex align-items-center">
                                <span class="seat-legend selected-legend mr-2"></span>
                                <span class="text-white">선택함</span>
                            </div>
                            <div class="d-flex align-items-center ml-4">
                                <span class="seat-legend reserved-legend mr-2"></span>
                                <span class="text-white">예매완료</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row mt-5 mb-5 justify-content-center">

                <div class="col-12 col-md-6">

                    <div class="d-flex justify-content-between align-items-center">

                        <h4 class="mb-0">총 가격: <span id="total-price" class="text-danger">0원</span></h4>

                        <button type="button"
                                class="btn btn-danger btn-lg w-50"
                                id="payment-button">
                            결제하기
                        </button>

                    </div>
                </div>
            </div>

        <!-- 바디 끝 부분 -->
        <script>
            const SCREEN_NAME_FROM_JSP = "${selectedScreen}";
            const CINEMA_NAME_FROM_JSP = "${selectedCinema}";
            const MOVIE_TITLE_FROM_JSP = "${selectedMovie}";
        </script>

        <!-- footer -->
        <c:import url="/WEB-INF/views/template/footer.jsp"></c:import>

        <!-- jQuery library -->
        <script src="/js/jquery-3.3.1.js"></script>
        <!-- Bootstrap -->
        <script src="/js/screen.js"></script>
        <script src="/bootstrap/js/bootstrap.js"></script>
        <!-- Paralax.js -->
        <%-- <script src="/parallax.js/parallax.js"></script> --%>
        <!-- Waypoints -->
        <script src="/waypoints/jquery.waypoints.min.js"></script>
        <!-- Slick carousel -->
        <script src="/slick/slick.min.js"></script>
        <!-- Magnific Popup -->
        <script src="/magnific-popup/jquery.magnific-popup.min.js"></script>
        <!-- Inits product scripts -->
        <script src="/js/script.js"></script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJ4Qy67ZAILavdLyYV2ZwlShd0VAqzRXA&callback=initMap&loading=async&v=beta&libraries=marker" async></script>
        <script async defer src="https://ia.media-imdb.com/images/G/01/imdb/plugins/rating/js/rating.js"></script>
        </body>
    </html>