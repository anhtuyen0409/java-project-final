package model;

public class DonDatHangModel {
	private int maDDH;
	private String ngayDat;
	private String ngayGiao;
	private int maKH;
	private int daXoa;
	public int getMaDDH() {
		return maDDH;
	}
	public void setMaDDH(int maDDH) {
		this.maDDH = maDDH;
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
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public int getDaXoa() {
		return daXoa;
	}
	public void setDaXoa(int daXoa) {
		this.daXoa = daXoa;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return maDDH+"";
	}
}
