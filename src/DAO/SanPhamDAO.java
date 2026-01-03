package DAO;

import DB.DBConnection;
import model.SanPham_m;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {

    public List<SanPham_m> getAll() {
        List<SanPham_m> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new SanPham_m(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("gia"),
                        rs.getInt("soLuong")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    public boolean insert(SanPham_m sp) {
//        String sql = "INSERT INTO sanpham VALUES (?, ?, ?, ?)";
//
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, sp.getMaSP());
//            ps.setString(2, sp.getTenSP());
//            ps.setDouble(3, sp.getGia());
//            ps.setInt(4, sp.getSoLuong());
//
//            return ps.executeUpdate() > 0;
//        }
//    }
    public boolean insert(SanPham_m sp) throws SQLException {
        String sql = "INSERT INTO sanpham VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, sp.getMaSP());
        ps.setString(2, sp.getTenSP());
        ps.setDouble(3, sp.getGia());
        ps.setInt(4, sp.getSoLuong());

            return ps.executeUpdate() > 0;
        }
    }


    public boolean update(SanPham_m sp) {
        String sql = "UPDATE sanpham SET tenSP=?, gia=?, soLuong=? WHERE maSP=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSP());
            ps.setDouble(2, sp.getGia());
            ps.setInt(3, sp.getSoLuong());
            ps.setString(4, sp.getMaSP());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maSP) {
        String sql = "DELETE FROM sanpham WHERE maSP=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maSP);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // Tìm kiếm theo mã hoặc tên SP
    public List<SanPham_m> search(String keyword) {
        List<SanPham_m> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE maSP LIKE ? OR tenSP LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new SanPham_m(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("gia"),
                        rs.getInt("soLuong")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
