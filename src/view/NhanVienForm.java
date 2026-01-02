package view;

import javax.swing.*;
import java.awt.*;

public class NhanVienForm extends JDialog {

    public JTextField txtMaNV, txtTenNV, txtSdt, txtUsername;
    public JPasswordField txtPassword;
    public JComboBox<String> cbChucVu;
    public JButton btnLuu, btnHuy;

    public NhanVienForm(JFrame parent) {
        super(parent, "Thêm nhân viên", true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        form.add(new JLabel("Mã NV:"));
        txtMaNV = new JTextField();
        form.add(txtMaNV);

        form.add(new JLabel("Tên NV:"));
        txtTenNV = new JTextField();
        form.add(txtTenNV);

        form.add(new JLabel("Chức vụ:"));
        cbChucVu = new JComboBox<>(new String[]{"NhanVien", "Admin"});
        form.add(cbChucVu);

        form.add(new JLabel("SĐT:"));
        txtSdt = new JTextField();
        form.add(txtSdt);

        form.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        form.add(txtUsername);

        form.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        form.add(txtPassword);

        JPanel btnPanel = new JPanel();
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");

        btnPanel.add(btnLuu);
        btnPanel.add(btnHuy);

        add(form, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
