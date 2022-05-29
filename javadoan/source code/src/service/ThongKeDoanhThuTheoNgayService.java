package service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.ThongKeDoanhThuTheoNgayModel;

public class ThongKeDoanhThuTheoNgayService extends SQLServerService{
	public Vector<ThongKeDoanhThuTheoNgayModel> thongKeDoanhThuTheoNgay(){
		Vector<ThongKeDoanhThuTheoNgayModel> ds = new Vector<ThongKeDoanhThuTheoNgayModel>();
		try {
			CallableStatement cstmt = conn.prepareCall("{call ThongKeDoanhThuTheoNgay}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next())
			{
				ThongKeDoanhThuTheoNgayModel tktn = new ThongKeDoanhThuTheoNgayModel();
				tktn.setNgay(rs.getString(1));
				tktn.setTongTien(rs.getLong(2));
				ds.add(tktn);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ds;
	}
	
}
