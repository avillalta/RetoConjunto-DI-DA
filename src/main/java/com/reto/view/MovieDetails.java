package com.reto.view;

import com.reto.Session;
import com.reto.dao.JdbcUtils;
import com.reto.dao.UserDAO;
import com.reto.dto.MovieDTO;
import com.reto.model.Movie;

import javax.swing.*;

public class MovieDetails extends JFrame {
    UserDAO dao = new UserDAO(JdbcUtils.getCon());

    private JButton backBtn;
    private JTextField tfTitle;
    private JTextField tfGenre;
    private JTextField tfYear;
    private JTextArea taDescription;
    private JTextField tfDirector;
    private JTextField tfStatus;
    private JTextField tfFormat;
    private JPanel mainPanel;
    private JLabel idCopy;

    public MovieDetails() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLocationRelativeTo(null);
        setVisible(true);

        setInfoMovie(Session.movieSelected);
        backBtn.addActionListener(e -> back());
    }

    private void back() {
        MainView back = new MainView();
        Session.movieSelected = null;
        dispose();
        back.setVisible(true);
    }

    private void setInfoMovie(MovieDTO movie) {
        idCopy.setText(movie.getMovie().getId().toString());
        tfTitle.setText(movie.getMovie().getTitle());
        tfGenre.setText(movie.getMovie().getGenre());
        tfYear.setText(movie.getMovie().getYear().toString());
        taDescription.setText(movie.getMovie().getDescription());
        tfDirector.setText(movie.getMovie().getDirector());
        tfStatus.setText(movie.getStatus().toString());
        tfFormat.setText(movie.getFormat().toString());
    }
}
