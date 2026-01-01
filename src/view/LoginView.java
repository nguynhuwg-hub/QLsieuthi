package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Đăng nhập hệ thống");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        txtUser = new JTextField();
        panel.add(txtUser);

        panel.add(new JLabel("Password:"));
        txtPass = new JPasswordField();
        panel.add(txtPass);

        panel.add(new JLabel());
        btnLogin = new JButton("Đăng nhập");
        panel.add(btnLogin);

        add(panel);
        setVisible(true);
    }

    public String getUsername() {
        return txtUser.getText();
    }

    public String getPassword() {
        return new String(txtPass.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }
}
