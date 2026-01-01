package view;


import javax.swing.*;
import java.awt.*;

public class AdminView extends JPanel {

    public JButton btnSanPham, btnNhanVien, btnThongKe, btnLogout;
    public JPanel contentPanel;
    public CardLayout cardLayout;

    public AdminView() {
        initUI();
        initEvent();
        setVisible(true);
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
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(new Color(44, 62, 80));
        sidebar.setLayout(new GridLayout(6, 1, 0, 10));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        btnSanPham = createMenuButton("Quản lý sản phẩm");
        btnNhanVien = createMenuButton("Quản lý nhân viên");
        btnThongKe = createMenuButton("Thống kê");
        btnLogout = createMenuButton("Đăng xuất");

        sidebar.add(btnSanPham);
        sidebar.add(btnNhanVien);
        sidebar.add(btnThongKe);
        sidebar.add(new JLabel()); // spacer
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        // ===== CONTENT =====
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);

        contentPanel.add(new SanPhamView(), "SANPHAM");
        contentPanel.add(new NhanVienView(), "NHANVIEN");
        contentPanel.add(new ThongKeView(), "THONGKE");

        add(contentPanel, BorderLayout.CENTER);
    }
    private void initEvent() {
        btnSanPham.addActionListener(e ->
                cardLayout.show(contentPanel, "SANPHAM"));

        btnNhanVien.addActionListener(e ->
                cardLayout.show(contentPanel, "NHANVIEN"));

        btnThongKe.addActionListener(e ->
                cardLayout.show(contentPanel, "THONGKE"));
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(52, 152, 219));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return btn;
    }

    public static void main(String[] args) {
        new AdminView();
    }
}

