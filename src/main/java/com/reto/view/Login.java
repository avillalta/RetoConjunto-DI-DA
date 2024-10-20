package com.reto.view;

import com.reto.Session;
import com.reto.dao.JdbcUtils;
import com.reto.dao.UserDAO;
import com.reto.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Login extends JFrame{
    UserDAO dao = new UserDAO(JdbcUtils.getCon());

    private JPanel LoginPanel;
    private JTextField tfUserName;
    private JPasswordField pfPassword;
    private JButton loginBtn;

    public Login() {


        tfUserName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pfPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setContentPane(LoginPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);

        loginBtn.addActionListener(e -> {loginProcess(dao);});
    }

    private void loginProcess(UserDAO dao) {
        String password = new String(pfPassword.getPassword());
        Optional<User> logged = dao.loginProcess(tfUserName.getText(), password);

        if(logged.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Datos incorrectos, introduzca un usuario correcto");
        } else {
            Session.userSelected = logged.get();
            dispose();
            MainView mainView = new MainView();
            mainView.setVisible(true);
        }
    }

}
