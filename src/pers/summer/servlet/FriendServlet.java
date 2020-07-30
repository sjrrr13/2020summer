package pers.summer.servlet;

import pers.summer.entity.User;
import pers.summer.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FriendServlet", urlPatterns = {"/FriendServlet"})
public class FriendServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        List<User> list = userDAOImpl.getFriend(user.getUid());

        request.setAttribute("friend", list);
        request.getRequestDispatcher("/friend.jsp").forward(request, response);
    }
}
