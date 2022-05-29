
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import service.ChiTietDonDatHangService;
import service.ChiTietPhieuNhapService;
import service.DonDatHangService;
import service.KhachHangService;
import service.LoaiSanPhamService;
import service.NhaCungCapService;
import service.NhanVienService;
import service.PhieuNhapService;
import service.SanPhamService;
import model.ChiTietDonDatHangModel;
import model.ChiTietPhieuNhapModel;
import model.DonDatHangModel;
import model.HienThiToanBoChiTietDonDatHangModel;
import model.HienThiToanBoDonDatHangModel;
import model.HienThiToanBoPhieuNhapModel;
import model.KhachHangModel;
import model.KhuyenMaiModel;
import model.LoaiSanPhamModel;
import model.NhaCungCapModel;
import model.NhaSanXuatModel;
import model.NhanVienModel;
import model.PhieuNhapModel;
import model.SanPhamModel;

public class BoPhanBanHangUI extends JFrame{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	JMenuBar mnuBar;//nơi để đựng (chứa) các JMenu
	JMenu mnuHeThong;
	JMenuItem mnuThongTin;
	JMenuItem mnuDangXuat;
	JMenuItem mnuThoat;
	JToolBar tooBar;
	JButton btnThongTin,btnDangXuat, btnThoat/*, btnTroVe*/;
	JPanel pnTab1, pnTab2;
	JTabbedPane tab;

	//controls Tab1
	//controls pnLeft
	DefaultTableModel dtmDonDatHang;
	JTable tblDonDatHang;
	JTextField txtTimKiemDDH;
	JDateChooser dcNgayDat, dcNgayGiao;
	JButton btnTimKiemDDH, btnThemDDH, btnSuaDDH, btnXoaDDH/*, btnReloadKH*/;
	JComboBox<KhachHangModel> cboKhachHang;
	Vector<HienThiToanBoDonDatHangModel> dsDonDatHang;
	JCheckBox chkHienThiToanBoDDH;
	//controls pnRight
	DefaultTableModel dtmChiTietDDH;
	JTable tblChiTietDDH;
	JPanel pnBottomOfRight;
	JTextField txtTimKiemCTDDH, txtUuDai, txtThanhTien, txtTongThanhToan, txtSoLuong;
	JButton btnTimKiemCTDDH, btnThemCTDDH, btnSuaCTDDH, btnXoaCTDDH, btnInHoaDon/*, btnReloadDDH*/;
	JComboBox<SanPhamModel> cboSanPham;
	Vector<HienThiToanBoChiTietDonDatHangModel> dsCTDDH;
	JComboBox<HienThiToanBoDonDatHangModel> cboDonDatHang;
	//JComboBox<Integer> cboSoLuong;
	JCheckBox chkHienThiToanBoCTDDH;
	JLabel lblAnhSP;
	BoPhanQuanLyKhoUI ui;

	//controls Tab2
	JTextField txtTimKiemKhachHangTheoTen, txtTimKiemKhachHangTheoSDT, txtTenKH, txtDiaChi, txtSDT, txtEmail;
	JButton btnTimKiemKhachHangTheoTen, btnTimKiemKhachHangTheoSDT, btnThemKH, btnSuaKH, btnXoaKH;
	DefaultTableModel dtmKhachHang;
	JTable tblKhachHang;
	Vector<KhachHangModel> dsKhachHang;
	JCheckBox chkHienThiToanBoKhachHang;
	public BoPhanBanHangUI(String title){
		super(title);
		addControls();
		addEvents();
		hienThiToanBoKhachHang();
		hienThiKhachHangLenCombobox();
		hienThiToanBoSanPhamLenCombobox();
		hienThiToanBoDonDatHang();
		hienThiToanBoDonDatHangLenCombobox();
		HienThiToanBoChiTietDonDatHang();
	}
	private void HienThiToanBoChiTietDonDatHang() {
		ChiTietDonDatHangService ctddhService = new ChiTietDonDatHangService();
		dsCTDDH = ctddhService.docToanBoCTDDH();
		dtmChiTietDDH.setRowCount(0);
		for(HienThiToanBoChiTietDonDatHangModel ctddh : dsCTDDH)
		{
			Vector<Object>vec = new Vector<Object>();
			vec.add(ctddh.getMaCTDDH());
			vec.add(ctddh.getTenSP());
			vec.add(ctddh.getSoLuong());
			vec.add(ctddh.getThanhTien());
			vec.add(ctddh.getUuDai());
			vec.add(ctddh.getTongThanhToan());
			dtmChiTietDDH.addRow(vec);
		}
	}
	private void hienThiToanBoDonDatHangLenCombobox() {
		DonDatHangService ddhService = new DonDatHangService();
		Vector<HienThiToanBoDonDatHangModel>dsddh = ddhService.docToanBoDonDatHang();
		cboDonDatHang.removeAllItems();
		for(HienThiToanBoDonDatHangModel ddh : dsddh)
		{
			cboDonDatHang.addItem(ddh);
		}
	}
	private void hienThiToanBoDonDatHang() {
		DonDatHangService ddhService = new DonDatHangService();
		dsDonDatHang = ddhService.docToanBoDonDatHang();
		dtmDonDatHang.setRowCount(0);
		for(HienThiToanBoDonDatHangModel ddh : dsDonDatHang)
		{
			Vector<Object>vec = new Vector<Object>();
			vec.add(ddh.getMaDDH());
			vec.add(ddh.getTenKH());
			vec.add(ddh.getNgayDat());
			vec.add(ddh.getNgayGiao());
			dtmDonDatHang.addRow(vec);
		}
	}
	private void hienThiToanBoSanPhamLenCombobox() {
		SanPhamService spService = new SanPhamService();
		ArrayList<SanPhamModel>dssp = spService.docToanBoSanPham();
		cboSanPham.removeAllItems();
		for(SanPhamModel sp : dssp)
		{
			cboSanPham.addItem(sp);
		}
	}
	private void hienThiKhachHangLenCombobox() {
		KhachHangService khService = new KhachHangService();
		Vector<KhachHangModel>vec = khService.docToanBoKhachHang();
		cboKhachHang.removeAllItems();
		for(KhachHangModel kh : vec)
		{
			cboKhachHang.addItem(kh);
		}
	}
	private void hienThiToanBoKhachHang() {
		KhachHangService khService = new KhachHangService();
		dsKhachHang = khService.docToanBoKhachHang();
		dtmKhachHang.setRowCount(0);
		for(KhachHangModel kh : dsKhachHang)
		{
			Vector<Object>vec=new Vector<Object>();
			vec.add(kh.getMaKH());
			vec.add(kh.getTenKH());
			vec.add(kh.getDiaChi());
			vec.add(kh.getSDT());
			vec.add(kh.getEmail());
			dtmKhachHang.addRow(vec);
		}
	}
	public void addControls(){
		mnuBar = new JMenuBar();
		setJMenuBar(mnuBar);
		mnuHeThong=new JMenu("Hệ Thống");
		mnuBar.add(mnuHeThong);
		mnuThongTin = new JMenuItem("Thông Tin");
		mnuThongTin.setIcon(new ImageIcon("images/info1.png"));
		mnuHeThong.add(mnuThongTin);
		mnuHeThong.addSeparator();
		mnuDangXuat = new JMenuItem("Đăng Xuất");
		mnuDangXuat.setIcon(new ImageIcon("images/logout1.png"));
		mnuHeThong.add(mnuDangXuat);
		mnuHeThong.addSeparator();
		mnuThoat = new JMenuItem("Thoát");
		mnuThoat.setIcon(new ImageIcon("images/exit5.png"));
		mnuHeThong.add(mnuThoat);
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		tooBar=new JToolBar();
		tooBar.setBackground(Color.LIGHT_GRAY);
		btnThongTin=new JButton("Thông Tin");
		btnThongTin.setBackground(Color.WHITE);
		btnThongTin.setIcon(new ImageIcon("images/info2.png"));
		btnThongTin.setFont(new Font("", Font.BOLD, 12));
		btnDangXuat=new JButton("Đăng Xuất");
		btnDangXuat.setBackground(Color.WHITE);
		btnDangXuat.setIcon(new ImageIcon("images/logout3.png"));
		btnDangXuat.setFont(new Font("", Font.BOLD, 12));
		btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.WHITE);
		btnThoat.setIcon(new ImageIcon("images/exit4.png"));
		btnThoat.setFont(new Font("", Font.BOLD, 12));
		//btnTroVe = new JButton("Trở Về");
		//btnTroVe.setFont(new Font("", Font.BOLD, 12));
		//btnTroVe.setBackground(Color.WHITE);
		//btnTroVe.setIcon(new ImageIcon("images/return1.png"));
		//tooBar.add(btnTroVe);
		tooBar.add(btnThongTin);tooBar.add(btnDangXuat);
		tooBar.add(btnThoat);
		con.add(tooBar,BorderLayout.NORTH);
		tab=new JTabbedPane();
		con.add(tab);
		pnTab1 = new JPanel();
		pnTab2 = new JPanel();
		tab.add(pnTab1,"Đơn đặt hàng");
		tab.add(pnTab2, "Khách hàng");

		//giao diện Tab 1 - đơn hàng & mã đơn hàng
		pnTab1.setLayout(new BorderLayout());
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(600, 0));
		JPanel pnRight = new JPanel();
		JSplitPane sp = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, pnLeft,pnRight);
		sp.setOneTouchExpandable(true);
		pnTab1.add(sp,BorderLayout.CENTER);

		//giao diện pnLeft
		pnLeft.setLayout(new BorderLayout());
		JPanel pnTopOfLeft = new JPanel();
		TitledBorder borderDDH = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED),
				"Thông tin đơn đặt hàng");
		borderDDH.setTitleColor(Color.BLUE);
		borderDDH.setTitleFont(new Font("", Font.BOLD, 15));
		pnTopOfLeft.setBorder(borderDDH);
		pnTopOfLeft.setLayout(new BorderLayout());
		pnLeft.add(pnTopOfLeft,BorderLayout.CENTER);
		pnTopOfLeft.setPreferredSize(new Dimension(0, 300));

		dtmDonDatHang = new DefaultTableModel();
		dtmDonDatHang.addColumn("Mã đơn hàng");
		dtmDonDatHang.addColumn("Khách hàng");
		dtmDonDatHang.addColumn("Ngày đặt hàng");
		dtmDonDatHang.addColumn("Ngày giao hàng");
		tblDonDatHang = new JTable(dtmDonDatHang);
		tblDonDatHang.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableDDH = new JScrollPane(tblDonDatHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfLeft.add(scTableDDH,BorderLayout.CENTER);

		JPanel pnBottomOfLeft = new JPanel();
		pnBottomOfLeft.setLayout(new BoxLayout(pnBottomOfLeft, BoxLayout.Y_AXIS));
		pnLeft.add(pnBottomOfLeft,BorderLayout.SOUTH);
		
		JPanel pnHienThiToanBoDDH = new JPanel();
		pnHienThiToanBoDDH.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkHienThiToanBoDDH = new JCheckBox("Hiển thị toàn bộ đơn đặt hàng");
		chkHienThiToanBoDDH.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBoDDH.setForeground(Color.BLUE);
		pnHienThiToanBoDDH.add(chkHienThiToanBoDDH);
		pnBottomOfLeft.add(pnHienThiToanBoDDH);

		JPanel pnTimKiemDDH = new JPanel();
		pnTimKiemDDH.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTimKiemDDH = new JLabel("Nhập dữ liệu tìm kiếm: ");
		lblTimKiemDDH.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemDDH = new JTextField(25);
		btnTimKiemDDH = new JButton("Tìm kiếm");
		btnTimKiemDDH.setIcon(new ImageIcon("images/search7.png"));
		pnTimKiemDDH.add(lblTimKiemDDH);
		pnTimKiemDDH.add(txtTimKiemDDH);
		pnTimKiemDDH.add(btnTimKiemDDH);
		pnBottomOfLeft.add(pnTimKiemDDH);

		JPanel pnComboKhachHang = new JPanel();
		pnComboKhachHang.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboKhachHang = new JLabel("Khách hàng: ");
		lblComboKhachHang.setFont(new Font("", Font.PLAIN, 15));
		cboKhachHang = new JComboBox<KhachHangModel>();
		cboKhachHang.setPreferredSize(new Dimension(390, 20));
		//btnReloadKH = new JButton();
		//btnReloadKH.setIcon(new ImageIcon("images/reload.png"));
		//btnReloadKH.setBackground(Color.WHITE);
		pnComboKhachHang.add(lblComboKhachHang);
		pnComboKhachHang.add(cboKhachHang);
		//pnComboKhachHang.add(btnReloadKH);
		pnBottomOfLeft.add(pnComboKhachHang);

		JPanel pnNgayDatHang = new JPanel();
		pnNgayDatHang.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgayDatHang = new JLabel("Ngày đặt hàng: ");
		lblNgayDatHang.setFont(new Font("", Font.PLAIN, 15));
		dcNgayDat = new JDateChooser();
		dcNgayDat.setPreferredSize(new Dimension(390, 20));
		pnNgayDatHang.add(lblNgayDatHang);
		pnNgayDatHang.add(dcNgayDat);
		pnBottomOfLeft.add(pnNgayDatHang);

		JPanel pnNgayGiaoHang = new JPanel();
		pnNgayGiaoHang.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgayGiaoHang = new JLabel("Ngày giao hàng: ");
		lblNgayGiaoHang.setFont(new Font("", Font.PLAIN, 15));
		dcNgayGiao = new JDateChooser();
		dcNgayGiao.setPreferredSize(new Dimension(390, 20));
		pnNgayGiaoHang.add(lblNgayGiaoHang);
		pnNgayGiaoHang.add(dcNgayGiao);
		pnBottomOfLeft.add(pnNgayGiaoHang);


		JPanel pnControlsLeft = new JPanel();
		pnControlsLeft.setLayout(new FlowLayout());
		btnThemDDH = new JButton("Thêm mới");
		btnThemDDH.setIcon(new ImageIcon("images/new.png"));
		btnSuaDDH = new JButton("Sửa thông tin");
		btnSuaDDH.setIcon(new ImageIcon("images/edit.png"));
		btnXoaDDH = new JButton("Xoá");
		btnXoaDDH.setIcon(new ImageIcon("images/remove.png"));
		pnControlsLeft.add(btnThemDDH);
		pnControlsLeft.add(btnSuaDDH);
		pnControlsLeft.add(btnXoaDDH);
		pnBottomOfLeft.add(pnControlsLeft);

		//canh chỉnh
		lblComboKhachHang.setPreferredSize(lblTimKiemDDH.getPreferredSize());
		lblNgayDatHang.setPreferredSize(lblTimKiemDDH.getPreferredSize());
		lblNgayGiaoHang.setPreferredSize(lblTimKiemDDH.getPreferredSize());

		//giao diện pnRight
		pnRight.setLayout(new BorderLayout());
		JPanel pnTopOfRight = new JPanel();
		TitledBorder borderCTDDH = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED),
				"Thông tin chi tiết đơn đặt hàng");
		borderCTDDH.setTitleColor(Color.BLUE);
		borderCTDDH.setTitleFont(new Font("", Font.BOLD, 15));
		pnTopOfRight.setBorder(borderCTDDH);
		pnTopOfRight.setLayout(new BorderLayout());
		pnRight.add(pnTopOfRight,BorderLayout.CENTER);

		dtmChiTietDDH = new DefaultTableModel();
		dtmChiTietDDH.addColumn("Mã chi tiết đơn hàng");
		dtmChiTietDDH.addColumn("Sản phẩm");
		dtmChiTietDDH.addColumn("Số lượng");
		dtmChiTietDDH.addColumn("Thành tiền");
		dtmChiTietDDH.addColumn("Ưu đãi");
		dtmChiTietDDH.addColumn("Tổng thanh toán");
		tblChiTietDDH = new JTable(dtmChiTietDDH);
		tblChiTietDDH.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableCTDDH = new JScrollPane(tblChiTietDDH,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfRight.add(scTableCTDDH,BorderLayout.CENTER);

		pnBottomOfRight = new JPanel();
		pnBottomOfRight.setPreferredSize(new Dimension(0, 480));
		pnBottomOfRight.setLayout(new BorderLayout());
		pnRight.add(pnBottomOfRight,BorderLayout.SOUTH);

		JPanel pnLeftOfBottomOfRight = new JPanel();
		pnLeftOfBottomOfRight.setLayout(new BoxLayout(pnLeftOfBottomOfRight, BoxLayout.Y_AXIS));
		pnBottomOfRight.add(pnLeftOfBottomOfRight, BorderLayout.CENTER);
		
		JPanel pnHienThiToanBoCTDDH = new JPanel();
		pnHienThiToanBoCTDDH.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkHienThiToanBoCTDDH = new JCheckBox("Hiển thị toàn bộ chi tiết đơn đặt hàng");
		chkHienThiToanBoCTDDH.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBoCTDDH.setForeground(Color.BLUE);
		pnHienThiToanBoCTDDH.add(chkHienThiToanBoCTDDH);
		pnLeftOfBottomOfRight.add(pnHienThiToanBoCTDDH);

		JPanel pnTimKiemCTDDH = new JPanel();
		pnTimKiemCTDDH.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTimKiemCTDDH = new JLabel("Nhập dữ liệu tìm kiếm: ");
		lblTimKiemCTDDH.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemCTDDH = new JTextField(20);
		btnTimKiemCTDDH = new JButton("Tìm kiếm");
		btnTimKiemCTDDH.setIcon(new ImageIcon("images/search3.png"));
		pnTimKiemCTDDH.add(lblTimKiemCTDDH);
		pnTimKiemCTDDH.add(txtTimKiemCTDDH);
		pnTimKiemCTDDH.add(btnTimKiemCTDDH);
		pnLeftOfBottomOfRight.add(pnTimKiemCTDDH);

		JPanel pnComboDonDatHang = new JPanel();
		pnComboDonDatHang.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboDonDatHang = new JLabel("Mã đơn đặt hàng: ");
		lblComboDonDatHang.setFont(new Font("", Font.PLAIN, 15));
		cboDonDatHang = new JComboBox<HienThiToanBoDonDatHangModel>();
		cboDonDatHang.setPreferredSize(new Dimension(340, 20));
		/*btnReloadDDH = new JButton();
		btnReloadDDH.setIcon(new ImageIcon("images/reload3.png"));
		btnReloadDDH.setBackground(Color.WHITE);*/
		pnComboDonDatHang.add(lblComboDonDatHang);
		pnComboDonDatHang.add(cboDonDatHang);
		//pnComboDonDatHang.add(btnReloadDDH);
		pnLeftOfBottomOfRight.add(pnComboDonDatHang);

		JPanel pnComboSanPham = new JPanel();
		pnComboSanPham.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboSanPham = new JLabel("Sản phẩm: ");
		lblComboSanPham.setFont(new Font("", Font.PLAIN, 15));
		cboSanPham = new JComboBox<SanPhamModel>();
		cboSanPham.setPreferredSize(new Dimension(340, 20));
		pnComboSanPham.add(lblComboSanPham);
		pnComboSanPham.add(cboSanPham);
		pnLeftOfBottomOfRight.add(pnComboSanPham);

		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSoLuong = new JLabel("Số lượng: ");
		lblSoLuong.setFont(new Font("", Font.PLAIN, 15));
		/*cboSoLuong = new JComboBox<Integer>();
		cboSoLuong.setPreferredSize(new Dimension(340, 20));
		Vector<Integer>vec = new Vector<Integer>();
		cboSoLuong.removeAllItems();
		for(int i=0; i<100; i++){
			vec.add(i);
		}
		for(Integer x : vec)
		{
			cboSoLuong.addItem(x);
		}*/
		txtSoLuong = new JTextField(30);
		pnSoLuong.add(lblSoLuong);
		//pnSoLuong.add(cboSoLuong);
		pnSoLuong.add(txtSoLuong);
		pnLeftOfBottomOfRight.add(pnSoLuong);

		JPanel pnThanhTien = new JPanel();
		pnThanhTien.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblThanhTien = new JLabel("Thành tiền: ");
		lblThanhTien.setFont(new Font("", Font.PLAIN, 15));
		txtThanhTien = new JTextField(30);
		txtThanhTien.disable();
		pnThanhTien.add(lblThanhTien);
		pnThanhTien.add(txtThanhTien);
		pnLeftOfBottomOfRight.add(pnThanhTien);

		JPanel pnUuDai = new JPanel();
		pnUuDai.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblUuDai = new JLabel("Ưu đãi: ");
		lblUuDai.setFont(new Font("", Font.PLAIN, 15));
		txtUuDai = new JTextField(30);
		txtUuDai.disable();
		pnUuDai.add(lblUuDai);
		pnUuDai.add(txtUuDai);
		pnLeftOfBottomOfRight.add(pnUuDai);

		JPanel pnTongThanhToan = new JPanel();
		pnTongThanhToan.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTongThanhToan = new JLabel("Tổng thanh toán: ");
		lblTongThanhToan.setFont(new Font("", Font.PLAIN, 15));
		txtTongThanhToan = new JTextField(30);
		txtTongThanhToan.disable();
		pnTongThanhToan.add(lblTongThanhToan);
		pnTongThanhToan.add(txtTongThanhToan);
		pnLeftOfBottomOfRight.add(pnTongThanhToan);

		JPanel pnControlsRight = new JPanel();
		pnControlsRight.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnThemCTDDH = new JButton("Thêm mới");
		btnThemCTDDH.setIcon(new ImageIcon("images/new6.png"));
		btnSuaCTDDH = new JButton("Sửa thông tin");
		btnSuaCTDDH.setIcon(new ImageIcon("images/edit6.png"));
		btnXoaCTDDH = new JButton("Xoá");
		btnXoaCTDDH.setIcon(new ImageIcon("images/remove6.png"));
		btnInHoaDon = new JButton("In hoá đơn");
		btnInHoaDon.setIcon(new ImageIcon("images/print.png"));
		pnControlsRight.add(btnThemCTDDH);
		pnControlsRight.add(btnSuaCTDDH);
		pnControlsRight.add(btnXoaCTDDH);
		pnControlsRight.add(btnInHoaDon);
		pnLeftOfBottomOfRight.add(pnControlsRight);

		//canh chỉnh
		lblComboSanPham.setPreferredSize(lblTimKiemCTDDH.getPreferredSize());
		lblSoLuong.setPreferredSize(lblTimKiemCTDDH.getPreferredSize());
		lblUuDai.setPreferredSize(lblTimKiemCTDDH.getPreferredSize());
		lblThanhTien.setPreferredSize(lblTimKiemCTDDH.getPreferredSize());
		lblComboDonDatHang.setPreferredSize(lblTimKiemCTDDH.getPreferredSize());
		lblTongThanhToan.setPreferredSize(lblTimKiemCTDDH.getPreferredSize());

		//giao diện Tab 2 - khách hàng
		pnTab2.setLayout(new BorderLayout());
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.Y_AXIS));
		pnTab2.add(pnTop, BorderLayout.NORTH);

		JPanel pnTimKiemKhachHangTheoTen = new JPanel();
		pnTimKiemKhachHangTheoTen.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTimKiemKhachHangTheoTen = new JLabel("Nhập tên khách hàng tìm kiếm: ");
		lblTimKiemKhachHangTheoTen.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemKhachHangTheoTen = new JTextField(30);
		btnTimKiemKhachHangTheoTen = new JButton("Tìm kiếm");
		btnTimKiemKhachHangTheoTen.setIcon(new ImageIcon("images/search8.png"));
		pnTimKiemKhachHangTheoTen.add(lblTimKiemKhachHangTheoTen);
		pnTimKiemKhachHangTheoTen.add(txtTimKiemKhachHangTheoTen);
		pnTimKiemKhachHangTheoTen.add(btnTimKiemKhachHangTheoTen);
		pnTop.add(pnTimKiemKhachHangTheoTen);
		
		JPanel pnTimKiemKhachHangTheoSDT = new JPanel();
		pnTimKiemKhachHangTheoSDT.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTimKiemKhachHangTheoSDT = new JLabel("Nhập số điện thoại tìm kiếm: ");
		lblTimKiemKhachHangTheoSDT.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemKhachHangTheoSDT = new JTextField(30);
		btnTimKiemKhachHangTheoSDT = new JButton("Tìm kiếm");
		btnTimKiemKhachHangTheoSDT.setIcon(new ImageIcon("images/search11.png"));
		pnTimKiemKhachHangTheoSDT.add(lblTimKiemKhachHangTheoSDT);
		pnTimKiemKhachHangTheoSDT.add(txtTimKiemKhachHangTheoSDT);
		pnTimKiemKhachHangTheoSDT.add(btnTimKiemKhachHangTheoSDT);
		pnTop.add(pnTimKiemKhachHangTheoSDT);

		JPanel pnTenKH = new JPanel();
		pnTenKH.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTenKH = new JLabel("Tên khách hàng: ");
		lblTenKH.setFont(new Font("", Font.PLAIN, 15));
		txtTenKH = new JTextField(40);
		pnTenKH.add(lblTenKH);
		pnTenKH.add(txtTenKH);
		pnTop.add(pnTenKH);

		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("", Font.PLAIN, 15));
		txtDiaChi = new JTextField(40);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnTop.add(pnDiaChi);

		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("", Font.PLAIN, 15));
		txtSDT = new JTextField(40);
		pnSDT.add(lblSDT);
		pnSDT.add(txtSDT);
		pnTop.add(pnSDT);

		JPanel pnEmail = new JPanel();
		pnEmail.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("", Font.PLAIN, 15));
		txtEmail = new JTextField(40);
		pnEmail.add(lblEmail);
		pnEmail.add(txtEmail);
		pnTop.add(pnEmail);
		
		JPanel pnHienThiToanBoKhachHang = new JPanel();
		pnHienThiToanBoKhachHang.setLayout(new FlowLayout(FlowLayout.CENTER));
		chkHienThiToanBoKhachHang = new JCheckBox("Hiển thị toàn bộ khách hàng");
		chkHienThiToanBoKhachHang.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBoKhachHang.setForeground(Color.BLUE);
		pnHienThiToanBoKhachHang.add(chkHienThiToanBoKhachHang);
		pnTop.add(pnHienThiToanBoKhachHang);

		//canh chỉnh
		lblTimKiemKhachHangTheoSDT.setPreferredSize(lblTimKiemKhachHangTheoTen.getPreferredSize());
		lblTenKH.setPreferredSize(lblTimKiemKhachHangTheoTen.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTimKiemKhachHangTheoTen.getPreferredSize());
		lblSDT.setPreferredSize(lblTimKiemKhachHangTheoTen.getPreferredSize());
		lblEmail.setPreferredSize(lblTimKiemKhachHangTheoTen.getPreferredSize());
		chkHienThiToanBoKhachHang.setPreferredSize(txtTimKiemKhachHangTheoTen.getPreferredSize());

		JPanel pnControlsOfKH = new JPanel();
		pnControlsOfKH.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThemKH = new JButton("Thêm mới");
		btnThemKH.setIcon(new ImageIcon("images/new7.png"));
		btnSuaKH = new JButton("Sửa thông tin");
		btnSuaKH.setIcon(new ImageIcon("images/edit7.png"));
		btnXoaKH = new JButton("Xoá");
		btnXoaKH.setIcon(new ImageIcon("images/remove7.png"));
		pnControlsOfKH.add(btnThemKH);
		pnControlsOfKH.add(btnSuaKH);
		pnControlsOfKH.add(btnXoaKH);
		pnTop.add(pnControlsOfKH);


		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnTab2.add(pnBottom, BorderLayout.CENTER);

		TitledBorder borderKH = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED),
				"Thông tin khách hàng");
		borderKH.setTitleColor(Color.BLUE);
		borderKH.setTitleFont(new Font("", Font.BOLD, 15));
		pnBottom.setBorder(borderKH);

		dtmKhachHang = new DefaultTableModel();
		dtmKhachHang.addColumn("Mã khách hàng");
		dtmKhachHang.addColumn("Tên Khách hàng");
		dtmKhachHang.addColumn("Địa chỉ");
		dtmKhachHang.addColumn("Số điện thoai");
		dtmKhachHang.addColumn("Email");
		tblKhachHang = new JTable(dtmKhachHang);
		tblKhachHang.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableKH = new JScrollPane(tblKhachHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottom.add(scTableKH,BorderLayout.CENTER);
	}
	public void addEvents(){
		mnuDangXuat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất không?","Thông báo",JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.NO_OPTION){
					return;
				}
				else{
					dispose();
					DangNhapUI ui = new DangNhapUI("Login");
					ui.showWindows();
				}
			}
		});
		mnuThoat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thoát không?","Thông báo",JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.NO_OPTION){
					return;
				}
				else{
					System.exit(0);
				}
			}
		});
		mnuThongTin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Mọi thông tin liên hệ liên hệ sdt 0394420076 hoặc gửi mail đến địa chỉ nguyenanhtuyen10a5@gmail.com!");
			}
		});
		btnThongTin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Mọi thông tin liên hệ liên hệ sdt 0394420076 hoặc gửi mail đến địa chỉ nguyenanhtuyen10a5@gmail.com!");
			}
		});
		/*btnTroVe.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				DanhSachChucNangUI ui = new DanhSachChucNangUI("Hệ thống cửa hàng điện thoại");
				ui.showWindows();
			}
		});*/
		btnDangXuat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất không?","Thông báo",JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.NO_OPTION){
					return;
				}
				else{
					dispose();
					DangNhapUI ui = new DangNhapUI("Login");
					ui.showWindows();
				}
			}
		});
		btnThoat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thoát không?","Thông báo",JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.NO_OPTION){
					return;
				}
				else{
					System.exit(0);
				}
			}
		});

		//events Tab1
		tblDonDatHang.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseClicked(MouseEvent e) {
				int row = tblDonDatHang.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					HienThiToanBoDonDatHangModel ddh = dsDonDatHang.get(row);
					//dcNgayDat.set(ddh.getNgayDat());
					//dcNgayGiao.setText(ddh.getNgayGiao());

					ChiTietDonDatHangService ctddhService = new ChiTietDonDatHangService();
					dsCTDDH = ctddhService.docCTDDHTheoMaDDH(Integer.parseInt(tblDonDatHang.getValueAt(row, 0)+""));
					dtmChiTietDDH.setRowCount(0);
					for(HienThiToanBoChiTietDonDatHangModel ctddh : dsCTDDH)
					{
						Vector<Object>vec = new Vector<Object>();
						vec.add(ctddh.getMaCTDDH());
						vec.add(ctddh.getTenSP());
						vec.add(ctddh.getSoLuong());
						vec.add(ctddh.getThanhTien());
						vec.add(ctddh.getUuDai());
						vec.add(ctddh.getTongThanhToan());
						dtmChiTietDDH.addRow(vec);
					}
				}
			}
		});
		tblChiTietDDH.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseClicked(MouseEvent e) {
				int row = tblChiTietDDH.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					HienThiToanBoChiTietDonDatHangModel ctddh = dsCTDDH.get(row);
					txtUuDai.setText(ctddh.getUuDai()+"");
					txtThanhTien.setText(ctddh.getThanhTien()+"");
					txtTongThanhToan.setText(ctddh.getTongThanhToan()+"");
				}
			}
		});
		/*btnReloadKH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				hienThiKhachHangLenCombobox();
			}
		});*/
		chkHienThiToanBoDDH.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					DonDatHangService ddhService = new DonDatHangService();
					dsDonDatHang = ddhService.docToanBoDonDatHang();
					dtmDonDatHang.setRowCount(0);
					for(HienThiToanBoDonDatHangModel ddh : dsDonDatHang)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(ddh.getMaDDH());
						vec.add(ddh.getTenKH());
						vec.add(ddh.getNgayDat());
						vec.add(ddh.getNgayGiao());
						dtmDonDatHang.addRow(vec);
					}
				}
				else{
					dtmDonDatHang.setRowCount(0);
				}
			}
		});
		chkHienThiToanBoCTDDH.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					ChiTietDonDatHangService ctddhService = new ChiTietDonDatHangService();
					dsCTDDH = ctddhService.docToanBoCTDDH();
					dtmChiTietDDH.setRowCount(0);
					for(HienThiToanBoChiTietDonDatHangModel ctddh : dsCTDDH)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(ctddh.getMaCTDDH());
						vec.add(ctddh.getTenSP());
						vec.add(ctddh.getSoLuong());
						vec.add(ctddh.getThanhTien());
						vec.add(ctddh.getUuDai());
						vec.add(ctddh.getTongThanhToan());
						dtmChiTietDDH.addRow(vec);
					}
				}
				else{
					dtmChiTietDDH.setRowCount(0);
				}
			}
		});
		chkHienThiToanBoKhachHang.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					KhachHangService khService = new KhachHangService();
					dsKhachHang = khService.docToanBoKhachHang();
					dtmKhachHang.setRowCount(0);
					for(KhachHangModel kh : dsKhachHang)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(kh.getMaKH());
						vec.add(kh.getTenKH());
						vec.add(kh.getDiaChi());
						vec.add(kh.getSDT());
						vec.add(kh.getEmail());
						dtmKhachHang.addRow(vec);
					}
				}
				else{
					dtmKhachHang.setRowCount(0);
				}
			}
		});
		btnTimKiemDDH.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemDonDatHang();
			}
		});
		btnThemDDH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemDonDatHang();
			}
		});
		btnSuaDDH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaDonDatHang();
			}
		});
		btnXoaDDH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaDonDatHang();
			}
		});

		cboDonDatHang.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(cboDonDatHang.getSelectedIndex() == -1){
					return;
				}
				else{
					ChiTietDonDatHangService ctddhService = new ChiTietDonDatHangService();
					dsCTDDH = ctddhService.docCTDDHTheoMaDDH(Integer.parseInt(tblDonDatHang.getValueAt(cboDonDatHang.getSelectedIndex(), 0)+""));
					dtmChiTietDDH.setRowCount(0);
					for(HienThiToanBoChiTietDonDatHangModel ctddh : dsCTDDH)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(ctddh.getMaCTDDH());
						vec.add(ctddh.getTenSP());
						vec.add(ctddh.getSoLuong());
						vec.add(ctddh.getThanhTien());
						vec.add(ctddh.getUuDai());
						vec.add(ctddh.getTongThanhToan());
						dtmChiTietDDH.addRow(vec);
					}
				}
			}
		});

		/*cboSoLuong.addActionListener(new ActionListener() {
			BoPhanQuanLyKhoUI ui = new BoPhanQuanLyKhoUI("");
			public void actionPerformed(ActionEvent e) {
				try{
					txtThanhTien.setText(Integer.toString((ui.layDonGiaSanphamTheoTen(
							cboSanPham.getSelectedIndex())*Integer.parseInt(cboSoLuong.getSelectedItem()+""))));
					txtUuDai.setText(String.valueOf(tinhUuDai()));
					txtTongThanhToan.setText(String.valueOf((Integer.parseInt(txtThanhTien.getText())-Integer.parseInt(txtUuDai.getText()))));
				}
				catch( java.lang.NumberFormatException ex){
					ex.printStackTrace();
				}
			}
		});*/
		
		txtSoLuong.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent e) {
				warm();
			}
			public void insertUpdate(DocumentEvent e) {
				warm();
			}
			public void changedUpdate(DocumentEvent e) {
				warm();
			}
			public void warm(){
				if(txtSoLuong.getText().equals("")){
					txtThanhTien.setText(0+"");
					txtUuDai.setText(0+"");
					txtTongThanhToan.setText(0+"");
				}
				else{
					BoPhanQuanLyKhoUI ui = new BoPhanQuanLyKhoUI("");
					try{
						txtThanhTien.setText(Integer.toString((ui.layDonGiaSanphamTheoTen(
								cboSanPham.getSelectedIndex())*Integer.parseInt(txtSoLuong.getText()))));
						txtUuDai.setText(tinhUuDai()+"");
						txtTongThanhToan.setText(String.valueOf((Integer.parseInt(txtThanhTien.getText())-Integer.parseInt(txtUuDai.getText()))));
					}
					catch( java.lang.NumberFormatException ex){
						ex.printStackTrace();
					}
				}
			}
		});
		
		btnTimKiemCTDDH.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemCTDDH();
			}
		});

		btnThemCTDDH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemCTDDH();
			}
		});
		btnSuaCTDDH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaCTDDH();
			}
		});
		btnXoaCTDDH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaCTDDH();
			}
		});
		cboSanPham.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(cboSanPham.getSelectedIndex() == -1){
					return;
				}
				else{
					SanPhamService spService = new SanPhamService();
					ui = new BoPhanQuanLyKhoUI("");
					//giao diện ảnh sản phẩm
					JPanel pnRightOfBottomOfRight = new JPanel();
					pnRightOfBottomOfRight.setBackground(Color.WHITE);
					pnRightOfBottomOfRight.setPreferredSize(new Dimension(450, 0));
					pnRightOfBottomOfRight.setLayout(new BorderLayout());
					pnBottomOfRight.add(pnRightOfBottomOfRight,BorderLayout.EAST);
					TitledBorder borderAnhSP=
							new TitledBorder(
									BorderFactory.createLineBorder(Color.RED),
									"Hình ảnh sản phẩm");
					borderAnhSP.setTitleColor(Color.BLUE);
					borderAnhSP.setTitleFont(new Font("", Font.ITALIC, 20));
					pnRightOfBottomOfRight.setBorder(borderAnhSP);
					lblAnhSP = new JLabel(new ImageIcon(spService.layDiaChiHinhAnhTheoMaSP(ui.layMaSanPhamTheoThuTu(cboSanPham.getSelectedIndex()))));
					pnRightOfBottomOfRight.add(lblAnhSP, BorderLayout.CENTER);
				}

			}
		});
		/*btnReloadDDH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				hienThiToanBoDonDatHangLenCombobox();
			}
		});*/

		//events Tab2
		tblKhachHang.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseClicked(MouseEvent e) {
				int row = tblKhachHang.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					KhachHangModel kh = dsKhachHang.get(row);
					txtTenKH.setText(kh.getTenKH());
					txtDiaChi.setText(kh.getDiaChi());
					txtSDT.setText(kh.getSDT());
					txtEmail.setText(kh.getEmail());
				}
			}
		});
		btnThemKH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemKhachHang();
			}
		});
		btnSuaKH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaKhachHang();
			}
		});
		btnXoaKH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaKhachHang();
			}
		});
		btnTimKiemKhachHangTheoTen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemKhachHangTheoTen();
			}
		});
		btnTimKiemKhachHangTheoSDT.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemKhachHangTheoSDT();
			}
		});
	}
	protected void xuLyTimKiemKhachHangTheoSDT() {
		if(txtTimKiemKhachHangTheoSDT.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại tìm kiếm!");
			return;
		}
		else{
			KhachHangService khService = new KhachHangService();
			Vector<KhachHangModel>dskh = khService.timKhachHangTheoSDT(txtTimKiemKhachHangTheoSDT.getText());
			dtmKhachHang.setRowCount(0);
			for(KhachHangModel kh : dskh)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(kh.getMaKH());
				vec.add(kh.getTenKH());
				vec.add(kh.getDiaChi());
				vec.add(kh.getSDT());
				vec.add(kh.getEmail());
				dtmKhachHang.addRow(vec);
			}
		}
	}
	protected void xuLyTimKiemCTDDH() {
		if(txtTimKiemCTDDH.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm!");
			return;
		}
		else{
			ChiTietDonDatHangService ctddhService = new ChiTietDonDatHangService();
			Vector<HienThiToanBoChiTietDonDatHangModel>dsctddh = ctddhService.timKiemCTDDHTheoTenSP(txtTimKiemCTDDH.getText());
			dtmChiTietDDH.setRowCount(0);
			for(HienThiToanBoChiTietDonDatHangModel ctddh : dsctddh)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(ctddh.getMaCTDDH());
				vec.add(ctddh.getTenSP());
				vec.add(ctddh.getSoLuong());
				vec.add(ctddh.getThanhTien());
				vec.add(ctddh.getUuDai());
				vec.add(ctddh.getTongThanhToan());
				dtmChiTietDDH.addRow(vec);
			}
		}
	}
	protected void xuLyTimKiemDonDatHang() {
		if(txtTimKiemDDH.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm!");
			return;
		}
		else{
			DonDatHangService ddhService = new DonDatHangService();
			Vector<HienThiToanBoDonDatHangModel>dsddh = ddhService.timKiemDonDatHangTheoTenKH(txtTimKiemDDH.getText());
			dtmDonDatHang.setRowCount(0);
			for(HienThiToanBoDonDatHangModel ddh : dsddh)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(ddh.getMaDDH());
				vec.add(ddh.getTenKH());
				vec.add(ddh.getNgayDat());
				vec.add(ddh.getNgayGiao());
				dtmDonDatHang.addRow(vec);
			}
		}
	}
	protected void xuLyXoaCTDDH() {
		int rowSelected = tblChiTietDDH.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng cần xoá!");
			return;
		}
		else{
			int ctddhSelected = Integer.parseInt(tblChiTietDDH.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					ChiTietDonDatHangService ctddhService = new ChiTietDonDatHangService();
					if(ctddhService.xoaChiTietDonDatHang(ctddhSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá thành công!");
						txtThanhTien.setText("");
						txtUuDai.setText("");
						txtTongThanhToan.setText("");
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaCTDDH() {
		BoPhanQuanLyKhoUI ui = new BoPhanQuanLyKhoUI("");
		int rowSelected = tblChiTietDDH.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng cần sửa!");
			return;
		}
		else {
			int ctddhSelected = Integer.parseInt(tblChiTietDDH.getValueAt(rowSelected, 0)+"");
			try {
				ChiTietDonDatHangService ctddhService = new ChiTietDonDatHangService();
				if(ctddhService.suaChiTietDonDatHang(ctddhSelected, ui.layMaSanPhamTheoTen(cboSanPham.getSelectedIndex()),
						/*Integer.parseInt(cboSoLuong.getSelectedItem()+"")*/ Integer.parseInt(txtSoLuong.getText()), Integer.parseInt(txtThanhTien.getText()), 
						Integer.parseInt(txtUuDai.getText()), Integer.parseInt(txtTongThanhToan.getText())) > 0){
					JOptionPane.showMessageDialog(null, "Sửa thông tin thành công!");
					txtThanhTien.setText("");
					txtUuDai.setText("");
					txtTongThanhToan.setText("");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	protected void xuLyThemCTDDH() {
		BoPhanQuanLyKhoUI ui = new BoPhanQuanLyKhoUI("");
		ChiTietDonDatHangModel ctddh = new ChiTietDonDatHangModel();
		ctddh.setMaDDH(Integer.parseInt(cboDonDatHang.getSelectedItem()+""));
		ctddh.setMaSP(ui.layMaSanPhamTheoTen(cboSanPham.getSelectedIndex()));
		ctddh.setSoLuong(/*Integer.parseInt(cboSoLuong.getSelectedItem()+""*/ Integer.parseInt(txtSoLuong.getText()));
		try {
			ctddh.setThanhTien(Integer.parseInt(txtThanhTien.getText()));
			ctddh.setUuDai(Integer.parseInt(txtUuDai.getText()));
			ctddh.setTongThanhToan(Integer.parseInt(txtTongThanhToan.getText()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ChiTietDonDatHangService ctddhService = new ChiTietDonDatHangService();
		if(ctddhService.themChiTietDonDatHang(ctddh) > 0)
		{
			JOptionPane.showMessageDialog(null, "Thêm thành công!");
			txtThanhTien.setText("");
			txtUuDai.setText("");
			txtTongThanhToan.setText("");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Thêm thất bại!");
		}
	}
	public int tinhUuDai(){
		BanQuanLyUI ui = new BanQuanLyUI("");
		int uuDai = ui.layUuDaiTheoTienThanhToan(Integer.parseInt(txtThanhTien.getText()));
		return Integer.parseInt(txtThanhTien.getText())*uuDai/100;
	}
	protected void xuLyXoaDonDatHang() {
		int rowSelected = tblDonDatHang.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn đơn đặt hàng cần xoá!");
			return;
		}
		else{
			int ddhSelected = Integer.parseInt(tblDonDatHang.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					DonDatHangService ddhService = new DonDatHangService();
					if(ddhService.xoaDonDatHang(ddhSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá đơn đặt hàng thành công!");
						hienThiToanBoDonDatHang();
						hienThiToanBoDonDatHangLenCombobox();
						//dcNgayDat.setText("");
						//dcNgayGiao.setText("");
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaDonDatHang() {
		int rowSelected = tblDonDatHang.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn đơn đặt hàng cần sửa!");
			return;
		}
		else {
			int ddhSelected = Integer.parseInt(tblDonDatHang.getValueAt(rowSelected, 0)+"");
			try {
				DonDatHangService ddhService = new DonDatHangService();
				if(ddhService.suaDonDatHang(ddhSelected, sdf.format(dcNgayDat.getDate()), sdf.format(dcNgayGiao.getDate()),
						Integer.parseInt(tblKhachHang.getValueAt(cboKhachHang.getSelectedIndex(), 0)+"")) > 0){
					JOptionPane.showMessageDialog(null, "Sửa thông tin đơn đặt hàng thành công!");
					hienThiToanBoDonDatHang();
					hienThiToanBoDonDatHangLenCombobox();
					//dcNgayDat.setText("");
					//dcNgayGiao.setText("");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	protected void xuLyThemDonDatHang() {
		DonDatHangModel ddh = new DonDatHangModel();
		DonDatHangService ddhService = new DonDatHangService();
		ddh.setNgayDat(sdf.format(dcNgayDat.getDate()));
		ddh.setNgayGiao(sdf.format(dcNgayGiao.getDate()));
		ddh.setMaKH(Integer.parseInt(tblKhachHang.getValueAt(cboKhachHang.getSelectedIndex(), 0)+""));
		if(ddhService.themDonDatHang(ddh) > 0)
		{
			JOptionPane.showMessageDialog(null, "Thêm đơn đặt hàng thành công!");
			hienThiToanBoDonDatHang();
			hienThiToanBoDonDatHangLenCombobox();
			//dcNgayDat.setText("");
			//dcNgayGiao.setText("");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Thêm đơn đặt hàng thất bại!");
		}
	}


	protected void xuLyTimKiemKhachHangTheoTen() {
		if(txtTimKiemKhachHangTheoTen.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng tìm kiếm!");
			return;
		}
		else{
			KhachHangService khService = new KhachHangService();
			Vector<KhachHangModel>dskh = khService.timKhachHangTheoTen(txtTimKiemKhachHangTheoTen.getText());
			dtmKhachHang.setRowCount(0);
			for(KhachHangModel kh : dskh)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(kh.getMaKH());
				vec.add(kh.getTenKH());
				vec.add(kh.getDiaChi());
				vec.add(kh.getSDT());
				vec.add(kh.getEmail());
				dtmKhachHang.addRow(vec);
			}
		}
		
	}
	protected void xuLyXoaKhachHang() {
		int rowSelected = tblKhachHang.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng cần xoá!");
			return;
		}
		else{
			int khSelected = Integer.parseInt(tblKhachHang.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá khách hàng "+txtTenKH.getText()+" không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					KhachHangService khService = new KhachHangService();
					if(khService.xoaKhachHang(khSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá khách hàng thành công!");
						txtTenKH.setText("");
						txtDiaChi.setText("");
						txtSDT.setText("");
						txtEmail.setText("");
						txtTenKH.requestFocus();
						hienThiToanBoKhachHang();
						hienThiKhachHangLenCombobox();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaKhachHang() {
		int rowSelected = tblKhachHang.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng cần sửa!");
			return;
		}
		else {
			if(txtTenKH.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Không được để trống tên khách hàng!");
				return;
			}
			else{
				if(txtDiaChi.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Không được để trống địa chỉ!");
					return;
				}
				else{
					if(txtSDT.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Không được để trống số điện thoại!");
						return;
					}
					else{
						if(!isPhone(txtSDT.getText())){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số điện thoại!");
							return;
						}
						else{
							if(txtEmail.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Không được để trống email!");
								return;
							}
							else{
								if(!isEmail(txtEmail.getText())){
									JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng email!");
									return;
								}
								else{
									int khSelected = Integer.parseInt(tblKhachHang.getValueAt(rowSelected, 0)+"");
									try {
										KhachHangService khService = new KhachHangService();
										if(khService.suaKhachHang(khSelected, txtTenKH.getText(), txtDiaChi.getText(), 
												txtSDT.getText(), txtEmail.getText()) > 0){
											JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thành công!");
											txtTenKH.setText("");
											txtDiaChi.setText("");
											txtSDT.setText("");
											txtEmail.setText("");
											txtTenKH.requestFocus();
											hienThiToanBoKhachHang();
											hienThiKhachHangLenCombobox();
										}
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
	}
	protected void xuLyThemKhachHang() {
		if(txtTenKH.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng!");
			return;
		}
		else{
			if(txtDiaChi.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ khách hàng!");
				return;
			}
			else{
				if(txtSDT.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!");
					return;
				}
				else{
					if(txtEmail.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Vui lòng nhập email!");
						return;
					}
					else{
						KhachHangModel kh = new KhachHangModel();
						kh.setTenKH(txtTenKH.getText());
						kh.setDiaChi(txtDiaChi.getText());
						if(!isPhone(txtSDT.getText())){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số điện thoại!");
							return;
						}
						else{
							kh.setSDT(txtSDT.getText());
						}
						if(!isEmail(txtEmail.getText())){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng email!");
							return;
						}
						else{
							kh.setEmail(txtEmail.getText());
						}	
						KhachHangService khService = new KhachHangService();
						if(khService.themKhachHang(kh) > 0)
						{
							JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
							txtTenKH.setText("");
							txtDiaChi.setText("");
							txtSDT.setText("");
							txtEmail.setText("");
							txtTenKH.requestFocus();
							hienThiToanBoKhachHang();
							hienThiKhachHangLenCombobox();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
						}
					}
				}
			}
		}
	}
	public boolean isPhone(String  inputPhone){
		String regex = "^0[2-9]{1}\\d{8}$";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(inputPhone);
		if(mat.find()){
			return true;
		}
		return false;
	}
	public boolean isEmail(String inputEmail)
	{
		String regex = "^[a-zA-Z]+[a-zA-Z0-9]*@{1}[a-zA-Z]+mail.com$";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(inputEmail);
		if(mat.find()){
			return true;
		}
		return false;
	}
	public void showWindows(){
		this.setSize(1650, 900);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
