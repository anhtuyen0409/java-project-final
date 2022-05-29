package model;

public class NhanVienModel {
	private int maNV;
	private String tenNV;
	private String namSinh;
	private String diaChi;
	private String SDT;
	private String Email;
	private String ngayVaoLamViec;
	private int daXoa;
	private int maLoaiNV;
	private String tenDangNhap;
	/*private TaiKhoanModel taiKhoan;
	public TaiKhoanModel getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoanModel taiKhoan) {
		this.taiKhoan = taiKhoan;
	}*/
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getNamSinh() {
		return namSinh;
	}
	public void setNamSinh(String namSinh) {
		this.namSinh = namSinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getNgayVaoLamViec() {
		return ngayVaoLamViec;
	}
	public void setNgayVaoLamViec(String ngayVaoLamViec) {
		this.ngayVaoLamViec = ngayVaoLamViec;
	}
	public int getDaXoa() {
		return daXoa;
	}
	public void setDaXoa(int daXoa) {
		this.daXoa = daXoa;
	}
	public int getMaLoaiNV() {
		return maLoaiNV;
	}
	public void setMaLoaiNV(int maLoaiNV) {
		this.maLoaiNV = maLoaiNV;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tenNV;
	}
}
