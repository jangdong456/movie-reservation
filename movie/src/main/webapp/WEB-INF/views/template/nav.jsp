<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <header class="header header-horizontal header-view-pannel">
            <div class="container">
                <nav class="navbar">
                    <a class="navbar-brand" href="/">
                        <span class="logo-element">
                            <span class="logo-tape">
                                <span class="svg-content svg-fill-theme" data-svg="/images/svg/logo-part.svg"></span>
                            </span>
                            <span class="logo-text text-uppercase">
                                <span>C</span>GV</span>
                        </span>
                    </a>
                    <button class="navbar-toggler" type="button">
                        <span class="th-dots-active-close th-dots th-bars">
                            <span></span>
                            <span></span>
                            <span></span>
                        </span>
                    </button>
                    <div class="navbar-collapse">
                        <ul class="navbar-nav">
                            <li class="nav-item nav-item-arrow-down nav-hover-show-sub">
                                <a class="nav-link" href="#">예매</a>
                                <div class="nav-arrow"><i class="fas fa-chevron-down"></i></div>
                                <ul class="collapse nav">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/bookingModal">예매하기</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item nav-item-arrow-down nav-hover-show-sub">
                                <a class="nav-link" href="#" data-role="nav-toggler">영화관</a>
                                <div class="nav-arrow"><i class="fas fa-chevron-down"></i></div>
                                <ul class="collapse nav">

                                    <li class="nav-item nav-item-arrow-down nav-hover-show-sub">
                                        <a class="nav-link" href="#" data-role="nav-toggler">서울</a>
                                        <div class="nav-arrow"><i class="fas fa-chevron-down"></i></div>
                                        <ul class="collapse nav">

                                            <c:forEach var="cinema" items="${cinemaDTO}" begin="3" end="7">

                                                <li class="nav-item">
                                                    <a class="nav-link" href="movies-blocks.html">${cinema.cinemaName}</a>
                                                </li>

                                            </c:forEach>
                                        </ul>
                                    </li>

                                    <li class="nav-item nav-item-arrow-down nav-hover-show-sub">
                                        <a class="nav-link" href="#" data-role="nav-toggler">인천</a>
                                        <div class="nav-arrow"><i class="fas fa-chevron-down"></i></div>
                                        <ul class="collapse nav">

                                            <c:forEach var="cinema" items="${cinemaDTO}" begin="0" end="2">
                                                <li class="nav-item">
                                                    <a class="nav-link" href="sign-in.html">${cinema.cinemaName}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </li>

                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="movies-blocks.html">영화</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="contact-us.jsp">고객센터</a>
                            </li>
                        </ul>
                        <div class="navbar-extra">
                            <c:if test="${empty sessionScope.loggedInId}">
                                <%-- 1. 로그인 되어 있지 않을 때 (세션 ID가 비어있을 때) --%>
                                <a class="btn-theme btn" href="/member/login">
                                    <i class="fas fa-ticket-alt"></i>&nbsp;&nbsp;로그인
                                </a>
                            </c:if>

                            <c:if test="${not empty sessionScope.loggedInId}">
                                <%-- 2. 로그인 되어 있을 때 (세션 ID가 비어있지 않을 때) --%>

                                <a class="btn-theme btn" href="/member/logout">
                                    <i class="fas fa-sign-out-alt"></i>&nbsp;&nbsp;로그아웃
                                </a>

                                <%-- 선택 사항: 사용자 이름 표시 --%>
                                <span class="text-white ml-3">
                                    ${sessionScope.loggedInId}님
                                </span>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </div>
        </header>