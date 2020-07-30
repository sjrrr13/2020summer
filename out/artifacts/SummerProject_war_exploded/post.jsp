<%@ page import="pers.summer.util.JspFunction" %>
<%@ page import="pers.summer.entity.Image" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/28
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>POST</title>
    <link href="css/header.css" rel="stylesheet" type="text/css">
    <link href="css/post.css" rel="stylesheet" type="text/css">
    <link href="css/flowFooter.css" rel="stylesheet" type="text/css">
</head>
<body onload="initCountry()">
<header>
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
</header>
<section>

    <%
        if(request.getAttribute("imageToModify") == null) {
            JspFunction.outputPost(out);
        } else {
            Image image = (Image) request.getAttribute("imageToModify");
            JspFunction.outputModify(out, image);
        }
    %>

</section>
<footer>
    <label>Copyright&copy; 2020 Outstanding Ability of Software Development</label>
</footer>
<script src="js/city.js"></script>
<script>
    let URL = window.URL || window.webkitURL || window.mozURL;
    let countryObj = document.getElementById("country");
    let city = document.getElementById("city");
    let title = document.getElementById("title");
    let des = document.getElementById("des");
    let content = document.getElementById("theme");

    function uploadImg(e,dom) {
        document.getElementById("content").innerHTML = "";
        var e = event || e;
        let fileObj = dom instanceof HTMLElement ? dom.files[0] : $(dom)[0].files[0];
        console.log(e);
        console.log(dom);
        let container = document.querySelector('.preview');
        let img = new Image();
        img.src = URL.createObjectURL(fileObj);
        console.log(img.src);
        img.onload = function() {
            container.appendChild(img)
        };
        document.getElementById("tip").style.display = "none";
    }

    function initCountry() {
        for(let key in cities){
            let option = new Option(key, key);
            countryObj.add(option);
        }
    }

    function changeCity() {
        let countryName = document.getElementById("country").value;
        city.innerHTML = "";
        for(let key in cities){
            if(key == countryName){
                let value = cities[key];
                for(let i in value){
                    let option = new Option(value[i], value[i]);
                    city.add(option);
                }
            }
        }
    }

    function check() {
        let canPost = true;
        if(title.value == "" || des.value == "" || countryObj.value == "0"
            || city.value == "0" || content.value == "") {
            alert("请输入完整的内容后再提交");
            canPost = false;
        }
        return canPost;
    }
</script>
</body>
</html>
