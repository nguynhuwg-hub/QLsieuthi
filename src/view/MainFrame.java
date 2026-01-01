package view;

import view.AdminView;
import view.banhang;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame(String role) {
        setTitle("Hệ thống quản lý siêu thị");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new AdminView(), "ADMIN");
        mainPanel.add(new banhang(), "NHANVIEN");

        add(mainPanel);

        if (role.equals("ADMIN")) {
            cardLayout.show(mainPanel, "ADMIN");
        } else {
            cardLayout.show(mainPanel, "NHANVIEN");
        }

        setVisible(true);
    }
}