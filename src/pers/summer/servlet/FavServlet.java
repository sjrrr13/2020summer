package pers.summer.servlet;

import pers.summer.entity.User;
import pers.summer.impl.ImageDAOImpl;
import pers.summer.impl.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FavServlet", urlPatterns = {"/FavServlet"})
public class FavServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageID = request.getParameter("tool");
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();

        User user = (User) request.getSession().getAttribute("user");
        String uid = "" + user.getUid();
        imageDAOImpl.addFav(imageDAOImpl.getSingleWithID(imageID), uid);
        request.setAttribute("success", true);
        request.setAttribute("image", imageDAOImpl.getSingleWithID(imageID));

        request.getRequestDispatcher("/detail.jsp").forward(request, response);
    }
}
