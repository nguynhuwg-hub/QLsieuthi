package model;

public class NhanVien_m {
    private String maNV;
    private String tenNV;
    private String chucVu;
    private String sdt;
    private String username;
    private String password;

    public NhanVien_m() { }

    public NhanVien_m(String maNV, String tenNV, String chucVu,String sdt, String username,String password) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
        this.sdt = sdt;
        this.username = username;
        this.password = password;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String  maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChucVu() {
        return chucVu;
    }

    public String getTenNV() {
        return tenNV;
    }
}
