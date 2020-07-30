<%@ page import="pers.summer.util.JspFunction" %>
<%@ page import="java.util.List" %>
<%@ page import="pers.summer.entity.Image" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/28
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ALBUM</title>
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
            if(request.getAttribute("success") != null) {
        %>

        <script>
            alert("Successfully Added")
        </script>

        <%
                request.setAttribute("success", null);
            }

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
    <div class="fav">
        ALBUM
    </div>

    <%
        List<Image> list = (List<Image>) request.getAttribute("albumList");
        if(list.size() == 0) {
            out.print("<script>alert(\"You haven't collected any image\")</script>");
        } else if(list.size() <= 6) {
            JspFunction.outputAlbum(out, list);
        } else {
            JspFunction.outputAlbums(out, list);
        }

        if(list.size() > 2) {
            String chooseCss = "<script>" +
                    "let css = document.getElementById(\"footerCss\");" +
                    "css.setAttribute(\"href\", \"css/flowFooter.css\");" +
                    "</script>";
            out.print(chooseCss);
        }
    %>

</section>
<footer>
    <label>Copyright&copy; 2020 Outstanding Ability of Software Development</label>
</footer>
</body>
</html>
