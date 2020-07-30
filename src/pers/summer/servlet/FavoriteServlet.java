package pers.summer.servlet;

import pers.summer.entity.Image;
import pers.summer.entity.User;
import pers.summer.impl.ImageDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FavoriteServlet", urlPatterns = {"/FavoriteServlet"})
public class FavoriteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
        User user = (User) request.getSession().getAttribute("user");
        List<Image> favorite = imageDAOImpl.getFavorite(user.getUid());

        request.setAttribute("favList", favorite);
        request.getRequestDispatcher("/favorite.jsp").forward(request, response);
    }
}
