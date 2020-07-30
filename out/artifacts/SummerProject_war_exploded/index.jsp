<%@ page import="pers.summer.impl.ImageDAOImpl" %>
<%@ page import="pers.summer.entity.Image" %>
<%@ page import="java.util.List" %>
<%@ page import="pers.summer.util.JspFunction" %>
<%@ page import="pers.summer.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/17
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>HOME</title>
  <link href="css/reset.css" rel="stylesheet" type="text/css">
  <link href="css/header.css" rel="stylesheet" type="text/css">
  <link href="css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
  <img src="image/icon/fish.jpg" alt="flag">
  <div class="links">
    <a href="index.jsp" class="currentTag">HOME</a>
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
    <script>

      <%
      User user = (User) request.getSession().getAttribute("user");
        out.print("alert(\"Hello, " + user.getUserName() + ");");
      %>

    </script>

    <%
    } else {
      out.print("<a href=\"login.jsp\">SIGN IN</a>");
    }
    %>

  </div>
</header>
<section class="content">
  <div class="wrapBox">
    <div class="wrap">
      <div class="wrapImages" id="wrapImages" style="left: 0">

      <%
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
        List<String> hotImage = imageDAOImpl.getFav();

        JspFunction.getWrap(out, hotImage);
      %>

      </div>
      <div class="buttons">
        <span data-index='0' style="background-color: rgb(215, 81, 15)"></span>
        <span data-index='1' style=""></span>
        <span data-index='2' style=""></span>
      </div>
    </div>
  </div>
  <div class="images" id="images">

    <%
      List<Image> newImage = imageDAOImpl.getNew();

      JspFunction.getNew(out, newImage);
    %>

  </div>
</section>
<footer>
  <div >
    TERMS<br><br>
    PRIVACY
  </div>
  <div>
    HELP<br><br>
    CONSULT
  </div>
  <div>
    <img src="image/icon/QRcode.png" alt="QRcode">
  </div>
  <div class="bottom">
    <label>Copyright&copy; 2020 Outstanding Ability of Software Development</label>
  </div>
</footer>
<script>
  let imageBox = document.getElementById("wrapImages");
  let button = document.querySelectorAll('.buttons span');

  function move() {
    if(imageBox.style.left == "-108em") {
      imageBox.style.left = "0";
      chooseButton(0);
    } else if(imageBox.style.left == "-54em") {
      imageBox.style.left = "-108em";
      chooseButton(2);
    } else {
      imageBox.style.left = "-54em";
      chooseButton(1);
    }
  }

  function chooseButton(i) {
    for(let j = 0; j < 3; j++) {
      if(j != i) {
        button[j].style.backgroundColor = "#eee";
      } else {
        button[i].style.backgroundColor = "rgb(215, 81, 15)";
      }
    }

  }

  for(let i = 0; i < 3; i++) {
    button[i].onclick = function() {
      imageBox.style.left = -54 * this.getAttribute('data-index') + 'em';
      chooseButton(i);
    }
  }

  imageBox.onmouseover = function() {
    clearInterval(window.timer);
  };

  imageBox.onmouseout = function() {
    window.timer=setInterval(move,2000);
  };

  window.timer = setInterval(move, 2000);
</script>
</body>
</html>
