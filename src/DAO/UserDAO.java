package DAO;

import model.NhanVien_m;
import java.sql.*;

public class UserDAO {

    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public NhanVien_m login(String username, String password) {
        String sql = "SELECT * FROM nhanvien WHERE username=? AND password=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new NhanVien_m(
                        rs.getString("maNV"),
                        rs.getString("tenNV"),
                        rs.getString("chucVu"),
                        rs.getString("username")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
