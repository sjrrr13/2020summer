package pers.summer.servlet;

import pers.summer.entity.Image;
import pers.summer.impl.ImageDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchContent = request.getParameter("searchTitle");
        String searchBy = request.getParameter("searchBy");
        String orderBy = request.getParameter("orderBy");
        String sql = "SELECT * FROM travelimage WHERE ";
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
        List<Image> list = null;

        if(searchBy.equals("title")) {
            sql += "Title Like \"%" + searchContent + "%\"";
        } else {
            sql += "Content Like \"%" + searchContent + "%\"";
        }

        if(orderBy.equals("time")) {
            list = imageDAOImpl.getSearchResultByTime(sql);
        } else {
            list = imageDAOImpl.getSearchResultByHot(sql);
        }

        request.setAttribute("result", list);
        request.getRequestDispatcher("/search.jsp").forward(request, response);
    }

}
