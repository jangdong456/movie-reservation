    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>
        <head>
            <c:import url="template/header.jsp"></c:import>
        </head>
        <body class="body">
            <c:import url="template/nav.jsp"></c:import>

            <section class="section-text-white position-relative">
                <div class="d-background" data-image-src="http://placehold.complaceholder.com/1920x1080" data-parallax="scroll"></div>
                <div class="d-background bg-theme-blacked"></div>
                <div class="mt-auto container position-relative">
                    <div class="top-block-head text-uppercase">
                        <h2 class="display-4">Now
                            <span class="text-theme">in cinema</span>
                        </h2>
                    </div>
                    <div class="top-block-footer">
                        <div class="slick-spaced slick-carousel" data-slick-view="navigation responsive-4">
                            <div class="slick-slides">

                            <c:forEach var="movie" items="${movieDto}" end="7">

                                <div class="slick-slide">
                                    <article class="poster-entity" data-role="hover-wrap">
                                        <div class="embed-responsive embed-responsive-poster">


                                        <!-- 메인화면 포스터 이미지 걸리는 첫 부분!! -->
                                            <img class="embed-responsive-item" src="${movie.posterPath}" alt="" />
                                        </div>
                                        <div class="d-background bg-theme-lighted collapse animated faster" data-show-class="fadeIn show" data-hide-class="fadeOut show"></div>

                                        <!-- 메인화면 포스터 밑 영상링크 걸리는 부분 -->
                                        <div class="d-over bg-highlight-bottom">
                                            <div class="collapse animated faster entity-play" data-show-class="fadeIn show" data-hide-class="fadeOut show">
                                                <a class="action-icon-theme action-icon-bordered rounded-circle" href="https://www.youtube.com/watch?v=d96cjJhvlMA" data-magnific-popup="iframe">
                                                    <span class="icon-content"><i class="fas fa-play"></i></span>
                                                </a>
                                            </div>

                                            <!-- 포스터 제목 -->
                                            <h4 class="entity-title">
                                                <a class="content-link" href="movie-info-sidebar-right.html">${movie.title}</a>
                                            </h4>
                                            <div class="entity-info">
                                                <div class="info-lines">
                                                    <div class="info info-short">
                                                        <span class="text-theme info-icon"><i class="fas fa-star"></i></span>
                                                        <span class="info-text">8,1</span>
                                                        <span class="info-rest">/10</span>
                                                    </div>
                                                    <div class="info info-short">
                                                        <span class="text-theme info-icon"><i class="fas fa-clock"></i></span>
                                                        <span class="info-text">125</span>
                                                        <span class="info-rest">&nbsp;min</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </article>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="slick-arrows">
                            <div class="slick-arrow-prev">
                                <span class="th-dots th-arrow-left th-dots-animated">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </span>
                            </div>

                            <div class="slick-arrow-next">
                                <span class="th-dots th-arrow-right th-dots-animated">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!------------ 중간 화면 ---------->

            <section class="section-long">
                <div class="container">
                    <div class="section-head">
                        <h2 class="section-title text-uppercase">현재 상영 영화</h2>
                        <p class="section-text">2025년 12월</p>
                    </div>


                    <c:forEach var="movie" items="${movieDto}" end="7">
                        <article class="movie-line-entity">
                            <div class="entity-poster" data-role="hover-wrap">
                                <div class="embed-responsive embed-responsive-poster">
                                    <img class="embed-responsive-item" src="${movie.posterPath}" alt="" />
                                </div>
                                <div class="d-over bg-theme-lighted collapse animated faster" data-show-class="fadeIn show" data-hide-class="fadeOut show">
                                    <div class="entity-play">
                                        <a class="action-icon-theme action-icon-bordered rounded-circle" href="https://www.youtube.com/watch?v=d96cjJhvlMA" data-magnific-popup="iframe">
                                            <span class="icon-content"><i class="fas fa-play"></i></span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="entity-content">
                                <h4 class="entity-title">
                                    <a class="content-link" href="movie-info-sidebar-right.html">${movie.title}</a>
                                </h4>
                                <div class="entity-category">
                                    <a class="content-link" href="movies-blocks.html">crime</a>,
                                    <a class="content-link" href="movies-blocks.html">comedy</a>
                                </div>
                                <div class="entity-info">
                                    <div class="info-lines">
                                        <div class="info info-short">
                                            <span class="text-theme info-icon"><i class="fas fa-star"></i></span>
                                            <span class="info-text">8,1</span>
                                            <span class="info-rest">/10</span>
                                        </div>
                                        <div class="info info-short">
                                            <span class="text-theme info-icon"><i class="fas fa-clock"></i></span>
                                            <span class="info-text">125</span>
                                            <span class="info-rest">&nbsp;min</span>
                                        </div>
                                    </div>
                                </div>
                                <p class="text-short entity-text">${movie.overview}
                                </p>
                            </div>
                            <div class="entity-extra">
                                <div class="text-uppercase entity-extra-title">Showtime</div>
                                <div class="entity-showtime">
                                    <div class="showtime-wrap">
                                        <div class="showtime-item">
                                            <span class="disabled btn-time btn" aria-disabled="true">11 : 30</span>
                                        </div>
                                        <div class="showtime-item">
                                            <a class="btn-time btn" aria-disabled="false" href="#">13 : 25</a>
                                        </div>
                                        <div class="showtime-item">
                                            <a class="btn-time btn" aria-disabled="false" href="#">16 : 07</a>
                                        </div>
                                        <div class="showtime-item">
                                            <a class="btn-time btn" aria-disabled="false" href="#">19 : 45</a>
                                        </div>
                                        <div class="showtime-item">
                                            <a class="btn-time btn" aria-disabled="false" href="#">21 : 30</a>
                                        </div>
                                        <div class="showtime-item">
                                            <a class="btn-time btn" aria-disabled="false" href="#">23 : 10</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </c:forEach>
                </div>
            </section>

            <!-- 밑 부분 화면 -->

            <section class="section-solid-long section-text-white position-relative">
                <div class="d-background" data-image-src="http://placehold.com/1920x1080" data-parallax="scroll"></div>
                <div class="d-background bg-gradient-black"></div>
                <div class="container position-relative">
                    <div class="section-head">
                        <h2 class="section-title text-uppercase">Comming Soon</h2>
                    </div>


                    <div class="slick-spaced slick-carousel" data-slick-view="navigation single">
                        <div class="slick-slides">

                            <c:forEach var="movie" items="${movieDto}" begin="12" end="16">
                                    <div class="slick-slide">
                                        <article class="movie-line-entity">
                                            <div class="entity-preview">
                                                <div class="embed-responsive embed-responsive-16by9">
                                                    <img class="embed-responsive-item" src="${movie.posterPath}" alt="" />
                                                </div>
                                                <div class="d-over">
                                                    <div class="entity-play">
                                                        <a class="action-icon-theme action-icon-bordered rounded-circle" href="https://www.youtube.com/watch?v=d96cjJhvlMA" data-magnific-popup="iframe">
                                                            <span class="icon-content"><i class="fas fa-play"></i></span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="entity-content">
                                                <h4 class="entity-title">
                                                    <a class="content-link" href="movie-info-sidebar-right.html">${movie.title}</a>
                                                </h4>
                                                <div class="entity-category">
                                                    <a class="content-link" href="movies-blocks.html">sport</a>,
                                                    <a class="content-link" href="movies-blocks.html">musical</a>
                                                </div>
                                                <div class="entity-info">
                                                    <div class="info-lines">
                                                        <div class="info info-short">
                                                            <span class="text-theme info-icon"><i class="fas fa-calendar-alt"></i></span>
                                                            <span class="info-text">18 jul 2020</span>
                                                        </div>
                                                        <div class="info info-short">
                                                            <span class="text-theme info-icon"><i class="fas fa-clock"></i></span>
                                                            <span class="info-text">130</span>
                                                            <span class="info-rest">&nbsp;min</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <p class="text-short entity-text">${movie.overview}
                                                </p>
                                            </div>
                                        </article>
                                    </div>
                            </c:forEach>
                        </div>


                        <div class="slick-arrows">
                            <div class="slick-arrow-prev">
                                <span class="th-dots th-arrow-left th-dots-animated">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </span>
                            </div>
                            <div class="slick-arrow-next">
                                <span class="th-dots th-arrow-right th-dots-animated">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>


            <!-- footer -->
            <c:import url="template/footer.jsp"></c:import>

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