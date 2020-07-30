package pers.summer.servlet;

import pers.summer.entity.User;
import pers.summer.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收从前端传过来的数据，创建User对象
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        Date date = new Date((new java.util.Date()).getTime());

        User user = new User(null, userName, email, pass, 1, date, date, 1);

        //创建UserDaoImpl实例
        UserDAOImpl userRegister = new UserDAOImpl();
        int result = userRegister.register(user);

        if(result > 0) {
            //设置session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            //请求转发
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.setAttribute("error", true);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
