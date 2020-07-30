package pers.summer.servlet;

import pers.summer.dao.UserDAO;
import pers.summer.entity.User;
import pers.summer.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收从前端传过来的数据
        String userName = request.getParameter("userName");
        String pass = request.getParameter("password");

        //创建UserDaoImpl实例
        long result = 0;
        User user = null;
        UserDAOImpl userLogin = new UserDAOImpl();
        if(!userName.contains("@")) {
            result = userLogin.login(userName, pass);
            user = userLogin.getSingleWithName(userName);
        } else {
            result = userLogin.loginWithEmail(userName, pass);
            user = userLogin.getSingleWithEmail(userName);
        }

        if (result > 0) {
            //设置session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            //请求转发
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.setAttribute("myLoginError", true);
            request.setAttribute("userName", userName);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
