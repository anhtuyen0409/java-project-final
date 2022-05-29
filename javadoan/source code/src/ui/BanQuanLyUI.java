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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import service.KhuyenMaiService;
import service.LoaiNhanVienService;
import service.LoaiTaiKhoanService;
import service.NhanVienService;
import service.SanPhamService;
import service.TaiKhoanService;
import service.ThongKeDoanhThuTheoNgayService;
import service.ThongKeDoanhThuTheoThangService;
import model.HienThiToanBoNhanVienModel;
import model.KhuyenMaiModel;
import model.LoaiNhanVienModel;
import model.LoaiSanPhamModel;
import model.LoaiTaiKhoanModel;
import model.NhanVienModel;
import model.SanPhamModel;
import model.TaiKhoanModel;
import model.ThongKeDoanhThuTheoNgayModel;
import model.ThongKeDoanhThuTheoThangModel;

public class BanQuanLyUI extends JFrame{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	JMenuBar mnuBar;//nơi để đựng (chứa) các JMenu
	JMenu mnuHeThong;
	JMenuItem mnuThongTin;
	JMenuItem mnuDangXuat;
	JMenuItem mnuThoat;
	JToolBar tooBar;
	JButton btnThongTin,btnDangXuat, btnThoat, btnTroVe;
	JPanel pnTabNV, pnTabThongKe, pnTabKhuyenMai;
	JTabbedPane tab;

	//controls TabNV
	JTextField txtTimKiemNhanVien, txtTenNV, txtDiaChi, txtSDT, txtEmail;
	JButton btnTimKiemNhanVien, btnThemTaiKhoan, btnThemNV, btnSuaNV, btnXoaNV, btnReload;
	JComboBox<LoaiNhanVienModel>cboLoaiNhanVien;
	JComboBox<TaiKhoanModel>cboTenTaiKhoan;
	DefaultTableModel dtmNhanVien;
	Vector<HienThiToanBoNhanVienModel>dsNhanVien;
	JTable tblNhanVien;
	JCheckBox chkHienThiToanBoNhanVien;
	JDateChooser dcNamSinh, dcNgayVaoLamViec;

	//controls Tab Thống kê
	JCheckBox chkThongKeTheoNgay, chkThongKeTheoThang;
	DefaultTableModel dtmThongKeTheoNgay, dtmThongKeTheoThang;
	JTable tblThongKeTheoNgay, tblThongKeTheoThang;
	//JComboBox<Integer> cboNam;
	JTextField txtNam;
	Vector<ThongKeDoanhThuTheoNgayModel> dsThongKeTheoNgay;
	Vector<ThongKeDoanhThuTheoThangModel> dsThongKeTheoThang;

	//controls TabKhuyenMai
	JTextField txtTienToiThieu, txtTienToiDa, txtUuDai;
	JButton btnThemKM, btnSuaKM, btnXoaKM;
	DefaultTableModel dtmKhuyenMai;
	JTable tblKhuyenMai;
	Vector<KhuyenMaiModel>dsKM;
	public BanQuanLyUI(String title){
		super(title);
		addControls();
		addEvents();
		hienThiToanBoTaiKhoanTenCombobox();
		hienThiToanBoLoaiNhanVienTrenCombobox();
		hienThiToanBoNhanVien();
		hienThiToanBoKhuyenMai();
	}
	private void hienThiToanBoKhuyenMai() {
		KhuyenMaiService kmService = new KhuyenMaiService();
		dsKM = kmService.docToanBoKhuyenMai();
		dtmKhuyenMai.setRowCount(0);
		for(KhuyenMaiModel km : dsKM)
		{
			Vector<Object>vec = new Vector<Object>();
			vec.add(km.getMaKM());
			vec.add(km.getTienToiThieu());
			vec.add(km.getTienToiDa());
			vec.add(km.getUuDai());
			dtmKhuyenMai.addRow(vec);
		}
	}
	public int layMaNhanVienTheoTen(int index){
		return Integer.parseInt(tblNhanVien.getValueAt(index, 0)+"");
	}
	public int layUuDaiTheoTienThanhToan(int tienThanhToan){
		int uuDai=0;
		for(int i=0; i<tblKhuyenMai.getRowCount(); i++){
			if(tienThanhToan>Integer.parseInt(tblKhuyenMai.getValueAt(i, 1)+"") && tienThanhToan<Integer.parseInt(tblKhuyenMai.getValueAt(i, 2)+"")){
				uuDai = Integer.parseInt(tblKhuyenMai.getValueAt(i, 3)+"");
			}
		}
		return uuDai;
	}
	private void hienThiToanBoNhanVien() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		NhanVienService nvService = new NhanVienService();
		dsNhanVien = nvService.docToanBoNhanVien();
		dtmNhanVien.setRowCount(0);
		for(HienThiToanBoNhanVienModel nv : dsNhanVien)
		{
			Vector<Object>vec = new Vector<Object>();
			vec.add(nv.getMaNV());
			vec.add(nv.getTenNV());
			vec.add(nv.getNamSinh());
			vec.add(nv.getDiaChi());
			vec.add(nv.getSDT());
			vec.add(nv.getEmail());
			vec.add(nv.getNgayVaoLamViec());
			vec.add(nv.getTenBoPhan());
			vec.add(nv.getTaiKhoan());
			dtmNhanVien.addRow(vec);
		}
	}
	private void hienThiToanBoLoaiNhanVienTrenCombobox() {
		LoaiNhanVienService lnvService = new LoaiNhanVienService();
		Vector<LoaiNhanVienModel>vec = lnvService.docToanBoLoaiNhanVien();
		cboLoaiNhanVien.removeAllItems();
		for(LoaiNhanVienModel lnv : vec)
		{
			cboLoaiNhanVien.addItem(lnv);
		}

	}
	private void hienThiToanBoTaiKhoanTenCombobox() {
		TaiKhoanService tkService = new TaiKhoanService();
		Vector<TaiKhoanModel>vec = tkService.docToanBoTaiKhoan();
		cboTenTaiKhoan.removeAllItems();
		for(TaiKhoanModel tk : vec)
		{
			cboTenTaiKhoan.addItem(tk);
		}
	}
	public void addControls(){
		mnuBar = new JMenuBar();
		setJMenuBar(mnuBar);
		mnuHeThong = new JMenu("Hệ Thống");
		mnuBar.add(mnuHeThong);
		mnuThongTin = new JMenuItem("Thông Tin");
		mnuThongTin.setIcon(new ImageIcon("images/info3.png"));
		mnuHeThong.add(mnuThongTin);
		mnuHeThong.addSeparator();
		mnuDangXuat = new JMenuItem("Đăng Xuất");
		mnuDangXuat.setIcon(new ImageIcon("images/logout4.png"));
		mnuHeThong.add(mnuDangXuat);
		mnuHeThong.addSeparator();
		mnuThoat = new JMenuItem("Thoát");
		mnuThoat.setIcon(new ImageIcon("images/exit6.png"));
		mnuHeThong.add(mnuThoat);
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		tooBar = new JToolBar();
		tooBar.setBackground(Color.LIGHT_GRAY);
		btnThongTin = new JButton("Thông Tin");
		btnThongTin.setBackground(Color.WHITE);
		btnThongTin.setIcon(new ImageIcon("images/info4.png"));
		btnThongTin.setFont(new Font("", Font.BOLD, 12));
		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.setBackground(Color.WHITE);
		btnDangXuat.setIcon(new ImageIcon("images/logout5.png"));
		btnDangXuat.setFont(new Font("", Font.BOLD, 12));
		btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.WHITE);
		btnThoat.setIcon(new ImageIcon("images/exit7.png"));
		btnThoat.setFont(new Font("", Font.BOLD, 12));
		btnTroVe = new JButton("Trở Về");
		btnTroVe.setFont(new Font("", Font.BOLD, 12));
		btnTroVe.setBackground(Color.WHITE);
		btnTroVe.setIcon(new ImageIcon("images/return2.png"));
		tooBar.add(btnTroVe);
		tooBar.add(btnThongTin);
		tooBar.add(btnDangXuat);
		tooBar.add(btnThoat);
		con.add(tooBar,BorderLayout.NORTH);
		tab = new JTabbedPane();
		con.add(tab);
		pnTabNV = new JPanel();
		tab.add(pnTabNV,"Quản lý nhân viên");
		pnTabThongKe = new JPanel();
		tab.add(pnTabThongKe,"Thống kê doanh thu");
		pnTabKhuyenMai = new JPanel();
		tab.add(pnTabKhuyenMai,"Khuyến mãi");

		//giao diện TabNV
		pnTabNV.setLayout(new BorderLayout());
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.Y_AXIS));
		pnTabNV.add(pnTop, BorderLayout.NORTH);

		JPanel pnTimKiemNhanVien = new JPanel();
		pnTimKiemNhanVien.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTimKiemNhanVien = new JLabel("Nhập dữ liệu tìm kiếm:   ");
		lblTimKiemNhanVien.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemNhanVien = new JTextField(30);
		btnTimKiemNhanVien = new JButton("Tìm kiếm");
		btnTimKiemNhanVien.setIcon(new ImageIcon("images/search9.png"));
		pnTimKiemNhanVien.add(lblTimKiemNhanVien);
		pnTimKiemNhanVien.add(txtTimKiemNhanVien);
		pnTimKiemNhanVien.add(btnTimKiemNhanVien);
		pnTop.add(pnTimKiemNhanVien);

		JPanel pnComboLoaiNV = new JPanel();
		pnComboLoaiNV.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblComboLoaiNV = new JLabel("Bộ phận:   ");
		lblComboLoaiNV.setFont(new Font("", Font.PLAIN, 15));
		cboLoaiNhanVien = new JComboBox<LoaiNhanVienModel>();
		cboLoaiNhanVien.setPreferredSize(new Dimension(445, 20));
		pnComboLoaiNV.add(lblComboLoaiNV);
		pnComboLoaiNV.add(cboLoaiNhanVien);
		pnTop.add(pnComboLoaiNV);

		JPanel pnTenNV = new JPanel();
		pnTenNV.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTenNV = new JLabel("Tên nhân viên:   ");
		lblTenNV.setFont(new Font("", Font.PLAIN, 15));
		txtTenNV = new JTextField(40);
		pnTenNV.add(lblTenNV);
		pnTenNV.add(txtTenNV);
		pnTop.add(pnTenNV);

		JPanel pnNamSinh = new JPanel();
		pnNamSinh.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblNamSinh = new JLabel("Năm sinh:   ");
		lblNamSinh.setFont(new Font("", Font.PLAIN, 15));
		dcNamSinh = new JDateChooser();
		dcNamSinh.setPreferredSize(new Dimension(445, 20));
		pnNamSinh.add(lblNamSinh);
		pnNamSinh.add(dcNamSinh);
		pnTop.add(pnNamSinh);

		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblDiaChi = new JLabel("Địa chỉ:   ");
		lblDiaChi.setFont(new Font("", Font.PLAIN, 15));
		txtDiaChi = new JTextField(40);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnTop.add(pnDiaChi);

		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblSDT = new JLabel("Số điện thoại:   ");
		lblSDT.setFont(new Font("", Font.PLAIN, 15));
		txtSDT = new JTextField(40);
		pnSDT.add(lblSDT);
		pnSDT.add(txtSDT);
		pnTop.add(pnSDT);

		JPanel pnEmail = new JPanel();
		pnEmail.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblEmail = new JLabel("Email:   ");
		lblEmail.setFont(new Font("", Font.PLAIN, 15));
		txtEmail = new JTextField(40);
		pnEmail.add(lblEmail);
		pnEmail.add(txtEmail);
		pnTop.add(pnEmail);

		JPanel pnNgayVaoLamViec = new JPanel();
		pnNgayVaoLamViec.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblNgayVaoLamViec = new JLabel("Ngày vào làm việc:   ");
		lblNgayVaoLamViec.setFont(new Font("", Font.PLAIN, 15));
		dcNgayVaoLamViec = new JDateChooser();
		dcNgayVaoLamViec.setPreferredSize(new Dimension(445, 20));
		pnNgayVaoLamViec.add(lblNgayVaoLamViec);
		pnNgayVaoLamViec.add(dcNgayVaoLamViec);
		pnTop.add(pnNgayVaoLamViec);

		JPanel pnComboTenTK = new JPanel();
		pnComboTenTK.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblComboTenTK = new JLabel("Tên tài khoản:   ");
		lblComboTenTK.setFont(new Font("", Font.PLAIN, 15));
		cboTenTaiKhoan = new JComboBox<TaiKhoanModel>();
		cboTenTaiKhoan.setPreferredSize(new Dimension(230, 20));
		btnThemTaiKhoan = new JButton("Thêm tài khoản");
		btnThemTaiKhoan.setIcon(new ImageIcon("images/them1.png"));
		btnReload = new JButton();
		btnReload.setIcon(new ImageIcon("images/reload2.png"));
		btnReload.setBackground(Color.WHITE);
		pnComboTenTK.add(lblComboTenTK);
		pnComboTenTK.add(cboTenTaiKhoan);
		pnComboTenTK.add(btnReload);
		pnComboTenTK.add(btnThemTaiKhoan);
		pnTop.add(pnComboTenTK);

		JPanel pnHienThiToanBoNhanVien = new JPanel();
		pnHienThiToanBoNhanVien.setLayout(new FlowLayout(FlowLayout.CENTER));
		chkHienThiToanBoNhanVien = new JCheckBox("Hiển thị toàn bộ nhân viên");
		chkHienThiToanBoNhanVien.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBoNhanVien.setForeground(Color.BLUE);
		pnHienThiToanBoNhanVien.add(chkHienThiToanBoNhanVien);
		pnTop.add(pnHienThiToanBoNhanVien);

		//canh chỉnh
		lblTenNV.setPreferredSize(lblTimKiemNhanVien.getPreferredSize());
		lblNamSinh.setPreferredSize(lblTimKiemNhanVien.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTimKiemNhanVien.getPreferredSize());
		lblSDT.setPreferredSize(lblTimKiemNhanVien.getPreferredSize());
		lblEmail.setPreferredSize(lblTimKiemNhanVien.getPreferredSize());
		lblNgayVaoLamViec.setPreferredSize(lblTimKiemNhanVien.getPreferredSize());
		lblComboLoaiNV.setPreferredSize(lblTimKiemNhanVien.getPreferredSize());
		lblComboTenTK.setPreferredSize(lblTimKiemNhanVien.getPreferredSize());
		chkHienThiToanBoNhanVien.setPreferredSize(txtTimKiemNhanVien.getPreferredSize());

		JPanel pnControlsOfNV = new JPanel();
		pnControlsOfNV.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThemNV = new JButton("Thêm mới");
		btnThemNV.setIcon(new ImageIcon("images/new8.png"));
		btnSuaNV = new JButton("Sửa thông tin");
		btnSuaNV.setIcon(new ImageIcon("images/edit8.png"));
		btnXoaNV = new JButton("Xoá");
		btnXoaNV.setIcon(new ImageIcon("images/remove8.png"));
		pnControlsOfNV.add(btnThemNV);
		pnControlsOfNV.add(btnSuaNV);
		pnControlsOfNV.add(btnXoaNV);
		pnTop.add(pnControlsOfNV);


		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BorderLayout());
		pnTabNV.add(pnBottom, BorderLayout.CENTER);

		TitledBorder borderNV = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED),
				"Thông tin nhân viên");
		borderNV.setTitleColor(Color.BLUE);
		borderNV.setTitleFont(new Font("", Font.BOLD, 15));
		pnBottom.setBorder(borderNV);

		dtmNhanVien = new DefaultTableModel();
		dtmNhanVien.addColumn("Mã nhân viên");
		dtmNhanVien.addColumn("Tên nhân viên");
		dtmNhanVien.addColumn("Năm sinh");
		dtmNhanVien.addColumn("Địa chỉ");
		dtmNhanVien.addColumn("Số điện thoai");
		dtmNhanVien.addColumn("Email");
		dtmNhanVien.addColumn("Ngày vào làm việc");
		dtmNhanVien.addColumn("Bộ phân");
		dtmNhanVien.addColumn("Tài khoản");
		tblNhanVien = new JTable(dtmNhanVien);
		tblNhanVien.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableNV = new JScrollPane(tblNhanVien,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottom.add(scTableNV,BorderLayout.CENTER);


		//giao diện Tab thống kê
		//giao diện pnLeft
		pnTabThongKe.setLayout(new BorderLayout());
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(700, 0));
		JPanel pnRight = new JPanel();
		JSplitPane sp = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, pnLeft,pnRight);
		sp.setOneTouchExpandable(true);
		pnTabThongKe.add(sp,BorderLayout.CENTER);

		pnLeft.setLayout(new BorderLayout());
		JPanel pnTopOfLeft = new JPanel();
		pnTopOfLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkThongKeTheoNgay = new JCheckBox("Thống kê doanh thu theo ngày");
		chkThongKeTheoNgay.setFont(new Font("", Font.ITALIC, 20));
		chkThongKeTheoNgay.setForeground(Color.BLUE);
		pnTopOfLeft.add(chkThongKeTheoNgay);
		pnLeft.add(pnTopOfLeft, BorderLayout.NORTH);

		JPanel pnBottomOfLeft = new JPanel();
		pnBottomOfLeft.setLayout(new BorderLayout());
		TitledBorder borderThongKeTheoNgay = new TitledBorder
				(BorderFactory.createLineBorder(Color.RED),"Thông tin chi tiết");
		borderThongKeTheoNgay.setTitleColor(Color.BLUE);
		borderThongKeTheoNgay.setTitleFont(new Font("", Font.BOLD, 15));
		pnBottomOfLeft.setBorder(borderThongKeTheoNgay);
		pnLeft.add(pnBottomOfLeft,BorderLayout.CENTER);
		//pnBottomOfLeft.setPreferredSize(new Dimension(0, 300));

		dtmThongKeTheoNgay = new DefaultTableModel();
		dtmThongKeTheoNgay.addColumn("Ngày");
		dtmThongKeTheoNgay.addColumn("Tổng số tiền thu được");
		tblThongKeTheoNgay = new JTable(dtmThongKeTheoNgay);
		tblThongKeTheoNgay.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableThongKeTheoNgay = new JScrollPane
				(tblThongKeTheoNgay,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottomOfLeft.add(scTableThongKeTheoNgay,BorderLayout.CENTER);

		//xử lý giao diện pnRight
		pnRight.setLayout(new BorderLayout());
		JPanel pnTopOfRight = new JPanel();
		pnTopOfRight.setLayout(new BoxLayout(pnTopOfRight, BoxLayout.Y_AXIS));
		pnRight.add(pnTopOfRight, BorderLayout.NORTH);

		JPanel pnNam = new JPanel();
		pnNam.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNam = new JLabel("Năm: ");
		lblNam.setForeground(Color.RED);
		lblNam.setFont(new Font("", Font.ITALIC, 15));
		//cboNam = new JComboBox<Integer>();
		//cboNam.setPreferredSize(new Dimension(200, 20));
		//Vector<Integer>vec = new Vector<Integer>();
		//cboNam.removeAllItems();
		//for(int i=2022; i<2030; i++){
			//vec.add(i);
		//}
		//for(Integer x : vec)
		//{
			//cboNam.addItem(x);
		//}
		txtNam = new JTextField(20);
		pnNam.add(lblNam);
		//pnNam.add(cboNam);
		pnNam.add(txtNam);
		pnTopOfRight.add(pnNam);

		JPanel pnThongKeTheoThang = new JPanel();
		pnThongKeTheoThang.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkThongKeTheoThang = new JCheckBox("Thống kê doanh thu theo tháng");
		chkThongKeTheoThang.setFont(new Font("", Font.ITALIC, 20));
		chkThongKeTheoThang.setForeground(Color.BLUE);
		pnThongKeTheoThang.add(chkThongKeTheoThang);
		pnTopOfRight.add(pnThongKeTheoThang);

		JPanel pnBottomOfRight = new JPanel();
		pnBottomOfRight.setLayout(new BorderLayout());
		TitledBorder borderThongKeTheoThang = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED),
				"Thông tin chi tiết");
		borderThongKeTheoThang.setTitleColor(Color.BLUE);
		borderThongKeTheoThang.setTitleFont(new Font("", Font.BOLD, 15));
		pnBottomOfRight.setBorder(borderThongKeTheoThang);
		pnRight.add(pnBottomOfRight,BorderLayout.CENTER);
		dtmThongKeTheoThang = new DefaultTableModel();
		dtmThongKeTheoThang.addColumn("Tháng");
		dtmThongKeTheoThang.addColumn("Tổng số tiền thu được");
		tblThongKeTheoThang = new JTable(dtmThongKeTheoThang);
		tblThongKeTheoThang.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableThongKeTheoThang = new JScrollPane(
				tblThongKeTheoThang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottomOfRight.add(scTableThongKeTheoThang,BorderLayout.CENTER);

		//giao diện TabKhuyenMai
		pnTabKhuyenMai.setLayout(new BorderLayout());
		JPanel pnTop2 = new JPanel();
		pnTop2.setLayout(new BoxLayout(pnTop2, BoxLayout.Y_AXIS));
		pnTabKhuyenMai.add(pnTop2, BorderLayout.NORTH);

		JPanel pnTienToiThieu = new JPanel();
		pnTienToiThieu.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTienToiThieu = new JLabel("Số tiền tối thiểu:   ");
		lblTienToiThieu.setFont(new Font("", Font.PLAIN, 15));
		txtTienToiThieu = new JTextField(40);
		pnTienToiThieu.add(lblTienToiThieu);
		pnTienToiThieu.add(txtTienToiThieu);
		pnTop2.add(pnTienToiThieu);

		JPanel pnTienToiDa = new JPanel();
		pnTienToiDa.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTienToiDa = new JLabel("Số tiền tối đa:   ");
		lblTienToiDa.setFont(new Font("", Font.PLAIN, 15));
		txtTienToiDa = new JTextField(40);
		pnTienToiDa.add(lblTienToiDa);
		pnTienToiDa.add(txtTienToiDa);
		pnTop2.add(pnTienToiDa);

		JPanel pnUuDai = new JPanel();
		pnUuDai.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblUuDai = new JLabel("Ưu đãi (%):   ");
		lblUuDai.setFont(new Font("", Font.PLAIN, 15));
		txtUuDai = new JTextField(40);
		pnUuDai.add(lblUuDai);
		pnUuDai.add(txtUuDai);
		pnTop2.add(pnUuDai);

		lblTienToiDa.setPreferredSize(lblTienToiThieu.getPreferredSize());
		lblUuDai.setPreferredSize(lblTienToiThieu.getPreferredSize());


		JPanel pnControlsOfKhuyenMai = new JPanel();
		pnControlsOfKhuyenMai.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThemKM = new JButton("Thêm mới");
		btnThemKM.setIcon(new ImageIcon("images/new10.png"));
		btnSuaKM = new JButton("Sửa thông tin");
		btnSuaKM.setIcon(new ImageIcon("images/edit10.png"));
		btnXoaKM = new JButton("Xoá");
		btnXoaKM.setIcon(new ImageIcon("images/remove10.png"));
		pnControlsOfKhuyenMai.add(btnThemKM);
		pnControlsOfKhuyenMai.add(btnSuaKM);
		pnControlsOfKhuyenMai.add(btnXoaKM);
		pnTop2.add(pnControlsOfKhuyenMai);


		JPanel pnBottomOfKM = new JPanel();
		pnBottomOfKM.setLayout(new BorderLayout());
		pnTabKhuyenMai.add(pnBottomOfKM, BorderLayout.CENTER);

		TitledBorder borderKM = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED),
				"Thông tin khuyến mãi");
		borderKM.setTitleColor(Color.BLUE);
		borderKM.setTitleFont(new Font("", Font.BOLD, 15));
		pnBottomOfKM.setBorder(borderKM);

		dtmKhuyenMai = new DefaultTableModel();
		dtmKhuyenMai.addColumn("Mã khuyến mãi");
		dtmKhuyenMai.addColumn("Số tiền tối thiểu");
		dtmKhuyenMai.addColumn("Số tiền tối đa");
		dtmKhuyenMai.addColumn("Ưu đãi (%)");
		tblKhuyenMai = new JTable(dtmKhuyenMai);
		tblKhuyenMai.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableKM = new JScrollPane(tblKhuyenMai,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnBottomOfKM.add(scTableKM,BorderLayout.CENTER);
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
		btnTroVe.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				DanhSachChucNangUI ui = new DanhSachChucNangUI("Hệ thống cửa hàng điện thoại");
				ui.showWindows();
			}
		});
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
		btnThemTaiKhoan.addActionListener(new  ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ThemTaiKhoanUI ui = new ThemTaiKhoanUI("Thêm tài khoản");
				ui.showWindows();
			}
		});
		btnThemNV.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemNhanVien();
			}
		});
		cboLoaiNhanVien.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(cboLoaiNhanVien.getSelectedIndex() == -1){
					return;
				}
				else{
					NhanVienService nvService = new NhanVienService();
					dsNhanVien = nvService.docNhanVienTheoLoai(cboLoaiNhanVien.getSelectedIndex()==0?1:2);
					dtmNhanVien.setRowCount(0);
					for(HienThiToanBoNhanVienModel nv : dsNhanVien)
					{
						Vector<Object>vec = new Vector<Object>();
						vec.add(nv.getMaNV());
						vec.add(nv.getTenNV());
						vec.add(nv.getNamSinh());
						vec.add(nv.getDiaChi());
						vec.add(nv.getSDT());
						vec.add(nv.getEmail());
						vec.add(nv.getNgayVaoLamViec());
						vec.add(nv.getTenBoPhan());
						vec.add(nv.getTaiKhoan());
						dtmNhanVien.addRow(vec);
					}
				}	
			}
		});
		cboTenTaiKhoan.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(cboTenTaiKhoan.getSelectedIndex() == -1){
					return;
				}
				else{
					NhanVienService nvService = new NhanVienService();
					dsNhanVien = nvService.docNhanVienTheoTenDangNhap(cboTenTaiKhoan.getSelectedItem()+"");
					dtmNhanVien.setRowCount(0);
					for(HienThiToanBoNhanVienModel nv : dsNhanVien)
					{
						Vector<Object>vec = new Vector<Object>();
						vec.add(nv.getMaNV());
						vec.add(nv.getTenNV());
						vec.add(nv.getNamSinh());
						vec.add(nv.getDiaChi());
						vec.add(nv.getSDT());
						vec.add(nv.getEmail());
						vec.add(nv.getNgayVaoLamViec());
						vec.add(nv.getTenBoPhan());
						vec.add(nv.getTaiKhoan());
						dtmNhanVien.addRow(vec);
					}
				}	
			}
		});
		tblNhanVien.addMouseListener(new MouseListener() {

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

				int row = tblNhanVien.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					HienThiToanBoNhanVienModel nv = dsNhanVien.get(row);
					txtTenNV.setText(nv.getTenNV());
					//dcNamSinh.setText(nv.getNamSinh());
					txtDiaChi.setText(nv.getDiaChi());
					txtSDT.setText(nv.getSDT());
					txtEmail.setText(nv.getEmail());
					//dcNgayVaoLamViec.setText(nv.getNgayVaoLamViec());
				}
			}
		});
		btnSuaNV.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaNhanVien();
			}
		});
		btnXoaNV.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaNhanVien();
			}
		});
		btnTimKiemNhanVien.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemNhanVien();
			}
		});
		chkHienThiToanBoNhanVien.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					NhanVienService nvService = new NhanVienService();
					dsNhanVien = nvService.docToanBoNhanVien();
					dtmNhanVien.setRowCount(0);
					for(HienThiToanBoNhanVienModel nv : dsNhanVien)
					{
						Vector<Object>vec = new Vector<Object>();
						vec.add(nv.getMaNV());
						vec.add(nv.getTenNV());
						vec.add(nv.getNamSinh());
						vec.add(nv.getDiaChi());
						vec.add(nv.getSDT());
						vec.add(nv.getEmail());
						vec.add(nv.getNgayVaoLamViec());
						vec.add(nv.getTenBoPhan());
						vec.add(nv.getTaiKhoan());
						dtmNhanVien.addRow(vec);
					}
				}
				else{
					dtmNhanVien.setRowCount(0);
				}
			}
		});
		btnReload.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				hienThiToanBoTaiKhoanTenCombobox();
			}
		});
		chkThongKeTheoNgay.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					ThongKeDoanhThuTheoNgayService tktnService = new ThongKeDoanhThuTheoNgayService();
					dsThongKeTheoNgay = tktnService.thongKeDoanhThuTheoNgay();
					dtmThongKeTheoNgay.setRowCount(0);
					for(ThongKeDoanhThuTheoNgayModel tktn : dsThongKeTheoNgay)
					{
						Vector<Object>vec = new Vector<Object>();
						vec.add(tktn.getNgay());
						vec.add(tktn.getTongTien());
						dtmThongKeTheoNgay.addRow(vec);
					}
				}
				else{
					dtmThongKeTheoNgay.setRowCount(0);
				}
			}
		});
		chkThongKeTheoThang.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(txtNam.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập năm cần thống kê!");
					return;
				}
				else{
					if(e.getStateChange()==1){
						ThongKeDoanhThuTheoThangService tkttService = new ThongKeDoanhThuTheoThangService();
						dsThongKeTheoThang = tkttService.thongKeDoanhThuTheoThang(Integer.parseInt(txtNam.getText()));
						dtmThongKeTheoThang.setRowCount(0);
						for(ThongKeDoanhThuTheoThangModel tktt : dsThongKeTheoThang){
							Vector<Object>vec = new Vector<Object>();
							vec.add(tktt.getThang());
							vec.add(tktt.getTongTien());
							dtmThongKeTheoThang.addRow(vec);
						}
					}
					else{
						dtmThongKeTheoThang.setRowCount(0);
					}
				}
				
			}
		});
		tblKhuyenMai.addMouseListener(new MouseListener() {
			
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

				int row = tblKhuyenMai.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					KhuyenMaiModel km = dsKM.get(row);
					txtTienToiThieu.setText(km.getTienToiThieu()+"");
					txtTienToiDa.setText(km.getTienToiDa()+"");
					txtUuDai.setText(km.getUuDai()+"");
				}
			}
		});
		btnThemKM.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemKhuyenMai();
			}
		});
		btnSuaKM.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaKhuyenMai();
			}
		});
		btnXoaKM.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyXoaKhuyenMai();
			}
		});
	}

	protected void xuLyXoaKhuyenMai() {
		int rowSelected = tblKhuyenMai.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn khuyến mãi cần xoá!");
			return;
		}
		else{
			int kmSelected = Integer.parseInt(tblKhuyenMai.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					KhuyenMaiService kmService = new KhuyenMaiService();
					if(kmService.xoaKhuyenMai(kmSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá thành công!");
						txtTienToiThieu.setText("");
						txtTienToiDa.setText("");
						txtUuDai.setText("");
						txtTienToiThieu.requestFocus();
						hienThiToanBoKhuyenMai();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaKhuyenMai() {
		int rowSelected = tblKhuyenMai.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn khuyến mãi cần sửa!");
			return;
		}
		else {
			int kmSelected = Integer.parseInt(tblKhuyenMai.getValueAt(rowSelected, 0)+"");
			try {
				KhuyenMaiService kmService = new KhuyenMaiService();
				if(kmService.suaKhuyenMai(kmSelected, Integer.parseInt(txtTienToiThieu.getText()), 
						Integer.parseInt(txtTienToiDa.getText()), Integer.parseInt(txtUuDai.getText())) > 0){
					JOptionPane.showMessageDialog(null, "Sửa thông tin khuyến mãi thành công!");
					txtTienToiThieu.setText("");
					txtTienToiDa.setText("");
					txtUuDai.setText("");
					txtTienToiThieu.requestFocus();
					hienThiToanBoKhuyenMai();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	protected void xuLyThemKhuyenMai() {
		KhuyenMaiModel km = new KhuyenMaiModel();
		km.setTienToiThieu(Integer.parseInt(txtTienToiThieu.getText()));
		km.setTienToiDa(Integer.parseInt(txtTienToiDa.getText()));
		km.setUuDai(Integer.parseInt(txtUuDai.getText()));
		KhuyenMaiService kmService = new KhuyenMaiService();
		if(kmService.themKhuyenMai(km) > 0)
		{
			JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thành công!");
			txtTienToiThieu.setText("");
			txtTienToiDa.setText("");
			txtUuDai.setText("");
			txtTienToiThieu.requestFocus();
			hienThiToanBoKhuyenMai();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thất bại!");
		}
	}
	protected void xuLyTimKiemNhanVien() {
		if(txtTimKiemNhanVien.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm!");
			return;
		}
		else{
			NhanVienService nvService = new NhanVienService();
			Vector<HienThiToanBoNhanVienModel>dsnv = nvService.timNhanVienTheoTen(txtTimKiemNhanVien.getText());
			dtmNhanVien.setRowCount(0);
			for(HienThiToanBoNhanVienModel nv : dsnv)
			{
				Vector<Object> vec = new Vector<Object>();
				vec.add(nv.getMaNV());
				vec.add(nv.getTenNV());
				vec.add(nv.getNamSinh());
				vec.add(nv.getDiaChi());
				vec.add(nv.getSDT());
				vec.add(nv.getEmail());
				vec.add(nv.getNgayVaoLamViec());
				vec.add(nv.getTenBoPhan());
				vec.add(nv.getTaiKhoan());
				dtmNhanVien.addRow(vec);
			}
		}

	}
	protected void xuLyXoaNhanVien() {
		int rowSelected = tblNhanVien.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần xoá!");
			return;
		}
		else{
			int nvSelected = Integer.parseInt(tblNhanVien.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá nhân viên "+txtTenNV.getText()+" không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					NhanVienService nvService = new NhanVienService();
					if(nvService.xoaNhanVien(nvSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá nhân viên thành công!");
						txtTenNV.setText("");
						//dcNamSinh.setText("");
						txtDiaChi.setText("");
						txtSDT.setText("");
						txtEmail.setText("");
						//dcNgayVaoLamViec.setText("");
						txtTenNV.requestFocus();
						hienThiToanBoNhanVien();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaNhanVien() {
		int rowSelected = tblNhanVien.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần sửa!");
			return;
		}
		else {
			if(txtTenNV.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Không được để trống tên!");
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
									int nvSelected = Integer.parseInt(tblNhanVien.getValueAt(rowSelected, 0)+"");
									try {
										NhanVienService nvService = new NhanVienService();
										if(nvService.suaNhanVien(nvSelected, txtTenNV.getText(), sdf.format(dcNamSinh.getDate()), 
												txtDiaChi.getText(), txtSDT.getText(), txtEmail.getText(), 
												sdf.format(dcNgayVaoLamViec.getDate())) > 0){
											JOptionPane.showMessageDialog(null, "Sửa thông tin nhân viên thành công!");
											txtTenNV.setText("");
											//dcNamSinh.setText("");
											txtDiaChi.setText("");
											txtSDT.setText("");
											txtEmail.setText("");
											//dcNgayVaoLamViec.setText("");
											txtTenNV.requestFocus();
											hienThiToanBoNhanVien();
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
	protected void xuLyThemNhanVien() {
		if(txtTenNV.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhân viên!");
			return;
		}
		else{
			if(txtDiaChi.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ!");
				return;
			}
			else{
				if(txtSDT.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!");
					return;
				}
				else{
					if(!isPhone(txtSDT.getText())){
						JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số điện thoại!");
						return;
					}
					else{
						if(txtEmail.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập email!");
							return;
						}
						else{
							if(!isEmail(txtEmail.getText())){
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng email!");
								return;
							}
							else{
								NhanVienModel nv = new NhanVienModel();
								nv.setTenNV(txtTenNV.getText());
								nv.setNamSinh(sdf.format(dcNamSinh.getDate())); //nhập theo format yyyy/MM/dd
								nv.setDiaChi(txtDiaChi.getText());
								nv.setSDT(txtSDT.getText());
								nv.setEmail(txtEmail.getText());
								nv.setNgayVaoLamViec(sdf.format(dcNgayVaoLamViec.getDate())); //nhập theo format yyyy/MM/dd
								nv.setMaLoaiNV(cboLoaiNhanVien.getSelectedIndex()==0 ? 1 : 2); 
								nv.setTenDangNhap(cboTenTaiKhoan.getSelectedItem()+"");
								NhanVienService nvService = new NhanVienService();
								if(nvService.themNhanVien(nv) > 0)
								{
									JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
									txtTenNV.setText("");
									//dcNamSinh.setText("");
									txtDiaChi.setText("");
									txtSDT.setText("");
									txtEmail.setText("");
									//dcNgayVaoLamViec.setText("");
									txtTenNV.requestFocus();
									hienThiToanBoNhanVien();
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!");
								}
							}
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
		this.setSize(1500, 900);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
