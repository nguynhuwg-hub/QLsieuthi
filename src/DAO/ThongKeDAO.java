package DAO;

import DB.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {

    // ================= THỐNG KÊ NGÀY =================
    public double getDoanhThuNgay(String ngay) {
        String sql = "SELECT IFNULL(SUM(tongTien), 0) FROM hoadon WHERE DATE(ngayLap) = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ngay);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getDouble(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSoHoaDonNgay(String ngay) {
        String sql = "SELECT COUNT(*) FROM hoadon WHERE DATE(ngayLap) = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ngay);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ================= THỐNG KÊ THÁNG =================
    public double getDoanhThuThang(int thang) {
        String sql = "SELECT IFNULL(SUM(tongTien), 0) FROM hoadon WHERE MONTH(ngayLap) = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, thang);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getDouble(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSoHoaDonThang(int thang) {
        String sql = "SELECT COUNT(*) FROM hoadon WHERE MONTH(ngayLap) = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, thang);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ================= TOP SẢN PHẨM =================
    public List<Object[]> getTopSanPham(int thang) {
        List<Object[]> list = new ArrayList<>();

        String sql = """
            SELECT sp.maSP, sp.tenSP,
                   SUM(ct.soLuong) AS tongBan,
                   SUM(ct.soLuong * ct.donGia) AS doanhThu
            FROM chitiethoadon ct
            JOIN sanpham sp ON ct.maSP = sp.maSP
            JOIN hoadon hd ON ct.maHD = hd.maHD
            WHERE MONTH(hd.ngayLap) = ?
            GROUP BY sp.maSP, sp.tenSP
            ORDER BY tongBan DESC
            LIMIT 10
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, thang);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Object[]{
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getInt("tongBan"),
                        rs.getDouble("doanhThu")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
