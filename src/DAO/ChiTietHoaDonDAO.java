package DAO;

import DB.DBConnection;
import model.ChiTietHoaDon_m;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ChiTietHoaDonDAO {
    public void insert(ChiTietHoaDon_m ct, Connection con) throws Exception {
        String sql = "INSERT INTO chitiethoadon(maHD, maSP, soLuong, donGia) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ct.getMaHD());
        ps.setString(2, ct.getMaSP());
        ps.setInt(3, ct.getSoLuong());
        ps.setDouble(4, ct.getDonGia());
        ps.executeUpdate();
    }
}
