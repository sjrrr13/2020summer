<%@ page import="pers.summer.util.JspFunction" %>
<%@ page import="pers.summer.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/28
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FRIEND</title>
    <link href="css/header.css" rel="stylesheet" type="text/css">
    <link href="css/friend.css" rel="stylesheet" type="text/css">
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
<section>

    <%
        if(request.getAttribute("friend") != null) {
            List<User> list = (List<User>) request.getAttribute("friend");
            JspFunction.outputFriend(out, list);
        }
    %>

</section>
<footer>
    <label>Copyright&copy; 2020 Outstanding Ability of Software Development</label>
</footer>
</body>
</html>
