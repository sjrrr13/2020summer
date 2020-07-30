package pers.summer.impl;

import pers.summer.dao.DAO;
import pers.summer.dao.ImageDAO;
import pers.summer.entity.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageDAOImpl extends DAO<Image> implements ImageDAO {
    //获取最热图片的方法
    @Override
    public List<String> getFav() {
        List<String> hotImagePath = new ArrayList<>();
        String sql = "SELECT ImageID, count(*) AS count FROM travelimagefavor GROUP BY ImageID " +
                "ORDER BY count DESC LIMIT 3";

        List<Image> hotID = queryForList(sql);

        for(Image image: hotID) {
            sql = "SELECT * FROM travelimage WHERE ImageID = ?";
            Image hotImage = query(sql, image.getImageID());
            hotImagePath.add(hotImage.getImageID() + "&" + hotImage.getPATH());
        }

        return hotImagePath;
    }

    //获取最新图片的方法
    @Override
    public List<Image> getNew() {
        String sql = "SELECT * FROM travelimage ORDER BY Time desc LIMIT 6";
        return queryForList(sql);
    }

    //利用id获取单张图片的方法
    @Override
    public Image getSingleWithID(String id) {
        String sql = "SELECT * FROM travelimage WHERE ImageID = ?";
        return query(sql, id);
    }

    //获取图片所属国家的方法
    @Override
    public String getCountry(String iso) {
        String sql = "SELECT Country_RegionName FROM geocountries_regions WHERE ISO = ?";
        return queryForValue(sql, iso);
    }

    //获取图片所属城市的方法
    @Override
    public String getCity(Long code) {
        String sql = "SELECT AsciiName FROM geocities WHERE GeoNameID = ?";
        return queryForValue(sql, code);
    }

    //获取图片热度的方法
    @Override
    public Long getFavNum(Integer id) {
        String sql = "SELECT COUNT(*) FROM travelimagefavor WHERE ImageID = ?";
        return queryForValue(sql, id);
    }

    //获取图片作者的方法
    @Override
    public String getUser(int uid) {
        String sql = "SELECT UserName FROM traveluser WHERE UID = ?";
        return queryForValue(sql, uid);
    }

    //判断图片是否已经被收藏的方法
    @Override
    public boolean isFav(long uid, int imgId) {
        String sql = "SELECT UID FROM travelimagefavor WHERE UID = ? AND ImageID = ?";
        return(queryForValue(sql, uid, imgId) != null);
    }

    //添加图片收藏的方法
    @Override
    public void addFav(Image image, String uid) {
        String sql = "INSERT INTO travelimagefavor (UID, ImageID) VALUES (?, ?)";
        update(sql, uid, image.getImageID());
    }

    //获取搜索结果并按照时间排序的方法
    @Override
    public List<Image> getSearchResultByTime(String sql) {
        sql += " ORDER BY Time desc";
        return queryForList(sql);
    }

    //获取搜索结果并按照热度排序的方法
    @Override
    public List<Image> getSearchResultByHot(String sql) {
        List<Image> list = queryForList(sql);
        Long[] favNums = new Long[list.size()];

        for(int i = 0; i < list.size(); i++) {
            favNums[i] = getFavNum(list.get(i).getImageID());
            for(int j = i - 1; j >= 0; j--) {
                if(favNums[j] < favNums[j + 1]) {
                    Image temp = list.get(j + 1);
                    Long tempNum = favNums[j + 1];
                    list.set(j + 1, list.get(j));
                    favNums[j + 1] = favNums[j];
                    list.set(j, temp);
                    favNums[j] = tempNum;
                }
            }
        }

        return list;
    }

    //获取特定用户收藏图片的方法
    @Override
    public List<Image> getFavorite(Integer uid) {
        String sql = "SELECT ImageID FROM travelimagefavor WHERE UID = ?";
        List<Object[]> list = queryForValueList(sql, uid);
        List<Image> result = new ArrayList<>();

        sql = "SELECT * FROM travelimage WHERE ImageID = ?";
        for(int i = 0; i < list.size(); i++) {
            result.add(query(sql, list.get(i)[0]));
        }

        return result;
    }

    //删除收藏图片的方法
    @Override
    public int deleteFav(String imageID, String uid) {
        String sql = "DELETE FROM travelimagefavor WHERE ImageID = ? AND UID = ?";
        return update(sql, imageID, uid);
    }

    //获取用户图片的方法
    @Override
    public List<Image> getAlbum(Integer uid) {
        String sql = "SELECT * FROM travelimage WHERE UID = ?";
        return queryForList(sql, uid);
    }

    //删除用户图片的方法
    @Override
    public int deleteImage(String imageID) {
        String sql = "DELETE FROM travelimage WHERE ImageID = ?";
        return update(sql, imageID);
    }

    @Override
    public Long getCodeWithCity(String name) {
        String sql = "SELECT GeoNameID FROM geocities WHERE AsciiName = ?";
        Integer result = (Integer) queryForValue(sql, name);
        return result.longValue();
    }

    @Override
    public String getCodeWithCountry(String name) {
        String sql = "SELECT ISO FROM geocountries_regions WHERE Country_RegionName = ?";
        return queryForValue(sql, name);
    }

    @Override
    public int getMaxImageID() {
        String sql = " SELECT MAX(ImageID) FROM travelimage";
        int max = queryForValue(sql);
        return (max + 1);
    }

    @Override
    public int saveImage(Image image) {
        String sql = "INSERT INTO travelimage(ImageID, Title, Description, Latitude, Longitude, CityCode, " +
                "Country_RegionCodeISO, UID, PATH, Content, Time) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return update(sql, image.getImageID(), image.getTitle(),image.getDescription(),
                image.getLatitude(), image.getLongitude(), image.getCityCode(),
                image.getCountry_RegionCodeISO(), image.getUID(), image.getPATH(),
                image.getContent(), image.getTime());
    }

    @Override
    public int modifyImage(String title, String description, String country_RegionCodeISO,
                           Long cityCode, String content, String imageID) {
        String sql = "UPDATE travelimage set Title = ?, Description = ?, " +
                "Country_RegionCodeISO = ?, CityCode = ?, content = ? WHERE ImageID = ?";
        return update(sql, title, description, country_RegionCodeISO, cityCode, content, imageID);
    }

}
