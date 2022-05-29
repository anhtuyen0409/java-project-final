package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import model.KhuyenMaiModel;
import model.NhaSanXuatModel;

public class KhuyenMaiService extends SQLServerService{
	public Vector<KhuyenMaiModel> docToanBoKhuyenMai()
	{
		Vector<KhuyenMaiModel>dsKM = new Vector<KhuyenMaiModel>();
		try
		{
			String sql="select * from KhuyenMai where DaXoa=0";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next())
			{
				KhuyenMaiModel km = new KhuyenMaiModel();
				km.setMaKM(result.getInt(1));
				km.setTienToiThieu(result.getInt(2));
				km.setTienToiDa(result.getInt(3));
				km.setUuDai(result.getInt(4));
				km.setDaXoa(0);
				dsKM.add(km);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsKM;
	}
	public int themKhuyenMai(KhuyenMaiModel km)
	{
		try
		{
			String sql="insert into KhuyenMai values(?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, km.getTienToiThieu());
			preStatement.setInt(2, km.getTienToiDa());
			preStatement.setInt(3, km.getUuDai());
			preStatement.setInt(4, 0);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaKhuyenMai(int ma, int tienToiThieuMoi, int tienToiDaMoi, int uuDaiMoi){
		try
		{
			String sql="update KhuyenMai set TienToiThieu=?, TienToiDa=?, UuDai=? where MaKM=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, tienToiThieuMoi);
			preStatement.setInt(2, tienToiDaMoi);
			preStatement.setInt(3, uuDaiMoi);
			preStatement.setInt(4, ma);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaKhuyenMai(int ma){
		try
		{
			String sql="update KhuyenMai set DaXoa=? where MaKM=?";
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
}
