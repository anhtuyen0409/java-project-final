package service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import model.LoaiSanPhamModel;
import model.LoaiTaiKhoanModel;

public class LoaiTaiKhoanService extends SQLServerService{
	public Vector<LoaiTaiKhoanModel> docToanBoLoaiTaiKhoan()
	{
		Vector<LoaiTaiKhoanModel> vec=new Vector<LoaiTaiKhoanModel>();
		try
		{
			String sql="select * from LoaiTaiKhoan";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				LoaiTaiKhoanModel ltk = new LoaiTaiKhoanModel();
				ltk.setMaLoaiTK(result.getInt(1));
				ltk.setTenLoaiTK(result.getString(2));
				vec.add(ltk);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
}
