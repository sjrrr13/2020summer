package pers.summer.servlet;

import pers.summer.entity.Image;
import pers.summer.impl.ImageDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyServlet", urlPatterns = {"/ModifyServlet"})
public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
        String imageID = request.getParameter("tool");
        Image image = imageDAOImpl.getSingleWithID(imageID);

        request.setAttribute("imageToModify", image);
        request.getRequestDispatcher("/post.jsp").forward(request, response);
    }
}
