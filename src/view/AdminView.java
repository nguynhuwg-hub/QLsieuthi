package view;

import controller.*;
import javax.swing.*;
import java.awt.*;

public class AdminView extends JPanel {

    public JButton btnSanPham, btnNhanVien, btnThongKe, btnLogout;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public AdminView() {
        initUI();
        initEvent();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 60));
        header.setBackground(new Color(52, 73, 94));

        JLabel lblTitle = new JLabel("  HỆ THỐNG QUẢN LÝ SIÊU THỊ - ADMIN");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.add(lblTitle, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        // ===== SIDEBAR =====
        JPanel sidebar = new JPanel(new GridLayout(6, 1, 0, 10));
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(new Color(44, 62, 80));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        btnSanPham = createMenuButton("Quản lý sản phẩm");
        btnNhanVien = createMenuButton("Quản lý nhân viên");
        btnThongKe  = createMenuButton("Thống kê");
        btnLogout   = createMenuButton("Đăng xuất");

        sidebar.add(btnSanPham);
        sidebar.add(btnNhanVien);
        sidebar.add(btnThongKe);
        sidebar.add(new JLabel());
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        // ===== CONTENT =====
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // ===== SẢN PHẨM =====
        SanPhamView spView = new SanPhamView();
        new SanPhamController(spView);
        contentPanel.add(spView, "SANPHAM");

        // ===== NHÂN VIÊN =====
        NhanVienView nvView = new NhanVienView();
        new NhanVienController(nvView);
        contentPanel.add(nvView, "NHANVIEN");

        // ===== THỐNG KÊ =====
        ThongKeView thongKeView = new ThongKeView();
        new ThongKeController(thongKeView);
        contentPanel.add(thongKeView, "THONGKE");

        add(contentPanel, BorderLayout.CENTER);

        // ✅ HIỂN THỊ MẶC ĐỊNH
        cardLayout.show(contentPanel, "NHANVIEN");
    }

    private void initEvent() {
        btnSanPham.addActionListener(e ->
                cardLayout.show(contentPanel, "SANPHAM"));

        btnNhanVien.addActionListener(e ->
                cardLayout.show(contentPanel, "NHANVIEN"));

        btnThongKe.addActionListener(e ->
                cardLayout.show(contentPanel, "THONGKE"));

        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn đăng xuất?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) window.dispose();

                java.sql.Connection conn = DB.DBConnection.getConnection();
                view.LoginView loginView = new view.LoginView();
                new controller.LoginController(loginView, conn);
            }
        });
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(52, 152, 219));
        btn.setFocusPainted(false);
        return btn;
    }
}
