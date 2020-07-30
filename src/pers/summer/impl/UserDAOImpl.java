package pers.summer.impl;

import pers.summer.dao.DAO;
import pers.summer.dao.UserDAO;
import pers.summer.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends DAO<User> implements UserDAO {

    //用来注册的函数
    @Override
    public int register(User user) {
        if(getNameCount(user.getUserName()) == 0) {
            String sql = "INSERT INTO traveluser(UID, Email, UserName, Pass, State, DateJoined, DateLastModified)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?)";
            return update(sql, user.getUid(), user.getEmail(), user.getUserName(), user.getPassword(), user.getState(),
                    user.getDateJoined(), user.getDateLastModified());
        }
        return 0;
    }

    //获取特定用户名数量的函数，如果返回0则可以注册
    @Override
    public long getNameCount(String name) {
        String sql = "SELECT COUNT(UID) FROM traveluser WHERE UserName = ?";
        return queryForValue(sql, name);
    }

    //用来检验登录的函数
    @Override
    public long login(String name, String password) {
        String sql = "SELECT COUNT(UID) FROM traveluser WHERE UserName = ? AND Pass = ?";
        return queryForValue(sql, name, password);
    }

    @Override
    public long loginWithEmail(String email, String password) {
        String sql = "SELECT COUNT(UID) FROM traveluser WHERE Email = ? AND Pass = ?";
        return queryForValue(sql, email, password);
    }

    //通过用户名获取id的方法
    @Override
    public String getUIDWithName(String name) {
        String sql = "SELECT UID FROM traveluser WHERE UserName = ?";
        return ("" + queryForValue(sql, name));
    }

    @Override
    public User getSingleWithEmail(String email) {
        String sql = "SELECT * FROM traveluser WHERE Email = ?";
        return query(sql, email);
    }

    //通过用户名获取用户对象的方法
    @Override
    public User getSingleWithName(String name) {
        String sql = "SELECT * FROM traveluser WHERE UserName = ?";
        return query(sql, name);
    }

    //通过id获取好友列表的方法
    @Override
    public List<User> getFriend(int uid) {
        List<User> friendList = new ArrayList<>();
        String sql = "SELECT FriendID FROM friend WHERE UID = ?";
        List<Object[]> friendIDList = queryForValueList(sql, uid);
        sql = "SELECT * FROM traveluser WHERE UID = ?";

        for(Object[] id: friendIDList) {
            String friendID = id[0] + "";
            friendList.add(query(sql, friendID));
        }
        return friendList;
    }

    //通过id获取用户对象的方法
    @Override
    public User getSingleWithID(String uid) {
        String sql = "SELECT * FROM traveluser WHERE UID = ?";
        return query(sql, uid);
    }

}
