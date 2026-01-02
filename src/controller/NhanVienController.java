package controller;

import DAO.NhanVienDAO;
import model.NhanVien_m;
import view.NhanVienForm;
import view.NhanVienView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class NhanVienController {

    private NhanVienView view;
    private NhanVienDAO dao;
    private DefaultTableModel tableModel;

    public NhanVienController(NhanVienView view) {
        this.view = view;
        this.dao = new NhanVienDAO();

        initTable();
        loadData();
        initEvent();
    }

    private void initTable() {
        tableModel = new DefaultTableModel(
                new Object[]{"Mã NV", "Tên NV", "Chức vụ", "SĐT", "Username"}, 0
        );
        view.tableNV.setModel(tableModel);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<NhanVien_m> list = dao.getAll();

        for (NhanVien_m nv : list) {
            tableModel.addRow(new Object[]{
                    nv.getMaNV(),
                    nv.getTenNV(),
                    nv.getChucVu(),
                    nv.getSdt(),
                    nv.getUsername()
            });
        }
    }

    private void initEvent() {

        // THÊM
        view.btnThem.addActionListener(e -> {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(view);
            NhanVienForm form = new NhanVienForm(parent);

            form.btnLuu.addActionListener(ev -> {
                // Validate
                if (form.txtMaNV.getText().isEmpty() ||
                        form.txtTenNV.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(form, "Không được để trống dữ liệu");
                    return;
                }

                NhanVien_m nv = new NhanVien_m();
                nv.setMaNV(form.txtMaNV.getText());
                nv.setTenNV(form.txtTenNV.getText());
                nv.setChucVu(form.cbChucVu.getSelectedItem().toString());
                nv.setSdt(form.txtSdt.getText());
                nv.setUsername(form.txtUsername.getText());
                nv.setPassword(new String(form.txtPassword.getPassword()));

                if (dao.insert(nv)) {
                    JOptionPane.showMessageDialog(form, "Thêm thành công");
                    form.dispose();
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(form, "Thêm thất bại");
                }
            });

            form.btnHuy.addActionListener(ev -> form.dispose());

            form.setVisible(true);
        });

        // SỬA
        // SỬA (dùng NhanVienForm)
        view.btnSua.addActionListener(e -> {
            int row = view.tableNV.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên cần sửa");
                return;
            }

            // Lấy dữ liệu từ dòng đang chọn
            NhanVien_m nv = new NhanVien_m();
            nv.setMaNV(tableModel.getValueAt(row, 0).toString());
            nv.setTenNV(tableModel.getValueAt(row, 1).toString());
            nv.setChucVu(tableModel.getValueAt(row, 2).toString());
            nv.setSdt(tableModel.getValueAt(row, 3).toString());
            nv.setUsername(tableModel.getValueAt(row, 4).toString());
            nv.setPassword(""); // có thể để trống hoặc load lại từ DB

            // Mở form sửa
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(view);
            NhanVienForm form = new NhanVienForm(parent);

            // Đổ dữ liệu lên form
            form.txtMaNV.setText(nv.getMaNV());
            form.txtMaNV.setEnabled(false); // không cho sửa mã
            form.txtTenNV.setText(nv.getTenNV());
            form.cbChucVu.setSelectedItem(nv.getChucVu());
            form.txtSdt.setText(nv.getSdt());
            form.txtUsername.setText(nv.getUsername());

            // Xử lý nút LƯU
            form.btnLuu.addActionListener(ev -> {
                nv.setTenNV(form.txtTenNV.getText());
                nv.setChucVu(form.cbChucVu.getSelectedItem().toString());
                nv.setSdt(form.txtSdt.getText());
                nv.setUsername(form.txtUsername.getText());
                nv.setPassword(new String(form.txtPassword.getPassword()));

                if (dao.update(nv)) {
                    JOptionPane.showMessageDialog(form, "Cập nhật thành công");
                    form.dispose();
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(form, "Cập nhật thất bại");
                }
            });

            form.btnHuy.addActionListener(ev -> form.dispose());

            form.setVisible(true);
        });

        // XÓA
        view.btnXoa.addActionListener(e -> {
            int row = view.tableNV.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(view, "Chọn nhân viên cần xóa");
                return;
            }

            String maNV = tableModel.getValueAt(row, 0).toString();

            if (JOptionPane.showConfirmDialog(view, "Xóa nhân viên này?")
                    == JOptionPane.YES_OPTION) {

                if (dao.delete(maNV)) {
                    loadData();
                }
            }
        });
    }
}
