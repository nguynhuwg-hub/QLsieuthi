package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginView() {
        initUI();
    }

    private void initUI() {
        setTitle("Đăng nhập hệ thống");
        setSize(420, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // ===== PANEL CHÍNH =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // ===== TIÊU ĐỀ =====
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ SIÊU THỊ", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(52, 73, 94));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // ===== FORM =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(25, 10, 20, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // USERNAME
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblUser, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        txtUser = new JTextField(18);
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtUser, gbc);

        // PASSWORD
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblPass, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        txtPass = new JPasswordField(18);
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtPass, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // ===== NÚT ĐĂNG NHẬP =====
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(52, 152, 219));
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(150, 42));

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnLogin);

        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    // ===== MVC METHODS (GIỮ NGUYÊN – KHÔNG PHÁ CONTROLLER) =====
    public String getUsername() {
        return txtUser.getText().trim();
    }

    public String getPassword() {
        return new String(txtPass.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }
}
