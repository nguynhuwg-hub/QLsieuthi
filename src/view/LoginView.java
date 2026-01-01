package view;



import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton btnLogin, btnExit;
    public JLabel lblMessage;

    public LoginView() {
        setTitle("Đăng nhập hệ thống");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ===== TITLE =====
        JLabel lblTitle = new JLabel("HỆ THỐNG BÁN HÀNG SIÊU THỊ", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblTitle, BorderLayout.NORTH);

        // ===== CENTER =====
        JPanel center = new JPanel(new GridLayout(2, 2, 10, 10));

        center.add(new JLabel("Tên đăng nhập:"));
        txtUsername = new JTextField();
        center.add(txtUsername);

        center.add(new JLabel("Mật khẩu:"));
        txtPassword = new JPasswordField();
        center.add(txtPassword);

        panel.add(center, BorderLayout.CENTER);

        // ===== SOUTH =====
        JPanel south = new JPanel(new GridLayout(3, 1, 5, 5));

        lblMessage = new JLabel("", JLabel.CENTER);
        lblMessage.setForeground(Color.RED);
        south.add(lblMessage);

        JPanel btnPanel = new JPanel();
        btnLogin = new JButton("Đăng nhập");
        btnExit = new JButton("Thoát");
        btnPanel.add(btnLogin);
        btnPanel.add(btnExit);

        south.add(btnPanel);

        panel.add(south, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        new LoginView();
    }
}

