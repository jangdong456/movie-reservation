    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
        <head>
            <c:import url="/WEB-INF/views/template/header.jsp"></c:import>
        </head>
        <body class="body">
            <c:import url="/WEB-INF/views/template/nav.jsp"></c:import>

        <!-- 바디부분 -->
        <div class="container">
            <div class="row justify-content-center pt-5 pb-5">
                <div class="col-sm-12 col-lg-8">

                    <div class="card shadow-lg border-0 rounded-lg mt-5 mb-5">

                        <div class="card-header bg-success text-white text-center py-3">
                            <h3 class="font-weight-light my-2">회원가입 (Sign Up)</h3>
                            <p class="mb-0 small text-light">새로운 계정을 만들고 서비스를 시작하세요.</p>
                        </div>

                        <div class="card-body">

                        <!-- ======== form 시작 부분 ========= -->
                            <form action="/member/signup" method="POST">
                                <div class="form-row">

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="small mb-1" for="inputUsername">아이디</label>
                                            <input type="text" class="form-control" id="inputUsername" name="loginId" placeholder="아이디 입력 (5~12자)" required>
                                            <div class="invalid-feedback">
                                                아이디를 5자 이상 12자 이하로 입력해주세요.
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="small mb-1" for="inputPhone">휴대폰 번호</label>
                                            <input type="tel"
                                                   class="form-control"
                                                   id="inputPhone"
                                                   name="memberPhone"
                                                   placeholder="010-XXXX-XXXX"
                                                   pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}"
                                                   required>
                                            <div class="small text-muted mt-1">하이픈(-)을 포함하여 입력해주세요.</div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="small mb-1" for="inputPassword">비밀번호</label>
                                            <input type="password" class="form-control" id="inputPassword" name="memberPassword" placeholder="비밀번호 입력" required>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="small mb-1" for="inputConfirmPassword">비밀번호 확인</label>
                                            <input type="password" class="form-control" id="inputConfirmPassword" name="confirmPassword" placeholder="비밀번호 재입력" required>
                                            <div class="invalid-feedback">
                                                비밀번호가 일치하지 않습니다.
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="small mb-1" for="inputName">이름</label>
                                    <input type="text" class="form-control" id="inputName" name="memberName" placeholder="실명 입력" required>
                                </div>

                                <div class="form-group mt-4">
                                    <div class="form-check">
                                        <input class="form-check-input" id="termsCheck" type="checkbox" required />
                                        <label class="form-check-label small" for="termsCheck">
                                            <a href="#">이용약관</a> 및 <a href="#">개인정보 처리방침</a>에 동의합니다.
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group mt-4 mb-0">
                                    <button type="submit" class="btn btn-success btn-block py-2">계정 생성</button>
                                </div>

                            </form>
                            <!-- ======== form 끝 부분 ========= -->
                        </div>

                        <div class="card-footer text-center">
                            <div class="small">이미 계정이 있으신가요? <a href="#">로그인 (Sign In)</a></div>
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