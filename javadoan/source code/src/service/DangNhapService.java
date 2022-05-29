package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.DangNhapModel;
import model.NhanVienModel;

public class DangNhapService extends SQLServerService{
	public DangNhapModel login(String tenDangNhap, String matKhau){
		DangNhapModel account=null;
		try
		{
			String sql="select * from TaiKhoan where TenDangNhap=? and MatKhau=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenDangNhap);
			preStatement.setString(2, matKhau);
			ResultSet result=preStatement.executeQuery();
			if(result.next())
			{
				account=new DangNhapModel();
				account.setTenDangNhap(result.getString(1));
				account.setMatKhau(result.getString(2));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return account;
	}
	//test
	public DangNhapModel loginQLKho(String tenDangNhap, String matKhau){
		DangNhapModel account=null;
		try
		{
			String sql = "select * from TaiKhoan where TenDangNhap=? and MatKhau=? and MaLoaiTK=?";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenDangNhap);
			preStatement.setString(2, matKhau);
			preStatement.setInt(3, 1);
			ResultSet result=preStatement.executeQuery();
			if(result.next())
			{
				account=new DangNhapModel();
				account.setTenDangNhap(result.getString(1));
				account.setMatKhau(result.getString(2));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return account;
	}
	public DangNhapModel loginQLBanHang(String tenDangNhap, String matKhau){
		DangNhapModel account=null;
		try
		{
			String sql="select * from TaiKhoan where TenDangNhap=? and MatKhau=? and MaLoaiTK=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenDangNhap);
			preStatement.setString(2, matKhau);
			preStatement.setInt(3, 2);
			ResultSet result=preStatement.executeQuery();
			if(result.next())
			{
				account=new DangNhapModel();
				account.setTenDangNhap(result.getString(1));
				account.setMatKhau(result.getString(2));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return account;
	}
	/*public NhanVienModel nhanVienLogin(String tenDangNhap){
		NhanVienModel nv = null;
		try
		{
			String sql="select * from NhanVien where TenDangNhap=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenDangNhap);
			ResultSet result=preStatement.executeQuery();
			if(result.next())
			{
				nv = new NhanVienModel();
				nv.setTenDangNhap(result.getString(1));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return nv;
	}*/
}
