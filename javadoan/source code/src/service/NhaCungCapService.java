package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import model.NhaCungCapModel;
import model.NhaSanXuatModel;

public class NhaCungCapService extends SQLServerService{
	public int themNhaCungCap(NhaCungCapModel ncc)
	{
		try
		{
			String sql="insert into NhaCungCap values(?,?,?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, ncc.getTenNCC());
			preStatement.setString(2, ncc.getDiaChi());
			preStatement.setString(3, ncc.getSDT());
			preStatement.setString(4, ncc.getEmail());
			preStatement.setString(5, ncc.getFax());
			preStatement.setInt(6, 0);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaNhaCungCap(int ma, String tenMoi, String diaChiMoi, String SDTMoi, String emailMoi, String FaxMoi){
		try
		{
			String sql="update NhaCungCap set TenNCC=?, DiaChi=?, SDT=?, Email=?, Fax=? where MaNCC=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenMoi);
			preStatement.setString(2, diaChiMoi);
			preStatement.setString(3, SDTMoi);
			preStatement.setString(4, emailMoi);
			preStatement.setString(5, FaxMoi);
			preStatement.setInt(6, ma);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaNhaCungCap(int ma){
		try
		{
			String sql="update NhaCungCap set DaXoa=? where MaNCC=?";
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
	
	public Vector<NhaCungCapModel>timNhaCungCapTheoTen(String tenNCC)
	{
		Vector<NhaCungCapModel> vecncc = new Vector<NhaCungCapModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemNhaCungCapTheoTen(?)}");
			callStatement.setString(1, tenNCC);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				NhaCungCapModel ncc = new NhaCungCapModel();
				ncc.setMaNCC(rs.getInt(1));
				ncc.setTenNCC(rs.getString(2));
				ncc.setDiaChi(rs.getString(3));
				ncc.setSDT(rs.getString(4));
				ncc.setEmail(rs.getString(5));
				ncc.setFax(rs.getString(6));
				vecncc.add(ncc);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vecncc; //trả về danh sách tìm kiếm
	}
	public Vector<NhaCungCapModel> docToanBoNhaCungCap()
	{
		Vector<NhaCungCapModel> vec = new Vector<NhaCungCapModel>();
		try
		{
			String sql="select * from NhaCungCap where DaXoa=0";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next())
			{
				NhaCungCapModel ncc = new NhaCungCapModel();
				ncc.setMaNCC(result.getInt(1));
				ncc.setTenNCC(result.getString(2));
				ncc.setDiaChi(result.getString(3));
				ncc.setSDT(result.getString(4));
				ncc.setEmail(result.getString(5));
				ncc.setFax(result.getString(6));
				ncc.setDaXoa(0);
				vec.add(ncc);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
}
