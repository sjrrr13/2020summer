<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/7/20
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>REGISTER</title>
    <link href="css/reset.css" rel="stylesheet" type="text/css">
    <link href="css/header.css" rel="stylesheet" type="text/css">
    <link href="css/form.css" rel="stylesheet" type="text/css">
    <link href="css/flowFooter.css" rel="stylesheet" type="text/css">
</head>
<body>

    <%
        if(request.getAttribute("error") != null) {
            out.print("<script>alert(\"The User Name already exists\")</script>");
        }
    %>

<header>
    <img src="image/icon/fish.jpg" alt="flag">
    <div class="links">
        <a href="index.jsp">HOME</a>
        <a href="search.jsp">SEARCH</a>
        <a href="login.jsp">SIGN IN</a>
    </div>
</header>
<section>
    <form action="RegisterServlet" method="post" name="registerForm">
        <div class="tip">User name</div>
        <input class="inputBox" type="text" name="userName" placeholder="4-15characters">
        <div class="tip">E-mail</div>
        <input class="inputBox" type="text" name="email">
        <div class="tip">
            Password &nbsp; &nbsp; <div id="reminder"></div>
        </div>
        <input class="inputBox" type="password" name="password" autocomplete="new-password"
               placeholder="6-12characters">
        <div class="tip">Confirm Password</div>
        <input class="inputBox" type="password" name="rePassword">
        <br>
        <input type="submit" value="Register" id="submitBtn" onclick="return check();">
    </form>
    <div class="help">
        Already have an account?
        <a href="index.jsp">Click here to sign in!</a>
        <br><br><br><br>
    </div>
</section>
<footer>
    <label>Copyright&copy; 2020 Outstanding Ability of Software Development</label>
</footer>
<script>
    let pwdBox = document.registerForm.password;

    //密码强度的判断
    pwdBox.onblur = function () {
        let strength = 0;
        let reminder = document.getElementById("reminder");
        let patterns = [/[0-9]/, /[a-z]/, /[A-Z]/, /[^0-9a-zA-Z]/];
        let pwd = document.registerForm.password.value;

        for(let i = 0; i < 4; i++) {
            if(patterns[i].test(pwd)){
                strength++;
            }
        }
        switch (strength) {
            case 1:
                reminder.innerHTML = "WEAK";
                reminder.style.color = "red";
                break;
            case 2:
                reminder.innerHTML = "MEDIUM";
                reminder.style.color = "orange";
                break;
            case 3:
                reminder.innerHTML = "STRONG";
                reminder.style.color = "green";
                break;
            default:
                reminder.innerHTML = "";
                break;
        }
    };

    //进行表单合法性验证
    function check() {
        //获取表单数据
        let userNameBox = document.registerForm.userName;
        let username = document.registerForm.userName.value;
        let emailBox = document.registerForm.email;
        let email = document.registerForm.email.value;
        let pwdBox = document.registerForm.password;
        let pwd = document.registerForm.password.value;
        let rePwdBox = document.registerForm.rePassword;
        let rePwd = document.registerForm.rePassword.value;

        //1.非空
        if(username == "" || email == "" || pwd == "" || rePwd == "") {
            alert("Input cannot be empty");
            userNameBox.value = username;
            emailBox.value = email;
            pwdBox.value = pwd;
            rePwdBox.value = "";
            return false;
        }

        //2.用户名长度4-15
        if(username.length < 4 || username.length > 15) {
            alert("The userName should be 4-15 characters");
            userNameBox.value = "";
            emailBox.value = email;
            pwdBox.value = pwd;
            rePwdBox.value = "";
            return false;
        }

        //3.邮箱地址的检验
        let pattern = /[A-Za-z0-9]\w{2,}@(\w{2,}\.){1,2}\w{2,4}/i;
        if(!(pattern.test(email))) {
            alert("invalid e-mail address");
            userNameBox.value = username;
            emailBox.value = "";
            pwdBox.value = pwd;
            rePwdBox.value = "";
            return false;
        }

        //4.密码长度6-12
        if(pwd.length < 6 || pwd.length > 12) {
            alert("The password should be 6-12 characters");
            userNameBox.value = username;
            emailBox.value = email;
            pwdBox.value = "";
            rePwdBox.value = "";
            return false;
        }

        //5.密码和确认密码是否一致
        if(pwd !== rePwd) {
            alert("The password does not match the confirmation password");
            userNameBox.value = username;
            emailBox.value = email;
            pwdBox.value = "";
            rePwdBox.value = "";
            return false;
        }

        return true;
    }
</script>
</body>
</html>
