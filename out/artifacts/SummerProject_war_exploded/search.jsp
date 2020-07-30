<%@ page import="pers.summer.entity.Image" %>
<%@ page import="java.util.List" %>
<%@ page import="pers.summer.util.JspFunction" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/19
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SEARCH</title>
    <link href="css/header.css" rel="stylesheet" type="text/css">
    <link href="css/search.css" rel="stylesheet" type="text/css">
    <link href="css/fixedFooter.css" rel="stylesheet" type="text/css" id="footerCss">
</head>
<body>
<script>
    function chooseCss() {
        let height = window.screen.availHeight;
        let imgHeight = document.body.clientHeight;
        let css = document.getElementById("footerCss");
        if(height > imgHeight) {
            css.setAttribute("href", "css/fixedFooter.css");
        } else {
            css.setAttribute("href", "css/flowFooter.css");
        }
    }
</script>
<header>
    <img src="image/icon/fish.jpg" alt="flag">
    <div class="links">
        <a href="index.jsp">HOME</a>
        <a href="search.jsp" class="currentTag">SEARCH</a>

        <%
            if (request.getSession().getAttribute("user") != null) {
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
    <div class="search">
        <form name="searchForm" action="SearchServlet" method="post">
            <div class="input">
                <input type="search" name="searchTitle">
                <input type="image" src="image/icon/search.jpg">
            </div>
            <div class="radio">
                <label><input type="radio" name="searchBy" value="title" checked>Search By Title</label>
                <label><input type="radio" name="searchBy" value="content">Search By Content</label>
            </div>
            <label id="break">|</label>
            <div class="radio">
                <label><input type="radio" name="orderBy" value="hot" checked>Order By Popularity</label>
                <label><input type="radio" name="orderBy" value="time">Order By Time</label>
            </div>
        </form>
    </div>

        <%
            if(request.getAttribute("result") != null) {
                List<Image> list = (List<Image>) request.getAttribute("result");
                if(list.size() > 0) {
                    out.print("<div class=\"result\">");
                    if(list.size() <= 6) {
                        JspFunction.outputResult(out, list);
                    } else {
                        JspFunction.outputResults(out, list);
                    }
                    out.print("</div>");

                    if(list.size() > 4) {
                        String chooseCss = "<script>" +
                                "let css = document.getElementById(\"footerCss\");" +
                                "css.setAttribute(\"href\", \"css/flowFooter.css\");" +
                                "</script>";
                        out.print(chooseCss);
                    }
                } else {
                    out.print("<script>alert(\"No Result\")</script>");
                }
            }
        %>

</section>
<footer>
    <label>Copyright&copy; 2020 Outstanding Ability of Software Development</label>
</footer>
<script>
    let pages = document.getElementsByClassName("pages");
    let btns = document.getElementsByClassName("btns");
    let pageNum = pages.length;
    let preBtn = document.getElementById("pre");
    let nextBtn = document.getElementById("next");

    preBtn.onclick = function () {
        if(pages[0].style.display != "none") {
            alert("It's the first page");
        } else {
            for(let i = 0; i < pageNum; i++) {
                if(pages[i].style.display != "none") {
                    pages[i].style.display = "none";
                    pages[i - 1].style.display = "";
                    btns[i].style.color = "black";
                    btns[i - 1].style.color = "blue";
                    break;
                }
            }
        }
    };

    nextBtn.onclick = function () {
        if(pages[pageNum - 1].style.display != "none") {
            alert("It's the last page");
        } else {
            for(let i = 0; i < pages.length; i++) {
                if(pages[i].style.display != "none") {
                    pages[i].style.display = "none";
                    pages[i + 1].style.display = "block";
                    btns[i].style.color = "black";
                    btns[i + 1].style.color = "blue";
                    break;
                }
            }
        }
    };

    for(let i = 0; i < pageNum; i++) {
        btns[i].onclick = function () {
            for(let j = 0; j < pageNum; j++) {
                pages[j].style.display = "none";
                btns[j].style.color = "black";
            }
            pages[i].style.display = "block";
            btns[i].style.color = "blue";
        }
    }
</script>
</body>
</html>
