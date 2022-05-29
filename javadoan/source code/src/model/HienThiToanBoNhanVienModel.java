package model;

public class HienThiToanBoNhanVienModel {
	private int maNV;
	private String tenNV;
	private String namSinh;
	private String diaChi;
	private String SDT;
	private String Email;
	private String ngayVaoLamViec;
	private String tenBoPhan;
	private String taiKhoan;
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
	public String getTenBoPhan() {
		return tenBoPhan;
	}
	public void setTenBoPhan(String tenBoPhan) {
		this.tenBoPhan = tenBoPhan;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tenNV;
	}
}
