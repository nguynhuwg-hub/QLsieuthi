package view;

import controller.BanHangController;

import javax.swing.*;
import java.awt.*;

public class mainframe extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String maNV;

    public mainframe(String role) {
        this(role, null);
    }

    public mainframe(String role, String maNV) {
        this.maNV = maNV;

        setTitle("Hệ thống quản lý siêu thị");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // ===== ADMIN =====
        AdminView adminView = new AdminView();
        mainPanel.add(adminView, "Admin");

        // ===== NHÂN VIÊN (BÁN HÀNG) =====
        banhang bhView = new banhang();
        new BanHangController(bhView, maNV); // ✅ giờ KHÔNG còn lỗi
        mainPanel.add(bhView, "NhanVien");

        add(mainPanel);

        if ("Admin".equalsIgnoreCase(role)) {
            cardLayout.show(mainPanel, "Admin");
        } else {
            cardLayout.show(mainPanel, "NhanVien");
        }

        setVisible(true);
    }
}
