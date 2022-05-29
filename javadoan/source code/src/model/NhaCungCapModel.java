package model;

public class NhaCungCapModel {
	private int maNCC;
	private String tenNCC;
	private String diaChi;
	private String SDT;
	private String email;
	private String fax;
	private int daXoa;
	public int getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
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
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
		return tenNCC;
	}
}
