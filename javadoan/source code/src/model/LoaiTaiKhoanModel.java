package model;

public class LoaiTaiKhoanModel {
	private int maLoaiTK;
	private String tenLoaiTK;
	public int getMaLoaiTK() {
		return maLoaiTK;
	}
	public void setMaLoaiTK(int maLoaiTK) {
		this.maLoaiTK = maLoaiTK;
	}
	public String getTenLoaiTK() {
		return tenLoaiTK;
	}
	public void setTenLoaiTK(String tenLoaiTK) {
		this.tenLoaiTK = tenLoaiTK;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tenLoaiTK;
	}
}
