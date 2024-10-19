package dao;

import model.Movie;

import java.sql.Connection;
import java.util.List;

public class MovieDAO implements DAO<Movie>{
    private static Connection connection = null;
    private static final String INSERT_MOVIE = "INSERT INTO Movies (title, genre, year, description, director) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_MOVIE = "DELETE FROM Movies WHERE id = ?;";
    private static final String UPDATE_MOVIE = "UPDATE Movies SET title = ?, genre = ?, year = ?, description = ?, director = ? WHERE id = ?;";

    public MovieDAO(Connection con){ connection = con;}

    @Override
    public List<Movie> findAll() {

        return List.of();
    }

    @Override
    public Movie findById(Integer id) {
        return null;
    }

    @Override
    public boolean save(Movie movie) {
        return false;
    }

    @Override
    public void update(Movie movie) {

    }

    @Override
    public boolean delete(Movie movie) {
        return false;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
