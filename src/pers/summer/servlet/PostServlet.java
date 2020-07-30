package pers.summer.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pers.summer.entity.Image;
import pers.summer.entity.User;
import pers.summer.impl.ImageDAOImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "PostServlet", urlPatterns = {"/PostServlet"})
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            post(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<FileItem> getRequestFileItems(HttpServletRequest request, ServletContext servletContext) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            String str = "javax.servlet.context.tempdir";
            File repository = (File) servletContext.getAttribute(str);
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                return upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private boolean saveFile(FileItem item, String fileName) {
        File savePath = new File("D:\\study\\2020summer\\Project\\web\\image\\travel-images\\large");
        if (!savePath.exists()) {
            savePath.mkdirs();
        }

        File uploadFile = new File(savePath + File.separator + fileName);

        try {
            item.write(uploadFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }
    }

    private String getPhotoNewName() {
        Date date = new Date();
        int second = getSecondTimestamp(date);
        return second + ".jpg";
    }

    private void post(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        List<String> list = new ArrayList<String>();
        String filename = getPhotoNewName();
        ServletContext servletContext = null;
        servletContext = request.getSession().getServletContext();

        List<FileItem> items = getRequestFileItems(request, servletContext);
        boolean isLoadToSQL = false;
        for (FileItem item : items) {
            if (!item.isFormField()) {
                isLoadToSQL = saveFile(item, filename);
            } else {
                list.add(item.getString("UTF-8"));
            }
        }

        User user = (User) request.getSession().getAttribute("user");
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();

        int uid = user.getUid();

        String title = list.get(0);

        String description = list.get(1);

        String country = list.get(2);
        String country_RegionCodeISO = imageDAOImpl.getCodeWithCountry(country);

        String city = list.get(3);
        Long cityCode = imageDAOImpl.getCodeWithCity(city);

        String content = list.get(4);

        Date time = new Date();

        int imageID = imageDAOImpl.getMaxImageID();

        Image image = new Image();
        image.setImageID(imageID);
        image.setTitle(title);
        image.setDescription(description);
        image.setLatitude(null);
        image.setLongitude(null);
        image.setCityCode(cityCode);
        image.setCountry_RegionCodeISO(country_RegionCodeISO);
        image.setUID(uid);
        image.setPATH(filename);
        image.setContent(content);
        image.setTime(time);

        if (imageDAOImpl.saveImage(image) > 0 && isLoadToSQL) {
            response.sendRedirect("/SummerProject_war_exploded/AlbumServlet");
        }
    }
}
