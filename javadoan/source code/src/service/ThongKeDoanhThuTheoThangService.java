package service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.ThongKeDoanhThuTheoNgayModel;
import model.ThongKeDoanhThuTheoThangModel;

public class ThongKeDoanhThuTheoThangService extends SQLServerService{
	public Vector<ThongKeDoanhThuTheoThangModel> thongKeDoanhThuTheoThang(int nam){
		Vector<ThongKeDoanhThuTheoThangModel> ds = new Vector<ThongKeDoanhThuTheoThangModel>();
		try {
			CallableStatement callStatement = conn.prepareCall("{call ThongKeDoanhThuTheoThang(?)}");
			callStatement.setInt(1, nam);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				ThongKeDoanhThuTheoThangModel tktt = new ThongKeDoanhThuTheoThangModel();
				tktt.setThang(rs.getInt(1));
				tktt.setTongTien(rs.getLong(2));
				ds.add(tktt);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ds;
	}
}
