package model;
import java.time.LocalDateTime;

public class HoaDon_m {
    private String maHD;
    private LocalDateTime ngayLap;
    private String maNV;
    private double tongTien;

    public HoaDon_m(String maHD, String maNV, double tongTien) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.tongTien = tongTien;
        this.ngayLap = LocalDateTime.now();
    }

    public String getMaHD() { return maHD; }
    public String getMaNV() { return maNV; }
    public double getTongTien() { return tongTien; }
}
