package service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import model.LoaiNhanVienModel;
import model.TaiKhoanModel;

public class LoaiNhanVienService extends SQLServerService{
	public Vector<LoaiNhanVienModel> docToanBoLoaiNhanVien()
	{
		Vector<LoaiNhanVienModel> vec = new Vector<LoaiNhanVienModel>();
		try
		{
			String sql="select * from LoaiNhanVien where DaXoa=0";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next())
			{
				LoaiNhanVienModel lnv = new LoaiNhanVienModel();
				lnv.setMaLoaiNV(result.getInt(1));
				lnv.setTenLoaiNV(result.getString(2));
				lnv.setDaXoa(0);
				vec.add(lnv);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
}
