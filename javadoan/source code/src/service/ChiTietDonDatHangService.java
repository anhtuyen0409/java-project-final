package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.ChiTietDonDatHangModel;
import model.ChiTietPhieuNhapModel;
import model.HienThiToanBoChiTietDonDatHangModel;
import model.HienThiToanBoChiTietPhieuNhapModel;
import model.KhuyenMaiModel;

public class ChiTietDonDatHangService extends SQLServerService{
	public int themChiTietDonDatHang(ChiTietDonDatHangModel ctddh)
	{
		try
		{
			String sql="insert into ChiTietDonDatHang values(?,?,?,?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, ctddh.getMaDDH());
			preStatement.setInt(2, ctddh.getMaSP());
			preStatement.setInt(3, ctddh.getSoLuong());
			preStatement.setInt(4, ctddh.getUuDai());
			preStatement.setInt(5, ctddh.getThanhTien());
			preStatement.setInt(6, 0);
			preStatement.setInt(7, ctddh.getTongThanhToan());
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaChiTietDonDatHang(int maCTDDH, int MaSPMoi, int soLuongMoi, int thanhTienMoi, int uuDaiMoi, int tongThanhToanMoi){
		try
		{
			String sql="update ChiTietDonDatHang set MaSP=?, SoLuong=?, ThanhTien=?, UuDai=?, TongThanhToan=? where MaCTDDH=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, MaSPMoi);
			preStatement.setInt(2, soLuongMoi);
			preStatement.setInt(3, thanhTienMoi);
			preStatement.setInt(4, uuDaiMoi);
			preStatement.setInt(5, tongThanhToanMoi);
			preStatement.setInt(6, maCTDDH);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaChiTietDonDatHang(int ma){
		try
		{
			String sql="update ChiTietDonDatHang set DaXoa=? where MaCTDDH=?";
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
	public Vector<HienThiToanBoChiTietDonDatHangModel> docCTDDHTheoMaDDH(int maDDH)
	{
		Vector<HienThiToanBoChiTietDonDatHangModel>dsCTDDH = new Vector<HienThiToanBoChiTietDonDatHangModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call HienThiToanBoChiTietDonDatHangTheoMaDonDatHang(?)}");
			callStatement.setInt(1, maDDH);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoChiTietDonDatHangModel ctddh = new HienThiToanBoChiTietDonDatHangModel();
				ctddh.setMaCTDDH(rs.getInt(1));
				ctddh.setTenSP(rs.getString(2));
				ctddh.setSoLuong(rs.getInt(3));
				ctddh.setThanhTien(rs.getLong(4));
				ctddh.setUuDai(rs.getLong(5));
				ctddh.setTongThanhToan(rs.getLong(6));
				dsCTDDH.add(ctddh);
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsCTDDH;
	}
	public Vector<HienThiToanBoChiTietDonDatHangModel> docToanBoCTDDH(){
		Vector<HienThiToanBoChiTietDonDatHangModel>dsCTDDH = new Vector<HienThiToanBoChiTietDonDatHangModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call HienThiToanBoChiTietDonDatHang}");
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoChiTietDonDatHangModel ctddh = new HienThiToanBoChiTietDonDatHangModel();
				ctddh.setMaCTDDH(rs.getInt(1));
				ctddh.setTenSP(rs.getString(2));
				ctddh.setSoLuong(rs.getInt(3));
				ctddh.setThanhTien(rs.getLong(4));
				ctddh.setUuDai(rs.getLong(5));
				ctddh.setTongThanhToan(rs.getLong(6));
				dsCTDDH.add(ctddh);
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsCTDDH;
	}
	public Vector<HienThiToanBoChiTietDonDatHangModel> timKiemCTDDHTheoTenSP(String tenSP)
	{
		Vector<HienThiToanBoChiTietDonDatHangModel>dsCTDDH = new Vector<HienThiToanBoChiTietDonDatHangModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemChiTietDonDatHangTheoTenSanPham(?)}");
			callStatement.setString(1, tenSP);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoChiTietDonDatHangModel ctddh = new HienThiToanBoChiTietDonDatHangModel();
				ctddh.setMaCTDDH(rs.getInt(1));
				ctddh.setTenSP(rs.getString(2));
				ctddh.setSoLuong(rs.getInt(3));
				ctddh.setThanhTien(rs.getLong(4));
				ctddh.setUuDai(rs.getLong(5));
				ctddh.setTongThanhToan(rs.getLong(6));
				dsCTDDH.add(ctddh);
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsCTDDH;
	}
	/*public KhuyenMaiModel TimUuDaiTheoTienThanhToan(int tienThanhToan)
	{
		KhuyenMaiModel km = new KhuyenMaiModel();
		try{
			CallableStatement callStatement = conn.prepareCall("{call TimKhuyenMaiTheoTienThanhToan(?)}");
			callStatement.setInt(1, tienThanhToan);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				km.setMaKM(rs.getInt(1));
				km.setTienToiThieu(rs.getInt(2));
				km.setTienToiDa(rs.getInt(3));
				km.setUuDai(rs.getInt(4));
			}	
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return km;
	}*/
}
