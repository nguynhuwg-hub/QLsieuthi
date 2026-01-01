package model;

public class NhanVien_m {
    private String maNV;
    private String tenNV;
    private String chucVu;
    private String username;

    public NhanVien_m(String maNV, String tenNV, String chucVu, String username) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
        this.username = username;
    }

    public String getChucVu() {
        return chucVu;
    }

    public String getTenNV() {
        return tenNV;
    }
}
