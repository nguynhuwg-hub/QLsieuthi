package view;

import javax.swing.*;
import java.awt.*;

public class ThongKeView extends JPanel {

    // Thống kê ngày
    public JTextField txtNgay;
    public JButton btnThongKeNgay;
    public JLabel lblDoanhThuNgay, lblHoaDonNgay;

    // Thống kê tháng
    public JComboBox<String> cbThang;
    public JButton btnThongKeThang;
    public JLabel lblDoanhThuThang, lblHoaDonThang;

    // Bảng SP bán chạy
    public JTable tableTopSP;

    public ThongKeView() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(createFilterPanel(), BorderLayout.NORTH);
        add(createResultPanel(), BorderLayout.CENTER);
        add(createTopProductPanel(), BorderLayout.SOUTH);
    }

    // ===== PANEL CHỌN NGÀY / THÁNG =====
    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 10));
        panel.setBackground(Color.WHITE);

        // Theo ngày
        JPanel dayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dayPanel.setBackground(Color.WHITE);
        dayPanel.setBorder(BorderFactory.createTitledBorder("Thống kê theo ngày"));

        txtNgay = new JTextField(10);
        txtNgay.setToolTipText("yyyy-mm-dd");
        btnThongKeNgay = createButton("Xem");

        dayPanel.add(new JLabel("Ngày:"));
        dayPanel.add(txtNgay);
        dayPanel.add(btnThongKeNgay);

        // Theo tháng
        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        monthPanel.setBackground(Color.WHITE);
        monthPanel.setBorder(BorderFactory.createTitledBorder("Thống kê theo tháng"));

        cbThang = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            cbThang.addItem("Tháng " + i);
        }
        btnThongKeThang = createButton("Xem");

        monthPanel.add(new JLabel("Tháng:"));
        monthPanel.add(cbThang);
        monthPanel.add(btnThongKeThang);

        panel.add(dayPanel);
        panel.add(monthPanel);

        return panel;
    }

    // ===== KẾT QUẢ DOANH THU =====
    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 20));
        panel.setBackground(Color.WHITE);

        lblDoanhThuNgay = createCard("DOANH THU NGÀY", "0 VNĐ");
        lblHoaDonNgay   = createCard("SỐ HÓA ĐƠN NGÀY", "0");

        lblDoanhThuThang = createCard("DOANH THU THÁNG", "0 VNĐ");
        lblHoaDonThang   = createCard("SỐ HÓA ĐƠN THÁNG", "0");

        JPanel left = new JPanel(new GridLayout(2, 1, 10, 10));
        left.setBackground(Color.WHITE);
        left.add(lblDoanhThuNgay);
        left.add(lblHoaDonNgay);

        JPanel right = new JPanel(new GridLayout(2, 1, 10, 10));
        right.setBackground(Color.WHITE);
        right.add(lblDoanhThuThang);
        right.add(lblHoaDonThang);

        panel.add(left);
        panel.add(right);

        return panel;
    }

    // ===== TOP SẢN PHẨM =====
    private JPanel createTopProductPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Sản phẩm bán chạy theo tháng"));

        tableTopSP = new JTable();
        JScrollPane scroll = new JScrollPane(tableTopSP);

        panel.add(scroll, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(0, 200));

        return panel;
    }

    // ===== BUTTON STYLE =====
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(52, 152, 219));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        return btn;
    }

    // ===== CARD STYLE =====
    private JLabel createCard(String title, String value) {
        JLabel card = new JLabel(
                "<html><center>" +
                        "<p style='color:#7f8c8d; font-size:13px'>" + title + "</p>" +
                        "<h1 style='color:#2c3e50'>" + value + "</h1>" +
                        "</center></html>",
                JLabel.CENTER
        );
        card.setOpaque(true);
        card.setBackground(new Color(236, 240, 241));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        return card;
    }
}
