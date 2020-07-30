<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/18
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <link href="css/header.css" rel="stylesheet" type="text/css">
    <link href="css/form.css" rel="stylesheet" type="text/css">
    <link href="css/fixedFooter.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <img src="image/icon/fish.jpg" alt="flag">
    <div class="links">
        <a href="index.jsp">HOME</a>
        <a href="search.jsp">SEARCH</a>
        <a href="login.jsp" class="currentTag">SIGN IN</a>
    </div>
</header>
<section>
    <form action="LoginServlet" method="post" name="loginForm">
        <div class="tip">User name/E-mail</div>
        <input class="inputBox" type="text" name="userName" autocomplete="off">
        <div class="tip">Password</div>
        <input class="inputBox" type="password" name="password" autocomplete="new-password">
        <br>
        <input type="submit" value="Sign in" id="submitBtn" onclick="return check()">
    </form>
    <div class="help">
        New to Fisher?
        <a href="register.jsp">Create a new account!</a>
    </div>
</section>
<footer>
    <label>Copyright&copy; 2020 Outstanding Ability of Software Development</label>
</footer>
<script>

    <%
        if(request.getAttribute("myLoginError") != null) {
            String content =
                    "alert(\"Wrong userName or password\");\n" +
                    "document.loginForm.userName.value = \"" + request.getAttribute("userName") + "\";";

            out.print(content);
    }
    %>

    function check() {
        //获取表单数据
        let username = document.loginForm.userName.value;
        let pwd = document.loginForm.password.value;

        if(username == "" || pwd == "") {
            alert("Input cannot be empty");
            return false;
        }

        return true;
    }
</script>
</body>
</html>
