    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <!DOCTYPE html>
    <html>
        <head>
            <c:import url="/WEB-INF/views/template/header.jsp"></c:import>
            <link rel="stylesheet" href="/css/styles.css">
        </head>
        <body class="body">
            <c:import url="/WEB-INF/views/template/nav.jsp"></c:import>
            <c:set var="now" value="<%= new java.util.Date() %>" />
        <!-- 바디부분 -->
<div class="container text-center">
    <div class="row align-items-stretch">

        <%-- >>>>>>> 컬럼1 부분 <<<<<<<< --%>
        <div class="col-4 column-divider-wrap cinema-region-wrapper">
            <div class="column-header">
                영화관
            </div>
            <div class="column-content">
                <div class="row no-gutters text-left">

                    <div class="col-3 region-list">
                        <a href="#" class="d-block region-name" data-region="서울">서울</a>
                        <a href="#" class="d-block region-name" data-region="인천">인천</a>
                    </div>

                    <div class="col-9 cinema-list-area">
                        <ul id="cinema-list" class="list-unstyled"></ul>
                        <c:forEach var="cinema" items="${cinemaDto}" begin="3" end="7">
                            <li class="cinema-item" data-region="서울" style="display: none;">
                                <a href="#">${cinema.cinemaName}</a>
                            </li>
                        </c:forEach>
                        <c:forEach var="cinema" items="${cinemaDto}" begin="0" end="2">
                            <li class="cinema-item" data-region="인천" style="display: none;">
                                <a href="#">${cinema.cinemaName}</a>
                            </li>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>

            <%-- >>>>>>> 컬럼2 부분 <<<<<<<< --%>
            <div class="col-4 column-divider-wrap">
                <div class="column-header">
                    상영영화
                </div>

                <div class="column-content scrollable-content">

                        <ul class="list-unstyled">
                            <c:forEach var="movie" items="${movieDto}" begin="0" end="9">
                                <li><a href="#">${movie.title}</a></li>
                            </c:forEach>
                        </ul>

                </div>
            </div>


            <%-- >>>>>>> 컬럼3 부분 <<<<<<<< --%>
            <div class="col-4">
                <div class="column-header date-header-divider">
                    <fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 (E)" />
                </div>

                <div class="column-content date-selector-content">

                    <div class="date-navigation d-flex justify-content-between align-items-center">

                        <button class="btn btn-sm btn-light date-nav-btn" id="prev-week-btn"><</button>

                        <div class="date-display-area d-flex justify-content-around w-100" id="date-list">
                        </div>

                        <button class="btn btn-sm btn-light date-nav-btn" id="next-week-btn">></button>
                    </div>

                    <div id="latest-selection-display" class="p-2 mb-3 border rounded text-left">
                        <p class="m-0 text-muted">선택된 영화관 없음</p>
                    </div>

                    <div id="showtime-list" class="showtime-display-area scrollable-content">
                        <a href="#" class="showtime-box">
                            <span class="time-main">20:05</span>
                            <span class="seat-info">
                                잔여석 <span class="seat-available">267</span> / <span class="seat-total">332</span>
                            </span>
                        </a>

                        <a href="#" class="showtime-box">
                            <span class="time-main">21:40</span>
                            <span class="seat-info">
                                잔여석 <span class="seat-available">2</span> / <span class="seat-total">332</span>
                            </span>
                        </a>
                    </div>
                        <div class="mt-3 text-center">
                            <button type="button" class="btn btn-primary btn-lg" id="select-button" disabled>선택</button>
                        </div>

                </div>
            </div>

    </div>
</div>

            <!-- footer -->
            <c:import url="/WEB-INF/views/template/footer.jsp"></c:import>

            <!-- jQuery library -->
            <script src="/js/jquery-3.3.1.js"></script>


            <!-- Bootstrap -->
            <script src="/bootstrap/js/bootstrap.js"></script>

            <script src="/js/booking.js"></script>


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