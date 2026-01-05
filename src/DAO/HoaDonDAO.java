package DAO;

import DB.DBConnection;
import model.HoaDon_m;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class HoaDonDAO {
    public void insert(HoaDon_m hd, Connection con) throws Exception {
        String sql = "INSERT INTO hoadon(maHD, maNV, tongTien) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, hd.getMaHD());
        ps.setString(2, hd.getMaNV());
        ps.setDouble(3, hd.getTongTien());
        ps.executeUpdate();
    }
}
