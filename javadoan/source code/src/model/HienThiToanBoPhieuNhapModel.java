package model;

public class HienThiToanBoPhieuNhapModel {
	private int maPN;
	private String ngayNhap;
	private String tenNV;
	private String tenNCC;
	public int getMaPN() {
		return maPN;
	}
	public void setMaPN(int maPN) {
		this.maPN = maPN;
	}
	
	public String getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(String ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return maPN+"";
	}
}
