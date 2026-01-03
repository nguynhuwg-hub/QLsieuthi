package view;

import javax.swing.*;
import java.awt.*;

public class SanPhamForm extends JDialog {

    public JTextField txtMaSP, txtTenSP, txtGia, txtSoLuong;
    public JButton btnLuu, btnHuy;

    public SanPhamForm(JFrame parent) {
        super(parent, "Sản phẩm", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        form.add(new JLabel("Mã SP:"));
        txtMaSP = new JTextField();
        form.add(txtMaSP);

        form.add(new JLabel("Tên SP:"));
        txtTenSP = new JTextField();
        form.add(txtTenSP);

        form.add(new JLabel("Giá:"));
        txtGia = new JTextField();
        form.add(txtGia);

        form.add(new JLabel("Số lượng:"));
        txtSoLuong = new JTextField();
        form.add(txtSoLuong);

        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnLuu);
        btnPanel.add(btnHuy);

        add(form, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
