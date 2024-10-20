package com.reto.dao;

import com.reto.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements DAO<Movie>{
    private static Connection connection = null;
    private static final String INSERT_MOVIE = "INSERT INTO Movies (title, genre, year, description, director) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_MOVIE = "DELETE FROM Movies WHERE id = ?;";
    private static final String UPDATE_MOVIE = "UPDATE Movies SET title = ?, genre = ?, year = ?, description = ?, director = ? WHERE id = ?;";

    public MovieDAO(Connection con){ connection = con;}

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Movies;")){
            ResultSet rs =  ps.executeQuery();
            while (rs.next()){
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getString("description"),
                        rs.getString("director")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    @Override
    public Movie findById(Integer id) {
        Movie movie = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Movies WHERE id = ?;")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getString("description"),
                        rs.getString("director")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movie;
    }

    @Override
    public boolean save(Movie movie) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getGenre());
            ps.setInt(3, movie.getYear());
            ps.setString(4, movie.getDescription());
            ps.setString(5, movie.getDirector());

            if (ps.executeUpdate() == 1){
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                movie.setId(rs.getInt(1));
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void update(Movie movie) {
        try ( PreparedStatement ps = connection.prepareStatement(UPDATE_MOVIE)){
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getGenre());
            ps.setInt(3, movie.getYear());
            ps.setString(4, movie.getDescription());
            ps.setString(5, movie.getDirector());
            ps.setInt(6, movie.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Movie movie) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(DELETE_MOVIE)){
            ps.setInt(1, movie.getId());
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
        try (PreparedStatement ps = connection.prepareStatement(DELETE_MOVIE)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
