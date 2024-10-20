package com.reto.dao;

import com.reto.dto.MovieDTO;
import com.reto.enums.Format;
import com.reto.enums.Status;
import com.reto.model.Movie;
import com.reto.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User>{
    private static Connection connection = null;
    private static final String INSERT_USER = "INSERT INTO User (user_name, password) VALUES (?, ?);";
    private static final String DELETE_USER = "DELETE FROM User WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE User SET user_name = ?, password = ? WHERE id = ?;";

    public UserDAO(Connection con){ connection = con;}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM User;")){
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
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM User WHERE id=?;")){
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
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());

            if (ps.executeUpdate() == 1){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                user.setId(rs.getInt(1));
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void update(User user) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE_USER)){
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)){
            ps.setInt(1, user.getId());
            if (ps.executeUpdate() > 0){
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MovieDTO> findUserCopies(User user){
        List<MovieDTO> userCopies = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from Movies inner join Copies on id = movie_id where user_id = ?;")){
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                userCopies.add(new MovieDTO(
                        new Movie(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("genre"),
                                rs.getInt("year"),
                                rs.getString("description"),
                                rs.getString("director")
                        ),
                        Status.valueOf(rs.getString("status")),
                        Format.valueOf(rs.getString("format"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userCopies;
    }

    public Optional<User> loginProcess(String userName, String password) {
        User result = null;
        try(var ps = connection.prepareStatement("Select * from User where user_name = ?;")) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if(rs.next() && password.equals(rs.getString("password"))) {
                result = new User(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(result);
    }
}
