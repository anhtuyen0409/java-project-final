package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.NhanVienModel;

public class DanhSachChucNangUI extends JFrame{
	JButton btnBoPhanQuanLyKho, btnBoPhanBanHang, btnBanQuanLy, btnDangXuat, btnThoat;
	public DanhSachChucNangUI(String title){
		super(title);
		addControls();
		addEvents();
	}
	public void addControls(){
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnNorth = new JPanel();
		JLabel lblTieuDe = new JLabel("Hệ Thống Quản Lý Cửa Hàng Điện Thoại TMD");
		lblTieuDe.setFont(new Font("tahoma", Font.BOLD, 30));
		lblTieuDe.setForeground(Color.BLUE);
		pnNorth.add(lblTieuDe);
		con.add(pnNorth, BorderLayout.NORTH);
		
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		btnBoPhanQuanLyKho = new JButton();
		btnBoPhanQuanLyKho.setPreferredSize(new  Dimension(190, 175));
		btnBoPhanQuanLyKho.setBackground(Color.WHITE);
		btnBoPhanQuanLyKho.setLayout(new BorderLayout());
		JPanel pnQuanLyKho = new JPanel();
		pnQuanLyKho.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnQuanLyKho.setBackground(Color.WHITE);
		JLabel lblQuanLyKho = new JLabel("Bộ Phận Quản Lý Kho");
		lblQuanLyKho.setFont(new Font("", Font.BOLD, 15));
		pnQuanLyKho.add(lblQuanLyKho);
		btnBoPhanQuanLyKho.add(pnQuanLyKho, BorderLayout.SOUTH);
		JLabel lblIcon = new JLabel(new ImageIcon("images/user2.png"));
		btnBoPhanQuanLyKho.add(lblIcon,BorderLayout.CENTER);
		pnButton.add(btnBoPhanQuanLyKho);
		
		btnBoPhanBanHang = new JButton();
		btnBoPhanBanHang.setPreferredSize(new  Dimension(190, 175));
		btnBoPhanBanHang.setBackground(Color.WHITE);
		btnBoPhanBanHang.setLayout(new BorderLayout());
		JPanel pnBoPhanBanHang = new JPanel();
		pnBoPhanBanHang.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnBoPhanBanHang.setBackground(Color.WHITE);
		JLabel lblBanHang = new JLabel("Bộ Phận Bán Hàng");
		lblBanHang.setFont(new Font("", Font.BOLD, 15));
		pnBoPhanBanHang.add(lblBanHang);
		btnBoPhanBanHang.add(pnBoPhanBanHang, BorderLayout.SOUTH);
		JLabel lblIcon2 = new JLabel(new ImageIcon("images/user1.png"));
		btnBoPhanBanHang.add(lblIcon2,BorderLayout.CENTER);
		pnButton.add(btnBoPhanBanHang);
		
		btnBanQuanLy = new JButton();
		btnBanQuanLy.setPreferredSize(new  Dimension(190, 175));
		btnBanQuanLy.setBackground(Color.WHITE);
		btnBanQuanLy.setLayout(new BorderLayout());
		JPanel pnBanQuanLy = new JPanel();
		pnBanQuanLy.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnBanQuanLy.setBackground(Color.WHITE);
		JLabel lblBanQuanLy = new JLabel("Ban Quản Lý");
		lblBanQuanLy.setFont(new Font("", Font.BOLD, 15));
		pnBanQuanLy.add(lblBanQuanLy);
		btnBanQuanLy.add(pnBanQuanLy, BorderLayout.SOUTH);
		JLabel lblIcon3 = new JLabel(new ImageIcon("images/admin2.png"));
		btnBanQuanLy.add(lblIcon3,BorderLayout.CENTER);
		pnButton.add(btnBanQuanLy);
		
		btnDangXuat = new JButton();
		btnDangXuat.setPreferredSize(new  Dimension(190, 175));
		btnDangXuat.setBackground(Color.WHITE);
		btnDangXuat.setLayout(new BorderLayout());
		JPanel pnDangXuat = new JPanel();
		pnDangXuat.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnDangXuat.setBackground(Color.WHITE);
		JLabel lblDangXuat = new JLabel("Đăng Xuất");
		lblDangXuat.setFont(new Font("", Font.BOLD, 15));
		pnDangXuat.add(lblDangXuat);
		btnDangXuat.add(pnDangXuat, BorderLayout.SOUTH);
		JLabel lblIcon4 = new JLabel(new ImageIcon("images/logout.png"));
		btnDangXuat.add(lblIcon4,BorderLayout.CENTER);
		pnButton.add(btnDangXuat);
		
		btnThoat = new JButton();
		btnThoat.setPreferredSize(new  Dimension(190, 175));
		btnThoat.setBackground(Color.WHITE);
		btnThoat.setLayout(new BorderLayout());
		JPanel pnThoat = new JPanel();
		pnThoat.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnThoat.setBackground(Color.WHITE);
		JLabel lblThoat = new JLabel("Thoát");
		lblThoat.setFont(new Font("", Font.BOLD, 15));
		pnThoat.add(lblThoat);
		btnThoat.add(pnThoat, BorderLayout.SOUTH);
		JLabel lblIcon5 = new JLabel(new ImageIcon("images/exit2.png"));
		btnThoat.add(lblIcon5,BorderLayout.CENTER);
		pnButton.add(btnThoat);
		
		con.add(pnButton, BorderLayout.CENTER);
		
		JPanel pnGioiThieu = new JPanel();
		pnGioiThieu.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblGioiThieu = new JLabel("Địa chỉ: 15c khu phố 6 p.Hiệp Phú Q.9, SĐT: 0394420076");
		lblGioiThieu.setFont(new Font("", Font.ITALIC, 20));
		pnGioiThieu.add(lblGioiThieu);
		con.add(pnGioiThieu, BorderLayout.SOUTH);
		
		/*JPanel pnTest = new JPanel();
		DangNhapUI ui = new DangNhapUI("");
		JLabel lblten = new JLabel("xin chào: "+ui.nhanVienLogin()+"!");
		pnTest.add(lblten);
		con.add(pnTest,BorderLayout.NORTH);*/
	}
	public void addEvents(){
		btnDangXuat.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				DangNhapUI ui = new DangNhapUI("Login");
				ui.showWindows();
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
		btnBoPhanQuanLyKho.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				BoPhanQuanLyKhoUI ui = new BoPhanQuanLyKhoUI("Bộ phận quản lý kho");
				ui.showWindows();
			}
		});
		btnBoPhanBanHang.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				BoPhanBanHangUI ui = new BoPhanBanHangUI("Bộ phận bán hàng");
				ui.showWindows();
			}
		});
		btnBanQuanLy.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				BanQuanLyUI ui = new BanQuanLyUI("Ban quản lý");
				ui.showWindows();
			}
		});
	}
	public void showWindows(){
		this.setSize(1000, 320);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
