package controller;

import DAO.ThongKeDAO;
import view.ThongKeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ThongKeController {

    private ThongKeView view;
    private ThongKeDAO dao;
    private DefaultTableModel tableModel;

    public ThongKeController(ThongKeView view) {
        this.view = view;
        this.dao = new ThongKeDAO();

        initTable();
        initEvent();
    }

    private void initTable() {
        tableModel = new DefaultTableModel(
                new Object[]{"Mã SP", "Tên SP", "Số lượng bán", "Doanh thu"}, 0
        );
        view.tableTopSP.setModel(tableModel);
    }

    private void initEvent() {

        // ===== THỐNG KÊ NGÀY =====
        view.btnThongKeNgay.addActionListener(e -> {
            String ngay = view.txtNgay.getText().trim();

            if (ngay.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập ngày (yyyy-mm-dd)");
                return;
            }

            double doanhThu = dao.getDoanhThuNgay(ngay);
            int soHD = dao.getSoHoaDonNgay(ngay);

            view.lblDoanhThuNgay.setText(createCardHTML("DOANH THU NGÀY", formatMoney(doanhThu)));
            view.lblHoaDonNgay.setText(createCardHTML("SỐ HÓA ĐƠN NGÀY", String.valueOf(soHD)));
        });

        // ===== THỐNG KÊ THÁNG =====
        view.btnThongKeThang.addActionListener(e -> {
            int thang = view.cbThang.getSelectedIndex() + 1;

            double doanhThu = dao.getDoanhThuThang(thang);
            int soHD = dao.getSoHoaDonThang(thang);

            view.lblDoanhThuThang.setText(createCardHTML("DOANH THU THÁNG", formatMoney(doanhThu)));
            view.lblHoaDonThang.setText(createCardHTML("SỐ HÓA ĐƠN THÁNG", String.valueOf(soHD)));

            loadTopSanPham(thang);
        });
    }

    private void loadTopSanPham(int thang) {
        tableModel.setRowCount(0);
        List<Object[]> list = dao.getTopSanPham(thang);

        for (Object[] row : list) {
            tableModel.addRow(row);
        }
    }

    private String formatMoney(double money) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return nf.format(money);
    }

    private String createCardHTML(String title, String value) {
        return "<html><center>" +
                "<p style='color:#7f8c8d; font-size:13px'>" + title + "</p>" +
                "<h1 style='color:#2c3e50'>" + value + "</h1>" +
                "</center></html>";
    }
}
