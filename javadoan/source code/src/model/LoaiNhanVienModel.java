package model;

public class LoaiNhanVienModel {
	private int maLoaiNV;
	private String tenLoaiNV;
	private int daXoa;
	public int getMaLoaiNV() {
		return maLoaiNV;
	}
	public void setMaLoaiNV(int maLoaiNV) {
		this.maLoaiNV = maLoaiNV;
	}
	public String getTenLoaiNV() {
		return tenLoaiNV;
	}
	public void setTenLoaiNV(String tenLoaiNV) {
		this.tenLoaiNV = tenLoaiNV;
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
		return tenLoaiNV;
	}
}
