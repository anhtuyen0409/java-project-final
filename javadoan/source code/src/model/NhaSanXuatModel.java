package model;

public class NhaSanXuatModel {
	private int maNSX;
	private String tenNSX;
	private String thongTin;
	private int daXoa;
	public int getMaNSX() {
		return maNSX;
	}
	public void setMaNSX(int maNSX) {
		this.maNSX = maNSX;
	}
	public String getTenNSX() {
		return tenNSX;
	}
	public void setTenNSX(String tenNSX) {
		this.tenNSX = tenNSX;
	}
	public String getThongTin() {
		return thongTin;
	}
	public void setThongTin(String thongTin) {
		this.thongTin = thongTin;
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
		return tenNSX;
	}
}
