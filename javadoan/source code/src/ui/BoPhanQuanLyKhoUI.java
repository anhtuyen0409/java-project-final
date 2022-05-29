package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.chrono.JapaneseDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.TableUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import model.ChiTietPhieuNhapModel;
import model.HienThiToanBoChiTietPhieuNhapModel;
import model.HienThiToanBoNhanVienModel;
import model.HienThiToanBoPhieuNhapModel;
import model.LoaiSanPhamModel;
import model.NhaCungCapModel;
import model.NhaSanXuatModel;
import model.NhanVienModel;
import model.PhieuNhapModel;
import model.SanPhamModel;
import service.ChiTietPhieuNhapService;
import service.LoaiSanPhamService;
import service.NhaCungCapService;
import service.NhaSanXuatService;
import service.NhanVienService;
import service.PhieuNhapService;
import service.SanPhamService;


public class BoPhanQuanLyKhoUI extends JFrame{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	JMenuBar mnuBar;//nơi để đựng (chứa) các JMenu
	JMenu mnuHeThong;
	JMenuItem mnuThongTin;
	JMenuItem mnuDangXuat;
	JMenuItem mnuThoat;
	JToolBar tooBar;
	JButton btnThongTin,btnDangXuat, btnThoat/*, btnTroVe*/;
	JPanel pnTab1, pnTab2, pnTab3;
	JTabbedPane tab;
	ArrayList<SanPhamModel>dsSp;
	LoaiSanPhamModel loaiSPSelected = null;
	JLabel lblAnhSP;
	JPanel pnBottomOfRight;

	//controls Tab1 sp và loại sp
	//controls pnLeft
	DefaultTableModel dtmLoaiSanPham;
	JTable tblLoaiSanPham;
	Vector<LoaiSanPhamModel>dsLoaiSP;
	JComboBox<LoaiSanPhamModel>cboLoaiSP;
	JComboBox<NhaCungCapModel>cboNhaCungCap;
	JComboBox<NhaSanXuatModel>cboNhaSanXuat;
	JTextField txtTimKiemLoaiSP, txtTenLoaiSP;
	JButton btnTimKiemLoaiSP, btnThemLoaiSP, btnSuaLoaiSP, btnXoaLoaiSP;
	JCheckBox chkHienThiToanBoLoaiSP;
	//controls pnRight
	DefaultTableModel dtmSanPham;
	Vector<SanPhamModel> dsSanPham;
	JTable tblSanPham;
	JTextArea txtMoTa;
	JTextField txtTenSP, txtDonGia, txtHinhAnh, txtSoLuongTon, txtTimKiemSP;
	JButton btnThemSP, btnSuaSP, btnXoaSP, btnThemAnh, btnTimKiemSP;
	JCheckBox chkHienThiToanBo;
	JDateChooser dcNgayCapNhat;

	//controls Tab2 - phiếu nhập & chi tiết phiếu nhập
	//controls pnLeft
	DefaultTableModel dtmPhieuNhap;
	JTable tblPhieuNhap;
	JTextField txtTimKiemPhieuNhap;
	JDateChooser dcNgayNhap;
	JButton btnTimKiemPhieuNhap, btnThemPhieuNhap, btnSuaPhieuNhap, btnXoaPhieuNhap, btnReloadNCC;
	JComboBox<NhaCungCapModel>cboNhaCungCap2;
	JComboBox<HienThiToanBoNhanVienModel>cboNhanVien;
	Vector<HienThiToanBoPhieuNhapModel>dspn;
	JCheckBox chkHienThiToanBoPhieuNhap;
	//controls pnRight
	DefaultTableModel dtmChiTietPhieuNhap;
	JTable tblChiTietPhieuNhap;
	JTextField txtTimKiemCTPN, txtSoLuong;
	JButton btnTimKiemCTPN, btnThemCTPN, btnSuaCTPN, btnXoaCTPN;
	JComboBox<SanPhamModel>cboSanPham;
	JComboBox<HienThiToanBoPhieuNhapModel>cboPhieuNhap;
	Vector<HienThiToanBoChiTietPhieuNhapModel>dsCTPN;
	JCheckBox chkHienThiToanBoCTPN;

	//controls nguồn cung ứng
	//controls pnLeft
	DefaultTableModel dtmNhaSanXuat;
	JTable tblNhaSanXuat;
	JTextField txtTimKiemNhaSanXuat, txtTenNhaSanXuat;
	JButton btnTimKiemNhaSanXuat, btnThemNSX, btnXoaNSX, btnSuaNSX;
	JTextArea txtThongTinNSX;
	Vector<NhaSanXuatModel>dsNhaSanXuat;
	JCheckBox chkHienThiToanBoNSX;
	//controls pnRight
	DefaultTableModel dtmNhaCungCap;
	JTable tblNhaCungCap;
	JTextField txtTimKiemNCC, txtTenNCC, txtDiaChi, txtEmail, txtSDT, txtFax;
	JButton btnTimKiemNCC, btnThemNCC, btnSuaNCC, btnXoaNCC;
	Vector<NhaCungCapModel>dsNhaCungCap;
	JCheckBox chkHienThiToanBoNCC;
	public BoPhanQuanLyKhoUI(String title){
		super(title);
		addControls();
		addEvents();
		hienThiToanBoLoaiSanPham();
		hienThiToanBoLoaiSanPhamLenCombobox();
		hienThiToanBoNhaSanXuat();
		hienThiToanBoNhaSanXuatLenCombobox();
		hienThiToanBoNhaCungCap();
		hienThiToanBoNhaCungCapLenCombobox();
		hienThiToanBoNhaCungCapLenCombobox2();
		hienThiToanBoSanPhamLenCombobox();
		hienThiToanBoNhanVienLenCombobox();
		hienThiToanBoPhieuNhap();
		hienThiToanBoMaPhieuNhapLenCombobox();
		hienThiToanBoSanPham();
		hienThiToanBoChiTietPhieuNhap();
	}
	private void hienThiToanBoChiTietPhieuNhap() {
		ChiTietPhieuNhapService ctpnService = new ChiTietPhieuNhapService();
		dsCTPN = ctpnService.docToanBoCTPN();
		dtmChiTietPhieuNhap.setRowCount(0);
		for(HienThiToanBoChiTietPhieuNhapModel ctpn : dsCTPN)
		{
			Vector<Object>vec=new Vector<Object>();
			vec.add(ctpn.getMaCTPN());
			vec.add(ctpn.getMaPN());
			vec.add(ctpn.getTenSP());
			vec.add(ctpn.getSoLuong());
			dtmChiTietPhieuNhap.addRow(vec);
		}
	}
	private void hienThiToanBoSanPham() {
		SanPhamService spService = new SanPhamService();
		dsSp = spService.docToanBoSanPham();
		dtmSanPham.setRowCount(0);
		for(SanPhamModel sp : dsSp)
		{
			Vector<Object>vec=new Vector<Object>();
			vec.add(sp.getMaSP());
			vec.add(sp.getTenSP());
			vec.add(sp.getDonGia());
			vec.add(sp.getNgayCapNhat());
			vec.add(sp.getMoTa());
			vec.add(sp.getHinhAnh());
			vec.add(sp.getSLTon());
			dtmSanPham.addRow(vec);
		}
	}
	private void hienThiToanBoMaPhieuNhapLenCombobox() {
		PhieuNhapService pnService = new PhieuNhapService();
		Vector<HienThiToanBoPhieuNhapModel>vec = pnService.docToanBoPhieuNhap();
		cboPhieuNhap.removeAllItems();
		for(HienThiToanBoPhieuNhapModel pn : vec)
		{
			cboPhieuNhap.addItem(pn);
		}
	}
	private void hienThiToanBoNhanVienLenCombobox() {
		NhanVienService nvService = new NhanVienService();
		Vector<HienThiToanBoNhanVienModel>vec = nvService.docToanBoNhanVien();
		cboNhanVien.removeAllItems();
		for(HienThiToanBoNhanVienModel nv : vec)
		{
			cboNhanVien.addItem(nv);
		}
	}
	private void hienThiToanBoPhieuNhap() {
		PhieuNhapService pnService = new PhieuNhapService();
		dspn = pnService.docToanBoPhieuNhap();
		dtmPhieuNhap.setRowCount(0);
		for(HienThiToanBoPhieuNhapModel pn : dspn)
		{
			Vector<Object>vec = new Vector<Object>();
			vec.add(pn.getMaPN());
			vec.add(pn.getNgayNhap());
			vec.add(pn.getTenNV());
			vec.add(pn.getTenNCC());
			dtmPhieuNhap.addRow(vec);
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
	private void hienThiToanBoNhaCungCapLenCombobox2() {
		NhaCungCapService nccService = new NhaCungCapService();
		Vector<NhaCungCapModel>vec = nccService.docToanBoNhaCungCap();
		cboNhaCungCap2.removeAllItems();
		for(NhaCungCapModel ncc : vec)
		{
			cboNhaCungCap2.addItem(ncc);
		}
	}
	private void hienThiToanBoNhaCungCapLenCombobox() {
		NhaCungCapService nccService = new NhaCungCapService();
		Vector<NhaCungCapModel>vec = nccService.docToanBoNhaCungCap();
		cboNhaCungCap.removeAllItems();
		for(NhaCungCapModel ncc : vec)
		{
			cboNhaCungCap.addItem(ncc);
		}
	}
	private void hienThiToanBoNhaSanXuatLenCombobox() {
		NhaSanXuatService nsxService = new NhaSanXuatService();
		Vector<NhaSanXuatModel>vec = nsxService.docToanBoNhaSanXuat();
		cboNhaSanXuat.removeAllItems();
		for(NhaSanXuatModel nsx : vec)
		{
			cboNhaSanXuat.addItem(nsx);
		}
	}
	private void hienThiToanBoNhaCungCap() {
		NhaCungCapService nccService = new NhaCungCapService();
		dsNhaCungCap = nccService.docToanBoNhaCungCap();
		dtmNhaCungCap.setRowCount(0);
		for(NhaCungCapModel ncc : dsNhaCungCap)
		{
			Vector<Object>vec=new Vector<Object>();
			vec.add(ncc.getMaNCC());
			vec.add(ncc.getTenNCC());
			vec.add(ncc.getDiaChi());
			vec.add(ncc.getSDT());
			vec.add(ncc.getEmail());
			vec.add(ncc.getFax());
			dtmNhaCungCap.addRow(vec);
		}
	}
	private void hienThiToanBoNhaSanXuat() {
		NhaSanXuatService nsxService = new NhaSanXuatService();
		dsNhaSanXuat = nsxService.docToanBoNhaSanXuat();
		dtmNhaSanXuat.setRowCount(0);
		for(NhaSanXuatModel nsx : dsNhaSanXuat)
		{
			Vector<Object>vec=new Vector<Object>();
			vec.add(nsx.getMaNSX());
			vec.add(nsx.getTenNSX());
			vec.add(nsx.getThongTin());
			dtmNhaSanXuat.addRow(vec);
		}
	}
	public int layMaSanPhamTheoThuTu(int index){
		return Integer.parseInt(tblSanPham.getValueAt(index, 0)+"");
	}
	private void addEvents() {

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

		tblLoaiSanPham.addMouseListener(new MouseListener() {

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
				int row = tblLoaiSanPham.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					//hiển thị thông tin loại sp trên các controls
					LoaiSanPhamModel lsp = dsLoaiSP.get(row);
					txtTenLoaiSP.setText(lsp.getTenLoaiSP());

					//hiển thị thông tin sản phẩm theo loại sản phẩm
					//đã fix xong 
					SanPhamService spService=new SanPhamService();
					dsSp = spService.docSanPhamTheoLoai(Integer.parseInt(tblLoaiSanPham.getValueAt(row, 0)+""));
					dtmSanPham.setRowCount(0);
					for(SanPhamModel sp : dsSp)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(sp.getMaSP());
						vec.add(sp.getTenSP());
						vec.add(sp.getDonGia());
						vec.add(sp.getNgayCapNhat());
						vec.add(sp.getMoTa());
						vec.add(sp.getHinhAnh());
						vec.add(sp.getSLTon());
						dtmSanPham.addRow(vec);
					}
				}
			}
		});
		tblSanPham.addMouseListener(new MouseListener() {

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
				int row = tblSanPham.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					SanPhamModel sp = dsSp.get(row);
					txtTenSP.setText(sp.getTenSP());
					txtDonGia.setText(sp.getDonGia()+"");
					txtMoTa.setText(sp.getMoTa());
					txtSoLuongTon.setText(sp.getSLTon()+"");
					txtHinhAnh.setText(sp.getHinhAnh());
					lblAnhSP = new JLabel(new ImageIcon(txtHinhAnh.getText()));
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
					lblAnhSP = new JLabel(new ImageIcon(txtHinhAnh.getText()));
					pnRightOfBottomOfRight.add(lblAnhSP, BorderLayout.CENTER);
				}
			}
		});
		cboLoaiSP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(cboLoaiSP.getSelectedIndex() == -1){
					return;
				}
				else{
					SanPhamService spService = new SanPhamService();
					dsSp = spService.docSanPhamTheoLoai(Integer.parseInt(tblLoaiSanPham.getValueAt(cboLoaiSP.getSelectedIndex(), 0)+""));
					dtmSanPham.setRowCount(0);
					for(SanPhamModel sp : dsSp)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(sp.getMaSP());
						vec.add(sp.getTenSP());
						vec.add(sp.getDonGia());
						vec.add(sp.getNgayCapNhat());
						vec.add(sp.getMoTa());
						vec.add(sp.getHinhAnh());
						vec.add(sp.getSLTon());
						dtmSanPham.addRow(vec);
					}
				}
			}
		});
		cboNhaSanXuat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(cboNhaSanXuat.getSelectedIndex() == -1){
					return;
				}
				else{
					SanPhamService spService = new SanPhamService();
					dsSp = spService.docSanPhamTheoNhaSanXuat(Integer.parseInt(tblNhaSanXuat.getValueAt(cboNhaSanXuat.getSelectedIndex(), 0)+""));
					dtmSanPham.setRowCount(0);
					for(SanPhamModel sp : dsSp)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(sp.getMaSP());
						vec.add(sp.getTenSP());
						vec.add(sp.getDonGia());
						vec.add(sp.getNgayCapNhat());
						vec.add(sp.getMoTa());
						vec.add(sp.getHinhAnh());
						vec.add(sp.getSLTon());
						dtmSanPham.addRow(vec);
					}
				}
			}
		});
		cboNhaCungCap.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(cboNhaCungCap.getSelectedIndex() == -1){
					return;
				}
				else{
					SanPhamService spService = new SanPhamService();
					dsSp = spService.docSanPhamTheoNhaCungCap(Integer.parseInt(tblNhaCungCap.getValueAt(cboNhaCungCap.getSelectedIndex(), 0)+""));
					dtmSanPham.setRowCount(0);
					for(SanPhamModel sp : dsSp)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(sp.getMaSP());
						vec.add(sp.getTenSP());
						vec.add(sp.getDonGia());
						vec.add(sp.getNgayCapNhat());
						vec.add(sp.getMoTa());
						vec.add(sp.getHinhAnh());
						vec.add(sp.getSLTon());
						dtmSanPham.addRow(vec);
					}
				}
			}
		});
		btnThemLoaiSP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemLoaiSanPham();
			}
		});
		btnSuaLoaiSP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaLoaiSanPham();		
			}
		});
		btnXoaLoaiSP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaLoaiSanPham();
			}
		});
		btnTimKiemLoaiSP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyTimLoaiSanPham();
			}
		});
		chkHienThiToanBoLoaiSP.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					LoaiSanPhamService lspService = new LoaiSanPhamService();
					dsLoaiSP = lspService.docToanBoLoaiSanPham();
					dtmLoaiSanPham.setRowCount(0);
					for(LoaiSanPhamModel lsp : dsLoaiSP)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(lsp.getMaLoaiSP());
						vec.add(lsp.getTenLoaiSP());
						dtmLoaiSanPham.addRow(vec);
					}
				}
				else{
					dtmLoaiSanPham.setRowCount(0);
				}
			}
		});
		chkHienThiToanBo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					SanPhamService spService = new SanPhamService();
					dsSp = spService.docToanBoSanPham();
					dtmSanPham.setRowCount(0);
					for(SanPhamModel sp : dsSp)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(sp.getMaSP());
						vec.add(sp.getTenSP());
						vec.add(sp.getDonGia());
						vec.add(sp.getNgayCapNhat());
						vec.add(sp.getMoTa());
						vec.add(sp.getHinhAnh());
						vec.add(sp.getSLTon());
						dtmSanPham.addRow(vec);
					}
				}
				else{
					dtmSanPham.setRowCount(0);
				}
			}
		});

		btnThemSP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemSanPham();
			}
		});
		btnSuaSP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaSanPham();
			}
		});
		btnXoaSP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaSanPham();
			}
		});
		btnTimKiemSP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemSanPham();
			}
		});

		//test
		btnThemAnh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemAnh();
			}
		});

		//events Tab2
		chkHienThiToanBoPhieuNhap.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					PhieuNhapService pnService = new PhieuNhapService();
					dspn = pnService.docToanBoPhieuNhap();
					dtmPhieuNhap.setRowCount(0);
					for(HienThiToanBoPhieuNhapModel pn : dspn)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(pn.getMaPN());
						vec.add(pn.getNgayNhap());
						vec.add(pn.getTenNV());
						vec.add(pn.getTenNCC());
						dtmPhieuNhap.addRow(vec);
					}
				}
				else{
					dtmPhieuNhap.setRowCount(0);
				}
			}
		});
		chkHienThiToanBoCTPN.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					ChiTietPhieuNhapService ctpnService = new ChiTietPhieuNhapService();
					dsCTPN = ctpnService.docToanBoCTPN();
					dtmChiTietPhieuNhap.setRowCount(0);
					for(HienThiToanBoChiTietPhieuNhapModel ctpn : dsCTPN)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(ctpn.getMaCTPN());
						vec.add(ctpn.getMaPN());
						vec.add(ctpn.getTenSP());
						vec.add(ctpn.getSoLuong());
						dtmChiTietPhieuNhap.addRow(vec);
					}
				}
				else{
					dtmChiTietPhieuNhap.setRowCount(0);
				}
			}
		});
		tblPhieuNhap.addMouseListener(new MouseListener() {

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
				int row = tblPhieuNhap.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					//hiển thị thông tin ngày nhập trên controls
					HienThiToanBoPhieuNhapModel pn = dspn.get(row);
					//dcNgayNhap.setDate(pn.getNgayNhap());

					//hiển thị thông tin chi tiết phiếu nhập theo phiếu nhập
					ChiTietPhieuNhapService ctpnService = new ChiTietPhieuNhapService();
					dsCTPN = ctpnService.docCTPNTheoMaPN(Integer.parseInt(tblPhieuNhap.getValueAt(row, 0)+""));
					dtmChiTietPhieuNhap.setRowCount(0);
					for(HienThiToanBoChiTietPhieuNhapModel ctpn : dsCTPN)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(ctpn.getMaCTPN());
						vec.add(ctpn.getMaPN());
						vec.add(ctpn.getTenSP());
						vec.add(ctpn.getSoLuong());
						dtmChiTietPhieuNhap.addRow(vec);
					}
				}
			}
		});
		tblChiTietPhieuNhap.addMouseListener(new MouseListener() {

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
				int row = tblChiTietPhieuNhap.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					HienThiToanBoChiTietPhieuNhapModel ctpn = dsCTPN.get(row);
					txtSoLuong.setText(ctpn.getSoLuong()+"");
				}
			}
		});
		btnTimKiemPhieuNhap.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemPhieuNhap();
			}
		});
		btnThemPhieuNhap.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemPhieuNhap();
			}
		});
		btnSuaPhieuNhap.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaPhieuNhap();
			}
		});
		btnXoaPhieuNhap.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaPhieunhap();
			}
		});
		cboNhanVien.addActionListener(new ActionListener() {
			BanQuanLyUI ui=new BanQuanLyUI("");
			public void actionPerformed(ActionEvent e) {
				if(cboNhanVien.getSelectedIndex() == -1){
					return;
				}
				else{
					PhieuNhapService pnService = new PhieuNhapService();
					dspn = pnService.docPhieuNhapTheoNhanVien(ui.layMaNhanVienTheoTen(cboNhanVien.getSelectedIndex()));
					dtmPhieuNhap.setRowCount(0);
					for(HienThiToanBoPhieuNhapModel pn : dspn)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(pn.getMaPN());
						vec.add(pn.getNgayNhap());
						vec.add(pn.getTenNV());
						vec.add(pn.getTenNCC());
						dtmPhieuNhap.addRow(vec);
					}
				}
			}
		});
		cboNhaCungCap2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(cboNhanVien.getSelectedIndex() == -1){
					return;
				}
				else{
					PhieuNhapService pnService = new PhieuNhapService();
					dspn = pnService.docPhieuNhapTheoNhaCungCap(
							Integer.parseInt(tblNhaCungCap.getValueAt(cboNhaCungCap2.getSelectedIndex(), 0)+""));
					dtmPhieuNhap.setRowCount(0);
					for(HienThiToanBoPhieuNhapModel pn : dspn)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(pn.getMaPN());
						vec.add(pn.getNgayNhap());
						vec.add(pn.getTenNV());
						vec.add(pn.getTenNCC());
						dtmPhieuNhap.addRow(vec);
					}
				}
			}
		});
		/*btnReloadNCC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				hienThiToanBoNhaCungCapLenCombobox2();
			}
		});*/
		cboPhieuNhap.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(cboPhieuNhap.getSelectedIndex() == -1){
					return;
				}
				else{
					ChiTietPhieuNhapService ctpnService = new ChiTietPhieuNhapService();
					dsCTPN = ctpnService.docCTPNTheoMaPN(
							Integer.parseInt(tblPhieuNhap.getValueAt(cboPhieuNhap.getSelectedIndex(), 0)+""));
					dtmChiTietPhieuNhap.setRowCount(0);
					for(HienThiToanBoChiTietPhieuNhapModel ctpn : dsCTPN)
					{
						Vector<Object>vec=new Vector<Object>();
						vec.add(ctpn.getMaCTPN());
						vec.add(ctpn.getMaPN());
						vec.add(ctpn.getTenSP());
						vec.add(ctpn.getSoLuong());
						dtmChiTietPhieuNhap.addRow(vec);
					}
				}
			}
		});
		
		btnTimKiemCTPN.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemCTPN();
			}
		});

		btnThemCTPN.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemCTPN();
			}
		});
		btnSuaCTPN.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaCTPN();
			}
		});
		btnXoaCTPN.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaCTPN();
			}
		});
		//events Tab3 
		tblNhaSanXuat.addMouseListener(new MouseListener() {

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
				int row = tblNhaSanXuat.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					NhaSanXuatModel nsx = dsNhaSanXuat.get(row);
					txtTenNhaSanXuat.setText(nsx.getTenNSX());
					txtThongTinNSX.setText(nsx.getThongTin());
				}
			}
		});
		tblNhaCungCap.addMouseListener(new MouseListener() {

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
				int row = tblNhaCungCap.getSelectedRow();
				if(row == -1){
					return;
				}
				else{
					NhaCungCapModel ncc = dsNhaCungCap.get(row);
					txtTenNCC.setText(ncc.getTenNCC());
					txtDiaChi.setText(ncc.getDiaChi());
					txtSDT.setText(ncc.getSDT());
					txtEmail.setText(ncc.getEmail());
					txtFax.setText(ncc.getFax());
				}
			}
		});
		btnThemNSX.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemNhaSanXuat();
			}
		});
		btnSuaNSX.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaNhaSanXuat();
			}
		});
		btnXoaNSX.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaNhaSanXuat();
			}
		});
		btnTimKiemNhaSanXuat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemNhaSanXuat();
			}
		});
		btnThemNCC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThemNhaCungCap();
			}
		});
		btnSuaNCC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLySuaNhaCungCap();

			}
		});
		btnXoaNCC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyXoaNhaCungCap();

			}
		});
		btnTimKiemNCC.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyTimKiemNhaCungCap();
			}
		});
		chkHienThiToanBoNSX.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					NhaSanXuatService nsxService = new NhaSanXuatService();
					dsNhaSanXuat = nsxService.docToanBoNhaSanXuat();
					dtmNhaSanXuat.setRowCount(0);
					for(NhaSanXuatModel nsx : dsNhaSanXuat)
					{
						Vector<Object>vec = new Vector<Object>();
						vec.add(nsx.getMaNSX());
						vec.add(nsx.getTenNSX());
						vec.add(nsx.getThongTin());
						dtmNhaSanXuat.addRow(vec);
					}
				}
				else{
					dtmNhaSanXuat.setRowCount(0);
				}
			}
		});
		chkHienThiToanBoNCC.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1){
					NhaCungCapService nccService = new NhaCungCapService();
					dsNhaCungCap = nccService.docToanBoNhaCungCap();
					dtmNhaCungCap.setRowCount(0);
					for(NhaCungCapModel ncc : dsNhaCungCap)
					{
						Vector<Object>vec = new Vector<Object>();
						vec.add(ncc.getMaNCC());
						vec.add(ncc.getTenNCC());
						vec.add(ncc.getDiaChi());
						vec.add(ncc.getSDT());
						vec.add(ncc.getEmail());
						vec.add(ncc.getFax());
						dtmNhaCungCap.addRow(vec);
					}
				}
				else{
					dtmNhaCungCap.setRowCount(0);
				}
			}
		});
	}
	protected void xuLyTimKiemCTPN() {
		if(txtTimKiemCTPN.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm!");
			return;
		}
		else{
			ChiTietPhieuNhapService ctpnService = new ChiTietPhieuNhapService();
			Vector<HienThiToanBoChiTietPhieuNhapModel>dsctpn = ctpnService.timKiemChiTietPhieuNhapTheoTenSP(txtTimKiemCTPN.getText());
			dtmChiTietPhieuNhap.setRowCount(0);
			for(HienThiToanBoChiTietPhieuNhapModel ctpn : dsctpn)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(ctpn.getMaCTPN());
				vec.add(ctpn.getMaPN());
				vec.add(ctpn.getTenSP());
				vec.add(ctpn.getSoLuong());
				dtmChiTietPhieuNhap.addRow(vec);
			}
		}
	}
	protected void xuLyTimKiemPhieuNhap() {
		if(txtTimKiemPhieuNhap.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm!");
			return;
		}
		else{
			PhieuNhapService pnService = new PhieuNhapService();
			Vector<HienThiToanBoPhieuNhapModel>dspn = pnService.timKiemPhieuNhapTheoNgayNhap(txtTimKiemPhieuNhap.getText());
			dtmPhieuNhap.setRowCount(0);
			for(HienThiToanBoPhieuNhapModel pn : dspn)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(pn.getMaPN());
				vec.add(pn.getNgayNhap());
				vec.add(pn.getTenNV());
				vec.add(pn.getTenNCC());
				dtmPhieuNhap.addRow(vec);
			}
		}
	}
	protected void xuLyXoaCTPN() {
		int rowSelected = tblChiTietPhieuNhap.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng cần xoá!");
			return;
		}
		else{
			int ctpnSelected = Integer.parseInt(tblChiTietPhieuNhap.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					ChiTietPhieuNhapService ctpnService = new ChiTietPhieuNhapService();
					if(ctpnService.xoaChiTietPhieuNhap(ctpnSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá thành công!");
						hienThiToanBoChiTietPhieuNhap();
						txtSoLuong.setText("");
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaCTPN() {
		int rowSelected = tblChiTietPhieuNhap.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng cần sửa!");
			return;
		}
		else {
			if(txtSoLuong.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Không được để trống số lượng!");
				return;
			}
			else{
				int ctpnSelected = Integer.parseInt(tblChiTietPhieuNhap.getValueAt(rowSelected, 0)+"");
				try {
					ChiTietPhieuNhapService ctpnService = new ChiTietPhieuNhapService();
					if(ctpnService.suaChiTietPhieuNhap(ctpnSelected, Integer.parseInt(tblPhieuNhap.getValueAt(cboPhieuNhap.getSelectedIndex(), 0)+""),
							Integer.parseInt(tblSanPham.getValueAt(cboSanPham.getSelectedIndex(), 0)+""), Integer.parseInt(txtSoLuong.getText())) > 0){
						JOptionPane.showMessageDialog(null, "Sửa thông tin thành công!");
						hienThiToanBoChiTietPhieuNhap();
						txtSoLuong.setText("");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLyThemCTPN() {
		if(txtSoLuong.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng!");
			return;
		}
		else{
			ChiTietPhieuNhapModel ctpn = new ChiTietPhieuNhapModel();
			ctpn.setMaPN(Integer.parseInt(cboPhieuNhap.getSelectedItem()+""));
			ctpn.setMaSP(Integer.parseInt(tblSanPham.getValueAt(cboSanPham.getSelectedIndex(), 0)+""));
			ctpn.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
			ChiTietPhieuNhapService ctpnService = new ChiTietPhieuNhapService();
			if(ctpnService.themChiTietPhieunhap(ctpn) > 0)
			{
				JOptionPane.showMessageDialog(null, "Thêm thành công!");
				hienThiToanBoChiTietPhieuNhap();
				txtSoLuong.setText("");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Thêm thất bại!");
			}
		}
	}
	protected void xuLyXoaPhieunhap() {
		int rowSelected = tblPhieuNhap.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn phiếu nhập cần xoá!");
			return;
		}
		else{
			int pnSelected = Integer.parseInt(tblPhieuNhap.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					PhieuNhapService pnService = new PhieuNhapService();
					if(pnService.xoaPhieuNhap(pnSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá phiếu nhập thành công!");
						hienThiToanBoPhieuNhap();
						hienThiToanBoMaPhieuNhapLenCombobox();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaPhieuNhap() {
		BanQuanLyUI ui = new BanQuanLyUI("");
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		int rowSelected = tblPhieuNhap.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn phiếu nhập cần sửa!");
			return;
		}
		else {
			int pnSelected = Integer.parseInt(tblPhieuNhap.getValueAt(rowSelected, 0)+"");
			try {
				PhieuNhapService pnService = new PhieuNhapService();
				if(pnService.suaPhieuNhap(pnSelected, sdf.format(dcNgayNhap.getDate()), 
						ui.layMaNhanVienTheoTen(cboNhanVien.getSelectedIndex()), 
						Integer.parseInt(tblNhaCungCap.getValueAt(cboNhaCungCap2.getSelectedIndex(), 0)+"")) > 0){
					JOptionPane.showMessageDialog(null, "Sửa thông tin phiếu nhập thành công!");
					hienThiToanBoPhieuNhap();
					hienThiToanBoMaPhieuNhapLenCombobox();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	protected void xuLyThemPhieuNhap() {
		PhieuNhapModel pn = new PhieuNhapModel();
		NhanVienService nvService = new NhanVienService();
		pn.setNgayNhap(sdf.format(dcNgayNhap.getDate()));
		//pn.setMaNV(nvService.layMaNhanVienTheoTen(cboNhanVien.getSelectedItem()+""));
		BanQuanLyUI ui = new BanQuanLyUI("");
		pn.setMaNV(ui.layMaNhanVienTheoTen(cboNhanVien.getSelectedIndex()));
		pn.setMaNCC(Integer.parseInt(tblNhaCungCap.getValueAt(cboNhaCungCap2.getSelectedIndex(), 0)+""));
		PhieuNhapService pnService = new PhieuNhapService();
		if(pnService.themPhieuNhap(pn) > 0)
		{
			JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thành công!");
			hienThiToanBoPhieuNhap();
			hienThiToanBoMaPhieuNhapLenCombobox();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Thêm phiếu nhập thất bại!");
		}
	}
	//xử lý lấy đường link của ảnh
	protected void xuLyThemAnh() {
		JFileChooser chooser = new JFileChooser();
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			try {
				File selectedFile = chooser.getSelectedFile();
				txtHinhAnh.setText(selectedFile.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	protected void xuLyTimKiemSanPham() {
		if(txtTimKiemSP.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm!");
			return;
		}
		else{
			SanPhamService spService = new SanPhamService();
			Vector<SanPhamModel>dssp = spService.timSanPhamTheoTen(txtTimKiemSP.getText());
			dtmSanPham.setRowCount(0);
			for(SanPhamModel sp : dssp)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(sp.getMaSP());
				vec.add(sp.getTenSP());
				vec.add(sp.getDonGia());
				vec.add(sp.getNgayCapNhat());
				vec.add(sp.getMoTa());
				vec.add(sp.getHinhAnh());
				vec.add(sp.getSLTon());
				dtmSanPham.addRow(vec);
			}
		}
	}
	protected void xuLyXoaSanPham() {
		int rowSelected = tblSanPham.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm cần xoá!");
			return;
		}
		else{
			int spSelected = Integer.parseInt(tblSanPham.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá sản phẩm "+txtTenSP.getText()+" không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					SanPhamService spService = new SanPhamService();
					if(spService.xoaSanPham(spSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá sản phẩm thành công!");
						hienThiToanBoSanPham();
						hienThiToanBoSanPhamLenCombobox();
						txtTenSP.setText("");
						txtDonGia.setText("");
						txtMoTa.setText("");
						txtHinhAnh.setText("");
						txtSoLuongTon.setText("");
						txtTenSP.requestFocus();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}

	protected void xuLySuaSanPham() {
		int rowSelected = tblSanPham.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm cần sửa!");
			return;
		}
		else {
			if(txtTenSP.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Không được để trống tên sản phẩm!");
				return;
			}
			else{
				if(txtDonGia.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Không được để trống đơn giá!");
					return;
				}
				else{
					if(txtMoTa.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Không được để trống mô tả");
						return;
					}
					else{
						if(txtHinhAnh.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Không được để trống hình ảnh!");
							return;
						}
						else{
							if(txtSoLuongTon.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Không được để trống số lượng tồn!");
								return;
							}
							else{
								int spSelected = Integer.parseInt(tblSanPham.getValueAt(rowSelected, 0)+"");
								try {
									SanPhamService spService = new SanPhamService();
									if(spService.suaSanPham(spSelected, txtTenSP.getText(), Integer.parseInt(txtDonGia.getText()), 
											sdf.format(dcNgayCapNhat.getDate()), txtMoTa.getText(), txtHinhAnh.getText(), 
											Integer.parseInt(txtSoLuongTon.getText())) > 0){
										JOptionPane.showMessageDialog(null, "Sửa thông tin sản phẩm thành công!");
										hienThiToanBoSanPham();
										hienThiToanBoSanPhamLenCombobox();
										txtTenSP.setText("");
										txtDonGia.setText("");
										txtMoTa.setText("");
										txtHinhAnh.setText("");
										txtSoLuongTon.setText("");
										txtTenSP.requestFocus();
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
	protected void xuLyThemSanPham() {
		if(txtTenSP.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm!");
			return;
		}
		else{
			if(txtDonGia.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đơn giá!");
				return;
			}
			else{

				if(txtMoTa.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mô tả sản phẩm!");
					return;
				}
				else{
					if(txtHinhAnh.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Vui lòng chọn ảnh cho sản phẩm!");
						return;
					}
					else{
						SanPhamModel sp = new SanPhamModel();
						sp.setTenSP(txtTenSP.getText());
						sp.setDonGia(Integer.parseInt(txtDonGia.getText()));
						sp.setNgayCapNhat(sdf.format(dcNgayCapNhat.getDate()));
						sp.setMoTa(txtMoTa.getText());
						sp.setHinhAnh(txtHinhAnh.getText());
						sp.setSLTon(0);
						//phải có
						sp.setMaNSX(Integer.parseInt(tblNhaSanXuat.getValueAt(cboNhaSanXuat.getSelectedIndex(), 0)+"")); 
						sp.setMaNCC(Integer.parseInt(tblNhaCungCap.getValueAt(cboNhaCungCap.getSelectedIndex(), 0)+""));
						sp.setMaLoaiSP(Integer.parseInt(tblLoaiSanPham.getValueAt(cboLoaiSP.getSelectedIndex(), 0)+""));
						SanPhamService spService = new SanPhamService();
						if(spService.themSanPham(sp) > 0)
						{
							JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
							hienThiToanBoSanPham();
							hienThiToanBoSanPhamLenCombobox();
							txtTenSP.setText("");
							txtDonGia.setText("");
							txtMoTa.setText("");
							txtHinhAnh.setText("");
							txtSoLuongTon.setText("");
							txtTenSP.requestFocus();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
						}
					}
				}
			}
		}
	}


	protected void xuLyTimKiemNhaCungCap() {
		if(txtTimKiemNCC.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm!");
		}
		else{
			NhaCungCapService nccService = new NhaCungCapService();
			Vector<NhaCungCapModel>dsncc = nccService.timNhaCungCapTheoTen(txtTimKiemNCC.getText());
			dtmNhaCungCap.setRowCount(0);
			for(NhaCungCapModel ncc : dsncc)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(ncc.getMaNCC());
				vec.add(ncc.getTenNCC());
				vec.add(ncc.getDiaChi());
				vec.add(ncc.getSDT());
				vec.add(ncc.getEmail());
				vec.add(ncc.getFax());
				dtmNhaCungCap.addRow(vec);
			}
		}
	}
	protected void xuLyXoaNhaCungCap() {
		int rowSelected = tblNhaCungCap.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhà cung cấp cần xoá!");
			return;
		}
		else{
			int nccSelected = Integer.parseInt(tblNhaCungCap.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá nhà cung cấp "+txtTenNCC.getText()+" không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					NhaCungCapService nccService = new NhaCungCapService();
					if(nccService.xoaNhaCungCap(nccSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá nhà cung cấp thành công!");
						txtTenNCC.setText("");
						txtDiaChi.setText("");
						txtSDT.setText("");
						txtEmail.setText("");
						txtFax.setText("");
						txtTenNCC.requestFocus();
						hienThiToanBoNhaCungCap();
						hienThiToanBoNhaCungCapLenCombobox();
						//hienThiToanBoNhaCungCapLenCombobox2();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaNhaCungCap() {
		int rowSelected = tblNhaCungCap.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhà cung cấp cần sửa!");
			return;
		}
		else {
			if(txtTenNCC.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Không được để trống tên!");
			}
			else{
				if(txtDiaChi.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Khôn được để trống địa chỉ!");
				}
				else{
					if(txtSDT.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Không được để trống số điện thoại!");
					}
					else{
						if(txtEmail.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Không được để trống email!");
						}
						else{
							if(txtFax.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Không được để trống số fax!");
							}
							else{
								int nccSelected = Integer.parseInt(tblNhaCungCap.getValueAt(rowSelected, 0)+"");
								try {
									NhaCungCapService nccService = new NhaCungCapService();
									if(nccService.suaNhaCungCap(nccSelected, txtTenNCC.getText(), txtDiaChi.getText(), 
											txtSDT.getText(), txtEmail.getText(), txtFax.getText()) > 0){
										JOptionPane.showMessageDialog(null, "Sửa thông tin nhà cung cấp thành công!");
										txtTenNCC.setText("");
										txtDiaChi.setText("");
										txtSDT.setText("");
										txtEmail.setText("");
										txtFax.setText("");
										txtTenNCC.requestFocus();
										hienThiToanBoNhaCungCap();
										hienThiToanBoNhaCungCapLenCombobox();
										//hienThiToanBoNhaCungCapLenCombobox2();
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
	//^[(]{1}09[2-8]{1}[)]{1}.\\d{3}.\\d{4}$"
	public boolean isPhone(String  inputPhone){
		String regex = "^0[2-9]{1}\\d{8}$";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(inputPhone);
		if(mat.find()){
			return true;
		}
		return false;
	}
	/*public boolean isFax(String inputFax){
		String regex = "(?<=Fax[:\\s])([+\\d\\s]+\\S)(?=\\s\\D)|";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(inputFax);
		if(mat.find()){
			return true;
		}
		return false;
	}*/
	protected void xuLyThemNhaCungCap() {
		if(txtTenNCC.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhà cung cấp!");
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
					if(txtEmail.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Vui lòng nhập email!");
						return;
					}
					else{
						if(txtFax.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Vui lòng nhập số fax!");
							return;
						}
						else{
							NhaCungCapModel ncc = new NhaCungCapModel();
							ncc.setTenNCC(txtTenNCC.getText());
							ncc.setDiaChi(txtDiaChi.getText());
							if(!isPhone(txtSDT.getText())){
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số điện thoại!");
								return;
							}
							else{
								ncc.setSDT(txtSDT.getText());
							}
							//xử lý nhập đúng định dạng email
							//sử dụng regular expression
							if(!isEmail(txtEmail.getText())){
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng email!");
								return;
							}
							else{
								ncc.setEmail(txtEmail.getText());
							}
							/*if(!isFax(txtFax.getText())){
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số fax!");
								return;
							}
							else{
								ncc.setFax(txtFax.getText());
							}*/
							ncc.setFax(txtFax.getText());
							NhaCungCapService nccService = new NhaCungCapService();
							if(nccService.themNhaCungCap(ncc) > 0)
							{
								JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công!");
								txtTenNCC.setText("");
								txtDiaChi.setText("");
								txtSDT.setText("");
								txtEmail.setText("");
								txtFax.setText("");
								txtTenNCC.requestFocus();
								hienThiToanBoNhaCungCap();
								hienThiToanBoNhaCungCapLenCombobox();
								//hienThiToanBoNhaCungCapLenCombobox2();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thất bại!");
							}
						}
					}
				}
			}
		}

	}
	protected void xuLyTimKiemNhaSanXuat() {
		if(txtTimKiemNhaSanXuat.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm!");
		}
		else{
			NhaSanXuatService nsxService = new NhaSanXuatService();
			Vector<NhaSanXuatModel>dsnsx = nsxService.timNhaSanXuatTheoTen(txtTimKiemNhaSanXuat.getText());
			dtmNhaSanXuat.setRowCount(0);
			for(NhaSanXuatModel nsx : dsnsx)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(nsx.getMaNSX());
				vec.add(nsx.getTenNSX());
				vec.add(nsx.getThongTin());
				dtmNhaSanXuat.addRow(vec);
			}
		}
	}
	protected void xuLyXoaNhaSanXuat() {
		int rowSelected = tblNhaSanXuat.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhà sản xuất cần xoá!");
			return;
		}
		else{
			int nsxSelected = Integer.parseInt(tblNhaSanXuat.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, 
					"Bạn có chắc chắn xoá nhà sản xuất "+txtTenNhaSanXuat.getText()+" không?",
					"Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					NhaSanXuatService nsxService = new NhaSanXuatService();
					if(nsxService.xoaNhaSanXuat(nsxSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá nhà sản xuất thành công!");
						txtTenNhaSanXuat.setText("");
						txtThongTinNSX.setText("");
						txtTenNhaSanXuat.requestFocus();
						hienThiToanBoNhaSanXuat();
						hienThiToanBoNhaSanXuatLenCombobox();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaNhaSanXuat() {
		int rowSelected = tblNhaSanXuat.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhà sản xuất cần sửa!");
			return;
		}
		else {
			if(txtTenNhaSanXuat.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Không được để trống tên!");
			}
			else{
				if(txtThongTinNSX.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Không được để trống thông tin!");
				}
				else{
					int nsxSelected = Integer.parseInt(tblNhaSanXuat.getValueAt(rowSelected, 0)+"");
					try {
						NhaSanXuatService nsxService = new NhaSanXuatService();
						if(nsxService.suaNhaSanXuat(nsxSelected, txtTenNhaSanXuat.getText(), txtThongTinNSX.getText()) > 0){
							JOptionPane.showMessageDialog(null, "Sửa thông tin nhà sản xuất thành công!");
							txtTenNhaSanXuat.setText("");
							txtThongTinNSX.setText("");
							txtTenNhaSanXuat.requestFocus();
							hienThiToanBoNhaSanXuat();
							hienThiToanBoNhaSanXuatLenCombobox();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}	
		}
	}
	protected void xuLyThemNhaSanXuat() {
		if(txtTenNhaSanXuat.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhà sản xuất!");
		}
		else{
			if(txtThongTinNSX.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin nhà sản xuất!");
			}
			else{
				NhaSanXuatModel nsx = new NhaSanXuatModel();
				nsx.setTenNSX(txtTenNhaSanXuat.getText());
				nsx.setThongTin(txtThongTinNSX.getText());
				NhaSanXuatService nsxService = new NhaSanXuatService();
				if(nsxService.themNhaSanXuat(nsx) > 0)
				{
					JOptionPane.showMessageDialog(null, "Thêm nhà sản xuất thành công!");
					txtTenNhaSanXuat.setText("");
					txtThongTinNSX.setText("");
					txtTenNhaSanXuat.requestFocus();
					hienThiToanBoNhaSanXuat();
					hienThiToanBoNhaSanXuatLenCombobox();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Thêm nhà sản xuất thất bại!");
				}
			}
		}
	}
	private void hienThiToanBoLoaiSanPhamLenCombobox() {
		LoaiSanPhamService lspService = new LoaiSanPhamService();
		Vector<LoaiSanPhamModel>vec=lspService.docToanBoLoaiSanPham();
		cboLoaiSP.removeAllItems();
		for(LoaiSanPhamModel lsp : vec)
		{
			cboLoaiSP.addItem(lsp);
		}
	}
	private void hienThiToanBoLoaiSanPham() {
		LoaiSanPhamService lspService=new LoaiSanPhamService();
		dsLoaiSP = lspService.docToanBoLoaiSanPham();
		dtmLoaiSanPham.setRowCount(0);
		for(LoaiSanPhamModel lsp : dsLoaiSP)
		{
			Vector<Object>vec=new Vector<Object>();
			vec.add(lsp.getMaLoaiSP());
			vec.add(lsp.getTenLoaiSP());
			dtmLoaiSanPham.addRow(vec);
		}
	}
	public void addControls(){
		mnuBar=new JMenuBar();
		setJMenuBar(mnuBar);
		mnuHeThong=new JMenu("Hệ Thống");
		mnuBar.add(mnuHeThong);
		mnuThongTin = new JMenuItem("Thông Tin");
		mnuThongTin.setIcon(new ImageIcon("images/info.png"));
		mnuHeThong.add(mnuThongTin);
		mnuHeThong.addSeparator();
		mnuDangXuat = new JMenuItem("Đăng Xuất");
		mnuDangXuat.setIcon(new ImageIcon("images/logout2.png"));
		mnuHeThong.add(mnuDangXuat);
		mnuHeThong.addSeparator();
		mnuThoat = new JMenuItem("Thoát");
		mnuThoat.setIcon(new ImageIcon("images/exit3.png"));
		mnuHeThong.add(mnuThoat);
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		tooBar=new JToolBar();
		tooBar.setBackground(Color.LIGHT_GRAY);
		btnThongTin=new JButton("Thông Tin");
		btnThongTin.setBackground(Color.WHITE);
		btnThongTin.setIcon(new ImageIcon("images/info.png"));
		btnThongTin.setFont(new Font("", Font.BOLD, 12));
		btnDangXuat=new JButton("Đăng Xuất");
		btnDangXuat.setBackground(Color.WHITE);
		btnDangXuat.setIcon(new ImageIcon("images/logout2.png"));
		btnDangXuat.setFont(new Font("", Font.BOLD, 12));
		btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.WHITE);
		btnThoat.setIcon(new ImageIcon("images/exit3.png"));
		btnThoat.setFont(new Font("", Font.BOLD, 12));
		//btnTroVe = new JButton("Trở Về");
		//btnTroVe.setFont(new Font("", Font.BOLD, 12));
		//btnTroVe.setBackground(Color.WHITE);
		//btnTroVe.setIcon(new ImageIcon("images/return.png"));
		//tooBar.add(btnTroVe);
		tooBar.add(btnThongTin);tooBar.add(btnDangXuat);
		tooBar.add(btnThoat);
		con.add(tooBar,BorderLayout.NORTH);
		tab=new JTabbedPane();
		con.add(tab);
		pnTab1 = new JPanel();
		pnTab2 = new JPanel();
		pnTab3 = new JPanel();
		tab.add(pnTab1,"Sản Phẩm");
		tab.add(pnTab2, "Phiếu nhập");
		tab.add(pnTab3,"Nguồn cung ứng");

		//giao diện Tab 1 - Sản phẩm & loại sp
		pnTab1.setLayout(new BorderLayout());
		JPanel pnLeft=new JPanel();
		pnLeft.setPreferredSize(new Dimension(500, 0));
		JPanel pnRight=new JPanel();
		JSplitPane sp=new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, pnLeft,pnRight);
		sp.setOneTouchExpandable(true);
		pnTab1.add(sp,BorderLayout.CENTER);

		//giao diện pnLeft
		pnLeft.setLayout(new BorderLayout());
		JPanel pnTopOfLeft=new JPanel();
		TitledBorder borderLoaiSP=
				new TitledBorder(
						BorderFactory.createLineBorder(Color.RED),
						"Thông tin loại sản phẩm");
		borderLoaiSP.setTitleColor(Color.BLUE);
		borderLoaiSP.setTitleFont(new Font("", Font.BOLD, 15));
		pnTopOfLeft.setBorder(borderLoaiSP);
		pnTopOfLeft.setLayout(new BorderLayout());
		pnLeft.add(pnTopOfLeft,BorderLayout.CENTER);
		pnTopOfLeft.setPreferredSize(new Dimension(0, 300));

		dtmLoaiSanPham=new DefaultTableModel();
		dtmLoaiSanPham.addColumn("Mã loại sản phẩm");
		dtmLoaiSanPham.addColumn("Tên loại sản phẩm");
		tblLoaiSanPham=new JTable(dtmLoaiSanPham);
		tblLoaiSanPham.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableLoaiSP=new JScrollPane(tblLoaiSanPham,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfLeft.add(scTableLoaiSP,BorderLayout.CENTER);

		JPanel pnBottomOfLeft = new JPanel();
		pnBottomOfLeft.setLayout(new BoxLayout(pnBottomOfLeft, BoxLayout.Y_AXIS));
		pnLeft.add(pnBottomOfLeft,BorderLayout.SOUTH);
		
		JPanel pnHienThiToanBoLoaiSP = new JPanel();
		pnHienThiToanBoLoaiSP.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkHienThiToanBoLoaiSP = new JCheckBox("Hiển thị toàn bộ loại sản phẩm");
		chkHienThiToanBoLoaiSP.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBoLoaiSP.setForeground(Color.BLUE);
		pnHienThiToanBoLoaiSP.add(chkHienThiToanBoLoaiSP);
		pnBottomOfLeft.add(pnHienThiToanBoLoaiSP);

		JPanel pnTimKiemLoaiSP=new JPanel();
		pnTimKiemLoaiSP.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTimKiemLoaiSP=new JLabel("Nhập tên tìm kiếm: ");
		lblTimKiemLoaiSP.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemLoaiSP = new JTextField(20);
		btnTimKiemLoaiSP = new JButton("Tìm kiếm");
		btnTimKiemLoaiSP.setIcon(new ImageIcon("images/search2.png"));
		pnTimKiemLoaiSP.add(lblTimKiemLoaiSP);
		pnTimKiemLoaiSP.add(txtTimKiemLoaiSP);
		pnTimKiemLoaiSP.add(btnTimKiemLoaiSP);
		pnBottomOfLeft.add(pnTimKiemLoaiSP);

		JPanel pnTenLoaiSP = new JPanel();
		pnTenLoaiSP.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenLoaiSP = new JLabel("Tên loại sản phẩm: ");
		lblTenLoaiSP.setFont(new Font("", Font.PLAIN, 15));
		txtTenLoaiSP = new JTextField(30);
		pnTenLoaiSP.add(lblTenLoaiSP);
		pnTenLoaiSP.add(txtTenLoaiSP);
		pnBottomOfLeft.add(pnTenLoaiSP);

		JPanel pnControlsLeft = new JPanel();
		pnControlsLeft.setLayout(new FlowLayout());
		btnThemLoaiSP = new JButton("Thêm mới");
		btnThemLoaiSP.setIcon(new ImageIcon("images/new.png"));
		btnSuaLoaiSP = new JButton("Sửa thông tin");
		btnSuaLoaiSP.setIcon(new ImageIcon("images/edit.png"));
		btnXoaLoaiSP = new JButton("Xoá");
		btnXoaLoaiSP.setIcon(new ImageIcon("images/remove.png"));
		pnControlsLeft.add(btnThemLoaiSP);
		pnControlsLeft.add(btnSuaLoaiSP);
		pnControlsLeft.add(btnXoaLoaiSP);
		pnBottomOfLeft.add(pnControlsLeft);

		//xử lý giao diện pnRight
		pnRight.setLayout(new BorderLayout());
		JPanel pnTopOfRight=new JPanel();
		TitledBorder borderSanPham=
				new TitledBorder(
						BorderFactory.createLineBorder(Color.RED),
						"Thông tin sản phẩm");
		borderSanPham.setTitleColor(Color.BLUE);
		borderSanPham.setTitleFont(new Font("", Font.BOLD, 15));
		pnTopOfRight.setBorder(borderSanPham);
		pnTopOfRight.setLayout(new BorderLayout());
		pnRight.add(pnTopOfRight,BorderLayout.CENTER);

		dtmSanPham=new DefaultTableModel();
		dtmSanPham.addColumn("Mã sản phẩm");
		dtmSanPham.addColumn("Tên sản phẩm");
		dtmSanPham.addColumn("Đơn giá");
		dtmSanPham.addColumn("Ngày cập nhật");
		dtmSanPham.addColumn("Mô tả");
		dtmSanPham.addColumn("Hình ảnh");
		dtmSanPham.addColumn("Số lượng tồn");
		tblSanPham=new JTable(dtmSanPham);
		tblSanPham.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableSanPham=new JScrollPane(tblSanPham,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfRight.add(scTableSanPham,BorderLayout.CENTER);

		pnBottomOfRight = new JPanel();
		pnBottomOfRight.setLayout(new BorderLayout());
		pnRight.add(pnBottomOfRight,BorderLayout.SOUTH);

		JPanel pnLeftOfBottomOfRight = new JPanel();
		pnLeftOfBottomOfRight.setLayout(new BoxLayout(pnLeftOfBottomOfRight, BoxLayout.Y_AXIS));
		pnBottomOfRight.add(pnLeftOfBottomOfRight, BorderLayout.CENTER);

		JPanel pnHienThiToanBo = new JPanel();
		pnHienThiToanBo.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkHienThiToanBo = new JCheckBox("Hiển thị toàn bộ sản phẩm");
		chkHienThiToanBo.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBo.setForeground(Color.BLUE);
		pnHienThiToanBo.add(chkHienThiToanBo);
		pnLeftOfBottomOfRight.add(pnHienThiToanBo);

		JPanel pnTimKiemSP = new JPanel();
		pnTimKiemSP.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTimKiemSP = new JLabel("Nhập dữ liệu tìm kiếm: ");
		lblTimKiemSP.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemSP = new JTextField(20);
		btnTimKiemSP = new JButton("Tìm kiếm");
		btnTimKiemSP.setIcon(new ImageIcon("images/search3.png"));
		pnTimKiemSP.add(lblTimKiemSP);
		pnTimKiemSP.add(txtTimKiemSP);
		pnTimKiemSP.add(btnTimKiemSP);
		pnLeftOfBottomOfRight.add(pnTimKiemSP);

		JPanel pnComboLoaiSP = new JPanel();
		pnComboLoaiSP.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboLoaiSP = new JLabel("Loại sản phẩm: ");
		lblComboLoaiSP.setFont(new Font("", Font.PLAIN, 15));
		cboLoaiSP = new JComboBox<LoaiSanPhamModel>();
		cboLoaiSP.setPreferredSize(new Dimension(340, 20));
		pnComboLoaiSP.add(lblComboLoaiSP);
		pnComboLoaiSP.add(cboLoaiSP);
		pnLeftOfBottomOfRight.add(pnComboLoaiSP);

		JPanel pnComboNhaCungCap = new JPanel();
		pnComboNhaCungCap.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboNhaCungCap = new JLabel("Nhà cung cấp: ");
		lblComboNhaCungCap.setFont(new Font("", Font.PLAIN, 15));
		cboNhaCungCap = new JComboBox<NhaCungCapModel>();
		cboNhaCungCap.setPreferredSize(new Dimension(340, 20));
		pnComboNhaCungCap.add(lblComboNhaCungCap);
		pnComboNhaCungCap.add(cboNhaCungCap);
		pnLeftOfBottomOfRight.add(pnComboNhaCungCap);

		JPanel pnComboNhaSanXuat = new JPanel();
		pnComboNhaSanXuat.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboNhaSanXuat = new JLabel("Nhà sản xuất: ");
		lblComboNhaSanXuat.setFont(new Font("", Font.PLAIN, 15));
		cboNhaSanXuat = new JComboBox<NhaSanXuatModel>();
		cboNhaSanXuat.setPreferredSize(new Dimension(340, 20));
		pnComboNhaSanXuat.add(lblComboNhaSanXuat);
		pnComboNhaSanXuat.add(cboNhaSanXuat);
		pnLeftOfBottomOfRight.add(pnComboNhaSanXuat);

		JPanel pnTenSP = new JPanel();
		pnTenSP.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenSP = new JLabel("Tên sản phẩm: ");
		lblTenSP.setFont(new Font("", Font.PLAIN, 15));
		txtTenSP = new JTextField(30);
		pnTenSP.add(lblTenSP);
		pnTenSP.add(txtTenSP);
		pnLeftOfBottomOfRight.add(pnTenSP);

		JPanel pnDonGia = new JPanel();
		pnDonGia.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDonGia = new JLabel("Đơn giá: ");
		lblDonGia.setFont(new Font("", Font.PLAIN, 15));
		txtDonGia = new JTextField(30);
		pnDonGia.add(lblDonGia);
		pnDonGia.add(txtDonGia);
		pnLeftOfBottomOfRight.add(pnDonGia);

		JPanel pnNgayCapNhat = new JPanel();
		pnNgayCapNhat.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgayCapNhat = new JLabel("Ngày cập nhật: ");
		lblNgayCapNhat.setFont(new Font("", Font.PLAIN, 15));
		dcNgayCapNhat = new JDateChooser();
		dcNgayCapNhat.setPreferredSize(new Dimension(340, 20));
		pnNgayCapNhat.add(lblNgayCapNhat);
		pnNgayCapNhat.add(dcNgayCapNhat);
		pnLeftOfBottomOfRight.add(pnNgayCapNhat);

		JPanel pnMoTa = new JPanel();
		pnMoTa.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMoTa = new JLabel("Mô tả: ");
		lblMoTa.setFont(new Font("", Font.PLAIN, 15));
		txtMoTa=new JTextArea(10, 29);
		txtMoTa.setWrapStyleWord(true);
		txtMoTa.setLineWrap(true);
		JScrollPane scMoTa=new JScrollPane(txtMoTa,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnMoTa.add(lblMoTa);
		pnMoTa.add(scMoTa);
		pnLeftOfBottomOfRight.add(pnMoTa);

		JPanel pnHinhAnh = new JPanel();
		pnHinhAnh.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblHinhAnh = new JLabel("Hình ảnh: ");
		lblHinhAnh.setFont(new Font("", Font.PLAIN, 15));
		txtHinhAnh = new JTextField(20);
		btnThemAnh = new JButton("Thêm ảnh");
		btnThemAnh.setIcon(new ImageIcon("images/them.png"));
		pnHinhAnh.add(lblHinhAnh);
		pnHinhAnh.add(txtHinhAnh);
		pnHinhAnh.add(btnThemAnh);
		pnLeftOfBottomOfRight.add(pnHinhAnh);

		JPanel pnSoLuongTon = new JPanel();
		pnSoLuongTon.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSoLuongTon = new JLabel("Số lượng tồn: ");
		lblSoLuongTon.setFont(new Font("", Font.PLAIN, 15));
		txtSoLuongTon = new JTextField(30);
		//txtSoLuongTon.disable();
		pnSoLuongTon.add(lblSoLuongTon);
		pnSoLuongTon.add(txtSoLuongTon);
		pnLeftOfBottomOfRight.add(pnSoLuongTon);

		//canh chỉnh
		lblTenSP.setPreferredSize(lblTimKiemSP.getPreferredSize());
		lblDonGia.setPreferredSize(lblTimKiemSP.getPreferredSize());
		lblNgayCapNhat.setPreferredSize(lblTimKiemSP.getPreferredSize());
		lblHinhAnh.setPreferredSize(lblTimKiemSP.getPreferredSize());
		lblSoLuongTon.setPreferredSize(lblTimKiemSP.getPreferredSize());
		lblMoTa.setPreferredSize(lblTimKiemSP.getPreferredSize());
		lblComboLoaiSP.setPreferredSize(lblTimKiemSP.getPreferredSize());
		lblComboNhaCungCap.setPreferredSize(lblTimKiemSP.getPreferredSize());
		lblComboNhaSanXuat.setPreferredSize(lblTimKiemSP.getPreferredSize());

		JPanel pnControlsRight = new JPanel();
		pnControlsRight.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnThemSP = new JButton("Thêm mới");
		btnThemSP.setIcon(new ImageIcon("images/new.png"));
		btnSuaSP = new JButton("Sửa thông tin");
		btnSuaSP.setIcon(new ImageIcon("images/edit.png"));
		btnXoaSP = new JButton("Xoá");
		btnXoaSP.setIcon(new ImageIcon("images/remove.png"));
		pnControlsRight.add(btnThemSP);
		pnControlsRight.add(btnSuaSP);
		pnControlsRight.add(btnXoaSP);
		pnLeftOfBottomOfRight.add(pnControlsRight);


		//giao diện Tab2 - Phiếu nhập & chi tiết phiếu nhập
		//giao diện pnLeft
		pnTab2.setLayout(new BorderLayout());
		JPanel pnLeft2 = new JPanel();
		pnLeft2.setPreferredSize(new Dimension(750, 0));
		JPanel pnRight2 = new JPanel();
		JSplitPane sp2 = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, pnLeft2, pnRight2);
		sp2.setOneTouchExpandable(true);
		pnTab2.add(sp2,BorderLayout.CENTER);

		//giao diện pnLeft
		pnLeft2.setLayout(new BorderLayout());
		JPanel pnTopOfLeft2 = new JPanel();
		TitledBorder borderPhieuNhap = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED),
				"Thông tin phiếu nhập");
		borderPhieuNhap.setTitleColor(Color.BLUE);
		borderPhieuNhap.setTitleFont(new Font("", Font.BOLD, 15));
		pnTopOfLeft2.setBorder(borderPhieuNhap);
		pnTopOfLeft2.setLayout(new BorderLayout());
		pnLeft2.add(pnTopOfLeft2,BorderLayout.CENTER);
		pnTopOfLeft2.setPreferredSize(new Dimension(0, 300));

		dtmPhieuNhap = new DefaultTableModel();
		dtmPhieuNhap.addColumn("Mã phiếu nhập");
		dtmPhieuNhap.addColumn("Ngày nhập");
		dtmPhieuNhap.addColumn("Nhân viên lập phiếu");
		dtmPhieuNhap.addColumn("Nhà cung cấp");
		tblPhieuNhap = new JTable(dtmPhieuNhap);
		tblPhieuNhap.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTablePhieuNhap = new JScrollPane(tblPhieuNhap,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfLeft2.add(scTablePhieuNhap,BorderLayout.CENTER);

		JPanel pnBottomOfLeft2 = new JPanel();
		pnBottomOfLeft2.setLayout(new BoxLayout(pnBottomOfLeft2, BoxLayout.Y_AXIS));
		pnLeft2.add(pnBottomOfLeft2,BorderLayout.SOUTH);
		
		JPanel pnHienThiToanBoPhieuNhap = new JPanel();
		pnHienThiToanBoPhieuNhap.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkHienThiToanBoPhieuNhap = new JCheckBox("Hiển thị toàn bộ phiếu nhập");
		chkHienThiToanBoPhieuNhap.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBoPhieuNhap.setForeground(Color.BLUE);
		pnHienThiToanBoPhieuNhap.add(chkHienThiToanBoPhieuNhap);
		pnBottomOfLeft2.add(pnHienThiToanBoPhieuNhap);

		JPanel pnTimKiemPhieuNhap = new JPanel();
		pnTimKiemPhieuNhap.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTimKiemPhieuNhap = new JLabel("Nhập dữ liệu tìm kiếm: ");
		lblTimKiemPhieuNhap.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemPhieuNhap = new JTextField(40);
		btnTimKiemPhieuNhap = new JButton("Tìm kiếm");
		btnTimKiemPhieuNhap.setIcon(new ImageIcon("images/search6.png"));
		pnTimKiemPhieuNhap.add(lblTimKiemPhieuNhap);
		pnTimKiemPhieuNhap.add(txtTimKiemPhieuNhap);
		pnTimKiemPhieuNhap.add(btnTimKiemPhieuNhap);
		pnBottomOfLeft2.add(pnTimKiemPhieuNhap);

		JPanel pnComboNhanVien= new JPanel();
		pnComboNhanVien.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboNhanVien = new JLabel("Nhân viên lập: ");
		lblComboNhanVien.setFont(new Font("", Font.PLAIN, 15));
		cboNhanVien = new JComboBox<HienThiToanBoNhanVienModel>();
		cboNhanVien.setPreferredSize(new Dimension(550, 20));
		pnComboNhanVien.add(lblComboNhanVien);
		pnComboNhanVien.add(cboNhanVien);
		pnBottomOfLeft2.add(pnComboNhanVien);

		JPanel pnComboNhaCungCap2 = new JPanel();
		pnComboNhaCungCap2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboNhaCungCap2 = new JLabel("Nhà cung cấp: ");
		lblComboNhaCungCap2.setFont(new Font("", Font.PLAIN, 15));
		cboNhaCungCap2 = new JComboBox<NhaCungCapModel>();
		cboNhaCungCap2.setPreferredSize(new Dimension(550, 20));
		/*btnReloadNCC = new JButton();
		btnReloadNCC.setIcon(new ImageIcon("images/reload4.png"));
		btnReloadNCC.setBackground(Color.WHITE);*/
		pnComboNhaCungCap2.add(lblComboNhaCungCap2);
		pnComboNhaCungCap2.add(cboNhaCungCap2);
		//pnComboNhaCungCap2.add(btnReloadNCC);
		pnBottomOfLeft2.add(pnComboNhaCungCap2);

		JPanel pnNgayNhap = new JPanel();
		pnNgayNhap.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgayNhap = new JLabel("Ngày nhập: ");
		lblNgayNhap.setFont(new Font("", Font.PLAIN, 15));
		dcNgayNhap = new JDateChooser();
		dcNgayNhap.setPreferredSize(new Dimension(550, 20));
		pnNgayNhap.add(lblNgayNhap);
		pnNgayNhap.add(dcNgayNhap);
		pnBottomOfLeft2.add(pnNgayNhap);

		JPanel pnControlsLeft2 = new JPanel();
		pnControlsLeft2.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThemPhieuNhap = new JButton("Thêm mới");
		btnThemPhieuNhap.setIcon(new ImageIcon("images/new4.png"));
		btnSuaPhieuNhap = new JButton("Sửa thông tin");
		btnSuaPhieuNhap.setIcon(new ImageIcon("images/edit4.png"));
		btnXoaPhieuNhap = new JButton("Xoá");
		btnXoaPhieuNhap.setIcon(new ImageIcon("images/remove4.png"));
		pnControlsLeft2.add(btnThemPhieuNhap);
		pnControlsLeft2.add(btnSuaPhieuNhap);
		pnControlsLeft2.add(btnXoaPhieuNhap);
		pnBottomOfLeft2.add(pnControlsLeft2);

		//canh chỉnh
		lblComboNhaCungCap2.setPreferredSize(lblTimKiemPhieuNhap.getPreferredSize());
		lblNgayNhap.setPreferredSize(lblTimKiemPhieuNhap.getPreferredSize());
		lblComboNhanVien.setPreferredSize(lblTimKiemPhieuNhap.getPreferredSize());

		//giao diện pnRight
		pnRight2.setLayout(new BorderLayout());
		JPanel pnTopOfRight2 = new JPanel();
		TitledBorder borderChiTietPhieuNhap = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED),
				"Thông tin chi tiết phiếu nhập");
		borderChiTietPhieuNhap.setTitleColor(Color.BLUE);
		borderChiTietPhieuNhap.setTitleFont(new Font("", Font.BOLD, 15));
		pnTopOfRight2.setBorder(borderChiTietPhieuNhap);
		pnTopOfRight2.setLayout(new BorderLayout());
		pnRight2.add(pnTopOfRight2,BorderLayout.CENTER);

		dtmChiTietPhieuNhap = new DefaultTableModel();
		dtmChiTietPhieuNhap.addColumn("Mã chi tiết phiếu nhập");
		dtmChiTietPhieuNhap.addColumn("Mã phiếu nhập");
		dtmChiTietPhieuNhap.addColumn("Sản phẩm");
		dtmChiTietPhieuNhap.addColumn("Số lượng");
		tblChiTietPhieuNhap = new JTable(dtmChiTietPhieuNhap);
		tblChiTietPhieuNhap.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableCTPN = new JScrollPane(
				tblChiTietPhieuNhap,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfRight2.add(scTableCTPN,BorderLayout.CENTER);

		JPanel pnBottomOfRight2 = new JPanel();
		pnBottomOfRight2.setLayout(new BoxLayout(pnBottomOfRight2, BoxLayout.Y_AXIS));
		pnRight2.add(pnBottomOfRight2,BorderLayout.SOUTH);
		
		JPanel pnHienThiToanBoCTPN = new JPanel();
		pnHienThiToanBoCTPN.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkHienThiToanBoCTPN = new JCheckBox("Hiển thị toàn bộ chi tiết phiếu nhập");
		chkHienThiToanBoCTPN.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBoCTPN.setForeground(Color.BLUE);
		pnHienThiToanBoCTPN.add(chkHienThiToanBoCTPN);
		pnBottomOfRight2.add(pnHienThiToanBoCTPN);

		JPanel pnTimKiemCTPN = new JPanel();
		pnTimKiemCTPN.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTimKiemCTPN = new JLabel("Nhập dữ liệu tìm kiếm: ");
		lblTimKiemCTPN.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemCTPN = new JTextField(30);
		btnTimKiemCTPN = new JButton("Tìm kiếm");
		btnTimKiemCTPN.setIcon(new ImageIcon("images/search5.png"));
		pnTimKiemCTPN.add(lblTimKiemCTPN);
		pnTimKiemCTPN.add(txtTimKiemCTPN);
		pnTimKiemCTPN.add(btnTimKiemCTPN);
		pnBottomOfRight2.add(pnTimKiemCTPN);

		JPanel pnComboPhieuNhap = new JPanel();
		pnComboPhieuNhap.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboPhieuNhap = new JLabel("Mã phiếu nhập: ");
		lblComboPhieuNhap.setFont(new Font("", Font.PLAIN, 15));
		cboPhieuNhap = new JComboBox<HienThiToanBoPhieuNhapModel>();
		cboPhieuNhap.setPreferredSize(new Dimension(440, 20));
		pnComboPhieuNhap.add(lblComboPhieuNhap);
		pnComboPhieuNhap.add(cboPhieuNhap);
		pnBottomOfRight2.add(pnComboPhieuNhap);

		JPanel pnComboSanPham = new JPanel();
		pnComboSanPham.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblComboSanPham = new JLabel("Sản phẩm: ");
		lblComboSanPham.setFont(new Font("", Font.PLAIN, 15));
		cboSanPham = new JComboBox<SanPhamModel>();
		cboSanPham.setPreferredSize(new Dimension(440, 20));
		pnComboSanPham.add(lblComboSanPham);
		pnComboSanPham.add(cboSanPham);
		pnBottomOfRight2.add(pnComboSanPham);

		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSoLuong = new JLabel("Số lượng: ");
		lblSoLuong.setFont(new Font("", Font.PLAIN, 15));
		txtSoLuong = new JTextField(40);
		pnSoLuong.add(lblSoLuong);
		pnSoLuong.add(txtSoLuong);
		pnBottomOfRight2.add(pnSoLuong);

		JPanel pnControlsRight2 = new JPanel();
		pnControlsRight2.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThemCTPN = new JButton("Thêm mới");
		btnThemCTPN.setIcon(new ImageIcon("images/new5.png"));
		btnSuaCTPN = new JButton("Sửa thông tin");
		btnSuaCTPN.setIcon(new ImageIcon("images/edit5.png"));
		btnXoaCTPN = new JButton("Xoá");
		btnXoaCTPN.setIcon(new ImageIcon("images/remove5.png"));
		pnControlsRight2.add(btnThemCTPN);
		pnControlsRight2.add(btnSuaCTPN);
		pnControlsRight2.add(btnXoaCTPN);
		pnBottomOfRight2.add(pnControlsRight2);

		//canh chỉnh
		lblComboSanPham.setPreferredSize(lblTimKiemCTPN.getPreferredSize());
		lblSoLuong.setPreferredSize(lblTimKiemCTPN.getPreferredSize());
		lblComboPhieuNhap.setPreferredSize(lblTimKiemCTPN.getPreferredSize());


		//giao diện Tab3 - Nguồn cung ứng
		//giao diện pnLeft
		pnTab3.setLayout(new BorderLayout());
		JPanel pnLeft3 = new JPanel();
		pnLeft3.setPreferredSize(new Dimension(600, 0));
		JPanel pnRight3 = new JPanel();
		JSplitPane sp3 = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, pnLeft3,pnRight3);
		sp3.setOneTouchExpandable(true);
		pnTab3.add(sp3,BorderLayout.CENTER);

		pnLeft3.setLayout(new BorderLayout());
		JPanel pnTopOfLeft3 = new JPanel();
		TitledBorder borderNhaSanXuat = new TitledBorder
				(BorderFactory.createLineBorder(Color.RED),"Thông tin nhà sản xuất");
		borderNhaSanXuat.setTitleColor(Color.BLUE);
		borderNhaSanXuat.setTitleFont(new Font("", Font.BOLD, 15));
		pnTopOfLeft3.setBorder(borderNhaSanXuat);
		pnTopOfLeft3.setLayout(new BorderLayout());
		pnLeft3.add(pnTopOfLeft3,BorderLayout.CENTER);
		pnTopOfLeft3.setPreferredSize(new Dimension(0, 300));

		dtmNhaSanXuat = new DefaultTableModel();
		dtmNhaSanXuat.addColumn("Mã nhà sản xuất");
		dtmNhaSanXuat.addColumn("Tên nhà sản xuất");
		dtmNhaSanXuat.addColumn("Thông tin");
		tblNhaSanXuat = new JTable(dtmNhaSanXuat);
		tblNhaSanXuat.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableNhaSanXuat = new JScrollPane
				(tblNhaSanXuat,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfLeft3.add(scTableNhaSanXuat,BorderLayout.CENTER);

		JPanel pnBottomOfLeft3 = new JPanel();
		pnBottomOfLeft3.setLayout(new BoxLayout(pnBottomOfLeft3, BoxLayout.Y_AXIS));
		pnLeft3.add(pnBottomOfLeft3,BorderLayout.SOUTH);

		JPanel pnHienThiToanBoNSX = new JPanel();
		pnHienThiToanBoNSX.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkHienThiToanBoNSX = new JCheckBox("Hiển thị toàn bộ nhà sản xuất");
		chkHienThiToanBoNSX.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBoNSX.setForeground(Color.BLUE);
		pnHienThiToanBoNSX.add(chkHienThiToanBoNSX);
		pnBottomOfLeft3.add(pnHienThiToanBoNSX);

		JPanel pnTimKiemNhaSanXuat = new JPanel();
		pnTimKiemNhaSanXuat.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTimKiemNhaSanXuat = new JLabel("Nhập tên tìm kiếm: ");
		lblTimKiemNhaSanXuat.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemNhaSanXuat = new JTextField(30);
		btnTimKiemNhaSanXuat = new JButton("Tìm kiếm");
		btnTimKiemNhaSanXuat.setIcon(new ImageIcon("images/search4.png"));
		pnTimKiemNhaSanXuat.add(lblTimKiemNhaSanXuat);
		pnTimKiemNhaSanXuat.add(txtTimKiemNhaSanXuat);
		pnTimKiemNhaSanXuat.add(btnTimKiemNhaSanXuat);
		pnBottomOfLeft3.add(pnTimKiemNhaSanXuat);

		JPanel pnTenNhaSanXuat = new JPanel();
		pnTenNhaSanXuat.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenNhaSanXuat = new JLabel("Tên nhà sản xuất: ");
		lblTenNhaSanXuat.setFont(new Font("", Font.PLAIN, 15));
		txtTenNhaSanXuat = new JTextField(40);
		pnTenNhaSanXuat.add(lblTenNhaSanXuat);
		pnTenNhaSanXuat.add(txtTenNhaSanXuat);
		pnBottomOfLeft3.add(pnTenNhaSanXuat);

		JPanel pnThongTinNhaSanXuat = new JPanel();
		pnThongTinNhaSanXuat.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblThongTinNSX = new JLabel("Thông tin: ");
		lblThongTinNSX.setFont(new Font("", Font.PLAIN, 15));
		txtThongTinNSX = new JTextArea(6, 39);
		txtThongTinNSX.setWrapStyleWord(true);
		txtThongTinNSX.setLineWrap(true);
		JScrollPane scThongTinNSX = new JScrollPane(txtThongTinNSX,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnThongTinNhaSanXuat.add(lblThongTinNSX);
		pnThongTinNhaSanXuat.add(scThongTinNSX);
		pnBottomOfLeft3.add(pnThongTinNhaSanXuat);

		JPanel pnControlsLeft3 = new JPanel();
		pnControlsLeft3.setLayout(new FlowLayout());
		btnThemNSX = new JButton("Thêm mới");
		btnThemNSX.setIcon(new ImageIcon("images/new2.png"));
		btnSuaNSX = new JButton("Sửa thông tin");
		btnSuaNSX.setIcon(new ImageIcon("images/edit2.png"));
		btnXoaNSX = new JButton("Xoá");
		btnXoaNSX.setIcon(new ImageIcon("images/remove2.png"));
		pnControlsLeft3.add(btnThemNSX);
		pnControlsLeft3.add(btnSuaNSX);
		pnControlsLeft3.add(btnXoaNSX);
		pnBottomOfLeft3.add(pnControlsLeft3);
		//canh chỉnh
		lblThongTinNSX.setPreferredSize(lblTimKiemNhaSanXuat.getPreferredSize());
		lblTenNhaSanXuat.setPreferredSize(lblTimKiemNhaSanXuat.getPreferredSize());

		//xử lý giao diện pnRight
		pnRight3.setLayout(new BorderLayout());
		JPanel pnTopOfRight3 = new JPanel();
		TitledBorder borderNhaCungCap = new TitledBorder(
				BorderFactory.createLineBorder(Color.RED),
				"Thông tin nhà cung cấp");
		borderNhaCungCap.setTitleColor(Color.BLUE);
		borderNhaCungCap.setTitleFont(new Font("", Font.BOLD, 15));
		pnTopOfRight3.setBorder(borderNhaCungCap);
		pnTopOfRight3.setLayout(new BorderLayout());
		pnRight3.add(pnTopOfRight3,BorderLayout.CENTER);

		dtmNhaCungCap = new DefaultTableModel();
		dtmNhaCungCap.addColumn("Mã nhà cung cấp");
		dtmNhaCungCap.addColumn("Tên nhà cung cấp");
		dtmNhaCungCap.addColumn("Địa chỉ");
		dtmNhaCungCap.addColumn("Số điện thoại");
		dtmNhaCungCap.addColumn("Email");
		dtmNhaCungCap.addColumn("Fax");
		tblNhaCungCap = new JTable(dtmNhaCungCap);
		tblNhaCungCap.setFont(new Font("", Font.ITALIC, 15));
		JScrollPane scTableNCC = new JScrollPane(
				tblNhaCungCap,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfRight3.add(scTableNCC,BorderLayout.CENTER);

		JPanel pnBottomOfRight3 = new JPanel();
		pnBottomOfRight3.setLayout(new BoxLayout(pnBottomOfRight3, BoxLayout.Y_AXIS));
		pnRight3.add(pnBottomOfRight3,BorderLayout.SOUTH);

		JPanel pnHienThiToanBoNCC = new JPanel();
		pnHienThiToanBoNCC.setLayout(new FlowLayout(FlowLayout.LEFT));
		chkHienThiToanBoNCC = new JCheckBox("Hiển thị toàn bộ nhà cung cấp");
		chkHienThiToanBoNCC.setFont(new Font("", Font.ITALIC, 15));
		chkHienThiToanBoNCC.setForeground(Color.BLUE);
		pnHienThiToanBoNCC.add(chkHienThiToanBoNCC);
		pnBottomOfRight3.add(pnHienThiToanBoNCC);

		JPanel pnTimKiemNCC = new JPanel();
		pnTimKiemNCC.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTimKiemNCC = new JLabel("Nhập dữ liệu tìm kiếm: ");
		lblTimKiemNCC.setFont(new Font("", Font.PLAIN, 15));
		txtTimKiemNCC = new JTextField(30);
		btnTimKiemNCC = new JButton("Tìm kiếm");
		btnTimKiemNCC.setIcon(new ImageIcon("images/search5.png"));
		pnTimKiemNCC.add(lblTimKiemNCC);
		pnTimKiemNCC.add(txtTimKiemNCC);
		pnTimKiemNCC.add(btnTimKiemNCC);
		pnBottomOfRight3.add(pnTimKiemNCC);

		JPanel pnTenNCC = new JPanel();
		pnTenNCC.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenNCC = new JLabel("Tên nhà cung cấp: ");
		lblTenNCC.setFont(new Font("", Font.PLAIN, 15));
		txtTenNCC = new JTextField(40);
		pnTenNCC.add(lblTenNCC);
		pnTenNCC.add(txtTenNCC);
		pnBottomOfRight3.add(pnTenNCC);

		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("", Font.PLAIN, 15));
		txtDiaChi = new JTextField(40);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnBottomOfRight3.add(pnDiaChi);

		JPanel pnSDT = new JPanel();
		pnSDT.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("", Font.PLAIN, 15));
		txtSDT = new JTextField(40);
		pnSDT.add(lblSDT);
		pnSDT.add(txtSDT);
		pnBottomOfRight3.add(pnSDT);

		JPanel pnEmail = new JPanel();
		pnEmail.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("", Font.PLAIN, 15));
		txtEmail = new JTextField(40);
		pnEmail.add(lblEmail);
		pnEmail.add(txtEmail);
		pnBottomOfRight3.add(pnEmail);

		JPanel pnFax = new JPanel();
		pnFax.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblFax = new JLabel("Số Fax: ");
		lblFax.setFont(new Font("", Font.PLAIN, 15));
		txtFax = new JTextField(40);
		pnFax.add(lblFax);
		pnFax.add(txtFax);
		pnBottomOfRight3.add(pnFax);

		//canh chỉnh
		lblTenNCC.setPreferredSize(lblTimKiemNCC.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTimKiemNCC.getPreferredSize());
		lblEmail.setPreferredSize(lblTimKiemNCC.getPreferredSize());
		lblSDT.setPreferredSize(lblTimKiemNCC.getPreferredSize());
		lblFax.setPreferredSize(lblTimKiemNCC.getPreferredSize());

		JPanel pnControlsRight3 = new JPanel();
		pnControlsRight3.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnThemNCC = new JButton("Thêm mới");
		btnThemNCC.setIcon(new ImageIcon("images/new3.png"));
		btnSuaNCC = new JButton("Sửa thông tin");
		btnSuaNCC.setIcon(new ImageIcon("images/edit3.png"));
		btnXoaNCC = new JButton("Xoá");
		btnXoaNCC.setIcon(new ImageIcon("images/remove3.png"));
		pnControlsRight3.add(btnThemNCC);
		pnControlsRight3.add(btnSuaNCC);
		pnControlsRight3.add(btnXoaNCC);
		pnBottomOfRight3.add(pnControlsRight3);
	}

	protected void xuLyTimLoaiSanPham() {
		if(txtTimKiemLoaiSP.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm!");
			return;
		}
		else{
			LoaiSanPhamService lspService = new LoaiSanPhamService();
			Vector<LoaiSanPhamModel>dslsp = lspService.timLoaiSanPhamTheoTen(txtTimKiemLoaiSP.getText());
			dtmLoaiSanPham.setRowCount(0);
			for(LoaiSanPhamModel lsp : dslsp)
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(lsp.getMaLoaiSP());
				vec.add(lsp.getTenLoaiSP());
				dtmLoaiSanPham.addRow(vec);
			}
		}
	}
	protected void xuLyXoaLoaiSanPham() {
		int rowSelected = tblLoaiSanPham.getSelectedRow();
		if(rowSelected == -1){
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn loại sản phẩm cần xoá!");
			return;
		}
		else{
			int lspSelected = Integer.parseInt(tblLoaiSanPham.getValueAt(rowSelected, 0)+"");
			int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xoá loại sản phẩm "+txtTenLoaiSP.getText()+" không?","Xác nhận xoá",JOptionPane.YES_NO_OPTION);
			if(ret == JOptionPane.NO_OPTION){
				return;
			}
			else{
				try{
					LoaiSanPhamService lspService = new LoaiSanPhamService();
					if(lspService.xoaLoaiSanPham(lspSelected) > 0){
						JOptionPane.showMessageDialog(null, "Xoá loại sản phẩm thành công!");
						txtTenLoaiSP.setText("");
						txtTenLoaiSP.requestFocus();
						hienThiToanBoLoaiSanPham();
						hienThiToanBoLoaiSanPhamLenCombobox();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	protected void xuLySuaLoaiSanPham() {
		int rowSelected = tblLoaiSanPham.getSelectedRow();
		if(rowSelected == -1) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn loại sản phẩm cần sửa!");
			return;
		}
		else {
			if(txtTenLoaiSP.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Không được để trống tên loại sản phảm!");
				return;
			}
			int lspSelected = Integer.parseInt(tblLoaiSanPham.getValueAt(rowSelected, 0)+"");
			try {
				LoaiSanPhamService lspService = new LoaiSanPhamService();
				if(lspService.suaLoaiSanPham(lspSelected, txtTenLoaiSP.getText()) > 0){
					JOptionPane.showMessageDialog(null, "Sửa thông tin loại sản phẩm thành công!");
					txtTenLoaiSP.setText("");
					txtTenLoaiSP.requestFocus();
					hienThiToanBoLoaiSanPham();
					hienThiToanBoLoaiSanPhamLenCombobox();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	protected void xuLyThemLoaiSanPham() {
		if(txtTenLoaiSP.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên loại sản phẩm!");
			return;
		}
		else{
			LoaiSanPhamModel lsp = new LoaiSanPhamModel();
			lsp.setTenLoaiSP(txtTenLoaiSP.getText());
			LoaiSanPhamService lspService = new LoaiSanPhamService();
			if(lspService.themLoaiSanPham(lsp) > 0)
			{
				JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thành công!");
				txtTenLoaiSP.setText("");
				txtTenLoaiSP.requestFocus();
				hienThiToanBoLoaiSanPham();
				hienThiToanBoLoaiSanPhamLenCombobox();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thất bại!");
			}
		}

	}
	public int layMaSanPhamTheoTen(int index){
		return Integer.parseInt(tblSanPham.getValueAt(index, 0)+"");
	}
	public int layDonGiaSanphamTheoTen(int index){
		return Integer.parseInt(tblSanPham.getValueAt(index, 2)+"");
	}
	public void showWindows(){
		this.setSize(1550, 950);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
