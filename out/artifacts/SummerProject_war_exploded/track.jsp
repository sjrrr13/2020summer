<%@ page import="pers.summer.entity.Image" %>
<%@ page import="java.util.List" %>
<%@ page import="pers.summer.util.JspFunction" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/30
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TRACK</title>
    <link href="css/header.css" rel="stylesheet" type="text/css">
    <link href="css/track.css" rel="stylesheet" type="text/css">
    <link href="css/fixedFooter.css" rel="stylesheet" type="text/css">
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
<div class="tip">
    TRACK<br>
    <a href="FavoriteServlet">back</a>
</div>
<section>

    <%
        if(request.getSession().getAttribute("track") != null) {
            List<Image> list = (List<Image>) request.getSession().getAttribute("track");

            if(list.size() <= 10) {
                JspFunction.outputTrack(out, list);
            } else {
                List<Image> shortList = new ArrayList<>();
                for(int i = 0; i < 10; i++) {
                    shortList.add(list.get(i));
                }
                JspFunction.outputTrack(out, shortList);
            }
        } else {
            request.getSession().setAttribute("noTracks", true);
            request.getRequestDispatcher("FavoriteServlet").forward(request,response);
        }
    %>

</section>
<footer>
    <label>Copyright&copy; 2020 Outstanding Ability of Software Development</label>
</footer>
</body>
</html>
