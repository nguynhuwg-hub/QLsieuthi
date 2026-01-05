package model;

public class ChiTietHoaDon_m {
    private String maHD;
    private String maSP;
    private int soLuong;
    private double donGia;

    public ChiTietHoaDon_m(String maHD, String maSP, int soLuong, double donGia) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaHD() { return maHD; }
    public String getMaSP() { return maSP; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }
}
