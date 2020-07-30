package pers.summer.dao;

import pers.summer.entity.Image;
import pers.summer.entity.User;

import java.util.List;

public interface UserDAO {

    public int register(User user);

    public long getNameCount(String name);

    public long login(String name, String password);

    public long loginWithEmail(String email, String password);

    public String getUIDWithName(String name);

    public User getSingleWithEmail(String email);

    public User getSingleWithName(String userName);

    public List<User> getFriend(int uid);

    public User getSingleWithID(String uid);
}
