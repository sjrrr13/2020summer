package pers.summer.util;

import pers.summer.entity.Image;
import pers.summer.entity.User;
import pers.summer.impl.ImageDAOImpl;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JspFunction {
    //主页输出轮播图片的函数
    public static void getWrap(JspWriter out, List<String> list) throws IOException {
        String wrap = "";
        String info = "";
        String[] infos = null;
        String num = "";

        for(int i = 0; i < list.size(); i++) {
            info = list.get(i);
            infos = info.split("&");
            num = "" + (i + 1);

            wrap =
                    "   <a href=\"DetailServlet?id=" + infos[0] + "\">" +
                    "       <img src=\"image/travel-images/large/" + infos[1] +
                    "\" id=\"image" + num + "\">" +
                    "   </a>";

            out.print(wrap);
        }
    }

    //主页输出最新图片的函数
    public static void getNew(JspWriter out, List<Image> list) throws IOException {
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();

        for(int i = 0; i < list.size(); i++) {
            int num = i + 1;

            String newImage =
                    "<div class=\"block\">" +
                    "<a href=\"DetailServlet?id=" + list.get(i).getImageID() + "\">" +
                    "<img src=\"image/travel-images/large/" + list.get(i).getPATH() + "\" " +
                     "id=\"image" + num  + "\">" +
                    "</a>" +
                    "<div>" +
                    "<span class=\"title\">" + list.get(i).getTitle() + "</span><br>" +
                    "<div class=\"info\">" + imageDAOImpl.getUser(list.get(i).getUID()) +
                    "&nbsp &nbsp &nbsp" + list.get(i).getContent() + "<br>" +
                    list.get(i).getTime() +
                    "</div>" +
                    "</div>" +
                    "</div>";

            out.print(newImage);
        }
    }

    //图片详情页面输出函数（有登陆的情况）
    public static void outputDetail(JspWriter out, Image image, User user) throws IOException {
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
        String country = imageDAOImpl.getCountry(image.getCountry_RegionCodeISO());
        country = (country != null) ? country : "Unknown";
        String city = imageDAOImpl.getCity(image.getCityCode());
        city = (city != null) ? city : "Unknown";
        String des = image.getDescription();
        des = (des != null) ? des : "Unknown";
        boolean isFav = imageDAOImpl.isFav(user.getUid(), image.getImageID());
        String btn = isFav ? "<input type=\"button\" id=\"added\" value=\"Add To Favorite\" onclick=\"reminder()\">"
                : "<input type=\"submit\" value=\"ADD TO FAVORITE\">";

        String content =
                "<div class=\"title\">" +
                image.getTitle() + "<span>BY " + imageDAOImpl.getUser(image.getUID()) + "</span>" +
                "</div>" +
                "<div class=\"show_area\">" +
                "<img id=\"image\" src=\"image/travel-images/large/" + image.getPATH() + "\">" +
                "<div class=\"detail\">" +
                "<table><tr><th>COUNTRY:</th><td>" + country + "</td></tr>" +
                "<tr><th>CITY:</th><td>" + city + "</td></tr>" +
                "<tr><th>POST TIME:</th><td>" + image.getTime() + "</td></tr>" +
                "<tr><th>CONTENT:</th><td>" + image.getContent() + "</td></tr></table>" +
                "<form action=\"FavServlet\" method=\"post\" name=\"favorite\">" +
                "<input name=\"tool\" type=\"text\" value=\"" + image.getImageID() + "\">" +
                btn +
                "</form>" +
                "<div class=\"liked\">Like Number: <span>" + imageDAOImpl.getFavNum(image.getImageID()) + "</span></div>" +
                "</div>" +
                "</div>" +
                "<div class=\"description\"><span>DESCRIPTION:&nbsp;&nbsp;</span>" + des +
                "</div>";

        out.print(content);
    }

    //图片详情页面输出函数（无登录的情况，这种情况下无法对图片进行收藏）
    public static void outputDetailNoUser(JspWriter out, Image image) throws IOException {
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
        String country = imageDAOImpl.getCountry(image.getCountry_RegionCodeISO());
        country = (country != null) ? country : "Unknown";
        String city = imageDAOImpl.getCity(image.getCityCode());
        city = (city != null) ? city : "Unknown";
        String des = image.getDescription();
        des = (des != null) ? des : "Unknown";

        String content =
                "<div class=\"title\">" +
                image.getTitle() + "<span>BY " + imageDAOImpl.getUser(image.getUID()) + "</span>" +
                "</div>" +
                "<div class=\"show_area\">" +
                "<img id=\"image\" src=\"image/travel-images/large/" + image.getPATH() + "\">" +
                "<div class=\"detail\">" +
                "<table><tr><th>COUNTRY:</th><td>" + country + "</td></tr>" +
                "<tr><th>CITY:</th><td>" + city + "</td></tr>" +
                "<tr><th>POST TIME:</th><td>" + image.getTime() + "</td></tr>" +
                "<tr><th>CONTENT:</th><td>" + image.getContent() + "</td></tr></table>" +
                "<form action=\"FavServlet\" method=\"post\" name=\"favorite\">" +
                "<input name=\"tool\" type=\"text\" value=\"" + image.getImageID() + "\">" +
                "<input type=\"button\" id=\"added\" value=\"Add To Favorite\" onclick=\"reminderNoUser()\">" +
                "</form>" +
                "<div class=\"liked\">Like Number: <span>" + imageDAOImpl.getFavNum(image.getImageID()) + "</span></div>" +
                "</div>" +
                "</div>" +
                "<div class=\"description\"><span>DESCRIPTION:&nbsp;&nbsp;</span>" + des +
                "</div>";

        out.print(content);
    }

    //搜索页面输出搜索结果的函数（不需要分页）
    public static void outputResult(JspWriter out, List<Image> list) throws IOException {
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
        String country = "";
        String city = "";
        String description = "";

        for(Image image: list) {
            country = imageDAOImpl.getCountry(image.getCountry_RegionCodeISO());
            country = country == null ? "Unknown" : country;
            city = imageDAOImpl.getCity(image.getCityCode());
            city = city == null ? "Unknown" : city;
            description = image.getDescription() == null ? "No Description" : image.getDescription();

            String content =
                    "<div class=\"container\">" +
                    "<div class=\"image\">" +
                    "<a href=\"DetailServlet?id=" + image.getImageID() + "\">" +
                    "<img src=\"image/travel-images/large/" + image.getPATH() + "\">" +
                    "</a>" + "</div>" + "<div class=\"info\">" +
                    "<div class=\"title\">" + image.getTitle() + "</div>" +
                    "<div class=\"country\">Country: &nbsp " + country + "</div>" +
                    "<div class=\"city\">City: &nbsp " + city + "</div>" +
                    "<div class=\"hot\">Like Number: &nbsp " +imageDAOImpl.getFavNum(image.getImageID()) + "</div>" +
                    "<div class=\"description\">" + description + ",&nbsp" + image.getTime() + "</div>" +
                    "</div>" + "</div>";
            out.print(content);
        }
    }

    //搜索页面输出搜索结果的函数（需要分页）
    public static void outputResults(JspWriter out, List<Image> list) throws IOException {
        int pageNumber = (list.size() % 6 == 0) ? (list.size() / 6) : (list.size() / 6 + 1);
        List<Image> page = new ArrayList<>();
        String btn = "<div class=\"button\">" +
                "<div id=\"pre\">&lt;</div>";

        for(int i = 1; i <= pageNumber; i++) {
            page.clear();
            for(int j = (i - 1) * 6; j < i * 6; j++) {
                if(j < list.size()) {
                    page.add(list.get(j));
                }
            }
            if(i == 1) {
                out.print("<div class=\"pages\" id=\"page" + i + "\" style=\"display:block\">");
                outputResult(out, page);
                out.print("</div>");
                btn += "<div class=\"btns\" id=\"btn" + i + "\" style=\"color:blue\">" + i + "</div>";
            } else {
                out.print("<div class=\"pages\" id=\"page" + i + "\" style=\"display:none\">");
                outputResult(out, page);
                out.print("</div>");
                btn += "<div class=\"btns\" id=\"btn" + i + "\" style=\"color:black\">" + i + "</div>";
            }
        }
        btn += "<div id=\"next\">&gt;</div>";
        out.print(btn);
    }

    //收藏页面输出函数
    public static void outputFavorite(JspWriter out, List<Image> list) throws IOException {
        for(Image image: list) {
            String content =
                    "<div class=\"block\">" +
                    "<a href=\"DetailServlet?id=" + image.getImageID() + "\">" +
                    "<img src=\"image/travel-images/large/" + image.getPATH() + "\">" +
                     "</a>" + "<div class=\"content\">" +
                     "<div class=\"title\">" + image.getTitle() + "</div>" +
                     "<div class=\"info\">" + image.getDescription() + "</div>" +
                     "<div class=\"button\">" + "<form action=\"DeleteFavorServlet\" method=\"post\">" +
                     "<input name=\"tool\" id=\"tool\" value=\"" + image.getImageID() + "\">" +
                     "<input type=\"submit\" id=\"submit\" value=\"DELETE\">" +
                     "</form>" + "</div>" + "</div>" + "</div>";

            out.print(content);
        }
    }

    //好友收藏图片输出函数
    public static void outputFriendFav(JspWriter out, List<Image> list) throws IOException {
        for(Image image: list) {
            String content =
                    "<div class=\"block\">" +
                            "<a href=\"DetailServlet?id=" + image.getImageID() + "\">" +
                            "<img src=\"image/travel-images/large/" + image.getPATH() + "\">" +
                            "</a>" + "<div class=\"content\">" +
                            "<div class=\"title\">" + image.getTitle() + "</div>" +
                            "<div class=\"info\">" + image.getDescription() + "</div>" +
                            "</div>" + "</div>";

            out.print(content);
        }
    }

    //好友收藏图片分页输出函数
    public static void outputFriendFavs(JspWriter out, List<Image> list) throws IOException {
        int pageNumber = (list.size() % 6 == 0) ? (list.size() / 6) : (list.size() / 6 + 1);
        List<Image> page = new ArrayList<>();
        String btn = "<div class=\"pageButton\">" +
                "<div id=\"pre\">&lt;</div>";

        for(int i = 1; i <= pageNumber; i++) {
            page.clear();
            for(int j = (i - 1) * 6; j < i * 6; j++) {
                if(j < list.size()) {
                    page.add(list.get(j));
                }
            }
            if(i == 1) {
                out.print("<div class=\"pages\" id=\"page" + i + "\" style=\"display:block\">");
                outputFriendFav(out, page);
                out.print("</div>");
                btn += "<div class=\"btns\" id=\"btn" + i + "\" style=\"color:blue\">" + i + "</div>";
            } else {
                out.print("<div class=\"pages\" id=\"page" + i + "\" style=\"display:none\">");
                out.print("</div>");
                btn += "<div class=\"btns\" id=\"btn" + i + "\" style=\"color:black\">" + i + "</div>";
            }
        }
        btn += "<div id=\"next\">&gt;</div>";
        out.print(btn);
    }

    //收藏页面分页输出函数
    public static void outputFavorites(JspWriter out, List<Image> list) throws IOException {
        int pageNumber = (list.size() % 6 == 0) ? (list.size() / 6) : (list.size() / 6 + 1);
        List<Image> page = new ArrayList<>();
        String btn = "<div class=\"pageButton\">" +
                "<div id=\"pre\">&lt;</div>";

        for(int i = 1; i <= pageNumber; i++) {
            page.clear();
            for(int j = (i - 1) * 6; j < i * 6; j++) {
                if(j < list.size()) {
                    page.add(list.get(j));
                }
            }
            if(i == 1) {
                out.print("<div class=\"pages\" id=\"page" + i + "\" style=\"display:block\">");
                outputFavorite(out, page);
                out.print("</div>");
                btn += "<div class=\"btns\" id=\"btn" + i + "\" style=\"color:blue\">" + i + "</div>";
            } else {
                out.print("<div class=\"pages\" id=\"page" + i + "\" style=\"display:none\">");
                outputFavorite(out, page);
                out.print("</div>");
                btn += "<div class=\"btns\" id=\"btn" + i + "\" style=\"color:black\">" + i + "</div>";
            }
        }
        btn += "<div id=\"next\">&gt;</div>";
        out.print(btn);
    }

    //我的照片页面输出函数
    public static void outputAlbum(JspWriter out, List<Image> list) throws IOException {
        for (Image image : list) {
            String content =
                    "<div class=\"block\">" +
                            "<a href=\"DetailServlet?id=" + image.getImageID() + "\">" +
                            "<img src=\"image/travel-images/large/" + image.getPATH() + "\">" +
                            "</a>" + "<div class=\"content\">" +
                            "<div class=\"title\">" + image.getTitle() + "</div>" +
                            "<div class=\"info\">" + image.getDescription() + "</div>" +
                            "<div class=\"button\">" +
                            "<form action=\"ModifyServlet\" method=\"post\">" +
                            "<input name=\"tool\" value=\"" + image.getImageID() + "\">" +
                            "<input type=\"submit\" value=\"MODIFY\">" + "</form>" +
                            "<form action=\"DeleteAlbumServlet\" method=\"post\">" +
                            "<input name=\"tool\" value=\"" + image.getImageID() + "\">" +
                            "<input type=\"submit\" value=\"DELETE\">" + "</form>" +
                            "</div>" + "</div>" + "</div>";

            out.print(content);
        }
    }

    //我的照片页面分页输出函数
    public static void outputAlbums(JspWriter out, List<Image> list) throws IOException {
        int pageNumber = (list.size() % 6 == 0) ? (list.size() / 6) : (list.size() / 6 + 1);
        List<Image> page = new ArrayList<>();
        String btn = "<div class=\"pageButton\">" +
                "<div id=\"pre\">&lt;</div>";

        for(int i = 1; i <= pageNumber; i++) {
            page.clear();
            for(int j = (i - 1) * 6; j < i * 6; j++) {
                if(j < list.size()) {
                    page.add(list.get(j));
                }
            }
            if(i == 1) {
                out.print("<div class=\"pages\" id=\"page" + i + "\" style=\"display:block\">");
                outputAlbum(out, page);
                out.print("</div>");
                btn += "<div class=\"btns\" id=\"btn" + i + "\" style=\"color:blue\">" + i + "</div>";
            } else {
                out.print("<div class=\"pages\" id=\"page" + i + "\" style=\"display:none\">");
                outputAlbum(out, page);
                out.print("</div>");
                btn += "<div class=\"btns\" id=\"btn" + i + "\" style=\"color:black\">" + i + "</div>";
            }
        }
        btn += "<div id=\"next\">&gt;</div>";
        out.print(btn);
    }
    
    //上传页面输出函数
    public static void outputPost(JspWriter out) throws IOException {
        String content = 
                "<form action=\"PostServlet\" method=\"post\" enctype=\"multipart/form-data\">" +
                "<div class=\"container\">" +
                "<div id=\"tip\">Picture not uploaded</div>" +
                "<div class=\"preview\" id=\"content\"></div>" +
                "<div class=\"button\"><span>CHOOSE A PICTURE</span><br>" +
                "<input type=\"file\" name=\"file\" accept=\"image/*\" onchange=\"uploadImg(event,this)\">" +
                "</div>" +
                "</div>" +
                "<div class=\"info\">" +
                "<div class=\"tip\">Title</div>" +
                "<input type=\"text\" name=\"title\" id=\"title\" class=\"description\">" +
                "<div class=\"tip\">Description</div>" +
                "<textarea class=\"description\" id=\"des\" name=\"description\" rows=\"3\"></textarea>" +
                "<div class=\"tip\">Country</div>" +
                "<select class=\"description\" id=\"country\" name=\"country\" onchange=\"changeCity()\">" +
                "<option value=\"0\">-Choose a country-</option>" +
                "</select>" +
                "<div class=\"tip\">City</div>" +
                "<select class=\"description\" id=\"city\" name=\"city\">" +
                "<option value=\"0\">-Choose a city-</option>" +
                "</select>" +
                "<div class=\"tip\">Content</div>" +
                "<input type=\"text\" name=\"content\" id=\"theme\" class=\"description\">" +
                "<input type=\"submit\" name=\"postBtn\" value=\"UPLOAD\" onclick=\"return check()\">" +
                "</div>" +
                "</form>";

        out.print(content);
    }

    //修改页面输出函数
    public static void outputModify(JspWriter out, Image image) throws IOException {
        ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
        String country = imageDAOImpl.getCountry(image.getCountry_RegionCodeISO());
        String city = imageDAOImpl.getCity(image.getCityCode());

        String content =
                "<form action=\"GetModifyServlet\" method=\"post\">" +
                "<div class=\"container\">" +
                "<div class=\"preview\" id=\"content\">" +
                "<a href=\"DetailServlet?id=" + image.getImageID() + "\">" +
                "<img src=\"image/travel-images/large/" + image.getPATH() + "\">" + "</a>" +
                "</div>" +
                "</div>" +
                "<div class=\"info\">" +
                "<div class=\"tip\">Title</div>" +
                "<input type=\"text\" name=\"title\" id=\"title\" class=\"description\" value=\"" +
                        image.getTitle() + "\">" +
                "<div class=\"tip\">Description</div>" +
                "<textarea class=\"description\" id=\"des\" name=\"description\" rows=\"3\">" +
                image.getDescription() + "</textarea>" +
                "<div class=\"tip\">Country</div>" +
                "<select class=\"description\" id=\"country\" name=\"country\" value=\"" + country + "\" onchange=\"changeCity()\">" +
                "<option value=\"" + country + "\">" + country + "</option>" +
                "</select>" +
                "<div class=\"tip\">City</div>" +
                "<select class=\"description\" id=\"city\" value=\"" + city + "\" name=\"city\">" +
                "<option value=\"" + city + "\">" + city + "</option>" +
                "</select>" +
                "<div class=\"tip\">Content</div>" +
                "<input type=\"text\" name=\"content\" id=\"theme\" class=\"description\" value=\"" +
                        image.getContent() + "\">" +
                "<input type=\"text\" name=\"tool\" id=\"tool\" value=\"" + image.getImageID() + "\">" +
                "<input type=\"submit\" name=\"postBtn\" value=\"MODIFY\" onclick=\"return check()\">" +
                "</div>" +
                "</form>";

        out.print(content);
    }

    //足迹页面输出函数
    public static void outputTrack(JspWriter out, List<Image> list) throws IOException {
        String content = "";

        for(Image image: list) {
            content =
                    "<div class=\"block\">" +
                    "<a href=\"DetailServlet?id=\"" + image.getImageID() + "\">" +
                    "<img src=\"image/travel-images/large/" + image.getPATH() + "\">" +
                    "</a>" +
                    "<div class=\"title\">" + image.getTitle() +
                    "</div>" +
                    "</div>";

            out.print(content);
        }
    }

    //好友页面输出函数
    public static void outputFriend(JspWriter out, List<User> list) throws IOException {
        String content = "";

        for(User user: list) {
            Date date = user.getDateJoined();
            content =
                    "<div class=\"block\">" +
                    "<div class=\"name\">" +
                    "<a href=\"FriendFavServlet?id=" + user.getUid() + "\">" + user.getUserName() + "</a></div>" +
                    "<div class=\"email\">" +
                    "<a href=\"FriendFavServlet?id=" + user.getUid() + "\">" + user.getEmail() + "</a></div>" +
                    "<div class=\"time\">" + date + "</div>" +
                    "</div>";

            out.print(content);
        }
    }

}
