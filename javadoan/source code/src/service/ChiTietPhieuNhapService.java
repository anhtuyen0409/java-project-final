package service;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import model.ChiTietPhieuNhapModel;
import model.HienThiToanBoChiTietPhieuNhapModel;
import model.HienThiToanBoDonDatHangModel;
import model.HienThiToanBoPhieuNhapModel;
import model.SanPhamModel;

public class ChiTietPhieuNhapService extends SQLServerService{
	//thêm,xoá,sửa sử dụng PreparedStatement để tương tác csdl
	public int themChiTietPhieunhap(ChiTietPhieuNhapModel ctpn)
	{
		try
		{
			String sql="insert into ChiTietPhieuNhap values(?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, ctpn.getMaPN());
			preStatement.setInt(2, ctpn.getMaSP());
			preStatement.setInt(3, ctpn.getSoLuong());
			preStatement.setInt(4, 0);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaChiTietPhieuNhap(int maCTPN, int maPNMoi, int MaSPMoi, int soLuongMoi){
		try
		{
			String sql="update ChiTietPhieuNhap set MaPN=?, MaSP=?, SoLuong=? where MaCTPN=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maPNMoi);
			preStatement.setInt(2, MaSPMoi);
			preStatement.setInt(3, soLuongMoi);
			preStatement.setInt(4, maCTPN);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaChiTietPhieuNhap(int ma){
		try
		{
			String sql="update ChiTietPhieuNhap set DaXoa=? where MaCTPN=?";
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
	//hiển thị trên giao diện sử dụng procedure
	public Vector<HienThiToanBoChiTietPhieuNhapModel> docCTPNTheoMaPN(int maPN)
	{
		Vector<HienThiToanBoChiTietPhieuNhapModel>dsCTPN = new Vector<HienThiToanBoChiTietPhieuNhapModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call HienThiToanBoChiTietPhieuNhapTheoMaPhieuNhap(?)}");
			callStatement.setInt(1, maPN);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoChiTietPhieuNhapModel ctpn = new HienThiToanBoChiTietPhieuNhapModel();
				ctpn.setMaCTPN(rs.getInt(1));
				ctpn.setMaPN(rs.getInt(2));
				ctpn.setTenSP(rs.getString(3));
				ctpn.setSoLuong(rs.getInt(4));
				dsCTPN.add(ctpn);
			}	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsCTPN;
	}
	public Vector<HienThiToanBoChiTietPhieuNhapModel> docToanBoCTPN()
	{
		Vector<HienThiToanBoChiTietPhieuNhapModel> vec = new Vector<HienThiToanBoChiTietPhieuNhapModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call HienThiToanBoChiTietPhieuNhap}");
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoChiTietPhieuNhapModel ctpn = new HienThiToanBoChiTietPhieuNhapModel();
				ctpn.setMaCTPN(rs.getInt(1));
				ctpn.setMaPN(rs.getInt(2));
				ctpn.setTenSP(rs.getString(3));
				ctpn.setSoLuong(rs.getInt(4));
				vec.add(ctpn);
			}		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public Vector<HienThiToanBoChiTietPhieuNhapModel> timKiemChiTietPhieuNhapTheoTenSP(String tenSP)
	{
		Vector<HienThiToanBoChiTietPhieuNhapModel> vec = new Vector<HienThiToanBoChiTietPhieuNhapModel>();
		try
		{
			CallableStatement callStatement = conn.prepareCall("{call TimKiemChiTietPhieuNhapTheoTenSanPham(?)}");
			callStatement.setString(1, tenSP);
			ResultSet rs = callStatement.executeQuery();
			while(rs.next())
			{
				HienThiToanBoChiTietPhieuNhapModel ctpn = new HienThiToanBoChiTietPhieuNhapModel();
				ctpn.setMaCTPN(rs.getInt(1));
				ctpn.setMaPN(rs.getInt(2));
				ctpn.setTenSP(rs.getString(3));
				ctpn.setSoLuong(rs.getInt(4));
				vec.add(ctpn);
			}		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
}
