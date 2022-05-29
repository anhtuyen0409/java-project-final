package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import model.DonDatHangModel;
import model.HienThiToanBoChiTietDonDatHangModel;
import model.HienThiToanBoDonDatHangModel;
import model.HienThiToanBoPhieuNhapModel;
import model.KhachHangModel;
import model.NhaCungCapModel;
import model.PhieuNhapModel;

public class DonDatHangService extends SQLServerService{
	public int themDonDatHang(DonDatHangModel ddh)
	{
		try
		{
			String sql="insert into DonDatHang values(?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, ddh.getNgayDat());
			preStatement.setString(2, ddh.getNgayGiao());
			preStatement.setInt(3, 0);
			preStatement.setInt(4, ddh.getMaKH());
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaDonDatHang(int maDDH, String ngayDatMoi, String ngayGiaoMoi, int MaKHMoi){
		try
		{
			String sql="update DonDatHang set NgayDat=?, NgayGiao=?, MaKH=? where MaDDH=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, ngayDatMoi);
			preStatement.setString(2, ngayGiaoMoi);
			preStatement.setInt(3, MaKHMoi);
			preStatement.setInt(4, maDDH);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaDonDatHang(int ma){
		try
		{
			String sql="update DonDatHang set DaXoa=? where MaDDH=?";
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
	public Vector<HienThiToanBoDonDatHangModel> docToanBoDonDatHang()
	{
		Vector<HienThiToanBoDonDatHangModel> vec = new Vector<HienThiToanBoDonDatHangModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call HienThiToanBoDonDatHang}");
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoDonDatHangModel ddh = new HienThiToanBoDonDatHangModel();
				ddh.setMaDDH(rs.getInt(1));
				ddh.setTenKH(rs.getString(2));
				ddh.setNgayDat(rs.getString(3));
				ddh.setNgayGiao(rs.getString(4));
				vec.add(ddh);
			}		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public Vector<HienThiToanBoDonDatHangModel> docDonDatHangTheoMaKhachHang(int maKH)
	{
		Vector<HienThiToanBoDonDatHangModel>dsddh = new Vector<HienThiToanBoDonDatHangModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call HienThiToanBoDonDatHangTheoMaKhachHang(?)}");
			callStatement.setInt(1, maKH);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoDonDatHangModel ddh = new HienThiToanBoDonDatHangModel();
				ddh.setMaDDH(rs.getInt(1));
				ddh.setTenKH(rs.getString(2));
				ddh.setNgayDat(rs.getString(3));
				ddh.setNgayGiao(rs.getString(4));
				dsddh.add(ddh);
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsddh;
	}
	public Vector<HienThiToanBoDonDatHangModel> timKiemDonDatHangTheoTenKH(String tenKH){
		Vector<HienThiToanBoDonDatHangModel>dsddh = new Vector<HienThiToanBoDonDatHangModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemDonDatHangTheoTenKhachHang(?)}");
			callStatement.setString(1, tenKH);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoDonDatHangModel ddh = new HienThiToanBoDonDatHangModel();
				ddh.setMaDDH(rs.getInt(1));
				ddh.setTenKH(rs.getString(2));
				ddh.setNgayDat(rs.getString(3));
				ddh.setNgayGiao(rs.getString(4));
				dsddh.add(ddh);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsddh; //trả về danh sách tìm kiếm
	}
}
