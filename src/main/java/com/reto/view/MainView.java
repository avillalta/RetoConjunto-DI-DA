package com.reto.view;

import com.reto.Session;
import com.reto.dao.JdbcUtils;
import com.reto.dao.UserDAO;
import com.reto.dto.MovieDTO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainView extends JFrame {
    UserDAO dao = new UserDAO(JdbcUtils.getCon());

    private JPanel MainPanel;
    private JTable tableMovies;
    private JButton logOutBtn;
    private JLabel userName;
    private DefaultTableModel model;
    private List<MovieDTO> movies;

    public MainView() {
        String[] header = {"Title", "Status", "Format"};
        model = new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        movies = dao.findUserCopies(Session.userSelected);

        movies.forEach(movie -> {
            Object[] row = { movie.getMovie(), movie.getStatus(), movie.getFormat() };
            model.addRow(row);
        });

        tableMovies.setModel(model);


        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);

        userName.setText(UserInfo());
        logOutBtn.addActionListener(this::logOut);
        tableMovies.getSelectionModel().addListSelectionListener(this::Detail);

    }

    private void logOut(ActionEvent actionEvent) {
        Session.setParamsNull();
        dispose();
        Login login = new Login();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    private void Detail(ListSelectionEvent listSelectionEvent) {
        if(!listSelectionEvent.getValueIsAdjusting()) {
            Session.movieSelected = getMovieDto();
            if(Session.movieSelected != null) {
                dispose();
                MovieDetails movieDetails = new MovieDetails();
                movieDetails.setVisible(true);
            }
        }
    }

    private MovieDTO getMovieDto() {
        MovieDTO movie = null;
        int row = tableMovies.getSelectedRow();
        if(row >= 0 && row < movies.size()) {
            movie = movies.get(row);
        }
        return movie;
    }

    private String UserInfo() {
        int totalCopies = dao.findUserCopies(Session.userSelected).size();
        String message = String.format(
                "<html>Name: %s<br/>Account info: Id: %d | Num of movies: %d</html>",
                Session.userSelected.getUserName(),
                Session.userSelected.getId(),
                totalCopies
        );
        return message;
    }
}
