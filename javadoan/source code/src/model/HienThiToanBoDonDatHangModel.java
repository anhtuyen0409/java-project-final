package model;

public class HienThiToanBoDonDatHangModel {
	private int maDDH;
	private String tenKH;
	private String ngayDat;
	private String ngayGiao;
	public int getMaDDH() {
		return maDDH;
	}
	public void setMaDDH(int maDDH) {
		this.maDDH = maDDH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(String ngayDat) {
		this.ngayDat = ngayDat;
	}
	public String getNgayGiao() {
		return ngayGiao;
	}
	public void setNgayGiao(String ngayGiao) {
		this.ngayGiao = ngayGiao;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return maDDH+"";
	}
}
