package view;

import javax.swing.*;
import java.awt.*;

public class mainframe extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public mainframe(String role) {
        setTitle("Hệ thống quản lý siêu thị");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new AdminView(), "Admin");
        mainPanel.add(new banhang(), "NhanVien");

        add(mainPanel);

        if ("Admin".equalsIgnoreCase(role)) {
            cardLayout.show(mainPanel, "Admin");
        } else {
            cardLayout.show(mainPanel, "NhanVien");
        }

        setVisible(true);
    }
}
