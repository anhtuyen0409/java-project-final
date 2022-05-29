package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import model.LoaiSanPhamModel;
import model.NhaSanXuatModel;

public class NhaSanXuatService extends SQLServerService{
	public int themNhaSanXuat(NhaSanXuatModel nsx)
	{
		try
		{
			String sql="insert into NhaSanXuat values(?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, nsx.getTenNSX());
			preStatement.setString(2, nsx.getThongTin());
			preStatement.setInt(3, 0);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaNhaSanXuat(int ma, String tenMoi, String thongTinMoi){
		try
		{
			String sql="update NhaSanXuat set TenNSX=?, ThongTin=? where MaNSX=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenMoi);
			preStatement.setString(2, thongTinMoi);
			preStatement.setInt(3, ma);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaNhaSanXuat(int ma){
		try
		{
			String sql="update NhaSanXuat set DaXoa=? where MaNSX=?";
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
	public Vector<NhaSanXuatModel>timNhaSanXuatTheoTen(String tenNSX)
	{
		Vector<NhaSanXuatModel> vecnsx = new Vector<NhaSanXuatModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemNhaSanXuatTheoTen(?)}");
			callStatement.setString(1, tenNSX);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				//hiển thị kq tìm kiếm, vì có 3 cột nên có 3 nsx.set
				NhaSanXuatModel nsx = new NhaSanXuatModel();
				nsx.setMaNSX(rs.getInt(1));
				nsx.setTenNSX(rs.getString(2));
				nsx.setThongTin(rs.getString(3));
				vecnsx.add(nsx);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vecnsx; //trả về danh sách tìm kiếm
	}
	public Vector<NhaSanXuatModel> docToanBoNhaSanXuat()
	{
		Vector<NhaSanXuatModel> vec = new Vector<NhaSanXuatModel>();
		try
		{
			String sql="select * from NhaSanXuat where DaXoa=0";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next())
			{
				NhaSanXuatModel nsx = new NhaSanXuatModel();
				nsx.setMaNSX(result.getInt(1));
				nsx.setTenNSX(result.getString(2));
				nsx.setThongTin(result.getString(3));
				nsx.setDaXoa(0);
				vec.add(nsx);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
}
