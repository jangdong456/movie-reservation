    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
        <head>
            <c:import url="/WEB-INF/views/template/header.jsp"></c:import>
        </head>
        <body class="body">
            <c:import url="/WEB-INF/views/template/nav.jsp"></c:import>
            <div class="container">
                <div class="row justify-content-center pt-5 pb-5">
                    <div class="col-sm-10 col-md-8 col-lg-5">

                        <div class="card shadow-lg border-0 rounded-lg mt-5">

                            <div class="card-header bg-dark text-white text-center py-3">
                                <h3 class="font-weight-light my-2">회원 로그인</h3>
                                <p class="mb-0 small text-light">서비스 이용을 위해 로그인해주세요.</p>
                            </div>

                            <div class="card-body">

                                <div class="alert alert-danger fade show small" role="alert" style="display: none;">
                                    <i class="fas fa-exclamation-triangle mr-2"></i> 아이디 또는 비밀번호가 일치하지 않습니다.
                                </div>

                                <!-- ==== form 시작 부분 ==== -->
                                <form action="/member/login" method="POST">

                                    <div class="form-group">
                                        <label class="small mb-1" for="inputUsername">아이디</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                                            </div>
                                            <input type="text"
                                                   class="form-control py-4"
                                                   id="inputUsername"
                                                   name="loginId"
                                                   placeholder="아이디 입력"
                                                   required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="small mb-1" for="inputPassword">비밀번호</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><i class="fas fa-lock"></i></span>
                                            </div>
                                            <input type="password"
                                                   class="form-control py-4"
                                                   id="inputPassword"
                                                   name="memberPassword"
                                                   placeholder="비밀번호 입력"
                                                   required>
                                        </div>
                                    </div>

                                    <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <div class="form-check">
                                            <input class="form-check-input" id="rememberPasswordCheck" type="checkbox" />
                                            <label class="form-check-label small" for="rememberPasswordCheck">로그인 유지</label>
                                        </div>
                                        <a class="small" href="#">비밀번호 찾기</a>
                                    </div>

                                    <div class="form-group mt-4 mb-0">
                                        <button type="submit" class="btn btn-primary btn-block py-2">로그인</button>
                                    </div>

                                </form>
                                <!-- ==== form 끝 부분 ==== -->
                            </div>

                            <div class="card-footer text-center">
                                <p class="small text-muted mb-2">SNS로 간편 로그인</p>
                                <a class="btn btn-outline-danger btn-sm mx-1" href="#"><i class="fab fa-google"></i></a>
                                <a class="btn btn-outline-warning btn-sm mx-1" href="#"><i class="fab fa-kakao"></i></a>
                                <hr class="my-3">
                                <div class="small">계정이 없으신가요? <a href="<c:url value='/member/signup' />">회원가입</a></div>
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