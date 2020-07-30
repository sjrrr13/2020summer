package pers.summer.servlet;

import pers.summer.entity.Image;
import pers.summer.entity.User;
import pers.summer.impl.ImageDAOImpl;
import pers.summer.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FriendFavServlet", urlPatterns = {"/FriendFavServlet"})
public class FriendFavServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("id");
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
        UserDAOImpl userDAOImpl = new UserDAOImpl();

        User user = userDAOImpl.getSingleWithID(uid);
        List<Image> list =  imageDAOImpl.getFavorite(user.getUid());

        request.setAttribute("friendName", user.getUserName());
        request.setAttribute("friendFav", list);
        request.setAttribute("canRead", user.getCanRead());

        request.getRequestDispatcher("/favorite.jsp").forward(request, response);
    }
}
