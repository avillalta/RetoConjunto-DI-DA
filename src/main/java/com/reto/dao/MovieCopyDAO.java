package com.reto.dao;

import com.reto.enums.Format;
import com.reto.enums.Status;
import com.reto.model.MovieCopy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieCopyDAO implements DAO<MovieCopy>{
    private static Connection connection = null;
    private static final String INSERT_COPY = "INSERT INTO Copies (movie_id, user_id, status, format) VALUES (?, ?, ?, ?);";
    private static final String DELETE_COPY = "DELETE FROM Copies WHERE id = ?;";
    private static final String UPDATE_COPY = "UPDATE Copies SET movie_id = ?, user_id = ?, status = ?, format = ? WHERE id = ?;";

    public MovieCopyDAO(Connection con){ connection = con;}

    @Override
    public List<MovieCopy> findAll() {
        List<MovieCopy> copies = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Copies;")){
            ResultSet rs =  ps.executeQuery();
            while (rs.next()){
                copies.add(new MovieCopy(
                        rs.getInt("copy_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        Status.valueOf(rs.getString("status")),
                        Format.valueOf(rs.getString("format"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return copies;
    }

    @Override
    public MovieCopy findById(Integer id) {
        MovieCopy copy = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Copies WHERE copy_id = ?;")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                copy = new MovieCopy(
                        rs.getInt("copy_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        Status.valueOf(rs.getString("status")),
                        Format.valueOf(rs.getString("format"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return copy;
    }

    @Override
    public boolean save(MovieCopy movieCopy) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_COPY, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, movieCopy.getFilmId());
            ps.setInt(2, movieCopy.getUserId());
            ps.setString(3, movieCopy.getStatus().toString());
            ps.setString(4, movieCopy.getFormat().toString());

            if (ps.executeUpdate() == 1){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                movieCopy.setCopyId(rs.getInt(1));
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void update(MovieCopy movieCopy) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE_COPY)){
            ps.setInt(1, movieCopy.getFilmId());
            ps.setInt(2, movieCopy.getUserId());
            ps.setString(3, movieCopy.getStatus().toString());
            ps.setString(4, movieCopy.getFormat().toString());
            ps.setInt(5, movieCopy.getCopyId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(MovieCopy movieCopy) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(DELETE_COPY)){
            ps.setInt(1, movieCopy.getCopyId());
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
        try (PreparedStatement ps = connection.prepareStatement(DELETE_COPY)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
