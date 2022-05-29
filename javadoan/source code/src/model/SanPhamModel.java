package model;

import java.awt.Image;
import java.util.Date;

public class SanPhamModel {
	private int maSP;
	private String tenSP;
	private int donGia;
	private String ngayCapNhat; //sửa lại String
	private String moTa;
	private String hinhAnh;
	private int SLTon;
	private int maLoaiSP;
	private int maNCC;
	private int maNSX;
	private int daXoa;
	public int getMaSP() {
		return maSP;
	}
	public void setMaSP(int maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getDonGia() {
		return donGia;
	}
	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
	public String getNgayCapNhat() {
		return ngayCapNhat;
	}
	public void setNgayCapNhat(String ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public int getSLTon() {
		return SLTon;
	}
	public void setSLTon(int sLTon) {
		SLTon = sLTon;
	}
	public int getMaLoaiSP() {
		return maLoaiSP;
	}
	public void setMaLoaiSP(int maLoaiSP) {
		this.maLoaiSP = maLoaiSP;
	}
	public int getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}
	public int getMaNSX() {
		return maNSX;
	}
	public void setMaNSX(int maNSX) {
		this.maNSX = maNSX;
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
		return tenSP;
	}
}
