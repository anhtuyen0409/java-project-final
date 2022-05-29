package model;

public class TaiKhoanModel {
	private String tenDangNhap;
	private String matKhau;
	private int daXoa;
	private int maLoaiTK;
	/*private LoaiTaiKhoanModel loaiTaiKhoan;
	public LoaiTaiKhoanModel getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}
	public void setLoaiTaiKhoan(LoaiTaiKhoanModel loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}*/
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public int getDaXoa() {
		return daXoa;
	}
	public void setDaXoa(int daXoa) {
		this.daXoa = daXoa;
	}
	public int getMaLoaiTK() {
		return maLoaiTK;
	}
	public void setMaLoaiTK(int maLoaiTK) {
		this.maLoaiTK = maLoaiTK;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tenDangNhap;
	}
}
