package view;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class banhang extends JPanel {

    public JTextField txtMaSP, txtTenSP, txtGia, txtSoLuong;
    public JButton btnTim, btnThem, btnThanhToan, btnHuy;
    public JTable tableGioHang;
    public JLabel lblTongTien;

    public banhang() {
        txtMaSP = createTextField();
        txtTenSP = createTextField();
        txtTenSP.setEditable(false);

        txtGia = createTextField();
        txtGia.setEditable(false);

        txtSoLuong = createTextField();

        btnTim = createButton("Tìm sản phẩm");
        btnThem = createButton("Thêm vào giỏ");

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setVisible(true);

        add(createTitle(), BorderLayout.NORTH);
        add(createInputPanel(), BorderLayout.WEST);
        add(createCartPanel(), BorderLayout.CENTER);
        add(createPaymentPanel(), BorderLayout.SOUTH);
    }

    // ===== TITLE =====
    private JLabel createTitle() {
        JLabel lbl = new JLabel("BÁN HÀNG - NHÂN VIÊN");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setForeground(new Color(52, 73, 94));
        return lbl;
    }

    // ===== INPUT SẢN PHẨM =====
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(320, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== DÒNG 1: Mã SP =====
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Mã sản phẩm"), gbc);

        gbc.gridx = 1;
        panel.add(txtMaSP, gbc);

        // ===== DÒNG 2: Tìm =====
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(btnTim, gbc);

        // ===== DÒNG 3: Tên SP =====
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Tên sản phẩm"), gbc);

        gbc.gridx = 1;
        panel.add(txtTenSP, gbc);

        // ===== DÒNG 4: Giá =====
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Giá"), gbc);

        gbc.gridx = 1;
        panel.add(txtGia, gbc);

        // ===== DÒNG 5: Số lượng =====
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Số lượng"), gbc);

        gbc.gridx = 1;
        panel.add(txtSoLuong, gbc);

        // ===== DÒNG 6: Thêm vào giỏ =====
        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(btnThem, gbc);

        return panel;
    }

    // ===== GIỎ HÀNG =====
    private JScrollPane createCartPanel() {
        String[] columns = {"Mã SP", "Tên SP", "Giá", "SL", "Thành tiền"};
        tableGioHang = new JTable(new DefaultTableModel(columns, 0));
        tableGioHang.setRowHeight(28);
        tableGioHang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tableGioHang.getTableHeader().setBackground(new Color(236, 240, 241));

        return new JScrollPane(tableGioHang);
    }

    // ===== THANH TOÁN =====
    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        lblTongTien = new JLabel("Tổng tiền: 0 VNĐ");
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTongTien.setForeground(new Color(231, 76, 60));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        btnPanel.setBackground(Color.WHITE);

        btnThanhToan = createButton("Thanh toán");
        btnHuy = createButton("Hủy hóa đơn");

        btnPanel.add(btnThanhToan);
        btnPanel.add(btnHuy);

        panel.add(lblTongTien, BorderLayout.WEST);
        panel.add(btnPanel, BorderLayout.EAST);

        return panel;
    }

    // ===== STYLE =====
    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return txt;
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(52, 152, 219));
        btn.setFocusPainted(false);
        return btn;
    }

    public static void main(String[] args) {
        new banhang();
    }
}

