package service;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import model.HienThiToanBoDonDatHangModel;
import model.HinhAnhModel;
import model.NhaCungCapModel;
import model.SanPhamModel;



public class SanPhamService extends SQLServerService{
	//chưa fix xong
	public int themSanPham(SanPhamModel sp)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String sql="insert into SanPham values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, sp.getTenSP());
			preStatement.setInt(2, sp.getDonGia());
			preStatement.setString(3, sp.getNgayCapNhat());
			preStatement.setString(4, sp.getMoTa());
			preStatement.setString(5, sp.getHinhAnh());
			preStatement.setInt(6, sp.getSLTon());
			preStatement.setInt(7, 0);
			preStatement.setInt(8, sp.getMaLoaiSP());
			preStatement.setInt(9, sp.getMaNCC());
			preStatement.setInt(10, sp.getMaNSX());
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	
	public int suaSanPham(int ma, String tenMoi, int donGiaMoi, String date, String moTaMoi, String hinhAnhMoi, int SLTonMoi){
		try
		{
			String sql="update SanPham set TenSP=?, DonGia=?, NgayCapNhat=?, MoTa=?, HinhAnh=?, SLTon=? where MaSP=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenMoi);
			preStatement.setInt(2, donGiaMoi);
			preStatement.setString(3, date);
			preStatement.setString(4, moTaMoi);
			preStatement.setString(5, hinhAnhMoi);
			preStatement.setInt(6, SLTonMoi);
			preStatement.setInt(7, ma);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaSanPham(int ma){
		try
		{
			String sql="update SanPham set DaXoa=? where MaSP=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, 1);
			preStatement.setInt(2, ma);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public Vector<SanPhamModel>timSanPhamTheoTen(String tenSP)
	{
		Vector<SanPhamModel> vecsp = new Vector<SanPhamModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemSanPhamTheoTenSanPham(?)}");
			callStatement.setString(1, tenSP);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				SanPhamModel sp = new SanPhamModel();
				sp.setMaSP(rs.getInt(1));
				sp.setTenSP(rs.getString(2));
				sp.setDonGia(rs.getInt(3));
				sp.setNgayCapNhat(rs.getString(4));
				sp.setMoTa(rs.getString(5));
				sp.setHinhAnh(rs.getString(6));
				sp.setSLTon(rs.getInt(7));
				//tìm kiếm thì không cần liệt kê các FK
				vecsp.add(sp);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vecsp; //trả về danh sách tìm kiếm
	}
	public ArrayList<SanPhamModel> docSanPhamTheoLoai(int maLoai)
	{
		ArrayList<SanPhamModel>dsSP=new ArrayList<SanPhamModel>();
		try
		{
			String sql="select * from SanPham where MaLoaiSP=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maLoai);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				SanPhamModel sp=new SanPhamModel();
				sp.setMaSP(result.getInt(1));
				sp.setTenSP(result.getString(2));
				sp.setDonGia(result.getInt(3));
				sp.setNgayCapNhat(result.getString(4));
				sp.setMoTa(result.getString(5));
				sp.setHinhAnh(result.getString(6));
				sp.setSLTon(result.getInt(7));
				sp.setMaLoaiSP(result.getInt(8));
				sp.setMaNCC(result.getInt(9));
				sp.setMaNSX(result.getInt(10));
				sp.setDaXoa(0);
				dsSP.add(sp);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsSP;
	}
	public ArrayList<SanPhamModel> docSanPhamTheoNhaSanXuat(int maNSX)
	{
		ArrayList<SanPhamModel>dsSP = new ArrayList<SanPhamModel>();
		try
		{
			String sql="select * from SanPham where MaNSX=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maNSX);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				SanPhamModel sp = new SanPhamModel();
				sp.setMaSP(result.getInt(1));
				sp.setTenSP(result.getString(2));
				sp.setDonGia(result.getInt(3));
				sp.setNgayCapNhat(result.getString(4));
				sp.setMoTa(result.getString(5));
				sp.setHinhAnh(result.getString(6));
				sp.setSLTon(result.getInt(7));
				sp.setMaLoaiSP(result.getInt(8));
				sp.setMaNCC(result.getInt(9));
				sp.setMaNSX(result.getInt(10));
				sp.setDaXoa(0);
				dsSP.add(sp);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsSP;
	}
	public ArrayList<SanPhamModel> docSanPhamTheoNhaCungCap(int maNCC)
	{
		ArrayList<SanPhamModel>dsSP = new ArrayList<SanPhamModel>();
		try
		{
			String sql="select * from SanPham where MaNCC=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maNCC);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				SanPhamModel sp = new SanPhamModel();
				sp.setMaSP(result.getInt(1));
				sp.setTenSP(result.getString(2));
				sp.setDonGia(result.getInt(3));
				sp.setNgayCapNhat(result.getString(4));
				sp.setMoTa(result.getString(5));
				sp.setHinhAnh(result.getString(6));
				sp.setSLTon(result.getInt(7));
				sp.setMaLoaiSP(result.getInt(8));
				sp.setMaNCC(result.getInt(9));
				sp.setMaNSX(result.getInt(10));
				sp.setDaXoa(0);
				dsSP.add(sp);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsSP;
	}
	public ArrayList<SanPhamModel> docToanBoSanPham()
	{
		ArrayList<SanPhamModel>dsSP = new ArrayList<SanPhamModel>();
		try
		{
			String sql="select * from SanPham where DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				SanPhamModel sp = new SanPhamModel();
				sp.setMaSP(result.getInt(1));
				sp.setTenSP(result.getString(2));
				sp.setDonGia(result.getInt(3));
				sp.setNgayCapNhat(result.getString(4));
				sp.setMoTa(result.getString(5));
				sp.setHinhAnh(result.getString(6));
				sp.setSLTon(result.getInt(7));
				sp.setMaLoaiSP(result.getInt(8));
				sp.setMaNCC(result.getInt(9));
				sp.setMaNSX(result.getInt(10));
				sp.setDaXoa(0);
				dsSP.add(sp);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsSP;
	}
	public String layDiaChiHinhAnhTheoMaSP(int maSP){
		HinhAnhModel anh = null;
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call LayDiaChiHinhAnhTheoMaSanPham(?)}");
			callStatement.setInt(1, maSP);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next()){
				anh = new HinhAnhModel();
				anh.setHinhAnh(rs.getString(1));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return anh+"";
	}
}
