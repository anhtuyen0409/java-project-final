package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import model.KhachHangModel;
import model.NhaCungCapModel;

public class KhachHangService extends SQLServerService{
	public int themKhachHang(KhachHangModel kh)
	{
		try
		{
			String sql="insert into KhachHang values(?,?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, kh.getTenKH());
			preStatement.setString(2, kh.getDiaChi());
			preStatement.setString(3, kh.getSDT());
			preStatement.setString(4, kh.getEmail());
			preStatement.setInt(5, 0);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaKhachHang(int ma, String tenMoi, String diaChiMoi, String SDTMoi, String emailMoi){
		try
		{
			String sql="update KhachHang set TenKH=?, DiaChi=?, SDT=?, Email=? where MaKH=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenMoi);
			preStatement.setString(2, diaChiMoi);
			preStatement.setString(3, SDTMoi);
			preStatement.setString(4, emailMoi);
			preStatement.setInt(5, ma);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaKhachHang(int ma){
		try
		{
			String sql="update KhachHang set DaXoa=? where MaKH=?";
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
	public Vector<KhachHangModel>timKhachHangTheoTen(String tenKH)
	{
		Vector<KhachHangModel> veckh = new Vector<KhachHangModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemKhachHangTheoTen(?)}");
			callStatement.setString(1, tenKH);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				KhachHangModel kh = new KhachHangModel();
				kh.setMaKH(rs.getInt(1));
				kh.setTenKH(rs.getString(2));
				kh.setDiaChi(rs.getString(3));
				kh.setSDT(rs.getString(4));
				kh.setEmail(rs.getString(5));
				veckh.add(kh);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return veckh; //trả về danh sách tìm kiếm
	}
	public Vector<KhachHangModel>timKhachHangTheoSDT(String sdt)
	{
		Vector<KhachHangModel> veckh = new Vector<KhachHangModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemKhachHangTheoSDT(?)}");
			callStatement.setString(1, sdt);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				KhachHangModel kh = new KhachHangModel();
				kh.setMaKH(rs.getInt(1));
				kh.setTenKH(rs.getString(2));
				kh.setDiaChi(rs.getString(3));
				kh.setSDT(rs.getString(4));
				kh.setEmail(rs.getString(5));
				veckh.add(kh);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return veckh; //trả về danh sách tìm kiếm
	}
	public Vector<KhachHangModel> docToanBoKhachHang()
	{
		Vector<KhachHangModel> vec = new Vector<KhachHangModel>();
		try
		{
			String sql="select * from KhachHang where DaXoa=0";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next())
			{
				KhachHangModel kh = new KhachHangModel();
				kh.setMaKH(result.getInt(1));
				kh.setTenKH(result.getString(2));
				kh.setDiaChi(result.getString(3));
				kh.setSDT(result.getString(4));
				kh.setEmail(result.getString(5));
				kh.setDaXoa(0);
				vec.add(kh);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
}
