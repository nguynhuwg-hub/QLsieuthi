package DAO;

import model.NhanVien_m;
import DB.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    // Lấy danh sách nhân viên
    public List<NhanVien_m> getAll() {
        List<NhanVien_m> list = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhanVien_m nv = new NhanVien_m(
                        rs.getString("maNV"),
                        rs.getString("tenNV"),
                        rs.getString("chucVu"),
                        rs.getString("sdt"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm nhân viên
    public boolean insert(NhanVien_m nv) throws SQLException {
        String sql = "INSERT INTO nhanvien(maNV, tenNV, chucVu, sdt, username, password) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setString(3, nv.getChucVu());
            ps.setString(4, nv.getSdt());
            ps.setString(5, nv.getUsername());
            ps.setString(6, nv.getPassword());

            return ps.executeUpdate() > 0;
        }
    }


    // Cập nhật
    public boolean update(NhanVien_m nv) {
        String sql = "UPDATE nhanvien SET tenNV=?, chucVu=?, sdt=?, username=?, password=? WHERE maNV=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getChucVu());
            ps.setString(3, nv.getSdt());
            ps.setString(4, nv.getUsername());
            ps.setString(5, nv.getPassword());
            ps.setString(6, nv.getMaNV());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa
    public boolean delete(String maNV) {
        String sql = "DELETE FROM nhanvien WHERE maNV=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // Tìm kiếm theo mã NV hoặc tên NV
    public List<NhanVien_m> search(String keyword) {
        List<NhanVien_m> list = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE maNV LIKE ? OR tenNV LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien_m nv = new NhanVien_m(
                        rs.getString("maNV"),
                        rs.getString("tenNV"),
                        rs.getString("chucVu"),
                        rs.getString("sdt"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                list.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
