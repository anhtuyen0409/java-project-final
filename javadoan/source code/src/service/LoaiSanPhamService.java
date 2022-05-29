package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import ui.BoPhanQuanLyKhoUI;
import model.LoaiSanPhamModel;


public class LoaiSanPhamService extends SQLServerService{
	public int themLoaiSanPham(LoaiSanPhamModel lsp)
	{
		try
		{
			String sql="insert into LoaiSanPham values(?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, lsp.getTenLoaiSP());
			preStatement.setInt(2, 0);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaLoaiSanPham(int ma, String tenMoi){
		try
		{
			String sql="update LoaiSanPham set TenLoaiSP=? where MaLoaiSP=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenMoi);
			preStatement.setInt(2, ma);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaLoaiSanPham(int ma){
		try
		{
			String sql="update LoaiSanPham set DaXoa=? where MaLoaiSP=?";
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
	public Vector<LoaiSanPhamModel>timLoaiSanPhamTheoTen(String tenLSP)
	{
		Vector<LoaiSanPhamModel> veclsp = new Vector<LoaiSanPhamModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemLoaiSanPhamTheoTenLoai(?)}");
			callStatement.setString(1, tenLSP);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				LoaiSanPhamModel lsp = new LoaiSanPhamModel();
				lsp.setMaLoaiSP(rs.getInt(1));
				lsp.setTenLoaiSP(rs.getString(2));
				veclsp.add(lsp);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return veclsp;
	}
	public Vector<LoaiSanPhamModel> docToanBoLoaiSanPham()
	{
		Vector<LoaiSanPhamModel> vec=new Vector<LoaiSanPhamModel>();
		try
		{
			String sql="select * from LoaiSanPham where DaXoa=0";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				LoaiSanPhamModel lsp=new LoaiSanPhamModel();
				lsp.setMaLoaiSP(result.getInt(1));
				lsp.setTenLoaiSP(result.getString(2));
				lsp.setDaXoa(0);
				vec.add(lsp);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
}
