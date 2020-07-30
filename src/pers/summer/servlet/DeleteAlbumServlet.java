package pers.summer.servlet;

import pers.summer.impl.ImageDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteAlbumServlet", urlPatterns = {"/DeleteAlbumServlet"})
public class DeleteAlbumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageID = request.getParameter("tool");
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();

        if(imageDAOImpl.deleteImage(imageID) > 0) {
            response.sendRedirect("/SummerProject_war_exploded/AlbumServlet");
        }
    }
}
