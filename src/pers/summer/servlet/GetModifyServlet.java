package pers.summer.servlet;

import pers.summer.impl.ImageDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetModifyServlet", urlPatterns = {"/GetModifyServlet"})
public class GetModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();

        String imageID = request.getParameter("tool");
        String title = request.getParameter("title");

        String description = request.getParameter("description");

        String country = request.getParameter("country");
        String country_RegionCodeISO = imageDAOImpl.getCodeWithCountry(country);

        String city = request.getParameter("city");
        Long cityCode = imageDAOImpl.getCodeWithCity(city);

        String content = request.getParameter("content");
        
        if(imageDAOImpl.modifyImage(title, description, country_RegionCodeISO,
                cityCode, content, imageID) > 0) {
            response.sendRedirect("/SummerProject_war_exploded/AlbumServlet");
        }
    }
}

