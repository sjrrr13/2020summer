package pers.summer.servlet;

import pers.summer.entity.Image;
import pers.summer.impl.ImageDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DetailServlet", urlPatterns = {"/DetailServlet"})
public class DetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageID = request.getParameter("id");
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();

        Image image = imageDAOImpl.getSingleWithID(imageID);
        request.setAttribute("image", image);

        if(request.getSession().getAttribute("track") != null) {
            List<Image> list = (List<Image>) request.getSession().getAttribute("track");
            list.add(imageDAOImpl.getSingleWithID(imageID));
            request.getSession().setAttribute("track", list);
        } else {
            List<Image> list = new ArrayList<>();
            list.add(imageDAOImpl.getSingleWithID(imageID));
            request.getSession().setAttribute("track", list);
        }

        request.getRequestDispatcher("/detail.jsp").forward(request, response);
    }
}
