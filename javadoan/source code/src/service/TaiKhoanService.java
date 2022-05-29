package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import model.NhaSanXuatModel;
import model.NhanVienModel;
import model.SanPhamModel;
import model.TaiKhoanModel;

public class TaiKhoanService extends SQLServerService{
	public int themTaiKhoan(TaiKhoanModel tk)
	{
		try
		{
			String sql="insert into TaiKhoan values(?,?,?,?)";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tk.getTenDangNhap());
			preStatement.setString(2, tk.getMatKhau());
			preStatement.setInt(3, 0);
			preStatement.setInt(4, tk.getMaLoaiTK());
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaTaiKhoan(String tenDangNhap, String matKhauMoi){
		try
		{
			String sql="update TaiKhoan set MatKhau=? where TenDangNhap=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, matKhauMoi);
			preStatement.setString(2, tenDangNhap);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaTaiKhoan(String tenDangNhap){
		try
		{
			String sql="update TaiKhoan set DaXoa=? where TenDangNhap=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, 1);
			preStatement.setString(2, tenDangNhap);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public Vector<TaiKhoanModel>timTaiKhoanTheoTenDangNhap(String tenDangNhap)
	{
		Vector<TaiKhoanModel> vectk = new Vector<TaiKhoanModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemTaiKhoanTheoTenDangNhap(?)}");
			callStatement.setString(1, tenDangNhap);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				TaiKhoanModel tk = new TaiKhoanModel();
				tk.setTenDangNhap(rs.getString(1));
				tk.setMatKhau(rs.getString(2));
				vectk.add(tk);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vectk; //trả về danh sách tìm kiếm
	}
	public Vector<TaiKhoanModel> docToanBoTaiKhoan()
	{
		Vector<TaiKhoanModel> vec = new Vector<TaiKhoanModel>();
		try
		{
			String sql="select * from TaiKhoan where DaXoa=0";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next())
			{
				TaiKhoanModel tk = new TaiKhoanModel();
				tk.setTenDangNhap(result.getString(1));
				tk.setMatKhau(result.getString(2));
				tk.setMaLoaiTK(result.getInt(3));
				tk.setDaXoa(0);
				vec.add(tk);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public Vector<TaiKhoanModel> docTaiKhoanTheoLoai(int maLoai)
	{
		Vector<TaiKhoanModel>dsTK = new Vector<TaiKhoanModel>();
		try
		{
			String sql="select * from TaiKhoan where MaLoaiTK=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maLoai);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				TaiKhoanModel tk = new TaiKhoanModel();
				tk.setTenDangNhap(result.getString(1));
				tk.setMatKhau(result.getString(2));
				tk.setDaXoa(0);
				tk.setMaLoaiTK(result.getInt(4));
				dsTK.add(tk);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsTK;
	}
	public int doiMatKhau(String tenDangNhap, String matKhauCu, String matKhauMoi){
		try
		{
			String sql="update TaiKhoan set MatKhau=? where TenDangNhap=? and MatKhau=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, matKhauMoi);
			preStatement.setString(2, tenDangNhap);
			preStatement.setString(3, matKhauCu);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
}
