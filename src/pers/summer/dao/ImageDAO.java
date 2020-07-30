package pers.summer.dao;

import pers.summer.entity.Image;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ImageDAO {

    public List<String> getFav();

    public List<Image> getNew();

    public Image getSingleWithID(String id);

    public String getCountry(String iso);

    public String getCity(Long code);

    public Long getFavNum(Integer id);

    public String getUser(int uid);

    public boolean isFav(long uid, int imgId);

    public void addFav(Image image, String uid);

    public List<Image> getSearchResultByTime(String sql);

    public List<Image> getSearchResultByHot(String sql);

    public List<Image> getFavorite(Integer uid);

    public int deleteFav(String imageID, String uid);

    public List<Image> getAlbum(Integer uid);

    public int deleteImage(String imageID);

    public Long getCodeWithCity(String name);

    public String getCodeWithCountry(String name);

    public int getMaxImageID();

    public int saveImage(Image image);

    public int modifyImage(String title, String description, String country_RegionCodeISO,
                           Long cityCode, String content, String imageID);
}
