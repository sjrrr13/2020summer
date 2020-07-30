<%@ page import="pers.summer.entity.Image" %>
<%@ page import="java.util.List" %>
<%@ page import="pers.summer.util.JspFunction" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/28
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FAVORITE</title>
    <link href="css/header.css" rel="stylesheet" type="text/css">
    <link href="css/album.css" rel="stylesheet" type="text/css">
    <link href="css/fixedFooter.css" rel="stylesheet" type="text/css" id="footerCss">
</head>
<body>
<header>
    <img src="image/icon/fish.jpg" alt="flag">
    <div class="links">
        <a href="index.jsp">HOME</a>
        <a href="search.jsp">SEARCH</a>

        <%
            if(request.getSession().getAttribute("user") != null) {
        %>

        <div class="dropdown">
            <label class="drop">MY ACCOUNT</label>
            <div class="dropdown-content" id="dropTags">
                <a href="post.jsp"><img src="image/icon/post.jpg" alt="post" class="icon">
                    POST</a>
                <a href="AlbumServlet"><img src="image/icon/album.jpg" alt="post" class="icon">
                    ALBUM</a>
                <a href="FavoriteServlet"><img src="image/icon/favorite.jpg" alt="post" class="icon">
                    FAVORITE</a>
                <a href="FriendServlet"><img src="image/icon/friends.jpg" alt="post" class="icon">
                    FRIENDS</a>
                <a href="LogOutServlet"><img src="image/icon/login.jpg" alt="post" class="icon">
                    LOG OUT</a>
            </div>
        </div>

        <%
            } else {
                out.print("<a href=\"login.jsp\">SIGN IN</a>");
            }
        %>

    </div>
</header>
<section>

        <%
            if(request.getAttribute("friendName") != null) {
                int canRead = (int) request.getAttribute("canRead");
                String friend = (String) request.getAttribute("friendName");
                out.print("<div class=\"fav\">");
                out.print(friend + "'s FAVORITE");
                out.print("</div>");

                if(canRead > 0) {
                    List<Image> list = (List<Image>) request.getAttribute("friendFav");
                    if(list.size() == 0) {
                        out.print("<script>alert(\"He/She haven't collected any image\")</script>");
                    } else if(list.size() <= 6) {
                        JspFunction.outputFriendFav(out, list);
                    } else {
                        JspFunction.outputFriendFavs(out, list);
                    }

                    if(list.size() > 2) {
                        String chooseCss = "<script>" +
                                "let css = document.getElementById(\"footerCss\");" +
                                "css.setAttribute(\"href\", \"css/flowFooter.css\");" +
                                "</script>";
                        out.print(chooseCss);
                    }
                } else {
                    out.print("<div class=\"tip\">Friend don't want to show his/her favorite images</div>");
                }
            } else {
        %>
            <div class="fav">
                FAVORITE
            </div>
            <div class="tracks">
                <a href="track.jsp">TRACK</a>
            </div>
        <%
                List<Image> list = (List<Image>) request.getAttribute("favList");
                if(list.size() == 0) {
                    out.print("<script>alert(\"You haven't collected any image\")</script>");
                } else if(list.size() <= 6) {
                    JspFunction.outputFavorite(out, list);
                } else {
                    JspFunction.outputFavorites(out, list);
                }

                if(list.size() > 2) {
                    String chooseCss = "<script>" +
                            "let css = document.getElementById(\"footerCss\");" +
                            "css.setAttribute(\"href\", \"css/flowFooter.css\");" +
                            "</script>";
                    out.print(chooseCss);
                }
            }
        %>

</section>
<footer>
    <label>Copyright&copy; 2020 Outstanding Ability of Software Development</label>
</footer>
<script src="js/turnPage.js"></script>

    <%
        if(request.getSession().getAttribute("noTracks") != null) {
    %>
    <script>
        alert("No tracks");
    </script>
    <%
            request.getSession().removeAttribute("noTracks");
        }
    %>

</body>
</html>
