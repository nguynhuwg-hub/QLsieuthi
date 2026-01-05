package controller;

import view.LoginView;
import java.awt.Window;
import javax.swing.SwingUtilities;
import DAO.ChiTietHoaDonDAO;
import DAO.HoaDonDAO;
import DAO.SanPhamDAO;
import DB.DBConnection;
import model.ChiTietHoaDon_m;
import model.HoaDon_m;
import model.SanPham_m;
import view.banhang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;

public class BanHangController {

    private banhang view;
    private SanPhamDAO sanPhamDAO;
    private DefaultTableModel model;
    private double tongTien = 0;
    private String maNV;


    public BanHangController(banhang view, String maNV) {
        this.view = view;
        this.maNV = maNV;
        this.sanPhamDAO = new SanPhamDAO();
        this.model = (DefaultTableModel) view.tableGioHang.getModel();
        initAction();
    }

    private void initAction() {
        view.btnTim.addActionListener(e -> timSanPham());
        view.btnThem.addActionListener(e -> themVaoGio());
        view.btnThanhToan.addActionListener(e -> thanhToan());
        view.btnHuy.addActionListener(e -> huyHoaDon());
        view.btnDangXuat.addActionListener(e -> dangXuat());

    }

    // ================== TÌM SẢN PHẨM ==================
    private void timSanPham() {
        String maSP = view.txtMaSP.getText().trim();
        SanPham_m sp = sanPhamDAO.findByMa(maSP);

        if (sp == null) {
            JOptionPane.showMessageDialog(view, "Không tìm thấy sản phẩm");
            return;
        }

        view.txtTenSP.setText(sp.getTenSP());
        view.txtGia.setText(String.valueOf(sp.getGia()));
    }

    // ================== THÊM VÀO GIỎ ==================
    private void themVaoGio() {
        if (view.txtTenSP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Chưa chọn sản phẩm");
            return;
        }

        String maSP = view.txtMaSP.getText();
        String tenSP = view.txtTenSP.getText();
        double gia = Double.parseDouble(view.txtGia.getText());

        int soLuong;
        try {
            soLuong = Integer.parseInt(view.txtSoLuong.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số lượng không hợp lệ");
            return;
        }

        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(view, "Số lượng phải lớn hơn 0");
            return;
        }

        // ✅ KIỂM TRA TỒN KHO NGAY KHI THÊM
        SanPham_m sp = sanPhamDAO.findByMa(maSP);
        if (sp.getSoLuong() < soLuong) {
            JOptionPane.showMessageDialog(
                    view,
                    "Sản phẩm chỉ còn " + sp.getSoLuong() + " trong kho!"
            );
            return;
        }

        double thanhTien = gia * soLuong;
        tongTien += thanhTien;

        model.addRow(new Object[]{
                maSP, tenSP, gia, soLuong, thanhTien
        });

        view.lblTongTien.setText("Tổng tiền: " + tongTien + " VNĐ");
        clearInput();
    }

    // ================== THANH TOÁN ==================
    private void thanhToan() {

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Giỏ hàng trống!");
            return;
        }

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // ⭐ TRANSACTION

            // ===== KIỂM TRA TỒN KHO (LẦN CUỐI) =====
            for (int i = 0; i < model.getRowCount(); i++) {
                String maSP = model.getValueAt(i, 0).toString();
                int soLuongBan = Integer.parseInt(model.getValueAt(i, 3).toString());

                int soLuongTon = sanPhamDAO.getSoLuongTon(maSP, con);

                if (soLuongTon < soLuongBan) {
                    JOptionPane.showMessageDialog(
                            view,
                            "Sản phẩm " + maSP + " chỉ còn " + soLuongTon + " trong kho!"
                    );
                    con.rollback();
                    return;
                }
            }

            String maHD = "HD" + System.currentTimeMillis();

            HoaDon_m hd = new HoaDon_m(maHD, this.maNV, tongTien);

            HoaDonDAO hdDAO = new HoaDonDAO();
            ChiTietHoaDonDAO ctDAO = new ChiTietHoaDonDAO();

            hdDAO.insert(hd, con);

            for (int i = 0; i < model.getRowCount(); i++) {
                String maSP = model.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(model.getValueAt(i, 3).toString());
                double donGia = Double.parseDouble(model.getValueAt(i, 2).toString());

                ChiTietHoaDon_m ct = new ChiTietHoaDon_m(maHD, maSP, soLuong, donGia);
                ctDAO.insert(ct, con);
                sanPhamDAO.truSoLuong(maSP, soLuong, con);
            }

            con.commit();
            JOptionPane.showMessageDialog(view, "Thanh toán thành công!");
            huyHoaDon();

        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(view, "Lỗi thanh toán!");
            e.printStackTrace();
        }
    }

    // ================== HỦY ==================
    private void huyHoaDon() {
        model.setRowCount(0);
        tongTien = 0;
        view.lblTongTien.setText("Tổng tiền: 0 VNĐ");
        clearInput();
    }

    private void dangXuat() {
        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn có chắc chắn muốn đăng xuất?",
                "Xác nhận đăng xuất",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            // Đóng JFrame chứa banhang
            Window window = SwingUtilities.getWindowAncestor(view);
            if (window != null) {
                window.dispose();
            }

            // ✅ TẠO CONNECTION Ở ĐÂY
            Connection conn = DBConnection.getConnection();

            // Mở lại Login
            LoginView loginView = new LoginView();
            new LoginController(loginView, conn);
        }
    }



    private void clearInput() {
        view.txtMaSP.setText("");
        view.txtTenSP.setText("");
        view.txtGia.setText("");
        view.txtSoLuong.setText("");
    }
}
