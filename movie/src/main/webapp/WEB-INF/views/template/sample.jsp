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