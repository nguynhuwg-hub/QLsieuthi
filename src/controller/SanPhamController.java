package controller;

import DAO.SanPhamDAO;
import model.SanPham_m;
import view.SanPhamForm;
import view.SanPhamView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SanPhamController {

    private SanPhamView view;
    private SanPhamDAO dao;
    private DefaultTableModel tableModel;

    public SanPhamController(SanPhamView view) {
        this.view = view;
        this.dao = new SanPhamDAO();

        initTable();
        loadData();
        initEvent();
    }

    private void initTable() {
        tableModel = new DefaultTableModel(
                new Object[]{"Mã SP", "Tên SP", "Giá", "Số lượng"}, 0
        );
        view.tableSP.setModel(tableModel);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<SanPham_m> list = dao.getAll();

        for (SanPham_m sp : list) {
            tableModel.addRow(new Object[]{
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getGia(),
                    sp.getSoLuong()
            });
        }
    }
    private void loadTable(List<SanPham_m> list) {
        tableModel.setRowCount(0);
        for (SanPham_m sp : list) {
            tableModel.addRow(new Object[]{
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getGia(),
                    sp.getSoLuong()
            });
        }
    }


    private void initEvent() {

        // THÊM
        view.btnThem.addActionListener(e -> {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(view);
            SanPhamForm form = new SanPhamForm(parent);

            form.btnLuu.addActionListener(ev -> {
                try {
                    SanPham_m sp = new SanPham_m();
                    sp.setMaSP(form.txtMaSP.getText().trim());
                    sp.setTenSP(form.txtTenSP.getText().trim());
                    sp.setGia(Double.parseDouble(form.txtGia.getText()));
                    sp.setSoLuong(Integer.parseInt(form.txtSoLuong.getText()));

                    if (dao.insert(sp)) {
                        JOptionPane.showMessageDialog(form, "Thêm thành công");
                        form.dispose();
                        loadData();
                    }

                } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
                    JOptionPane.showMessageDialog(
                            form,
                            "Sản phẩm đã tồn tại!",
                            "Lỗi trùng dữ liệu",
                            JOptionPane.ERROR_MESSAGE
                    );

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            form,
                            "Dữ liệu không hợp lệ!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });


            form.btnHuy.addActionListener(ev -> form.dispose());
            form.setVisible(true);
        });

        // SỬA
        view.btnSua.addActionListener(e -> {
            int row = view.tableSP.getSelectedRow();
            if (row == -1) return;

            SanPham_m sp = new SanPham_m();
            sp.setMaSP(tableModel.getValueAt(row, 0).toString());
            sp.setTenSP(tableModel.getValueAt(row, 1).toString());
            sp.setGia(Double.parseDouble(tableModel.getValueAt(row, 2).toString()));
            sp.setSoLuong(Integer.parseInt(tableModel.getValueAt(row, 3).toString()));

            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(view);
            SanPhamForm form = new SanPhamForm(parent);

            form.txtMaSP.setText(sp.getMaSP());
            form.txtMaSP.setEnabled(false);
            form.txtTenSP.setText(sp.getTenSP());
            form.txtGia.setText(String.valueOf(sp.getGia()));
            form.txtSoLuong.setText(String.valueOf(sp.getSoLuong()));

            form.btnLuu.addActionListener(ev -> {
                sp.setTenSP(form.txtTenSP.getText());
                sp.setGia(Double.parseDouble(form.txtGia.getText()));
                sp.setSoLuong(Integer.parseInt(form.txtSoLuong.getText()));

                if (dao.update(sp)) {
                    JOptionPane.showMessageDialog(form, "Cập nhật thành công");
                    form.dispose();
                    loadData();
                }
            });

            form.btnHuy.addActionListener(ev -> form.dispose());
            form.setVisible(true);
        });

        // XÓA
        view.btnXoa.addActionListener(e -> {
            int row = view.tableSP.getSelectedRow();
            if (row == -1) return;

            String maSP = tableModel.getValueAt(row, 0).toString();

            if (JOptionPane.showConfirmDialog(view, "Xóa sản phẩm này?")
                    == JOptionPane.YES_OPTION) {

                if (dao.delete(maSP)) {
                    loadData();
                }
            }
        });
        view.btnSearch.addActionListener(e -> {
            String keyword = view.txtSearch.getText().trim();

            if (keyword.isEmpty()) {
                loadData();
                return;
            }

            List<SanPham_m> result = dao.search(keyword);

            if (result.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy sản phẩm");
            }

            loadTable(result);
        });

    }
}
