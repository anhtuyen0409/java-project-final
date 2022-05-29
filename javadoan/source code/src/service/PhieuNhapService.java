package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import model.HienThiToanBoPhieuNhapModel;
import model.NhaCungCapModel;
import model.PhieuNhapModel;
import model.SanPhamModel;
import model.ThongKeDoanhThuTheoNgayModel;

public class PhieuNhapService extends SQLServerService{
	public int themPhieuNhap(PhieuNhapModel pn)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			String sql="insert into PhieuNhap values(?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, pn.getNgayNhap());
			preStatement.setInt(2, 0);
			preStatement.setInt(3, pn.getMaNV());
			preStatement.setInt(4, pn.getMaNCC());
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaPhieuNhap(int maPN, String ngayNhapMoi, int maNVMoi, int MaNCCMoi){
		try
		{
			String sql="update PhieuNhap set NgayNhap=?, MaNV=?, MaNCC=? where MaPN=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, ngayNhapMoi);
			preStatement.setInt(2, maNVMoi);
			preStatement.setInt(3, MaNCCMoi);
			preStatement.setInt(4, maPN);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaPhieuNhap(int ma){
		try
		{
			String sql="update PhieuNhap set DaXoa=? where MaPN=?";
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
	
	public Vector<HienThiToanBoPhieuNhapModel> docToanBoPhieuNhap()
	{
		Vector<HienThiToanBoPhieuNhapModel> vec = new Vector<HienThiToanBoPhieuNhapModel>();
		try
		{
			CallableStatement cstmt = conn.prepareCall("{call HienThiToanBoPhieuNhap}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next())
			{
				HienThiToanBoPhieuNhapModel htpn = new HienThiToanBoPhieuNhapModel();
				htpn.setMaPN(rs.getInt(1));
				htpn.setNgayNhap(rs.getString(2));
				htpn.setTenNV(rs.getString(3));
				htpn.setTenNCC(rs.getString(4));
				vec.add(htpn);
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public Vector<HienThiToanBoPhieuNhapModel> docPhieuNhapTheoNhanVien(int maNV)
	{
		Vector<HienThiToanBoPhieuNhapModel>dspn = new Vector<HienThiToanBoPhieuNhapModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call HienThiToanBoPhieuNhapTheoMaNhanVien(?)}");
			callStatement.setInt(1, maNV);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoPhieuNhapModel htpn = new HienThiToanBoPhieuNhapModel();
				htpn.setMaPN(rs.getInt(1));
				htpn.setNgayNhap(rs.getString(2));
				htpn.setTenNV(rs.getString(3));
				htpn.setTenNCC(rs.getString(4));
				dspn.add(htpn);
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dspn;
	}
	public Vector<HienThiToanBoPhieuNhapModel> docPhieuNhapTheoNhaCungCap(int maNCC)
	{
		Vector<HienThiToanBoPhieuNhapModel>dspn = new Vector<HienThiToanBoPhieuNhapModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call HienThiToanBoPhieuNhapTheoMaNhaCungCap(?)}");
			callStatement.setInt(1, maNCC);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoPhieuNhapModel htpn = new HienThiToanBoPhieuNhapModel();
				htpn.setMaPN(rs.getInt(1));
				htpn.setNgayNhap(rs.getString(2));
				htpn.setTenNV(rs.getString(3));
				htpn.setTenNCC(rs.getString(4));
				dspn.add(htpn);
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dspn;
	}
	public Vector<HienThiToanBoPhieuNhapModel> timKiemPhieuNhapTheoNgayNhap(String ngayNhap)
	{
		Vector<HienThiToanBoPhieuNhapModel> dspn = new Vector<HienThiToanBoPhieuNhapModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemPhieuNhapTheoNgayNhap(?)}");
			callStatement.setString(1, ngayNhap);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoPhieuNhapModel pn = new HienThiToanBoPhieuNhapModel();
				pn.setMaPN(rs.getInt(1));
				pn.setNgayNhap(rs.getString(2));
				pn.setTenNV(rs.getString(3));
				pn.setTenNCC(rs.getString(4));
				//tìm kiếm thì không cần liệt kê các FK
				dspn.add(pn);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dspn; //trả về danh sách tìm kiếm
	}
}
