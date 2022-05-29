package model;

public class LoaiSanPhamModel {
	private int maLoaiSP;
	private String tenLoaiSP;
	private int daXoa;
	public int getMaLoaiSP() {
		return maLoaiSP;
	}
	public void setMaLoaiSP(int maLoaiSP) {
		this.maLoaiSP = maLoaiSP;
	}
	public String getTenLoaiSP() {
		return tenLoaiSP;
	}
	public void setTenLoaiSP(String tenLoaiSP) {
		this.tenLoaiSP = tenLoaiSP;
	}
	public int getDaXoa() {
		return daXoa;
	}
	public void setDaXoa(int daXoa) {
		this.daXoa = daXoa;
	}
	@Override
	public String toString() {
		return tenLoaiSP;
	}
}
