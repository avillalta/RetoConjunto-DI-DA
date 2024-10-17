package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User>{
    private Connection con;
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM User;")){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User findById(Integer id) {
        User user = null;
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM User WHERE id=?;")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user = new User(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean save(User user) {

        return false;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
